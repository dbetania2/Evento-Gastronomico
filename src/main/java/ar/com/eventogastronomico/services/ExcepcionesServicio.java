package ar.com.eventogastronomico.services;
import ar.com.eventogastronomico.model.*;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

//validarEntradaInt
//validarFecha
//validarCadenaNoVacia

public class ExcepcionesServicio {

    // Metodo para validar y obtener un entero desde la entrada del usuario
    public static int validarEntradaInt(Scanner scanner) {
        while (true) {
            try {
                int valor = scanner.nextInt();
                scanner.nextLine(); // Limpia el buffer en caso de error
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Debe ser un número entero.");
                scanner.nextLine(); // Limpia el buffer en caso de error
            }
        }
    }

    // Metodo para validar y obtener una fecha en formato YYYY-MM-DD
    public static LocalDate validarFecha(Scanner scanner) {
        while (true) {
            try {
                String fechaString = scanner.nextLine();
                LocalDate fecha = LocalDate.parse(fechaString); // Intentar parsear la fecha
                return fecha;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Asegúrese de usar el formato YYYY-MM-DD.");
            }
        }
    }

    // Metodo para validar y obtener una cadena no vacía
    public static String validarCadenaNoVacia(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            if (input != null && !input.trim().isEmpty()) {
                return input;
            } else {
                System.out.println("Entrada inválida. No debe estar vacía.");
            }
        }
    }

    // Metodo para validar y obtener una hora en formato 24 horas (0-23)
    public static int validarHora(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Ingrese la hora (0-23):");
                int hora = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de linea

                // Validar rango de hora
                if (hora < 0 || hora > 23) {
                    System.out.println("Hora inválida. Debe estar entre 0 y 23.");
                } else {
                    return hora;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Debe ser un número entero.");
                scanner.nextLine(); // Limpia el buffer en caso de error
            }
        }
    }
}
