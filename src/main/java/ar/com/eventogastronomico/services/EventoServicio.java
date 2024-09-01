package ar.com.eventogastronomico.services;
import ar.com.eventogastronomico.model.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;

public class EventoServicio {

    private FechaServicio fechaServicio;




    // Constructor con FechaServicio
    public EventoServicio(FechaServicio fechaServicio) {
        this.fechaServicio = fechaServicio; // Usa la instancia pasada

    }
    // --------------------------------- menú para organizadores-----------------------------------------------
    public void mostrarMenu() {
        EventoGastronomico eventoSeleccionado=null;
        Scanner scanner = new Scanner(System.in);


        int opcion;
        do {
            System.out.println("Menú del Organizador:");
            System.out.println("1. Ver detalles de un Evento");
            System.out.println("2. Crear Evento");
            System.out.println("3. Eliminar Evento");
            System.out.println("4. Editar Evento");
            System.out.println("5. Listar eventos disponibles (no se alcanzo el cupo)");
            System.out.println("6. Exportar Eventos No Disponibles (se alcanzo el cupo)");
            System.out.println("7. Volver");
            System.out.print("Ingrese su opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                     eventoSeleccionado= menuDeBusquedaEvento();
                    detallesEvento(eventoSeleccionado);
                    break;
                case 2:
                    crearEvento();
                    break;
                case 3:
                    eventoSeleccionado= menuDeBusquedaEvento();
                    fechaServicio.eliminarEvento(eventoSeleccionado);
                    break;
                case 4:
                     eventoSeleccionado= menuDeBusquedaEvento();
                    editarEvento(eventoSeleccionado);

                    break;
                case 5:
                    listarEventosDisponibles();
                    break;
                case 6:
                    exportarEventosNoDisponibles();
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 7);

    }

    //Metodo para ver detalles de un evento-----------------------------------------------------------------------------
    public void detallesEvento(EventoGastronomico evento) {
        evento.toString();
    }

    //Metodo listar eventos por fecha


    //Metodo buscar evento por nombre----------------------------------------------------------------------------------
    private EventoGastronomico buscarEventoPorNombre(String nombre) {
        // Iterar sobre todas las fechas en FechaServicio
        for (Fecha fecha : fechaServicio.getFechas().values()) {
            for (EventoGastronomico evento : fecha.getEventos()) {
                if (evento.getNombre().equalsIgnoreCase(nombre)) {
                    return evento; // Retornar el evento encontrado
                }
            }
        }
        System.out.println("Evento no encontrado.");
        return null; // Si no se encuentra, retornar null
    }

    // Metodo para buscar un evento-------------------------------------------------------------------------------------

    public EventoGastronomico menuDeBusquedaEvento() {
        Scanner scanner = new Scanner(System.in);
        EventoGastronomico eventoSeleccionado = null;

        System.out.println("¿Cómo desea buscar el evento?");
        System.out.println("1. Lista de todas las fechas");
        System.out.println("2. Buscar un evento específico por nombre");
        System.out.println("3. Lista de eventos de una fecha en específico");

        int opcion = ExcepcionesServicio.validarEntradaInt(scanner); // Validación de entero

        switch (opcion) {
            case 1:
                // Mostrar todas las fechas y eventos
                fechaServicio.mostrarFechas(); // Muestra todas las fechas y sus eventos

                System.out.println("Ingrese la fecha del evento (YYYY-MM-DD):");
                LocalDate fechaBuscada = ExcepcionesServicio.validarFecha(scanner); // Validación de fecha

                // Obtener lista de eventos para la fecha proporcionada
                List<EventoGastronomico> eventos = fechaServicio.obtenerEventosPorFecha(fechaBuscada);
                if (eventos.isEmpty()) {
                    System.out.println("No hay eventos en esta fecha.");
                    break;
                }

                // Mostrar eventos numerados
                System.out.println("Seleccione el número del evento que desea seleccionar:");
                for (int i = 0; i < eventos.size(); i++) {
                    System.out.println((i + 1) + ". " + eventos.get(i));
                }

                int numeroEvento = ExcepcionesServicio.validarEntradaInt(scanner); // Validación de entero

                // Buscar y mostrar el evento seleccionado
                if (numeroEvento > 0 && numeroEvento <= eventos.size()) {
                    eventoSeleccionado = eventos.get(numeroEvento - 1);
                    System.out.println("Evento seleccionado: " + eventoSeleccionado);
                } else {
                    System.out.println("Número de evento no válido.");
                }
                break;

            case 2:
                // Buscar evento por nombre
                System.out.println("Ingrese el nombre del evento:");
                String nombre = ExcepcionesServicio.validarCadenaNoVacia(scanner); // Validación de cadena no vacía
                eventoSeleccionado = buscarEventoPorNombre(nombre);
                if (eventoSeleccionado == null) {
                    System.out.println("Evento no encontrado.");
                } else {
                    System.out.println("Evento encontrado: " + eventoSeleccionado);
                }
                break;

            case 3:
                // Listar eventos de una fecha específica
                System.out.println("Ingrese la fecha para listar eventos (formato YYYY-MM-DD):");
                fechaBuscada = ExcepcionesServicio.validarFecha(scanner); // Validación de fecha

                // Buscar el objeto Fecha para la fecha dada
                Fecha fechaEncontrada = fechaServicio.buscarFecha(fechaBuscada);
                if (fechaEncontrada == null) {
                    System.out.println("No hay eventos para la fecha ingresada.");
                } else {
                    System.out.println("Eventos en la fecha " + fechaBuscada + ":");

                    // Mostrar eventos numerados para la fecha encontrada
                    List<EventoGastronomico> eventosPorFecha = fechaEncontrada.getEventos();
                    if (eventosPorFecha.isEmpty()) {
                        System.out.println("No hay eventos en esta fecha.");
                    } else {
                        for (int i = 0; i < eventosPorFecha.size(); i++) {
                            System.out.println((i + 1) + ". " + eventosPorFecha.get(i));
                        }
                        System.out.println("Ingrese el número del evento que desea seleccionar:");
                        int numeroEventoSeleccionado = ExcepcionesServicio.validarEntradaInt(scanner); // Validación de entero
                        if (numeroEventoSeleccionado > 0 && numeroEventoSeleccionado <= eventosPorFecha.size()) {
                            eventoSeleccionado = eventosPorFecha.get(numeroEventoSeleccionado - 1);
                            System.out.println("Evento seleccionado: " + eventoSeleccionado);
                        } else {
                            System.out.println("Número de evento no válido.");
                        }
                    }
                }
                break;

            default:
                System.out.println("Opción no válida.");
                break;
        }

        return eventoSeleccionado;
    }




    // Metodo para creaaar eventos---------------------------------------------------------------------------------
    public void crearEvento() {

        Scanner scanner = new Scanner(System.in);

        // Nombre
        System.out.println("Nombre del Evento:");
        String nombre = ExcepcionesServicio.validarCadenaNoVacia(scanner); // Validacion de cadena no vacia

        // Descripción
        System.out.println("Descripción:");
        String descripcion = ExcepcionesServicio.validarCadenaNoVacia(scanner); // Validacion de cadena no vacia

        // Fecha
        LocalDate fecha = null;
        System.out.println("Ingrese fecha en formato (YYYY-MM-DD):");
        fecha = ExcepcionesServicio.validarFecha(scanner); // Validacion de fecha

        // Hora
        int hora = ExcepcionesServicio.validarHora(scanner); // Validación de hora


        // Ubicacion
        System.out.println("Ubicación:");
        String ubicacion = ExcepcionesServicio.validarCadenaNoVacia(scanner); // Validacion de cadena no vacia

        // Capacidad
        System.out.println("Capacidad:");
        int capacidad = ExcepcionesServicio.validarEntradaInt(scanner); // Validacion de entero

        // Chef
        Chef chef = null;
        System.out.println("Desea Asignar Chef?");
        System.out.println("1. Sí");
        System.out.println("2. No");
        int opcion = ExcepcionesServicio.validarEntradaInt(scanner); // Validacion de entero
        if (opcion == 1) {
            chef = agregarChef();
        }

        // Instanciar el objeto evento
        EventoGastronomico nuevoEvento = new EventoGastronomico(nombre, descripcion, fecha, hora, ubicacion, capacidad, chef);


        // Verificar y agregar el evento a la fecha
        fechaServicio.verificarFechaEvento(fecha, nuevoEvento);

        // Agregarlo
        fechaServicio.agregarEvento(fecha,nuevoEvento);
        System.out.println("El evento se creo perfectamente");
    }


    // Metodo para agregar un chef------------------------------------------------------------------
    public Chef agregarChef() {
        Scanner scanner = new Scanner(System.in);

        // Obtener el DNI del chef
        System.out.println("Ingrese el DNI del chef:");
        String dni = ExcepcionesServicio.validarCadenaNoVacia(scanner); // Validacion de cadena no vacia

        // Obtener el nombre del chef
        System.out.println("Ingrese el nombre del chef:");
        String nombre = ExcepcionesServicio.validarCadenaNoVacia(scanner); // Validacion de cadena no vacia

        // Obtener la especialidad del chef
        System.out.println("Ingrese la especialidad del chef:");
        String especialidad = ExcepcionesServicio.validarCadenaNoVacia(scanner); // Validacion de cadena no vacia

        // Crear una nueva instancia de Chef
        Chef nuevoChef = new Chef(dni, nombre, especialidad);
        return nuevoChef;
    }

    //Metodo para Editar evento ------------------------------------------------------------
    public void editarEvento(EventoGastronomico evento) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n¿Qué atributo desea editar?");
            System.out.println("1. Nombre");
            System.out.println("2. Descripción");
            System.out.println("3. Fecha");
            System.out.println("4. Ubicación");
            System.out.println("5. Capacidad");
            System.out.println("6. Chef");
            System.out.println("7. Terminar edición");

            // Lee la opción del usuario y valida que sea un entero
            opcion = ExcepcionesServicio.validarEntradaInt(scanner); // Validacion de entero

            switch (opcion) {
                case 1:

                    System.out.println("Nombre actual: " + evento.getNombre());
                    System.out.println("Ingrese nuevo nombre:");
                    evento.setNombre(ExcepcionesServicio.validarCadenaNoVacia(scanner)); // Validacion de cadena no vacía
                    break;

                case 2:

                    System.out.println("Descripción actual: " + evento.getDescripcion());
                    System.out.println("Ingrese nueva descripción:");
                    evento.setDescripcion(ExcepcionesServicio.validarCadenaNoVacia(scanner)); // Validacion de cadena no vacía
                    break;

                case 3:
                    // Mostrar la fecha actual del evento
                    System.out.println("Fecha actual: " + evento.getFecha());

                    // Obtener la nueva fecha
                    System.out.println("Ingrese nueva fecha en formato (YYYY-MM-DD):");
                    LocalDate nuevaFechaLocalDate = ExcepcionesServicio.validarFecha(scanner); // Validación de nueva fecha

                    // Obtener la fecha actual del evento
                    LocalDate fechaActualLocalDate = evento.getFecha();

                    // Eliminar el evento de la fecha actual
                    fechaServicio.eliminarEvento(fechaActualLocalDate, evento);

                    // Actualizar la fecha del evento a la nueva fecha
                    evento.setFecha(nuevaFechaLocalDate);

                    // Agregar el evento a la nueva fecha
                    fechaServicio.agregarEvento(nuevaFechaLocalDate, evento);

                    System.out.println("Fecha del evento actualizada exitosamente.");
                    break;


                case 4:

                    System.out.println("Ubicación actual: " + evento.getUbicacion());
                    System.out.println("Ingrese nueva ubicación:");
                    evento.setUbicacion(ExcepcionesServicio.validarCadenaNoVacia(scanner)); // Validacion de cadena no vacía
                    break;

                case 5:

                    System.out.println("Capacidad actual: " + evento.getCapacidad());
                    System.out.println("Ingrese nueva capacidad:");
                    evento.setCapacidad(ExcepcionesServicio.validarEntradaInt(scanner)); // Validacion de entero
                    break;

                case 6:

                    System.out.println("Chef actual: " + (evento.getChef() != null ? evento.getChef().getNombre() : "No asignado"));
                    System.out.println("Desea cambiar el chef?");
                    System.out.println("1. Si");
                    System.out.println("2. No");
                    int opcionChef = ExcepcionesServicio.validarEntradaInt(scanner); // Validacion de entero
                    if (opcionChef == 1) {
                        evento.setChef(agregarChef());
                    }
                    break;

                case 7:

                    System.out.println("Edición finalizada.");
                    break;

                default:

                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 7);
    }

