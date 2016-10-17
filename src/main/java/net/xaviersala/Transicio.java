package net.xaviersala;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import acm.graphics.GImage;
import acm.graphics.GOval;

public class Transicio {

    private Random aleatori = new Random();

    App pantalla;
    private GImage imatge;
    private List<GOval> rodones;

    Transicio(App pantalla, GImage imatge) {
        this.pantalla = pantalla;
        this.imatge = imatge;
        pantalla.remove(imatge);
        rodones = new ArrayList<>();
    }

    /** 
     * Centra la imatge en la pantalla.
     */
    void posicionaImatge() {
        imatge.setLocation((pantalla.getWidth() - imatge.getWidth())/2,
                           (pantalla.getHeight()- imatge.getHeight())/2 );
    }

    /**
     * Crea el fons de pantalla i posa el tio fent petons al mig.
     */
    void crea() {
        posicionaImatge();
        for(int i=0; i<100; i++) {
            GOval rodona = new GOval(aleatori.nextInt(pantalla.getWidth()),
                                     aleatori.nextInt(pantalla.getHeight()),
                                     aleatori.nextInt(pantalla.getWidth()/4),
                                     aleatori.nextInt(pantalla.getHeight()/4));
            rodona.setFilled(true);
            rodona.setFillColor(new Color(aleatori.nextFloat(), aleatori.nextFloat(), aleatori.nextFloat()));
            pantalla.add(rodona);
            rodones.add(rodona);
        }
        pantalla.add(imatge);
    }

    void elimina() {
        for(GOval rodona: rodones) {
            pantalla.remove(rodona);
        }
        rodones.clear();
        pantalla.remove(imatge);
    }
}
