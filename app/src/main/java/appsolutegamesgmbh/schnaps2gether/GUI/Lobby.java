package appsolutegamesgmbh.schnaps2gether.GUI;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.nsd.WifiP2pDnsSdServiceInfo;
import android.net.wifi.p2p.nsd.WifiP2pDnsSdServiceRequest;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import appsolutegamesgmbh.schnaps2gether.R;
import appsolutegamesgmbh.schnaps2gether.W2P2.WP2PBroadCastReceiver;

public class Lobby extends Activity {

    private TextView spielliste;

    // Variablen für die w2p2 Verbindung
    private IntentFilter w2p2IntentFilter;
    private WifiP2pManager w2p2Manager;
    private WifiP2pManager.Channel w2p2Kanal;
    private BroadcastReceiver w2p2BroadCastReceiver;
    private WifiP2pDnsSdServiceRequest request;
    private List peers = new ArrayList();

    final HashMap<String, String> players = new HashMap<String, String>();

    private WifiP2pManager.PeerListListener peerListListener = new WifiP2pManager.PeerListListener() {
        @Override
        public void onPeersAvailable(WifiP2pDeviceList peerList) {

            // Out with the old, in with the new.
            peers.clear();
            peers.addAll(peerList.getDeviceList());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        spielliste = (TextView) findViewById(R.id.spielliste);

        //Initialisieren und Erstellen der w2p2 Objekte für die Aktivity
        w2p2Manager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        w2p2Kanal = w2p2Manager.initialize(this, getMainLooper(), null);
        w2p2BroadCastReceiver = new WP2PBroadCastReceiver(w2p2Manager, w2p2Kanal, this,peerListListener);

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
    }


    public void neu(View v){
        startActivity(new Intent(Lobby.this, NeuesSpiel.class));
        //finish();
        startRegistration();
    }

    private void discoverOtherDevicesinPeer()
    {
        w2p2Manager.discoverPeers(w2p2Kanal, new WifiP2pManager.ActionListener() {

            @Override
            public void onSuccess() {
                // Code for when the discovery initiation is successful goes here.
                // No services have actually been discovered yet, so this method
                // can often be left blank.  Code for peer discovery goes in the
                // onReceive method, detailed below.
            }

            @Override
            public void onFailure(int reasonCode) {
                // Code for when the discovery initiation fails goes here.
                // Alert the user that something went wrong.
            }
        });
    }

    public void connect() {
        // Picking the first device found on the network.
        WifiP2pDevice device = (WifiP2pDevice) peers.get(0);

        WifiP2pConfig config = new WifiP2pConfig();
        config.deviceAddress = device.deviceAddress;
        config.wps.setup = WpsInfo.PBC;

        w2p2Manager.connect(w2p2Kanal, config, new WifiP2pManager.ActionListener() {

            @Override
            public void onSuccess() {
                // WiFiDirectBroadcastReceiver will notify us. Ignore for now.
            }

            @Override
            public void onFailure(int reason) {;
            }
        });
    }

    public void onConnectionInfoAvailable(final WifiP2pInfo info) {

        // InetAddress from WifiP2pInfo struct.
        String groupOwnerAddress = info.groupOwnerAddress.getHostAddress();

        // After the group negotiation, we can determine the group owner.
        if (info.groupFormed && info.isGroupOwner) {
            // Do whatever tasks are specific to the group owner.
            // One common case is creating a server thread and accepting
            // incoming connections.
        } else if (info.groupFormed) {
            // The other device acts as the client. In this case,
            // you'll want to create a client thread that connects to the group
            // owner.
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void startRegistration() {
        //  Create a string map containing information about your service.
        Map record = new HashMap();
        record.put("Spiel Veronika verfügbar", "sichtbar");

        // Service information.  Pass it an instance name, service type
        // _protocol._transportlayer , and the map containing
        // information other devices will want once they connect to this one.
        WifiP2pDnsSdServiceInfo serviceInfo =
                WifiP2pDnsSdServiceInfo.newInstance("_Veronika", "_presence._tcp", record);


        // Add the local service, sending the service info, network channel,
        // and listener that will be used to indicate success or failure of
        // the request.
        w2p2Manager.addLocalService(w2p2Kanal, serviceInfo, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                // Command successful! Code isn't necessarily needed here,
                // Unless you want to update the UI or add logging statements.
                Log.d("add local Service","Local Service has been created successfully!");
                players.put("Veronika","Spiel2");
                spielliste.setText("Veronika");
            }

            @Override
            public void onFailure(int arg0) {
                // Command failed.  Check for P2P_UNSUPPORTED, ERROR, or BUSY
                Log.d("add local Service","Local Service has not been created!");
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void discoverService() {

        WifiP2pManager.DnsSdTxtRecordListener txtListener = new WifiP2pManager.DnsSdTxtRecordListener() {
            @Override
        /* Callback includes:
         * fullDomain: full domain name: e.g "printer._ipp._tcp.local."
         * record: TXT record dta as a map of key/value pairs.
         * device: The device running the advertised service.
         */

            public void onDnsSdTxtRecordAvailable(
                    String fullDomain, Map record, WifiP2pDevice device) {
                Log.d("Service available", "DnsSdTxtRecord available -" + record.toString());
                players.put(device.deviceAddress, (String) record.get("Veronika"));
            }
        };

        WifiP2pManager.DnsSdServiceResponseListener servListener = new WifiP2pManager.DnsSdServiceResponseListener() {
            @Override
            public void onDnsSdServiceAvailable(String instanceName, String registrationType,
                                                WifiP2pDevice resourceType) {

                // Update the device name with the human-friendly version from
                // the DnsTxtRecord, assuming one arrived.
                resourceType.deviceName = players
                        .containsKey(resourceType.deviceAddress) ? players
                        .get(resourceType.deviceAddress) : resourceType.deviceName;

            }
        };

        w2p2Manager.setDnsSdResponseListeners(w2p2Kanal, servListener, txtListener);

        w2p2Manager.discoverServices(w2p2Kanal, new WifiP2pManager.ActionListener() {

            @Override
            public void onSuccess() {
                // Success!
            }

            @Override
            public void onFailure(int code) {
                // Command failed.  Check for P2P_UNSUPPORTED, ERROR, or BUSY
                Log.d("failure ", "failure");
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void createRequest() {

    request = WifiP2pDnsSdServiceRequest.newInstance();
    w2p2Manager.addServiceRequest(w2p2Kanal,
    request,
            new WifiP2pManager.ActionListener() {
        @Override
        public void onSuccess() {
            // Success!
        }

        @Override
        public void onFailure(int code) {
            // Command failed.  Check for P2P_UNSUPPORTED, ERROR, or BUSY
        }
    });
    }

    public void beitreten(View view) {
        discoverService();
    }
}
