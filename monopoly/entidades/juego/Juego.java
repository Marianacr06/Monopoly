/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.monopoly.entidades.juego;

import co.edu.udea.monopoly.entidades.tablero.Casilla;
import co.edu.udea.monopoly.entidades.tablero.CasillaEspecial;
import co.edu.udea.monopoly.entidades.tablero.CasillaPropiedad;
import co.edu.udea.monopoly.entidades.tablero.Tablero;
import co.edu.udea.monopoly.entidades.tarjeta.Tarjeta;
import co.edu.udea.monopoly.entidades.tarjeta.TarjetaCarcelIrCarcel;
import co.edu.udea.monopoly.entidades.tarjeta.TarjetaCarcelSalirCarcel;
import co.edu.udea.monopoly.entidades.tarjeta.TarjetaCobrarAcciones;
import co.edu.udea.monopoly.entidades.tarjeta.TarjetaCobrarBanco;
import co.edu.udea.monopoly.entidades.tarjeta.TarjetaCobrarConcursoBelleza;
import co.edu.udea.monopoly.entidades.tarjeta.TarjetaCobrarConsultoria;
import co.edu.udea.monopoly.entidades.tarjeta.TarjetaCobrarCrucigrama;
import co.edu.udea.monopoly.entidades.tarjeta.TarjetaCobrarCumpleaños;
import co.edu.udea.monopoly.entidades.tarjeta.TarjetaCobrarErrorBanco;
import co.edu.udea.monopoly.entidades.tarjeta.TarjetaCobrarFestivo;
import co.edu.udea.monopoly.entidades.tarjeta.TarjetaCobrarHerencia;
import co.edu.udea.monopoly.entidades.tarjeta.TarjetaCobrarImpuestosPrestamos;
import co.edu.udea.monopoly.entidades.tarjeta.TarjetaCobrarOpera;
import co.edu.udea.monopoly.entidades.tarjeta.TarjetaCobrarReembolsoImpuesto;
import co.edu.udea.monopoly.entidades.tarjeta.TarjetaCobrarSeguroVida;
import co.edu.udea.monopoly.entidades.tarjeta.TarjetaIrCasillaCasillaPropiedad;
import co.edu.udea.monopoly.entidades.tarjeta.TarjetaIrCasillaCobrarInicio;
import co.edu.udea.monopoly.entidades.tarjeta.TarjetaIrCasillaProximaUtilidad;
import co.edu.udea.monopoly.entidades.tarjeta.TarjetaIrCasillaTresCasillasAtras;
import co.edu.udea.monopoly.entidades.tarjeta.TarjetaPagarArreglosMejoras;
import co.edu.udea.monopoly.entidades.tarjeta.TarjetaPagarCasasHoteles;
import co.edu.udea.monopoly.entidades.tarjeta.TarjetaPagarDoctor;
import co.edu.udea.monopoly.entidades.tarjeta.TarjetaPagarEscuela;
import co.edu.udea.monopoly.entidades.tarjeta.TarjetaPagarHospital;
import co.edu.udea.monopoly.entidades.tarjeta.TarjetaPagarJugadores;
import co.edu.udea.monopoly.entidades.tarjeta.TarjetaPagarPobres;
import co.edu.udea.monopoly.gui.GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author felipe
 */
public class Juego {

    private final Banco banco;
    private final Tablero tablero;
    private final GUI gui;
    private final Dado dadoA;
    private final Dado dadoB;
    private final ArrayList<Tarjeta> tarjetasArcaComun;
    private final ArrayList<Tarjeta> tarjetasCasualidad;
    private final ArrayList<Jugador> jugadores;

