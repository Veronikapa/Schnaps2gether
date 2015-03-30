package appsolutegamesgmbh.schnaps2gether.DataStructure;


public class Bummerl2 {

    private int punkteS1;
    private int punkteS2;

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

    public Bummerl2() {
        punkteS1 = 0;
        punkteS2 = 0;
    }

    public boolean istBummerlzuEnde()
    {
        if(punkteS1 >= 7 || punkteS2 >= 7)
            return true;
        else
            return false;
    }

}
