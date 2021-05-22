package com.company.ada;

public class Mensaje {
    public void DarBienvenida() {
        System.out.println("****************************************");
        System.out.println("*            D.N.R.P.A                 *");
        System.out.println("* Dirección Nacional de los Registros  *");
        System.out.println("* Nacionales de la Propiedad Automotor *");
        System.out.println("****************************************");
    }
    public void mostrarMenuPrincipal() {
        System.out.println("\n-------------------------------------");
        System.out.println("Menu Principal");
        System.out.println("-------------------------------------");
        System.out.println("[1] Dar de alta un automotor.");
        System.out.println("[2] Cambiar de Propietario.");
        System.out.println("[3] Ver la lista de los autos registrados.");
        System.out.println("[4] Ver la lista de los propietarios de Camiones.");
        System.out.println("-------------------------------------");
        System.out.println("[0] Salir");
        System.out.println("-------------------------------------");
    }
    public void salir() {
        System.out.println("-----------¡Gracias!------------");
    }

    public void mostrarPresent(int opcion) {
        System.out.println("######################################################################");
        switch (opcion){
            case 2: System.out.println("[2] CAMBIAR DATOS DEL PROPIETARIO");
                break;
            case 3: System.out.println("[3] LISTA DE LOS AUTOS REGISTRADOS");
                break;
            case 4: System.out.println("[4] LISTA DE LOS PROPIETARIOS DE CAMIONES EN ORDEN ALFABÉTICO");
                break;
        }
    }
}
