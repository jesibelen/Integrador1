package com.company.ada;

public enum Provincia {
    BUENOS_AIRES ("011"), CABA ("870"),
    CATAMARCA ("039"), CHACO ("034"),
    CHUBUT("349"), CORDOBA ("345"),
    LA_PAMPA ("098"), SALTA ("286"),
    TIERRA_DEL_FUEGO ("068"), TUCUMAN ("753");

    private String oficina;

    Provincia(String oficina) {
        this.oficina = oficina;
    }

    public String getOficina() {
        return oficina;
    }
}
