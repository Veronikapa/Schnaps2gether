package appsolutegamesgmbh.schnaps2gether.DataStructure;

import java.util.ArrayList;

import appsolutegamesgmbh.schnaps2gether.GUI.Spielfeld4Host;

/**
 * Created by kirederf on 05.06.2015.
 */
public class Spielfeld4Logik {
    //Konstanten fuer Teams
    private static final int TEAM1 = 0;
    private static final int TEAM2 = 1;

    private Spielfeld4Host spielfeld;
    private Spiel4 spiel;
    private Bummerl4 bummerl;
    private int bummerlTeam1Vorher;
    private int bummerlTeam2Vorher;
    private Spieler selbst;
    private Spieler mitspieler;
    private Spieler gegner1;
    private Spieler gegner2;
    private ArrayList<Spieler> alleSpieler;
    private boolean angesagt;
    private ArrayList<Karte> tischKarten;
    private int ausspielenderSpielerNr;
    private int amZugSpielerNr;
    private int letzterAmZugSpielerNr;
    private int ruferSpielerNr;
    private boolean zugBeginn;
    private ArrayList<String> spieleAnsagbar;
    private ArrayList<Boolean> handkartenSpielbar;
    private boolean hatVierziger;
    private boolean hatZwanziger;
    private ArrayList<String> verfuegbareZwanziger;
    private boolean spielRundenEnde;
    private boolean spielEnde;
    private boolean spielRufRunde;
    private boolean fleckRunde;
    private boolean gegenFleckRunde;
    private int nichtGefleckt;
    private int winners;
    private int finalWinners;

    public Spielfeld4Logik(Spielfeld4Host spielfeld) {
        this.bummerl = new Bummerl4();
        this.bummerlTeam1Vorher = bummerl.getPunkteS1();
        this.bummerlTeam2Vorher = bummerl.getPunkteS2();
        this.spielfeld = spielfeld;
        try {
            this.spiel = new Spiel4(bummerl.getAnzahlSpiele());
        } catch (Exception e) {

        }
        this.selbst = spiel.getS1();
        this.mitspieler = spiel.getS3();
        this.gegner1 = spiel.getS2();
        this.gegner2 = spiel.getS4();
        this.ausspielenderSpielerNr = 0;
        this.amZugSpielerNr = 0;
        this.letzterAmZugSpielerNr = 3;
        this.ruferSpielerNr = amZugSpielerNr;
        this.nichtGefleckt = 0;
        this.zugBeginn = true;
        //this.angesagt = false;
        this.hatVierziger = false;
        this.hatZwanziger = false;
        this.alleSpieler = new ArrayList<Spieler>();
        this.alleSpieler.add(selbst);
        this.alleSpieler.add(mitspieler);
        this.alleSpieler.add(gegner1);
        this.alleSpieler.add(gegner2);
        this.tischKarten = new ArrayList<Karte>();
        this.handkartenSpielbar = new ArrayList<Boolean>();
        this.verfuegbareZwanziger = new ArrayList<String>();
        this.spielRundenEnde = false;
        this.spielEnde = false;
        berechneMoeglicheAktionen();
        spielfeld.sendStartMessage();
    }

    public Karte karteAusspielen(int handindex) {
        Spieler ausspielenderSpieler = alleSpieler.get(ausspielenderSpielerNr);
        Karte gespielteKarte = ausspielenderSpieler.Hand.get(handindex);
        tischKarten.add(gespielteKarte);
        spiel.Auspielen(gespielteKarte, ausspielenderSpieler);
        if (tischKarten.size()==4) {
            zugende();
        } else {
            zugBeginn = false;
            ausspielenderSpielerNr = (ausspielenderSpielerNr + 1)%4;
            berechneSpielbareKarten();
        }
        return gespielteKarte;
    }

    private void zugende() {
        spiel.ZugAuswerten(tischKarten.get(0), tischKarten.get(1), tischKarten.get(2), tischKarten.get(3));
        if (spiel.istSpielzuEnde(bummerl)) {
            spielRundenEnde();
        } else {
            tischKarten.clear();
            if (alleSpieler.get(0).isIstdran()) amZugSpielerNr = 0;
            else if (alleSpieler.get(1).isIstdran()) amZugSpielerNr = 1;
            else if (alleSpieler.get(2).isIstdran()) amZugSpielerNr = 2;
            else amZugSpielerNr = 3;
            ausspielenderSpielerNr = amZugSpielerNr;
            zugBeginn = true;
            berechneMoeglicheAktionen();
        }
    }

