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
        if (!Farbe.equals(other.Farbe))
            return false;
        if (!Wertigkeit.equals(other.Wertigkeit))
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
            case "Bube": return 2;
            case "Dame": return 3;
            case "König": return 4;
            case "10er": return 10;
            case "Ass": return 11;
            default: return 0;
        }
    }
    public String getFarbeIcon() {
        return new String(changeToLowerCase(Farbe)+"_icon");
    }

    public static String getFarbeIcon(String farbe) {
        return new String(changeToLowerCase(farbe)+"_icon");
    }


    public static String changeToLowerCase(String value){
            String temp;
            if(value.equals("König")) {
                temp = "koenig";
            }else {
                temp = value.toLowerCase();
            }
            return temp;
        }

    public String getPngName() {
        return new String(changeToLowerCase(Farbe)+"_"+ changeToLowerCase(Wertigkeit));
    }
    @Override
    public String toString() {
        return this.getFarbe()+" "+this.getWertigkeit();
    }

    public static ArrayList<Karte> erstelleKartendeck()
    {
        ArrayList<Karte> Kartendeck = new ArrayList<Karte>(20);

        Kartendeck.add(new Karte("Herz","Bube",2));
        Kartendeck.add(new Karte("Herz","Dame",3));
        Kartendeck.add(new Karte("Herz","König",4));
        Kartendeck.add(new Karte("Herz","10er",10));
        Kartendeck.add(new Karte("Herz","Ass",11));

        Kartendeck.add(new Karte("Karo","Bube",2));
        Kartendeck.add(new Karte("Karo","Dame",3));
        Kartendeck.add(new Karte("Karo","König",4));
        Kartendeck.add(new Karte("Karo","10er",10));
        Kartendeck.add(new Karte("Karo","Ass",11));

        Kartendeck.add(new Karte("Pik","Bube",2));
        Kartendeck.add(new Karte("Pik","Dame",3));
        Kartendeck.add(new Karte("Pik","König",4));
        Kartendeck.add(new Karte("Pik","10er",10));
        Kartendeck.add(new Karte("Pik","Ass",11));

        Kartendeck.add(new Karte("Kreuz","Bube",2));
        Kartendeck.add(new Karte("Kreuz","Dame",3));
        Kartendeck.add(new Karte("Kreuz","König",4));
        Kartendeck.add(new Karte("Kreuz","10er",10));
        Kartendeck.add(new Karte("Kreuz","Ass",11));

        return Kartendeck;
    }
    public int getIconResourceId(){
        Field f;
        int id = -1;
        try {
            f = R.drawable.class.getDeclaredField(getFarbeIcon());
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

    public static int getIconResourceId(String farbe){
        Field f;
        int id = -1;
        try {
            f = R.drawable.class.getDeclaredField(getFarbeIcon(farbe));
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
}
