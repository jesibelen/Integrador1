package com.company.ada;
import java.time.LocalDate;

public class Auto extends Automotor implements VehiculoCombustion{
    private Combustible combustible;

    public Auto(String patente, Marca marca, int anioAuto, Propietario propietario, TipoUso uso, Combustible combustible){
        super(patente, marca, anioAuto, propietario, uso);
        this.combustible=combustible;
    }

    public Auto(String patente, Marca marca, int anioAuto, Propietario propietario, TipoUso uso, Combustible combustible, LocalDate fechaDeAlta ) {
        super(patente, marca, anioAuto, propietario, uso, fechaDeAlta);
        this.combustible=combustible;
    }
    public Combustible getCombustible() {
        return combustible;
    }

    @Override
    public String toString() {
        return "COMBUSTIBLE: " + combustible;
    }
}


