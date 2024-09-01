package ar.com.eventogastronomico.model;

import java.util.ArrayList;
import java.util.List;

public class Chef {
    private String dni;
    private String nombre;
    private String especialidad;
    private List<EventoGastronomico> historialEventos;

    public Chef(String dni, String nombre, String especialidad) {
        this.dni = dni;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.historialEventos = new ArrayList<>();
    }

    // Getters y setters
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public List<EventoGastronomico> getHistorialEventos() { return historialEventos; }
    public void setHistorialEventos(List<EventoGastronomico> historialEventos) { this.historialEventos = historialEventos; }

    @Override
    public String toString() {
        // Verifica si la lista es null y usa una cadena adecuada
        String historialEventosInfo = (historialEventos != null ? historialEventos.size() + " eventos" : "No hay eventos en el historial");

        return "Chef {\n" +
                "  dni='" + dni + "'\n" +
                "  nombre='" + nombre + "'\n" +
                "  especialidad='" + especialidad + "'\n" +
                "  historialEventos=" + historialEventosInfo + "\n" +
                '}';
    }

}
