package appsolutegamesgmbh.schnaps2gether;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spieler;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.KartenKomparator;

import java.util.ArrayList;
import java.util.Collections;

public class Spiel2random {

    private ArrayList<Karte> kartendeck;
    private ArrayList<Karte> stapel;
    private Spieler s1;
    private Spieler s2;
    private String trumpf;
    private Karte aufgedeckterTrumpf;
    private boolean zugedreht;
    private String angesagteFarbe;

    public Spieler getS1() {
        return s1;
    }

    public Spieler getS2() {
        return s2;
    }

    public boolean isZugedreht() {
        return zugedreht;
    }

    public String getTrumpf() {
        return trumpf;
    }

    public Karte getAufgedeckterTrumpf() {
        return aufgedeckterTrumpf;
    }

    public Spiel2random()
    {
        kartendeck = Karte.erstelleKartendeck();
        s1 = new Spieler();
        s1.setIstdran(true);
        zugedreht = false;
        s2 = new Spieler();
        angesagteFarbe = null;
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
        aufgedeckterTrumpf = stapel.get(0);
        trumpf = aufgedeckterTrumpf.getFarbe();
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
        //Ausgespielte Karte wird aus Hand von S1 entfernt
        s1.Hand.remove(karteS1);
        s1.setIstdran(false);
    }

    /*
    / Enstcheidet welche Karte der Computer ausspielt.
    / Wenn s1 bereits ausgespielt hat, entscheide basierend auf dieser Karte
    / @return: Liefert Karte die der Computer ausspielt
     */
    public Karte AuspielenComputer(Karte karteAndererSpieler) {

        Karte zuspielen = null;
        zuspielen = findeAuszuspielendeKarte(karteAndererSpieler);

        s2.Hand.remove(zuspielen);

        s1.setIstdran(true);
        return zuspielen;
    }

    public Karte findeAuszuspielendeKarte(Karte karteS1) {
        Karte zuspielen;//Spieler1 hat bereits Karte ausgespielt, entscheide basierend auf dieser Karte
        if (karteS1 != null) {

            if(zugedreht)
                zuspielen =  SpieleComputerKarteWennS1Zugedreht(karteS1);

            else
                zuspielen = SpieleComputerKarte(karteS1);
        }

        //Spiele die zur Zeit am sinnvollsten Karte aus
        else {

            if(!zugedreht && sollComputerZudrehen())
                Zudrehen();

            if(zugedreht)
            {
                //Spiele höchsten Trumpf der noch offen ist, wenn ich ihn besitze
                zuspielen = spieleHoechsteAusstehendeKarteEinerFarbe(aufgedeckterTrumpf.getFarbe());

                //Spiele Farbe wo ich die höchste noch ausstehende Farbe habe
                zuspielen = spieleHoechsteAusstehendeKarte();

                //Sonst spiele höchste Karte die ich besitze
                zuspielen =  hoechsteKarteSpieler();
            }

            else
            {
                //Spiele Farbe wo ich die höchste noch ausstehende Farbe habe
                zuspielen = spieleHoechsteAusstehendeKarte();

                //Sonst spiele höchste Karte die ich besitze
                zuspielen = hoechsteKarteSpieler();
            }
        }
        return zuspielen;
    }

    /*
    / Gibt die höchste Karte zurück die ein Spieler besitzt
     */
    private Karte hoechsteKarteSpieler() {
        Karte hoechsteKarte = null;

        for(int i=0; i<s2.Hand.size(); i++)
        {
            if(hoechsteKarte == null)
                hoechsteKarte = s2.Hand.get(i);

            else if(hoechsteKarte.getPunkte() > s2.Hand.get(i).getPunkte())
                hoechsteKarte = s2.Hand.get(i);
        }
        return hoechsteKarte;
    }

