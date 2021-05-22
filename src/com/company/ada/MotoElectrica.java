package com.company.ada;

import java.time.LocalDate;

public class MotoElectrica extends Automotor implements VehiculoElectrico {
    private Integer voltaje;

    public MotoElectrica(){ }
    public MotoElectrica(String patente, Marca marca, int anioAuto, Propietario propietario, TipoUso uso, Integer voltaje) {
        super(patente, marca, anioAuto, propietario, uso);
        this.voltaje=voltaje;
    }
    public MotoElectrica(String patente, Marca marca, int anioAuto, Propietario propietario, TipoUso uso, Integer voltaje, LocalDate fechaDeAlta) {
        super(patente, marca, anioAuto, propietario, uso, fechaDeAlta);
        this.voltaje=voltaje;
    }

    @Override
    public Integer getVoltaje() {
        return voltaje;
    }
}