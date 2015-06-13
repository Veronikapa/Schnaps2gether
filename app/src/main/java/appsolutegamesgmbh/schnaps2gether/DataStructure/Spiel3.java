package appsolutegamesgmbh.schnaps2gether.DataStructure;


import java.util.ArrayList;
import java.util.Collections;

public class Spiel3 {

    private ArrayList<Karte> kartendeck;
    private ArrayList<Karte> stapel;
    public ArrayList<Karte> Talon;
    private Spieler s1;
    private Spieler s2;
    private Spieler s3;
    private String trumpf;
    private String angesagteFarbe;
    private Karte hoechstekarteamTisch;
    private Spieler besitzer; //Besitzer der höchsten Karte, die am Tisch liegt
    private Rufspiel spiel;
    private Spieler spieler; //derjenige, der das Spiel macht (die anderen zwei Spieler spielen zusammen)
    private int flecken;
    private ArrayList<Spieler> sieger;


    public ArrayList<Karte> getTalon() {
        return Talon;
    }

    public Spieler getS1() {
        return s1;
    }

    public Spieler getS2() {
        return s2;
    }

    public Spieler getS3() {
        return s3;
    }

    public String getTrumpf() {
        return trumpf;
    }


    public Rufspiel getSpiel() {
        return spiel;
    }

    public void setSpiel(Rufspiel spiel) {
        this.spiel = spiel;
    }

    public Spieler getSpieler() {
        return spieler;
    }

    public void setSpieler(Spieler spieler) {
        this.spieler = spieler;
    }

    public Spieler getBesitzer() {
        return besitzer;
    }

    public void setBesitzer(Spieler besitzer) {
        this.besitzer = besitzer;
    }

    public Karte getHoechstekarteamTisch() {
        return hoechstekarteamTisch;
    }

    public void setHoechstekarteamTisch(Karte hoechstekarteamTisch) {
        this.hoechstekarteamTisch = hoechstekarteamTisch;
    }

    public void setAngesagteFarbe(String angesagteFarbe) {
        this.angesagteFarbe = angesagteFarbe;
    }

    public void setTrumpf(String trumpf) {
        this.trumpf = trumpf;
    }

    public String getAngesagteFarbe() {
        return angesagteFarbe;
    }

    public ArrayList<Spieler> getSieger() {
        return sieger;
    }

    public void setSieger(ArrayList<Spieler> sieger) {
        this.sieger = sieger;
    }

    public Spiel3(int AnzahlSpiele) throws WrongGameException
    {
        kartendeck = Karte.erstelleKartendeck();
        s1 = new Spieler();
        s2 = new Spieler();
        s3 = new Spieler();
        angesagteFarbe = null;

        if(AnzahlSpiele%3 == 0) {
            s1.setIstdran(true);
            spieler = s1;
            s1.setMitspieler(s1);
            s2.setMitspieler(s3);
            s3.setMitspieler(s2);
        }
        else if(AnzahlSpiele%3 == 1) {
            s2.setIstdran(true);
            spieler = s2;
            s1.setMitspieler(s3);
            s2.setMitspieler(s2);
            s3.setMitspieler(s1);
        }
        else {
            s3.setIstdran(true);
            spieler = s3;
            s1.setMitspieler(s2);
            s2.setMitspieler(s1);
            s3.setMitspieler(s3);
        }

        spiel = new Rufspiel("normal");
        flecken = 1;

        Anfangsdeck(AnzahlSpiele);
    }