    public Juego() {
        this.jugadores = new ArrayList<>();
        this.dadoA = new Dado();
        this.dadoB = new Dado();
        this.tablero = new Tablero();
        this.banco = new Banco(tablero.getCasillas());
        this.gui = new GUI();
        this.tarjetasArcaComun = new ArrayList<>();
        this.tarjetasCasualidad = new ArrayList<>();
        this.gui.getJButtonLanzar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                getGui().getJButtonLanzar().setEnabled(false);
                jugar();
                getGui().getJButtonLanzar().setEnabled(true);
            }
        });
        this.gui.getJButtonRegistrar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre = getGui().getJTextFieldUsuario().getText();
                    if (nombre.length() > 1) {
                        registrarJugador(nombre);
                        getGui().mostrarJugadoresEnTabla(getJugadores());
                        habilitarJuego();
                    } else {
                        JOptionPane.showMessageDialog(getGui(), ":(");
                    }
                    getGui().getJTextFieldUsuario().setText("");
                    getGui().getJTextFieldUsuario().requestFocus();
                } catch (Exception ee) {
                    JOptionPane.showMessageDialog(getGui(), "Ingrese un nombre válido.");
                }
            }
        });
        this.gui.getJButtonIniciar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                getGui().getJButtonIniciar().setEnabled(false);
                getGui().getJButtonRegistrar().setEnabled(false);
                getGui().getJTextFieldUsuario().setEnabled(false);
                getGui().getJButtonLanzar().setEnabled(true);
            }
        });
    }

    public void siguienteJugador() {
        getJugadores().add(getJugadores().get(0));
        getJugadores().remove(0);
    }

    public int lanzarDados() {
        return dadoA.getNumero() + dadoB.getNumero();
    }

    public void registrarJugador(String nombre) {
        Ficha ficha = new Ficha("Ficha" + nombre);
        Jugador jugador = new Jugador(ficha, nombre);
        getJugadores().add(jugador);
    }

    public void habilitarJuego() {
        if (getJugadores().size() > 1) {
            getGui().getJButtonIniciar().setEnabled(true);
        }
        if (getJugadores().size() > 7) {
            getGui().getJButtonRegistrar().setEnabled(false);
        }
    }

    /**
     * @return the banco
     */
    public Banco getBanco() {
        return banco;
    }

    /**
     * @return the tablero
     */
    public Tablero getTablero() {
        return tablero;
    }

    public Tarjeta getTarjetaArcaComun() {
        int tarjeta = (int) (Math.random() * tarjetasArcaComun.size() - 1);
        return tarjetasArcaComun.get(tarjeta);
    }

    public Tarjeta getTarjetaCasualidad() {
        int tarjeta = (int) (Math.random() * tarjetasCasualidad.size() - 1);
        return tarjetasCasualidad.get(tarjeta);
    }

    public void crearTarjetas() {

        //tarjetas para el arreglo de tarjetasArcaComun
        TarjetaIrCasillaCobrarInicio tarjetaIrCasillaCobrarInicio = new TarjetaIrCasillaCobrarInicio();
        TarjetaCobrarErrorBanco tarjetaCobrarErrorBanco = new TarjetaCobrarErrorBanco();
        TarjetaPagarDoctor tarjetaPagarDoctor = new TarjetaPagarDoctor();
        TarjetaCarcelSalirCarcel tarjetaCarcelSalirCarcel = new TarjetaCarcelSalirCarcel();
        TarjetaCarcelIrCarcel tarjetaCarcelIrCarcel = new TarjetaCarcelIrCarcel();
        TarjetaCobrarCumpleaños tarjetaCobrarCumpleaños = new TarjetaCobrarCumpleaños();
        TarjetaCobrarOpera tarjetaCobrarOpera = new TarjetaCobrarOpera();
        TarjetaCobrarReembolsoImpuesto tarjetaCobrarReembolsoImpuesto = new TarjetaCobrarReembolsoImpuesto();
        TarjetaCobrarSeguroVida tarjetaCobrarSeguroVida = new TarjetaCobrarSeguroVida();
        TarjetaPagarHospital tarjetaPagarHospital = new TarjetaPagarHospital();
        TarjetaPagarEscuela tarjetaPagarEscuela = new TarjetaPagarEscuela();
        TarjetaCobrarConsultoria tarjetaCobrarConsultoria = new TarjetaCobrarConsultoria();
        TarjetaPagarCasasHoteles tarjetaPagarCasasHoteles = new TarjetaPagarCasasHoteles();
        TarjetaCobrarConcursoBelleza tarjetaCobrarConcursoBelleza = new TarjetaCobrarConcursoBelleza();
        TarjetaCobrarHerencia tarjetaCobrarHerencia = new TarjetaCobrarHerencia();
        TarjetaCobrarAcciones tarjetaCobrarAcciones = new TarjetaCobrarAcciones();
        TarjetaCobrarFestivo tarjetaCobrarFestivo = new TarjetaCobrarFestivo();

        //agrega al arreglo tarjetasArcaComun todas las tarjetas
        tarjetasArcaComun.add(tarjetaIrCasillaCobrarInicio);
        tarjetasArcaComun.add(tarjetaCobrarErrorBanco);
        tarjetasArcaComun.add(tarjetaPagarDoctor);
        tarjetasArcaComun.add(tarjetaCarcelSalirCarcel);
        tarjetasArcaComun.add(tarjetaCarcelIrCarcel);
        tarjetasArcaComun.add(tarjetaCobrarCumpleaños);
        tarjetasArcaComun.add(tarjetaCobrarOpera);
        tarjetasArcaComun.add(tarjetaCobrarReembolsoImpuesto);
        tarjetasArcaComun.add(tarjetaCobrarSeguroVida);
        tarjetasArcaComun.add(tarjetaPagarHospital);
        tarjetasArcaComun.add(tarjetaPagarEscuela);
        tarjetasArcaComun.add(tarjetaCobrarConsultoria);
        tarjetasArcaComun.add(tarjetaPagarCasasHoteles);
        tarjetasArcaComun.add(tarjetaCobrarConcursoBelleza);
        tarjetasArcaComun.add(tarjetaCobrarHerencia);
        tarjetasArcaComun.add(tarjetaCobrarAcciones);
        tarjetasArcaComun.add(tarjetaCobrarFestivo);

        //tarjetas para el arreglo de Casualidad
        TarjetaIrCasillaCasillaPropiedad tarjetaIrIllinois = new TarjetaIrCasillaCasillaPropiedad();
        TarjetaIrCasillaCobrarInicio tarjetaIrCasillaCobrarInicio1 = new TarjetaIrCasillaCobrarInicio();
        TarjetaIrCasillaProximaUtilidad tarjetaIrCasillaProximaUtilidad = new TarjetaIrCasillaProximaUtilidad();
        TarjetaIrCasillaProximaUtilidad tarjetaIrProximoFerro = new TarjetaIrCasillaProximaUtilidad();
        TarjetaIrCasillaProximaUtilidad tarjetaIrProximoFerro1 = new TarjetaIrCasillaProximaUtilidad();
        TarjetaIrCasillaCasillaPropiedad tarjetaIrStCharles = new TarjetaIrCasillaCasillaPropiedad();
        TarjetaCobrarBanco tarjetaCobrarBanco = new TarjetaCobrarBanco();
        TarjetaCarcelSalirCarcel tarjetaCarcelSalirCarcel1 = new TarjetaCarcelSalirCarcel();
        TarjetaIrCasillaTresCasillasAtras tarjetaIrCasillaTresCasillasAtras = new TarjetaIrCasillaTresCasillasAtras();
        TarjetaCarcelIrCarcel tarjetaCarcelIrCarcel1 = new TarjetaCarcelIrCarcel();
        TarjetaPagarArreglosMejoras tarjetaPagarArreglosMejoras = new TarjetaPagarArreglosMejoras();
        TarjetaPagarPobres tarjetaPagarPobres = new TarjetaPagarPobres();
        TarjetaIrCasillaProximaUtilidad tarjetaIrCasillaReadingFerro = new TarjetaIrCasillaProximaUtilidad();
        TarjetaIrCasillaCasillaPropiedad TarjetaIrCasillaBoardWalk = new TarjetaIrCasillaCasillaPropiedad();
        TarjetaPagarJugadores tarjetaPagarJugadores = new TarjetaPagarJugadores();
        TarjetaCobrarImpuestosPrestamos tarjetaCobrarImpuestosPrestamos = new TarjetaCobrarImpuestosPrestamos();
        TarjetaCobrarCrucigrama tarjetaCobrarCrucigrama = new TarjetaCobrarCrucigrama();

        //agrega al arreglo tarjetasCasualidad todas las tarjetas
        tarjetasCasualidad.add(tarjetaIrIllinois);
        tarjetasCasualidad.add(tarjetaIrCasillaCobrarInicio1);
        tarjetasCasualidad.add(tarjetaIrCasillaProximaUtilidad);
        tarjetasCasualidad.add(tarjetaIrProximoFerro);
        tarjetasCasualidad.add(tarjetaIrProximoFerro1);
        tarjetasCasualidad.add(tarjetaIrStCharles);
        tarjetasCasualidad.add(tarjetaCobrarBanco);
        tarjetasCasualidad.add(tarjetaCarcelSalirCarcel1);
        tarjetasCasualidad.add(tarjetaIrCasillaTresCasillasAtras);
        tarjetasCasualidad.add(tarjetaCarcelIrCarcel1);
//        tarjetasCasualidad.add(tarjetaPagarArreglosMejoras);
        tarjetasCasualidad.add(tarjetaPagarPobres);
        tarjetasCasualidad.add(tarjetaIrCasillaReadingFerro);
        tarjetasCasualidad.add(TarjetaIrCasillaBoardWalk);
        tarjetasCasualidad.add(tarjetaPagarJugadores);
        tarjetasCasualidad.add(tarjetaCobrarImpuestosPrestamos);
        tarjetasCasualidad.add(tarjetaCobrarCrucigrama);
    }

    public void jugar() {
        Jugador jugador = getJugadores().get(0);
        int valorDados;
        valorDados = lanzarDados();
        jugador.getFicha().aumentarPosicion(valorDados);
        int pos = jugador.getFicha().getPosicion();
        Casilla casilla = tablero.getCasillaByPos(pos);
        JOptionPane.showMessageDialog(getGui(), "El jugador " + jugador.getNombre()
                + " sacó " + valorDados + "\nCalló en la posicion " + pos + "\n"
                + "" + casilla.getNombre());
        switch (casilla.getTipoCasilla()) {
            case Casilla.TIPO_CASILLA_PROPIEDAD:
                CasillaPropiedad propiedad = (CasillaPropiedad) casilla;
                JOptionPane.showMessageDialog(getGui(), "Propiedad "+ propiedad.getNombre().toString()+" Valor "+ propiedad.getValor());
                switch (propiedad.getEstado()) {
                    case CasillaPropiedad.ADQUIRIDA:
                        JOptionPane.showMessageDialog(getGui(), "Adquirida");
                        if (!jugador.getCuenta().getPropiedades().contains(propiedad)) {
                            JOptionPane.showMessageDialog(getGui(), "Paga renta.");
                            if (propiedad.getPropietario().cobrarRenta(propiedad, jugador)) {
                                JOptionPane.showMessageDialog(getGui(), jugador.getNombre() + "\n"
                                        + "paga " + propiedad.getRenta() + " \n" + propiedad.getPropietario().getNombre());
                            } else {
                                JOptionPane.showMessageDialog(getGui(), "El jugador no puedo pagar renta\n"
                                        + "queda endedudado");
                            }
                        } else {
                            JOptionPane.showMessageDialog(getGui(), "Propia, no paga renta.");
                        }
                        break;
                    case CasillaPropiedad.DISPONIBLE:
                        JOptionPane.showMessageDialog(getGui(), "Disponible");
                        int opcion = JOptionPane.showConfirmDialog(getGui(), "Desea comprar esta propiedad?");
                        if (opcion == 0) {
                            jugador.comprarPropiedad(banco, propiedad);
                        }
                        break;

                    case CasillaPropiedad.HIPOTECADA:
                        JOptionPane.showMessageDialog(getGui(), "Hhipotecada.\n"
                                + "no se paga renta.");
                        break;
                }
                break;
            case Casilla.TIPO_CASILLA_ESPECIAL:
                JOptionPane.showMessageDialog(getGui(), "Especial");
                CasillaEspecial especial = (CasillaEspecial) casilla;
                switch (especial.getTipoCasillaEspecial()) {
                    case (CasillaEspecial.TIPO_CASILLA_ESPECIAL_EVENTUALIDAD):
                        JOptionPane.showMessageDialog(getGui(), "Coge tarjeta");
                        break;
                    default:
                        JOptionPane.showMessageDialog(getGui(), "Ejecuta accion ");
                        especial.ejecutarAccion(jugador, this);
                }
                break;
        }
        siguienteJugador();
        getGui().mostrarJugadoresEnTabla(getJugadores());
    }

    /**
     * @return the gui
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * @return the jugadores
     */
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
}