    private void berechneMoeglicheAktionen() {
        berechneSpielbareKarten();
        hatVierziger = false;
        hatZwanziger = false;
        verfuegbareZwanziger.clear();
        Spieler ausspielenderSpieler = alleSpieler.get(ausspielenderSpielerNr);
        ArrayList<String> zwanzigerUndVierziger= spiel.hat20er(ausspielenderSpieler);
        if (zwanzigerUndVierziger.contains(spiel.getAngesagteFarbe())) {
            hatVierziger = true;
            zwanzigerUndVierziger.remove(spiel.getAngesagteFarbe());
        }
        if (!zwanzigerUndVierziger.isEmpty()) {
            hatZwanziger = true;
            this.verfuegbareZwanziger.addAll(zwanzigerUndVierziger);
        }
    }

    private void berechneSpielbareKarten() {
        handkartenSpielbar.clear();
        Spieler ausspielenderSpieler = alleSpieler.get(ausspielenderSpielerNr);
        for (Karte k: ausspielenderSpieler.Hand) {
            handkartenSpielbar.add(spiel.DarfKarteAuswaehlen(k, ausspielenderSpieler));
        }
    }

    private void berechneAnsagbareSpiele() {
        Spieler amZugSpieler = alleSpieler.get(amZugSpielerNr);
        spieleAnsagbar = new ArrayList<>();
        try {
            if (spiel.DarfSpielAnsagen(new Rufspiel("Schnapser"), amZugSpieler))
                spieleAnsagbar.add("Schnapser");
            if (spiel.DarfSpielAnsagen(new Rufspiel("Land"), amZugSpieler))
                spieleAnsagbar.add("Land");
            if (spiel.DarfSpielAnsagen(new Rufspiel("Kontraschnapser"), amZugSpieler))
                spieleAnsagbar.add("Kontraschnapser");
            if (spiel.DarfSpielAnsagen(new Rufspiel("Bauernschnapser"), amZugSpieler))
                spieleAnsagbar.add("Bauernschnapser");
            if (spiel.DarfSpielAnsagen(new Rufspiel("Kontrabauernschnapser"), amZugSpieler))
                spieleAnsagbar.add("Kontrabauernschnapser");
            if (spiel.DarfSpielAnsagen(new Rufspiel("Farbenjodler"), amZugSpieler))
                spieleAnsagbar.add("Farbenjodler");
            if (spiel.DarfSpielAnsagen(new Rufspiel("Herrenjodler"), amZugSpieler))
                spieleAnsagbar.add("Herrenjodler");
        } catch (Exception e) {

        }
    }

    private void berechneSpielerAmFlecken() {
        if (fleckRunde) {
            int ersterAmZugSpielerNr = (letzterAmZugSpielerNr + 1) % 4;
            if (ruferSpielerNr == 0 || ruferSpielerNr == 2) {
                if (ersterAmZugSpielerNr == 0 || ersterAmZugSpielerNr == 1) {
                    amZugSpielerNr = 1;
                } else {
                    amZugSpielerNr = 3;
                }
            } else if (ruferSpielerNr == 1 || ruferSpielerNr == 3) {
                if (ersterAmZugSpielerNr == 3 || ersterAmZugSpielerNr == 0) {
                    amZugSpielerNr = 0;
                } else {
                    amZugSpielerNr = 2;
                }
            }
        }
    }

    public void trumpfAnsagen(String farbe) {
        if (farbe == null) {
            farbe = spiel.Aufdrehen().getFarbe();
        }
        spiel.Trumpfansagen(farbe, bummerl.getAnzahlSpiele());
        spielRufRunde = true;
        berechneAnsagbareSpiele();
    }

    public void spielRufen(String rufspiel) {
        if (amZugSpielerNr==letzterAmZugSpielerNr) {
            spielRufRunde = false;
            fleckRunde = true;
            berechneSpielerAmFlecken();
        }
        Spieler amZugSpieler = alleSpieler.get(amZugSpielerNr);
        if (!rufspiel.equals("Weiter")) {
            try {
                spiel.SpielAnsagen(new Rufspiel(rufspiel), amZugSpieler);
            } catch (Exception e) {

            }
            ruferSpielerNr = amZugSpielerNr;
            if (spiel.istSpielzuEnde(bummerl)) {
                spielRundenEnde();
            }
        }
        amZugSpielerNr = (amZugSpielerNr + 1) % 4;
    }

    public void flecken() {
        spiel.Flecken();
        if (gegenFleckRunde) {
            gegenFleckRunde = false;
            if (selbst.isIstdran()) amZugSpielerNr = 0;
            else if (gegner1.isIstdran()) amZugSpielerNr = 1;
            else if (mitspieler.isIstdran()) amZugSpielerNr = 2;
            else amZugSpielerNr = 3;
        } else {
            fleckRunde = false;
            gegenFleckRunde = true;
            amZugSpielerNr = (amZugSpielerNr + 1 + nichtGefleckt * 2) % 4;
            nichtGefleckt = 0;
        }
    }

