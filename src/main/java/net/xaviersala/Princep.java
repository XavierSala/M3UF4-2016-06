package net.xaviersala;

import acm.graphics.GImage;

public class Princep {

    GImage imatge;
    int velocitat;

    public Princep(GImage imatge, int velocitat) {
        this.imatge = imatge;
        this.velocitat = velocitat;
    }

    /**
     * @return the imatge
     */
    public GImage getImatge() {
        return imatge;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Princep ("+ imatge.getX() + "," + imatge.getY() + ")";
    }

    public void mou(int x, int y) {
        imatge.move(x * velocitat, y * velocitat);

    }


}
