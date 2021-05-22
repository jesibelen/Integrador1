package com.company.ada;
import java.time.LocalDate;

public class Moto extends Automotor implements VehiculoCombustion{
    private Combustible combustible;

    public Moto(){}
    public Moto(String patente, Marca marca, int anioAuto, Propietario propietario, TipoUso uso, Combustible combustible) {
        super(patente, marca, anioAuto, propietario, uso);
        this.combustible=combustible;
    }
    public Moto(String patente, Marca marca, int anioAuto, Propietario propietario, TipoUso uso, Combustible combustible, LocalDate fechaDeAlta ) {
        super(patente, marca, anioAuto, propietario, uso, fechaDeAlta);
        this.combustible=combustible;
    }
    @Override
    public Combustible getCombustible() {
        return combustible;
    }
}
