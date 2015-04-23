package appsolutegamesgmbh.schnaps2gether.GUI;

import android.app.Activity;
<<<<<<< HEAD
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
=======
import android.content.Intent;
>>>>>>> origin/master
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.nio.channels.Channel;

import appsolutegamesgmbh.schnaps2gether.R;
import appsolutegamesgmbh.schnaps2gether.W2P2.WP2PBroadCastReceiver;

public class Lobby extends Activity {

    // Variablen für die w2p2 Verbindung
    private IntentFilter w2p2IntentFilter;
    private WifiP2pManager w2p2Manager;
    private WifiP2pManager.Channel w2p2Kanal;
    private BroadcastReceiver w2p2BroadCastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        //Initialisieren und Erstellen der w2p2 Objekte für die Aktivity
        w2p2Manager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        w2p2Kanal = w2p2Manager.initialize(this, getMainLooper(), null);
        w2p2BroadCastReceiver = new WP2PBroadCastReceiver(w2p2Manager, w2p2Kanal, this);

        //Registrieren der w2p2 Aktionen in Activity
        w2p2IntentFilter = new IntentFilter();
        w2p2IntentFilter.addAction(w2p2Manager.WIFI_P2P_STATE_CHANGED_ACTION);
        w2p2IntentFilter.addAction(w2p2Manager.WIFI_P2P_PEERS_CHANGED_ACTION);
        w2p2IntentFilter.addAction(w2p2Manager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        w2p2IntentFilter.addAction(w2p2Manager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
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

<<<<<<< HEAD
    //VP: Registrierung des broadcast receivers
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(w2p2BroadCastReceiver, w2p2IntentFilter);
    }

    // De-Registierung des w2p2 broadcast receivers
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(w2p2BroadCastReceiver);
=======

    public void neu(View v){
        Intent intent = new Intent(this, NeuesSpiel.class);
>>>>>>> origin/master
    }
}
