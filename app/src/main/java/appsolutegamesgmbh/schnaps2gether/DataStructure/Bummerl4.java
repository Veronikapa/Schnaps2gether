package appsolutegamesgmbh.schnaps2gether.DataStructure;

public class Bummerl4 {
    private int punkteS1;
    private int punkteS2;
    private int punkteS3;
    private int punkteS4;
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

    public int getPunkteS3() {
        return punkteS3;
    }

    public void setPunkteS3(int punkteS3) {
        this.punkteS3 = punkteS3;
    }

    public int getPunkteS4() {
        return punkteS4;
    }

    public void setPunkteS4(int punkteS4) {
        this.punkteS4 = punkteS4;
    }

    public int getAnzahlSpiele() {
        return AnzahlSpiele;
    }

    public void setAnzahlSpiele(int anzahlSpiele) {
        AnzahlSpiele = anzahlSpiele;
    }

    public Bummerl4() {
        punkteS1 = 0;
        punkteS2 = 0;
        punkteS3 = 0;
        punkteS4 = 0;
        AnzahlSpiele = 0;
    }

    public boolean istBummerlzuEnde()
    {
        if(punkteS1 >= 24 || punkteS2 >= 24)
            return true;
        else
            return false;
    }
}
