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

import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Rufspiel;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spieler;
import appsolutegamesgmbh.schnaps2gether.R;

/**
 * Created by kirederf on 24.05.2015.
 */
public class Spielfeld4Client extends Activity implements PopupMenu.OnMenuItemClickListener, GameEnd.GameEndDialogListener,
        Connections.MessageListener {

    //Konstanten f�r das Kennzeichnen und Parsen von Nachrichten
    private static final String KARTEGESPIELT = "0";
    private static final String WEITER = "1";
    private static final String PUNKTE = "2";
    private static final String TRUMPFANSAGEN = "3";
    private static final String ANGESAGT20ER = "4";
    private static final String ANGESAGT40ER = "5";
    private static final String SPIELANSAGEN = "6";
    private static final String SPIELENDE = "7";
    private static final String BUMMERL = "8";
    private static final String HANDKARTEN = "9";
    private static final String TRUMPFFARBE = "10";
    private static final String ZUGENDE = "11";
    private static final String DISCONNECT = "12";
    private static final String AUFDREHEN = "13";

    // Identify if the device is the host
    private boolean mIsHost = false;
    private GoogleApiClient mGoogleApiClient;
    private ArrayList<String> endpointIDs;

    private static Context appContext;



    private ImageView imageView_karte1;
    private  ImageView imageView_karte2;
    private  ImageView imageView_karte3;
    private  ImageView imageView_karte4;
    private  ImageView imageView_karte5;

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
    private Karte karte1;
    private Karte karte2;
    private Karte karte3;
    private Karte karte4;
    private Karte karte5;
    private static ArrayList<Boolean> kartenSpielbar;
    private static Karte gegnerischeKarte;
    private static Karte trumpfkarte;
    private static TextView punkteGegner;
    private static TextView punkteSelbst;
    private TextView txtSelbst;
    private TextView txtGegner;
    private static Bummerl2 bummerl;
    private static boolean zugedreht;
    private static boolean hat20er;
    private static boolean hat40er;
    private static ArrayList<String> hab20er;
    private static int stapelKartenAnz;
    private static int p1;
    private static int p2;
    private static String angesagteFarbe;
    private static ArrayList<String> spieleAnsagbar;

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

        mGoogleApiClient = Lobby.m_GoogleApiClient;
        endpointIDs = Lobby.endpointIds;

        appContext = this.getApplicationContext();
        //endpointIDs.remove(Nearby.Connections.getLocalEndpointId(mGoogleApiClient));

        imageView_karte1 =(ImageView) findViewById(R.id.imageView_karte1);
        //  findViewById(R.id.imageView_karte1).setOnTouchListener(new MyTouchListener());
        imageView_karte2 = (ImageView) findViewById(R.id.imageView_karte2);
        //findViewById(R.id.imageView_karte2).setOnTouchListener(new MyTouchListener());
        imageView_karte3 = (ImageView) findViewById(R.id.imageView_karte3);
        //findViewById(R.id.imageView_karte3).setOnTouchListener(new MyTouchListener());
        imageView_karte4 = (ImageView) findViewById(R.id.imageView_karte4);
        //findViewById(R.id.imageView_karte4).setOnTouchListener(new MyTouchListener());
        imageView_karte5 = (ImageView) findViewById(R.id.imageView_karte5);
        // findViewById(R.id.imageView_karte5).setOnTouchListener(new MyTouchListener());




        buttonZudrehen = (Button) findViewById(R.id.main_buttonZ);
        button20er = (Button) findViewById(R.id.main_button20er);
        button40er = (Button) findViewById(R.id.main_button40er);
        buttonTrumpfTauschen = (Button) findViewById(R.id.main_buttonTtauschen);
        punkteGegner = (TextView) findViewById(R.id.pointsText);
        punkteSelbst = (TextView) findViewById(R.id.pointsText2);
        txtSelbst = (TextView) findViewById(R.id.I);
        txtGegner = (TextView) findViewById(R.id.Enemy);


        imageView_deck = (ImageView) findViewById(R.id.imageView_deck);
        imageView_eigeneKarte = (ImageView) findViewById(R.id.imageView_eigeneKarte);
        // findViewById(R.id.imageView_eigeneKarte).setOnDragListener(new MyDragListener());
        imageView_karteGegner = (ImageView) findViewById(R.id.imageView_karteGegner);
        imageView_trumpf = (ImageView) findViewById(R.id.imageView_trumpf);
        imageView_trumpfIcon = (ImageView) findViewById(R.id.imageView_trumpfIcon);


        handkartenImages = new ArrayList<ImageView>();
        handkartenImages.add(0, imageView_karte1);
        handkartenImages.add(1, imageView_karte2);
        handkartenImages.add(2, imageView_karte3);
        handkartenImages.add(3, imageView_karte4);
        handkartenImages.add(4, imageView_karte5);


        spielStart();
    }

    private void zugAusfuehren(int i) {
        final Karte k = selbst.Hand.get(i);
        buttonsNichtKlickbar();
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (KARTEGESPIELT + ":" + k.toString()).getBytes());
        gespielteKarteEntfernen(i);

        imageView_eigeneKarte.setImageResource(k.getImageResourceId());

        //buttonEigeneKarte.setText(k.getFarbe() + k.getWertigkeit());
        if (gegnerischeKarte == null) {
            Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (WEITER + ":" + 0).getBytes());
        }
    }

    private static void eigenerZug() {
        if (!zugedreht) {
            buttonZudrehen.setEnabled(true);
            buttonZudrehen.setAlpha(1f);

            if (selbst.Hand.contains(new Karte(trumpfkarte.getFarbe(),"Bube",2))) {
                buttonTrumpfTauschen.setEnabled(true);
                buttonTrumpfTauschen.setAlpha(1f);
            }
            else {
                buttonTrumpfTauschen.setEnabled(false);
                buttonTrumpfTauschen.setAlpha(0.4f);
            }
            if(hat20er) {
                button20er.setEnabled(true);
                button20er.setAlpha(1f);
            }
            else {
                button20er.setEnabled(false);
                button20er.setAlpha(0.4f);
            }
            if(hat40er) {
                button40er.setEnabled(true);
                button40er.setAlpha(1f);
            }
            else {
                button40er.setEnabled(false);
                button40er.setAlpha(0.4f);
            }
        }
    }

    private static void handAktualisieren() {
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

    private static void punkteAktualisieren() {

        punkteGegner.setText(Integer.toString(p2));
        punkteSelbst.setText(Integer.toString(p1));
    }

    private void gespielteKarteEntfernen(int i) {
        //handkartenButtons.get(i).setVisibility(View.INVISIBLE);
        handkartenImages.get(i).setVisibility(View.INVISIBLE);
    }

    private void spielStart() {

        selbst = new Spieler();

        //Toast.makeText(appContext, "apiclientconnected: "+Boolean.toString(mGoogleApiClient.isConnected()), Toast.LENGTH_LONG).show();
        /*Handler handler3  =  new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, "HostEndpointID: " + endpointIDs.get(0), Toast.LENGTH_LONG).show();
            }
        }, 2000);*/
        //Toast.makeText(appContext, "HostEndpointID==ownEndpointID " + Boolean.toString(Nearby.Connections.getLocalEndpointId(mGoogleApiClient).equals(endpointIDs.get(0))), Toast.LENGTH_LONG).show();
        //Toast.makeText(appContext, "endpointIDs.size: " + endpointIDs.size(), Toast.LENGTH_LONG).show();

        zugedreht = true;
        angesagteFarbe = null;
        spieleAnsagbar = new ArrayList<String>();
        // buttonStapel.setText("20");
//        buttonEigeneKarte.setText("");
//        buttonGegnerischeKarte.setText("");
        punkteSelbst.setText("0");
        punkteGegner.setText("0");
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
        buttonZudrehen.setEnabled(false);
        buttonZudrehen.setAlpha(0.4f);
        buttonTrumpfTauschen.setEnabled(false);
        buttonTrumpfTauschen.setAlpha(0.4f);
    }

    private static void handKartenKlickbar() {
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
                imageViewK.setAlpha(0.4f); // Karte die nicht klickbar ist ist transparent (alpha =0 w�re durchsichtig)
                // buttonK.setEnabled(false);

            }
        }
    }

    private static void spielEnde(boolean win) {
        Bundle args = new Bundle();
        imageView_karteGegner.setImageDrawable(null); // Ansicht der Karten wird f�r n�chstes Spiel gel�scht
        imageView_eigeneKarte.setImageDrawable(null);

        args.putBoolean("win", win);
        DialogFragment gameEndDialogFragment = new GameEnd();
        gameEndDialogFragment.setArguments(args);
        //gameEndDialogFragment.show(getFragmentManager(), "GameEnd");
    }

    private void trumpfansagen(int i) {
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TRUMPFFARBE + ":" + selbst.Hand.get(i).getFarbe()).getBytes());
        buttonsNichtKlickbar();
        //aufdrehenButton.setVisibility(View.INVISIBLE);
        //buttonSpielAnsagen.setVisibility(View.VISIBLE);
    }

    public void popup20er(View view) {
        PopupMenu popup = new PopupMenu(Spielfeld4Client.this, button20er);
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

    public void popupSpielAnsagen(final View view) {
        //TODO: change buttonSpielAnsagen
        final Button buttonSpielAnsagen = new Button(appContext);
        PopupMenu popup = new PopupMenu(Spielfeld4Client.this, buttonSpielAnsagen);
        popup.inflate(R.menu.popup_menu_spiel_ansagen);
        MenuItem schnapser = (MenuItem) popup.getMenu().getItem(1);
        MenuItem land = (MenuItem) popup.getMenu().getItem(2);
        MenuItem kontraschnapser = (MenuItem) popup.getMenu().getItem(3);
        MenuItem bauernschnapser = (MenuItem) popup.getMenu().getItem(4);
        MenuItem kontrabauernschnapser = (MenuItem) popup.getMenu().getItem(5);
        MenuItem farbenjodler = (MenuItem) popup.getMenu().getItem(6);
        MenuItem herrenjodler = (MenuItem) popup.getMenu().getItem(7);
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
            if (spieleAnsagbar.get(5).equals("1")) farbenjodler.setVisible(true);
            if (spieleAnsagbar.get(6).equals("1")) herrenjodler.setVisible(true);
        } catch (Exception e) {

        }
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                try {
                    switch (menuItem.getTitle().toString()) {
                        case "Weiter": Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIELANSAGEN + ":" + 0).getBytes());
                            break;
                        case "Schnapser": Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIELANSAGEN + ":" + "Schnapser").getBytes());
                            break;
                        case "Land": Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIELANSAGEN + ":" + "Land").getBytes());
                            break;
                        case "Kontraschnapser": Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIELANSAGEN + ":" + "Kontraschnapser").getBytes());
                            break;
                        case "Bauernschnapser": Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIELANSAGEN + ":" + "Bauernschnapser").getBytes());
                            break;
                        case "Kontrabauernschnapser": Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIELANSAGEN + ":" + "Kontrabauernschnapser").getBytes());
                            break;
                        case "Farbenjodler": Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIELANSAGEN + ":" + "Farbenjodler").getBytes());
                            break;
                        case "Herrenjodler": Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIELANSAGEN + ":" + "Herrenjodler").getBytes());
                            break;
                        default: return false;
                    }
                } catch (Exception e) {

                }
                buttonSpielAnsagen.setVisibility(view.INVISIBLE);
                return true;
            }
        });
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

    public void karte1OnClick(View view) {
        if (angesagteFarbe==null) trumpfansagen(0);
        else zugAusfuehren(0);
    }

    public void karte2OnClick(View view) {
        if (angesagteFarbe==null) trumpfansagen(1);
        else zugAusfuehren(1);
    }

    public void karte3OnClick(View view) {
        if (angesagteFarbe==null) trumpfansagen(2);
        else zugAusfuehren(2);
    }

    public void karte4OnClick(View view) {
        if (angesagteFarbe==null) trumpfansagen(3);
        else zugAusfuehren(3);
    }

    public void karte5OnClick(View view) {
        if (angesagteFarbe==null) trumpfansagen(4);
        else zugAusfuehren(4);
    }

    public void aufdrehenOnClick(View view) {
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (AUFDREHEN + ":").getBytes());
        //aufdrehenButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        spielStart();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        startActivity(new Intent(Spielfeld4Client.this, Startmenue.class));
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

    public static void receiveFromLobby(String endpointID, byte[] payload, boolean isReliable) {
        String message = new String(payload);
        switch ((message.split(":")[0])) {
            case BUMMERL: bummerl = new Bummerl2(message.substring(2));
                //Toast.makeText(appContext, "bummerl: "+message.substring(2), Toast.LENGTH_SHORT).show();
                break;
            case HANDKARTEN: String[] messageParts = message.split(":");
                stapelKartenAnz = Integer.decode(messageParts[1]);
                String[] hand = messageParts[2].substring(1).split(",");
                String[] spielbar = messageParts[3].substring(1).split(" ");
                Toast.makeText(appContext, "spielbar: " + messageParts[3], Toast.LENGTH_SHORT).show();
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
                Toast.makeText(appContext, "Weiter", Toast.LENGTH_SHORT).show();
                break;
            case ANGESAGT40ER: Toast.makeText(appContext, "40er angesagt", Toast.LENGTH_SHORT).show();
                break;
            case ANGESAGT20ER: String farbe = message.substring(2);
                Toast.makeText(appContext, farbe+" 20er angesagt", Toast.LENGTH_SHORT).show();
                break;
            case ZUGENDE:
                // Execute some code after 2 seconds have passed
                Handler handler = new Handler();
                handler.postDelayed(new Zugende(), 2000);
                break;
            case PUNKTE:
                p1 = Integer.decode(message.split(":")[1].split(" ")[1]);
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
            case TRUMPFFARBE: angesagteFarbe = message.split(":")[1];
                imageView_trumpfIcon.setImageResource(Karte.getIconResourceId(angesagteFarbe));
                break;
            case SPIELANSAGEN: for(String ansagbar: message.split(":")[1].split(" ")) {
                spieleAnsagbar.add(ansagbar);
                }
                //buttonSpielAnsagen.setVisibility(View.VISIBLE);
                break;
            case TRUMPFANSAGEN: handKartenKlickbar();
                //aufdrehenButton.setVisibility(View.VISIBLE);
                break;
            default: break;
        }
    }

    public void abbrechenSpiel(View v){
        Intent i = new Intent(this, Startmenue.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onMessageReceived(String endpointID, byte[] payload, boolean isReliable) {
        receiveFromLobby(endpointID,payload,isReliable);
    }

    @Override
    public void onDisconnected(String s) {

    }

    static class Zugende implements Runnable {

        @Override
        public void run() {
            //TODO: zus�tzlich views hinzuf�gen
            imageView_eigeneKarte.setImageDrawable(null);
            imageView_karteGegner.setImageDrawable(null);
            punkteAktualisieren();
            gegnerischeKarte = null;
        }
    }
}
