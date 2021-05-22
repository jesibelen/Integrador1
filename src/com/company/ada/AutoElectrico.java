package com.company.ada;
import java.time.LocalDate;

public class AutoElectrico extends Automotor implements VehiculoElectrico {
    private Integer voltaje;

    public AutoElectrico(){ }
    public AutoElectrico(String patente, Marca marca, int anioAuto, Propietario propietario, TipoUso uso, Integer voltaje) {
        super(patente, marca, anioAuto, propietario, uso);
        this.voltaje=voltaje;
    }
    public AutoElectrico(String patente, Marca marca, int anioAuto, Propietario propietario, TipoUso uso, Integer voltaje, LocalDate fechaDeAlta) {
        super(patente, marca, anioAuto, propietario, uso, fechaDeAlta);
        this.voltaje=voltaje;
    }

    public Integer getVoltaje() {
        return voltaje;
    }

    @Override
    public String toString() {
        return "AutoElectrico{" +
                "voltaje=" + voltaje +
                '}';
    }
}
