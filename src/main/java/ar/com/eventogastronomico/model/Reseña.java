package ar.com.eventogastronomico.model;

import java.util.UUID;

public class Reseña {
    private UUID id;
    private EventoGastronomico evento;
    private Participante participante;
    private int clasificacion; // Debe estar en el rango 1-5
    private String comentario;
    //fecha

    public Reseña(EventoGastronomico evento, Participante participante, int clasificacion, String comentario) {

    }
    public Reseña(UUID id, EventoGastronomico evento, Participante participante, int clasificacion, String comentario) {
        this.id = UUID.randomUUID(); // Genera un UUID único
        this.evento = evento;
        this.participante = participante;
        this.clasificacion = clasificacion;
        this.comentario = comentario;
    }

    // Getters y setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public EventoGastronomico getEvento() { return evento; }
    public void setEvento(EventoGastronomico evento) { this.evento = evento; }

    public Participante getParticipante() { return participante; }
    public void setParticipante(Participante participante) { this.participante = participante; }

    public int getClasificacion() { return clasificacion; }
    public void setClasificacion(int clasificacion) { this.clasificacion = clasificacion; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    @Override
    public String toString() {
        // Validacion para la clasificacion
        String clasificacionStr = (clasificacion >= 1 && clasificacion <= 5)
                ? String.valueOf(clasificacion)
                : "No asignada";

        return "Reseña {\n" +
                "  id=" + (id != null ? id.toString() : "No asignado") + "\n" +
                "  evento=" + (evento != null ? evento.getNombre() : "No asignado") + "\n" +
                "  participante=" + (participante != null ? participante.getNombre() : "No asignado") + "\n" +
                "  clasificacion=" + clasificacionStr + "\n" +
                "  comentario='" + (comentario != null ? comentario : "Sin comentario") + "'\n" +
                '}';
    }
}
