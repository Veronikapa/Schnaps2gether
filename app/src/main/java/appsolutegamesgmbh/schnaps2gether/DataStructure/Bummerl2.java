package appsolutegamesgmbh.schnaps2gether.DataStructure;


public class Bummerl2 {

    private int punkteS1;
    private int punkteS2;
    private int AnzahlSpiele;

    public int getPunkteS1() {
        return punkteS1;
    }

    public void setPunkteS1(int punkteS1) {
        this.punkteS1 = punkteS1;
    }

    public int getPunkteS2() {
        return punkteS2;
    }

    public void setPunkteS2(int punkteS2) {
        this.punkteS2 = punkteS2;
    }

    public int getAnzahlSpiele() {
        return AnzahlSpiele;
    }

    public void setAnzahlSpiele(int anzahlSpiele) {
        AnzahlSpiele = anzahlSpiele;
    }

    public Bummerl2() {
        punkteS1 = 0;
        punkteS2 = 0;
        AnzahlSpiele = 0;
    }

    public Bummerl2(String punkteUndAnzSpiele) {
        String[] punkteAnzSpiele = punkteUndAnzSpiele.split(" ");
        punkteS1 = Integer.decode(punkteAnzSpiele[0]);
        punkteS2 = Integer.decode(punkteAnzSpiele[1]);
        AnzahlSpiele = Integer.decode(punkteAnzSpiele[2]);
    }

    public boolean istBummerlzuEnde()
    {
        if(punkteS1 >= 7 || punkteS2 >= 7)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return getPunkteS1()+" "+getPunkteS2()+" "+getAnzahlSpiele();
    }
}
