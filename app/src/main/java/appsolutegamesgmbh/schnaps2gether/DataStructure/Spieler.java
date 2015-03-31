package appsolutegamesgmbh.schnaps2gether.DataStructure;

import java.util.ArrayList;

public class Spieler {

    public ArrayList<Karte> Hand;
    private int Punkte;
    public ArrayList<Karte> Gestochen;
    private boolean istdran;

    public int getPunkte() {
        return Punkte;
    }

    public void setPunkte(int punkte) {
        Punkte = punkte;
    }

    public boolean isIstdran() {
        return istdran;
    }

    public void setIstdran(boolean istdran) {
        this.istdran = istdran;
    }

    public Spieler()
    {
        Hand = new ArrayList<Karte>(5);
        Punkte = 0;
        Gestochen = new ArrayList<Karte>(20);
        istdran = false;
    }

}
