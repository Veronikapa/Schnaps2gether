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
            if(lhs.getFarbe() == "kreuz")
                return -1;
            else if(lhs.getFarbe() == "herz")
                return 1;
            else if(rhs.getFarbe() == "kreuz")
                return 1;
            else if(rhs.getFarbe() == "herz")
                return -1;
            else if(lhs.getFarbe() == "pik" && rhs.getFarbe() == "karo")
                return -1;
            else
                return 1;

        }
    }
}
