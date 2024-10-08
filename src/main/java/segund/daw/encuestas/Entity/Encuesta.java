package segund.daw.encuestas.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;


@Entity
@Table(name="encuestas")



public class Encuesta {

    @Id     //Esta anotación especifica que este campo va a ser la clave principal de la tabla en la base de datos
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //Esta anotación especifica que la clave primaria sea "auto-increment"
    private Long id;


    @Size(min = 2, message = "El nombre ha de tener como mínimo dos caracteres.")
    @NotEmpty(message = "El nombre no puede estar en blanco. Además debe tener como mínimo dos carácteres")
    private String nombre;

    @Size(min = 2, message = "El apellido ha de tener como mínimo dos caracteres.")
    @NotEmpty(message = "Los apellidos no pueden estar en blanco. Además deben tener como mínimo dos carácteres")
    private String apellido1;

    @Size(min = 2, message = "El apellido ha de tener como mínimo dos caracteres.")
    @NotEmpty(message = "Los apellidos no pueden estar en blanco. Además deben tener como mínimo dos carácteres")
    private String apellido2;

    @Size(min = 9, message = "El número de teléfono tiene menos de 9 digitos. El número de teléfono ha de tener 9 digitos")
    @Size(max = 9, message = "El número de teléfono tiene mas de 9 digitos. El número de teléfono ha de tener 9 digitos")
    @NotEmpty(message = "El teléfono no puede estar en blanco")
    private String telefono;


    @Email
    @NotEmpty(message = "El Email no puede estar en blanco")
    private String email;

    @Min(value = 18, message = "Debes de ser mayor de edad para rellenar esta encuesta.")
    private Integer edad;

    @PastOrPresent
    private LocalDate fechaInicioVisita;

    @NotNull
    private String motivoVisita;

    @NotNull
    private String nivelSatisfacion;

    private String observaciones;

    private Boolean restaurante = false;

    private Boolean gimnasio = false;

    private  Boolean spa = false;

    private  Boolean piscina = false;

    private Boolean roomService = false;

    public Encuesta() {

    }

    public Encuesta(Long id, String nombre, String apellido1, String apellido2, String telefono,String email, Integer edad, LocalDate fechaInicioVisita, String motivoVisita, String nivelSatisfacion, String observaciones, Boolean restaurante, Boolean gimnasio, Boolean spa, Boolean piscina, boolean roomService) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
        this.edad = edad;
        this.fechaInicioVisita = fechaInicioVisita;
        this.motivoVisita = motivoVisita;
        this.nivelSatisfacion = nivelSatisfacion;
        this.observaciones = observaciones;
        this.restaurante = restaurante;
        this.gimnasio = gimnasio;
        this.spa = spa;
        this.piscina = piscina;
        this.roomService = roomService;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setRoomService(Boolean roomService) {
        this.roomService = roomService;
    }

    public String getTelefono() {
        return telefono;
    }

    public Boolean getRoomService() {
        return roomService;
    }

    public void setRestaurante(Boolean restaurante) {
        this.restaurante = restaurante;
    }

    public void setGimnasio(Boolean gimnasio) {
        this.gimnasio = gimnasio;
    }

    public void setPiscina(Boolean piscina) {
        this.piscina = piscina;
    }

    public void setSpa(Boolean spa) {
        this.spa = spa;
    }

    public void setRoomService(boolean roomService) {
        this.roomService = roomService;
    }

    public Boolean getRestaurante() {
        return restaurante;
    }

    public Boolean getGimnasio() {
        return gimnasio;
    }

    public Boolean getSpa() {
        return spa;
    }

    public Boolean getPiscina() {
        return piscina;
    }

    public boolean isRoomService() {
        return roomService;
    }

    public Long getId() {
        return id;
    }

    public  String getObservaciones() {
        return observaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public  String getApellido2() {
        return apellido2;
    }

    public  String getEmail() {
        return email;
    }

    public  Integer getEdad() {
        return edad;
    }

    public  LocalDate getFechaInicioVisita() {
        return fechaInicioVisita;
    }

    public String getMotivoVisita() {
        return motivoVisita;
    }

    public  String getNivelSatisfacion() {
        return nivelSatisfacion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido2( String apellido2) {
        this.apellido2 = apellido2;
    }

    public void setEmail( String email) {
        this.email = email;
    }

    public void setEdad( Integer edad) {
        this.edad = edad;
    }

    public void setFechaInicioVisita( LocalDate fechaInicioVisita) {
        this.fechaInicioVisita = fechaInicioVisita;
    }

    public void setMotivoVisita( String motivoVisita) {
        this.motivoVisita = motivoVisita;
    }

    public void setNivelSatisfacion( String nivelSatisfacion) {
        this.nivelSatisfacion = nivelSatisfacion;
    }

    public void setObservaciones( String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "Encuesta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", email='" + email + '\'' +
                ", edad=" + edad +
                ", fechaInicioVisita=" + fechaInicioVisita +
                ", motivoVisita='" + motivoVisita + '\'' +
                ", nivelSatisfacion='" + nivelSatisfacion + '\'' +
                '}';
    }
}
