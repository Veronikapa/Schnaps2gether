package appsolutegamesgmbh.schnaps2gether.GUI;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
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
public class Spielfeld2Client extends Activity implements GameEnd.GameEndDialogListener, PopupMenu.OnMenuItemClickListener,
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

    private Spiel2 spiel;
    private Button buttonKarte1;
    private Button buttonKarte2;
    private Button buttonKarte3;
    private Button buttonKarte4;
    private Button buttonKarte5;
    private ArrayList<Button> handkartenButtons;
    private Button buttonEigeneKarte;
    private Button buttonGegnerischeKarte;
    private Button buttonStapel;
    private Button buttonTrumpfkarte;
    private Button buttonZudrehen;
    private Button button20er;
    private Button button40er;
    private Button buttonTrumpfTauschen;
    private MenuItem herz20er;
    private MenuItem karo20er;
    private MenuItem pik20er;
    private MenuItem kreuz20er;
    private Spieler selbst;
    private Spieler gegner;
    private Karte karte1;
    private Karte karte2;
    private Karte karte3;
    private Karte karte4;
    private Karte karte5;
    private Karte gegnerischeKarte;
    private Karte trumpfkarte;
    private TextView punkteGegner;
    private TextView punkteSelbst;
    private TextView txtSelbst;
    private TextView txtGegner;
    private Bummerl2 bummerl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spielfeld2);

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
        buttonsNichtKlickbar();
        //spiel.Auspielen(k);
        gespielteKarteEntfernen(i);
        buttonEigeneKarte.setText(k.getFarbe() + k.getWertigkeit());
        if (gegnerischeKarte == null) {
            gegnerischerZug(karte1);
        }
        //spiel.ZugAuswerten(k, gegnerischeKarte);
        // Execute some code after 2 seconds have passed
        Handler handler = new Handler();
        handler.postDelayed(new Zugende(), 2000);
    }

    private void eigenerZug() {
        /*if (!spiel.isZugedreht()) {
            buttonZudrehen.setEnabled(true);

            if (selbst.Hand.contains(new Karte(spiel.getTrumpf(),"Bube",2))) {
                buttonTrumpfTauschen.setEnabled(true);
            }
            else {
                buttonTrumpfTauschen.setEnabled(false);
            }
            if(hat20er()) {
                button20er.setEnabled(true);
            }
            else {
                button20er.setEnabled(false);
            }
            if(hat40er()) {
                button40er.setEnabled(true);
            }
            else {
                button40er.setEnabled(false);
            }
        }*/
    }

    private void gegnerischerZug(Karte karteS1) {
        /*gegnerischeKarte = spiel.AuspielenComputer(karteS1);
        buttonGegnerischeKarte.setText(gegnerischeKarte.getFarbe() + gegnerischeKarte.getWertigkeit());
        if (spiel.isZugedreht()) {
            buttonZudrehen.setEnabled(false);
            buttonZudrehen.setText("Zugedreht");
        }*/
    }

    private void handAktualisieren() {
        //int handkartenAnz = selbst.Hand.size();
        /*for (int i=0;i<5;i++) {
            Button buttonK = handkartenButtons.get(i);
            if (i<handkartenAnz) {
                Karte k = selbst.Hand.get(i);
                buttonK.setText(k.getFarbe() + k.getWertigkeit());
                buttonK.setVisibility(View.VISIBLE);
            } else {
                buttonK.setVisibility(View.INVISIBLE);
            }
        }*/
    }

    private void punkteAktualisieren() {
        buttonEigeneKarte.setText("");
        buttonGegnerischeKarte.setText("");
        /*int p1 = selbst.getPunkte();
        int p2 = gegner.getPunkte();
        punkteGegner.setText(Integer.toString(p2));
        punkteSelbst.setText(Integer.toString(p1));*/
    }

    private void gespielteKarteEntfernen(int i) {
        handkartenButtons.get(i).setVisibility(View.INVISIBLE);
    }

    private boolean hat20er() {
        /*if ((spiel.hat20er(selbst).size()>0 && !hat40er()) || spiel.hat20er(selbst).size()>1)
            return true;*/
        return false;
    }

    private boolean hat40er() {
        /*if(spiel.hat20er(selbst).contains(spiel.getTrumpf()))
            return true;*/
        return false;
    }

    private void spielStart() {

        buttonStapel.setText("20");
        //trumpfkarte = spiel.getAufgedeckterTrumpf();
        buttonTrumpfkarte.setText(trumpfkarte.getFarbe() + trumpfkarte.getWertigkeit());

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

    private void handKartenKlickbar() {
        /*int handkartenAnz = selbst.Hand.size();
        for (int i=0;i<5;i++) {
            Button buttonK = handkartenButtons.get(i);
            if (i<handkartenAnz && spiel.DarfKarteAuswaehlen(gegnerischeKarte, selbst.Hand.get(i))) {
                buttonK.setEnabled(true);
            } else {
                buttonK.setEnabled(false);
            }
        }*/
    }

    private void spielEnde() {
        boolean win = true;
        if (selbst.getPunkte()<66) {
            win = false;
        }
        Bundle args = new Bundle();
        args.putBoolean("win", win);
        DialogFragment gameEndDialogFragment = new GameEnd();
        gameEndDialogFragment.setArguments(args);
        gameEndDialogFragment.show(getFragmentManager(), "GameEnd");
    }

    public void zudrehen(View view) {
        //spiel.Zudrehen();
        buttonZudrehen.setEnabled(false);
        buttonZudrehen.setText("Zugedreht");
    }

    public void popup20er(View view) {
        PopupMenu popup = new PopupMenu(Spielfeld2Client.this, button20er);
        popup.inflate(R.menu.popup_menu_20er);
        herz20er = (MenuItem) popup.getMenu().getItem(0);
        karo20er = (MenuItem) popup.getMenu().getItem(1);
        pik20er = (MenuItem) popup.getMenu().getItem(2);
        kreuz20er = (MenuItem) popup.getMenu().getItem(3);
        herz20er.setVisible(false);
        karo20er.setVisible(false);
        pik20er.setVisible(false);
        kreuz20er.setVisible(false);
        /*ArrayList<String> a = spiel.hat20er(selbst);
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
        }*/
        popup.setOnMenuItemClickListener(this);
        popup.show();
    }

    public void ansagen40er(View view) {
        /*spiel.Ansagen20er(spiel.getTrumpf(), selbst);
        if (spiel.istSpielzuEnde(bummerl)) spielEnde();*/
        punkteAktualisieren();
        button40er.setEnabled(false);
        button20er.setEnabled(false);
        handKartenKlickbar();
    }

    public void trumpfkarteTauschen(View view) {
        /*spiel.TrumpfkarteAustauschen(new Karte(spiel.getTrumpf(),"Bube",2), selbst);
        trumpfkarte = spiel.getAufgedeckterTrumpf();
        buttonTrumpfkarte.setText(trumpfkarte.getFarbe() + trumpfkarte.getWertigkeit());*/
        handAktualisieren();
        buttonTrumpfTauschen.setEnabled(false);
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
        startActivity(new Intent(Spielfeld2Client.this, Startmenue.class));
        finish();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        /*switch (menuItem.getItemId()) {
            case R.id.herz_20er:
                spiel.Ansagen20er("Herz", selbst);
                break;
            case R.id.karo_20er:
                spiel.Ansagen20er("Karo", selbst);
                break;
            case R.id.pik_20er:
                spiel.Ansagen20er("Pik", selbst);
                break;
            case R.id.kreuz_20er:
                spiel.Ansagen20er("Kreuz", selbst);
                break;
            default:
                return false;
        }
        button20er.setEnabled(false);
        button40er.setEnabled(false);
        if (spiel.istSpielzuEnde(bummerl)) spielEnde();*/
        punkteAktualisieren();
        handKartenKlickbar();
        return true;
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

    @Override
    public void onEndpointFound(String s, String s1, String s2, String s3) {

    }

    @Override
    public void onEndpointLost(String s) {

    }

    @Override
    public void onMessageReceived(String endpointID, byte[] payload, boolean isReliable) {
        String message = new String(payload);
        switch ((message.substring(0,1))) {
            case BUMMERL: bummerl = new Bummerl2(message.substring(2));
                break;
            default: break;
        }
    }

    @Override
    public void onDisconnected(String s) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    class Zugende implements Runnable {

        @Override
        public void run() {
            punkteAktualisieren();
            gegnerischeKarte = null;
            /*if (spiel.istSpielzuEnde(bummerl)) {
                spielEnde();
            } else {
                handAktualisieren();
                if(spiel.AnzahlKartenStapel()!=0)
                    buttonStapel.setText(Integer.toString(spiel.AnzahlKartenStapel()+1));
                else
                    buttonStapel.setText("0");
                if (selbst.isIstdran()) {
                    eigenerZug();
                } else {
                    gegnerischerZug(null);
                }
                handKartenKlickbar();
            }*/
        }
    }
}
