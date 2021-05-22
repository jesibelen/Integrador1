package com.company.ada;

import java.time.LocalDate;

public class Camion extends Automotor implements VehiculoCombustion {
    private Combustible combustible;

    public Camion(){ }
    public Camion(String patente, Marca marca, int anioAuto, Propietario propietario, TipoUso uso, Combustible combustible) {
        super(patente, marca, anioAuto, propietario, uso);
        this.combustible= combustible;
    }
    public Camion(String patente, Marca marca, int anioAuto, Propietario propietario, TipoUso uso, Combustible combustible, LocalDate fechaDeAlta) {
        super(patente, marca, anioAuto, propietario, uso, fechaDeAlta);
        this.combustible= combustible;
    }
    public Combustible getCombustible(){
        return this.combustible;
    }

}
