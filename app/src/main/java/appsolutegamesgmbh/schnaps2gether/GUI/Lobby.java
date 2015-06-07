package appsolutegamesgmbh.schnaps2gether.GUI;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.util.ArrayList;

import appsolutegamesgmbh.schnaps2gether.Services.NearbyConnectionService;
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
    NearbyConnectionService mService;
    boolean mBound = false;


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
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, NearbyConnectionService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        mService.setLobby(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
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

    public void spielGefunden(String spielId, String spielTypUndName) {
        int spielTyp = Integer.parseInt(spielTypUndName.substring(0,1));
        String spielerName = spielTypUndName.substring(1);

        //Neues Spiel zu Liste hinzufügen
        spieleListe.add(spielTyp+"er Schnapsen von "+spielerName);
        spieleIdListe.add(spielId);

        adapterSpieleListView = new ArrayAdapter<String>(appContext,
                android.R.layout.simple_list_item_1, spieleListe);

        // Assign adapter to ListView
        spieleListView.setAdapter(adapterSpieleListView);
    }

    public void spielStarten() {
        if (spielTyp == 2) {
            startActivity(new Intent(Lobby.this, Spielfeld2Client.class));
        } else if (spielTyp == 3) {
            startActivity(new Intent(Lobby.this, Spielfeld3Client.class));
        } else if (spielTyp == 4) {
            startActivity(new Intent(Lobby.this, Spielfeld4Client.class));
        }
    }

    public void spielZurListeHinzufügen() {
        //Neues Spiel zu Liste hinzufügen
        spieleListe.add(spielTyp + "er Schnapsen von " + spielerName);

        adapterSpieleListView = new ArrayAdapter<String>(appContext,
                android.R.layout.simple_list_item_1, spieleListe);

        // Assign adapter to ListView
        spieleListView.setAdapter(adapterSpieleListView);
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

        mService.spielErstellen(spielTyp, spielerName);
        return false;
    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            NearbyConnectionService.NearbyConnectionBinder binder = (NearbyConnectionService.NearbyConnectionBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}
