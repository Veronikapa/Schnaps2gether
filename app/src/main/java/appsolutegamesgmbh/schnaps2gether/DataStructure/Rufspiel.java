package appsolutegamesgmbh.schnaps2gether.DataStructure;

/**
 * Created by Kerstin on 05.05.2015.
 */
public class Rufspiel {
    private String spiel;
    private int punkte;

    public String getSpiel() {
        return spiel;
    }

    public int getPunkte() {
        return punkte;
    }

    public Rufspiel(String spiel) throws WrongGameException
    {
        this.spiel = spiel;
        if(spiel.equals("normal"))
            this.punkte = 3;
        else if(spiel.equals("Schnapser"))
            this.punkte = 6;
        else if(spiel.equals("Land"))
            this.punkte = 9;
        else if(spiel.equals("Kontraschnapser"))
            this.punkte = 12;
        else if(spiel.equals("Bauernschnapser"))
            this.punkte = 12;
        else if(spiel.equals("Kontrabauernschnapser"))
            this.punkte = 24;
        else if(spiel.equals("Herrenjodler"))
            this.punkte = 24;
        else if(spiel.equals("Farbenjodler"))
            this.punkte = 18;
        else
        {
           throw new WrongGameException();
        }
    }

}
