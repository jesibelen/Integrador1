package com.company.ada;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RegSeccional {
    //Atributos
    public Provincia provincia;
    private String oficina;
    private List<Automotor> automotors;

    //Constructores
    public RegSeccional() { }
    public RegSeccional(Provincia provincia, String oficina) {
        this.provincia = provincia;
        this.oficina = oficina;
        this.automotors = new ArrayList<>();
    }
    //Getter y Setter
    public Provincia getProvincia() {
        return provincia;
    }

    public String getOficina() {
        return oficina;
    }

    public List<Automotor> getAutomotors(){return automotors;}

    public void guardarSeccionales(List<RegSeccional> seccionales) {
        Provincia[] array = Provincia.values();
        for (Provincia provincia : array) {
            seccionales.add(new RegSeccional(provincia, provincia.getOficina()));
        }
    }

    public void agregarAutomotor(Automotor unVehiculo) {
        this.automotors.add(unVehiculo);
    }

    public void setAutorizado(int automotor, Autorizado autorizado) {
        Automotor automotor1 = this.automotors.get(automotor);
        automotor1.agregarAutorizado(autorizado);
    }
    @Override
    public String toString() {
        return "RegSeccional" +
                "provincia=" + provincia +
                ", oficina='" + oficina + '\'' +
                ", automotors=" + automotors +"\n"
                ;
    }

    public void mostrarSeccionales(List<RegSeccional> seccionales) {
        System.out.println("#################################################");
        System.out.println("[1] REGISTRO DE UN AUTO\n Por favor, Seleccione su provincia: ");
        System.out.println("------------------------------------------------");
        int num=0;
        //Mostrar menu de seccionales
        for (RegSeccional seccional: seccionales){
            num++;
            System.out.println(" "+num+")"+seccional.getProvincia());
        }
    }
    public String aceptarPatente(List<RegSeccional> seccionales) {
        String newPatente=""; boolean stop=true;
        while (stop) {
            newPatente = this.generarPatente();
            for (RegSeccional seccional : seccionales) {
                for (int j = 0; j < seccional.automotors.size(); j++) {
                    String patentRegistrada = seccional.obtenerPatente(j);
                    if (!(newPatente.equals(patentRegistrada))) {
                        stop = false;
                        break;
                    }
                }
            }
        }
        return newPatente;
    }

    public String obtenerPatente(int automovil) {
        Automotor auto1 =this.automotors.get(automovil);
        String patenteRegistrada = auto1.getPatente();
        return patenteRegistrada;
    }

    public String generarPatente() {
        int num; String randPatent=""; Random rnd = new Random();
        for (int i = 0; i < 6; i++) {
            if (i < 3) {
                num = rnd.nextInt(26) + 65;
                char letra = (char) num;
                randPatent += letra;
            } else { randPatent += rnd.nextInt(10); }
        }
        return randPatent;
    }
    public int pedirUbicacionAuto() {
        int posicionAuto= this.automotors.size()-1;
        return posicionAuto;
    }

    public void compararPatente(List<RegSeccional> seccionales, String patIngresado) {
        boolean stop=true;
        for (RegSeccional seccional : seccionales) {
            for (int j = 0; j < seccional.pedirTamanioListAut();j++){
                String patenteRegis = seccional.obtenerPatente(j);
                if (patIngresado.equals(patenteRegis)) {
                    LocalDate fechaRegistrada = seccional.obtenerFechaDeAlta(j);
                    if (ChronoUnit.YEARS.between(fechaRegistrada, LocalDate.now()) > 0) {
                        Propietario propietarioEleg = seccional.obtenerPropietario(j);
                        System.out.println(propietarioEleg);
                        HelperDNRPA dnrpa= new HelperDNRPA();
                        Propietario prop1 = (Propietario) dnrpa.pedirDatosPersona(true);
                        seccional.cambiarDePropietario(j,prop1);
                        seccional.crearFechaActualizacionProp(j);
                        System.out.println("Nuevo propietario: "+prop1);
                    } else {
                        System.out.println("NO SE PUEDE CAMBIAR DE PROPIETARIO DEBIDO QUE PASÓ MENOS DE 1 AÑO. ");
                    }
                    stop = false;
                    break;
                }
            }
        }
        if(stop) System.out.println("No se encontró el vehiculo registrado. ");
    }
    public int pedirTamanioListAut(){
        int tamanio= this.automotors.size();
        return tamanio;
    }
    public void crearFechaActualizacionProp(int auto){
        Automotor autom=this.automotors.get(auto);
        autom.setFechaCambioProp(LocalDate.now());
        System.out.println("Fecha de cambio de propietario: "+autom.getFechaCambioProp());
    }
    public void cambiarDePropietario(int auto,Propietario propietarioNew) {
        Automotor autom=this.automotors.get(auto);
        autom.setPropietario(propietarioNew);
    }
    public Propietario obtenerPropietario(int auto) {
        Automotor autom=this.automotors.get(auto);
        return autom.getPropietario();
    }
    public LocalDate obtenerFechaDeAlta(int auto) {
        Automotor autom= this.automotors.get(auto);
        return autom.getFechaDeAlta();
    }

    public String obtenerTipoAuto(int auto) {
        Automotor autom=this.automotors.get(auto);
        return autom.darTipoDeAuto();
    }

    public Marca obtenerMarca(int auto) {
        Automotor autom= this.automotors.get(auto);
        return autom.getMarca();
    }

    public int obtenerAnioAuto(int auto) {
        Automotor autom= this.automotors.get(auto);
        return autom.getAnioAuto();

    }

    public Integer obtenerVoltajeAuto(int i, String claseAuto) {
        Integer voltaje=0;
        switch (claseAuto){
            case ("MotoElectrica"):
                MotoElectrica motoE =(MotoElectrica)this.automotors.get(i);
                voltaje= motoE.getVoltaje();
                break;
            case ("AutoElectrico"):
                AutoElectrico autoE =(AutoElectrico) this.automotors.get(i);
                voltaje= autoE.getVoltaje();
                break;
        }
        System.out.println("VOLTAJE "+voltaje);
        return voltaje;

       /* if(claseAuto.equals("MotoElectrica")){
            MotoElectrica motoE =(MotoElectrica)this.automotors.get(i);
            voltaje= motoE.getVoltaje();
        }else if (claseAuto.equals("AutoElectrico")){
            AutoElectrico autoE =(AutoElectrico) this.automotors.get(i);
            voltaje= autoE.getVoltaje();
        }*/
    }
    public Combustible obtenerCombustible(int auto, String claseAuto) {
        Combustible combustible= null;
        switch (claseAuto) {
            case ("Camion"): Camion camion = (Camion) this.automotors.get(auto);
                combustible = camion.getCombustible();
                break;
            case ("Colectivo"): Colectivo cole = (Colectivo) this.automotors.get(auto);
                combustible = cole.getCombustible();
                break;
            case ("Utilitario"): Utilitario utili= (Utilitario) this.automotors.get(auto);
                combustible = utili.getCombustible();
                break;
            case ("Moto"): Moto moto = (Moto) this.automotors.get(auto);
                combustible = moto.getCombustible();
                break;
            case ("Auto"): Auto autom = (Auto) this.automotors.get(auto);
                combustible = autom.getCombustible();
                break;
        }
        return combustible;
    }

    public List<Autorizado> obtenerAutorizados(int auto) {
        Automotor autom=this.automotors.get(auto);
        return autom.getAutorizados();
    }

    public boolean compararCategoriaCamion(int auto) {
        boolean exito=true;
        Automotor autom= this.automotors.get(auto);
        String tipo= autom.darTipoDeAuto();
        if (tipo.equals("Camion")){
            exito=true;
        }else {exito=false;}
        return exito;
    }
    public String obtenerDatos(int auto,int dato){
        Automotor autom= this.automotors.get(auto);String dato1="";
        switch (dato){
            case 1: dato1= autom.pedirN();
                break;
            case 2: dato1=autom.pedirA();
                break;
            case 3:dato1=autom.pedirDNI();
                break;
        }
        return dato1;
    }
}