    private void Anfangsdeck(int AnzahlSpiele)
    {
        KartenMischen();

        if(AnzahlSpiele%3 == 0) {
            //Spieler 1 bekommt 3 Karten
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Spieler 2 bekommt 3 Karten
            s2.Hand.add(stapel.get(0));
            stapel.remove(0);
            s2.Hand.add(stapel.get(0));
            stapel.remove(0);
            s2.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Spieler 3 bekommt 3 Karten
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Talonkarten ausgeben
            Talon.add(stapel.get(0));
            stapel.remove(0);
            Talon.add(stapel.get(0));
            stapel.remove(0);
        }
        else if(AnzahlSpiele%3 == 1)
        {
            //Spieler 2 bekommt 3 Karten
            s2.Hand.add(stapel.get(0));
            stapel.remove(0);
            s2.Hand.add(stapel.get(0));
            stapel.remove(0);
            s2.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Spieler 3 bekommt 3 Karten
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Spieler 1 bekommt 3 Karten
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Talonkarten ausgeben
            Talon.add(stapel.get(0));
            stapel.remove(0);
            Talon.add(stapel.get(0));
            stapel.remove(0);
        }
        else
        {
            //Spieler 3 bekommt 3 Karten
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Spieler 1 bekommt 3 Karten
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Spieler 2 bekommt 3 Karten
            s2.Hand.add(stapel.get(0));
            stapel.remove(0);
            s2.Hand.add(stapel.get(0));
            stapel.remove(0);
            s2.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Talonkarten ausgeben
            Talon.add(stapel.get(0));
            stapel.remove(0);
            Talon.add(stapel.get(0));
            stapel.remove(0);
        }

    }

    //nächste Karte am Stapel gibt die Trumpf an
    public Karte Aufdrehen()
    {
        return stapel.get(0);
    }