    /*
    / Finde hoechste Karte des Computers üer alle Farben hinweg
     */
    private Karte spieleHoechsteAusstehendeKarte() {

        Karte hoechsteKarteHerz = null;
        Karte hoechsteKarteKaro = null;
        Karte hoechsteKarteKreuz = null;
        Karte hoechsteKartePik = null;
        Karte hoechsteKarte = null;

        hoechsteKarteHerz = spieleHoechsteAusstehendeKarteEinerFarbe("Herz");
        hoechsteKarteKaro = spieleHoechsteAusstehendeKarteEinerFarbe("Karo");
        hoechsteKarteKreuz = spieleHoechsteAusstehendeKarteEinerFarbe("Kreuz");
        hoechsteKartePik = spieleHoechsteAusstehendeKarteEinerFarbe("Pik");

        /*if(hoechsteKarteHerz.getPunkte() >= hoechsteKarteKaro.getPunkte())
            hoechsteKarte = hoechsteKarteHerz;

        else
            hoechsteKarte = hoechsteKarteKaro;*/
        if (hoechsteKarteHerz != null)
            hoechsteKarte = hoechsteKarteHerz;
        else if (hoechsteKarteKaro != null)
            hoechsteKarte = hoechsteKarteKaro;
        else if (hoechsteKarteKreuz != null)
            hoechsteKarte = hoechsteKarteKreuz;
        else if (hoechsteKartePik != null)
            hoechsteKarte = hoechsteKartePik;
        else

        if(hoechsteKarteKaro != null && hoechsteKarteKaro.getPunkte() >= hoechsteKarte.getPunkte())
            hoechsteKarte = hoechsteKarteKaro;

        if(hoechsteKarteKreuz != null && hoechsteKarteKreuz.getPunkte() >= hoechsteKarte.getPunkte())
            hoechsteKarte = hoechsteKarteKreuz;

        if(hoechsteKartePik != null && hoechsteKartePik.getPunkte() >= hoechsteKarte.getPunkte())
            hoechsteKarte = hoechsteKartePik;

        return hoechsteKarte;
    }

    /*
    / Wenn der Computer den höchste, noch ausstehenden Karte einer Farbe besitzt wird diese zurückgegeben,
    / sonst wird null zurückgegeben.
     */
    private Karte spieleHoechsteAusstehendeKarteEinerFarbe(String farbe) {
        Karte hoechsteKarteFarbe = null;

        boolean farbeAssGestochen = false;
        boolean farbe10erGestochen = false;
        boolean farbeKoenigGestochen = false;
        boolean farbeDameGestochen = false;
        boolean farbeBubGestochen = false;

        boolean habeFarbeAss = false;
        boolean habeFarbe10er = false;
        boolean habeFarbeKoenig = false;
        boolean habeFarbeDame = false;
        boolean habeFarbeBub = false;

        for (int i = 0; i < s2.Gestochen.size(); i++) {

            if (s2.Gestochen.get(i).getFarbe() == farbe) {
                if (!farbeAssGestochen && s2.Gestochen.get(i).getWertigkeit() == "Ass")
                    farbeAssGestochen = true;

                else if (!farbe10erGestochen && s2.Gestochen.get(i).getWertigkeit() == "10er")
                    farbe10erGestochen = true;

                else if (!farbeKoenigGestochen && s2.Gestochen.get(i).getWertigkeit() == "König")
                    farbeKoenigGestochen = true;

                else if (!farbeDameGestochen && s2.Gestochen.get(i).getWertigkeit() == "Dame")
                    farbeDameGestochen = true;

                else if (!farbeBubGestochen && s2.Gestochen.get(i).getWertigkeit() == "Bub")
                    farbeBubGestochen = true;
            }
        }

        for (int i = 0; i < s2.Hand.size(); i++) {

            if (s2.Hand.get(i).getFarbe() == farbe) {

                if(hoechsteKarteFarbe == null)
                    hoechsteKarteFarbe = s2.Hand.get(i);

                else if(hoechsteKarteFarbe.getPunkte()<s2.Hand.get(i).getPunkte())
                    hoechsteKarteFarbe = s2.Hand.get(i);
            }
        }

        if(hoechsteKarteFarbe == null)
            return null;

        if(hoechsteKarteFarbe.getPunkte()==11)
            return hoechsteKarteFarbe;

        if(hoechsteKarteFarbe.getPunkte() == 10 && farbeAssGestochen)
            return hoechsteKarteFarbe;

        if(hoechsteKarteFarbe.getPunkte() == 4 && farbeAssGestochen && farbe10erGestochen)
            return hoechsteKarteFarbe;

        if(hoechsteKarteFarbe.getPunkte() == 3 && farbeAssGestochen && farbe10erGestochen && farbeKoenigGestochen)
            return hoechsteKarteFarbe;

        //Wenn mein höchste Karte einer Farbe weniger als 3 Punkte hat und alle anderen gestochen sind, habe
        //ich letzte Karte von Farbe.
        return hoechsteKarteFarbe;
    }

