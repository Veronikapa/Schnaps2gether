package appsolutegamesgmbh.schnaps2gether.DataStructure;

import java.util.Comparator;

public class KartenKomparator implements Comparator<Karte> {

    @Override
    public int compare(Karte lhs, Karte rhs) {
        if(lhs.getFarbe().equals(rhs.getFarbe()))
        {
            if(lhs.getPunkte() < rhs.getPunkte())
                return -1;
            else
                return 1;
        }
        else
        {
            if(lhs.getFarbe() == "Kreuz")
                return -1;
            else if(lhs.getFarbe() == "Herz")
                return 1;
            else if(rhs.getFarbe() == "Kreuz")
                return 1;
            else if(rhs.getFarbe() == "Herz")
                return -1;
            else if(lhs.getFarbe() == "Pik" && rhs.getFarbe() == "Karo")
                return -1;
            else
                return 1;

        }
    }
}
