package ar.com.eventogastronomico.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Fecha {
    private LocalDate fecha;

    private List<EventoGastronomico> eventos;

    public Fecha(LocalDate fecha) {
        this.fecha = fecha;
        this.eventos = new ArrayList<>();
    }

    // Getter para fecha
    public LocalDate getFecha() {
        return fecha;
    }

    // Setter para fecha
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }


    // Getter para eventos
    public List<EventoGastronomico> getEventos() {
        return eventos;
    }

    // Setter para eventos
    public void setEventos(List<EventoGastronomico> eventos) {
        this.eventos = eventos;
    }

    @Override
    public String toString() {
        // Verifica si la lista es null o está vacía
        String eventosInfo = (eventos != null && !eventos.isEmpty())
                ? eventos.size() + " evento(s)"
                : "No hay eventos";

        return "Fecha {\n" +
                "  fecha=" + fecha + "\n" +
                "  eventos=" + eventosInfo + "\n" +
                '}';
    }
}

