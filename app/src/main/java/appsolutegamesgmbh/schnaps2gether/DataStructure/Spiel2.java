package appsolutegamesgmbh.schnaps2gether.DataStructure;

/**
 * Created by Kerstin on 29.03.2015.
 */
public class Spiel2 {

    private Kartendeck kartendeck;
    private Spieler s1;
    private Spieler s2;
    private Kartendeck trumpf;
    private boolean zugedreht;

    public Spiel2()
    {
        kartendeck = new Kartendeck();
        kartendeck.Befüllen();
        s1 = new Spieler();
        s1.istdran = true;
        zugedreht = false;
        s2 = new Spieler();
        Anfangsdeck();
    }

    private void Anfangsdeck()
    {
         //TODO: K
        //TODO: Spieler 1 bekommt 3 Karten sortiert in die Hand

        //TODO: Spieler 2 bekommt 3 Karten sortiert in die Hand

        //TODO: Trumpf ausgeben

        //TODO: Spieler 1 bekommt 2 Karten sortiert in die Hand

        //TODO: Spieler 2 bekommt 2 Karten sortiert in die Hand

    }

    public void Auspielen(Kartendeck karteS1)
    {
        //TODO: V
        //TODO: S1 klickt im Spielfeld2 auf Karte; Karte aus Hand S1 löschen; Zudrehen berücksichtigen
    }

    public void AuspielenComputer(Kartendeck karteS1)
    {
        //TODO: V
        //TODO: wenn S1 Karte ausgespielt hat (karteS1 != null), basierend auf Karte, Karte ausspielen sonst beliebige Karte ausspielen; Zudrehen berücksichtigen

        //TODO: istdrann = true, eventuell zudrehen

        //TODO: karteS1 == null, setze am Ende Spieler 1 ist dran
    }

    public void ZugAuswerten(Kartendeck karteS1, Kartendeck karteS2)
    {
        //TODO: K
        //TODO: Spieler mit höherer Karte istdran = true; Karten werden in Gestochen von Gewinner gelegt; Punkte werden bei Gewinner dazugezählt; Zudrehen berücksichtigen

        //TODO: hat ein Spieler 66 --> Spiel zu Ende sonst neue Karten austeilen, wenn möglich
    }
    public void Zudrehen()
    {
        zugedreht = true;
    }

    public void TrumpfkarteAustauschen()
    {

    }

    public boolean DarfKarteAuswählen(Kartendeck karteamTisch, Kartendeck karte)
    {
        //TODO: K
        return true;
    }


}
