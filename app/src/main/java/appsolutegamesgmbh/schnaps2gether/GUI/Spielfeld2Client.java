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

import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
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
    private static final String HANDKARTEN = "9";
    private static final String TRUMPFKARTE = "10";
    private static final String ZUGENDE = "11";
    private static final String DISCONNECT = "12";
    private static final String STAPELLEER = "13";

    // Identify if the device is the host
    private boolean mIsHost = false;
    private GoogleApiClient mGoogleApiClient;
    private ArrayList<String> endpointIDs;

    private static Context appContext;



    private static ImageView imageView_karte1;
    private static ImageView imageView_karte2;
    private static ImageView imageView_karte3;
    private static ImageView imageView_karte4;
    private static ImageView imageView_karte5;

    private static ArrayList<ImageView> handkartenImages;
    private static ImageView imageView_trumpf;
    private static ImageView imageView_deck;
    private static ImageView imageView_eigeneKarte;
    private static ImageView imageView_karteGegner;
    private static ImageView imageView_trumpfIcon;

    private static Button buttonZudrehen;
    private static Button button20er;
    private static Button button40er;
    private static Button buttonTrumpfTauschen;
    private MenuItem herz20er;
    private MenuItem karo20er;
    private MenuItem pik20er;
    private MenuItem kreuz20er;
    private static Spieler selbst;

    private static ArrayList<Boolean> kartenSpielbar;
    private static Karte gegnerischeKarte;
    private static Karte eigeneKarte;
    private static Karte trumpfkarte;
    private static TextView punkteSelbst;
    private static TextView BpunkteSelbst;
    private static TextView BpunkteGegner;
    private static TextView GegnerName;
    private static TextView Name;

    private static ImageView stichEigeneKarteG;
    private static ImageView stichGegnerKarteG;
    private static ImageView stichDeckG;

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


    private static boolean zugedreht;
    private static boolean hat20er;
    private static boolean hat40er;
    private static ArrayList<String> hab20er;
    private static int stapelKartenAnz;
    private static int p1;
    private static int p2;
    private static int GestochenSelbst;
    private static int GestochenGegner;
    private static int istdran;

    private static Bummerl2 bummerl;

    /*@Override
    public void onStart() {
        super.onStart();

        //Wenn Activity gestartet wird muss eine Verbindung zum GoogleApiClient erfolgen.
        mGoogleApiClient.connect();
    }*/

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

        //Screen lock deaktivieren
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mGoogleApiClient = Lobby.m_GoogleApiClient;
        endpointIDs = Lobby.endpointIds;

        appContext = this.getApplicationContext();
        //endpointIDs.remove(Nearby.Connections.getLocalEndpointId(mGoogleApiClient));

        imageView_karte1 =(ImageView) findViewById(R.id.imageView_karte1);
        imageView_karte2 = (ImageView) findViewById(R.id.imageView_karte2);
        imageView_karte3 = (ImageView) findViewById(R.id.imageView_karte3);
        imageView_karte4 = (ImageView) findViewById(R.id.imageView_karte4);
        imageView_karte5 = (ImageView) findViewById(R.id.imageView_karte5);

        buttonZudrehen = (Button) findViewById(R.id.main_buttonZ);
        button20er = (Button) findViewById(R.id.main_button20er);
        button40er = (Button) findViewById(R.id.main_button40er);
        buttonTrumpfTauschen = (Button) findViewById(R.id.main_buttonTtauschen);

        punkteSelbst = (TextView) findViewById(R.id.txt_BummerZahl);
        BpunkteSelbst = (TextView) findViewById(R.id.txt_PunkteZahl);
        BpunkteGegner = (TextView) findViewById(R.id.txt_BummerlZahlG1);


        imageView_deck = (ImageView) findViewById(R.id.imageView_deck);
        imageView_eigeneKarte = (ImageView) findViewById(R.id.imageView_eigeneKarte);
        imageView_karteGegner = (ImageView) findViewById(R.id.imageView_karteGegner);
        imageView_trumpf = (ImageView) findViewById(R.id.imageView_trumpf);
        imageView_trumpfIcon = (ImageView) findViewById(R.id.imageView_trumpfIcon);


        handkartenImages = new ArrayList<ImageView>();
        handkartenImages.add(0, imageView_karte1);
        handkartenImages.add(1, imageView_karte2);
        handkartenImages.add(2, imageView_karte3);
        handkartenImages.add(3, imageView_karte4);
        handkartenImages.add(4, imageView_karte5);

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

        stichEigeneKarteG =(ImageView) findViewById(R.id.stichEigeneKarteG);
        stichGegnerKarteG =(ImageView) findViewById(R.id.stichGegnerKarteG);
        stichDeckG = (ImageView) findViewById (R.id.stichDeckG);



        spielStart();
    }

    private void zugAusführen(int i) {
        eigeneKarte = selbst.Hand.get(i);
        buttonsNichtKlickbar();
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (KARTEGESPIELT + ":" + eigeneKarte.toString()).getBytes());
        gespielteKarteEntfernen(i);

        imageView_eigeneKarte.setImageResource(eigeneKarte.getImageResourceId());

        //buttonEigeneKarte.setText(k.getFarbe() + k.getWertigkeit());
        if (gegnerischeKarte == null) {
            Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (WEITER+":"+0).getBytes());
        }
    }

    private void eigenerZug() {
        if(gegnerischeKarte == null) {
            if (!zugedreht) {

                buttonZudrehen.setEnabled(true);
                buttonZudrehen.setAlpha(1f);


                if (selbst.Hand.contains(new Karte(trumpfkarte.getFarbe(), "Bube", 2))) {
                    buttonTrumpfTauschen.setEnabled(true);
                    buttonTrumpfTauschen.setAlpha(1f);
                } else {
                    buttonTrumpfTauschen.setEnabled(false);
                    buttonTrumpfTauschen.setAlpha(0.4f);
                }
            }
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

    }

    private void handAktualisieren() {
        int handkartenAnz = selbst.Hand.size();
        for (int i=0;i<5;i++) {

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

    private void punkteAktualisieren() {
        punkteSelbst.setText(Integer.toString(p1));
    }

    private void gespielteKarteEntfernen(int i) {
        handkartenImages.get(i).setVisibility(View.INVISIBLE);
    }

    private void spielStart() {

        selbst = new Spieler();

        stichDeckG.setVisibility(View.INVISIBLE);
        stichEigeneKarteG.setVisibility(View.INVISIBLE);
        stichGegnerKarteG.setVisibility(View.INVISIBLE);

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

        zugedreht = false;
        buttonZudrehen.setEnabled(false);
        buttonZudrehen.setAlpha(1f);
        buttonZudrehen.setText(R.string.buttonZ);
        BpunkteSelbst.setText("0");
        BpunkteGegner.setText("0");
        punkteSelbst.setText("0");
        gegnerischeKarte = null;
        buttonsNichtKlickbar();
    }

    private void internspielStart() {
        selbst = new Spieler();

        zugedreht = false;

        stichDeckG.setVisibility(View.INVISIBLE);
        stichEigeneKarteG.setVisibility(View.INVISIBLE);
        stichGegnerKarteG.setVisibility(View.INVISIBLE);

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

        imageView_deck.setAlpha((float) 1);
        imageView_trumpf.setAlpha((float)1);

        buttonZudrehen.setEnabled(true);
        buttonZudrehen.setAlpha(1f);
        buttonZudrehen.setText(R.string.buttonZ);
        punkteSelbst.setText("0");
        gegnerischeKarte = null;
        buttonsNichtKlickbar();
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


        button20er.setEnabled(false);
        button20er.setAlpha(0.4f);
        button40er.setEnabled(false);
        button40er.setAlpha(0.4f);
        if(!zugedreht) {
            buttonZudrehen.setEnabled(false);
            buttonZudrehen.setAlpha(0.4f);
            buttonTrumpfTauschen.setEnabled(false);
            buttonTrumpfTauschen.setAlpha(0.4f);
        }
    }

    private void handKartenKlickbar() {
        int handkartenAnz = selbst.Hand.size();
        for (int i=0;i<5;i++) {

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

    private void spielEnde(boolean win) {


        if (win) {
            Toast.makeText(appContext, "Gewonnen! Gegner:" + p2 + " Punkte" , Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(appContext, "Verloren! Gegner:" + p2 + " Punkte" , Toast.LENGTH_LONG).show();
        }

        BpunkteSelbst.setText(Integer.toString(bummerl.getPunkteS2()));
        BpunkteGegner.setText(Integer.toString(bummerl.getPunkteS1()));


        imageView_karteGegner.setImageDrawable(null); // Ansicht der Karten wird für nächstes Spiel gelöscht
        imageView_eigeneKarte.setImageDrawable(null);

        gegnerischeKarte = null;

        if(bummerl.istBummerlzuEnde()) {
            if(bummerl.getPunkteS2() >= 7)
                Toast.makeText(appContext, "Gratulation! Bummerl " + bummerl.getPunkteS2() + ":" + bummerl.getPunkteS1() + " gewonnen!" , Toast.LENGTH_LONG).show();
            else
                Toast.makeText(appContext, "Oje! Bummerl " + bummerl.getPunkteS2() + ":" + bummerl.getPunkteS1() + " verloren!" , Toast.LENGTH_LONG).show();


            spielStart();
        }
        else
            internspielStart();

    }

    public void zudrehen(View view) {
        zugedreht = true;
        buttonZudrehen.setEnabled(false);
        buttonZudrehen.setAlpha(0.4f);
        buttonZudrehen.setText("Zugedreht");
        buttonTrumpfTauschen.setEnabled(false);
        buttonTrumpfTauschen.setAlpha(0f);
        imageView_deck.setAlpha((float) 0);
        imageView_trumpf.setAlpha((float) 0);

        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (ZUGEDREHT + ":").getBytes());
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
        ArrayList<String> a = hab20er;
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
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (ANGESAGT40ER + ":").getBytes());
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handKartenKlickbar();
            }
        }, 1000);
        button40er.setEnabled(false);
        button40er.setAlpha(0.4f);
        button20er.setEnabled(false);
        button20er.setAlpha(0.4f);
    }

    public void trumpfkarteTauschen(View view) {
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TRUMPFGETAUSCHT+":").getBytes());
        trumpfkarte = new Karte(trumpfkarte.getFarbe(), "Bube", 2);

        //buttonTrumpfkarte.setText(trumpfkarte.getFarbe() + trumpfkarte.getWertigkeit());
        imageView_trumpf.setImageResource(trumpfkarte.getImageResourceId());
        //imageView_trumpfIcon.setImageResource(trumpfkarte.getIconResourceId()); // Trumpficon

        buttonTrumpfTauschen.setEnabled(false);
        buttonTrumpfTauschen.setAlpha(0.4f);
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
        BpunkteSelbst.setText("0");
        BpunkteGegner.setText("0");
        bummerl = new Bummerl2();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        startActivity(new Intent(Spielfeld2Client.this, Startmenue.class));
        finish();
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
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handKartenKlickbar();
            }
        }, 1000);
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
    public void receiveFromLobby(String endpointID, byte[] payload, boolean isReliable) {
        String message = new String(payload);
        switch ((message.split(":")[0])) {
            case BUMMERL: bummerl = new Bummerl2(message.substring(2));
                //Toast.makeText(appContext, "bummerl: "+message.substring(2), Toast.LENGTH_SHORT).show();
                break;
            case TRUMPFKARTE: trumpfkarte = new Karte(message.split(":")[1]);
                //Toast.makeText(appContext, "trumpfbuttonset "+Boolean.toString(buttonTrumpfkarte!=null), Toast.LENGTH_SHORT).show();
                //buttonTrumpfkarte.setText(trumpfkarte.getFarbe() + trumpfkarte.getWertigkeit());
                imageView_trumpf.setImageResource(trumpfkarte.getImageResourceId());
                imageView_trumpfIcon.setImageResource(trumpfkarte.getIconResourceId()); // Ok wenn Icon hier auch geändert wird?


                break;
            case HANDKARTEN: String[] messageParts = message.split(":");
                stapelKartenAnz = Integer.decode(messageParts[1]);
                String[] hand = messageParts[2].substring(1).split(",");
                String[] spielbar = messageParts[3].substring(1).split(" ");
                //Toast.makeText(appContext, "spielbar: "+messageParts[3], Toast.LENGTH_SHORT).show();
                selbst.Hand = new ArrayList<Karte>();
                kartenSpielbar = new ArrayList<Boolean>();
                for (int i=0; i<hand.length; i++) {
                    //Toast.makeText(appContext, "karte "+i+": "+hand[i], Toast.LENGTH_SHORT).show();
                    selbst.Hand.add(i,new Karte(hand[i]));
                    kartenSpielbar.add(i,spielbar[i].equals("1") ? true : false);
                }
                handAktualisieren();

                break;
            case KARTEGESPIELT: gegnerischeKarte = new Karte(message.substring(2));
              imageView_karteGegner.setImageResource(gegnerischeKarte.getImageResourceId());
                break;
            case WEITER: handKartenKlickbar();
                if (message.substring(2,3).equals("1")) {
                    hat20er = message.substring(4,5).equals("1") ? true : false;
                    hat40er = message.substring(6,7).equals("1") ? true : false;
                    hab20er = new ArrayList<String>();
                    if (hat20er) {
                        String[] meine20er = message.substring(8).split(" ");
                        for (String farbe: meine20er) {
                            hab20er.add(farbe);
                        }
                    }

                    eigenerZug();
                }
                else
                    eigenerZug();
                Toast.makeText(appContext, "Weiter", Toast.LENGTH_SHORT).show();
                break;
            case ZUGEDREHT: zugedreht = true;
                Toast.makeText(appContext, "Zugedreht", Toast.LENGTH_SHORT).show();
                zugedreht = true;
                buttonZudrehen.setEnabled(false);
                buttonZudrehen.setAlpha(0.4f);
                buttonZudrehen.setText("Zugedreht");
                buttonTrumpfTauschen.setEnabled(false);
                buttonTrumpfTauschen.setAlpha(0f);
                imageView_deck.setAlpha((float) 0);
                imageView_trumpf.setAlpha((float) 0);
                buttonZudrehen.setText("Zugedreht");
                break;
            case ANGESAGT40ER: Toast.makeText(appContext, "40er angesagt", Toast.LENGTH_SHORT).show();
                break;
            case ANGESAGT20ER: String farbe = message.substring(2);
                Toast.makeText(appContext, farbe+" 20er angesagt", Toast.LENGTH_SHORT).show();
                break;
            case TRUMPFGETAUSCHT: Toast.makeText(appContext, "Trumpfkarte ausgetauscht", Toast.LENGTH_SHORT).show();
                trumpfkarte = new Karte(trumpfkarte.getFarbe(), "Bube", 2);
                imageView_trumpf.setImageResource(trumpfkarte.getImageResourceId());
                break;
            case ZUGENDE:
                istdran = Integer.decode(message.split(":")[1].split(" ")[2]);
                GestochenSelbst = Integer.decode(message.split(":")[1].split(" ")[0]);
                GestochenGegner = Integer.decode(message.split(":")[1].split(" ")[1]);

                // Execute some code after 2 seconds have passed
                Handler handler = new Handler();
                handler.postDelayed(new Zugende(), 2000);

                break;
            case PUNKTE:
                p1 = Integer.decode(message.split(":")[1].split(" ")[1]);
                punkteAktualisieren();
                p2 = Integer.decode(message.split(":")[1].split(" ")[0]);
                break;
            case SPIELENDE: boolean win = message.substring(2).equals("1") ? true : false;
                spielEnde(win);
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
            case STAPELLEER:
                imageView_deck.setAlpha((float) 0);
                imageView_trumpf.setAlpha((float)0);
            default: break;
        }
    }

    public void abbrechenSpiel(View v){

        //Screen Lock aktivieren
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Intent i = new Intent(this, Startmenue.class);
        startActivity(i);
        onStop();
        finish();
    }

    @Override
    public void onMessageReceived(String endpointID, byte[] payload, boolean isReliable) {
        receiveFromLobby(endpointID, payload, isReliable);
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

            imageView_eigeneKarte.setImageDrawable(null);
            imageView_karteGegner.setImageDrawable(null);

            if(istdran == 0) {
                if (GestochenGegner == 2) {
                    stichEigeneKarteG.setImageResource(eigeneKarte.getImageResourceId());
                    stichGegnerKarteG.setImageResource(gegnerischeKarte.getImageResourceId());
                    stichEigeneKarteG.setVisibility(View.VISIBLE);
                    stichGegnerKarteG.setVisibility(View.VISIBLE);
                } else if (GestochenGegner > 10) {
                    stichDeckG.setImageResource(R.drawable.deck_5);
                } else if (GestochenGegner > 8) {
                    stichDeckG.setImageResource(R.drawable.deck_4);
                } else if (GestochenGegner > 6) {
                    stichDeckG.setImageResource(R.drawable.deck_3);
                    stichDeckG.setVisibility(View.VISIBLE);
                } else if (GestochenGegner > 4) {
                    stichDeckG.setImageResource(R.drawable.deck_2);
                    stichDeckG.setVisibility(View.VISIBLE);
                } else if (GestochenGegner > 2) {
                    stichDeckG.setVisibility(View.VISIBLE);
                    stichDeckG.setImageResource(R.drawable.deck);
                }
            }
            else if(istdran == 1) {

                if (GestochenSelbst > 14) {
                    stichK16.setImageResource(eigeneKarte.getImageResourceId());
                    stichK16.setVisibility(View.VISIBLE);
                    stichK15.setImageResource(gegnerischeKarte.getImageResourceId());
                    stichK15.setVisibility(View.VISIBLE);
                } else if (GestochenSelbst > 12) {
                    stichK14.setImageResource(eigeneKarte.getImageResourceId());
                    stichK14.setVisibility(View.VISIBLE);
                    stichK13.setImageResource(gegnerischeKarte.getImageResourceId());
                    stichK13.setVisibility(View.VISIBLE);
                } else if (GestochenSelbst > 10) {
                    stichK12.setImageResource(eigeneKarte.getImageResourceId());
                    stichK12.setVisibility(View.VISIBLE);
                    stichK11.setImageResource(gegnerischeKarte.getImageResourceId());
                    stichK11.setVisibility(View.VISIBLE);
                } else if (GestochenSelbst > 8) {
                    stichK10.setImageResource(eigeneKarte.getImageResourceId());
                    stichK10.setVisibility(View.VISIBLE);
                    stichK9.setImageResource(gegnerischeKarte.getImageResourceId());
                    stichK9.setVisibility(View.VISIBLE);
                } else if (GestochenSelbst > 6) {
                    stichK8.setImageResource(eigeneKarte.getImageResourceId());
                    stichK8.setVisibility(View.VISIBLE);
                    stichK7.setImageResource(gegnerischeKarte.getImageResourceId());
                    stichK7.setVisibility(View.VISIBLE);
                } else if (GestochenSelbst > 4) {
                    stichK6.setImageResource(eigeneKarte.getImageResourceId());
                    stichK6.setVisibility(View.VISIBLE);
                    stichK5.setImageResource(gegnerischeKarte.getImageResourceId());
                    stichK5.setVisibility(View.VISIBLE);
                } else if (GestochenSelbst > 2) {
                    stichK4.setImageResource(eigeneKarte.getImageResourceId());
                    stichK4.setVisibility(View.VISIBLE);
                    stichK3.setImageResource(gegnerischeKarte.getImageResourceId());
                    stichK3.setVisibility(View.VISIBLE);
                } else if (GestochenSelbst > 0) {
                    stichK2.setImageResource(eigeneKarte.getImageResourceId());
                    stichK2.setVisibility(View.VISIBLE);
                    stichK1.setImageResource(gegnerischeKarte.getImageResourceId());
                    stichK1.setVisibility(View.VISIBLE);
                }
            }

            punkteAktualisieren();
            gegnerischeKarte = null;
        }
    }
}
