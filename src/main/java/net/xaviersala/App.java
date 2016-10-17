package net.xaviersala;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

/**
 * Plantilla base per fer un programa fent servir les llibreries
 * ACM.
 *
 */
public class App extends GraphicsProgram
{

    private static final int MASSAGRANOTES = 20;
    /**
    *
    */
   private static final long serialVersionUID = 1299094805237490891L;
   public static final int AMPLADAPANTALLA = 1000;
   public static final int ALTURAPANTALLA = 600;
   private static final int NUMEROGRANOTES = 3;
   private static final int VELOCITATGRANOTA = 5;
   private static final int VELOCITATPRINCEP = 7;

   private static Random aleatori = new Random();
   private Bassa bassa = null;

    /**
     * Programa principal...
     */
    @Override
    public final void run() {

       Transicio transicio = new Transicio(this, carregaImatge("peto.png"));

       addKeyListeners();
       setSize(AMPLADAPANTALLA, ALTURAPANTALLA);

       // Crear les granotes i els prínceps
       Princep princep = generarPrincep();
       List<Granota> granotes = generarGranotesInicials(NUMEROGRANOTES);

       // Posar els prínceps i les granotes a la bassa
       bassa = new Bassa(this, AMPLADAPANTALLA, ALTURAPANTALLA, carregaImatge("bassa.png"));
       bassa.afegirPrincep(princep);
       bassa.afegirGranotes(granotes);

       clicaPerContinuar("Clica per començar");

       jugar(transicio);


    }

    /**
     * Implementa tota la lògica del joc:
     *   - Espera a que el cavaller trobi una granota
     *   - En funció de la granota que sigui acaba o
     *     n'afegeix una altra.
     *
     * @param transicio Pantalla que surt entre granota i granota
     */
    private void jugar(Transicio transicio) {

        while(bassa.start() != Resultat.CAPTURADA_PRINCESA) {

               transicio.crea();
               // afegir granota
               bassa.afegirGranota(generarGranota(false));
               pause(1000);
               transicio.elimina();

               if (bassa.getNumGranotes() > MASSAGRANOTES) {
                   break;
               }
           }

           removeAll();
           GImage comAcaba;

           if (bassa.getNumGranotes() > MASSAGRANOTES) {
               comAcaba = new GImage("fart.png");
           } else {
               comAcaba = new GImage("rei.png");
           }
           comAcaba.setLocation((getWidth()  - comAcaba.getWidth()) /2,
                           (getHeight() - comAcaba.getHeight())/2);
           add(comAcaba);
    }

/**
 * Genera un príncep.
 *
 * @return
 */
private Princep generarPrincep() {
        GImage imatge = carregaImatge("cavaller.png");
        Princep princepet = new Princep(imatge, VELOCITATPRINCEP);
        return princepet;
    }

/**
 * Genero les granotes amb les imatges.
 *
 * @param num Número de granotes
 * @return llista de granotes generades
 */
private List<Granota> generarGranotesInicials(int num) {

        List<Granota> granotes = new ArrayList<>(num);

        int quiEsPrincesa = aleatori.nextInt(num);

        for(int i=0; i<num; i++) {
            granotes.add(generarGranota(i == quiEsPrincesa));
        }

        return granotes;
    }

/**
 * Genera un objecte de tipus granota.
 *
 * @param esPrincesa
 * @return
 */
private Granota generarGranota(boolean esPrincesa) {
    GImage imatgeGranota;

    if (esPrincesa) {
        imatgeGranota = carregaImatge("princesa.png");
    } else {
        imatgeGranota = carregaImatge("granota.png");
    }
    add(imatgeGranota);
    return new Granota(imatgeGranota, esPrincesa, VELOCITATGRANOTA);
}

/**
 * Carrega una imatge de disc i la posa a pantalla
 *
 * @return
 */
private GImage carregaImatge(String nom) {
    GImage imatge = null;
    try {
        imatge = new GImage(nom);
        add(imatge);
    } catch (Exception e) {
        System.out.println("Imatge no trobada " + nom);
    }
    return imatge;
}

/**
 * Control·la els moviments del cavaller.
 *
 */
public void keyPressed(KeyEvent e){

    switch(e.getKeyCode()) {
    case KeyEvent.VK_UP:
        bassa.mouPrincep(0, -1);
        break;
    case KeyEvent.VK_DOWN:
        bassa.mouPrincep(0, 1);
        break;
    case KeyEvent.VK_LEFT:
        bassa.mouPrincep(-1, 0);
        break;
    case KeyEvent.VK_RIGHT:
        bassa.mouPrincep(1, 0);
        break;

    }
}

/**
 * Clica per començar.
 */
private void clicaPerContinuar(String missatge) {
    GLabel label = new GLabel(missatge);
    double x = (getWidth() - label.getWidth()) / 2;
    double y = (getHeight() + label.getAscent()) / 2;
    add(label, x, y);
    waitForClick();
    remove(label);
}

}
