package appsolutegamesgmbh.schnaps2gether.Network;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ArrayAdapter;
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
import java.util.Random;

import appsolutegamesgmbh.schnaps2gether.GUI.Spielfeld2Client;
import appsolutegamesgmbh.schnaps2gether.GUI.Spielfeld2Host;
import appsolutegamesgmbh.schnaps2gether.GUI.Spielfeld3Client;
import appsolutegamesgmbh.schnaps2gether.GUI.Spielfeld3Host;
import appsolutegamesgmbh.schnaps2gether.GUI.Spielfeld4Client;
import appsolutegamesgmbh.schnaps2gether.GUI.Spielfeld4Host;
import appsolutegamesgmbh.schnaps2gether.R;

public class NearbyConnectionService extends Service implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        Connections.ConnectionRequestListener,
        Connections.MessageListener,
        Connections.EndpointDiscoveryListener {
    public NearbyConnectionService() {
    }

    //Konstanten für das Kennzeichnen und Parsen von Nachrichten
    private static final String SPIELSTART = "16";
    private static final String CLIENT2 = "17";
    private static final String CLIENT3 = "18";
    private static final String CLIENT4 = "19";

    private Activity client;
    private Context appContext;
    // Legt fest ob das Gerät der Host ist
    private boolean m_IsHost = false;
    //Api Client der pro Gerät verfügbar sein muss
    public static GoogleApiClient m_GoogleApiClient;

    // Speichert die endpoint- und deviceIds von verbundenen Geräten
    public static ArrayList<String> endpointIds = new ArrayList<String>();
    public static ArrayList<String> deviceIds = new ArrayList<String>();

    //Geräte die sich verbinden wollen, müssen mit einem Wifi oder einem Ethernet verbunden sein
    private static int[] NETWORK_TYPES = {ConnectivityManager.TYPE_WIFI,
            ConnectivityManager.TYPE_ETHERNET};

    // Binder given to clients
    private final IBinder mBinder = new NearbyConnectionBinder();
    // Random number generator
    private final Random mGenerator = new Random();

    @Override
    public void onCreate() {
        appContext = this.getApplicationContext();

        //Beim Erstellen der Activity muss auch pro Gerät ein ApiClient für die Wifi Verbindung
        //angelegt werden
        m_GoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Nearby.CONNECTIONS_API)
                .build();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return START_STICKY;
    }

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class NearbyConnectionBinder extends Binder {
        NearbyConnectionService getService() {
            // Return this instance of LocalService so clients can call public methods
            return NearbyConnectionService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        intent.getComponent().getClassName();
        return mBinder;
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
    public void onEndpointFound(final String endpointId, String deviceId, String serviceId,
                                final String endpointName) {

        spielTyp = Integer.parseInt(endpointName.substring(0,1));
        String spielerName = endpointName.substring(1);

        //Neues Spiel zu Liste hinzufügen
        spieleListe.add(spielTyp+"er Schnapsen von "+spielerName);
        spieleIdListe.add(endpointId);

        adapterSpieleListView = new ArrayAdapter<String>(appContext,
                android.R.layout.simple_list_item_1, spieleListe);

        // Assign adapter to ListView
        spieleListView.setAdapter(adapterSpieleListView);
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
            if (message.equals(CLIENT2))
                isc1 = true;
            if (message.equals(CLIENT3) && !isc1)
                isc2 = true;
            if (message.equals(SPIELSTART)) {
                if (spielTyp == 2) {
                    startActivity(new Intent(Lobby.this, Spielfeld2Client.class));
                } else if (spielTyp == 3) {
                    startActivity(new Intent(Lobby.this, Spielfeld3Client.class));
                } else if (spielTyp == 4) {
                    startActivity(new Intent(Lobby.this, Spielfeld4Client.class));
                }
            } else if (spielTyp == 2) {
                c2.receiveFromLobby(endpointID, payload, isReliable);
            } else if (spielTyp == 3) {
                if (isc1)
                    c31.receiveFromLobby(endpointID, payload, isReliable);
                else
                    c32.receiveFromLobby(endpointID, payload, isReliable);
            } else if (spielTyp == 4)
                Spielfeld4Client.receiveFromLobby(endpointID, payload, isReliable);
        }
        else
        {
            if (spielTyp == 2) {
                h2.receiveFromLobby(endpointID, payload, isReliable);
            }

            else if (spielTyp == 3)
                h3.receiveFromLobby(endpointID, payload, isReliable);

            else if (spielTyp == 4)
                Spielfeld4Host.receiveFromLobby(endpointID, payload, isReliable);
        }
    }

    @Override
    public void onDisconnected(String s) {

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
        Nearby.Connections.startAdvertising(m_GoogleApiClient, spielTyp+spielerName, appMetadata, NO_TIMEOUT,
                this).setResultCallback(new ResultCallback<Connections.StartAdvertisingResult>() {
            @Override
            public void onResult(Connections.StartAdvertisingResult result) {
                if (result.getStatus().isSuccess()) {
                    //Neues Spiel zu Liste hinzufügen
                    spieleListe.add(spielTyp+"er Schnapsen von "+spielerName);

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
                            Toast.makeText(appContext, "Offene Spiele gefunden", Toast.LENGTH_SHORT).show();
                        }

                        //Es konnte kein Service in der Nähe gefunden werden.
                        else {
                            Toast.makeText(appContext, "Es konnten leider keine offenen Spiele gefunden werden.:(", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //Senden einer Verbindungsanfrage zu Host und Verbindung wenn möglich.
    private void connectTo(final String endpointId, final String endpointName) {
        byte[] myPayload = null;
        Nearby.Connections.sendConnectionRequest(m_GoogleApiClient, spielerName, endpointId, myPayload,
                new Connections.ConnectionResponseCallback() {
                    @Override
                    public void onConnectionResponse(String remoteEndpointId, Status status,
                                                     byte[] bytes) {
                        if (status.isSuccess()) {
                            Toast.makeText(appContext, "Geräte wurden verbunden! ", Toast.LENGTH_SHORT).show();
                            endpointIds.add(endpointId);

//                            if(endpointIds.size() == 1)
//                                isc1 = true;
//                            else if(endpointIds.size() == 2)
//                                isc2 = true;
//                            else if(endpointIds.size() == 3)
//                                isc3 = true;
                            /*if (spielTyp == 2 && endpointIds.size() == 1) {
                                startActivity(new Intent(Lobby.this, Spielfeld2Client.class));
                                finish();
                            } else if (spielTyp == 3 && endpointIds.size() == 2) {
                                startActivity(new Intent(Lobby.this, Spielfeld3Client.class));
                                finish();
                            }
                            else if (spielTyp == 4 && endpointIds.size() == 3) {
                                startActivity(new Intent(Lobby.this, Spielfeld4Client.class));
                                finish();
                            }*/
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

            Nearby.Connections.acceptConnectionRequest(m_GoogleApiClient, remoteEndpointId,
                    myPayload, this).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(Status status) {
                    if (status.isSuccess()) {
                        Toast.makeText(appContext, "Vebindung hergestellt zu " + remoteEndpointName,
                                Toast.LENGTH_SHORT).show();

                        endpointIds.add(remoteEndpointId);
                        deviceIds.add(remoteDeviceId);

                        if (endpointIds.size() == 1)
                            Nearby.Connections.sendReliableMessage(m_GoogleApiClient, endpointIds, CLIENT2.getBytes());
                        else if (endpointIds.size() == 2)
                            Nearby.Connections.sendReliableMessage(m_GoogleApiClient, endpointIds, CLIENT3.getBytes());
                        else if (endpointIds.size() == 3)
                            Nearby.Connections.sendReliableMessage(m_GoogleApiClient, endpointIds, CLIENT4.getBytes());

                        if (spielTyp == 2 && endpointIds.size() == 1) {
                            //Beenden der Service anzeige nach Verbindung der Geräte
                            Nearby.Connections.stopAdvertising(m_GoogleApiClient);

                            Nearby.Connections.sendReliableMessage(m_GoogleApiClient, endpointIds, SPIELSTART.getBytes());
                            startActivity(new Intent(Lobby.this, Spielfeld2Host.class));
                            finish();
                        } else if (spielTyp == 3 && endpointIds.size() == 2) {
                            //Beenden der Service anzeige nach Verbindung der Geräte
                            Nearby.Connections.stopAdvertising(m_GoogleApiClient);

                            Nearby.Connections.sendReliableMessage(m_GoogleApiClient, endpointIds, SPIELSTART.getBytes());
                            startActivity(new Intent(Lobby.this, Spielfeld3Host.class));
                            finish();
                        } else if (spielTyp == 4 && endpointIds.size() == 3) {
                            //Beenden der Service anzeige nach Verbindung der Geräte
                            Nearby.Connections.stopAdvertising(m_GoogleApiClient);

                            Nearby.Connections.sendReliableMessage(m_GoogleApiClient, endpointIds, SPIELSTART.getBytes());
                            startActivity(new Intent(Lobby.this, Spielfeld4Host.class));
                            finish();
                        }

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

    /** method for clients */
    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }

    public void setClient(Activity client) {
        this.client = client;
    }

    @Override
    public void onDestroy() {
    }
}
