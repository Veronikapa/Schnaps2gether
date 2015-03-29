package appsolutegamesgmbh.schnaps2gether.DataStructure;

/**
 * Created by Kerstin on 29.03.2015.
 */
public class Spieler {

    Kartendeck Hand;
    int Punkte;
    Kartendeck Gestochen;
    boolean istdran;

    public boolean isIstdran() {
        return istdran;
    }

    public void setIstdran(boolean istdran) {
        this.istdran = istdran;
    }

    public void setHand(Kartendeck hand) {
        Hand = hand;
    }

    public void setPunkte(int punkte) {
        Punkte = punkte;
    }

    public void setGestochen(Kartendeck gestochen) {
        Gestochen = gestochen;
    }

    public Kartendeck getHand() {
        return Hand;
    }

    public int getPunkte() {
        return Punkte;
    }

    public Kartendeck getGestochen() {
        return Gestochen;
    }

    public Spieler()
    {
        Hand = new Kartendeck();
        Punkte = 0;
        Gestochen = new Kartendeck();
        istdran = false;
    }




}
