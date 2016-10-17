package net.xaviersala;

import java.util.ArrayList;
import java.util.List;

import acm.graphics.GImage;
import acm.graphics.GRectangle;

/**
 * Objecte que representa la bassa on hi haurà les granotes i el cavaller.
 *
 * @author xavier
 *
 */

public class Bassa {

    /**
     * Velocitat a la que es mouen
     */
    private static final int SLEEP = 80;

    App pissarra;
    GImage fons;
    GRectangle dimensions;

    Princep princep;
    List<Granota> granotes;

    public Bassa(App pissarra, int ample, int alt, GImage fons) {
        // TODO: Canviar-ho per una cosa més digna
        this.pissarra = pissarra;
        dimensions = new GRectangle(100, 100, ample - 200, alt - 200);
        granotes = new ArrayList<>();
        this.fons = fons;
        this.fons.sendToBack();
        this.fons.setSize(ample, alt);
    }

    /**
     * Afegir les granotes a la bassa.
     *
     * @param granotes
     *            llista de granotes
     */
    public void afegirGranotes(List<Granota> granotes) {
        this.granotes = granotes;
    }

    /**
     * Afegeix una granota a la bassa.
     *
     * @param granota
     *            nova granota
     */
    public void afegirGranota(Granota granota) {
        granotes.add(granota);
    }

    /**
     * Afegir el príncep
     */
    public void afegirPrincep(Princep princep) {
        this.princep = princep;
    }

    /**
     * Barreja les granotes per aconseguir una nova posició.
     */
    public void barreja() {

        // Posiciona el príncep al mig

        for (Granota granota : granotes) {
            granota.collocaGranota();
        }

    }

    /**
     * Fa una ronda fins que el princep atrapa una granota.
     *
     *  0: Ha capturat la princesa
     *
     */
    public Resultat start() {
        Resultat resultat = Resultat.CAPTURADA_GRANOTA;
        boolean granotaAtrapada = false;

        barreja();

        while (!granotaAtrapada) {

            for (Granota granota : granotes) {
                granota.mou();
                if (!granota.getImatge().getBounds().intersects(dimensions)) {
                    granota.inverteixDireccio((int) (dimensions.getX() + dimensions.getWidth() / 2),
                            (int) (dimensions.getY() + dimensions.getHeight() / 2));
                }

                if (granotaCapturada(granota, princep)) {
                    granotaAtrapada = true;
                    if (granota.isPrincesa()) {
                        resultat = Resultat.CAPTURADA_PRINCESA;
                    }
                    break;
                }
            }

            pissarra.pause(SLEEP);
        }
        return resultat;
    }


    private boolean granotaCapturada(Granota granota, Princep princep2) {
        return (granota.getImatge().getBounds().intersects(princep.getImatge().getBounds()));
    }

    /**
     * Moure el príncep en la direcció especificada
     * @param i
     * @param j
     */
    public void mouPrincep(int i, int j) {

        // TODO: Comprovar si surt de la pantalla
        GRectangle posicio = princep.getImatge().getBounds();
        posicio.translate(i, j);
        GRectangle resultat = fons.getBounds().intersection(posicio);

        if (resultat.equals(posicio)) {
            princep.mou(i, j);
        }

    }

    public int getNumGranotes() {
        return granotes.size();
    }

}
