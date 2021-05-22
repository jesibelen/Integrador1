package com.company.ada;
import java.util.*;

public class DNRPA {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HelperDNRPA helperDNRPA= new HelperDNRPA();
        Mensaje mensaje=new Mensaje();
        RegSeccional regSec = new RegSeccional();
        List<RegSeccional> seccionales =new ArrayList<>();
        int opcion=1;

        //primero guardo mis seccionales
        regSec.guardarSeccionales(seccionales);
        helperDNRPA.registrarAutosPrueba(seccionales);

        mensaje.DarBienvenida();

        while (opcion!=0) {
            mensaje.mostrarMenuPrincipal();
            try{
                System.out.print("Seleccione una opción [0-4]: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1: helperDNRPA.darDeAltaAutomotor(seccionales,regSec);
                        break;
                    case 2: helperDNRPA.actualizarDatosPropietario(seccionales,regSec);
                        break;
                    case 3: helperDNRPA.listarTodoAutomotores(seccionales);
                        break;
                    case 4: helperDNRPA.listarPropietarioCamiones(seccionales);
                        break;
                    case 0: mensaje.salir();
                        break;
                    default: System.out.println("Opcion incorrecta");
                        break;
                }
            }catch (InputMismatchException e){
                System.out.println("ERROR: Solo ingresar números.");
                scanner.next();
            }
        }
    }
}