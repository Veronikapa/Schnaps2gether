package appsolutegamesgmbh.schnaps2gether.GUI;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.Connections;

import java.util.ArrayList;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl3;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel3;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spieler;
import appsolutegamesgmbh.schnaps2gether.R;

/**
 * Created by Kerstin on 23.05.2015.
 */
public class Spielfeld3Client extends Activity implements GameEnd.GameEndDialogListener, PopupMenu.OnMenuItemClickListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        Connections.ConnectionRequestListener,
        Connections.MessageListener,
        Connections.EndpointDiscoveryListener {

    //Konstanten für das Kennzeichnen und Parsen von Nachrichten
    private static final String KARTEGESPIELT = "0";
    private static final String WEITER = "1";
    private static final String PUNKTE = "2";
    private static final String ANGESAGT20ER = "4";
    private static final String ANGESAGT40ER = "5";
    private static final String TALONGETAUSCHT = "6";
    private static final String SPIELENDE = "7";
    private static final String BUMMERL = "8";
    private static final String HANDKARTEN = "9";
    //private static final String TRUMPFKARTE = "10";
    private static final String ZUGENDE = "11";
    private static final String DISCONNECT = "12";

    // Identify if the device is the host
    private boolean mIsHost = false;
    private static GoogleApiClient mGoogleApiClient;
    private static ArrayList<String> endpointIDs;

    private static Context appContext;

    private static Spiel3 spiel;

    private static ImageView imageView_karte1;
    private static ImageView imageView_karte2;
    private static ImageView imageView_karte3;
    private static ImageView imageView_karte4;
    private static ImageView imageView_karte5;

    private static ArrayList<ImageView> handkartenImages;

    private static ImageView imageView_trumpf;
    private static ImageView imageView_eigeneKarte;
    private static ImageView imageView_karteGegner;
    private static ImageView imageView_trumpfIcon;

    private static Button button20er;
    private static Button button40er;
    private static Button buttonTalonTauschen;
    private MenuItem herz20er;
    private MenuItem karo20er;
    private MenuItem pik20er;
    private MenuItem kreuz20er;
    private static Spieler selbst;
    private static Spieler gegner;
    private Karte karte1;
    private Karte karte2;
    private Karte karte3;
    private Karte karte4;
    private Karte karte5;
    private static Karte eigeneKarte;
    private static Karte gegnerischeKarte1;
    private static Karte gegnerischeKarte2;
    private static Karte trumpfkarte;
    private static TextView punkteGegner1;
    private static TextView punkteGegner2;
    private static TextView punkteSelbst;
    private TextView txtSelbst;
    private TextView txtGegner1;
    private TextView txtGegner2;
    private static Bummerl3 bummerl;

    private static int p1;
    private static int p2;
    private static int p3;

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spielfeld3);
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionRequest(String s, String s2, String s3, byte[] bytes) {

    }

    @Override
    public void onEndpointFound(String s, String s2, String s3, String s4) {

    }

    @Override
    public void onEndpointLost(String s) {

    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        startActivity(new Intent(Spielfeld3Client.this, Startmenue.class));
        finish();
    }

    @Override
    public void onMessageReceived(String s, byte[] bytes, boolean b) {

    }

    @Override
    public void onDisconnected(String s) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
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


    public static void receiveFromLobby(String endpointID, byte[] payload, boolean isReliable){

    }

    private static void punkteAktualisieren() {
        punkteGegner1.setText(Integer.toString(p2));
        punkteGegner2.setText(Integer.toString(p3));
        punkteSelbst.setText(Integer.toString(p1));
    }


    static class Zugende implements Runnable {

        @Override
        public void run() {
            punkteAktualisieren();
            gegnerischeKarte1 = null;
            gegnerischeKarte2 = null;
            eigeneKarte = null;
        }
    }

}