    /*
    / Überprüft ob Zudrehen des Computers sinnvoll ist
    / Zudrehen, wenn ich die 2 höhsten Trümpfe habe und mindestens 2 weitere Asse oder 10er
     */
    private boolean sollComputerZudrehen() {
        //Wenn der Computer weniger als Trümpfe hat, dann nicht zudrehen
        if(!computerHat2HoechsteTruempfe())
            return false;

        //Wenn ich zusätzlich min. 2 10er habe zudrehen
        if(computerHat2HoheKarten())
            return true;

        return false;
    }

    /*
    / Gibt true zurück wennn der Computer min. 2 Karten in der Hand hält die
    / ein 10er oder eine Ass sind. Trümpfe zählen nicht. Sonst wird false zurück gegeben.
     */
    private boolean computerHat2HoheKarten()
    {
        int counterHoheKarten = 0;

        for(int i = 0; i<s2.Hand.size(); i++)
        {
            if(s2.Hand.get(i).getPunkte()>=10)
            {
                if(s2.Hand.get(i).getFarbe()!=aufgedeckterTrumpf.getFarbe())
                    counterHoheKarten++;
            }
        }

        if(counterHoheKarten>=2)
            return true;

        else
            return false;
    }

    /*
    / Gibt zurück ob Computer mindestens 2 ausstehende Trümpfe besitzt.
     */
    private boolean computerHat2HoechsteTruempfe() {
        boolean trumpfAssGestochen = false;
        boolean trumpf10erGestochen = false;
        boolean trumpfKoenigGestochen = false;
        boolean trumpfDameGestochen = false;
        boolean trumpfBubGestochen = false;

        boolean habeTrumpfAss = false;
        boolean habeTrumpf10er = false;
        boolean habeTrumpfKoenig = false;
        boolean habeTrumpfDame = false;
        boolean habeTrumpfBub = false;

        for (int i = 0; i < s2.Gestochen.size(); i++) {

            if (s2.Gestochen.get(i).getFarbe() == aufgedeckterTrumpf.getFarbe()) {
                if (!trumpfAssGestochen && s2.Gestochen.get(i).getWertigkeit() == "Ass")
                    trumpfAssGestochen = true;

                else if (!trumpf10erGestochen && s2.Gestochen.get(i).getWertigkeit() == "10er")
                    trumpf10erGestochen = true;

                else if (!trumpfKoenigGestochen && s2.Gestochen.get(i).getWertigkeit() == "König")
                    trumpfKoenigGestochen = true;

                else if (!trumpfDameGestochen && s2.Gestochen.get(i).getWertigkeit() == "Dame")
                    trumpfDameGestochen = true;

                else if (!trumpfBubGestochen && s2.Gestochen.get(i).getWertigkeit() == "Bub")
                    trumpfBubGestochen = true;
            }
        }

        for (int i = 0; i < s2.Hand.size(); i++) {

            if (s2.Hand.get(i).getFarbe() == aufgedeckterTrumpf.getFarbe()) {
                if (!trumpfAssGestochen && s2.Hand.get(i).getWertigkeit() == "Ass")
                    habeTrumpfAss = true;

                else if (!trumpf10erGestochen && s2.Hand.get(i).getWertigkeit() == "10er")
                    habeTrumpf10er = true;

                else if (!trumpfKoenigGestochen && s2.Hand.get(i).getWertigkeit() == "König")
                    habeTrumpfKoenig= true;

                else if (!trumpfDameGestochen && s2.Hand.get(i).getWertigkeit() == "Dame")
                    habeTrumpfDame= true;

                else if (!trumpfBubGestochen && s2.Hand.get(i).getWertigkeit() == "Bub")
                    habeTrumpfBub = true;
            }
        }

        if(habeTrumpfAss && habeTrumpf10er)
            return true;

        if(trumpfAssGestochen && habeTrumpf10er && habeTrumpfKoenig)
            return true;

        if(trumpf10erGestochen && habeTrumpfAss && habeTrumpfKoenig)
            return true;

        if(trumpfAssGestochen && trumpf10erGestochen && habeTrumpfKoenig && habeTrumpfDame)
            return true;

        if(trumpfAssGestochen && trumpf10erGestochen && trumpfKoenigGestochen && habeTrumpfDame && habeTrumpfBub)
            return true;

        return false;
    }

