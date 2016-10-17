package net.xaviersala;

import java.util.Random;

import acm.graphics.GImage;
import acm.graphics.GMath;

/**
 * Classe que representa una granota i permet determinar
 * si la granota és una princesa o no.
 *
 * @author xavier
 *
 */
public class Granota {

    private static final double DELTA = 0.1;

    private int velocitat;

    Random aleatori = new Random();
    private GImage imatge;
    private boolean princesa;

    int angle;
    double horaDeCanviar;


    /**
     * Crea una granota i li proporciona una imatge.
     *
     * @param imatge
     *
     */
    public Granota(GImage imatge, boolean princesa, int velocitat) {
        super();
        this.imatge = imatge;
        this.velocitat = velocitat;

        this.princesa = princesa;
        if (imatge != null) {
            collocaGranota();
            canviaDireccio();
        }
    }

    /**
     * @return the imatge
     */
    public GImage getImatge() {
        return imatge;
    }

    /**
     * @return the princesa
     */
    public boolean isPrincesa() {
        return princesa;
    }

    /**
     * @param princesa the princesa to set
     */
    public void setPrincesa(boolean princesa) {
        this.princesa = princesa;
    }

    /**
     * Col·loca la granota aleatòriament per la pantalla.
     *
     */
    public void collocaGranota() {
        imatge.setLocation(aleatori.nextInt(App.AMPLADAPANTALLA - (int) imatge.getWidth()),
                           aleatori.nextInt(App.ALTURAPANTALLA - (int) imatge.getHeight()));

    }

    public void canviaDireccio() {
        horaDeCanviar = 1.5;
        angle = aleatori.nextInt(360);
    }

    /**
     *  Mou la granota
     */
    public void mou() {

        horaDeCanviar -= DELTA;

        if (horaDeCanviar <= 0) {
            canviaDireccio();
        }

        imatge.movePolar(velocitat, angle);

    }

    public void inverteixDireccio(int x, int y) {
        angle = (int) GMath.angle(imatge.getX(), imatge.getY(), x, y);

    }


}
