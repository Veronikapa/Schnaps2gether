package appsolutegamesgmbh.schnaps2gether.W2P2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;

import appsolutegamesgmbh.schnaps2gether.GUI.Lobby;

/**
 * Created by Veronika on 23.04.2015.
 * Broadcast receiver für peer2peer Wifi Verbindung zum Verarbeiten
 * von Events. Senden von updates an Aktivitäten, Wifi hardware Zugriff
 * und der Kommunikationskanal werden hier behandelt.
 */
public class WP2PBroadCastReceiver extends BroadcastReceiver{

    private WifiP2pManager wifiP2pManager;
    private WifiP2pManager.Channel kanal;
    private Lobby lobbyActivity;

    public WP2PBroadCastReceiver(WifiP2pManager manager, WifiP2pManager.Channel kanal, Lobby activity)
    {
        this.wifiP2pManager = manager;
        this.kanal = kanal;
        this.lobbyActivity = activity;
    }
    @Override
    public void onReceive(Context context, Intent intent) {

        //TODO VP: Aktionen behandeln
        String action = intent.getAction();

        if (wifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {

            WifiAktiviertPruefen(intent);

        } else if (wifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            // TODO VP: Call WifiP2pManager.requestPeers() to get a list of current peers
        } else if (wifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            // TODO VP: Respond to new connection or disconnections
        } else if (wifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
            // TODO VP: Respond to this device's wifi state changing
        }
    }

    // VP:Überprüft den aktuellen Status der Wifi Verbindung
    private void WifiAktiviertPruefen(Intent intent) {

        int wifiState = intent.getIntExtra(wifiP2pManager.EXTRA_WIFI_STATE, -1);

        //Wifi ist eingeschalten
        if (wifiState == wifiP2pManager.WIFI_P2P_STATE_ENABLED) {
            // TODO VP: Wifi P2P ist eingeschalten
        }

        //Wifi ist nicht eingeschalten
        else {
            // TODO VP: Wi-Fi P2P is nicht eingeschalten
        }
    }
}
