package appsolutegamesgmbh.schnaps2gether.DataStructure;

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
    private boolean angesagt;
    private Karte selbstGespielteKarte;

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
        spielfeld.sendStartMessage();
    }

    public Karte karteAusspielen(int handindex) {
        selbstGespielteKarte = selbst.Hand.get(handindex);
        spiel.Auspielen(selbstGespielteKarte, selbst);
        return selbstGespielteKarte;
    }
}
