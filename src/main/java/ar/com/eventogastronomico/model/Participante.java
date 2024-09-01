package ar.com.eventogastronomico.model;
import java.util.ArrayList;
import java.util.List;


public class Participante {
    private String dni;
    private String nombre;
    private String apellido;
    private String interesesCulinarios;
    private List<EventoGastronomico> historialEventos;
    private List<Reseña> reseñas; // Atributo para almacenar las reseñas del participante


    public Participante(String interesesCulinarios,String dni, String nombre, String apellido) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.interesesCulinarios = interesesCulinarios;
        this.historialEventos = new ArrayList<>();
        this.reseñas = new ArrayList<>(); // Inicializar la lista de reseñas
    }

    // Getters y setters
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getInteresesCulinarios() { return interesesCulinarios; }
    public void setInteresesCulinarios(String interesesCulinarios) { this.interesesCulinarios = interesesCulinarios; }

    public List<EventoGastronomico> getHistorialEventos() { return historialEventos; }
    public void setHistorialEventos(List<EventoGastronomico> historialEventos) { this.historialEventos = historialEventos; }

    public List<Reseña> getReseñas() { return reseñas; }
    public void setReseñas(List<Reseña> reseñas) { this.reseñas = reseñas; }

    @Override
    public String toString() {
        // Verifica si la lista es null y usa una cadena adecuada
        String historialEventosInfo = (historialEventos != null && !historialEventos.isEmpty())
                ? historialEventos.size() + " evento(s)"
                : "No hay eventos en el historial";

        String reseñasInfo = (reseñas != null && !reseñas.isEmpty())
                ? reseñas.size() + " reseña(s)"
                : "No hay reseñas";

        return "Participante {\n" +
                "  dni='" + dni + "'\n" +
                "  nombre='" + nombre + "'\n" +
                "  apellido='" + apellido + "'\n" +
                "  interesesCulinarios='" + interesesCulinarios + "'\n" +
                "  historialEventos=" + historialEventosInfo + "\n" +
                "  reseñas=" + reseñasInfo + "\n" +
                '}';
    }

}