    //Ansagen der Trumpf und Austeilen der restlichen Karten
    public void Trumpfansagen(String trumpf, int AnzahlSpiele)
    {
        this.trumpf = trumpf;

        if(AnzahlSpiele%3 == 0) {
            //Spieler 1 bekommt 3 Karten
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Spieler 2 bekommt 3 Karten
            s2.Hand.add(stapel.get(0));
            stapel.remove(0);
            s2.Hand.add(stapel.get(0));
            stapel.remove(0);
            s2.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Spieler 3 bekommt 3 Karten
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);
        }
        else if(AnzahlSpiele%3 == 1)
        {
            //Spieler 2 bekommt 3 Karten
            s2.Hand.add(stapel.get(0));
            stapel.remove(0);
            s2.Hand.add(stapel.get(0));
            stapel.remove(0);
            s2.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Spieler 3 bekommt 3 Karten
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Spieler 1 bekommt 3 Karten
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);
        }
        else
        {
            //Spieler 3 bekommt 3 Karten
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Spieler 1 bekommt 3 Karten
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Spieler 2 bekommt 3 Karten
            s2.Hand.add(stapel.get(0));
            stapel.remove(0);
            s2.Hand.add(stapel.get(0));
            stapel.remove(0);
            s2.Hand.add(stapel.get(0));
            stapel.remove(0);
        }


        //Hand Spieler 1 sortieren
        Collections.sort(s1.Hand, new KartenKomparator());

        //Hand Spieler 2 sortieren
        Collections.sort(s2.Hand,new KartenKomparator());

        //Hand Spieler 3 sortieren
        Collections.sort(s3.Hand,new KartenKomparator());
    }

    private void KartenMischen()
    {
        stapel = new ArrayList<Karte>(20);
        Talon = new ArrayList<Karte>(2);
        sieger = new ArrayList<Spieler>(2);
        int random = 0;
        for(int a = 20; a > 0; a--)
        {
            random = (int) (Math.random()*a);
            stapel.add(kartendeck.get(random));
            kartendeck.remove(random);
        }
    }

    public void Auspielen(Karte karte, Spieler s)
    {
        //Ausgespielte Karte wird aus Hand von Spieler entfernt
        s.Hand.remove(karte);
        s.setIstdran(false);

        //Überprüfen ob Karte höher als die bereits ausgepielten Karten
        if(hoechstekarteamTisch == null) {
            hoechstekarteamTisch = karte;
            besitzer = s;
        }
        else
        {
            if(hoechstekarteamTisch.getFarbe().equals(karte.getFarbe()))
            {
                if(hoechstekarteamTisch.getPunkte() < karte.getPunkte()) {
                    hoechstekarteamTisch = karte;
                    besitzer = s;
                }
            }
            else if(karte.getFarbe().equals(trumpf) && !(spiel.getSpiel().equals("Land"))) {
                hoechstekarteamTisch = karte;
                besitzer = s;
            }
        }
    }

    public boolean DarfKarteAuswaehlen(Karte karte, Spieler s)
    {
        if (angesagteFarbe != null) {
            if (karte.getFarbe().equals(angesagteFarbe) &&
                    (karte.getWertigkeit().equals("König") || karte.getWertigkeit().equals("Dame"))) {
                return true;
            }
            else {
                return false;
            }
        }

        if(hoechstekarteamTisch == null)
            return true;
        else
        {
            if(hoechstekarteamTisch.getFarbe().equals(karte.getFarbe()))
            {
                if(hoechstekarteamTisch.getPunkte() < karte.getPunkte())
                    return true;
                else
                {
                    if(hoechstekarteamTisch.getPunkte() == 3 &&  (s.Hand.contains(new Karte(hoechstekarteamTisch.getFarbe(),"König",4)) || s.Hand.contains(new Karte(hoechstekarteamTisch.getFarbe(),"10er",10)) ||
                            s.Hand.contains(new Karte(hoechstekarteamTisch.getFarbe(),"Ass",11))))
                        return false;
                    else if(hoechstekarteamTisch.getPunkte() == 4 && (s.Hand.contains(new Karte(hoechstekarteamTisch.getFarbe(),"10er",10)) || s.Hand.contains(new Karte(hoechstekarteamTisch.getFarbe(),"Ass",11))))
                        return false;
                    else if(hoechstekarteamTisch.getPunkte() == 10 && s.Hand.contains(new Karte(hoechstekarteamTisch.getFarbe(),"Ass",11)))
                        return false;
                    else
                        return true;
                }
            }
            else if(s.Hand.contains(new Karte(hoechstekarteamTisch.getFarbe(),"Bube",2)) || s.Hand.contains(new Karte(hoechstekarteamTisch.getFarbe(),"Dame",3)) ||
                    s.Hand.contains(new Karte(hoechstekarteamTisch.getFarbe(),"König",4)) || s.Hand.contains(new Karte(hoechstekarteamTisch.getFarbe(),"10er",10)) ||
                    s.Hand.contains(new Karte(hoechstekarteamTisch.getFarbe(),"Ass",11)))
            {
                return false;
            }
            //beim Land zählen keine Trumpf (alle restlichen Varianten mit Trumpf werden nicht mehr überprüft)
            else if(spiel.getSpiel() == "Land")
            {
                return true;
            }
            else if(karte.getFarbe().equals(trumpf))
            {
                return true;
            }
            else if(s.Hand.contains(new Karte(trumpf,"Bube",2)) || s.Hand.contains(new Karte(trumpf,"Dame",3)) ||
                    s.Hand.contains(new Karte(trumpf,"König",4)) || s.Hand.contains(new Karte(trumpf,"10er",10)) ||
                    s.Hand.contains(new Karte(trumpf,"Ass",11)))
            {
                return false;
            }
            else
                return true;
        }


    }

    public void ZugAuswerten(Karte karteS1, Karte karteS2, Karte karteS3)
    {
        //Spieler mit höherer Karte istdran = true; Karten werden in Gestochen von Gewinner gelegt; Punkte werden bei Gewinner dazugezählt;

        angesagteFarbe = null;

        if(besitzer == s1)
        {
            s1.Gestochen.add(karteS1);
            s1.Gestochen.add(karteS2);
            s1.Gestochen.add(karteS3);

            s1.setPunkte(s1.getPunkte()+karteS1.getPunkte()+karteS2.getPunkte()+karteS3.getPunkte());

            s1.setIstdran(true);
            s2.setIstdran(false);
            s3.setIstdran(false);
        }
        else if(besitzer == s2)
        {
            s2.Gestochen.add(karteS1);
            s2.Gestochen.add(karteS2);
            s2.Gestochen.add(karteS3);

            s2.setPunkte(s2.getPunkte()+karteS1.getPunkte()+karteS2.getPunkte()+karteS3.getPunkte());

            s1.setIstdran(false);
            s2.setIstdran(true);
            s3.setIstdran(false);
        }
        else
        {
            s3.Gestochen.add(karteS1);
            s3.Gestochen.add(karteS2);
            s3.Gestochen.add(karteS3);

            s3.setPunkte(s3.getPunkte()+karteS1.getPunkte()+karteS2.getPunkte()+karteS3.getPunkte());

            s1.setIstdran(false);
            s2.setIstdran(false);
            s3.setIstdran(true);
        }

        //s.isIstdran()=true --> Spieler hat den Stich gemacht, falls er oder sein Mitspieler einen 20er vor seinen ersten Stich angesagt hat
        // werden diese Punkte nachträglich hinzugezählt
        if((s2.isIstdran() || s2.getMitspieler().isIstdran()) && s2.isAngesagt20er())
        {
            s2.setPunkte(s2.getPunkte()+s2.getMerkePunkte());
            s2.setMerkePunkte(0);
            s2.setAngesagt20er(false);
        }
        else if((s1.isIstdran() || s1.getMitspieler().isIstdran()) && s1.isAngesagt20er())
        {
            s1.setPunkte(s1.getPunkte()+s1.getMerkePunkte());
            s1.setMerkePunkte(0);
            s1.setAngesagt20er(false);
        }
        else if((s3.isIstdran() || s3.getMitspieler().isIstdran()) && s3.isAngesagt20er())
        {
            s3.setPunkte(s3.getPunkte()+s3.getMerkePunkte());
            s3.setMerkePunkte(0);
            s3.setAngesagt20er(false);
        }

    }

    public boolean istSpielzuEnde(Bummerl3 bummerl) {
        if(spiel.getSpiel() == "normal") {
            if (s1 == spieler) {
                if (s1.getPunkte() >= 66) {
                    if (s2.getPunkte() + s3.getPunkte() >= 33)
                        bummerl.setPunkteS1(bummerl.getPunkteS1() + 1*flecken);
                    else if (s2.getPunkte() + s3.getPunkte() > 0)
                        bummerl.setPunkteS1(bummerl.getPunkteS1() + 2*flecken);
                    else
                        bummerl.setPunkteS1(bummerl.getPunkteS1() + 3*flecken);

                    sieger.add(s1);
                    bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                    return true;
                } else if (s2.getPunkte() + s3.getPunkte() >= 66) {
                    if (s1.getPunkte() >= 33) {
                        bummerl.setPunkteS2(bummerl.getPunkteS2() + 1*flecken);
                        bummerl.setPunkteS3(bummerl.getPunkteS3() + 1*flecken);
                    } else if (s1.getPunkte() > 0) {
                        bummerl.setPunkteS2(bummerl.getPunkteS2() + 2*flecken);
                        bummerl.setPunkteS3(bummerl.getPunkteS3() + 2*flecken);
                    } else {
                        bummerl.setPunkteS2(bummerl.getPunkteS2() + 3*flecken);
                        bummerl.setPunkteS3(bummerl.getPunkteS3() + 3*flecken);
                    }

                    sieger.add(s2);
                    sieger.add(s3);
                    bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                    return true;

                } else if (s1.Hand.isEmpty()) {
                    if (s1.isIstdran()) {
                        if (s2.getPunkte() + s3.getPunkte() >= 33)
                            bummerl.setPunkteS1(bummerl.getPunkteS1() + 1*flecken);
                        else if (s2.getPunkte() + s3.getPunkte() > 0)
                            bummerl.setPunkteS1(bummerl.getPunkteS1() + 2*flecken);
                        else
                            bummerl.setPunkteS1(bummerl.getPunkteS1() + 3*flecken);


                        sieger.add(s1);
                    }
                    else
                    {
                        if (s1.getPunkte() >= 33) {
                            bummerl.setPunkteS2(bummerl.getPunkteS2() + 1*flecken);
                            bummerl.setPunkteS3(bummerl.getPunkteS3() + 1*flecken);
                        } else if (s1.getPunkte() > 0) {
                            bummerl.setPunkteS2(bummerl.getPunkteS2() + 2*flecken);
                            bummerl.setPunkteS3(bummerl.getPunkteS3() + 2*flecken);
                        } else {
                            bummerl.setPunkteS2(bummerl.getPunkteS2() + 3*flecken);
                            bummerl.setPunkteS3(bummerl.getPunkteS3() + 3*flecken);
                        }

                        sieger.add(s2);
                        sieger.add(s3);
                    }
                    bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                    return true;
                }
                return false;
            }
            else if (s2 == spieler) {
                if (s2.getPunkte() >= 66) {
                    if (s1.getPunkte() + s3.getPunkte() >= 33)
                        bummerl.setPunkteS2(bummerl.getPunkteS2() + 1*flecken);
                    else if (s1.getPunkte() + s3.getPunkte() > 0)
                        bummerl.setPunkteS2(bummerl.getPunkteS2() + 2*flecken);
                    else
                        bummerl.setPunkteS2(bummerl.getPunkteS2() + 3*flecken);

                    sieger.add(s2);
                    bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                    return true;

                } else if (s1.getPunkte() + s3.getPunkte() >= 66) {
                    if (s2.getPunkte() >= 33) {
                        bummerl.setPunkteS1(bummerl.getPunkteS1() + 1*flecken);
                        bummerl.setPunkteS3(bummerl.getPunkteS3() + 1*flecken);
                    } else if (s2.getPunkte() > 0) {
                        bummerl.setPunkteS1(bummerl.getPunkteS1() + 2*flecken);
                        bummerl.setPunkteS3(bummerl.getPunkteS3() + 2*flecken);
                    } else {
                        bummerl.setPunkteS1(bummerl.getPunkteS1() + 3*flecken);
                        bummerl.setPunkteS3(bummerl.getPunkteS3() + 3*flecken);
                    }

                    sieger.add(s3);
                    sieger.add(s1);
                    bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                    return true;

                } else if (s1.Hand.isEmpty()) {
                    if (s1.isIstdran()) {
                        if (s1.getPunkte() + s3.getPunkte() >= 33)
                            bummerl.setPunkteS2(bummerl.getPunkteS2() + 1*flecken);
                        else if (s1.getPunkte() + s3.getPunkte() > 0)
                            bummerl.setPunkteS2(bummerl.getPunkteS2() + 2*flecken);
                        else
                            bummerl.setPunkteS2(bummerl.getPunkteS2() + 3*flecken);


                        sieger.add(s2);
                    }
                    else
                    {
                        if (s2.getPunkte() >= 33) {
                            bummerl.setPunkteS1(bummerl.getPunkteS1() + 1*flecken);
                            bummerl.setPunkteS3(bummerl.getPunkteS3() + 1*flecken);
                        } else if (s2.getPunkte() > 0) {
                            bummerl.setPunkteS1(bummerl.getPunkteS1() + 2*flecken);
                            bummerl.setPunkteS3(bummerl.getPunkteS3() + 2*flecken);
                        } else {
                            bummerl.setPunkteS1(bummerl.getPunkteS1() + 3*flecken);
                            bummerl.setPunkteS3(bummerl.getPunkteS3() + 3*flecken);
                        }

                        sieger.add(s1);
                        sieger.add(s3);
                    }

                    bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                    return true;
                }
                return false;

            }
            else {
                if (s3.getPunkte() >= 66) {
                    if (s1.getPunkte() + s2.getPunkte() >= 33)
                        bummerl.setPunkteS3(bummerl.getPunkteS3() + 1*flecken);
                    else if (s1.getPunkte() + s2.getPunkte() > 0)
                        bummerl.setPunkteS3(bummerl.getPunkteS3() + 2*flecken);
                    else
                        bummerl.setPunkteS3(bummerl.getPunkteS3() + 3*flecken);

                    sieger.add(s3);
                    bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                    return true;
                } else if (s1.getPunkte() + s2.getPunkte() >= 66) {
                    if (s3.getPunkte() >= 33) {
                        bummerl.setPunkteS1(bummerl.getPunkteS1() + 1*flecken);
                        bummerl.setPunkteS2(bummerl.getPunkteS2() + 1*flecken);
                    } else if (s3.getPunkte() > 0) {
                        bummerl.setPunkteS1(bummerl.getPunkteS1() + 2*flecken);
                        bummerl.setPunkteS2(bummerl.getPunkteS2() + 2*flecken);
                    } else {
                        bummerl.setPunkteS1(bummerl.getPunkteS1() + 3*flecken);
                        bummerl.setPunkteS2(bummerl.getPunkteS2() + 3*flecken);
                    }

                    sieger.add(s2);
                    sieger.add(s1);
                    bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                    return true;

                } else if (s1.Hand.isEmpty()) {
                    if (s1.isIstdran()) {
                        if (s1.getPunkte() + s2.getPunkte() >= 33)
                            bummerl.setPunkteS3(bummerl.getPunkteS3() + 1*flecken);
                        else if (s1.getPunkte() + s2.getPunkte() > 0)
                            bummerl.setPunkteS3(bummerl.getPunkteS3() + 2*flecken);
                        else
                            bummerl.setPunkteS3(bummerl.getPunkteS3() + 3*flecken);

                        sieger.add(s3);
                    }
                    else
                    {
                        if (s3.getPunkte() >= 33) {
                            bummerl.setPunkteS1(bummerl.getPunkteS1() + 1*flecken);
                            bummerl.setPunkteS2(bummerl.getPunkteS2() + 1*flecken);
                        } else if (s3.getPunkte() > 0) {
                            bummerl.setPunkteS1(bummerl.getPunkteS1() + 2*flecken);
                            bummerl.setPunkteS2(bummerl.getPunkteS2() + 2*flecken);
                        } else {
                            bummerl.setPunkteS1(bummerl.getPunkteS1() + 3*flecken);
                            bummerl.setPunkteS2(bummerl.getPunkteS2() + 3*flecken);
                        }

                        sieger.add(s2);
                        sieger.add(s1);
                    }

                    bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                    return true;
                }
                return false;
            }
        }
        else if(spiel.getSpiel() == "Schnapser" || spiel.getSpiel() == "Kontraschnapser"){
            if(s1 == spieler)
            {
                if(s2.isIstdran() || s3.isIstdran())
                {
                    bummerl.setPunkteS2(bummerl.getPunkteS2() + spiel.getPunkte()*flecken);
                    bummerl.setPunkteS3(bummerl.getPunkteS3() + spiel.getPunkte()*flecken);


                    sieger.add(s2);
                    sieger.add(s3);
                    bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                    return true;
                }
                else if(s1.Hand.size() == 3)
                {
                    if(s1.getPunkte() >= 66)
                    {
                        bummerl.setPunkteS1(bummerl.getPunkteS1() + spiel.getPunkte()*flecken);
                        sieger.add(s1);
                    }
                    else
                    {
                        bummerl.setPunkteS2(bummerl.getPunkteS2() + spiel.getPunkte()*flecken);
                        bummerl.setPunkteS3(bummerl.getPunkteS3() + spiel.getPunkte()*flecken);

                        sieger.add(s2);
                        sieger.add(s3);
                    }

                    bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                    return true;
                }

                return false;
            }
            else if(s2 == spieler)
            {
                if(s1.isIstdran() || s3.isIstdran())
                {
                    bummerl.setPunkteS1(bummerl.getPunkteS1() + spiel.getPunkte()*flecken);
                    bummerl.setPunkteS3(bummerl.getPunkteS3() + spiel.getPunkte()*flecken);

                    sieger.add(s1);
                    sieger.add(s3);

                    bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                    return true;
                }
                else if(s1.Hand.size() == 3)
                {
                    if(s2.getPunkte() >= 66)
                    {
                        bummerl.setPunkteS2(bummerl.getPunkteS2() + spiel.getPunkte()*flecken);

                        sieger.add(s2);
                    }
                    else
                    {
                        bummerl.setPunkteS1(bummerl.getPunkteS1() + spiel.getPunkte()*flecken);
                        bummerl.setPunkteS3(bummerl.getPunkteS3() + spiel.getPunkte()*flecken);

                        sieger.add(s1);
                        sieger.add(s3);
                    }

                    bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                    return true;
                }

                return false;
            }
            else
            {
                if(s1.isIstdran() || s2.isIstdran())
                {
                    bummerl.setPunkteS1(bummerl.getPunkteS1() + spiel.getPunkte()*flecken);
                    bummerl.setPunkteS2(bummerl.getPunkteS2() + spiel.getPunkte()*flecken);

                    sieger.add(s2);
                    sieger.add(s1);

                    bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                    return true;
                }
                else if(s1.Hand.size() == 3)
                {
                    if(s3.getPunkte() >= 66)
                    {
                        bummerl.setPunkteS3(bummerl.getPunkteS3() + spiel.getPunkte()*flecken);

                        sieger.add(s3);
                    }
                    else
                    {
                        bummerl.setPunkteS1(bummerl.getPunkteS1() + spiel.getPunkte()*flecken);
                        bummerl.setPunkteS2(bummerl.getPunkteS2() + spiel.getPunkte()*flecken);

                        sieger.add(s2);
                        sieger.add(s1);
                    }

                    bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                    return true;
                }

                return false;
            }
        }
        else if(spiel.getSpiel() == "Bauernschnapser" || spiel.getSpiel() == "Kontrabauernschnapser" || spiel.getSpiel() == "Land"){
            if(s1 == spieler)
            {
                if(s2.isIstdran() || s3.isIstdran())
                {
                    bummerl.setPunkteS2(bummerl.getPunkteS2() + spiel.getPunkte()*flecken);
                    bummerl.setPunkteS3(bummerl.getPunkteS3() + spiel.getPunkte()*flecken);

                    sieger.add(s2);
                    sieger.add(s3);

                    bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                    return true;
                }
                else if(s1.Hand.isEmpty())
                {
                    bummerl.setPunkteS1(bummerl.getPunkteS1() + spiel.getPunkte()*flecken);

                    sieger.add(s1);

                    bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                    return true;
                }

                return false;
            }
            else if(s2 == spieler)
            {
                if(s1.isIstdran() || s3.isIstdran())
                {
                    bummerl.setPunkteS1(bummerl.getPunkteS1() + spiel.getPunkte()*flecken);
                    bummerl.setPunkteS3(bummerl.getPunkteS3() + spiel.getPunkte()*flecken);

                    sieger.add(s1);
                    sieger.add(s3);

                    bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                    return true;
                }
                else if(s1.Hand.isEmpty())
                {
                    bummerl.setPunkteS2(bummerl.getPunkteS2() + spiel.getPunkte()*flecken);

                    sieger.add(s2);

                    bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                    return true;
                }

                return false;
            }
            else
            {
                if(s1.isIstdran() || s2.isIstdran())
                {
                    bummerl.setPunkteS1(bummerl.getPunkteS1() + spiel.getPunkte()*flecken);
                    bummerl.setPunkteS2(bummerl.getPunkteS2() + spiel.getPunkte()*flecken);

                    sieger.add(s2);
                    sieger.add(s1);

                    bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                    return true;
                }
                else if(s1.Hand.isEmpty())
                {
                    bummerl.setPunkteS3(bummerl.getPunkteS3() + spiel.getPunkte()*flecken);

                    sieger.add(s3);

                    bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                    return true;
                }

                return false;
            }
        }
        return false;

    }

    public boolean DarfSpielAnsagen(Rufspiel Spiel, Spieler s)
    {
        if(Spiel.getSpiel() == "Schnapser" || Spiel.getSpiel() == "Bauernschnapser")
        {
            if(s == spieler)
                return true;
            else
                return false;
        }
        else if(Spiel.getSpiel() == "Land" || Spiel.getSpiel() == "Kontraschnapser" || Spiel.getSpiel() == "Kontrabauernschnapser")
        {
            if(Spiel.getPunkte() > spiel.getPunkte())
                return true;
            else
                return false;
        }
        return false;
    }

    public void SpielAnsagen(Rufspiel Spiel, Spieler s)
    {
        spiel = Spiel;
        spieler = s;
        if(spiel.getSpiel().equals("Land"))
        {
            if(s1 == s)
            {
                s1.setIstdran(true);
                s2.setIstdran(false);
                s3.setIstdran(false);

                s1.setMitspieler(s1);
                s2.setMitspieler(s3);
                s3.setMitspieler(s2);
            }
            else if(s2 == s)
            {
                s1.setIstdran(false);
                s2.setIstdran(true);
                s3.setIstdran(false);

                s2.setMitspieler(s2);
                s3.setMitspieler(s1);
                s1.setMitspieler(s3);
            }
            else if(s3 == s)
            {
                s1.setIstdran(false);
                s2.setIstdran(false);
                s3.setIstdran(true);

                s3.setMitspieler(s3);
                s2.setMitspieler(s1);
                s1.setMitspieler(s2);
            }
        }
        else{
            if(s1 == s)
            {
                s1.setMitspieler(s1);
                s2.setMitspieler(s3);
                s3.setMitspieler(s2);
            }
            else if(s2 == s)
            {
                s2.setMitspieler(s2);
                s3.setMitspieler(s1);
                s1.setMitspieler(s3);
            }
            else if(s3 == s)
            {
                s3.setMitspieler(s3);
                s2.setMitspieler(s1);
                s1.setMitspieler(s2);
            }
        }
    }

    public void TalonAustauschen(Karte talon, Karte spielerkarte, Spieler s)
    {
        this.Talon.remove(talon);
        this.Talon.add(spielerkarte);
        s.Hand.remove(spielerkarte);
        s.Hand.add(talon);
    }

    public ArrayList<String> hat20er(Spieler s)
    {
        ArrayList<String> h20er = new ArrayList<String>(4);

        if(spiel.getSpiel() == "normal" || spiel.getSpiel() == "Schnapser" || spiel.getSpiel() == "Bauernschnapser") {
            if (s.Hand.contains(new Karte("Herz", "Dame", 3)) && s.Hand.contains(new Karte("Herz", "König", 4)))
                h20er.add("Herz");
            if (s.Hand.contains(new Karte("Pik", "Dame", 3)) && s.Hand.contains(new Karte("Pik", "König", 4)))
                h20er.add("Pik");
            if (s.Hand.contains(new Karte("Karo", "Dame", 3)) && s.Hand.contains(new Karte("Karo", "König", 4)))
                h20er.add("Karo");
            if (s.Hand.contains(new Karte("Kreuz", "Dame", 3)) && s.Hand.contains(new Karte("Kreuz", "König", 4)))
                h20er.add("Kreuz");
        }

        return h20er;
    }

    public void Ansagen20er(String farbe,Spieler s)
    {
        s.setAngesagt20er(true);
        angesagteFarbe = farbe;

        if(s.getPunkte()== 0)
        {
            if(farbe.equals(trumpf))
                s.setMerkePunkte(40);
            else
                s.setMerkePunkte(20);
        }
        else
        {
            if(farbe.equals(trumpf))
                s.setPunkte(s.getPunkte()+40);
            else
                s.setPunkte(s.getPunkte()+20);
        }

    }

    public boolean kannFlecken(Spieler s)
    {
        if(flecken == 1 && s != spieler)
            return true;
        else if(flecken == 2 && s == spieler)
            return true;
        return false;
    }

    public void Flecken()
    {
        flecken = flecken * 2;
    }






}
