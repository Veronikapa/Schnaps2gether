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
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.Connections;

import java.util.ArrayList;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spieler;
import appsolutegamesgmbh.schnaps2gether.R;

/**
 * Created by kirederf on 06.05.15.
 */
public class Spielfeld2Host extends Activity implements GameEnd.GameEndDialogListener, PopupMenu.OnMenuItemClickListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        Connections.ConnectionRequestListener,
        Connections.MessageListener,
        Connections.EndpointDiscoveryListener {

    //Konstanten für das Kennzeichnen und Parsen von Nachrichten
    private static final String KARTEGESPIELT = "0";
    private static final String WEITER = "1";
    private static final String PUNKTE = "2";
    private static final String ZUGEDREHT = "3";
    private static final String ANGESAGT20ER = "4";
    private static final String ANGESAGT40ER = "5";
    private static final String TRUMPFGETAUSCHT = "6";
    private static final String SPIELENDE = "7";
    private static final String BUMMERL = "8";
    private static final String HANDKARTEN = "9";
    private static final String TRUMPFKARTE = "10";
    private static final String ZUGENDE = "11";
    private static final String DISCONNECT = "12";

    // Identify if the device is the host
    private boolean mIsHost = true;
    private static GoogleApiClient mGoogleApiClient;
    private static ArrayList<String> endpointIDs;

    private static Context appContext;

    private static Spiel2 spiel;
    private Button buttonKarte1;
    private Button buttonKarte2;
    private Button buttonKarte3;
    private Button buttonKarte4;
    private Button buttonKarte5;
    private static ArrayList<Button> handkartenButtons;
    private static Button buttonEigeneKarte;
    private static Button buttonGegnerischeKarte;
    private static Button buttonStapel;
    private Button buttonTrumpfkarte;
    private static Button buttonZudrehen;
    private static Button button20er;
    private static Button button40er;
    private static Button buttonTrumpfTauschen;
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
    private static Karte gegnerischeKarte;
    private Karte trumpfkarte;
    private static TextView punkteGegner;
    private static TextView punkteSelbst;
    private TextView txtSelbst;
    private TextView txtGegner;
    private static Bummerl2 bummerl;

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
        setContentView(R.layout.activity_spielfeld2);

        mGoogleApiClient = Lobby.m_GoogleApiClient;
        endpointIDs = Lobby.endpointIds;
        endpointIDs.remove(Nearby.Connections.getLocalEndpointId(mGoogleApiClient));

        appContext = this.getApplicationContext();

        bummerl = new Bummerl2();
       Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (BUMMERL+":"+bummerl.toString()).getBytes());

        buttonKarte1 = (Button) findViewById(R.id.main_button1);
        buttonKarte2 = (Button) findViewById(R.id.main_button2);
        buttonKarte3 = (Button) findViewById(R.id.main_button3);
        buttonKarte4 = (Button) findViewById(R.id.main_button4);
        buttonKarte5 = (Button) findViewById(R.id.main_button5);
        buttonZudrehen = (Button) findViewById(R.id.main_buttonZ);
        button20er = (Button) findViewById(R.id.main_button20er);
        button40er = (Button) findViewById(R.id.main_button40er);
        buttonTrumpfTauschen = (Button) findViewById(R.id.main_buttonTtauschen);

        punkteGegner = (TextView) findViewById(R.id.pointsText);
        punkteSelbst = (TextView) findViewById(R.id.pointsText2);
        txtSelbst = (TextView) findViewById(R.id.I);
        txtGegner = (TextView) findViewById(R.id.Enemy);
        buttonEigeneKarte = (Button) findViewById(R.id.main_buttonI);
        buttonGegnerischeKarte = (Button) findViewById(R.id.main_buttonE);
        buttonTrumpfkarte = (Button) findViewById(R.id.main_buttonT);
        buttonStapel = (Button) findViewById(R.id.main_buttonD);

        handkartenButtons = new ArrayList<Button>();
        handkartenButtons.add(0, buttonKarte1);
        handkartenButtons.add(1, buttonKarte2);
        handkartenButtons.add(2, buttonKarte3);
        handkartenButtons.add(3, buttonKarte4);
        handkartenButtons.add(4, buttonKarte5);

        spielStart();
    }
    private void zugAusführen(int i) {
        final Karte k = selbst.Hand.get(i);
        eigeneKarte = k;
        buttonsNichtKlickbar();
        spiel.Auspielen(k, selbst);
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (KARTEGESPIELT + ":" + k.toString()).getBytes());
        gespielteKarteEntfernen(i);
        buttonEigeneKarte.setText(k.getFarbe() + k.getWertigkeit());
        if (gegnerischeKarte == null) {
            Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (WEITER+":"+0).getBytes());
        } else {
            zugEnde();
        }
    }

    private static void zugEnde() {
        spiel.ZugAuswerten(eigeneKarte, gegnerischeKarte);
        eigeneKarte = gegnerischeKarte = null;
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (ZUGENDE+":").getBytes());
        // Execute some code after 2 seconds have passed
        Handler handler = new Handler();
        handler.postDelayed(new Zugende(), 2000);
    }

    private static void eigenerZug() {
        if (!spiel.isZugedreht()) {
            buttonZudrehen.setEnabled(true);

            if (selbst.Hand.contains(new Karte(spiel.getTrumpf(),"Bube",2))) {
                buttonTrumpfTauschen.setEnabled(true);
            }
            else {
                buttonTrumpfTauschen.setEnabled(false);
            }
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
    }

    private static void handAktualisieren() {
        int handkartenAnz = selbst.Hand.size();
        for (int i=0;i<5;i++) {
            Button buttonK = handkartenButtons.get(i);
            if (i<handkartenAnz) {
                Karte k = selbst.Hand.get(i);
                buttonK.setText(k.getFarbe() + k.getWertigkeit());
                buttonK.setVisibility(View.VISIBLE);
            } else {
                buttonK.setVisibility(View.INVISIBLE);
            }
            gegnerischeHandAktualisieren();
        }
    }

    private static void gegnerischeHandAktualisieren() {
        String gegnerischeHand = "";
        String gegKartenSpielBar = "";
        int gegnerischeHandkartenAnz = gegner.Hand.size();
        for (int i=0;i<gegnerischeHandkartenAnz;i++) {
            gegnerischeHand += ","+gegner.Hand.get(i).toString();
            gegKartenSpielBar += " "+(spiel.DarfKarteAuswaehlen(eigeneKarte, gegner.Hand.get(i)) ? 1 : 0);
        }
        int stapelKartenAnz = spiel.AnzahlKartenStapel() == 0 ? 0 : spiel.AnzahlKartenStapel()+1;
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (HANDKARTEN+":"+stapelKartenAnz+":"+gegnerischeHand+":"+gegKartenSpielBar).getBytes());
    }

    private static void punkteAktualisieren() {
        buttonEigeneKarte.setText("");
        buttonGegnerischeKarte.setText("");
        int p1 = selbst.getPunkte();
        int p2 = gegner.getPunkte();
        punkteGegner.setText(Integer.toString(p2));
        punkteSelbst.setText(Integer.toString(p1));
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (PUNKTE+":"+Integer.toString(p1)+" "+Integer.toString(p2)).getBytes());
    }

    private void gespielteKarteEntfernen(int i) {
        handkartenButtons.get(i).setVisibility(View.INVISIBLE);
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

    private static void gegnerHat20er() {
        int hast20er = hat20er(gegner) ? 1 : 0;
        int hast40er = hat40er(gegner) ? 1 : 0;
        String hastDie20er = "";
        ArrayList<String> geg20er = spiel.hat20er(gegner);
        for(String farbe: geg20er) {
            hastDie20er += " "+farbe;
        }
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (WEITER + ":" + 1 + " " + hast20er + " " + hast40er + hastDie20er).getBytes());
    }

    private void spielStart() {
        Handler handler0 = new Handler();
        handler0.postDelayed(new Runnable() {
            @Override
            public void run() {
                spiel = new Spiel2(bummerl.getAnzahlSpiele());
                selbst = spiel.getS1();
                gegner = spiel.getS2();

                Toast.makeText(appContext, "OwnEndpointID: "+Nearby.Connections.getLocalEndpointId(mGoogleApiClient), Toast.LENGTH_LONG).show();
                Handler handler3  =  new Handler();
                handler3.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(appContext, "ClientEndpointID: " + endpointIDs.get(0), Toast.LENGTH_LONG).show();
                    }
                }, 2000);

                buttonStapel.setText("20");
                trumpfkarte = spiel.getAufgedeckterTrumpf();
                buttonTrumpfkarte.setText(trumpfkarte.getFarbe() + trumpfkarte.getWertigkeit());
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TRUMPFKARTE + ":" + trumpfkarte.toString()).getBytes());

                handKartenKlickbar();
                buttonZudrehen.setEnabled(true);
                buttonZudrehen.setText(R.string.buttonZ);
                buttonEigeneKarte.setText("");
                buttonGegnerischeKarte.setText("");
                punkteSelbst.setText("0");
                punkteGegner.setText("0");
                gegnerischeKarte = null;
                handAktualisieren();
                eigenerZug();
            }
        }, 2000);
    }

    private void buttonsNichtKlickbar() {
        buttonKarte1.setEnabled(false);
        buttonKarte2.setEnabled(false);
        buttonKarte3.setEnabled(false);
        buttonKarte4.setEnabled(false);
        buttonKarte5.setEnabled(false);
        button20er.setEnabled(false);
        button40er.setEnabled(false);
        buttonZudrehen.setEnabled(false);
        buttonTrumpfTauschen.setEnabled(false);
    }

    private static void handKartenKlickbar() {
        int handkartenAnz = selbst.Hand.size();
        for (int i=0;i<5;i++) {
            Button buttonK = handkartenButtons.get(i);
            if (i<handkartenAnz && spiel.DarfKarteAuswaehlen(gegnerischeKarte, selbst.Hand.get(i))) {
                buttonK.setEnabled(true);
            } else {
                buttonK.setEnabled(false);
            }
        }
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

    public void zudrehen(View view) {
        spiel.Zudrehen();
        buttonZudrehen.setEnabled(false);
        buttonZudrehen.setText("Zugedreht");
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (ZUGEDREHT+":").getBytes());
    }

    public void popup20er(View view) {
        PopupMenu popup = new PopupMenu(Spielfeld2Host.this, button20er);
        popup.inflate(R.menu.popup_menu_20er);
        herz20er = (MenuItem) popup.getMenu().getItem(0);
        karo20er = (MenuItem) popup.getMenu().getItem(1);
        pik20er = (MenuItem) popup.getMenu().getItem(2);
        kreuz20er = (MenuItem) popup.getMenu().getItem(3);
        herz20er.setVisible(false);
        karo20er.setVisible(false);
        pik20er.setVisible(false);
        kreuz20er.setVisible(false);
        ArrayList<String> a = spiel.hat20er(selbst);
        for (int i = 0; i < a.size(); i++) {
            switch (a.get(i)) {
                case "Herz":
                    herz20er.setVisible(true);
                    break;
                case "Karo":
                    karo20er.setVisible(true);
                    break;
                case "Pik":
                    pik20er.setVisible(true);
                    break;
                case "Kreuz":
                    kreuz20er.setVisible(true);
                    break;
                default:
                    ;
            }
        }
        popup.setOnMenuItemClickListener(this);
        popup.show();
    }

    public void ansagen40er(View view) {
        spiel.Ansagen20er(spiel.getTrumpf(), selbst);
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (ANGESAGT40ER+":").getBytes());
        if (spiel.istSpielzuEnde(bummerl)) spielEnde();
        punkteAktualisieren();
        button40er.setEnabled(false);
        button20er.setEnabled(false);
        handKartenKlickbar();
    }

    public void trumpfkarteTauschen(View view) {
        spiel.TrumpfkarteAustauschen(new Karte(spiel.getTrumpf(),"Bube",2), selbst);
        trumpfkarte = spiel.getAufgedeckterTrumpf();
        buttonTrumpfkarte.setText(trumpfkarte.getFarbe() + trumpfkarte.getWertigkeit());
        handAktualisieren();
        buttonTrumpfTauschen.setEnabled(false);
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TRUMPFGETAUSCHT+":").getBytes());
        eigenerZug();
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

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        spielStart();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        startActivity(new Intent(Spielfeld2Host.this, Startmenue.class));
        finish();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.herz_20er:
                spiel.Ansagen20er("Herz", selbst);
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (ANGESAGT20ER + ":" +"Herz").getBytes());
                break;
            case R.id.karo_20er:
                spiel.Ansagen20er("Karo", selbst);
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (ANGESAGT20ER + ":" +"Karo").getBytes());
                break;
            case R.id.pik_20er:
                spiel.Ansagen20er("Pik", selbst);
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (ANGESAGT20ER + ":" +"Pik").getBytes());
                break;
            case R.id.kreuz_20er:
                spiel.Ansagen20er("Kreuz", selbst);
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (ANGESAGT20ER + ":" +"Kreuz").getBytes());
                break;
            default:
                return false;
        }
        button20er.setEnabled(false);
        button40er.setEnabled(false);
        if (spiel.istSpielzuEnde(bummerl)) spielEnde();
        punkteAktualisieren();
        handKartenKlickbar();
        return true;
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public static void receiveFromLobby(String endpointID, byte[] payload, boolean isReliable)
    {
        String message = new String(payload);
        switch (((message.split(":")[0]))) {
            case KARTEGESPIELT: gegnerischeKarte = new Karte(message.substring(2));
                spiel.Auspielen(gegnerischeKarte, gegner);
                buttonGegnerischeKarte.setText(gegnerischeKarte.getFarbe() + gegnerischeKarte.getWertigkeit());
                if (eigeneKarte!=null) {
                    zugEnde();
                }
                break;
            case WEITER: handKartenKlickbar();
                if (message.substring(2,3).equals("1")) {
                    eigenerZug();
                }
                Toast.makeText(appContext, "Weiter", Toast.LENGTH_SHORT).show();
                break;
            case ZUGEDREHT: spiel.Zudrehen();
                buttonZudrehen.setEnabled(false);
                buttonZudrehen.setText("Zugedreht");
                Toast.makeText(appContext, "Zugedreht", Toast.LENGTH_SHORT).show();
                break;
            case ANGESAGT40ER: spiel.Ansagen20er(spiel.getTrumpf(), gegner);
                punkteAktualisieren();
                if (spiel.istSpielzuEnde(bummerl)) {
                    spielEnde();
                }
                gegnerischeHandAktualisieren();
                Toast.makeText(appContext, "40er angesagt", Toast.LENGTH_SHORT).show();
                break;
            case ANGESAGT20ER: String farbe = message.substring(2);
                spiel.Ansagen20er(farbe, gegner);
                punkteAktualisieren();
                if (spiel.istSpielzuEnde(bummerl)) {
                    spielEnde();
                }
                gegnerischeHandAktualisieren();
                Toast.makeText(appContext, farbe+" 20er angesagt", Toast.LENGTH_SHORT).show();
                break;
            case TRUMPFGETAUSCHT: spiel.TrumpfkarteAustauschen(new Karte(spiel.getTrumpf(),"Bube",2), gegner);
                gegnerischeHandAktualisieren();
                gegnerHat20er();
                Toast.makeText(appContext, "Trumpfkarte ausgetauscht", Toast.LENGTH_SHORT).show();
                break;
            default: break;
        }
    }
    @Override
    public void onMessageReceived(String endpointID, byte[] payload, boolean isReliable) {
        String message = new String(payload);
        switch ((message.substring(0,1))) {
            case KARTEGESPIELT: gegnerischeKarte = new Karte(message.substring(2));
                spiel.Auspielen(gegnerischeKarte, gegner);
                buttonGegnerischeKarte.setText(gegnerischeKarte.getFarbe() + gegnerischeKarte.getWertigkeit());
                if (eigeneKarte!=null) {
                    zugEnde();
                }
                break;
            case WEITER: handKartenKlickbar();
                if (message.substring(2,3).equals("1")) {
                    eigenerZug();
                }
                Toast.makeText(appContext, "Weiter", Toast.LENGTH_SHORT).show();
                break;
            case ZUGEDREHT: spiel.Zudrehen();
                buttonZudrehen.setEnabled(false);
                buttonZudrehen.setText("Zugedreht");
                Toast.makeText(appContext, "Zugedreht", Toast.LENGTH_SHORT).show();
                break;
            case ANGESAGT40ER: spiel.Ansagen20er(spiel.getTrumpf(), gegner);
                punkteAktualisieren();
                if (spiel.istSpielzuEnde(bummerl)) {
                    spielEnde();
                }
                gegnerischeHandAktualisieren();
                Toast.makeText(appContext, "40er angesagt", Toast.LENGTH_SHORT).show();
                break;
            case ANGESAGT20ER: String farbe = message.substring(2);
                spiel.Ansagen20er(farbe, gegner);
                punkteAktualisieren();
                if (spiel.istSpielzuEnde(bummerl)) {
                    spielEnde();
                }
                gegnerischeHandAktualisieren();
                Toast.makeText(appContext, farbe+" 20er angesagt", Toast.LENGTH_SHORT).show();
                break;
            case TRUMPFGETAUSCHT: spiel.TrumpfkarteAustauschen(new Karte(spiel.getTrumpf(),"Bube",2), gegner);
                gegnerischeHandAktualisieren();
                gegnerHat20er();
                Toast.makeText(appContext, "Trumpfkarte ausgetauscht", Toast.LENGTH_SHORT).show();
                break;
            default: break;
        }

    }

    @Override
    public void onDisconnected(String s) {
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (DISCONNECT+":").getBytes());
        Toast.makeText(appContext, "Verbindungsverlust eines Spielers - Das Spiel wird beendet...", Toast.LENGTH_SHORT).show();
        // Execute some code after 2 seconds have passed
        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2000);
    }

    @Override
    public void onEndpointFound(String s, String s1, String s2, String s3) {

    }

    @Override
    public void onEndpointLost(String s) {

    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionRequest(String s, String s1, String s2, byte[] bytes) {

    }

    static class Zugende implements Runnable {

        @Override
        public void run() {
            punkteAktualisieren();
            gegnerischeKarte = eigeneKarte = null;
            if (spiel.istSpielzuEnde(bummerl)) {
                spielEnde();
            } else {
                handAktualisieren();
                if(spiel.AnzahlKartenStapel()!=0)
                    buttonStapel.setText(Integer.toString(spiel.AnzahlKartenStapel()+1));
                else
                    buttonStapel.setText("0");
                if (selbst.isIstdran()) {
                    eigenerZug();
                    handKartenKlickbar();
                } else {
                    gegnerHat20er();
                }
            }
        }
    }
}
