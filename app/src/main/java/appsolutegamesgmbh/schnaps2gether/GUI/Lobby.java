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
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener,
        Connections.ConnectionRequestListener,
        Connections.MessageListener,
        Connections.EndpointDiscoveryListener {

    private Context appContext;
    private ListView spieleListView;
    private String[] spieleListe = new String[]{};
    private ArrayAdapter<String> adapterSpieleListView;
    private String spielerName = "";

    // Legt fest ob das Gerät der Host ist
    private boolean m_IsHost = false;
    //Api Client der pro Gerät verfügbar sein muss
    public static GoogleApiClient m_GoogleApiClient;

    // Speichert die endpoint- und deviceIds von verbundenen Geräten
    public static ArrayList<String> endpointIds = new ArrayList<String>();
    public static ArrayList<String> deviceIds = new ArrayList<String>();

    public static  boolean endpointIdsReceived;

    //Geräte die sich verbinden wollen, müssen mit einem Wifi oder einem Ethernet verbunden sein
    private static int[] NETWORK_TYPES = {ConnectivityManager.TYPE_WIFI,
            ConnectivityManager.TYPE_ETHERNET};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        // Get ListView object from xml
        spieleListView = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView
        spieleListe= new String[] {};
        appContext = this.getApplicationContext();

        //Beim Erstellen der Activity muss auch pro Gerät ein ApiClient für die Wifi Verbindung
        //angelegt werden
        m_GoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Nearby.CONNECTIONS_API)
                .build();

        endpointIdsReceived = false;

        //Anzeigen der bereits zum Spiel verbundenen Spieler
        //Verbinden des Spielers zu Spiel
        //Wenn Spiel genügend Spieler hat wird Spiel begonnen
        spieleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view,
        int position, long id) {

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

        //Anbieten eines neuen Spiels soll nur erfolgen, wenn eine Verbindung verfügbar ist.
        if (m_GoogleApiClient.isConnected()) {

            startAdvertising();
        }
    }

    /*
    * In dieser Methode wird nach vorhanden Spielen gesucht. Wenn ein Spiel verfügbar ist,
    * verbinden sich die Geräte.
     */
    public void beitreten(View v) {
        if (m_GoogleApiClient.isConnected()) {
            Toast.makeText(appContext, "Suche nach offenen Spielen...", Toast.LENGTH_SHORT).show();
            startDiscovery();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(appContext, "GoogleApiConnection erfolgreich.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionSuspended(int i) {
        //Zurzeit keine Implementierung. Hier wäre anzugeben, was passieren soll wenn die Verbindung
        //via GoogleApiClient fehlschlägt.
    }

    @Override
    /*
    * Nach dem man ein Gerät gefunden hat verbindet man sich zu diesem Gerät.
     */
    public void onEndpointFound(String s, String s2, String s3, String s4) {
        connectTo(s, spielerName);
    }

    @Override
    public void onEndpointLost(String s) {
        //Zurzeit keine Implementierung. Hier wäre anzugeben, was passieren soll wenn ein Service
        // nicht mehr verfügbar ist.
    }

    @Override
    public void onMessageReceived(String endpointID, byte[] payload, boolean isReliable) {
        String message = new String(payload);
        if (!m_IsHost) {
            if (!endpointIdsReceived) {
    /*            String[] aIds = message.split(" ");
                for (String ids : aIds) {
                    String[] aId = ids.split(":");
                    endpointIds.add(aId[0]);
                    //deviceIds.add(aId[1]);}
     */
                endpointIds.add(message);
                endpointIdsReceived = true;
            } else {
                Spielfeld2Client.receiveFromLobby(endpointID, payload, isReliable);
            }
        }

        else
            Spielfeld2Host.receiveFromLobby(endpointID,payload,isReliable);

    }

    @Override
    public void onDisconnected(String s) {
        //Zurzeit keine Implementierung. Hier ist anzugeben was passieren soll, wenn
        //Verbindung beendet wird.
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Toast.makeText(appContext, "Verbindung fehlgeschlagen!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();

        //Wenn Activity gestartet wird muss eine Verbindung zum GoogleApiClient erfolgen.
        m_GoogleApiClient.connect();
    }

    /*@Override
    public void onStop() {
        super.onStop();
        //Wenn Activity beendet wird und eine Verbindung vorhanden ist muss Google Api Client
        //Verbindung beendet werden.
        if (m_GoogleApiClient != null && m_GoogleApiClient.isConnected()) {
            m_GoogleApiClient.disconnect();
        }
    }*/

    /*
    * VP: Überprüft ob ein Gerät mit einem Wifi oder Ethernet Netwerk verbunden ist.
    * Diese Methode muss aufgerufen werden, bevor ein Gerät sich mit anderen Geräten verbinden möchte.
     */
    private boolean isConnectedToNetwork() {
        ConnectivityManager connManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        for (int networkType : NETWORK_TYPES) {
            NetworkInfo info = connManager.getNetworkInfo(networkType);
            if (info != null && info.isConnectedOrConnecting()) {
                return true;
            }
        }
        return false;
    }

    private void startAdvertising() {

        //Gerät muss mit Wifi oder Ethernet verbunden sein, damit es ein Service anbieten kann.
        if (!isConnectedToNetwork()) {
            Toast.makeText(appContext, "Sie sind mit keinem Netzwerk verbunden.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Gerät das Service anbietet ist der Host des Spiels.
         m_IsHost = true;

        //Hiermit wird sichergestellt, dass das Gerät das ein Service anbietet, die aktuelle Version
        // des Google Play Dienstes installiert hat. Wenn es nicht installiert ist, wird Benutzer
        // zu Installation aufgefordert.
        List<AppIdentifier> appIdentifierList = new ArrayList<>();
        appIdentifierList.add(new AppIdentifier(getPackageName()));
        AppMetadata appMetadata = new AppMetadata(appIdentifierList);

        //Timeout wird auf unendlich gesetzt. Anbieten des Services wird erst nach Verbindung gestoppt
        //via stopAdvertising();
        long NO_TIMEOUT = 0L;

        //Anbieten eines Services für andere Geräte.
        Nearby.Connections.startAdvertising(m_GoogleApiClient, spielerName, appMetadata, NO_TIMEOUT,
                this).setResultCallback(new ResultCallback<Connections.StartAdvertisingResult>() {
            @Override
            public void onResult(Connections.StartAdvertisingResult result) {
                if (result.getStatus().isSuccess()) {
                    //Neues Spiel zu Liste hinzufügen
                    spieleListe = new String[]{"2er Schnapsen von Veronika"};

                    adapterSpieleListView = new ArrayAdapter<String>(appContext,
                            android.R.layout.simple_list_item_1, spieleListe);

                    // Assign adapter to ListView
                    spieleListView.setAdapter(adapterSpieleListView);

                } else {
                    int statusCode = result.getStatus().getStatusCode();
                    // Advertising failed - see statusCode for more details
                }
            }
        });
    }

    private void startDiscovery() {
        //Gerät das auf der Suche nach Services ist, muss mit einem Wifi oder einem Ethernet
        //verbunden sein.
        if (!isConnectedToNetwork()) {
            Toast.makeText(appContext, "Sie sind leider mit keinem Netzwerk verbunden.", Toast.LENGTH_SHORT).show();
            return;
        }

        //Nach Services mit der angegebenen ServiceId wird gesucht.
        final String serviceId = getString(R.string.service_id);

        //Timeout für Serive-Suche ist auf 1 Minute gesetzt.
        long DISCOVER_TIMEOUT = 6000L;

        // Suche nach Services die in der Nähe sind und unserer App entsprechen.
        Nearby.Connections.startDiscovery(m_GoogleApiClient, serviceId, DISCOVER_TIMEOUT, this)
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {

                        //Service wurde gefunden
                        if (status.isSuccess()) {

                            //Neues Spiel zu Liste hinzufügen
                            spieleListe = new String[]{"2er Schnapsen von Veronika"};

                            adapterSpieleListView = new ArrayAdapter<String>(appContext,
                                    android.R.layout.simple_list_item_1, spieleListe);

                            // Assign adapter to ListView
                            spieleListView.setAdapter(adapterSpieleListView);

                            String endPointId = Nearby.Connections.getLocalEndpointId(m_GoogleApiClient);
                            String deviceId = Nearby.Connections.getLocalDeviceId(m_GoogleApiClient);
                            //Aufruf der Methode zur Handhabung des gefundenen Geräts
                            onEndpointFound(endPointId, deviceId, serviceId, spielerName);

                        }

                        //Es konnte kein Service in der Nähe gefunen werden.
                        else {

                            Toast.makeText(appContext, "Es konnten leider keine offenen Spiele gefunden werden.:(", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //Senden einer Verbindungsanfrage zu Host und Verbindung wenn möglich.
    private void connectTo(String endpointId, final String endpointName) {

        byte[] myPayload = null;
        Nearby.Connections.sendConnectionRequest(m_GoogleApiClient, spielerName, endpointId, myPayload,
                new Connections.ConnectionResponseCallback() {
                    @Override
                    public void onConnectionResponse(String remoteEndpointId, Status status,
                                                     byte[] bytes) {
                        if (status.isSuccess()) {
                            Toast.makeText(appContext, "Geräte wurden verbunden! ", Toast.LENGTH_SHORT).show();
                            endpointIds.add(Nearby.Connections.getLocalEndpointId(m_GoogleApiClient));
                            deviceIds.add(Nearby.Connections.getLocalDeviceId(m_GoogleApiClient));

                            int i;
                            if (m_IsHost) {
                                String allIds = "";
                                allIds = Nearby.Connections.getLocalEndpointId(m_GoogleApiClient);
                    /*            for (i=0; i<endpointIds.size(); i++) {
                                    allIds += endpointIds.get(i)+":"+deviceIds.get(i)+" ";
                                }
                                allIds = allIds.substring(0,allIds.length()-1);*/
                                Nearby.Connections.sendReliableMessage(m_GoogleApiClient, endpointIds, allIds.getBytes());
                            }

                            //Starten der nächsten Activity
                            startActivity(new Intent(Lobby.this, Spielfeld2Client.class));
                            finish();

                        } else {
                            // Verbindung fehlgeschlagen
                            Toast.makeText(appContext, "Geräte konnten nicht verbunden werden!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, this);
    }

    @Override
    //Empfangen einer Verbindungsanfrage von Client
    public void onConnectionRequest(final String remoteEndpointId, final String remoteDeviceId,
                                    final String remoteEndpointName, byte[] payload) {
        if (m_IsHost) {
            byte[] myPayload = null;

            //TODO VP: Einschränken auf Spielgröße, vorerst nur eine Verbindung pro Spiel erlaubt
            Nearby.Connections.acceptConnectionRequest(m_GoogleApiClient, remoteEndpointId,
                    myPayload, this).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(Status status) {
                    if (status.isSuccess()) {
                        Toast.makeText(appContext, "Vebindung hergestellt zu " + remoteEndpointName,
                                Toast.LENGTH_SHORT).show();
                        //Beenden der Service anzeige nach Verbindung der Geräte
                        Nearby.Connections.stopAdvertising(m_GoogleApiClient);

                        endpointIds.add(remoteEndpointId);
                        deviceIds.add(remoteDeviceId);

                        int i;
                        if (m_IsHost) {
                            String allIds = "";
                            for (i=0; i<endpointIds.size(); i++) {
                                allIds += endpointIds.get(i)+":"+deviceIds.get(i)+" ";
                            }
                            allIds = allIds.substring(0,allIds.length()-1);
                            Nearby.Connections.sendReliableMessage(m_GoogleApiClient, endpointIds, allIds.getBytes());
                        }

                        startActivity(new Intent(Lobby.this, Spielfeld2Host.class));
                        finish();

                    } else {
                        Toast.makeText(appContext, "Verbindung konnte nicht hergestellt werden." + remoteEndpointName,
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            // Verbindungsanfragen zu Clients werden unterbunden. Nur zu Hosts sollten
            // verbindungsanfragen versendet werden können.
            Nearby.Connections.rejectConnectionRequest(m_GoogleApiClient, remoteEndpointId);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View view) {
    }
}
