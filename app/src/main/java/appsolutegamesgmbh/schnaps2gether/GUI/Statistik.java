package appsolutegamesgmbh.schnaps2gether.GUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import appsolutegamesgmbh.schnaps2gether.R;


public class Statistik extends ActionBarActivity {


    TextView spieleGewonnen = null;
    TextView spieleVerloren = null;
    TextView bummerlAnzahl = null;
    TextView maxPunkte = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik);

        spieleGewonnen = (TextView) findViewById(R.id.txt_Spielsiege);
        spieleVerloren = (TextView) findViewById(R.id.txt_Spieleverloren);
        bummerlAnzahl = (TextView) findViewById(R.id.txt_Bummerlstatistik);
        maxPunkte = (TextView) findViewById(R.id.txt_MaxPunkteStatistik);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        spieleGewonnen.setText(sharedPreferences.getString("spieleGewonnen","0"));
        spieleVerloren.setText(sharedPreferences.getString("spieleVerloren", "0"));
        bummerlAnzahl.setText(sharedPreferences.getString("Bummerl", "0"));
        maxPunkte.setText(sharedPreferences.getString("maxPunkte", "0"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_spielregeln, menu);
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

    public void abbrechenStatistik(View v){
        Intent i = new Intent(this, Startmenue.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }
}