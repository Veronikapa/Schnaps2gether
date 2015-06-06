package appsolutegamesgmbh.schnaps2gether.DataStructure;

import java.util.ArrayList;

import appsolutegamesgmbh.schnaps2gether.GUI.Spielfeld4Host;

/**
 * Created by kirederf on 05.06.2015.
 */
public class Spielfeld4Logik {
    private Spielfeld4Host spielfeld;
    private Spiel4 spiel;
    private Bummerl4 bummerl;
    private Spieler selbst;
    private Spieler mitspieler;
    private Spieler gegner1;
    private Spieler gegner2;
    private ArrayList<Spieler> alleSpieler;
    private boolean angesagt;
    private ArrayList<Karte> tischKarten;
    private Spieler ausspielenderSpieler;
    private Spieler amZugSpieler;

    public Spielfeld4Logik(Spielfeld4Host spielfeld) {
        this.angesagt = false;
        this.bummerl = new Bummerl4();
        this.spielfeld = spielfeld;
        try {
            this.spiel = new Spiel4(bummerl.getAnzahlSpiele());
        } catch (Exception e) {

        }
        this.selbst = spiel.getS1();
        this.mitspieler = spiel.getS3();
        this.gegner1 = spiel.getS2();
        this.gegner2 = spiel.getS4();
        this.ausspielenderSpieler = this.selbst;
        this.amZugSpieler = this.selbst;
        this.alleSpieler = new ArrayList<Spieler>();
        this.alleSpieler.add(selbst);
        this.alleSpieler.add(mitspieler);
        this.alleSpieler.add(gegner1);
        this.alleSpieler.add(gegner2);
        this.tischKarten = new ArrayList<Karte>();
        spielfeld.sendStartMessage();
    }

    public Karte karteAusspielen(int handindex, int ausspielenderSpielerNr) {
        Spieler ausspielenderSpieler = alleSpieler.get(ausspielenderSpielerNr);
        Karte gespielteKarte = ausspielenderSpieler.Hand.get(handindex);
        tischKarten.add(gespielteKarte);
        spiel.Auspielen(gespielteKarte, ausspielenderSpieler);
        if (tischKarten.size()==4) {
            zugende();
        } else {
            //andererSpielerSpieltAus
        }
        //gegnerischeHandaktualisieren
        return gespielteKarte;
    }

    public Spieler zugende() {

        return null;
    }
}
