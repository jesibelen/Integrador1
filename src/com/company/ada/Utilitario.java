package com.company.ada;

import java.time.LocalDate;

public class Utilitario extends Automotor implements VehiculoCombustion{
    private Combustible combustible;
    //Constructor
    public Utilitario(){}
    public Utilitario(String patente, Marca marca, int anioAuto, Propietario propietario, TipoUso uso, Combustible combustible) {
        super(patente, marca, anioAuto, propietario, uso);
        this.combustible= combustible;
    }
    public Utilitario(String patente, Marca marca, int anioAuto, Propietario propietario, TipoUso uso,  LocalDate fechaDeAlta) {
        super(patente, marca, anioAuto, propietario, uso,fechaDeAlta);
    }

    public Combustible getCombustible() {
        return combustible;
    }
}