    // Metodo para agregar una reseña a un evento específico
    public void agregarReseña(EventoGastronomico evento, Reseña reseña) {
        if (evento != null && reseña != null) {
            // Agregar la reseña al evento
            evento.getReseñas().add(reseña);  // retorna la lista de reseñas
            System.out.println("Reseña agregada exitosamente al evento: " + evento.getNombre());
        } else {
            System.out.println("Error: El evento o la reseña no pueden ser nulos.");
        }
    }


    public void agregarParticipante(EventoGastronomico evento, Participante participante) {
        if (evento != null) {
            if (evento.getParticipantes().size() < evento.getCapacidad()) {
                evento.getParticipantes().add(participante);
                System.out.println("Participante agregado al evento exitosamente.");
            } else {
                System.out.println("El evento ha alcanzado su capacidad máxima.");
            }
        } else {
            System.out.println("Evento no válido.");
        }
    }


    // Metodo para obtener todos los eventos
    public List<EventoGastronomico> getEventos() {
        List<EventoGastronomico> eventos = new ArrayList<>();
        for (Fecha fecha : fechaServicio.getFechas().values()) {
            eventos.addAll(fecha.getEventos());
        }
        return eventos;
    }

    //Listar eventos disponibles-----------
    public void listarEventosDisponibles() {
        System.out.println("¿Desea listar eventos disponibles de una fecha específica o de todas las fechas?");
        System.out.println("1. De una fecha específica");
        System.out.println("2. De todas las fechas");
        System.out.print("Ingrese su opción: ");

        Scanner scanner = new Scanner(System.in);

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        switch (opcion) {
            case 1:
                System.out.print("Ingrese la fecha en formato (YYYY-MM-DD): ");
                LocalDate fecha = ExcepcionesServicio.validarFecha(scanner); // Validar la fecha
                List<EventoGastronomico> eventosDisponibles = fechaServicio.obtenerEventosPorFecha(fecha);
                System.out.println("Eventos disponibles en la fecha " + fecha + ":");
                for (EventoGastronomico evento : eventosDisponibles) {
                    if (evento.getCapacidad() > evento.getParticipantes().size()) {
                        System.out.println(evento);
                    }
                }
                break;
            case 2:
                System.out.println("Eventos disponibles en todas las fechas:");
                for (Fecha f : fechaServicio.getFechas().values()) {
                    System.out.println("Fecha: " + f.getFecha());
                    for (EventoGastronomico evento : f.getEventos()) {
                        if (evento.getCapacidad() > evento.getParticipantes().size()) {
                            System.out.println(evento);
                        }
                    }
                }
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    // Metodo para exportar eventos no disponibles a un archivo de texto
    public void exportarEventosNoDisponibles() {
        System.out.println("¿Desea exportar eventos no disponibles de una fecha específica o de todas las fechas?");
        System.out.println("1. De una fecha específica");
        System.out.println("2. De todas las fechas");
        System.out.print("Ingrese su opción: ");
        Scanner scanner = new Scanner(System.in);

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de linea

        // Nombre del archivo para exportar
        String nombreArchivo = "eventos_no_disponibles.txt";

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(nombreArchivo)))) {
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la fecha en formato (YYYY-MM-DD): ");
                    LocalDate fecha = ExcepcionesServicio.validarFecha(scanner); // Validar la fecha
                    List<EventoGastronomico> eventosNoDisponibles = fechaServicio.obtenerEventosPorFecha(fecha);
                    writer.println("Eventos no disponibles en la fecha " + fecha + ":");
                    for (EventoGastronomico evento : eventosNoDisponibles) {
                        if (evento.getCapacidad() <= evento.getParticipantes().size()) {
                            writer.println(evento);
                        }
                    }
                    break;
                case 2:
                    writer.println("Eventos no disponibles en todas las fechas:");
                    for (Fecha f : fechaServicio.getFechas().values()) {
                        writer.println("Fecha: " + f.getFecha());
                        for (EventoGastronomico evento : f.getEventos()) {
                            if (evento.getCapacidad() <= evento.getParticipantes().size()) {
                                writer.println(evento);
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
            System.out.println("Eventos no disponibles exportados a " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
}