    /*
    / Findet beste Karte zum Ausspielen, wenn s1 bereits gespielt
     */
    private Karte SpieleComputerKarte(Karte karteS1) {

        //Habe ich Karte mit selber Farbe
        Karte karteMitSelberFarbe = computerKannFaerbeln(karteS1);

        //Habe ich Trumph mit dem ich stechen könnte, wenn S1 keinen Trumpf gespielt hat
        Karte trumpfKarte = null;
        if(aufgedeckterTrumpf.getFarbe()!=karteS1.getFarbe())
        {
            trumpfKarte = computerHatTrumpf();
        }

        //Habe karteMitSelberFarbe die höher als die von s1 gespielte ist, spiele diese
        if(karteMitSelberFarbe!=null && karteMitSelberFarbe.getPunkte() > karteS1.getPunkte())
        {
            return karteMitSelberFarbe;
        }

        //Habe eine Trumpfkarte mit der ich stechen kann und s1 hat nicht Trumpf gespielt
        else if(trumpfKarte!=null)
            return trumpfKarte;

        //Wenn ich nicht stechen kann --> niedrigste Karte zugeben
        return niedrigsteKarteComputer();
    }

    /*
    / Findet beste Karte zum Ausspielen, wenn S1 schon gespielt hat und zugedreht wurde bzw.
    / wenn keine Karten mehr zum Heben vorhanden sind.
     */
    private Karte SpieleComputerKarteWennS1Zugedreht(Karte karteS1) {
        //Habe ich Karte mit selber Farbe
        Karte karteMitSelberFarbe = computerKannFaerbeln(karteS1);

        //Habe ich Trumph mit dem ich stechen könnte, wenn S1 keinen Trumpf gespielt hat
        Karte trumpfKarte = null;
        if(aufgedeckterTrumpf.getFarbe()!=karteS1.getFarbe())
        {
            trumpfKarte = computerHatTrumpf();
        }

        //Habe karteMitSelberFarbe, spiele diese (Faerbeln)
        if(karteMitSelberFarbe!=null)
        {
            return karteMitSelberFarbe;
        }

        //Habe eine Trumpfkarte mit der ich stechen kann und s1 hat nicht Trumpf gespielt
        else if(trumpfKarte!=null)
            return trumpfKarte;

        //Wenn ich nicht faerbeln kann und nicht stechen kann --> niedrigse Karte zugeben
        return niedrigsteKarteComputer();
    }

    /*
    / Sucht niedrigste Karte, nimmt keine Rücksicht auf Farbe
    / Gibt die niedrigste Karte zurück, die der Computer besitzt
     */
    private Karte niedrigsteKarteComputer() {
        Karte niedrigsteKarte = null;

        for(int i=0; i<s2.Hand.size(); i++)
        {
            if(niedrigsteKarte == null)
                niedrigsteKarte = s2.Hand.get(i);

            else if(niedrigsteKarte.getPunkte() > s2.Hand.get(i).getPunkte())
                niedrigsteKarte = s2.Hand.get(i);
        }
        return niedrigsteKarte;
    }

    /*
    / Such niedrigsten Trumpf den der Computer besitzt
    /  Wenn der Computer einen Trumpf in der Hand hat wird dieser zurückgegeben, sonst null
     */
    private Karte computerHatTrumpf() {

        Karte niedrigsterTrumpf = null;
        for (int i =0; i < s2.Hand.size(); i++)
        {
            if(s2.Hand.get(i).getFarbe()==aufgedeckterTrumpf.getFarbe())
            {
                if(niedrigsterTrumpf==null)
                    niedrigsterTrumpf = s2.Hand.get(i);

                else if(s2.Hand.get(i).getPunkte()<niedrigsterTrumpf.getPunkte())
                    niedrigsterTrumpf = s2.Hand.get(i);
            }
        }
        return niedrigsterTrumpf;
    }

