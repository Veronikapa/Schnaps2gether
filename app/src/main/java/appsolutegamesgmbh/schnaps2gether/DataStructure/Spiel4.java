package appsolutegamesgmbh.schnaps2gether.DataStructure;


import java.util.ArrayList;
import java.util.Collections;

public class Spiel4 {
    private ArrayList<Karte> kartendeck;
    private ArrayList<Karte> stapel;
    private Spieler s1;
    private Spieler s2;
    private Spieler s3;
    private Spieler s4; // 2 Teams: (s1,s3) (s2,s4)
    private String trumpf;
    private String angesagteFarbe;
    private Karte hoechstekarteamTisch;
    private Spieler besitzer; //Besitzer der höchsten Karte, die am Tisch liegt
    private Rufspiel spiel;
    private Spieler spieler; //derjenige, der das Spiel macht
    private int flecken;

    public Spiel4(int AnzahlSpiele) throws WrongGameException
    {
        kartendeck = Karte.erstelleKartendeck();
        s1 = new Spieler();
        s2 = new Spieler();
        s3 = new Spieler();
        s4 = new Spieler();
        angesagteFarbe = null;

        if(AnzahlSpiele%4 == 0) {
            s1.setIstdran(true);
            spieler = s1;
        }
        else if(AnzahlSpiele%4 == 1) {
            s2.setIstdran(true);
            spieler = s2;
        }
        else if(AnzahlSpiele%4 == 2){
            s3.setIstdran(true);
            spieler = s3;
        }
        else
        {
            s4.setIstdran(true);
            spieler = s4;
        }

        spiel = new Rufspiel("normal");
        flecken = 1;

        s1.setMitspieler(s3);
        s2.setMitspieler(s4);
        s3.setMitspieler(s1);
        s4.setMitspieler(s2);

        Anfangsdeck(AnzahlSpiele);
    }

