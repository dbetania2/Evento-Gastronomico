package ar.com.eventogastronomico.services;
import ar.com.eventogastronomico.model.EventoGastronomico;
import ar.com.eventogastronomico.model.Participante;
import ar.com.eventogastronomico.model.Reseña;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParticipanteServicio {

    private List<Participante> participantes;
    private FechaServicio fechaServicio;
    private EventoServicio eventoServicio;

    // Constructor con inyección de dependencias
    public ParticipanteServicio(FechaServicio fechaServicio, EventoServicio eventoServicio) {
        this.fechaServicio = fechaServicio;
        this.eventoServicio = eventoServicio;
    }

    // --------------------------------- menú para participantes-----------------------------------------------
    public void menuParticipante(Participante participante) {
        Scanner scanner = new Scanner(System.in);
        EventoGastronomico eventoSeleccionado = null;
        int opcion;

        do {
            System.out.println("Menú del Participante:");
            System.out.println("1. Inscribirse en Evento");
            System.out.println("2. Ver Detalles de un Evento");
            System.out.println("3. Dejar Reseña");
            System.out.println("4. Ver mis reseñas");
            System.out.println("5. Ver Historial de mis eventos");
            System.out.println("6. Volver");
            System.out.print("Ingrese su opción: ");

            opcion = ExcepcionesServicio.validarEntradaInt(scanner); // Validacion de entero

            switch (opcion) {
                case 1:
                    eventoSeleccionado = eventoServicio.menuDeBusquedaEvento();
                    if (eventoSeleccionado != null) {
                        inscribirseEnEvento(participante);
                    } else {
                        System.out.println("No se ha seleccionado ningún evento.");
                    }
                    break;

                case 2:
                    if (eventoSeleccionado != null) {
                        eventoServicio.detallesEvento(eventoSeleccionado);
                    } else {
                        System.out.println("Primero seleccione un evento.");
                    }
                    break;

                case 3:
                    eventoSeleccionado = eventoServicio.menuDeBusquedaEvento(); // Asignar el evento seleccionado
                    if (eventoSeleccionado != null) {
                        dejarReseña(eventoSeleccionado, participante);
                    } else {
                        System.out.println("No se ha seleccionado ningún evento.");
                    }
                    break;

                case 4:
                    verMisReseñas(participante);
                    break;

                case 5:
                    mostrarHistorialDeEventos(participante);
                    break;

                case 6:
                    System.out.println("Saliendo del menú...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 6);
    }


    // Método para inscribirse en un evento------------------------------------------------------
    private void inscribirseEnEvento(Participante participante) {
        EventoGastronomico eventoSeleccionado = eventoServicio.menuDeBusquedaEvento();
        if (eventoSeleccionado != null) {
            eventoServicio.agregarParticipante(eventoSeleccionado, participante);
            // Agregar evento al historial del participante
            agregarEventoAlHistorial(participante,eventoSeleccionado);
        } else {
            System.out.println("Evento no encontrado.");
        }
    }

    // Método para agregar un evento al historial de un participante-------------------------------
    public void agregarEventoAlHistorial(Participante participante, EventoGastronomico evento) {
        List<EventoGastronomico> historialEventos = participante.getHistorialEventos();
        historialEventos.add(evento);
    }

    //Mostrar historial evento--------------------------------------------------------------------
    public void mostrarHistorialDeEventos(Participante participante) {
        List<EventoGastronomico> historial = participante.getHistorialEventos();
        if (historial.isEmpty()) {
            System.out.println("No has asistido a ningún evento.");
        } else {
            System.out.println("Historial de eventos asistidos:");
            for (EventoGastronomico evento : historial) {
                System.out.println("- " + evento.getNombre());
            }
        }
    }

    //Dejar reseña-------------------------------------------------------------------------------
    public void dejarReseña(EventoGastronomico evento, Participante participante) {
        Scanner scanner = new Scanner(System.in);

        // Verificar si el participante asistio al evento
        if (!participante.getHistorialEventos().contains(evento)) {
            System.out.println("No puedes dejar una reseña para este evento porque no te has inscrito.");
            return;
        }

        System.out.println("Ingrese su calificación (1-5):");
        int clasificacion = ExcepcionesServicio.validarEntradaInt(scanner); // Validacion entero

        // Validación de la calificacion
        if (clasificacion < 1 || clasificacion > 5) { // Validación de rango
            System.out.println("Clasificación inválida. Debe estar entre 1 y 5.");
            return;
        }

        System.out.println("Ingrese su comentario:");
        String comentario = ExcepcionesServicio.validarCadenaNoVacia(scanner); // Validacion cadena no vacía

        // Crear una nueva reseña y agregarla al evento
        Reseña reseña = new Reseña(evento, participante, clasificacion, comentario);
        eventoServicio.agregarReseña(evento, reseña);

        System.out.println("¡Gracias por su reseña!");
    }


    // Ver todas las reseñas hechas por un participante--------------------------------------
    public void verMisReseñas(Participante participante) {
        // Obtener reseñas del participante
        List<Reseña> reseñas = obtenerReseñasPorParticipante(participante);

        if (reseñas.isEmpty()) {
            System.out.println("No ha dejado ninguna reseña.");
        } else {
            System.out.println("Sus reseñas:");

            // Mostrar cada reseña
            for (Reseña reseña : reseñas) {
                System.out.println("Evento: " + reseña.getEvento().getNombre());
                System.out.println("Calificación: " + reseña.getClasificacion());
                System.out.println("Comentario: " + reseña.getComentario());
                System.out.println("------------");
            }
        }
    }


    // Metodo para obtener reseñas por participante
    public List<Reseña> obtenerReseñasPorParticipante(Participante participante) {
        List<Reseña> reseñasDelParticipante = new ArrayList<>();

        // Buscar reseñas del participante en todos los eventos
        for (EventoGastronomico evento : eventoServicio.getEventos()) {
            // Recorrer todas las reseñas del evento
            for (Reseña reseña : evento.getReseñas()) {
                // Verificar si la reseña pertenece al participante dado
                if (reseña.getParticipante().equals(participante)) {
                    // Agregar la reseña a la lista de reseñas del participante
                    reseñasDelParticipante.add(reseña);
                }
            }
        }

        return reseñasDelParticipante;
    }




    //Registrar participante desde validacion --------------------------------------------
    public Participante registrarNuevoParticipante(String nombre, String apellido, String dni,String interesCulinario) {
        if (participantes == null) {
            participantes = new ArrayList<>(); // Inicializar la lista si es null
        }
        Participante participante = new Participante(nombre, apellido, dni,interesCulinario);
        participantes.add(participante);
        System.out.println("Participante registrado con éxito.");
        return participante;
    }

    // Buscar un participante por DNI----------------------------------------------------
    public Participante buscarParticipantePorDni(String dni) {
        for (Participante participante : participantes) {
            if (participante.getDni().equalsIgnoreCase(dni)) {
                return participante;
            }
        }
        return null;
    }

}
