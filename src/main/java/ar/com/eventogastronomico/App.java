package ar.com.eventogastronomico;

import ar.com.eventogastronomico.services.EventoServicio;
import ar.com.eventogastronomico.services.ExcepcionesServicio;
import ar.com.eventogastronomico.services.FechaServicio;
import ar.com.eventogastronomico.services.ParticipanteServicio;
import ar.com.eventogastronomico.model.Participante;  // Asegúrate de tener esta importación

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear instancias de los servicios necesarios
        FechaServicio fechaServicio = new FechaServicio();
        EventoServicio eventoServicio = new EventoServicio(fechaServicio);
        ParticipanteServicio participanteServicio = new ParticipanteServicio(fechaServicio, eventoServicio);

        int opcion;
        do {
            // Selección del rol
            System.out.println("Seleccione su identidad:");
            System.out.println("1. Organizador");
            System.out.println("2. Participante");
            System.out.println("3. Salir");
            System.out.print("Ingrese opción: ");
            opcion = ExcepcionesServicio.validarEntradaInt(scanner); // Validacion de entero

            switch (opcion) {
                case 1:
                    // Mostrar menúu para organizador
                    eventoServicio.mostrarMenu();
                    break;
                case 2:
                    Participante participante = validarParticipante(participanteServicio, scanner);
                    // Mostrar menu para participante
                    participanteServicio.menuParticipante(participante);
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 3);

        scanner.close();
    }

    private static Participante validarParticipante(ParticipanteServicio participanteServicio, Scanner scanner) {
        System.out.println("¿Es un nuevo participante o un participante existente?");
        System.out.println("1. Nuevo Participante");
        System.out.println("2. Participante Existente");
        System.out.print("Ingrese opcion: ");
        int opcion = ExcepcionesServicio.validarEntradaInt(scanner); // Validacion de entero

        if (opcion == 1) {
            // Registrar nuevo participante
            System.out.print("Ingrese su nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese su apellido: ");
            String apellido = scanner.nextLine();
            System.out.print("Ingrese su DNI: ");
            String dni = scanner.nextLine();
            System.out.print("Ingrese sus intereses culinarios: ");
            String interesCulinario = scanner.nextLine();

            Participante nuevoParticipante = participanteServicio.registrarNuevoParticipante(nombre, apellido, dni, interesCulinario);
            return nuevoParticipante;
        } else if (opcion == 2) {
            // Validar participante existente
            System.out.print("Ingrese su DNI: ");
            String dni = scanner.nextLine();

            Participante participanteExistente = participanteServicio.buscarParticipantePorDni(dni);
            if (participanteExistente != null) {
                return participanteExistente;
            } else {
                System.out.println("Participante no encontrado.");
                return validarParticipante(participanteServicio, scanner); // Reintentar si no encuentra al participante
            }
        } else {
            System.out.println("Opción no válida. Por favor, intente nuevamente.");
            return validarParticipante(participanteServicio, scanner); // Reintentar si la opción es inválida
        }
    }
}