    /*
    / Wenn der Computer eine Karte mit derselben Farbe hat, wird diese Karte zurückgegeben.
    / Wenn es mehrere solche Karten gibt. Gib die höchste Karte zurück.
    / Sonst wird null zurückgegeben.
    */
    private Karte computerKannFaerbeln(Karte karteS1) {

        Karte karteZumFaerbeln = null;
        for (int i =0; i < s2.Hand.size(); i++)
        {
            if(s2.Hand.get(i).getFarbe()==karteS1.getFarbe())
            {
                if(karteZumFaerbeln==null)
                    karteZumFaerbeln = s2.Hand.get(i);

                else if(s2.Hand.get(i).getPunkte()>karteZumFaerbeln.getPunkte())
                    karteZumFaerbeln = s2.Hand.get(i);
            }
        }
        return karteZumFaerbeln;
    }

    public void ZugAuswerten(Karte karteS1, Karte karteS2)
    {
        //Spieler mit höherer Karte istdran = true; Karten werden in Gestochen von Gewinner gelegt; Punkte werden bei Gewinner dazugezählt;


        angesagteFarbe = null;


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

        //s.isIstdran()=true --> Spieler hat den Stich gemacht, falls er einen 20er vor seinen ersten Stich angesagt hat werden diese Punkte nachträglich hinzugezählt
        if(s2.isIstdran() && s2.isAngesagt20er())
        {
            s2.setPunkte(s2.getPunkte()+s2.getMerkePunkte());
            s2.setMerkePunkte(0);
            s2.setAngesagt20er(false);
        }
        else if(s1.isIstdran() && s1.isAngesagt20er())
        {
            s1.setPunkte(s1.getPunkte()+s1.getMerkePunkte());
            s1.setMerkePunkte(0);
            s1.setAngesagt20er(false);
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
        else if (s1.Hand.isEmpty() && zugedreht) {
            return true;
        }
        else if (angesagteFarbe != null) {
            return false;
        }
        else
        {
            if(!zugedreht)
            {
                if(s1.isIstdran())
                {
                    s1.Hand.add(stapel.get(0));
                    stapel.remove(0);
                    if (!stapel.isEmpty()) {
                        s2.Hand.add(stapel.get(0));
                        stapel.remove(0);
                    }
                    else {
                        s2.Hand.add(aufgedeckterTrumpf);
                    }

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
                    if (!stapel.isEmpty()) {
                        s1.Hand.add(stapel.get(0));
                        stapel.remove(0);
                    }
                    else {
                        s1.Hand.add(aufgedeckterTrumpf);
                    }

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
            Karte Rueckgabekarte = aufgedeckterTrumpf;
            aufgedeckterTrumpf = karte;
            return Rueckgabekarte;
        }
        else
        {
            return karte;
        }
    }

    public ArrayList<String> hat20er(Spieler s)
    {
        ArrayList<String> h20er = new ArrayList<String>(4);
        if(s.Hand.contains(new Karte("Herz","Dame", 3))&&s.Hand.contains(new Karte("Herz","König", 4)))
            h20er.add("Herz");
        if(s.Hand.contains(new Karte("Pik","Dame", 3))&&s.Hand.contains(new Karte("Pik","König", 4)))
            h20er.add("Pik");
        if(s.Hand.contains(new Karte("Karo","Dame", 3))&&s.Hand.contains(new Karte("Karo","König", 4)))
            h20er.add("Karo");
        if(s.Hand.contains(new Karte("Kreuz","Dame", 3))&&s.Hand.contains(new Karte("Kreuz","König", 4)))
            h20er.add("Kreuz");

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


    public boolean DarfKarteAuswaehlen(Karte karteamTisch, Karte karte)
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

    public boolean istStapelleer()
    {
        if(stapel.isEmpty())
            return true;
        else
            return false;
    }

    public int AnzahlKartenStapel()
    {
        return stapel.size();
    }

}