    private void Anfangsdeck(int AnzahlSpiele)
    {
        KartenMischen();

        if(AnzahlSpiele%4 == 0) {
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

            //Spieler 4 bekommt 3 Karten
            s4.Hand.add(stapel.get(0));
            stapel.remove(0);
            s4.Hand.add(stapel.get(0));
            stapel.remove(0);
            s4.Hand.add(stapel.get(0));
            stapel.remove(0);


        }
        else if(AnzahlSpiele%4 == 1)
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

            //Spieler 4 bekommt 3 Karten
            s4.Hand.add(stapel.get(0));
            stapel.remove(0);
            s4.Hand.add(stapel.get(0));
            stapel.remove(0);
            s4.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Spieler 1 bekommt 3 Karten
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);

        }
        else if(AnzahlSpiele%4 == 2)
        {
            //Spieler 3 bekommt 3 Karten
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Spieler 4 bekommt 3 Karten
            s4.Hand.add(stapel.get(0));
            stapel.remove(0);
            s4.Hand.add(stapel.get(0));
            stapel.remove(0);
            s4.Hand.add(stapel.get(0));
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

        } else {
            //Spieler 4 bekommt 3 Karten
            s4.Hand.add(stapel.get(0));
            stapel.remove(0);
            s4.Hand.add(stapel.get(0));
            stapel.remove(0);
            s4.Hand.add(stapel.get(0));
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

            //Spieler 3 bekommt 3 Karten
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);
            s3.Hand.add(stapel.get(0));
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

        if(AnzahlSpiele%4 == 0) {
            //Spieler 1 bekommt 2 Karten
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Spieler 2 bekommt 2 Karten
            s2.Hand.add(stapel.get(0));
            stapel.remove(0);
            s2.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Spieler 3 bekommt 2 Karten
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Spieler 4 bekommt 2 Karten
            s4.Hand.add(stapel.get(0));
            stapel.remove(0);
            s4.Hand.add(stapel.get(0));
            stapel.remove(0);

        }
        else if(AnzahlSpiele%4 == 1)
        {
            //Spieler 2 bekommt 2 Karten
            s2.Hand.add(stapel.get(0));
            stapel.remove(0);
            s2.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Spieler 3 bekommt 2 Karten
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Spieler 4 bekommt 2 Karten
            s4.Hand.add(stapel.get(0));
            stapel.remove(0);
            s4.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Spieler 1 bekommt 2 Karten
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);
        }
        else if (AnzahlSpiele % 4 == 2)
        {
            //Spieler 3 bekommt 2 Karten
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Spieler 4 bekommt 2 Karten
            s4.Hand.add(stapel.get(0));
            stapel.remove(0);
            s4.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Spieler 1 bekommt 2 Karten
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Spieler 2 bekommt 2 Karten
            s2.Hand.add(stapel.get(0));
            stapel.remove(0);
            s2.Hand.add(stapel.get(0));
            stapel.remove(0);
        } else {
            //Spieler 4 bekommt 2 Karten
            s4.Hand.add(stapel.get(0));
            stapel.remove(0);
            s4.Hand.add(stapel.get(0));
            stapel.remove(0);
            
            //Spieler 1 bekommt 2 Karten
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Spieler 2 bekommt 2 Karten
            s2.Hand.add(stapel.get(0));
            stapel.remove(0);
            s2.Hand.add(stapel.get(0));
            stapel.remove(0);

            //Spieler 3 bekommt 2 Karten
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);
            s3.Hand.add(stapel.get(0));
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
        if(hoechstekarteamTisch == null)
            hoechstekarteamTisch = karte;
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

    public void ZugAuswerten(Karte karteS1, Karte karteS2, Karte karteS3, Karte karteS4)
    {
        //Spieler mit höchster Karte istdran = true; Karten werden in Gestochen von Gewinner gelegt; Punkte werden bei Gewinner dazugezählt;

        angesagteFarbe = null;

        if(besitzer == s1)
        {
            s1.Gestochen.add(karteS1);
            s1.Gestochen.add(karteS2);
            s1.Gestochen.add(karteS3);
            s1.Gestochen.add(karteS4);

            s1.setPunkte(s1.getPunkte()+karteS1.getPunkte()+karteS2.getPunkte()+karteS3.getPunkte()+karteS4.getPunkte());

            s1.setIstdran(true);
            s2.setIstdran(false);
            s3.setIstdran(false);
            s4.setIstdran(false);
        }
        else if(besitzer == s2)
        {
            s2.Gestochen.add(karteS1);
            s2.Gestochen.add(karteS2);
            s2.Gestochen.add(karteS3);
            s2.Gestochen.add(karteS4);

            s2.setPunkte(s2.getPunkte()+karteS1.getPunkte()+karteS2.getPunkte()+karteS3.getPunkte()+karteS4.getPunkte());

            s1.setIstdran(false);
            s2.setIstdran(true);
            s3.setIstdran(false);
            s4.setIstdran(false);
        }
        else if(besitzer == s3)
        {
            s3.Gestochen.add(karteS1);
            s3.Gestochen.add(karteS2);
            s3.Gestochen.add(karteS3);
            s3.Gestochen.add(karteS4);

            s3.setPunkte(s3.getPunkte()+karteS1.getPunkte()+karteS2.getPunkte()+karteS3.getPunkte()+karteS4.getPunkte());

            s1.setIstdran(false);
            s2.setIstdran(false);
            s3.setIstdran(true);
            s4.setIstdran(false);
        }
        else
        {
            s3.Gestochen.add(karteS1);
            s3.Gestochen.add(karteS2);
            s3.Gestochen.add(karteS3);
            s3.Gestochen.add(karteS4);

            s3.setPunkte(s3.getPunkte()+karteS1.getPunkte()+karteS2.getPunkte()+karteS3.getPunkte()+karteS4.getPunkte());

            s1.setIstdran(false);
            s2.setIstdran(false);
            s3.setIstdran(false);
            s4.setIstdran(true);
        }


        //s.isIstdran()=true --> Spieler hat den Stich gemacht, falls er oder sein Mitspieler einen 20er vor seinen ersten Stich angesagt hat
        // werden diese Punkte nachträglich hinzugezählt (auch wenn Mitspieler noch keinen Stich hat, werden Punkte trotzdem beim Mitspieler hinzugefügt)
        if((s2.isIstdran() || s4.isIstdran()) && (s2.isAngesagt20er() || s4.isAngesagt20er()) )
        {
            if(s2.isAngesagt20er()) {
                s2.setPunkte(s2.getPunkte() + s2.getMerkePunkte());
                s2.setMerkePunkte(0);
                s2.setAngesagt20er(false);
            }
            else{
                s4.setPunkte(s4.getPunkte()+s4.getMerkePunkte());
                s4.setMerkePunkte(0);
                s4.setAngesagt20er(false);
            }
        }
        else if((s1.isIstdran() || s3.isIstdran())&& (s1.isAngesagt20er() || s3.isAngesagt20er()))
        {
            if(s1.isAngesagt20er()) {
                s1.setPunkte(s1.getPunkte() + s1.getMerkePunkte());
                s1.setMerkePunkte(0);
                s1.setAngesagt20er(false);
            }
            else{
                s3.setPunkte(s3.getPunkte() + s3.getMerkePunkte());
                s3.setMerkePunkte(0);
                s3.setAngesagt20er(false);
            }
        }
    }

    //muss auch vor dem Ausspielen der ersten Karte überprüft werden, da bei einem Herren- oder Farbenjodler das Spiel sofort beendet ist
    public boolean istSpielzuEnde(Bummerl4 bummerl) {
        if(s1.Hand.size() == 5)
        {
            if(spiel.getSpiel() == "Herrenjodler" || spiel.getSpiel() == "Farbenjodler")
            {
                if(spieler == s1 || spieler == s3) {
                    bummerl.setPunkteS1(bummerl.getPunkteS1() + spiel.getPunkte());
                    bummerl.setPunkteS3(bummerl.getPunkteS3() + spiel.getPunkte());
                }
                else
                {
                    bummerl.setPunkteS2(bummerl.getPunkteS2() + spiel.getPunkte());
                    bummerl.setPunkteS4(bummerl.getPunkteS4() + spiel.getPunkte());
                }

                bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele() + 1);
                return true;
            }

            return false;
        }
        else {
            if (spiel.getSpiel() == "normal") {
                if (s1.getPunkte() + s3.getPunkte() >= 66) {
                    if (s2.getPunkte() + s4.getPunkte() >= 33) {
                        bummerl.setPunkteS1(bummerl.getPunkteS1() + 1 * flecken);
                        bummerl.setPunkteS3(bummerl.getPunkteS3() + 1 * flecken);
                    } else if (s2.getPunkte() + s4.getPunkte() > 0) {
                        bummerl.setPunkteS1(bummerl.getPunkteS1() + 2 * flecken);
                        bummerl.setPunkteS3(bummerl.getPunkteS3() + 2 * flecken);
                    } else {
                        bummerl.setPunkteS1(bummerl.getPunkteS1() + 3 * flecken);
                        bummerl.setPunkteS3(bummerl.getPunkteS3() + 3 * flecken);
                    }
                    return true;
                } else if (s2.getPunkte() + s4.getPunkte() >= 66) {
                    if (s1.getPunkte() + s3.getPunkte() >= 33) {
                        bummerl.setPunkteS2(bummerl.getPunkteS2() + 1 * flecken);
                        bummerl.setPunkteS4(bummerl.getPunkteS4() + 1 * flecken);
                    } else if (s1.getPunkte() + s3.getPunkte() > 0) {
                        bummerl.setPunkteS2(bummerl.getPunkteS2() + 2 * flecken);
                        bummerl.setPunkteS4(bummerl.getPunkteS4() + 2 * flecken);
                    } else {
                        bummerl.setPunkteS2(bummerl.getPunkteS2() + 3 * flecken);
                        bummerl.setPunkteS4(bummerl.getPunkteS4() + 3 * flecken);
                    }

                    bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                    return true;

                } else if (s1.Hand.isEmpty()) {
                    if (s1.isIstdran() || s3.isIstdran()) {
                        if (s2.getPunkte() + s4.getPunkte() >= 33) {
                            bummerl.setPunkteS1(bummerl.getPunkteS1() + 1 * flecken);
                            bummerl.setPunkteS3(bummerl.getPunkteS3() + 1 * flecken);
                        } else if (s2.getPunkte() + s4.getPunkte() > 0) {
                            bummerl.setPunkteS1(bummerl.getPunkteS1() + 2 * flecken);
                            bummerl.setPunkteS3(bummerl.getPunkteS3() + 2 * flecken);
                        } else {
                            bummerl.setPunkteS1(bummerl.getPunkteS1() + 3 * flecken);
                            bummerl.setPunkteS3(bummerl.getPunkteS3() + 3 * flecken);
                        }

                        bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                        return true;
                    } else if (s2.isIstdran() || s4.isIstdran()) {
                        if (s1.getPunkte() + s3.getPunkte() >= 33) {
                            bummerl.setPunkteS2(bummerl.getPunkteS2() + 1 * flecken);
                            bummerl.setPunkteS4(bummerl.getPunkteS4() + 1 * flecken);
                        } else if (s1.getPunkte() + s3.getPunkte() > 0) {
                            bummerl.setPunkteS2(bummerl.getPunkteS2() + 2 * flecken);
                            bummerl.setPunkteS4(bummerl.getPunkteS4() + 2 * flecken);
                        } else {
                            bummerl.setPunkteS2(bummerl.getPunkteS2() + 3 * flecken);
                            bummerl.setPunkteS4(bummerl.getPunkteS4() + 3 * flecken);
                        }
                        bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                        return true;
                    }
                    return false;
                }

            } else if (spiel.getSpiel() == "Schnapser" || spiel.getSpiel() == "Kontraschnapser") {
                if (s1 == spieler || s3 == spieler) {
                    if (s2.isIstdran() || s4.isIstdran() || spieler.getMitspieler().isIstdran()) {
                        bummerl.setPunkteS2(bummerl.getPunkteS2() + spiel.getPunkte());
                        bummerl.setPunkteS4(bummerl.getPunkteS4() + spiel.getPunkte());

                        bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                        return true;
                    } else if (s1.Hand.size() == 3) {
                        if (s1.getPunkte() + s3.getPunkte() >= 66) {
                            bummerl.setPunkteS1(bummerl.getPunkteS1() + spiel.getPunkte());
                            bummerl.setPunkteS3(bummerl.getPunkteS3() + spiel.getPunkte());
                        } else {
                            bummerl.setPunkteS2(bummerl.getPunkteS2() + spiel.getPunkte());
                            bummerl.setPunkteS4(bummerl.getPunkteS4() + spiel.getPunkte());
                        }
                        bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                        return true;
                    }

                    return false;
                } else if (s2 == spieler || s4 == spieler) {
                    if (s1.isIstdran() || s3.isIstdran() || spieler.getMitspieler().isIstdran()) {
                        bummerl.setPunkteS1(bummerl.getPunkteS1() + spiel.getPunkte());
                        bummerl.setPunkteS3(bummerl.getPunkteS3() + spiel.getPunkte());

                        bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                        return true;
                    } else if (s1.Hand.size() == 3) {
                        if (s2.getPunkte() + s4.getPunkte() >= 66) {
                            bummerl.setPunkteS2(bummerl.getPunkteS2() + spiel.getPunkte());
                            bummerl.setPunkteS4(bummerl.getPunkteS4() + spiel.getPunkte());
                        } else {
                            bummerl.setPunkteS1(bummerl.getPunkteS1() + spiel.getPunkte());
                            bummerl.setPunkteS3(bummerl.getPunkteS3() + spiel.getPunkte());
                        }
                        bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                        return true;
                    }

                    return false;
                }
            } else if (spiel.getSpiel() == "Bauernschnapser" || spiel.getSpiel() == "Kontrabauernschnapser" || spiel.getSpiel() == "Land") {
                if (s1 == spieler || s3 == spieler) {
                    if (s2.isIstdran() || s4.isIstdran() || spieler.getMitspieler().isIstdran()) {
                        bummerl.setPunkteS2(bummerl.getPunkteS2() + spiel.getPunkte());
                        bummerl.setPunkteS4(bummerl.getPunkteS4() + spiel.getPunkte());

                        bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                        return true;
                    } else if (s1.Hand.isEmpty()) {
                        bummerl.setPunkteS1(bummerl.getPunkteS1() + spiel.getPunkte());
                        bummerl.setPunkteS3(bummerl.getPunkteS3() + spiel.getPunkte());

                        bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                        return true;
                    }

                    return false;
                } else if (s2 == spieler || s4 == spieler) {
                    if (s1.isIstdran() || s3.isIstdran() || spieler.getMitspieler().isIstdran()) {
                        bummerl.setPunkteS1(bummerl.getPunkteS1() + spiel.getPunkte());
                        bummerl.setPunkteS3(bummerl.getPunkteS3() + spiel.getPunkte());

                        bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                        return true;
                    } else if (s1.Hand.isEmpty()) {
                        bummerl.setPunkteS2(bummerl.getPunkteS2() + spiel.getPunkte());
                        bummerl.setPunkteS4(bummerl.getPunkteS4() + spiel.getPunkte());

                        bummerl.setAnzahlSpiele(bummerl.getAnzahlSpiele()+1);
                        return true;
                    }

                    return false;
                }
            }
            return false;
        }

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
            if(Spiel.getPunkte() > spiel.getPunkte() && !(s==spieler))
                return true;
            else
                return false;
        }
        else if(Spiel.getSpiel() == "Farbenjodler")
        {
            ArrayList<String> f = new ArrayList<String>(4);
            f.add("Herz");
            f.add("Pik");
            f.add("Karo");
            f.add("Kreuz");
            f.remove(trumpf);

            if(s.Hand.get(0).getFarbe() == f.get(0) && s.Hand.get(1).getFarbe() == f.get(0) && s.Hand.get(2).getFarbe() == f.get(0) && s.Hand.get(3).getFarbe() == f.get(0) && s.Hand.get(4).getFarbe() == f.get(0))
                return true;
            if(s.Hand.get(0).getFarbe() == f.get(1) && s.Hand.get(1).getFarbe() == f.get(1) && s.Hand.get(2).getFarbe() == f.get(1) && s.Hand.get(3).getFarbe() == f.get(1) && s.Hand.get(4).getFarbe() == f.get(1))
                return true;
            if(s.Hand.get(0).getFarbe() == f.get(2) && s.Hand.get(1).getFarbe() == f.get(2) && s.Hand.get(2).getFarbe() == f.get(2) && s.Hand.get(3).getFarbe() == f.get(2) && s.Hand.get(4).getFarbe() == f.get(2))
                return true;
            else
                return false;

        }
        else{
            if(s.Hand.get(0).getFarbe() == trumpf && s.Hand.get(1).getFarbe() == trumpf && s.Hand.get(2).getFarbe() == trumpf && s.Hand.get(3).getFarbe() == trumpf && s.Hand.get(4).getFarbe() == trumpf)
                return true;
            else
                return false;

        }

    }

    public void SpielAnsagen(Rufspiel Spiel, Spieler s)
    {
        spiel = Spiel;
        spieler = s;
        if(spiel.getSpiel() == "Land" || spiel.getSpiel() == "Farbenjodler" || spiel.getSpiel() == "Herrenjodler")
        {
            if(s1 == s)
            {
                s1.setIstdran(true);
                s2.setIstdran(false);
                s3.setIstdran(false);
            }
            else if(s2 == s)
            {
                s1.setIstdran(false);
                s2.setIstdran(true);
                s3.setIstdran(false);
            }
            else if(s3 == s)
            {
                s1.setIstdran(false);
                s2.setIstdran(false);
                s3.setIstdran(true);
            }
        }
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
                s.setPunkte(s.getPunkte() + 20);
        }

    }

    public boolean kannFlecken(Spieler s)
    {
        if(flecken == 1 && (s != spieler || s.getMitspieler() != spieler))
            return true;
        else if(flecken == 2 && (s == spieler || s.getMitspieler() == spieler))
            return true;
        return false;
    }

    public void Flecken()
    {
        flecken = flecken * 2;
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

    public Spieler getS4() {
        return s4;
    }

    public String getAngesagteFarbe() {
        return angesagteFarbe;
    }

    public boolean isGefleckt() {
        return flecken == 2;
    }

    public Rufspiel getSpiel() {
        return spiel;
    }
}