    public void nichtFlecken() {
        nichtGefleckt++;
        if (nichtGefleckt == 1) {
            amZugSpielerNr = (amZugSpielerNr + 2) % 4;
        } else if (nichtGefleckt==2) {
            fleckRunde = false;
            gegenFleckRunde = false;
            amZugSpielerNr = (letzterAmZugSpielerNr + 1) % 4;
        }
    }

    public void zwanzigerAnsagen(String farbe) {
        Spieler amZugSpieler = alleSpieler.get(amZugSpielerNr);
        spiel.Ansagen20er(farbe, amZugSpieler);
        if (spiel.istSpielzuEnde(bummerl)) spielRundenEnde();
    }

    public void vierzigerAnsagen() {
        Spieler amZugSpieler = alleSpieler.get(amZugSpielerNr);
        spiel.Ansagen20er(spiel.getAngesagteFarbe(), amZugSpieler);
        if (spiel.istSpielzuEnde(bummerl)) spielRundenEnde();
    }

    private void spielRundenEnde() {
        spielRundenEnde = true;
        if (bummerl.istBummerlzuEnde()) {
            spielEnde();
        } else {
            if (bummerl.getPunkteS1()>bummerlTeam1Vorher) {
                winners = TEAM1;
            } else {
                winners = TEAM2;
            }
        }
    }

    public void neueSpielRunde() {
        try {
            this.spiel = new Spiel4(bummerl.getAnzahlSpiele());
        } catch (Exception e) {

        }
        this.bummerlTeam1Vorher = bummerl.getPunkteS1();
        this.bummerlTeam2Vorher = bummerl.getPunkteS2();
        this.selbst = spiel.getS1();
        this.mitspieler = spiel.getS3();
        this.gegner1 = spiel.getS2();
        this.gegner2 = spiel.getS4();
        this.ausspielenderSpielerNr = bummerl.getAnzahlSpiele() % 4;
        this.amZugSpielerNr = ausspielenderSpielerNr;
        this.letzterAmZugSpielerNr = (amZugSpielerNr + 3) % 4;
        this.ruferSpielerNr = amZugSpielerNr;
        this.nichtGefleckt = 0;
        this.zugBeginn = true;
        //this.angesagt = false;
        this.hatVierziger = false;
        this.hatZwanziger = false;
        this.alleSpieler.clear();
        this.alleSpieler.add(selbst);
        this.alleSpieler.add(mitspieler);
        this.alleSpieler.add(gegner1);
        this.alleSpieler.add(gegner2);
        this.tischKarten.clear();
        this.handkartenSpielbar.clear();
        this.verfuegbareZwanziger.clear();
        this.spielRundenEnde = false;
        this.spielEnde = false;
        berechneMoeglicheAktionen();
        spielfeld.sendStartMessage();
    }

    private void spielEnde() {
        spielEnde = true;
        if (bummerl.getPunkteS1() >= 24) {
            finalWinners = TEAM1;
        } else {
            finalWinners = TEAM2;
        }
    }

    public int getAmZugSpielerNr() {
        return this.amZugSpielerNr;
    }

    public int getAusspielenderSpielerNr() {
        return this.ausspielenderSpielerNr;
    }

    public boolean isZugBeginn() {
        return this.zugBeginn;
    }

    public ArrayList<Boolean> getHandkartenSpielbar() {
        return this.handkartenSpielbar;
    }

    public boolean hasHatZwanziger() {
        return this.hatZwanziger;
    }

    public ArrayList<String> getVerfuegbareZwanziger() {
        return this.verfuegbareZwanziger;
    }

    public boolean hasHatVierziger() {
        return this.hatVierziger;
    }

    public Spieler getSelbst() {
        return selbst;
    }

    public Spieler getMitspieler() {
        return mitspieler;
    }

    public Spieler getGegner1() {
        return gegner1;
    }

    public Spieler getGegner2() {
        return gegner2;
    }

    public ArrayList<Spieler> getAlleSpieler() {
        return alleSpieler;
    }

    public boolean isSpielRundenEnde() {
        return spielRundenEnde;
    }

    public boolean isSpielZuEnde() {
        return spielEnde;
    }

    public int getWinners() {
        return winners;
    }

    public int getFinalWinners() {
        return finalWinners;
    }

    public String getAngesagteFarbe() {
        return spiel.getAngesagteFarbe();
    }

    public ArrayList<String> getSpieleAnsagbar() {
        return spieleAnsagbar;
    }

    public boolean isSpielRufRunde() {
        return spielRufRunde;
    }

    public boolean isFleckRunde() {
        return fleckRunde;
    }

    public boolean isGegenFleckRunde() {
        return gegenFleckRunde;
    }

    public String getSpiel() {
        return spiel.getSpiel().getSpiel();
    }

    public Bummerl4 getBummerl() {
        return bummerl;
    }

    public ArrayList<Karte> getTischKarten() {
        return tischKarten;
    }
}
