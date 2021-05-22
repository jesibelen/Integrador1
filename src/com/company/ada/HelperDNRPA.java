package com.company.ada;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HelperDNRPA {
    Scanner scanner= new Scanner(System.in);
    boolean exito=true;
    Mensaje mensaje=new Mensaje();
    //######################################################################################################
    public void darDeAltaAutomotor(List<RegSeccional> seccionales,RegSeccional regSec) {
        int opcionSeccion=1;
        int cantSecc= seccionales.size();
        do {
            regSec.mostrarSeccionales(seccionales);
            try {
                System.out.print("Opción elegida (1-"+cantSecc+"): ");
                opcionSeccion = scanner.nextInt();
                if(opcionSeccion<1||opcionSeccion>cantSecc){
                    System.out.println("ERROR: No existe esa opcion.");
                    exito=false;
                }else {exito=true;}
            } catch (InputMismatchException e) {
                exito=false;
                System.out.println("ERROR: Solo ingresar números.");
                scanner.next();
            }
        }while(!exito);
        System.out.println("OPCION SECCIONAL: "+opcionSeccion);
        scanner.nextLine();

        System.out.println("-----------------------------------------\nDATOS DEL PROPIETARIO.");
        Propietario newPropietario= (Propietario) this.pedirDatosPersona(true);
        System.out.println(newPropietario);

        System.out.println("----------------------------------------\nDATOS DEL AUTO");
        Marca marca= this.obtenerMarcaSelecc();
        System.out.println("MARCA "+marca);
        TipoUso uso= this.obtenerUsoSelecc();
        System.out.println("TIPO DE USO "+uso);
        int anioAuto =this.obtenerAnioSelecc();
        System.out.println("AÑO DEL AUTO ES: "+anioAuto);
        String patente=regSec.aceptarPatente(seccionales);
        System.out.println("SU PATENTE GENERADA ES: "+patente);
        System.out.println("-----------------------------------------\nDATOS DE LOS AUTORIZADOS.");
        List<Autorizado>autorizados= new ArrayList<>();
        autorizados =this.obtenerNewAutoriz(autorizados);
        autorizados.forEach(System.out::println);
        int opcionCategoria=0,tamCategoria=0;
        do{
            tamCategoria=this.mostrarCategorias();
            try {
                opcionCategoria= this.obtenerCategoriaSelec(tamCategoria);
                this.registrarUnVehiculo(opcionSeccion,newPropietario,marca,uso,anioAuto,patente,autorizados,opcionCategoria,seccionales);

            }catch (InputMismatchException e){
                // System.out.println("opcion: "+opcionCategoria);
                System.out.println("ERROR: Solo ingresar números.");
                scanner.next();
            }
        }while(opcionCategoria<1||opcionCategoria>tamCategoria);
    }
    //#################################################################################################
    public void actualizarDatosPropietario(List<RegSeccional> seccionales, RegSeccional regSec) {
        mensaje.mostrarPresent(2);
        System.out.print("\nIngrese la patente del vehiculo: ");
        String patIngresado = scanner.next().toUpperCase();
        regSec.compararPatente(seccionales,patIngresado);
    }
    //#################################################################################################
    public void listarTodoAutomotores(List<RegSeccional> seccionales) {
        mensaje.mostrarPresent(3);
        Automotor automotor=new Automotor();
        for (RegSeccional seccional : seccionales) {
            System.out.println("\nSECCIONAL: " + seccional.getProvincia() + " - OFICINA: " + seccional.getOficina());
            System.out.println("____________________________________________________________________________________");

            for (int i= 0; i < seccional.pedirTamanioListAut();i++){
                LocalDate fechaRegis =seccional.obtenerFechaDeAlta(i);
                String fechaConFormato = fechaRegis.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String claseAuto= seccional.obtenerTipoAuto(i);
                String patente = seccional.obtenerPatente(i);
                Marca marca= seccional.obtenerMarca(i);
                int anio =seccional.obtenerAnioAuto(i);
                Integer voltaje= seccional.obtenerVoltajeAuto(i,claseAuto);
                Combustible combustible= seccional.obtenerCombustible(i,claseAuto);
                Propietario propietario= seccional.obtenerPropietario(i);
                List<Autorizado>autorizados=seccional.obtenerAutorizados(i);
                automotor.mostrarTodosLosDatos(fechaConFormato,claseAuto,patente,marca,anio,voltaje,propietario,combustible,autorizados);
            }
            if (seccional.getAutomotors().isEmpty()) {
                System.out.println("NO HAY AUTOMÓVILES REGISTRADOS EN ESTA SECCIONAL.");
            }
            System.out.println("\n");
        }
    }
    //#################################################################################################
    public void listarPropietarioCamiones(List<RegSeccional> seccionales) {
        mensaje.mostrarPresent(4);
        List<String>camionPropietarios= new ArrayList<>();

        for (RegSeccional seccional: seccionales){
            for (int j=0; j<seccional.pedirTamanioListAut();j++){
                boolean comparacion= seccional.compararCategoriaCamion(j);
                if (comparacion==true){
                    String propCamion=seccional.obtenerDatos(j,1);
                    String propCamionSurNam=seccional.obtenerDatos(j,2);
                    String propCamionDNI=seccional.obtenerDatos(j,3);
                    propCamion=propCamion.concat(" "+propCamionSurNam+" DNI: "+propCamionDNI);
                    camionPropietarios.add(propCamion);
                }
            }
        }

        Collections.sort(camionPropietarios);
        camionPropietarios.forEach(prop->{System.out.println("Propietario: "+prop);
        });
    }
    //#################################################################################################
    private void registrarUnVehiculo(int seccion, Propietario nuevoProp, Marca marca, TipoUso uso, int anioAuto, String patente, List<Autorizado> autorizados, int categoria,List<RegSeccional>seccionales) {
        Combustible combustible=null;
        Integer voltaje=0;
        int secTrue= seccion-1, posicionAuto=0;
        RegSeccional seccional=null;

        switch (categoria){
            case 1: combustible= this.obtenerCombustibleSelec();
                Auto auto= new Auto(patente,marca,anioAuto,nuevoProp,uso,combustible);
                seccional=seccionales.get(secTrue);
                seccional.agregarAutomotor(auto);
                posicionAuto=seccional.pedirUbicacionAuto();
                this.guardarAutorizados(seccional,posicionAuto,autorizados);
                break;

            case 2: voltaje =this.pedirVoltaje();
                System.out.println("VOLTAJE: "+voltaje);
                AutoElectrico autoE = new AutoElectrico(patente,marca,anioAuto,nuevoProp,uso,voltaje);
                seccional=seccionales.get(secTrue);
                seccional.agregarAutomotor(autoE);
                posicionAuto=seccional.pedirUbicacionAuto();
                this.guardarAutorizados(seccional,posicionAuto,autorizados);
                break;

            case 3: combustible= this.obtenerCombustibleSelec();
                Moto moto= new Moto(patente,marca,anioAuto,nuevoProp,uso,combustible);
                seccional=seccionales.get(secTrue);
                seccional.agregarAutomotor(moto);
                posicionAuto=seccional.pedirUbicacionAuto();
                this.guardarAutorizados(seccional,posicionAuto,autorizados);
                break;

            case 4: voltaje =this.pedirVoltaje();
                MotoElectrica motoE= new MotoElectrica(patente,marca,anioAuto,nuevoProp,uso,voltaje);
                seccional=seccionales.get(secTrue);
                seccional.agregarAutomotor(motoE);
                posicionAuto=seccional.pedirUbicacionAuto();
                this.guardarAutorizados(seccional,posicionAuto,autorizados);
                break;

            case 5: combustible= this.obtenerCombustibleSelec();
                Colectivo colectivo= new Colectivo(patente,marca,anioAuto,nuevoProp,uso,combustible);
                seccional=seccionales.get(secTrue);
                seccional.agregarAutomotor(colectivo);
                posicionAuto=seccional.pedirUbicacionAuto();
                this.guardarAutorizados(seccional,posicionAuto,autorizados);
                break;

            case 6: combustible= this.obtenerCombustibleSelec();
                Camion camion= new Camion(patente,marca,anioAuto,nuevoProp,uso,combustible);
                seccional=seccionales.get(secTrue);
                seccional.agregarAutomotor(camion);
                posicionAuto=seccional.pedirUbicacionAuto();
                this.guardarAutorizados(seccional,posicionAuto,autorizados);
                break;

            case 7: combustible= this.obtenerCombustibleSelec();
                Utilitario utilitario= new Utilitario(patente,marca,anioAuto,nuevoProp,uso,combustible);
                seccional=seccionales.get(secTrue);
                seccional.agregarAutomotor(utilitario);
                posicionAuto=seccional.pedirUbicacionAuto();
                this.guardarAutorizados(seccional,posicionAuto,autorizados);
                break;

            default:System.out.println("ERROR: No existe esa opción.");
                break;
        }
    }

    private void guardarAutorizados(RegSeccional seccional, int posicionAuto, List<Autorizado> autorizados) {
        for (Autorizado autorizado: autorizados){
            seccional.setAutorizado(posicionAuto,autorizado);
        }
    }

    private Integer pedirVoltaje() {
        Integer volt=0;
        do {
            try {
                System.out.print("Ingrese el voltaje: ");
                volt=scanner.nextInt();
                int cantCifra = (int)(Math.log10(volt)+1);
                if (cantCifra!=3) {
                    System.out.println("Error: el voltaje debe ser de 3 cifras.");
                    exito = false;
                }else { exito=true; }
            }catch(InputMismatchException e){
                exito=false;
                System.out.println("ERROR: Solo ingresar números.");
                scanner.next();
            }
        }while (!exito);
        return volt;
    }

    private Combustible obtenerCombustibleSelec() {
        Combustible combustible= null;
        do {
            try {
                combustible = this.mostrarCombustible("-----------------------------\nSeleccione el tipo de combustible que utiliza:");
                exito= true;
            }catch (IllegalArgumentException e){
                System.out.println("ERROR: tipo de combustible no válido. Escribalo nuevamente");
                exito= false;
            }
        }while (!exito);
        return combustible;
    }

    private Combustible mostrarCombustible(String mensaje) {
        Combustible[] vector = Combustible.values();
        System.out.println(mensaje);
        for (Combustible combustible: vector){
            System.out.println("* "+combustible);
        }
        System.out.print("OPCION ELEGIDA: ");
        String combustibleSelec= scanner.next().toUpperCase();
        Combustible combustible1 = Enum.valueOf(Combustible.class, combustibleSelec);
        return combustible1;
    }

    private int mostrarCategorias() {
        Categoria[] vector = Categoria.values();
        int tamanio= vector.length;
        System.out.println("-----------------------------");
        System.out.println("Seleccione la categoria: ");
        for (Categoria categoria : vector) {
            int num = 1 + categoria.ordinal();
            System.out.println(" " + num + ") " + categoria);
        }
        return tamanio;
    }
    private int obtenerCategoriaSelec(int tamCategoria) {
        int opcion=0;

        System.out.print("OPCIÓN ELEGIDA (1-"+tamCategoria+"): ");
        opcion = scanner.nextInt();
        return opcion;
    }
    private List<Autorizado> obtenerNewAutoriz(List<Autorizado> autorizados) {
        do{
            System.out.print("¿Quiere registrar un autorizado? (Y/N):");
            String opcion=scanner.next().toUpperCase();
            switch (opcion) {
                case "Y":  Autorizado newAutorizado= (Autorizado)this.pedirDatosPersona(false);
                    autorizados.add(newAutorizado);
                    break;
                case "N": exito=false;
                    break;
                default: System.out.println("Usted debe ingresar Y(yes)/N(no).");
            }
        }while (exito);
        return autorizados;
    }

    private int obtenerAnioSelecc() {
        int anioAuto=0;
        LocalDate fechaActual= LocalDate.now();
        int anioActual= fechaActual.getYear();
        do {
            try {
                System.out.print("Ingrese el año del automotor (yyyy): ");
                anioAuto = scanner.nextInt();
                exito=this.validarAnioAuto(anioAuto,anioActual);

            }catch (InputMismatchException e){
                exito=false;
                System.out.println("ERROR: Solo ingresar números.");
                scanner.next();
            }
        }while (!exito);

        return anioAuto;
    }

    private boolean validarAnioAuto(int anioAuto, int anioActual) {
        int cantCifra = (int)(Math.log10(anioAuto)+1);
        if (cantCifra!=4) {
            System.out.println("ERROR: Debe ingresar 4 cifras");
            exito=false;
        }else if(anioAuto>anioActual){
            System.out.println("ERROR: El año debe ser menor o igual al año "+anioActual+".");
            exito=false;
        }else exito=true;
        return exito;
    }

    public boolean validarDni(int dni){
        int cantCifra = (int)(Math.log10(dni)+1);
        if (cantCifra!=8){
            System.out.println("Error: el DNI debe tener 8 cifras.");
            exito=false;
        }else { exito=true; }
        return exito;
    }
    private TipoUso obtenerUsoSelecc() {
        TipoUso usoSelec=null;
        do {
            try {
                usoSelec=this.mostrarUsoAuto();
                exito= true;
            }catch (IllegalArgumentException e){
                System.out.println("ERROR: Tipo de uso inválido. Escribalo nuevamente");
                exito= false;
            }
        }while (!exito);
        return usoSelec;
    }

    private TipoUso mostrarUsoAuto() {
        TipoUso[] uso = TipoUso.values();
        System.out.println("-----------------------------");
        System.out.println("Seleccione el tipo de uso: ");
        for (TipoUso tipoUso: uso){
            System.out.println("* "+tipoUso);
        }
        System.out.print("OPCION ELEGIDA: ");
        String usoSelec= scanner.next().toUpperCase();
        TipoUso uso1 = Enum.valueOf(TipoUso.class, usoSelec);
        return uso1;
    }
    private Marca obtenerMarcaSelecc() {
        Marca marcaElegida=null;
        do {
            try {
                marcaElegida= this.mostrarMarcaAuto();
                exito = true;
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR:Marca inválida. Escribala nuevamente.");
                exito= false;
            }
        }while (!exito);
        return marcaElegida;
    }
    private Marca mostrarMarcaAuto() {
        Marca[] vector =Marca.values();
        System.out.println("-----------------------------");
        System.out.println("Seleccione la marca: ");
        for (Marca marca : vector) {
            System.out.println("* "+marca);
        }
        System.out.print("OPCION ELEGIDA: ");
        String marcaSelec = scanner.next().toUpperCase();
        Marca marca1 = Enum.valueOf(Marca.class, marcaSelec);
        return marca1;
    }

    public Persona pedirDatosPersona(boolean prop) {
        Scanner scanner= new Scanner(System.in);
        String nombre, apellido, domicilio; int dni=0;

        System.out.print("Ingrese el nombre: ");
        nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido: ");
        apellido = scanner.nextLine();
        do {
            try {
                System.out.print("Ingrese el DNI: ");
                dni = scanner.nextInt();
                exito= this.validarDni(dni);
            }catch (InputMismatchException e){
                exito=false;
                System.out.println("ERROR: Solo ingresar números.");
                scanner.next();
            }
        }while (!exito);
        scanner.nextLine();
        System.out.print("Ingrese la dirección de domicilio: ");
        domicilio= scanner.nextLine();
        Persona persona;
        if(prop==false) { persona= new Autorizado(nombre,apellido,dni,domicilio);}
        else { persona = new Propietario(nombre, apellido, dni, domicilio);}
        return persona;
    }
//###################################################################################################

    public void registrarAutosPrueba(List<RegSeccional> seccionales) {
        Propietario propietario1 = new Propietario("Arturo","Martinez",12345678,"Avenida Siempre Viva 1111");
        Propietario propietario2 = new Propietario("Maria","Benitez",23456789,"Avenida Rivadavia 3333");
        Propietario propietario3 = new Propietario("Leandro","Wood",12356789,"Avenida Sasas 444");
        Propietario propietario4 = new Propietario("Carolina","Perez",45689012,"Ecuador 123");
        Propietario propietario5 = new Propietario("Julian","Acevedo",34578902,"Avenida Siempre Viva 212");

        Autorizado autorizado10 = new Autorizado("Celeste", "Martinez",89012345,"blabla 123");
        Autorizado autorizado30 = new Autorizado("Lucy", "Wood",13782222,"belgrano 222");
        Autorizado autorizado31 = new Autorizado("Oscar", "Hills",90033321,"Barracas 222");
        Autorizado autorizado40 = new Autorizado("Sandro", "Perez",11111111,"Lopez 1111");
        Autorizado autorizado41 = new Autorizado("Gimena", "Mendez",33344422,"Mitre 345");
        Autorizado autorizado50 = new Autorizado("Jazmin", "Acevedo",90901111,"Hipolito 900");
        Autorizado autorizado51 = new Autorizado("Daniel", "Acevedo",66677722,"Yrigoyen 123");

        Auto auto1 = new Auto("GIT123",Marca.FORD,2006,propietario1,TipoUso.PARTICULAR,Combustible.NAFTA, LocalDate.of(2006,12,01));
        Camion camion2= new Camion("SHI092",Marca.MERCEDES_BENZ,2021,propietario2,TipoUso.PROFESIONAL_PRIVADO,Combustible.DIESEL,LocalDate.of(2021,01,10));
        MotoElectrica motoElectrica3 = new MotoElectrica("PER234",Marca.RENAULT,2020,propietario3,TipoUso.OFICIAL,300,LocalDate.of(2020,06,04));
        Colectivo colectivo4= new Colectivo("IDS254",Marca.CHEVROLET,2000,propietario4,TipoUso.PROFESIONAL_PUBLICO, Combustible.DIESEL,LocalDate.of(2007,11,02));
        Camion camion5= new Camion("KJL210",Marca.MERCEDES_BENZ,2010,propietario5,TipoUso.PROFESIONAL_PRIVADO,Combustible.NAFTA,LocalDate.of(2010,03,12));

        seccionales.get(0).agregarAutomotor(auto1);
        seccionales.get(0).agregarAutomotor(camion2);//BUENOS AIRES
        seccionales.get(5).agregarAutomotor(motoElectrica3);//CORDOBA
        seccionales.get(1).agregarAutomotor(colectivo4);//CABA
        seccionales.get(3).agregarAutomotor(camion5);//CHACO

        seccionales.get(0).setAutorizado(0, autorizado10);
        seccionales.get(5).setAutorizado(0, autorizado30);
        seccionales.get(5).setAutorizado(0, autorizado31);
        seccionales.get(1).setAutorizado(0, autorizado40);
        seccionales.get(1).setAutorizado(0, autorizado41);
        seccionales.get(3).setAutorizado(0, autorizado50);
        seccionales.get(3).setAutorizado(0, autorizado51);
    }
}