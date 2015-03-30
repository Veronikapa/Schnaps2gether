package appsolutegamesgmbh.schnaps2gether.GUI;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel2;
import appsolutegamesgmbh.schnaps2gether.R;


public class Spielfeld2 extends ActionBarActivity {

    /* TODO: Hand von Spieler1 auslesen und anzeigen; Trumpf Karte anzeigen;
    * TODO: Klick auf Karte Spieler 1
    * * Ist Spieler1 dran (zu Spielbeginn immer) und spiel.DarfKarteAusspielen() = true -->  spiel.Ausspielen() aufrufen, spiel.AusspielenComputer(), spiel.ZugAuswerten aufrufen()
    * * Ist danach Spieler1 wieder dran. Tu nix.
    * * Ist Spieler2 dran. Triggere AusspielenComputer
    *
    * TODO: Klick auf Zudrehen --> spiel.Zudrehen aufrufen, tbd..
    * TODO: Punkte auslesen von Spieler 1 nach Zugauswerten und anzeigen
    * TODO: Überprüfen ob Spiel weitergeht oder neues Spiel begonnen wird; Auslesen wer Spiel gewonnen hat
    * */
    private Spiel2 spiel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spielfeld2);

        spiel = new Spiel2();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spielfeld, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
