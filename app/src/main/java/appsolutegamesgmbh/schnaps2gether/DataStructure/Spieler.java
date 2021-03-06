package appsolutegamesgmbh.schnaps2gether.DataStructure;

import java.util.ArrayList;

public class Spieler {

    public ArrayList<Karte> Hand;
    private int Punkte;
    public ArrayList<Karte> Gestochen;
    private boolean istdran;
    private boolean angesagt20er;
    private boolean zugedreht;
    private int merkePunkte;
    private Spieler mitspieler;
    private int punktevorzugedreht;

    public int getPunktevorzugedreht() {
        return punktevorzugedreht;
    }

    public void setPunktevorzugedreht(int punktevorzugedreht) {
        this.punktevorzugedreht = punktevorzugedreht;
    }

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

    public boolean isAngesagt20er() {
        return angesagt20er;
    }

    public void setAngesagt20er(boolean angesagt20er) {
        this.angesagt20er = angesagt20er;
    }

    public int getMerkePunkte() {
        return merkePunkte;
    }

    public void setMerkePunkte(int merkePunkte) {
        this.merkePunkte = merkePunkte;
    }

    public Spieler getMitspieler() {
        return mitspieler;
    }

    public void setMitspieler(Spieler mitspieler) {
        this.mitspieler = mitspieler;
    }

    public boolean isZugedreht() {
        return zugedreht;
    }

    public void setZugedreht(boolean zugedreht) {
        this.zugedreht = zugedreht;
    }

    public Spieler()
    {
        Hand = new ArrayList<Karte>(5);
        Punkte = 0;
        Gestochen = new ArrayList<Karte>(20);
        istdran = false;
        mitspieler = null;
        zugedreht = false;
    }

}
