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

    }

    //nächste Karte am Stapel gibt die Trumpf an
    private Karte Aufdrehen()
    {
        return stapel.get(0);
    }

    //Ansagen der Trumpf und Austeilen der restlichen Karten
    private void Trumpfansagen(String trumpf, int AnzahlSpiele)
    {
        this.trumpf = trumpf;

        if(AnzahlSpiele%3 == 0) {
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
        else if(AnzahlSpiele%3 == 1)
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

            //Spieler 1 bekommt 2 Karten
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);
            s1.Hand.add(stapel.get(0));
            stapel.remove(0);
        }
        else
        {
            //Spieler 3 bekommt 2 Karten
            s3.Hand.add(stapel.get(0));
            stapel.remove(0);
            s3.Hand.add(stapel.get(0));
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

}
