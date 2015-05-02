package appsolutegamesgmbh.schnaps2gether.GUI;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
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

    // Legt fest ob das Gerät der Host ist
    private boolean m_IsHost = false;
    private GoogleApiClient m_GoogleApiClient;

    private static int[] NETWORK_TYPES = {ConnectivityManager.TYPE_WIFI,
            ConnectivityManager.TYPE_ETHERNET};

    // Request code to use when launching the resolution activity
    private static final int REQUEST_RESOLVE_ERROR = 1001;
    // Unique tag for the error dialog fragment
    private static final String DIALOG_ERROR = "dialog_error";
    // Bool to track whether the app is already resolving an error
    private boolean mResolvingError = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        appContext = getApplicationContext();

        m_GoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Nearby.CONNECTIONS_API)
                .build();

        mResolvingError = savedInstanceState != null
                && savedInstanceState.getBoolean(STATE_RESOLVING_ERROR, false);
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

    public void neu(View v) {
        startActivity(new Intent(Lobby.this, NeuesSpiel.class));
        //finish();
        //TODO VP: Überprüfen ob 2, 3 oder 4 Spieler
        if(m_GoogleApiClient.isConnected())
            startAdvertising();
    }

    public void beitreten(View v)
    {

    }

    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(appContext,"GoogleApiConnection succeeded.",Toast.LENGTH_SHORT);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onEndpointFound(String s, String s2, String s3, String s4) {

    }

    @Override
    public void onEndpointLost(String s) {

    }

    @Override
    public void onMessageReceived(String s, byte[] bytes, boolean b) {

    }

    @Override
    public void onDisconnected(String s) {

    }

    @Override
    public void onClick(View view) {

    }


    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (mResolvingError) {
            // Already attempting to resolve an error.
            return;
        } else if (result.hasResolution()) {
            try {
                mResolvingError = true;
                result.startResolutionForResult(this, REQUEST_RESOLVE_ERROR);
            } catch (IntentSender.SendIntentException e) {
                // There was an error with the resolution intent. Try again.
                m_GoogleApiClient.connect();
            }
        } else {
            // Show dialog using GooglePlayServicesUtil.getErrorDialog()
            showErrorDialog(result.getErrorCode());
            mResolvingError = true;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        m_GoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (m_GoogleApiClient != null && m_GoogleApiClient.isConnected()) {
            m_GoogleApiClient.disconnect();
        }
    }

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

        if (!this.isConnectedToNetwork()) {
            // Implement logic when device is not connected to a network
            Toast.makeText(appContext,"Sie sind mit keinem Netzwerk verbunden.",Toast.LENGTH_SHORT);
        }

        // Identify that this device is the host
        m_IsHost = true;

        // Advertising with an AppIdentifer lets other devices on the
        // network discover this application and prompt the user to
        // install the application.
        List<AppIdentifier> appIdentifierList = new ArrayList<>();
        appIdentifierList.add(new AppIdentifier(getPackageName()));
        AppMetadata appMetadata = new AppMetadata(appIdentifierList);

        // The advertising timeout is set to run indefinitely
        // Positive values represent timeout in milliseconds
        long NO_TIMEOUT = 0L;

        String name = "Veronika";
                //ChangeNickname.editNickname.getText().toString();
        Nearby.Connections.startAdvertising(m_GoogleApiClient, name, appMetadata, NO_TIMEOUT,
                this).setResultCallback(new ResultCallback<Connections.StartAdvertisingResult>() {
            @Override
            public void onResult(Connections.StartAdvertisingResult result) {
                if (result.getStatus().isSuccess()) {
                    Toast.makeText(appContext,"Verbindung wurde erfolgreich angelegt.",Toast.LENGTH_SHORT);
                } else {
                    int statusCode = result.getStatus().getStatusCode();
                    Toast.makeText(appContext,"Verbindung konnte nicht angelegt werden.",Toast.LENGTH_SHORT);
                }
            }
        });
    }

    private void startDiscovery() {
        if (!isConnectedToNetwork()) {
            // Implement logic when device is not connected to a network
        }
        String serviceId = getString(R.string.service_id);

        // Set an appropriate timeout length in milliseconds
        long DISCOVER_TIMEOUT = 1000L;

        // Discover nearby apps that are advertising with the required service ID.
        Nearby.Connections.startDiscovery(m_GoogleApiClient, serviceId, DISCOVER_TIMEOUT, this)
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        if (status.isSuccess()) {
                            // Device is discovering
                        } else {
                            // Advertising failed - see statusCode for more details
                        }

                    }
                });
    }
    private void connectTo(String endpointId, final String endpointName) {

        // Send a connection request to a remote endpoint. By passing 'null' for the name,
        // the Nearby Connections API will construct a default name based on device model
        // such as 'LGE Nexus 5'.
        String myName = null;
        byte[] myPayload = null;
        Nearby.Connections.sendConnectionRequest(m_GoogleApiClient, myName, endpointId, myPayload,
                new Connections.ConnectionResponseCallback() {
                    @Override
                    public void onConnectionResponse(String remoteEndpointId, Status status,
                                                     byte[] bytes) {
                        if (status.isSuccess()) {
                            // Successful connection
                        } else {
                            // Failed connection
                        }
                    }
                }, this);
    }

    @Override
    public void onConnectionRequest(final String remoteEndpointId, String remoteDeviceId,
                                    final String remoteEndpointName, byte[] payload) {
        if (m_IsHost) {
            byte[] myPayload = null;
            // Automatically accept all requests
            Nearby.Connections.acceptConnectionRequest(m_GoogleApiClient, remoteEndpointId,
                    myPayload, this).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(Status status) {
                    if (status.isSuccess()) {
                        Toast.makeText(appContext,"Connected to " + remoteEndpointName,
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(appContext, "Failed to connect to: " + remoteEndpointName,
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            // Clients should not be advertising and will reject all connection requests.
            Nearby.Connections.rejectConnectionRequest(m_GoogleApiClient, remoteEndpointId);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_RESOLVE_ERROR) {
            mResolvingError = false;
            if (resultCode == RESULT_OK) {
                // Make sure the app is not already connected or attempting to connect
                if (!m_GoogleApiClient.isConnecting() &&
                        !m_GoogleApiClient.isConnected()) {
                    m_GoogleApiClient.connect();
                }
            }
        }
    }
    private static final String STATE_RESOLVING_ERROR = "resolving_error";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_RESOLVING_ERROR, mResolvingError);
    }

    /* Creates a dialog for an error message */
    private void showErrorDialog(int errorCode) {
        // Create a fragment for the error dialog
        ErrorDialogFragment dialogFragment = new ErrorDialogFragment();
        // Pass the error that should be displayed
        Bundle args = new Bundle();
        args.putInt(DIALOG_ERROR, errorCode);
        dialogFragment.setArguments(args);
        dialogFragment.show(getFragmentManager(), "errordialog");
    }

    /* Called from ErrorDialogFragment when the dialog is dismissed. */
    public void onDialogDismissed() {
        mResolvingError = false;
    }

    /* A fragment to display an error dialog */
    public static class ErrorDialogFragment extends DialogFragment {
        public ErrorDialogFragment() { }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Get the error code and retrieve the appropriate dialog
            int errorCode = this.getArguments().getInt(DIALOG_ERROR);
            return GooglePlayServicesUtil.getErrorDialog(errorCode,
                    this.getActivity(), REQUEST_RESOLVE_ERROR);
        }

        @Override
        public void onDismiss(DialogInterface dialog) {
            ((Lobby)getActivity()).onDialogDismissed();
        }
    }

}
