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
    private static final String NAMENGEGNER = "19";

    // Identify if the device is the host
    private boolean mIsHost = false;
    private static GoogleApiClient mGoogleApiClient;
    private static ArrayList<String> endpointIDs;

    private static Context appContext;

    private static ArrayList<Boolean> kartenSpielbar;
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

    private static ImageView imageView_talonkarte1;
    private static ImageView imageView_talonkarte2;

    private static ImageView imageView_Stich1;
    private static ImageView imageView_Stich2;
    private static ImageView imageView_Stich3;

    private static Button button20er;
    private static Button button40er;
    private static ArrayList<String> spieleAnsagbar;

    private static Button buttonTalonTauschen;
    private static Button buttonSpielAnsagen;
    private static Button buttonTrumpfansagen;
    private static Button buttonFlecken;
    private static Button buttonGegenflecken;
    private static Button buttonWeiter;
    private MenuItem herz20er;
    private MenuItem karo20er;
    private MenuItem pik20er;
    private MenuItem kreuz20er;
    private static Spieler selbst;

    private static boolean hat20er;
    private static boolean hat40er;
    private static ArrayList<String> hab20er;


    private static int istdran;
    private static String SpielerID;


    //ausgespielte Karten
    private static Karte eigeneKarte;
    private static Karte gegnerischeKarte1;
    private static Karte gegnerischeKarte2;
    private static Karte trumpfkarte;
    private static String angesagteFarbe;

    private static TextView punkteGegner1;
    private static TextView punkteGegner2;
    private static TextView punkteSelbst;
    private static TextView BpunkteGegner1;
    private static TextView BpunkteGegner2;
    private static TextView BpunkteSelbst;
    private TextView txtSelbst;
    private TextView txtGegner1;
    private TextView txtGegner2;
    private static Bummerl3 bummerl;

    private static int p0;
    private static int p1;
    private static int p2;

    private static Handler handler = new Handler();


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

    private static TextView txt_Gegner1Name;
    private static TextView txt_BummerlNameGegner1;
    private static TextView txt_Gegner2Name;
    private static TextView txt_BummerlNameGegner2;
    private static TextView txt_BummerlMeinName;

    private static ArrayList<Karte> Talon;
    private static String talonID;
    private static boolean talontauschen;
    private static Karte ka, t;

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


        appContext = this.getApplicationContext();

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
        imageView_karteGegner2 = (ImageView) findViewById(R.id.imageView_karteGegner2);
        imageView_trumpf = (ImageView) findViewById(R.id.imageView_trumpf);
        imageView_trumpfIcon = (ImageView) findViewById(R.id.imageView_trumpfIcon);

        imageView_talonkarte1 =(ImageView)findViewById(R.id.imageView_talonkarte1);
        imageView_talonkarte2 =(ImageView)findViewById(R.id.imageView_talonkarte2);

        //imageView_Stich1 = (ImageView) findViewById(R.id.imageView_Stich1);
        //imageView_Stich2 = (ImageView) findViewById(R.id.imageView_Stich2);
        //imageView_Stich3 = (ImageView) findViewById(R.id.imageView_Stich3);

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

        txt_Gegner1Name = (TextView) findViewById(R.id.txt_NameGegner2);
        txt_Gegner2Name = (TextView) findViewById(R.id.txt_NameGegner1);
        txt_BummerlMeinName = (TextView) findViewById(R.id.txt_BummerlNameI);
        txt_BummerlNameGegner1 = (TextView) findViewById(R.id.txt_BummerlNameG1);
        txt_BummerlNameGegner2 = (TextView) findViewById(R.id.txt_BummerlNameG2);

        spielStart();

    }

    private void spielStart() {

        selbst = new Spieler();

        //Name an Host senden
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (NAMENGEGNER + ":" + Startmenue.SpielerName).getBytes());

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

        BpunkteSelbst.setText("0");
        BpunkteGegner1.setText("0");
        BpunkteGegner2.setText("0");
        punkteSelbst.setText("0");
        gegnerischeKarte1 = null;
        gegnerischeKarte2 = null;
        buttonsNichtKlickbar();


        if(Lobby.isc1)
            SpielerID = "1";
        else if(Lobby.isc2)
            SpielerID = "2";


    }

    public void popupSpielAnsagen(final View view) {
        PopupMenu popup = new PopupMenu(Spielfeld3Client.this, buttonSpielAnsagen);
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
            if (spieleAnsagbar.get(0).equals("1")) schnapser.setVisible(true);
            if (spieleAnsagbar.get(1).equals("1")) land.setVisible(true);
            if (spieleAnsagbar.get(2).equals("1")) kontraschnapser.setVisible(true);
            if (spieleAnsagbar.get(3).equals("1")) bauernschnapser.setVisible(true);
            if (spieleAnsagbar.get(4).equals("1")) kontrabauernschnapser.setVisible(true);
        } catch (Exception e) {

        }
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                try {
                    switch (menuItem.getTitle().toString()) {
                        case "Schnapser":
                            Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIEL + ":" + "Schnapser" + ":" + SpielerID).getBytes());
                            break;
                        case "Land":
                            Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIEL + ":" + "Land" + ":" + SpielerID).getBytes());
                            break;
                        case "Kontraschnapser":
                            Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIEL + ":" + "Kontraschnapser" + ":" + SpielerID).getBytes() );
                            break;
                        case "Bauernschnapser":
                            Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIEL + ":" + "Bauernschnapser" + ":" + SpielerID).getBytes());
                            break;
                        case "Kontrabauernschnapser":
                            Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIEL + ":" + "Kontrabauernschnapser" + ":" + SpielerID).getBytes());
                            break;
                        default: return false;
                    }
                } catch (Exception e) {

                }

                buttonSpielAnsagen.setVisibility(view.INVISIBLE);
                buttonWeiter.setEnabled(false);
                buttonWeiter.setAlpha(0.4f);
                return true;
            }
        });
        popup.show();
    }


    public void popupTrumpfansagen(View view) {
        PopupMenu popup = new PopupMenu(Spielfeld3Client.this, buttonTrumpfansagen);
        popup.inflate(R.menu.popup_menu_trumpfansagen);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                try {
                    switch (menuItem.getTitle().toString()) {
                        case "Herz":
                            imageView_trumpfIcon.setImageResource(Karte.getIconResourceId("Herz"));
                            Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TRUMPFFARBE+":Herz").getBytes());
                            break;
                        case "Karo":
                            imageView_trumpfIcon.setImageResource(Karte.getIconResourceId("Karo"));
                            Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TRUMPFFARBE+":Karo").getBytes());
                            break;
                        case "Pik":
                            imageView_trumpfIcon.setImageResource(Karte.getIconResourceId("Pik"));
                            Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TRUMPFFARBE+":Pik").getBytes());
                            break;
                        case "Kreuz":
                            imageView_trumpfIcon.setImageResource(Karte.getIconResourceId("Kreuz"));
                            Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TRUMPFFARBE+":Kreuz").getBytes());
                            break;
                        default: return false;
                    }
                } catch (Exception e) {

                }

                buttonWeiter.setText("Weiter");
                buttonWeiter.setEnabled(false);
                buttonWeiter.setAlpha(0.4f);
                buttonTrumpfansagen.setVisibility(View.INVISIBLE);
                buttonTrumpfansagen.setEnabled(false);
                return true;
            }
        });
        popup.show();
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

        Intent i = new Intent(Spielfeld3Client.this, Startmenue.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
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
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (ANGESAGT20ER+":"+"Herz").getBytes());
                break;
            case R.id.karo_20er:
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (ANGESAGT20ER+":"+"Karo").getBytes());
                break;
            case R.id.pik_20er:
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (ANGESAGT20ER+":"+"Pik").getBytes());
                break;
            case R.id.kreuz_20er:
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (ANGESAGT20ER+":"+"Kreuz").getBytes());
                break;
            default:
                return false;
        }
        button20er.setEnabled(false);
        button20er.setAlpha(0.4f);
        button40er.setEnabled(false);
        button40er.setAlpha(0.4f);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handKartenKlickbar();
            }
        }, 1000);
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
        String[] messageParts = message.split(":");
        switch ((message.split(":")[0])) {
            case BUMMERL: bummerl = new Bummerl3(message.substring(2));
                break;
            case TRUMPFANSAGEN:
                buttonWeiter.setText("Aufdecken");
                buttonTrumpfansagen.setEnabled(true);
                buttonTrumpfansagen.setVisibility(View.VISIBLE);
                break;
            case TRUMPFKARTE:
                trumpfkarte = new Karte(message.split(":")[1]);
                imageView_trumpf.setVisibility(View.VISIBLE);
                imageView_trumpf.setImageResource(trumpfkarte.getImageResourceId());
                imageView_trumpfIcon.setImageResource(trumpfkarte.getIconResourceId());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageView_trumpf.setVisibility(View.INVISIBLE);
                    }
                }, 2000);
                break;
            case HANDKARTEN:
                if(messageParts[3].equals(SpielerID)) {
                    String[] hand = messageParts[1].substring(1).split(",");
                    String[] spielbar = messageParts[2].substring(1).split(" ");
                    selbst.Hand = new ArrayList<Karte>();
                    kartenSpielbar = new ArrayList<Boolean>();
                    for (int i = 0; i < hand.length; i++) {
                        selbst.Hand.add(i, new Karte(hand[i]));
                        kartenSpielbar.add(i, spielbar[i].equals("1") ? true : false);
                    }
                    handAktualisieren();
                }
                break;
            case KARTEGESPIELT:
                if(SpielerID.equals("1")) {
                    if(messageParts[2].equals("2")) {
                        gegnerischeKarte1 = new Karte(messageParts[1]);
                        imageView_karteGegner1.setVisibility(View.VISIBLE);
                        imageView_karteGegner1.setImageResource(gegnerischeKarte1.getImageResourceId());
                    }
                    else if(messageParts[2].equals("0")) {
                        gegnerischeKarte2 = new Karte(messageParts[1]);
                        imageView_karteGegner2.setVisibility(View.VISIBLE);
                        imageView_karteGegner2.setImageResource(gegnerischeKarte2.getImageResourceId());
                    }
                }
                else if(SpielerID.equals("2")) {
                    if(messageParts[2].equals("0")) {
                        gegnerischeKarte1 = new Karte(messageParts[1]);
                        imageView_karteGegner1.setVisibility(View.VISIBLE);
                        imageView_karteGegner1.setImageResource(gegnerischeKarte1.getImageResourceId());
                    }
                    else if(messageParts[2].equals("1")) {
                        gegnerischeKarte2 = new Karte(messageParts[1]);
                        imageView_karteGegner2.setVisibility(View.VISIBLE);
                        imageView_karteGegner2.setImageResource(gegnerischeKarte2.getImageResourceId());
                    }
                }
                break;
            case TRUMPFFARBE: angesagteFarbe = messageParts[1];
                imageView_trumpfIcon.setImageResource(Karte.getIconResourceId(angesagteFarbe));
                break;
            case WEITER:
                if(messageParts[1].equals(SpielerID)) {
                    handKartenKlickbar();
                    if (messageParts[2].equals("1")) {
                        hat20er = messageParts[3].equals("1") ? true : false;
                        hat40er = messageParts[4].equals("1") ? true : false;
                        hab20er = new ArrayList<String>();
                        if (hat20er) {
                            String[] meine20er = messageParts[5].split(" ");
                            for (String farbe : meine20er) {
                                hab20er.add(farbe);
                            }
                        }

                        eigenerZug();

                    } else
                        eigenerZug();
                }

                break;
            case SPIELANSAGEN:
                if(messageParts[2].equals(SpielerID)) {
                    spieleAnsagbar = new ArrayList<String>();
                    for (String ansagbar : messageParts[1].split(" ")) {
                        spieleAnsagbar.add(ansagbar);
                    }
                    buttonSpielAnsagen.setVisibility(View.VISIBLE);
                    buttonWeiter.setVisibility(View.VISIBLE);
                    buttonWeiter.setEnabled(true);
                    buttonWeiter.setAlpha(1f);
                }
                break;
            case SPIEL:
                Toast.makeText(appContext, messageParts[1] + " wird gespielt", Toast.LENGTH_SHORT).show();
                break;
            case ANGESAGT40ER: Toast.makeText(appContext, "40er angesagt", Toast.LENGTH_SHORT).show();
                break;
            case ANGESAGT20ER: String farbe = message.substring(2);
                Toast.makeText(appContext, farbe+" 20er angesagt", Toast.LENGTH_SHORT).show();
                break;
            case TALONTAUSCHEN:
                if(messageParts[2].equals(SpielerID)){
                    talontauschen = true;
                    talonID = "0";
                    Talon = new ArrayList<Karte>(2);
                    Talon.add(new Karte(messageParts[1].split(",")[0]));
                    Talon.add(new Karte(messageParts[1].split(",")[1]));
                    imageView_talonkarte1.setVisibility(View.VISIBLE);
                    imageView_talonkarte1.setEnabled(true);
                    imageView_talonkarte1.setImageResource(Talon.get(0).getImageResourceId());
                    imageView_talonkarte1.setAlpha(0.6f);
                    imageView_talonkarte2.setVisibility(View.VISIBLE);
                    imageView_talonkarte2.setEnabled(true);
                    imageView_talonkarte2.setImageResource(Talon.get(1).getImageResourceId());
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

                    buttonWeiter.setEnabled(true);
                    buttonWeiter.setAlpha(1f);
                }
                break;
            case TALONGETAUSCHT: Toast.makeText(appContext, "Talonkarte ausgetauscht", Toast.LENGTH_SHORT).show();
                break;
            case ZUGENDE:
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageView_eigeneKarte.setVisibility(View.INVISIBLE);
                        imageView_karteGegner1.setVisibility(View.INVISIBLE);
                        imageView_karteGegner2.setVisibility(View.INVISIBLE);
                        eigeneKarte = null;
                        gegnerischeKarte1 = null;
                        gegnerischeKarte2 = null;
                    }
                }, 1000);

                break;
            case PUNKTE:
                p2 = Integer.decode(messageParts[1].split(" ")[2]);
                p1 = Integer.decode(messageParts[1].split(" ")[1]);
                p0 = Integer.decode(messageParts[1].split(" ")[0]);
                punkteAktualisieren();
                break;
            case SPIELENDE:

                break;
            case DISCONNECT: Toast.makeText(appContext, "Verbindungsverlust eines Spielers - Das Spiel wird beendet...", Toast.LENGTH_SHORT).show();
                // Execute some code after 2 seconds have passed
                Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // finish();
                    }
                }, 2000);
                break;
            case NAMENGEGNER:

                String nameHost = messageParts[1];
                String nameGegner = messageParts[2];

                //nameGegner nicht mein eigener Name --> ich bin Spieler 3
                if(nameGegner != Startmenue.SpielerName)
                {
                    txt_Gegner1Name.setText(nameHost);
                    txt_Gegner2Name.setText(nameGegner);
                    txt_BummerlMeinName.setText(Startmenue.SpielerName);
                    txt_BummerlNameGegner1.setText(nameHost);
                    txt_BummerlNameGegner2.setText(nameGegner);
                }

                //nameGegner = mein eigener Name --> ich bin Spieler 2
                else
                {
                    nameGegner = messageParts[3];
                    txt_Gegner1Name.setText(nameGegner);
                    txt_Gegner2Name.setText(nameHost);
                    txt_BummerlMeinName.setText(Startmenue.SpielerName);
                    txt_BummerlNameGegner1.setText(nameGegner);
                    txt_BummerlNameGegner2.setText(nameHost);
                }

                break;
            default: break;
        }
    }

    public void abbrechenSpiel(View v){

        //Screen Lock aktivieren
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Intent i = new Intent(this, Startmenue.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        onStop();
        finish();
    }

    private void handAktualisieren() {
        int handkartenAnz = selbst.Hand.size();
        for (int i=0;i<6;i++) {

            ImageView imageViewK = handkartenImages.get(i);
            if (i<handkartenAnz) {
                Karte k = selbst.Hand.get(i);

                imageViewK.setImageResource(k.getImageResourceId());
                imageViewK.setVisibility(View.VISIBLE);

            } else {
                imageViewK.setVisibility(View.INVISIBLE);
            }
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
                ka = Talon.get(0);
                imageView_karte1.setImageResource(ka.getImageResourceId());
                imageView_karte1.setAlpha(0.6f);

                t = selbst.Hand.get(0);
                imageView_talonkarte1.setImageResource(t.getImageResourceId());
                imageView_talonkarte1.setAlpha(0.6f);

                Talon.set(0, t);
                selbst.Hand.set(0, ka);
                talonID = "0";
            } else if (talonID.equals("12")) {
                ka = Talon.get(1);
                imageView_karte1.setImageResource(ka.getImageResourceId());
                imageView_karte1.setAlpha(0.6f);

                t = selbst.Hand.get(0);
                imageView_talonkarte1.setImageResource(t.getImageResourceId());
                imageView_talonkarte1.setAlpha(0.6f);

                Talon.set(1, t);
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
                ka = Talon.get(0);
                imageView_karte2.setImageResource(ka.getImageResourceId());
                imageView_karte2.setAlpha(0.6f);

                t = selbst.Hand.get(1);
                imageView_talonkarte1.setImageResource(t.getImageResourceId());
                imageView_talonkarte1.setAlpha(0.6f);

                Talon.set(0, t);
                selbst.Hand.set(1, ka);
                talonID = "0";
            } else if (talonID.equals("12")) {
                ka = Talon.get(1);
                imageView_karte2.setImageResource(ka.getImageResourceId());
                imageView_karte2.setAlpha(0.6f);

                t = selbst.Hand.get(1);
                imageView_talonkarte2.setImageResource(t.getImageResourceId());
                imageView_talonkarte2.setAlpha(0.6f);

                Talon.set(1, t);
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
                ka = Talon.get(0);
                imageView_karte3.setImageResource(ka.getImageResourceId());
                imageView_karte3.setAlpha(0.6f);

                t = selbst.Hand.get(2);
                imageView_talonkarte1.setImageResource(t.getImageResourceId());
                imageView_talonkarte1.setAlpha(0.6f);

                Talon.set(0, t);
                selbst.Hand.set(2, ka);
                talonID = "0";
            } else if (talonID.equals("12")) {
                ka = Talon.get(1);
                imageView_karte3.setImageResource(ka.getImageResourceId());
                imageView_karte3.setAlpha(0.6f);

                t = selbst.Hand.get(2);
                imageView_talonkarte2.setImageResource(t.getImageResourceId());
                imageView_talonkarte2.setAlpha(0.6f);

                Talon.set(1, t);
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
                ka = Talon.get(0);
                imageView_karte4.setImageResource(ka.getImageResourceId());
                imageView_karte4.setAlpha(0.6f);

                t = selbst.Hand.get(3);
                imageView_talonkarte1.setImageResource(t.getImageResourceId());
                imageView_talonkarte1.setAlpha(0.6f);

                Talon.set(0, t);
                selbst.Hand.set(3, ka);
                talonID = "0";
            } else if (talonID.equals("12")) {
                ka = Talon.get(1);
                imageView_karte4.setImageResource(ka.getImageResourceId());
                imageView_karte4.setAlpha(0.6f);

                t = selbst.Hand.get(3);
                imageView_talonkarte2.setImageResource(t.getImageResourceId());
                imageView_talonkarte2.setAlpha(0.6f);

                Talon.set(1, t);
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
                ka = Talon.get(0);
                imageView_karte5.setImageResource(ka.getImageResourceId());
                imageView_karte5.setAlpha(0.6f);

                t = selbst.Hand.get(4);
                imageView_talonkarte1.setImageResource(t.getImageResourceId());
                imageView_talonkarte1.setAlpha(0.6f);

                Talon.set(0, t);
                selbst.Hand.set(4, ka);
                talonID = "0";
            } else if (talonID.equals("12")) {
                ka = Talon.get(1);
                imageView_karte5.setImageResource(ka.getImageResourceId());
                imageView_karte5.setAlpha(0.6f);

                t = selbst.Hand.get(4);
                imageView_talonkarte2.setImageResource(t.getImageResourceId());
                imageView_talonkarte2.setAlpha(0.6f);

                Talon.set(1, t);
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
            zugAusführen(5);
        else {
            if (talonID.equals("0")) {
                imageView_karte6.setAlpha(1f);
                talonID = "26";
            } else if (talonID.equals("11")) {
                ka = Talon.get(0);
                imageView_karte6.setImageResource(ka.getImageResourceId());
                imageView_karte6.setAlpha(0.6f);

                t = selbst.Hand.get(5);
                imageView_talonkarte1.setImageResource(t.getImageResourceId());
                imageView_talonkarte1.setAlpha(0.6f);

                Talon.set(0, t);
                selbst.Hand.set(5, ka);
                talonID = "0";
            } else if (talonID.equals("12")) {
                ka = Talon.get(1);
                imageView_karte6.setImageResource(ka.getImageResourceId());
                imageView_karte6.setAlpha(0.6f);

                t = selbst.Hand.get(5);
                imageView_talonkarte2.setImageResource(t.getImageResourceId());
                imageView_talonkarte2.setAlpha(0.6f);

                Talon.set(1, t);
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
            ka = Talon.get(0);
            imageView_karte1.setImageResource(ka.getImageResourceId());
            imageView_karte1.setAlpha(0.6f);

            t = selbst.Hand.get(0);
            imageView_talonkarte1.setImageResource(t.getImageResourceId());
            imageView_talonkarte1.setAlpha(0.6f);

            Talon.set(0,t);
            selbst.Hand.set(0,ka);
            talonID = "0";
        } else if (talonID.equals("22")) {
            ka = Talon.get(0);
            imageView_karte2.setImageResource(ka.getImageResourceId());
            imageView_karte2.setAlpha(0.6f);

            t = selbst.Hand.get(1);
            imageView_talonkarte1.setImageResource(t.getImageResourceId());
            imageView_talonkarte1.setAlpha(0.6f);

            Talon.set(0, t);
            selbst.Hand.set(1, ka);
            talonID = "0";
        } else if (talonID.equals("23")) {
            ka = Talon.get(0);
            imageView_karte3.setImageResource(ka.getImageResourceId());
            imageView_karte3.setAlpha(0.6f);

            t = selbst.Hand.get(2);
            imageView_talonkarte1.setImageResource(t.getImageResourceId());
            imageView_talonkarte1.setAlpha(0.6f);

            Talon.set(0,t);
            selbst.Hand.set(2,ka);
            talonID = "0";
        } else if (talonID.equals("24")) {
            ka = Talon.get(0);
            imageView_karte4.setImageResource(ka.getImageResourceId());
            imageView_karte4.setAlpha(0.6f);

            t = selbst.Hand.get(3);
            imageView_talonkarte1.setImageResource(t.getImageResourceId());
            imageView_talonkarte1.setAlpha(0.6f);

            Talon.set(0,t);
            selbst.Hand.set(3,ka);
            talonID = "0";
        }else if (talonID.equals("25")) {
            ka = Talon.get(0);
            imageView_karte5.setImageResource(ka.getImageResourceId());
            imageView_karte5.setAlpha(0.6f);

            t = selbst.Hand.get(4);
            imageView_talonkarte1.setImageResource(t.getImageResourceId());
            imageView_talonkarte1.setAlpha(0.6f);

            Talon.set(0,t);
            selbst.Hand.set(4,ka);
            talonID = "0";
        }else if (talonID.equals("26")) {
            ka = Talon.get(0);
            imageView_karte6.setImageResource(ka.getImageResourceId());
            imageView_karte6.setAlpha(0.6f);

            t = selbst.Hand.get(5);
            imageView_talonkarte1.setImageResource(t.getImageResourceId());
            imageView_talonkarte1.setAlpha(0.6f);

            Talon.set(0, t);
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
            ka = Talon.get(1);
            imageView_karte1.setImageResource(ka.getImageResourceId());
            imageView_karte1.setAlpha(0.6f);

            t = selbst.Hand.get(0);
            imageView_talonkarte2.setImageResource(t.getImageResourceId());
            imageView_talonkarte2.setAlpha(0.6f);

            Talon.set(1,t);
            selbst.Hand.set(0,ka);
            talonID = "0";
        } else if (talonID.equals("22")) {
            ka = Talon.get(1);
            imageView_karte2.setImageResource(ka.getImageResourceId());
            imageView_karte2.setAlpha(0.6f);

            t = selbst.Hand.get(1);
            imageView_talonkarte2.setImageResource(t.getImageResourceId());
            imageView_talonkarte2.setAlpha(0.6f);

            Talon.set(1, t);
            selbst.Hand.set(1, ka);
            talonID = "0";
        } else if (talonID.equals("23")) {
            ka = Talon.get(1);
            imageView_karte3.setImageResource(ka.getImageResourceId());
            imageView_karte3.setAlpha(0.6f);

            t = selbst.Hand.get(2);
            imageView_talonkarte2.setImageResource(t.getImageResourceId());
            imageView_talonkarte2.setAlpha(0.6f);

            Talon.set(1,t);
            selbst.Hand.set(2,ka);
            talonID = "0";
        } else if (talonID.equals("24")) {
            ka = Talon.get(1);
            imageView_karte4.setImageResource(ka.getImageResourceId());
            imageView_karte4.setAlpha(0.6f);

            t = selbst.Hand.get(3);
            imageView_talonkarte2.setImageResource(t.getImageResourceId());
            imageView_talonkarte2.setAlpha(0.6f);

            Talon.set(1,t);
            selbst.Hand.set(3,ka);
            talonID = "0";
        }else if (talonID.equals("25")) {
            ka = Talon.get(1);
            imageView_karte5.setImageResource(ka.getImageResourceId());
            imageView_karte5.setAlpha(0.6f);

            t = selbst.Hand.get(4);
            imageView_talonkarte2.setImageResource(t.getImageResourceId());
            imageView_talonkarte2.setAlpha(0.6f);

            Talon.set(1,t);
            selbst.Hand.set(4,ka);
            talonID = "0";
        }else if (talonID.equals("26")) {
            ka = Talon.get(1);
            imageView_karte6.setImageResource(ka.getImageResourceId());
            imageView_karte6.setAlpha(0.6f);

            t = selbst.Hand.get(5);
            imageView_talonkarte2.setImageResource(t.getImageResourceId());
            imageView_talonkarte2.setAlpha(0.6f);

            Talon.set(1, t);
            selbst.Hand.set(5, ka);
            talonID = "0";
        }else if(talonID.equals("12")){
            imageView_talonkarte2.setAlpha(0.6f);
            talonID = "0";
        }else
            Toast.makeText(appContext, "Wählen Sie eine Handkarte zum Tauschen!", Toast.LENGTH_SHORT).show();
    }

    public void weiterOnClick(View view) {
        if (buttonWeiter.getText() == "Aufdecken"){
            Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (AUFGEDECKT).getBytes());
            buttonWeiter.setText("Weiter");
            buttonWeiter.setEnabled(false);
            buttonWeiter.setAlpha(0.4f);
            buttonTrumpfansagen.setVisibility(View.INVISIBLE);
            buttonTrumpfansagen.setEnabled(false);
        }else if (talontauschen) {
            talontauschen = false;
            imageView_talonkarte1.setVisibility(View.INVISIBLE);
            imageView_talonkarte2.setVisibility(View.INVISIBLE);
            buttonWeiter.setEnabled(false);
            buttonWeiter.setAlpha(0.4f);
            Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TALONGETAUSCHT + ":" + SpielerID).getBytes());
        }
        else{
            Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIEL + ":" + "weiter" + ":" + SpielerID).getBytes());
            buttonWeiter.setEnabled(false);
            buttonWeiter.setAlpha(0.4f);
            buttonSpielAnsagen.setVisibility(View.INVISIBLE);
            buttonSpielAnsagen.setEnabled(false);
        }
    }

    private void zugAusführen(int i) {
        eigeneKarte = selbst.Hand.get(i);
        buttonsNichtKlickbar();
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (KARTEGESPIELT + ":" + eigeneKarte.toString() + ":" + SpielerID).getBytes());
        gespielteKarteEntfernen(i);

        imageView_eigeneKarte.setVisibility(View.VISIBLE);
        imageView_eigeneKarte.setImageResource(eigeneKarte.getImageResourceId());


        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (WEITER + ":" + SpielerID).getBytes());


        //buttonEigeneKarte.setText(k.getFarbe() + k.getWertigkeit());
        /*if (gegnerischeKarte == null) {
            Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (WEITER+":"+0).getBytes());
        }*/
    }

    private void gespielteKarteEntfernen(int i) {
        handkartenImages.get(i).setVisibility(View.INVISIBLE);
    }

    private void punkteAktualisieren() {
        if (SpielerID.equals("1"))
            punkteSelbst.setText(Integer.toString(p1));
        else if (SpielerID.equals("2"))
            punkteSelbst.setText(Integer.toString(p2));
    }

    private void eigenerZug()
    {

        if (hat20er) {
            button20er.setEnabled(true);
            button20er.setAlpha(1f);
        } else {
            button20er.setEnabled(false);
            button20er.setAlpha(0.4f);
        }
        if (hat40er) {
            button40er.setEnabled(true);
            button40er.setAlpha(1f);
        } else {
            button40er.setEnabled(false);
            button40er.setAlpha(0.4f);
        }
    }


    private void handKartenKlickbar() {
        int handkartenAnz = selbst.Hand.size();
        for (int i=0;i<6;i++) {

            //Button buttonK = handkartenButtons.get(i);
            ImageView imageViewK = handkartenImages.get(i);
            if (i<handkartenAnz && kartenSpielbar.get(i)) {
                imageViewK.setEnabled(true);
                imageViewK.setAlpha(1f); // Karte die klickbar ist, ist auch sichtbar
                //buttonK.setEnabled(true);
            } else {
                imageViewK.setEnabled(false);
                imageViewK.setAlpha(0.4f); // Karte die nicht klickbar ist ist transparent (alpha =0 wäre durchsichtig)
                // buttonK.setEnabled(false);

            }
        }
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

    private void spielEnde(boolean win) {


        if (win) {
            Toast.makeText(appContext, "Gewonnen! Gegner:" + p2 + " Punkte" , Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(appContext, "Verloren! Gegner:" + p2 + " Punkte" , Toast.LENGTH_LONG).show();
        }

        BpunkteSelbst.setText(Integer.toString(bummerl.getPunkteS2()));
        BpunkteGegner1.setText(Integer.toString(bummerl.getPunkteS1()));
        BpunkteGegner2.setText(Integer.toString(bummerl.getPunkteS1()));


        imageView_karteGegner1.setImageDrawable(null); // Ansicht der Karten wird für nächstes Spiel gelöscht
        imageView_karteGegner2.setImageDrawable(null);
        imageView_eigeneKarte.setImageDrawable(null);

        gegnerischeKarte1 = null;
        gegnerischeKarte2 = null;

        if(bummerl.istBummerlzuEnde()) {
            if(bummerl.getPunkteS2() >= 24)
                Toast.makeText(appContext, "Gratulation! Bummerl " + bummerl.getPunkteS2() + ":" + bummerl.getPunkteS1() + " gewonnen!" , Toast.LENGTH_LONG).show();
            else
                Toast.makeText(appContext, "Oje! Bummerl " + bummerl.getPunkteS2() + ":" + bummerl.getPunkteS1() + " verloren!" , Toast.LENGTH_LONG).show();


            spielStart();
        }
        //else
            //internspielStart();

    }


    class Zugende implements Runnable {

        @Override
        public void run() {
            punkteAktualisieren();
            gegnerischeKarte1 = null;
            gegnerischeKarte2 = null;
            eigeneKarte = null;
        }
    }

}
