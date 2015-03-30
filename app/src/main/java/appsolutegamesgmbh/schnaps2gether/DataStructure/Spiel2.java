package appsolutegamesgmbh.schnaps2gether.DataStructure;

import java.util.ArrayList;
import java.util.Collections;

public class Spiel2 {

    private ArrayList<Karte> kartendeck;
    private ArrayList<Karte> stapel;
    private Spieler s1;
    private Spieler s2;
    private String trumpf;
    private Karte aufgedeckteTrumpf;
    private boolean zugedreht;

    public Spiel2()
    {
        kartendeck = Karte.erstelleKartendeck();
        s1 = new Spieler();
        s1.setIstdran(true);
        zugedreht = false;
        s2 = new Spieler();
        Anfangsdeck();
    }

    private void Anfangsdeck()
    {
        KartenMischen();

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

        //Trumpfkarte ausgeben
        aufgedeckteTrumpf = stapel.get(0);
        trumpf = aufgedeckteTrumpf.getFarbe();
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


        //Hand Spieler 1 sortieren
        Collections.sort(s1.Hand,new KartenKomparator());

        //Hand Spieler 2 sortieren
        Collections.sort(s2.Hand,new KartenKomparator());

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

    public void Auspielen(Karte karteS1)
    {
        //TODO: V
        //TODO: S1 klickt im Spielfeld2 auf Karte; Karte aus Hand S1 löschen; Zudrehen berücksichtigen (bzw. Methode DarfKarteAuswaehlen verwenden)
    }

    public void AuspielenComputer(Karte karteS1)
    {
        //TODO: V
        //TODO: wenn S1 Karte ausgespielt hat (karteS1 != null), basierend auf Karte, Karte ausspielen sonst beliebige Karte ausspielen; Zudrehen berücksichtigen (bzw. Methode DarfKarteAuswaehlen verwenden)

        //TODO: istdrann = true, eventuell zudrehen

        //TODO: karteS1 == null, setze am Ende Spieler 1 ist dran
    }

    public void ZugAuswerten(Karte karteS1, Karte karteS2)
    {
        //Spieler mit höherer Karte istdran = true; Karten werden in Gestochen von Gewinner gelegt; Punkte werden bei Gewinner dazugezählt;

        if(s1.isIstdran() == true)
        {
            if (karteS1.getFarbe() == trumpf && karteS2.getFarbe() == trumpf)
            {
                if(karteS1.getPunkte()>karteS2.getPunkte())
                {
                    s1.Gestochen.add(karteS1);
                    s1.Gestochen.add(karteS2);

                    s1.setPunkte(s1.getPunkte()+karteS1.getPunkte()+karteS2.getPunkte());

                    s1.setIstdran(true);
                    s2.setIstdran(false);

                }
                else
                {
                    s2.Gestochen.add(karteS1);
                    s2.Gestochen.add(karteS2);

                    s2.setPunkte(s2.getPunkte()+karteS1.getPunkte()+karteS2.getPunkte());

                    s2.setIstdran(true);
                    s1.setIstdran(false);

                }
            }
            else if(karteS1.getFarbe() != trumpf && karteS2.getFarbe() != trumpf)
            {
                if(karteS1.getFarbe().equals(karteS2.getFarbe())) {
                    if (karteS1.getPunkte() > karteS2.getPunkte()) {
                        s1.Gestochen.add(karteS1);
                        s1.Gestochen.add(karteS2);

                        s1.setPunkte(s1.getPunkte() + karteS1.getPunkte() + karteS2.getPunkte());

                        s1.setIstdran(true);
                        s2.setIstdran(false);

                    } else {
                        s2.Gestochen.add(karteS1);
                        s2.Gestochen.add(karteS2);

                        s2.setPunkte(s2.getPunkte() + karteS1.getPunkte() + karteS2.getPunkte());

                        s2.setIstdran(true);
                        s1.setIstdran(false);

                    }
                }
                else
                {
                    s1.Gestochen.add(karteS1);
                    s1.Gestochen.add(karteS2);

                    s1.setPunkte(s1.getPunkte() + karteS1.getPunkte() + karteS2.getPunkte());

                    s1.setIstdran(true);
                    s2.setIstdran(false);
                }
            }
            else
            {
                if(karteS1.getFarbe() == trumpf)
                {
                    s1.Gestochen.add(karteS1);
                    s1.Gestochen.add(karteS2);

                    s1.setPunkte(s1.getPunkte()+karteS1.getPunkte()+karteS2.getPunkte());

                    s1.setIstdran(true);
                    s2.setIstdran(false);

                }
                else
                {
                    s2.Gestochen.add(karteS1);
                    s2.Gestochen.add(karteS2);

                    s2.setPunkte(s2.getPunkte()+karteS1.getPunkte()+karteS2.getPunkte());

                    s2.setIstdran(true);
                    s1.setIstdran(false);

                }
            }
        }
        else
        {
            if (karteS1.getFarbe() == trumpf && karteS2.getFarbe() == trumpf)
            {
                if(karteS1.getPunkte()>karteS2.getPunkte())
                {
                    s1.Gestochen.add(karteS1);
                    s1.Gestochen.add(karteS2);

                    s1.setPunkte(s1.getPunkte()+karteS1.getPunkte()+karteS2.getPunkte());

                    s1.setIstdran(true);
                    s2.setIstdran(false);

                }
                else
                {
                    s2.Gestochen.add(karteS1);
                    s2.Gestochen.add(karteS2);

                    s2.setPunkte(s2.getPunkte()+karteS1.getPunkte()+karteS2.getPunkte());

                    s2.setIstdran(true);
                    s1.setIstdran(false);

                }
            }
            else if(karteS1.getFarbe() != trumpf && karteS2.getFarbe() != trumpf)
            {
                if(karteS1.getFarbe().equals(karteS2.getFarbe())) {
                    if (karteS1.getPunkte() > karteS2.getPunkte()) {
                        s1.Gestochen.add(karteS1);
                        s1.Gestochen.add(karteS2);

                        s1.setPunkte(s1.getPunkte() + karteS1.getPunkte() + karteS2.getPunkte());

                        s1.setIstdran(true);
                        s2.setIstdran(false);

                    } else {
                        s2.Gestochen.add(karteS1);
                        s2.Gestochen.add(karteS2);

                        s2.setPunkte(s2.getPunkte() + karteS1.getPunkte() + karteS2.getPunkte());

                        s2.setIstdran(true);
                        s1.setIstdran(false);

                    }
                }
                else
                {
                    s2.Gestochen.add(karteS1);
                    s2.Gestochen.add(karteS2);

                    s2.setPunkte(s2.getPunkte() + karteS1.getPunkte() + karteS2.getPunkte());

                    s2.setIstdran(true);
                    s1.setIstdran(false);
                }
            }
            else
            {
                if(karteS1.getFarbe() == trumpf)
                {
                    s1.Gestochen.add(karteS1);
                    s1.Gestochen.add(karteS2);

                    s1.setPunkte(s1.getPunkte()+karteS1.getPunkte()+karteS2.getPunkte());

                    s1.setIstdran(true);
                    s2.setIstdran(false);

                }
                else
                {
                    s2.Gestochen.add(karteS1);
                    s2.Gestochen.add(karteS2);

                    s2.setPunkte(s2.getPunkte()+karteS1.getPunkte()+karteS2.getPunkte());

                    s2.setIstdran(true);
                    s1.setIstdran(false);

                }
            }
        }
    }

    //Gibt aus ob Spiel zu Ende ist, wenn nicht werden neue Karten ausgeteilt(falls Stapel nicht leer), wenn ja ändern der Punkte im Bummerl
    public boolean istSpielzuEnde(Bummerl2 bummerl)
    {
        if(s1.getPunkte() >= 66)
        {
           if(s2.getPunkte()>= 33)
               bummerl.setPunkteS1(bummerl.getPunkteS1() + 1);
           else if(s2.getPunkte() > 0)
               bummerl.setPunkteS1(bummerl.getPunkteS1() + 2);
           else
               bummerl.setPunkteS1(bummerl.getPunkteS1() + 3);

            return true;
        }
        else if(s2.getPunkte() >= 66)
        {
            if(s1.getPunkte()>= 33)
                bummerl.setPunkteS2(bummerl.getPunkteS2() + 1);
            else if(s1.getPunkte() > 0)
                bummerl.setPunkteS2(bummerl.getPunkteS2() + 2);
            else
                bummerl.setPunkteS2(bummerl.getPunkteS2() + 3);

            return true;

        }
        else
        {
            if(!zugedreht)
            {
                if(s1.isIstdran())
                {
                    s1.Hand.add(stapel.get(0));
                    stapel.remove(0);
                    s2.Hand.add(stapel.get(0));
                    stapel.remove(0);

                    if(stapel.isEmpty())
                        zugedreht = true;

                    //Hand Spieler 1 sortieren
                    Collections.sort(s1.Hand,new KartenKomparator());

                    //Hand Spieler 2 sortieren
                    Collections.sort(s2.Hand,new KartenKomparator());
                }
                else
                {
                    s2.Hand.add(stapel.get(0));
                    stapel.remove(0);
                    s1.Hand.add(stapel.get(0));
                    stapel.remove(0);

                    if(stapel.isEmpty())
                        zugedreht = true;

                    //Hand Spieler 1 sortieren
                    Collections.sort(s1.Hand,new KartenKomparator());

                    //Hand Spieler 2 sortieren
                    Collections.sort(s2.Hand,new KartenKomparator());
                }
            }
            return false;
        }
    }


    public void Zudrehen()
    {
        zugedreht = true;
    }

    public Karte TrumpfkarteAustauschen(Karte karte)
    {
        if(karte.getFarbe() == trumpf && karte.getWertigkeit() == "Bube")
        {
            Karte Rueckgabekarte = aufgedeckteTrumpf;
            aufgedeckteTrumpf = karte;
            return Rueckgabekarte;
        }
        else
        {
            return karte;
        }
    }

    public boolean DarfKarteAuswaehlen(Karte karteamTisch, Karte karte)
    {
        if(karteamTisch == null || !zugedreht)
            return true;

        else
        {
            if(karteamTisch.getFarbe().equals(karte.getFarbe()))
                return true;
            else if(s1.Hand.contains(new Karte(karteamTisch.getFarbe(),"Bube",2)) || s1.Hand.contains(new Karte(karteamTisch.getFarbe(),"Dame",3)) ||
                s1.Hand.contains(new Karte(karteamTisch.getFarbe(),"König",4)) || s1.Hand.contains(new Karte(karteamTisch.getFarbe(),"10er",10)) ||
                s1.Hand.contains(new Karte(karteamTisch.getFarbe(),"Ass",11)))
            {
                return false;
            }
            else if(karte.getFarbe().equals(trumpf))
            {
                return true;
            }
            else if(s1.Hand.contains(new Karte(trumpf,"Bube",2)) || s1.Hand.contains(new Karte(trumpf,"Dame",3)) ||
                    s1.Hand.contains(new Karte(trumpf,"König",4)) || s1.Hand.contains(new Karte(trumpf,"10er",10)) ||
                    s1.Hand.contains(new Karte(trumpf,"Ass",11)))
            {
                return false;
            }
            else
                return true;
        }


    }


}
