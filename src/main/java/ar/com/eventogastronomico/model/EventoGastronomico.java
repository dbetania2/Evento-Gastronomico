package ar.com.eventogastronomico.model;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class EventoGastronomico {
    private UUID id;
    private String nombre;
    private String descripcion;
    private LocalDate fecha; // Fecha del evento
    private int hora; // Hora del evento
    private String ubicacion;
    private int capacidad;
    private Chef chef;
    private List<Participante> participantes;
    private List<Reseña> reseñas;

    public EventoGastronomico(String nombre, String descripcion, LocalDate fecha, int hora, String ubicacion, int capacidad, Chef chef) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.chef = chef;
        this.participantes = new ArrayList<>();
        this.reseñas = new ArrayList<>();
    }

    // Getters y setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public int getHora() { return hora; }
    public void setHora(int hora) { this.hora = hora; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public Chef getChef() { return chef; }
    public void setChef(Chef chef) { this.chef = chef; }

    public List<Participante> getParticipantes() { return participantes; }
    public void setParticipantes(List<Participante> participantes) { this.participantes = participantes; }

    public List<Reseña> getReseñas() { return reseñas; }
    public void setReseñas(List<Reseña> reseñas) { this.reseñas = reseñas; }

    @Override
    public String toString() {
        String participantesInfo = (participantes != null ? participantes.size() + " participantes" : "No hay participantes");
        String reseñasInfo = (reseñas != null ? reseñas.size() + " reseñas" : "No hay reseñas");

        return "EventoGastronomico {\n" +
                "  id=" + id + "\n" +
                "  nombre='" + nombre + "'\n" +
                "  descripcion='" + descripcion + "'\n" +
                "  fecha=" + fecha + "\n" +
                "  hora=" + hora + "\n" +
                "  ubicacion='" + ubicacion + "'\n" +
                "  capacidad=" + capacidad + "\n" +
                "  chef='" + (chef != null ? chef : "No asignado") + "'\n" +
                "  participantes=" + participantesInfo + "\n" +
                "  reseñas=" + reseñasInfo + "\n" +
                '}';
    }

}
