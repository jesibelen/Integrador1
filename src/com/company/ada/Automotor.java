package com.company.ada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Automotor {
    //Atributos
    private String patente;
    private Marca marca;
    private int anioAuto;
    private Propietario propietario;
    private TipoUso uso;
    private List<Autorizado> autorizados;
    private LocalDate fechaCambioProp;
    private LocalDate fechaDeAlta;
    private Combustible combustible;
    private Integer voltaje;

    public Automotor() {}
    public Automotor(String patente, Marca marca, int anioAuto, Propietario propietario, TipoUso uso, LocalDate fechaDeAlta) {
        this.patente = patente;
        this.marca = marca;
        this.anioAuto = anioAuto;
        this.propietario = propietario;
        this.uso = uso;
        this.autorizados = new ArrayList<>();
        this.fechaDeAlta = fechaDeAlta;
        this.combustible= null;
        this.voltaje=null;

    }
    public Automotor(String patente, Marca marca, int anioAuto, Propietario propietario, TipoUso uso) {
        this.patente = patente;
        this.marca = marca;
        this.anioAuto = anioAuto;
        this.propietario = propietario;
        this.uso = uso;
        this.autorizados = new ArrayList<>();
        this.fechaDeAlta = LocalDate.now();//Guardo automaticamente la fecha actual
        this.fechaCambioProp = null;
        this.combustible= null;
        this.voltaje=null;

    }

    public LocalDate getFechaDeAlta() {
        return fechaDeAlta;
    }

    public String getPatente() {
        return patente;
    }
    public Marca getMarca() {
        return marca;
    }
    public int getAnioAuto() {
        return anioAuto;
    }
    public Propietario getPropietario() {
        return propietario;
    }
    public TipoUso getUso() {
        return uso;
    }
    public List<Autorizado> getAutorizados() {
        return autorizados;
    }
    public LocalDate getFechaCambioProp() {
        return fechaCambioProp;
    }
    public void agregarAutorizado(Autorizado autorizado) {
        autorizados.add(autorizado);
    }
    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public void setFechaCambioProp(LocalDate fechaCambioProp) {
        this.fechaCambioProp = fechaCambioProp;
    }
    public String darTipoDeAuto() {
        return this.getClass().getSimpleName();//pasar el nombre de la Clase a string
    }

    public String obtenerFormatoFechReg() {
        String fechaConFormato = this.fechaDeAlta.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return fechaConFormato;
    }

    public void mostrarTodosLosDatos(String fechaConFormato, String claseAuto, String patente, Marca marca, int anio, Integer voltaje,Propietario propietario, Combustible combustible, List<Autorizado> autorizados) {
        System.out.println("\nFECHA DE REGISTRO: "+fechaConFormato);
        System.out.println("CATEGORIA: " +claseAuto);
        System.out.print("PATENTE: "+patente);
        System.out.print("\t\tMARCA: " +marca);
        System.out.print("\t\tAÑO DE FABRICACIÓN: " +anio);
        if (claseAuto.equals("MotoElectrica") || claseAuto.equals("AutoElectrico")) {
            System.out.print("\t\tVOLTAJE: " + voltaje+" V");
        } else {
            System.out.print("\t\tCOMBUSTIBLE: " + combustible);
        }
        System.out.println("\nPROPIETARIO: " + propietario);
        for (Autorizado autorizado: autorizados){
            System.out.println("AUTORIZADO: "+ autorizado);
        }
    }
    public String pedirN(){
        String propN= this.propietario.getNombre();
        return propN;
    }
    public String pedirA(){
        String propA= this.propietario.getApellido();
        return propA;
    }
    public String pedirDNI(){
        String propDNI= String.valueOf(propietario.getDni()) ;//this.propietario.getDni();
        return propDNI;
    }
}

