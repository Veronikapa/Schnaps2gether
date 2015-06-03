package appsolutegamesgmbh.schnaps2gether.GUI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.AppIdentifier;
import com.google.android.gms.nearby.connection.AppMetadata;
import com.google.android.gms.nearby.connection.Connections;

import java.util.ArrayList;
import java.util.List;

import appsolutegamesgmbh.schnaps2gether.R;

public class Lobby extends Activity implements
        View.OnClickListener,
        PopupMenu.OnMenuItemClickListener {

    Context appContext;
    private ListView spieleListView;
    public static ArrayList<String> spieleListe = new ArrayList<String>();
    public static ArrayList<String> spieleIdListe = new ArrayList<String>();
    private ArrayAdapter<String> adapterSpieleListView;
    private String spielerName = "";
    private int spielTyp= 0; //2 = 2er, 3 = 3er, 4 = 4er


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        // Get ListView object from xml
        spieleListView = (ListView) findViewById(R.id.list);

        //Anzeigen der bereits zum Spiel verbundenen Spieler
        //Verbinden des Spielers zu Spiel
        //Wenn Spiel genügend Spieler hat wird Spiel begonnen
        spieleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view,
        int position, long id) {
            //connectTo(spieleIdListe.get(position),null);
        }

        });


        spielerName = Startmenue.SpielerName;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lobby, menu);
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

    //Anlegen eines neuen Spiel-Services
    public void neu(View v) {

        //Öffnen der Spielauswahl Activity
        /*Intent i = new Intent(this, NeuesSpiel.class);
        startActivityForResult(i, 1);*/
        popupNeuesSpiel(v);
        spieleIdListe.clear();
        spieleListe.clear();
    }

    /*
    * In dieser Methode wird nach vorhanden Spielen gesucht. Wenn ein Spiel verfügbar ist,
    * verbinden sich die Geräte.
     */
    public void beitreten(View v) {
        /*if (m_GoogleApiClient.isConnected()) {
            Toast.makeText(appContext, "Suche nach offenen Spielen...", Toast.LENGTH_SHORT).show();
            startDiscovery();
        }*/
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View view) {
    }

    public void abbrechenLobby(View v){
        Intent i = new Intent(this, Startmenue.class);
        startActivity(i);
        finish();
    }

    public void popupNeuesSpiel(View view) {
        PopupMenu popup = new PopupMenu(Lobby.this, view);
        popup.inflate(R.menu.popup_menu_neues_spiel);
        popup.setOnMenuItemClickListener(this);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getTitle().toString()) {
            case "Zweier": spielTyp = 2;
                break;
            case "Dreier": spielTyp = 3;
                break;
            case "Vierer": spielTyp = 4;
                break;
            default: break;
        }

        /*if (m_GoogleApiClient.isConnected()) {

            startAdvertising();
        }*/
        return false;
    }
}
