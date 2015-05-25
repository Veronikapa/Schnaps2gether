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

public class Spielfeld3Host extends Activity implements GameEnd.GameEndDialogListener, PopupMenu.OnMenuItemClickListener,
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
    private boolean mIsHost = true;
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
    private static Spieler gegner1;
    private static Spieler gegner2;
    private Karte karte1;
    private Karte karte2;
    private Karte karte3;
    private Karte karte4;
    private Karte karte5;
    private static Karte eigeneKarte;
    private static Karte gegnerischeKarte1;
    private static Karte gegnerischeKarte2;
    private static TextView punkteGegner1;
    private static TextView punkteGegner2;
    private static TextView punkteSelbst;
    private TextView txtSelbst;
    private TextView txtGegner1;
    private TextView txtGegner2;
    private static Bummerl3 bummerl;

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
        startActivity(new Intent(Spielfeld3Host.this, Startmenue.class));
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

    public void karte1OnClick(View view) {
        zugAusführen(0);
    }

    public void karte2OnClick(View view) {
        zugAusführen(1);
    }

    public void karte3OnClick(View view) {
        zugAusführen(2);
    }

    public void karte4OnClick(View view) {
        zugAusführen(3);
    }

    public void karte5OnClick(View view) {
        zugAusführen(4);
    }

    private void zugAusführen(int i) {
        final Karte k = selbst.Hand.get(i);
        eigeneKarte = k;
        buttonsNichtKlickbar();
        spiel.Auspielen(k, selbst);
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (KARTEGESPIELT + ":" + k.toString()).getBytes());
        gespielteKarteEntfernen(i);

        //buttonEigeneKarte.setText(k.getFarbe() + k.getWertigkeit());
        imageView_eigeneKarte.setImageResource(k.getImageResourceId());


        if (gegnerischeKarte1 == null) {
            gegnerischeHandAktualisieren();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (WEITER + ":" + 0).getBytes());
                }
            }, 1000);
        } else {
            zugEnde();
        }
    }

    private static void zugEnde() {
        spiel.ZugAuswerten(eigeneKarte, gegnerischeKarte1, gegnerischeKarte2);
        eigeneKarte = null;
        gegnerischeKarte1 = null;
        gegnerischeKarte2 = null;
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (ZUGENDE+":").getBytes());
        // Execute some code after 2 seconds have passed
        Handler handler = new Handler();
        handler.postDelayed(new Zugende(), 2000);
    }


    private void gespielteKarteEntfernen(int i) {
        handkartenImages.get(i).setVisibility(View.INVISIBLE);
    }

    private void buttonsNichtKlickbar() {
        imageView_karte1.setEnabled(false);
        imageView_karte2.setEnabled(false);
        imageView_karte3.setEnabled(false);
        imageView_karte4.setEnabled(false);
        imageView_karte5.setEnabled(false);

        button20er.setEnabled(false);
        button40er.setEnabled(false);
    }

    private static void handAktualisieren() {
        int handkartenAnz = selbst.Hand.size();
        for (int i=0;i<5;i++) {
            //Button buttonK = handkartenButtons.get(i);
            ImageView imageViewK = handkartenImages.get(i);


            if (i<handkartenAnz) {
                Karte k = selbst.Hand.get(i);


              /*  buttonK.setText(k.getFarbe() + k.getWertigkeit());
                buttonK.setVisibility(View.VISIBLE);*/

                imageViewK.setImageResource(k.getImageResourceId());
                imageViewK.setVisibility(View.VISIBLE);
            } else {
                //buttonK.setVisibility(View.INVISIBLE);
                imageViewK.setVisibility(View.INVISIBLE);
            }
            gegnerischeHandAktualisieren();
        }
    }

    private static void gegnerischeHandAktualisieren() {
        String gegnerischeHand1 = "";
        String gegKartenSpielBar1 = "";
        String gegnerischeHand2 = "";
        String gegKartenSpielBar2 = "";
        int gegnerischeHandkartenAnz1 = gegner1.Hand.size();
        int gegnerischeHandkartenAnz2 = gegner2.Hand.size();
        //Toast.makeText(appContext,"gegnerischeHandkartenAnz: "+gegnerischeHandkartenAnz,Toast.LENGTH_SHORT).show();
        
        for (int i=0;i<gegnerischeHandkartenAnz1;i++) {
            //Toast.makeText(appContext,"gegnerischeHandkarte "+i+": "+gegner.Hand.get(i).toString(),Toast.LENGTH_SHORT).show();
            gegnerischeHand1 += ","+gegner1.Hand.get(i).toString();
            gegKartenSpielBar1 += " "+(spiel.DarfKarteAuswaehlen(gegner1.Hand.get(i), gegner1) ? 1 : 0);
        }
        for (int i=0;i<gegnerischeHandkartenAnz2;i++) {
            //Toast.makeText(appContext,"gegnerischeHandkarte "+i+": "+gegner.Hand.get(i).toString(),Toast.LENGTH_SHORT).show();
            gegnerischeHand2 += ","+gegner2.Hand.get(i).toString();
            gegKartenSpielBar2 += " "+(spiel.DarfKarteAuswaehlen(gegner2.Hand.get(i), gegner1) ? 1 : 0);
        }
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (HANDKARTEN+":"+gegnerischeHand1+":"+gegnerischeHand2+":"+gegKartenSpielBar1+":"+gegKartenSpielBar2).getBytes());
    }
    

    static class Zugende implements Runnable {

    @Override
    public void run() {
            punkteAktualisieren();
            gegnerischeKarte1 = null;
            gegnerischeKarte2 = null;
            eigeneKarte = null;
            if (spiel.istSpielzuEnde(bummerl)) {
                spielEnde();
            } else {
                handAktualisieren();
                if (selbst.isIstdran()) {
                    eigenerZug();
                    handKartenKlickbar();
                } else {
                    gegner1hat20er();
                    gegner2hat20er();
                }
            }
        }
    }

    private static void eigenerZug() {
            
        if(hat20er(selbst)) {
            button20er.setEnabled(true);
        }
        else {               
            button20er.setEnabled(false);
        }
        if(hat40er(selbst)) {
            button40er.setEnabled(true);
        }
        else {
            button40er.setEnabled(false);
        }
    }

    private static boolean hat20er(Spieler s) {
        if ((spiel.hat20er(s).size()>0 && !hat40er(s)) || spiel.hat20er(s).size()>1)
            return true;
        return false;
    }

    private static boolean hat40er(Spieler s) {
        if(spiel.hat20er(s).contains(spiel.getTrumpf()))
            return true;
        return false;
    }

    private static void gegner1hat20er() {
        int hast20er = hat20er(gegner1) ? 1 : 0;
        int hast40er = hat40er(gegner1) ? 1 : 0;
        String hastDie20er = "";
        ArrayList<String> geg20er = spiel.hat20er(gegner1);
        for(String farbe: geg20er) {
            hastDie20er += " " + farbe;
        }
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (WEITER + ":" + 1 + " " + hast20er + " " + hast40er + hastDie20er).getBytes());
    }

    private static void gegner2hat20er() {
        int hast20er = hat20er(gegner2) ? 1 : 0;
        int hast40er = hat40er(gegner2) ? 1 : 0;
        String hastDie20er = "";
        ArrayList<String> geg20er = spiel.hat20er(gegner2);
        for(String farbe: geg20er) {
            hastDie20er += " " + farbe;
        }
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (WEITER + ":" + 1 + " " + hast20er + " " + hast40er + hastDie20er).getBytes());
    }
    


    private static void punkteAktualisieren() {
        int p1 = selbst.getPunkte();
        int p2 = gegner1.getPunkte();
        int p3 = gegner2.getPunkte();
        punkteGegner1.setText(Integer.toString(p2));
        punkteGegner2.setText(Integer.toString(p3));
        punkteSelbst.setText(Integer.toString(p1));
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (PUNKTE + ":" + Integer.toString(p1) + " " + Integer.toString(p2) + " " + Integer.toString(p3)).getBytes());
    }


    private static void spielEnde() {
        boolean win = true;
        if (selbst.getPunkte()<66) {
            win = false;
        }
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIELENDE+":"+(win ? 0 : 1)).getBytes());
        Bundle args = new Bundle();
        args.putBoolean("win", win);
        DialogFragment gameEndDialogFragment = new GameEnd();
        gameEndDialogFragment.setArguments(args);
        // gameEndDialogFragment.show(getFragmentManager(), "GameEnd");
    }

    private static void handKartenKlickbar() {
        int handkartenAnz = selbst.Hand.size();
        for (int i=0;i<5;i++) {

            ImageView imageViewK = handkartenImages.get(i);

            if (i<handkartenAnz && spiel.DarfKarteAuswaehlen(selbst.Hand.get(i), selbst)) {
                imageViewK.setEnabled(true);
                imageViewK.setAlpha(1f);
            } else {
                imageViewK.setEnabled(false);
                imageViewK.setAlpha(0.2f);
            }
        }
    }

    
    


}