package ar.com.eventogastronomico.services;

import ar.com.eventogastronomico.model.EventoGastronomico;
import ar.com.eventogastronomico.model.Fecha;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FechaServicio {
    private Map<LocalDate, Fecha> fechas;

    public FechaServicio() {
        this.fechas = new HashMap<>();
    }

    // Metodo para verificar la fecha y agregar el evento
    public void verificarFechaEvento(LocalDate fecha, EventoGastronomico evento) {
        // Buscar la instancia de Fecha para la fecha dada
        Fecha fechaObj = fechas.get(fecha);

        // Si la instancia de Fecha no existe, crear una nueva
        if (fechaObj == null) {
            fechaObj = new Fecha(fecha);
            fechas.put(fecha, fechaObj);
        }

        // Agregar el evento a la lista de eventos de la instancia de Fecha correspondiente
        fechaObj.getEventos().add(evento);
    }

    // Metodo para agregar un evento a una fecha
    public void agregarEvento(LocalDate fecha, EventoGastronomico evento) {
        // Llamar a verificarFechaEvento para manejar la creacion de la instancia de Fecha si es necesario
        verificarFechaEvento(fecha, evento);
    }

    // Metodo para mostrar todas las fechas y sus eventos
    public void mostrarFechas() {
        for (Fecha fecha : fechas.values()) {
            System.out.println(fecha);
        }
    }

    // Buscar fecha en fechas y devolver instancia
    public Fecha buscarFecha(LocalDate fecha) {
        return fechas.get(fecha);
    }

    // Metodo para obtener eventos por fecha
    public List<EventoGastronomico> obtenerEventosPorFecha(LocalDate fecha) {
        Fecha fechaObj = fechas.get(fecha);
        return fechaObj != null ? fechaObj.getEventos() : new ArrayList<>();
    }

    // Metodo para obtener el Map de fechas
    public Map<LocalDate, Fecha> getFechas() {
        return fechas;
    }


    // Metodo para eliminar un evento dado la fecha y el evento
    public void eliminarEvento(LocalDate fecha, EventoGastronomico evento) {
        Fecha fechaObj = fechas.get(fecha);
        if (fechaObj != null) {
            fechaObj.getEventos().remove(evento);
            // si no hay eventos para esa fecha, elimina la entrada del mapa
            if (fechaObj.getEventos().isEmpty()) {
                fechas.remove(fecha);
            }
        }
    }

    // Metodo para eliminar un evento dado solo el evento (sin fecha)
    public void eliminarEvento(EventoGastronomico evento) {
        // Obtener la fecha actual del evento
        LocalDate fechaEvento = evento.getFecha();

        // Eliminar el evento usando la fecha
        eliminarEvento(fechaEvento, evento);
    }
}
