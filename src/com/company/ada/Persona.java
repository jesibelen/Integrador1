package com.company.ada;

public class Persona {
    private String nombre;
    private String apellido;
    private Integer dni;
    private String direccion;

    public Persona(String nombre, String apellido, Integer dni, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccion = direccion;
    }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public Integer getDni() { return dni; }
    public String getDireccion() { return direccion; }

    public void setDatosPersona(String nombre,String apellido,Integer dni,String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return
                "Nombre:" + nombre +
                        "\tApellido:" + apellido  +
                        "\tDNI:" + dni +
                        "\tDirección:" + direccion;
    }
}