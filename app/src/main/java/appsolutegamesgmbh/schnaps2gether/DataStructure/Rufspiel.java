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
        if(spiel == "normal")
            this.punkte = 3;
        else if(spiel == "Schnapser")
            this.punkte = 6;
        else if(spiel == "Land")
            this.punkte = 9;
        else if(spiel == "Kontraschnapser")
            this.punkte = 12;
        else if(spiel == "Bauernschnapser")
            this.punkte = 12;
        else if(spiel == "Kontrabauernschnapser")
            this.punkte = 24;
        else if(spiel == "Herrenjodler")
            this.punkte = 24;
        else if(spiel == "Farbenjodler")
            this.punkte = 18;
        else
        {
           throw new WrongGameException();
        }
    }

}
