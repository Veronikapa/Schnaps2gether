package appsolutegamesgmbh.schnaps2gether.GUI;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import appsolutegamesgmbh.schnaps2gether.R;


public class Spielfeld4 extends ActionBarActivity {

    /*TODO: gewünschter Spielablauf:
            - Spiel anlegen (als Parameter AnzahlSpiele von Klasse Bummerl mitgebeben) - jeder Spieler bekommt automatisch die ersten 3 Karten
            - nun ist die Vorhand dran den Trumpf anzusagen - durch aufdrehen (fürs aufdrehen ist Methode implementiert) oder direkt
            - Methode Trumpfansagen aufrufen (es werden automatisch die restlichen Karten ausgeteilt)
            - über DarfSpielAnsagen und Spielansagen nach der Reihe jedem Spieler fragen, ob er was spielen will (beginnend bei der Vorhand)
            - ist SpielzuEnde auch schon vor dem ersten Ausspielen abprüfen, da bei Herrenjodler und Farbenjodler spiel sofort beendet !!!

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spielfeld4);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spielfeld4, menu);
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
