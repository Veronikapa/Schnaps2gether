package appsolutegamesgmbh.schnaps2gether.GUI;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
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
import appsolutegamesgmbh.schnaps2gether.DataStructure.Rufspiel;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel3;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spieler;
import appsolutegamesgmbh.schnaps2gether.DataStructure.WrongGameException;
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
    private static final String FLECKEN = "3";
    private static final String ANGESAGT20ER = "4";
    private static final String ANGESAGT40ER = "5";
    private static final String TALONGETAUSCHT = "6";
    private static final String SPIELENDE = "7";
    private static final String BUMMERL = "8";
    private static final String HANDKARTEN = "9";
    private static final String TRUMPFKARTE = "10";
    private static final String ZUGENDE = "11";
    private static final String DISCONNECT = "12";
    private static final String TRUMPFFARBE = "13";
    private static final String TRUMPFANSAGEN = "14";
    private static final String SPIEL = "15";
    private static final String AUFGEDECKT = "16";
    private static final String SPIELANSAGEN = "17";
    private static final String TALONTAUSCHEN = "18";

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
    private static ImageView imageView_karte6;

    private static ArrayList<ImageView> handkartenImages;

    private static ImageView imageView_trumpf;
    private static ImageView imageView_eigeneKarte;
    private static ImageView imageView_karteGegner1;
    private static ImageView imageView_karteGegner2;
    private static ImageView imageView_trumpfIcon;
    private static String trumpffarbe;

    private static Button button20er;
    private static Button button40er;
    private static Button buttonSpielAnsagen;
    private static Button buttonTrumpfansagen;
    private static Button buttonFlecken;
    private static Button buttonGegenflecken;
    private static Button buttonWeiter;
    private static Button buttonTalonTauschen;
    private MenuItem herz20er;
    private MenuItem karo20er;
    private MenuItem pik20er;
    private MenuItem kreuz20er;
    private static Spieler selbst;
    private static Spieler gegner1;
    private static Spieler gegner2;

    private static Karte eigeneKarte;
    private static Karte gegnerischeKarte1;
    private static Karte gegnerischeKarte2;
    private static Karte aufgedrehteKarte;


    private static Karte ka,t;
    private static ImageView imageView_talonkarte1;
    private static ImageView imageView_talonkarte2;
    private static boolean talontauschen;
    private static String talonID;

    private static ImageView imageView_Stich1;
    private static ImageView imageView_Stich2;
    private static ImageView imageView_Stich3;

    private static TextView BpunkteGegner1;
    private static TextView BpunkteGegner2;
    private static TextView BpunkteSelbst;
    private static TextView punkteSelbst;
    private static TextView txtSelbst;
    private static TextView txtGegner1;
    private static TextView txtGegner2;
    private static Bummerl3 bummerl;
    private static Boolean angesagt;
    private static String trumpf;
    private static int siegerID;
    private static String spieleAnsagbar;


    private static ImageView stichK1;
    private static ImageView stichK2;
    private static ImageView stichK3;
    private static ImageView stichK4;
    private static ImageView stichK5;
    private static ImageView stichK6;
    private static ImageView stichK7;
    private static ImageView stichK8;
    private static ImageView stichK9;
    private static ImageView stichK10;
    private static ImageView stichK11;
    private static ImageView stichK12;
    private static ImageView stichK13;
    private static ImageView stichK14;
    private static ImageView stichK15;
    private static ImageView stichK16;

    private static Handler handler = new Handler();

    private static String[] messageParts;


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

        //Screen Lock deaktivieren
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mGoogleApiClient = Lobby.m_GoogleApiClient;
        endpointIDs = Lobby.endpointIds;
        endpointIDs.remove(Nearby.Connections.getLocalEndpointId(mGoogleApiClient));

        appContext = this.getApplicationContext();

        angesagt = false;

        bummerl = new Bummerl3();
        //Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (BUMMERL+":"+bummerl.toString()).getBytes());


        imageView_karte1 =(ImageView) findViewById(R.id.imageView_karte1);
        imageView_karte2 = (ImageView) findViewById(R.id.imageView_karte2);
        imageView_karte3 = (ImageView) findViewById(R.id.imageView_karte3);
        imageView_karte4 = (ImageView) findViewById(R.id.imageView_karte4);
        imageView_karte5 = (ImageView) findViewById(R.id.imageView_karte5);
        imageView_karte6 = (ImageView) findViewById(R.id.imageView_karte6);


        button20er = (Button) findViewById(R.id.main_button20er);
        button40er = (Button) findViewById(R.id.main_button40er);
        buttonSpielAnsagen = (Button) findViewById(R.id.cmd_rufspiel);
        buttonTrumpfansagen = (Button) findViewById(R.id.cmd_trumpfansagen);
        buttonFlecken = (Button) findViewById(R.id.cmd_flecken);
        buttonGegenflecken = (Button) findViewById(R.id.cmd_gegenflecken);
        buttonWeiter = (Button) findViewById(R.id.cmd_weiter);
        buttonTalonTauschen = (Button) findViewById(R.id.main_buttonTtauschen);

        imageView_eigeneKarte = (ImageView) findViewById(R.id.imageView_eigeneKarte);
        imageView_karteGegner1 = (ImageView) findViewById(R.id.imageView_karteGegner1);
        imageView_karteGegner1 = (ImageView) findViewById(R.id.imageView_karteGegner2);
        imageView_trumpf = (ImageView) findViewById(R.id.imageView_trumpf);
        imageView_trumpfIcon = (ImageView) findViewById(R.id.imageView_trumpfIcon);


        imageView_talonkarte1 =(ImageView)findViewById(R.id.imageView_talonkarte1);
        imageView_talonkarte2 =(ImageView)findViewById(R.id.imageView_talonkarte2);

        imageView_Stich1 = (ImageView) findViewById(R.id.imageView_eigeneKarte);
        imageView_Stich2 = (ImageView) findViewById(R.id.imageView_karteGegner1);
        imageView_Stich3 = (ImageView) findViewById(R.id.imageView_karteGegner2);


        handkartenImages = new ArrayList<ImageView>();
        handkartenImages.add(0, imageView_karte1);
        handkartenImages.add(1, imageView_karte2);
        handkartenImages.add(2, imageView_karte3);
        handkartenImages.add(3, imageView_karte4);
        handkartenImages.add(4, imageView_karte5);
        handkartenImages.add(5, imageView_karte6);

        stichK1= (ImageView) findViewById(R.id.stichK1);
        stichK2= (ImageView) findViewById(R.id.stichK2);
        stichK3= (ImageView) findViewById(R.id.stichK3);
        stichK4= (ImageView) findViewById(R.id.stichK4);
        stichK5= (ImageView) findViewById(R.id.stichK5);
        stichK6= (ImageView) findViewById(R.id.stichK6);
        stichK7= (ImageView) findViewById(R.id.stichK7);
        stichK8= (ImageView) findViewById(R.id.stichK8);
        stichK9= (ImageView) findViewById(R.id.stichK9);
        stichK10= (ImageView) findViewById(R.id.stichK10);
        stichK11= (ImageView) findViewById(R.id.stichK11);
        stichK12= (ImageView) findViewById(R.id.stichK12);
        stichK13= (ImageView) findViewById(R.id.stichK13);
        stichK14= (ImageView) findViewById(R.id.stichK14);
        stichK15= (ImageView) findViewById(R.id.stichK15);
        stichK16= (ImageView) findViewById(R.id.stichK16);

        punkteSelbst = (TextView) findViewById(R.id.txt_punkte);
        BpunkteSelbst = (TextView) findViewById(R.id.txt_PunkteZahl);
        BpunkteGegner1 = (TextView) findViewById(R.id.txt_BummerlZahlG1);
        BpunkteGegner2 = (TextView) findViewById(R.id.BummerlZahlG2);


        spielStart();

    }

    private void spielStart() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
        try {
            spiel = new Spiel3(bummerl.getAnzahlSpiele());
        }
        catch(Exception e){

        }
        selbst = spiel.getS1();
        gegner1 = spiel.getS2();
        gegner2 = spiel.getS3();
        trumpf = "";


        //Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TRUMPFKARTE + ":" + trumpfkarte.toString()).getBytes());

        stichK16.setVisibility(View.INVISIBLE);
        stichK15.setVisibility(View.INVISIBLE);
        stichK14.setVisibility(View.INVISIBLE);
        stichK13.setVisibility(View.INVISIBLE);
        stichK12.setVisibility(View.INVISIBLE);
        stichK11.setVisibility(View.INVISIBLE);
        stichK10.setVisibility(View.INVISIBLE);
        stichK9.setVisibility(View.INVISIBLE);
        stichK8.setVisibility(View.INVISIBLE);
        stichK7.setVisibility(View.INVISIBLE);
        stichK6.setVisibility(View.INVISIBLE);
        stichK5.setVisibility(View.INVISIBLE);
        stichK4.setVisibility(View.INVISIBLE);
        stichK3.setVisibility(View.INVISIBLE);
        stichK2.setVisibility(View.INVISIBLE);
        stichK1.setVisibility(View.INVISIBLE);


        //handKartenKlickbar();
        buttonsNichtKlickbar();
        handAktualisieren();

        if(bummerl.getAnzahlSpiele() == 0) {
            punkteSelbst.setText("0");
            BpunkteSelbst.setText("0");
            BpunkteGegner1.setText("0");
            BpunkteGegner2.setText("0");
            gegnerischeKarte1 = null;
            gegnerischeKarte2 = null;

            buttonWeiter.setEnabled(true);
            buttonWeiter.setAlpha(1f);
            buttonWeiter.setText("Aufdecken");
            buttonTrumpfansagen.setVisibility(View.VISIBLE);
        }
        else
        {
            punkteSelbst.setText("0");

            if(bummerl.getAnzahlSpiele()%3 == 0){
                buttonWeiter.setEnabled(true);
                buttonWeiter.setAlpha(1f);
                buttonWeiter.setText("Aufdecken");
                buttonTrumpfansagen.setVisibility(View.VISIBLE);
                buttonTrumpfansagen.setEnabled(true);
            }
            else if(bummerl.getAnzahlSpiele()%3 == 1) {
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TRUMPFANSAGEN + ":1").getBytes());
            }
            else{
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TRUMPFANSAGEN + ":2").getBytes());
            }
        }


        //eigenerZug();
            }
        }, 2000);

    }

    public void popup20er(View view) {
        PopupMenu popup = new PopupMenu(Spielfeld3Host.this, button20er);
        popup.inflate(R.menu.popup_menu_20er);
        MenuItem herz20er = (MenuItem) popup.getMenu().getItem(0);
        MenuItem karo20er = (MenuItem) popup.getMenu().getItem(1);
        MenuItem pik20er = (MenuItem) popup.getMenu().getItem(2);
        MenuItem kreuz20er = (MenuItem) popup.getMenu().getItem(3);
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

    public void popupTrumpfansagen(View view) {
        PopupMenu popup = new PopupMenu(Spielfeld3Host.this, buttonTrumpfansagen);
        popup.inflate(R.menu.popup_menu_trumpfansagen);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                try {
                    switch (menuItem.getTitle().toString()) {
                        case "Herz":
                            trumpffarbe = "Herz";
                            break;
                        case "Karo":
                            trumpffarbe = "Karo";
                            break;
                        case "Pik":
                            trumpffarbe = "Pik";
                            break;
                        case "Kreuz":
                            trumpffarbe = "Kreuz";
                            break;
                        default: return false;
                    }
                } catch (Exception e) {

                }

                imageView_trumpfIcon.setImageResource(Karte.getIconResourceId(trumpffarbe));
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TRUMPFFARBE+":"+trumpffarbe).getBytes());
                spiel.Trumpfansagen(trumpffarbe,bummerl.getAnzahlSpiele());

                handAktualisieren();

                buttonWeiter.setText("Weiter");
                buttonWeiter.setEnabled(false);
                buttonWeiter.setAlpha(0.4f);
                buttonTrumpfansagen.setVisibility(View.INVISIBLE);
                buttonTrumpfansagen.setEnabled(false);

                if(bummerl.getAnzahlSpiele()%3 == 0){
                    buttonWeiter.setEnabled(true);
                    buttonWeiter.setAlpha(1f);
                    buttonSpielAnsagen.setVisibility(View.VISIBLE);
                }
                else if(bummerl.getAnzahlSpiele()%3 == 1) {
                    andererSpielerKannSpielAnsagen(gegner1);
                    Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIELANSAGEN + ":" + spieleAnsagbar + ":1").getBytes());
                }
                else{
                    andererSpielerKannSpielAnsagen(gegner2);
                    Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIELANSAGEN + ":" + spieleAnsagbar + ":2").getBytes());
                }

                return true;
            }
        });
        popup.show();
    }

    public void popupSpielAnsagen(final View view) {
        PopupMenu popup = new PopupMenu(Spielfeld3Host.this, buttonSpielAnsagen);
        popup.inflate(R.menu.popup_menu_spiel_ansagen);
        MenuItem schnapser = (MenuItem) popup.getMenu().getItem(0);
        MenuItem land = (MenuItem) popup.getMenu().getItem(1);
        MenuItem kontraschnapser = (MenuItem) popup.getMenu().getItem(2);
        MenuItem bauernschnapser = (MenuItem) popup.getMenu().getItem(3);
        MenuItem kontrabauernschnapser = (MenuItem) popup.getMenu().getItem(4);
        MenuItem farbenjodler = (MenuItem) popup.getMenu().getItem(5);
        MenuItem herrenjodler = (MenuItem) popup.getMenu().getItem(6);
        schnapser.setVisible(false);
        land.setVisible(false);
        kontraschnapser.setVisible(false);
        bauernschnapser.setVisible(false);
        kontrabauernschnapser.setVisible(false);
        farbenjodler.setVisible(false);
        herrenjodler.setVisible(false);
        try {
            if (spiel.DarfSpielAnsagen(new Rufspiel("Schnapser"), selbst)) schnapser.setVisible(true);
            if (spiel.DarfSpielAnsagen(new Rufspiel("Land"), selbst)) land.setVisible(true);
            if (spiel.DarfSpielAnsagen(new Rufspiel("Kontraschnapser"), selbst)) kontraschnapser.setVisible(true);
            if (spiel.DarfSpielAnsagen(new Rufspiel("Bauernschnapser"), selbst)) bauernschnapser.setVisible(true);
            if (spiel.DarfSpielAnsagen(new Rufspiel("Kontrabauernschnapser"), selbst)) kontrabauernschnapser.setVisible(true);
        } catch (Exception e) {

        }
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                try {
                    switch (menuItem.getTitle().toString()) {
                        case "Schnapser":
                            spiel.SpielAnsagen(new Rufspiel("Schnapser"), selbst);
                            Toast.makeText(appContext, spiel.getSpiel().getSpiel() + " angesagt", Toast.LENGTH_SHORT).show();
                            break;
                        case "Land":
                            spiel.SpielAnsagen(new Rufspiel("Land"), selbst);
                            Toast.makeText(appContext, spiel.getSpiel().getSpiel() + " angesagt", Toast.LENGTH_SHORT).show();
                            break;
                        case "Kontraschnapser":
                            spiel.SpielAnsagen(new Rufspiel("Kontraschnapser"), selbst);
                            Toast.makeText(appContext, spiel.getSpiel().getSpiel() + " angesagt", Toast.LENGTH_SHORT).show();
                            break;
                        case "Bauernschnapser":
                            spiel.SpielAnsagen(new Rufspiel("Bauernschnapser"), selbst);
                            Toast.makeText(appContext, spiel.getSpiel().getSpiel() + " angesagt", Toast.LENGTH_SHORT).show();
                            break;
                        case "Kontrabauernschnapser":
                            spiel.SpielAnsagen(new Rufspiel("Kontrabauernschnapser"), selbst);
                            Toast.makeText(appContext, spiel.getSpiel().getSpiel() + " angesagt", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            return false;
                    }
                } catch (Exception e) {

                }

                buttonSpielAnsagen.setVisibility(view.INVISIBLE);
                buttonWeiter.setVisibility(view.INVISIBLE);

                if (bummerl.getAnzahlSpiele() % 3 == 1) {
                    Toast.makeText(appContext, spiel.getSpiel().getSpiel() + " wird gespielt", Toast.LENGTH_SHORT).show();
                    Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIEL + ":" + spiel.getSpiel().getSpiel()).getBytes());

                    if (spiel.getSpieler().equals(selbst)) {
                        talonzeigen();
                        buttonWeiter.setEnabled(true);
                        buttonWeiter.setAlpha(1f);
                    } else if (spiel.getSpieler().equals(gegner1)) {
                        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TALONTAUSCHEN + ":" + spiel.getTalon().get(0).toString() + "," + spiel.getTalon().get(1).toString() + ":1").getBytes());
                    } else {
                        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TALONTAUSCHEN + ":" + spiel.getTalon().get(0).toString() + "," + spiel.getTalon().get(1).toString() + ":2").getBytes());
                    }
                } else {
                    andererSpielerKannSpielAnsagen(gegner1);
                    Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIELANSAGEN + ":" + spieleAnsagbar + ":1").getBytes());
                }
                return true;
            }
        });
        popup.show();
    }

    private static void andererSpielerKannSpielAnsagen(Spieler andererSpieler) {
        spieleAnsagbar = "";
        try {
            if (spiel.DarfSpielAnsagen(new Rufspiel("Schnapser"), andererSpieler))
                spieleAnsagbar += "1 ";
            else spieleAnsagbar += "0 ";
            if (spiel.DarfSpielAnsagen(new Rufspiel("Land"), andererSpieler)) spieleAnsagbar += "1 ";
            else spieleAnsagbar += "0 ";
            if (spiel.DarfSpielAnsagen(new Rufspiel("Kontraschnapser"), andererSpieler))
                spieleAnsagbar += "1 ";
            else spieleAnsagbar += "0 ";
            if (spiel.DarfSpielAnsagen(new Rufspiel("Bauernschnapser"), andererSpieler))
                spieleAnsagbar += "1 ";
            else spieleAnsagbar += "0 ";
            if (spiel.DarfSpielAnsagen(new Rufspiel("Kontrabauernschnapser"), andererSpieler))
                spieleAnsagbar += "1";
            else spieleAnsagbar += "0";
        } catch (Exception e) {

        }

    }

    public void weiterOnClick(View view) {

        if (buttonWeiter.getText() == "Aufdecken") {
            aufdrehen();
            //Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (AUFGEDECKT).getBytes());

            buttonTrumpfansagen.setVisibility(View.INVISIBLE);
            buttonTrumpfansagen.setEnabled(false);
        } else if (talontauschen) {
            talontauschen = false;
            handAktualisieren();
            eigenerZug();
            handKartenKlickbar();
            imageView_talonkarte1.setVisibility(View.INVISIBLE);
            imageView_talonkarte2.setVisibility(View.INVISIBLE);
        } else {
            if (bummerl.getAnzahlSpiele() % 3 == 1) {
                buttonWeiter.setEnabled(false);
                buttonWeiter.setAlpha(0.6f);
                buttonSpielAnsagen.setVisibility(View.INVISIBLE);
                Toast.makeText(appContext, spiel.getSpiel().getSpiel() + " wird gespielt", Toast.LENGTH_SHORT).show();
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIEL + ":" + spiel.getSpiel().getSpiel()).getBytes());

                if(spiel.getSpieler().equals(selbst)) {
                    talonzeigen();
                    buttonWeiter.setEnabled(true);
                    buttonWeiter.setAlpha(1f);
                }
                else if(spiel.getSpieler().equals(gegner1)){
                    Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TALONTAUSCHEN + ":" + spiel.getTalon().get(0).toString() + "," + spiel.getTalon().get(1).toString() + ":1").getBytes());
                }
                else{
                    Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TALONTAUSCHEN + ":" + spiel.getTalon().get(0).toString() + "," + spiel.getTalon().get(1).toString() + ":2").getBytes());
                }
            } else {
                buttonWeiter.setEnabled(false);
                buttonWeiter.setAlpha(0.6f);
                buttonSpielAnsagen.setVisibility(View.INVISIBLE);
                andererSpielerKannSpielAnsagen(gegner1);
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIELANSAGEN + ":" + spieleAnsagbar + ":1").getBytes());
            }
        }
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
        //Screen Lock aktivieren
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        startActivity(new Intent(Spielfeld3Host.this, Startmenue.class));
        finish();
    }

    @Override
    public void onMessageReceived(String s, byte[] bytes, boolean b) {
        receiveFromLobby(s,bytes,b);
    }

    @Override
    public void onDisconnected(String s) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

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
        button20er.setAlpha(0.4f);
        button40er.setEnabled(false);
        button40er.setAlpha(0.4f);
        if (spiel.istSpielzuEnde(bummerl)) spielEnde();
        punkteAktualisieren();
        handKartenKlickbar();
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

    public void receiveFromLobby(String endpointID, byte[] payload, boolean isReliable){
        String message = new String(payload);
        messageParts = message.split(":");
        switch (((message.split(":")[0]))) {
            case KARTEGESPIELT:
                if(message.split(":")[2] == "1") {
                    gegnerischeKarte1 = new Karte(message.split(":")[1]);
                    spiel.Auspielen(gegnerischeKarte1, gegner1);
                    imageView_karteGegner1.setImageResource(gegnerischeKarte1.getImageResourceId());
                }
                else
                {
                    gegnerischeKarte2 = new Karte(message.split(":")[1]);
                    spiel.Auspielen(gegnerischeKarte2, gegner2);
                    imageView_karteGegner2.setImageResource(gegnerischeKarte2.getImageResourceId());

                    if(eigeneKarte == null)
                    {
                        handKartenKlickbar();
                    }
                }
                break;
            case WEITER: handKartenKlickbar();
                if (message.substring(2,3).equals("1")) {
                    eigenerZug();
                }
                Toast.makeText(appContext, "Weiter", Toast.LENGTH_SHORT).show();
                break;
            case SPIEL:
                //Spiel wurde angesagt (bzw. weiter gesagt)
                if(messageParts[2].equals("2")){
                    try {
                        if(!(messageParts[1].equals("weiter"))) {
                            spiel.SpielAnsagen(new Rufspiel(messageParts[1]), gegner2);
                            Toast.makeText(appContext, spiel.getSpiel().getSpiel() + " angesagt", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(appContext, "Weiter", Toast.LENGTH_SHORT).show();
                    } catch (WrongGameException e) {
                        e.printStackTrace();
                    }
                    //wenn nächster Spieler Vorhand --> höchtes angesagtes Spiel wird gespielt, wenn nächster Spieler nicht Vorhand --> nächster Spieler darf Spiel ansagen
                    if(!(bummerl.getAnzahlSpiele()%3 == 0)) {
                        buttonWeiter.setEnabled(true);
                        buttonWeiter.setAlpha(1f);
                        buttonSpielAnsagen.setVisibility(View.VISIBLE);
                    }
                    else{
                        Toast.makeText(appContext, spiel.getSpiel().getSpiel() + " wird gespielt", Toast.LENGTH_SHORT).show();
                        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIEL + ":" + spiel.getSpiel().getSpiel()).getBytes());

                        if(spiel.getSpieler().equals(selbst)) {
                            talonzeigen();
                            buttonWeiter.setEnabled(true);
                            buttonWeiter.setAlpha(1f);
                        }
                        else if(spiel.getSpieler().equals(gegner1)){
                            Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TALONTAUSCHEN + ":" + spiel.getTalon().get(0).toString() + "," + spiel.getTalon().get(1).toString() + ":1").getBytes());
                        }
                        else{
                            Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TALONTAUSCHEN + ":" + spiel.getTalon().get(0).toString() + "," + spiel.getTalon().get(1).toString() + ":2").getBytes());
                        }
                    }
                }
                else if(messageParts[2].equals("1")){
                    try {
                        if(!(messageParts[1].equals("weiter"))) {
                            spiel.SpielAnsagen(new Rufspiel(messageParts[1]), gegner1);
                            Toast.makeText(appContext, spiel.getSpiel().getSpiel() + " angesagt", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(appContext, "Weiter", Toast.LENGTH_SHORT).show();
                    } catch (WrongGameException e) {
                        e.printStackTrace();
                    }
                    //wenn nächster Spieler Vorhand --> höchtes angesagtes Spiel wird gespielt, wenn nächster Spieler nicht Vorhand --> nächster Spieler darf Spiel ansagen
                    if(!(bummerl.getAnzahlSpiele()%3 == 2)) {

                        andererSpielerKannSpielAnsagen(gegner2);
                        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIELANSAGEN + ":" + spieleAnsagbar + ":2").getBytes());
                    }
                    else{
                        Toast.makeText(appContext, spiel.getSpiel().getSpiel() + " wird gespielt", Toast.LENGTH_SHORT).show();
                        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIEL + ":" + spiel.getSpiel().getSpiel()).getBytes());

                        if(spiel.getSpieler().equals(selbst)) {
                            talonzeigen();
                            buttonWeiter.setEnabled(true);
                            buttonWeiter.setAlpha(1f);
                        }
                        else if(spiel.getSpieler().equals(gegner1)){
                            Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TALONTAUSCHEN + ":" + spiel.getTalon().get(0).toString() + "," + spiel.getTalon().get(1).toString() + ":1").getBytes());
                        }
                        else{
                            Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TALONTAUSCHEN + ":" + spiel.getTalon().get(0).toString() + "," + spiel.getTalon().get(1).toString() + ":2").getBytes());
                        }
                    }
                }
                break;
            case ANGESAGT40ER: spiel.Ansagen20er(spiel.getTrumpf(), gegner1);
                punkteAktualisieren();
                if (spiel.istSpielzuEnde(bummerl)) {
                    spielEnde();
                }
                gegnerischeHandAktualisieren();
                angesagt = true;
                Toast.makeText(appContext, "40er angesagt", Toast.LENGTH_SHORT).show();
                break;
            case ANGESAGT20ER: String farbe = message.substring(2);
                spiel.Ansagen20er(farbe, gegner1);
                punkteAktualisieren();
                if (spiel.istSpielzuEnde(bummerl)) {
                    spielEnde();
                }
                gegnerischeHandAktualisieren();
                angesagt = true;
                Toast.makeText(appContext, farbe+" 20er angesagt", Toast.LENGTH_SHORT).show();
                break;
            case TRUMPFFARBE:
                trumpffarbe = message.split(":")[1];
                imageView_trumpfIcon.setImageResource(Karte.getIconResourceId(trumpffarbe));
                break;
            case TALONGETAUSCHT:
                Toast.makeText(appContext, "Talon ausgetauscht", Toast.LENGTH_SHORT).show();
                break;
            case AUFGEDECKT:
                aufdrehen();
                break;
            default: break;
        }
    }

    public void karte1OnClick(View view) {
        if (!talontauschen)
            zugAusführen(0);
        else {
            if (talonID.equals("0")) {
                imageView_karte1.setAlpha(1f);
                talonID = "21";
            } else if (talonID.equals("11")) {
                ka = spiel.Talon.get(0);
                imageView_karte1.setImageResource(ka.getImageResourceId());
                imageView_karte1.setAlpha(0.6f);

                t = selbst.Hand.get(0);
                imageView_talonkarte1.setImageResource(t.getImageResourceId());
                imageView_talonkarte1.setAlpha(0.6f);

                spiel.Talon.set(0, t);
                selbst.Hand.set(0, ka);
                talonID = "0";
            } else if (talonID.equals("12")) {
                ka = spiel.Talon.get(1);
                imageView_karte1.setImageResource(ka.getImageResourceId());
                imageView_karte1.setAlpha(0.6f);

                t = selbst.Hand.get(0);
                imageView_talonkarte1.setImageResource(t.getImageResourceId());
                imageView_talonkarte1.setAlpha(0.6f);

                spiel.Talon.set(1, t);
                selbst.Hand.set(0, ka);
                talonID = "0";
            } else if (talonID.equals("21")) {
                imageView_karte1.setAlpha(0.6f);
                talonID = "0";
            } else
                Toast.makeText(appContext, "Wählen Sie eine Talonkarte zum Tauschen!", Toast.LENGTH_SHORT).show();

        }

    }

    public void karte2OnClick(View view) {
        if (!talontauschen)
            zugAusführen(1);
        else {
            if (talonID.equals("0")) {
                imageView_karte2.setAlpha(1f);
                talonID = "22";
            } else if (talonID.equals("11")) {
                ka = spiel.Talon.get(0);
                imageView_karte2.setImageResource(ka.getImageResourceId());
                imageView_karte2.setAlpha(0.6f);

                t = selbst.Hand.get(1);
                imageView_talonkarte1.setImageResource(t.getImageResourceId());
                imageView_talonkarte1.setAlpha(0.6f);

                spiel.Talon.set(0, t);
                selbst.Hand.set(1, ka);
                talonID = "0";
            } else if (talonID.equals("12")) {
                ka = spiel.Talon.get(1);
                imageView_karte2.setImageResource(ka.getImageResourceId());
                imageView_karte2.setAlpha(0.6f);

                t = selbst.Hand.get(1);
                imageView_talonkarte2.setImageResource(t.getImageResourceId());
                imageView_talonkarte2.setAlpha(0.6f);

                spiel.Talon.set(1, t);
                selbst.Hand.set(1, ka);
                talonID = "0";
            } else if (talonID.equals("22")) {
                imageView_karte2.setAlpha(0.6f);
                talonID = "0";
            } else
                Toast.makeText(appContext, "Wählen Sie eine Talonkarte zum Tauschen!", Toast.LENGTH_SHORT).show();

        }
    }

    public void karte3OnClick(View view) {
        if (!talontauschen)
            zugAusführen(2);
        else {
            if (talonID.equals("0")) {
                imageView_karte3.setAlpha(1f);
                talonID = "23";
            } else if (talonID.equals("11")) {
                ka = spiel.Talon.get(0);
                imageView_karte3.setImageResource(ka.getImageResourceId());
                imageView_karte3.setAlpha(0.6f);

                t = selbst.Hand.get(2);
                imageView_talonkarte1.setImageResource(t.getImageResourceId());
                imageView_talonkarte1.setAlpha(0.6f);

                spiel.Talon.set(0, t);
                selbst.Hand.set(2, ka);
                talonID = "0";
            } else if (talonID.equals("12")) {
                ka = spiel.Talon.get(1);
                imageView_karte3.setImageResource(ka.getImageResourceId());
                imageView_karte3.setAlpha(0.6f);

                t = selbst.Hand.get(3);
                imageView_talonkarte2.setImageResource(t.getImageResourceId());
                imageView_talonkarte2.setAlpha(0.6f);

                spiel.Talon.set(1, t);
                selbst.Hand.set(2, ka);
                talonID = "0";
            } else if (talonID.equals("23")) {
                imageView_karte3.setAlpha(0.6f);
                talonID = "0";
            } else
                Toast.makeText(appContext, "Wählen Sie eine Talonkarte zum Tauschen!", Toast.LENGTH_SHORT).show();

        }
    }

    public void karte4OnClick(View view) {
        if (!talontauschen)
            zugAusführen(3);
        else {
            if (talonID.equals("0")) {
                imageView_karte4.setAlpha(1f);
                talonID = "24";
            } else if (talonID.equals("11")) {
                ka = spiel.Talon.get(0);
                imageView_karte4.setImageResource(ka.getImageResourceId());
                imageView_karte4.setAlpha(0.6f);

                t = selbst.Hand.get(3);
                imageView_talonkarte1.setImageResource(t.getImageResourceId());
                imageView_talonkarte1.setAlpha(0.6f);

                spiel.Talon.set(0, t);
                selbst.Hand.set(3, ka);
                talonID = "0";
            } else if (talonID.equals("12")) {
                ka = spiel.Talon.get(1);
                imageView_karte4.setImageResource(ka.getImageResourceId());
                imageView_karte4.setAlpha(0.6f);

                t = selbst.Hand.get(3);
                imageView_talonkarte2.setImageResource(t.getImageResourceId());
                imageView_talonkarte2.setAlpha(0.6f);

                spiel.Talon.set(1, t);
                selbst.Hand.set(3, ka);
                talonID = "0";
            } else if (talonID.equals("24")) {
                imageView_karte4.setAlpha(0.6f);
                talonID = "0";
            } else
                Toast.makeText(appContext, "Wählen Sie eine Talonkarte zum Tauschen!", Toast.LENGTH_SHORT).show();

        }
    }

    public void karte5OnClick(View view) {
        if (!talontauschen)
            zugAusführen(4);
        else {
            if (talonID.equals("0")) {
                imageView_karte5.setAlpha(1f);
                talonID = "25";
            } else if (talonID.equals("11")) {
                ka = spiel.Talon.get(0);
                imageView_karte5.setImageResource(ka.getImageResourceId());
                imageView_karte5.setAlpha(0.6f);

                t = selbst.Hand.get(4);
                imageView_talonkarte1.setImageResource(t.getImageResourceId());
                imageView_talonkarte1.setAlpha(0.6f);

                spiel.Talon.set(0, t);
                selbst.Hand.set(4, ka);
                talonID = "0";
            } else if (talonID.equals("12")) {
                ka = spiel.Talon.get(1);
                imageView_karte5.setImageResource(ka.getImageResourceId());
                imageView_karte5.setAlpha(0.6f);

                t = selbst.Hand.get(4);
                imageView_talonkarte2.setImageResource(t.getImageResourceId());
                imageView_talonkarte2.setAlpha(0.6f);

                spiel.Talon.set(1, t);
                selbst.Hand.set(4, ka);
                talonID = "0";
            } else if (talonID.equals("25")) {
                imageView_karte5.setAlpha(0.6f);
                talonID = "0";
            } else
                Toast.makeText(appContext, "Wählen Sie eine Talonkarte zum Tauschen!", Toast.LENGTH_SHORT).show();

        }
    }

    public void karte6OnClick(View view) {
        if (!talontauschen)
            zugAusführen(0);
        else {
            if (talonID.equals("0")) {
                imageView_karte6.setAlpha(1f);
                talonID = "26";
            } else if (talonID.equals("11")) {
                ka = spiel.Talon.get(0);
                imageView_karte6.setImageResource(ka.getImageResourceId());
                imageView_karte6.setAlpha(0.6f);

                t = selbst.Hand.get(5);
                imageView_talonkarte1.setImageResource(t.getImageResourceId());
                imageView_talonkarte1.setAlpha(0.6f);

                spiel.Talon.set(0, t);
                selbst.Hand.set(5, ka);
                talonID = "0";
            } else if (talonID.equals("12")) {
                ka = spiel.Talon.get(1);
                imageView_karte6.setImageResource(ka.getImageResourceId());
                imageView_karte6.setAlpha(0.6f);

                t = selbst.Hand.get(5);
                imageView_talonkarte2.setImageResource(t.getImageResourceId());
                imageView_talonkarte2.setAlpha(0.6f);

                spiel.Talon.set(1, t);
                selbst.Hand.set(5, ka);
                talonID = "0";
            } else if (talonID.equals("26")) {
                imageView_karte6.setAlpha(0.6f);
                talonID = "0";
            } else
                Toast.makeText(appContext, "Wählen Sie eine Talonkarte zum Tauschen!", Toast.LENGTH_SHORT).show();

        }
    }

    public void talon1_onClick(View view) {
        if (talonID.equals("0")) {
            imageView_talonkarte1.setAlpha(1f);
            talonID = "11";
        } else if (talonID.equals("21")) {
            ka = spiel.Talon.get(0);
            imageView_karte1.setImageResource(ka.getImageResourceId());
            imageView_karte1.setAlpha(0.6f);

            t = selbst.Hand.get(0);
            imageView_talonkarte1.setImageResource(t.getImageResourceId());
            imageView_talonkarte1.setAlpha(0.6f);

            spiel.Talon.set(0,t);
            selbst.Hand.set(0,ka);
            talonID = "0";
        } else if (talonID.equals("22")) {
            ka = spiel.Talon.get(0);
            imageView_karte2.setImageResource(ka.getImageResourceId());
            imageView_karte2.setAlpha(0.6f);

            t = selbst.Hand.get(1);
            imageView_talonkarte1.setImageResource(t.getImageResourceId());
            imageView_talonkarte1.setAlpha(0.6f);

            spiel.Talon.set(0, t);
            selbst.Hand.set(1, ka);
            talonID = "0";
        } else if (talonID.equals("23")) {
            ka = spiel.Talon.get(0);
            imageView_karte3.setImageResource(ka.getImageResourceId());
            imageView_karte3.setAlpha(0.6f);

            t = selbst.Hand.get(2);
            imageView_talonkarte1.setImageResource(t.getImageResourceId());
            imageView_talonkarte1.setAlpha(0.6f);

            spiel.Talon.set(0,t);
            selbst.Hand.set(2,ka);
            talonID = "0";
        } else if (talonID.equals("24")) {
            ka = spiel.Talon.get(0);
            imageView_karte4.setImageResource(ka.getImageResourceId());
            imageView_karte4.setAlpha(0.6f);

            t = selbst.Hand.get(3);
            imageView_talonkarte1.setImageResource(t.getImageResourceId());
            imageView_talonkarte1.setAlpha(0.6f);

            spiel.Talon.set(0,t);
            selbst.Hand.set(3,ka);
            talonID = "0";
        }else if (talonID.equals("25")) {
            ka = spiel.Talon.get(0);
            imageView_karte5.setImageResource(ka.getImageResourceId());
            imageView_karte5.setAlpha(0.6f);

            t = selbst.Hand.get(4);
            imageView_talonkarte1.setImageResource(t.getImageResourceId());
            imageView_talonkarte1.setAlpha(0.6f);

            spiel.Talon.set(0,t);
            selbst.Hand.set(4,ka);
            talonID = "0";
        }else if (talonID.equals("26")) {
            ka = spiel.Talon.get(0);
            imageView_karte6.setImageResource(ka.getImageResourceId());
            imageView_karte6.setAlpha(0.6f);

            t = selbst.Hand.get(5);
            imageView_talonkarte1.setImageResource(t.getImageResourceId());
            imageView_talonkarte1.setAlpha(0.6f);

            spiel.Talon.set(0, t);
            selbst.Hand.set(5, ka);
            talonID = "0";
        }else if(talonID.equals("11")){
            imageView_talonkarte1.setAlpha(0.6f);
            talonID = "0";
        }else
            Toast.makeText(appContext, "Wählen Sie eine Handkarte zum Tauschen!", Toast.LENGTH_SHORT).show();
    }

    public void talon2_onClick(View view) {
        if (talonID.equals("0")) {
            imageView_talonkarte2.setAlpha(1f);
            talonID = "12";
        } else if (talonID.equals("21")) {
            ka = spiel.Talon.get(1);
            imageView_karte1.setImageResource(ka.getImageResourceId());
            imageView_karte1.setAlpha(0.6f);

            t = selbst.Hand.get(0);
            imageView_talonkarte2.setImageResource(t.getImageResourceId());
            imageView_talonkarte2.setAlpha(0.6f);

            spiel.Talon.set(1,t);
            selbst.Hand.set(0,ka);
            talonID = "0";
        } else if (talonID.equals("22")) {
            ka = spiel.Talon.get(1);
            imageView_karte2.setImageResource(ka.getImageResourceId());
            imageView_karte2.setAlpha(0.6f);

            t = selbst.Hand.get(1);
            imageView_talonkarte2.setImageResource(t.getImageResourceId());
            imageView_talonkarte2.setAlpha(0.6f);

            spiel.Talon.set(1, t);
            selbst.Hand.set(1, ka);
            talonID = "0";
        } else if (talonID.equals("23")) {
            ka = spiel.Talon.get(1);
            imageView_karte3.setImageResource(ka.getImageResourceId());
            imageView_karte3.setAlpha(0.6f);

            t = selbst.Hand.get(2);
            imageView_talonkarte2.setImageResource(t.getImageResourceId());
            imageView_talonkarte2.setAlpha(0.6f);

            spiel.Talon.set(1,t);
            selbst.Hand.set(2,ka);
            talonID = "0";
        } else if (talonID.equals("24")) {
            ka = spiel.Talon.get(1);
            imageView_karte4.setImageResource(ka.getImageResourceId());
            imageView_karte4.setAlpha(0.6f);

            t = selbst.Hand.get(3);
            imageView_talonkarte2.setImageResource(t.getImageResourceId());
            imageView_talonkarte2.setAlpha(0.6f);

            spiel.Talon.set(1,t);
            selbst.Hand.set(3,ka);
            talonID = "0";
        }else if (talonID.equals("25")) {
            ka = spiel.Talon.get(1);
            imageView_karte5.setImageResource(ka.getImageResourceId());
            imageView_karte5.setAlpha(0.6f);

            t = selbst.Hand.get(4);
            imageView_talonkarte2.setImageResource(t.getImageResourceId());
            imageView_talonkarte2.setAlpha(0.6f);

            spiel.Talon.set(1,t);
            selbst.Hand.set(4,ka);
            talonID = "0";
        }else if (talonID.equals("26")) {
            ka = spiel.Talon.get(1);
            imageView_karte6.setImageResource(ka.getImageResourceId());
            imageView_karte6.setAlpha(0.6f);

            t = selbst.Hand.get(5);
            imageView_talonkarte2.setImageResource(t.getImageResourceId());
            imageView_talonkarte2.setAlpha(0.6f);

            spiel.Talon.set(1, t);
            selbst.Hand.set(5, ka);
            talonID = "0";
        }else if(talonID.equals("12")){
            imageView_talonkarte1.setAlpha(0.6f);
            talonID = "0";
        }else
        Toast.makeText(appContext, "Wählen Sie eine Handkarte zum Tauschen!", Toast.LENGTH_SHORT).show();
    }

    private void zugAusführen(int i) {
        final Karte k = selbst.Hand.get(i);
        eigeneKarte = k;
        buttonsNichtKlickbar();
        spiel.Auspielen(k, selbst);
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (KARTEGESPIELT + ":" + k.toString() + ":0").getBytes());
        gespielteKarteEntfernen(i);

        //buttonEigeneKarte.setText(k.getFarbe() + k.getWertigkeit());
        imageView_eigeneKarte.setImageResource(k.getImageResourceId());


        if (gegnerischeKarte1 == null) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (WEITER + ":1").getBytes());
                }
            }, 1000);
        } else {
            zugEnde();
        }
    }

    private void talonzeigen()
    {
        buttonWeiter.setEnabled(true);
        buttonWeiter.setAlpha(1f);
        talontauschen = true;
        talonID = "0";
        imageView_talonkarte1.setVisibility(View.VISIBLE);
        imageView_talonkarte1.setImageResource(spiel.getTalon().get(0).getImageResourceId());
        imageView_talonkarte1.setEnabled(true);
        imageView_talonkarte1.setAlpha(0.6f);
        imageView_talonkarte2.setVisibility(View.VISIBLE);
        imageView_talonkarte2.setImageResource(spiel.getTalon().get(1).getImageResourceId());
        imageView_talonkarte2.setEnabled(true);
        imageView_talonkarte2.setAlpha(0.6f);

        imageView_karte1.setEnabled(true);
        imageView_karte1.setAlpha(0.6f);
        imageView_karte2.setEnabled(true);
        imageView_karte2.setAlpha(0.6f);
        imageView_karte3.setEnabled(true);
        imageView_karte3.setAlpha(0.6f);
        imageView_karte4.setEnabled(true);
        imageView_karte4.setAlpha(0.6f);
        imageView_karte5.setEnabled(true);
        imageView_karte5.setAlpha(0.6f);
        imageView_karte6.setEnabled(true);
        imageView_karte6.setAlpha(0.6f);
    }

    private void zugEnde() {
        spiel.ZugAuswerten(eigeneKarte, gegnerischeKarte1, gegnerischeKarte2);
        eigeneKarte = null;
        gegnerischeKarte1 = null;
        gegnerischeKarte2 = null;
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (ZUGENDE+":").getBytes());
        Handler handler = new Handler();
        handler.postDelayed(new Zugende(), 2000);
    }


    private void gespielteKarteEntfernen(int i) {
        handkartenImages.get(i).setVisibility(View.INVISIBLE);
    }

    private void buttonsNichtKlickbar() {
        imageView_karte1.setEnabled(false);
        imageView_karte1.setAlpha(0.6f);
        imageView_karte2.setEnabled(false);
        imageView_karte2.setAlpha(0.6f);
        imageView_karte3.setEnabled(false);
        imageView_karte3.setAlpha(0.6f);
        imageView_karte4.setEnabled(false);
        imageView_karte4.setAlpha(0.6f);
        imageView_karte5.setEnabled(false);
        imageView_karte5.setAlpha(0.6f);
        imageView_karte6.setEnabled(false);
        imageView_karte6.setAlpha(0.6f);

        button20er.setEnabled(false);
        button20er.setAlpha(0.4f);
        button40er.setEnabled(false);
        button40er.setAlpha(0.4f);
        buttonWeiter.setEnabled(false);
        buttonWeiter.setAlpha(0.4f);
    }

    private static void handAktualisieren() {
        int handkartenAnz = selbst.Hand.size();
        for (int i = 0; i < 6; i++) {
            //Button buttonK = handkartenButtons.get(i);
            ImageView imageViewK = handkartenImages.get(i);


            if (i < handkartenAnz) {
                Karte k = selbst.Hand.get(i);
                imageViewK.setImageResource(k.getImageResourceId());
                imageViewK.setVisibility(View.VISIBLE);
            } else {
                imageViewK.setVisibility(View.INVISIBLE);
            }

        }

        gegnerischeHandAktualisieren();


    }

    private static void gegnerischeHandAktualisieren() {
        String gegnerischeHand1 = "";
        String gegKartenSpielBar1 = "";
        String gegnerischeHand2 = "";
        String gegKartenSpielBar2 = "";
        int gegnerischeHandkartenAnz1 = gegner1.Hand.size();
        int gegnerischeHandkartenAnz2 = gegner2.Hand.size();
        
        for (int i=0;i<gegnerischeHandkartenAnz1;i++) {
            gegnerischeHand1 += ","+gegner1.Hand.get(i).toString();
            gegKartenSpielBar1 += " "+(spiel.DarfKarteAuswaehlen(gegner1.Hand.get(i), gegner1) ? 1 : 0);
        }
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (HANDKARTEN+":"+gegnerischeHand1+":"+gegKartenSpielBar1+":1").getBytes());
        for (int i=0;i<gegnerischeHandkartenAnz2;i++) {
            gegnerischeHand2 += ","+gegner2.Hand.get(i).toString();
            gegKartenSpielBar2 += " "+(spiel.DarfKarteAuswaehlen(gegner2.Hand.get(i), gegner1) ? 1 : 0);
        }
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (HANDKARTEN+":"+gegnerischeHand2+":"+gegKartenSpielBar2+":2").getBytes());

    }
    

    class Zugende implements Runnable {

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
                    //gegner1hat20er();
                    //gegner2hat20er();
                }
            }
        }
    }

    private static void eigenerZug() {
            
        if(hat20er(selbst)) {
            button20er.setEnabled(true);
            button20er.setAlpha(1f);
        }
        else {               
            button20er.setEnabled(false);
            button20er.setAlpha(0.6f);
        }
        if(hat40er(selbst)) {
            button40er.setEnabled(true);
            button40er.setAlpha(1f);
        }
        else {
            button40er.setEnabled(false);
            button40er.setAlpha(0.6f);
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
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (WEITER + ":" + 1 + ":" + hast20er + " " + hast40er + hastDie20er).getBytes());
    }

    private static void gegner2hat20er() {
        int hast20er = hat20er(gegner2) ? 1 : 0;
        int hast40er = hat40er(gegner2) ? 1 : 0;
        String hastDie20er = "";
        ArrayList<String> geg20er = spiel.hat20er(gegner2);
        for(String farbe: geg20er) {
            hastDie20er += " " + farbe;
        }
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (WEITER + ":" + 2 + ":" + hast20er + " " + hast40er + hastDie20er).getBytes());
    }
    


    private static void punkteAktualisieren() {
        int p1 = selbst.getPunkte();
        int p2 = gegner1.getPunkte();
        int p3 = gegner2.getPunkte();
        punkteSelbst.setText(Integer.toString(p1));
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (PUNKTE + ":" + Integer.toString(p1) + " " + Integer.toString(p2) + " " + Integer.toString(p3)).getBytes());
    }

    public void abbrechenSpiel(View v){

        //Screen Lock aktivieren
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Intent i = new Intent(this, Startmenue.class);
        startActivity(i);
        onStop();
        finish();
    }


    private void spielEnde() {
       /* if(spiel.)

        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIELENDE+":"+).getBytes());*/
        
    }
    private void aufdrehen(){
        aufgedrehteKarte = spiel.Aufdrehen();
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TRUMPFKARTE+":"+aufgedrehteKarte.toString()).getBytes());
        spiel.Trumpfansagen(aufgedrehteKarte.getFarbe(),bummerl.getAnzahlSpiele());

        imageView_trumpf.setVisibility(View.VISIBLE);
        imageView_trumpf.setImageResource(aufgedrehteKarte.getImageResourceId());
        imageView_trumpfIcon.setImageResource(aufgedrehteKarte.getIconResourceId());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView_trumpf.setVisibility(View.INVISIBLE);
            }
        }, 2000);

        handAktualisieren();

        buttonWeiter.setText("Weiter");
        buttonWeiter.setEnabled(false);
        buttonWeiter.setAlpha(0.4f);

        if(bummerl.getAnzahlSpiele()%3 == 0){
            buttonWeiter.setEnabled(true);
            buttonWeiter.setAlpha(1f);
            buttonSpielAnsagen.setVisibility(View.VISIBLE);
        }
        else if(bummerl.getAnzahlSpiele()%3 == 1) {
            andererSpielerKannSpielAnsagen(gegner1);
            Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIELANSAGEN + ":" + spieleAnsagbar + ":1").getBytes());
        }
        else{
            andererSpielerKannSpielAnsagen(gegner2);
            Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIELANSAGEN + ":" + spieleAnsagbar + ":2").getBytes());
        }



    }

    private void handKartenKlickbar() {
        int handkartenAnz = selbst.Hand.size();
        for (int i=0;i<6;i++) {

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
