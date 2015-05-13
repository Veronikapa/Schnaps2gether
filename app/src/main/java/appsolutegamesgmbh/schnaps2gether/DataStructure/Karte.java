package appsolutegamesgmbh.schnaps2gether.DataStructure;

import java.lang.reflect.Field;
import java.util.ArrayList;

import appsolutegamesgmbh.schnaps2gether.R;


public class Karte {

    private String Farbe;
    private String Wertigkeit;
    private int Punkte;

    public String getFarbe() {
        return Farbe;
    }

    public void setFarbe(String farbe) {
        Farbe = farbe;
    }

    public String getWertigkeit() {
        return Wertigkeit;
    }

    public void setWertigkeit(String wertigkeit) {
        Wertigkeit = wertigkeit;
    }

    public int getPunkte() {
        return Punkte;
    }

    public void setPunkte(int punkte) {
        Punkte = punkte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Karte other = (Karte) o;
        if (Farbe != other.Farbe)
            return false;
        if (Wertigkeit != other.Wertigkeit)
            return false;
        if (Punkte != other.Punkte)
            return false;
        return true;
    }

    public Karte(String farbe, String wertigkeit, int punkte) {
        Farbe = farbe;
        Wertigkeit = wertigkeit;
        Punkte = punkte;
    }

    public Karte(String farbeUndWertigkeit) {
        String[] farbeWertigkeit = farbeUndWertigkeit.split(" ");
        Farbe = farbeWertigkeit[0];
        Wertigkeit = farbeWertigkeit[1];
        Punkte = getPunkte(Wertigkeit);
    }

    public int getPunkte(String wertigkeit) {
        switch (wertigkeit) {
            case "bube": return 2;
            case "dame": return 3;
            case "koenig": return 4;
            case "10er": return 10;
            case "ass": return 11;
            default: return 0;
        }
    }
    public String getPngName() {
        return new String(Farbe+"_"+Wertigkeit);
    }


    public int getImageResourceId(){
        Field f;
        int id = -1;
        try {
            f = R.drawable.class.getDeclaredField(getPngName());
            id = f.getInt(null);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public String toString() {
        return this.getFarbe()+" "+this.getWertigkeit();
    }

    public static ArrayList<Karte> erstelleKartendeck()
    {
        ArrayList<Karte> Kartendeck = new ArrayList<Karte>(20);

        Kartendeck.add(new Karte("herz","bube",2));
        Kartendeck.add(new Karte("herz","dame",3));
        Kartendeck.add(new Karte("herz","koenig",4));
        Kartendeck.add(new Karte("herz","10er",10));
        Kartendeck.add(new Karte("herz","ass",11));

        Kartendeck.add(new Karte("karo","bube",2));
        Kartendeck.add(new Karte("karo","dame",3));
        Kartendeck.add(new Karte("karo","koenig",4));
        Kartendeck.add(new Karte("karo","10er",10));
        Kartendeck.add(new Karte("karo","ass",11));

        Kartendeck.add(new Karte("pik","bube",2));
        Kartendeck.add(new Karte("pik","dame",3));
        Kartendeck.add(new Karte("pik","koenig",4));
        Kartendeck.add(new Karte("pik","10er",10));
        Kartendeck.add(new Karte("pik","ass",11));

        Kartendeck.add(new Karte("kreuz","bube",2));
        Kartendeck.add(new Karte("kreuz","dame",3));
        Kartendeck.add(new Karte("kreuz","koenig",4));
        Kartendeck.add(new Karte("kreuz","10er",10));
        Kartendeck.add(new Karte("kreuz","ass",11));

        return Kartendeck;
    }
}
