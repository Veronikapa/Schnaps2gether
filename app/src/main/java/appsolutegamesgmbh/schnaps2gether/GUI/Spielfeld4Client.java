package appsolutegamesgmbh.schnaps2gether.GUI;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.nearby.connection.Connections;

import java.util.ArrayList;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spieler;
import appsolutegamesgmbh.schnaps2gether.Services.NearbyConnectionService;
import appsolutegamesgmbh.schnaps2gether.R;

/**
 * Created by kirederf on 24.05.2015.
 */
public class Spielfeld4Client extends Activity implements PopupMenu.OnMenuItemClickListener, GameEnd.GameEndDialogListener,
        Connections.MessageListener, Spielfeld {

    //Konstanten fuer das Kennzeichnen und Parsen von Nachrichtentypen
    private static final String AUSSPIELEN = "0";
    private static final String KARTENSPIELBAR = "1";
    private static final String PUNKTE = "2";
    private static final String TRUMPFANSAGEN = "3";
    private static final String ANGESAGT20ER = "4";
    private static final String ANGESAGT40ER = "5";
    private static final String SPIELANSAGEN = "6";
    private static final String SPIELRUNDENENDE = "7";
    private static final String SPIELSTART = "8";
    private static final String HANDKARTEN = "9";
    private static final String TRUMPFFARBE = "10";
    private static final String ZUGENDE = "11";
    private static final String DISCONNECT = "12";
    private static final String AUFDREHEN = "13";
    private static final String FLECKEN = "14";
    private static final String SPIEL = "15";

    //Konstanten fuer Spielernummern
    private static final int SPIELER1 = 0;
    private static final int SPIELER2 = 1;
    private static final int SPIELER3 = 2;
    private static final int SPIELER4 = 3;

    //Konstanten fuer Farben
    private static final String HERZ = "0";
    private static final String KARO = "1";
    private static final String PIK = "2";
    private static final String KREUZ = "3";

    //Konstanten fuer "Spiele"
    private static final int SCHNAPSER = 0;
    private static final int LAND = 1;
    private static final int KONTRASCHNAPSER = 2;
    private static final int BAUERNSCHNAPSER = 3;
    private static final int KONTRABAUERNSCHNAPSER = 4;
    private static final int FARBENJODLER = 5;
    private static final int HERRENJODLER = 6;

    private ArrayList<String> endpointIDs;
    NearbyConnectionService mService;
    boolean mBound = false;

    private Context appContext;



    private ImageView imageView_karte1;
    private ImageView imageView_karte2;
    private ImageView imageView_karte3;
    private ImageView imageView_karte4;
    private ImageView imageView_karte5;

    private ArrayList<ImageView> handkartenImages;
    private ImageView imageView_eigeneKarte;
    private ImageView imageView_karteGegner1;
    private ImageView imageView_karteMitspieler;
    private ImageView imageView_karteGegner2;
    private ImageView imageView_trumpfIcon;

    private Button button20er;
    private Button button40er;
    private Button buttonSpielAnsagen;
    private Button buttonAufdrehen;
    private Button buttonFlecken;
    private Button buttonGegenflecken;
    private Button buttonWeiter;
    private MenuItem herz20er;
    private MenuItem karo20er;
    private MenuItem pik20er;
    private MenuItem kreuz20er;
    private Spieler selbst;
    private ArrayList<Boolean> kartenSpielbar;
    private Karte gegnerischeKarte;
    private TextView punkteGegner;
    private TextView punkteSelbst;
    private TextView txtSelbst;
    private TextView txtGegner;
    private Bummerl2 bummerl;
    private boolean zugedreht;
    private boolean hat20er;
    private boolean hat40er;
    private ArrayList<String> hab20er;
    private int p1;
    private int p2;
    private String angesagteFarbe;
    private ArrayList<String> spieleAnsagbar;
    private Boolean spielAngesagt;
    private int spielerNummer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spielfeld2);

        //Screen Lock deaktivieren
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        appContext = this.getApplicationContext();

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




        button20er = (Button) findViewById(R.id.main_button20er);
        button40er = (Button) findViewById(R.id.main_button40er);
        buttonSpielAnsagen = (Button) findViewById(R.id.main_buttonSpielAnsagen);
        buttonAufdrehen = (Button) findViewById(R.id.main_buttonAufdrehen);
        buttonFlecken = (Button) findViewById(R.id.main_buttonFlecken);
        buttonGegenflecken = (Button) findViewById(R.id.main_buttonGegenFlecken);
        buttonWeiter = (Button) findViewById(R.id.main_buttonWeiter);

       // punkteGegner = (TextView) findViewById(R.id.pointsText);
        punkteSelbst = (TextView) findViewById(R.id.txt_PunkteZahlI);
        //txtSelbst = (TextView) findViewById(R.id.I);
        //txtGegner = (TextView) findViewById(R.id.Enemy);


        imageView_eigeneKarte = (ImageView) findViewById(R.id.imageView_eigeneKarte);
        // findViewById(R.id.imageView_eigeneKarte).setOnDragListener(new MyDragListener());
        imageView_karteGegner1 = (ImageView) findViewById(R.id.imageView_karteGegner1);
        imageView_karteMitspieler = (ImageView) findViewById(R.id.imageView_karteMitspieler);
        imageView_karteGegner2 = (ImageView) findViewById(R.id.imageView_karteGegner2);
        imageView_trumpfIcon = (ImageView) findViewById(R.id.imageView_trumpfIcon);


        handkartenImages = new ArrayList<ImageView>();
        handkartenImages.add(0, imageView_karte1);
        handkartenImages.add(1, imageView_karte2);
        handkartenImages.add(2, imageView_karte3);
        handkartenImages.add(3, imageView_karte4);
        handkartenImages.add(4, imageView_karte5);


        spielStart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to NearbyConnectionService
        Intent intent = new Intent(this, NearbyConnectionService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        mService.setSpielfeld(this);
        endpointIDs = mService.getEndpointIds();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
        // Stop the service
        Intent intent = new Intent(this, NearbyConnectionService.class);
        stopService(intent);
    }

    private void zugAusfuehren(int i) {
        final Karte k = selbst.Hand.get(i);
        buttonsNichtKlickbar();
        mService.delegateSendReliableMessage(endpointIDs, (AUSSPIELEN + ":" + k.toString()).getBytes());
        gespielteKarteEntfernen(i);

        imageView_eigeneKarte.setImageResource(k.getImageResourceId());
    }

    private void eigenerZug() {
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
        //punkteSelbst.setText(Integer.toString(p1));
    }

    private void gespielteKarteEntfernen(int i) {
        handkartenImages.get(i).setVisibility(View.INVISIBLE);
    }

    private void spielStart() {

        selbst = new Spieler();

        zugedreht = true;
        angesagteFarbe = null;
        spielAngesagt = false;
        spieleAnsagbar = new ArrayList<String>();
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
    }

    private void handKartenKlickbar() {
        int handkartenAnz = selbst.Hand.size();
        for (int i=0;i<5;i++) {

            ImageView imageViewK = handkartenImages.get(i);
            if (i<handkartenAnz && kartenSpielbar.get(i)) {
                imageViewK.setEnabled(true);
                imageViewK.setAlpha(1f); // Karte die klickbar ist, ist auch sichtbar
            } else {
                imageViewK.setEnabled(false);
                imageViewK.setAlpha(0.4f); // Karte die nicht klickbar ist ist transparent (alpha =0 waere durchsichtig)

            }
        }
    }

    private void spielEnde(boolean win) {
        Bundle args = new Bundle();
        imageView_karteGegner1.setImageDrawable(null); // Ansicht der Karten wird fuer naechstes Spiel geloescht
        imageView_eigeneKarte.setImageDrawable(null);
        imageView_karteMitspieler.setImageDrawable(null);
        imageView_karteGegner2.setImageDrawable(null);

        args.putBoolean("win", win);
        DialogFragment gameEndDialogFragment = new GameEnd();
        gameEndDialogFragment.setArguments(args);
        //gameEndDialogFragment.show(getFragmentManager(), "GameEnd");
    }

    private void trumpfansagen(String farbe) {
        mService.delegateSendReliableMessage(endpointIDs, (TRUMPFFARBE + ":" + farbe).getBytes());
        buttonsNichtKlickbar();
        buttonAufdrehen.setVisibility(View.INVISIBLE);
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
                case HERZ:
                    herz20er.setVisible(true);
                    break;
                case KARO:
                    karo20er.setVisible(true);
                    break;
                case PIK:
                    pik20er.setVisible(true);
                    break;
                case KREUZ:
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
        PopupMenu popup = new PopupMenu(Spielfeld4Client.this, buttonSpielAnsagen);
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
            if (spieleAnsagbar.get(5).equals("1")) farbenjodler.setVisible(true);
            if (spieleAnsagbar.get(6).equals("1")) herrenjodler.setVisible(true);
        } catch (Exception e) {

        }
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                try {
                    switch (menuItem.getTitle().toString()) {
                        case "Schnapser": mService.delegateSendReliableMessage(endpointIDs, (SPIELANSAGEN + ":" + "Schnapser").getBytes());
                            break;
                        case "Land": mService.delegateSendReliableMessage(endpointIDs, (SPIELANSAGEN + ":" + "Land").getBytes());
                            break;
                        case "Kontraschnapser": mService.delegateSendReliableMessage(endpointIDs, (SPIELANSAGEN + ":" + "Kontraschnapser").getBytes());
                            break;
                        case "Bauernschnapser": mService.delegateSendReliableMessage(endpointIDs, (SPIELANSAGEN + ":" + "Bauernschnapser").getBytes());
                            break;
                        case "Kontrabauernschnapser": mService.delegateSendReliableMessage(endpointIDs, (SPIELANSAGEN + ":" + "Kontrabauernschnapser").getBytes());
                            break;
                        case "Farbenjodler": mService.delegateSendReliableMessage(endpointIDs, (SPIELANSAGEN + ":" + "Farbenjodler").getBytes());
                            break;
                        case "Herrenjodler": mService.delegateSendReliableMessage(endpointIDs, (SPIELANSAGEN + ":" + "Herrenjodler").getBytes());
                            break;
                        default: return false;
                    }
                } catch (Exception e) {

                }
                spielAngesagt = true;
                buttonSpielAnsagen.setVisibility(view.INVISIBLE);
                buttonWeiter.setVisibility(View.INVISIBLE);
                return true;
            }
        });
        popup.show();
    }

    public void ansagen40er(View view) {
        mService.delegateSendReliableMessage(endpointIDs, (ANGESAGT40ER + ":").getBytes());
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

    public void fleckenOnClick(View view) {
        mService.delegateSendReliableMessage(endpointIDs, (FLECKEN + ":").getBytes());
        buttonFlecken.setVisibility(View.INVISIBLE);
        buttonWeiter.setVisibility(View.INVISIBLE);
    }
    
    public void weiterOnClick(View view) {
        if (!spielAngesagt)  {
            mService.delegateSendReliableMessage(endpointIDs, (SPIELANSAGEN + ":" + 0).getBytes());
            spielAngesagt = true;
            buttonSpielAnsagen.setVisibility(View.INVISIBLE);
            buttonWeiter.setVisibility(View.INVISIBLE);
        } else {
            mService.delegateSendReliableMessage(endpointIDs, (FLECKEN + ":" + 0).getBytes());
            buttonFlecken.setVisibility(View.INVISIBLE);
            buttonWeiter.setVisibility(View.INVISIBLE);
        }
    }

    public void karte1OnClick(View view) {
        if (angesagteFarbe==null) trumpfansagen(selbst.Hand.get(0).getFarbe());
        else zugAusfuehren(0);
    }

    public void karte2OnClick(View view) {
        if (angesagteFarbe==null) trumpfansagen(selbst.Hand.get(1).getFarbe());
        else zugAusfuehren(1);
    }

    public void karte3OnClick(View view) {
        if (angesagteFarbe==null) trumpfansagen(selbst.Hand.get(2).getFarbe());
        else zugAusfuehren(2);
    }

    public void karte4OnClick(View view) {
        if (angesagteFarbe==null) trumpfansagen(selbst.Hand.get(3).getFarbe());
        else zugAusfuehren(3);
    }

    public void karte5OnClick(View view) {
        if (angesagteFarbe==null) trumpfansagen(selbst.Hand.get(4).getFarbe());
        else zugAusfuehren(4);
    }

    public void aufdrehenOnClick(View view) {
        mService.delegateSendReliableMessage(endpointIDs, (AUFDREHEN + ":").getBytes());
        buttonAufdrehen.setVisibility(View.INVISIBLE);
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
                mService.delegateSendReliableMessage(endpointIDs, (ANGESAGT20ER + ":" + "Herz").getBytes());
                break;
            case R.id.karo_20er:
                mService.delegateSendReliableMessage(endpointIDs, (ANGESAGT20ER + ":" + "Karo").getBytes());
                break;
            case R.id.pik_20er:
                mService.delegateSendReliableMessage(endpointIDs, (ANGESAGT20ER + ":" + "Pik").getBytes());
                break;
            case R.id.kreuz_20er:
                mService.delegateSendReliableMessage(endpointIDs, (ANGESAGT20ER+":"+"Kreuz").getBytes());
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

    public void receiveFromLobby(String endpointID, byte[] payload, boolean isReliable) {
        String message = new String(payload);
        switch ((message.split(":")[0])) {
            case SPIELSTART: bummerl = new Bummerl2(message.substring(2).split(",")[1]);
                //Toast.makeText(appContext, message.substring(2,3), Toast.LENGTH_SHORT).show();
                spielerNummer = Integer.decode(message.substring(2,3));
                //Toast.makeText(appContext, "bummerl: "+message.substring(2), Toast.LENGTH_SHORT).show();
                break;
            case HANDKARTEN: String[] messageParts = message.split(":");
                String[] hand = messageParts[1].split(",");
                selbst.Hand = new ArrayList<Karte>();
                for (int i=0; i<hand.length; i++) {
                    //Toast.makeText(appContext, "karte "+i+": "+hand[i], Toast.LENGTH_SHORT).show();
                    selbst.Hand.add(i,new Karte(hand[i]));
                }
                handAktualisieren();

                break;
            case KARTENSPIELBAR: String[] messageParts1 = message.split(":");
                String[] spielbar = messageParts1[1].split(" ");
                //Toast.makeText(appContext, "spielbar: " + messageParts[2], Toast.LENGTH_SHORT).show();
                kartenSpielbar = new ArrayList<Boolean>();
                for (int i=0; i<spielbar.length; i++) {
                    //Toast.makeText(appContext, "karte "+i+": "+hand[i], Toast.LENGTH_SHORT).show();
                    kartenSpielbar.add(i,spielbar[i].equals("1") ? true : false);
                }
                handKartenKlickbar();
                break;
            case AUSSPIELEN:
                String[] messageParts2 = message.split(":");
                int spielerAmAusspielenNr = Integer.decode(message.substring(2).split(":")[0]);
                int ausspielenderSpielerNr = (spielerAmAusspielenNr +3) % 4;
                if (!messageParts2[2].equals("1")) {
                    gegnerischeKarte = new Karte(message.substring(2).split(":")[1]);
                    int position = 1;
                    if (ausspielenderSpielerNr>spielerNummer) {
                        position = position - spielerNummer;
                    } else {
                        position = position + spielerNummer;
                    }
                    switch (position) {
                        case 1: imageView_karteGegner1.setImageResource(gegnerischeKarte.getImageResourceId());
                            break;
                        case 2: imageView_karteMitspieler.setImageResource(gegnerischeKarte.getImageResourceId());
                            break;
                        case 3: imageView_karteGegner2.setImageResource(gegnerischeKarte.getImageResourceId());
                            break;

                    }
                }
                if (spielerAmAusspielenNr==spielerNummer) {
                    String[] spielbar1 = messageParts2[2].split(" ");
                    //Toast.makeText(appContext, "spielbar: " + messageParts[2], Toast.LENGTH_SHORT).show();
                    kartenSpielbar = new ArrayList<Boolean>();
                    for (int i = 0; i < spielbar1.length; i++) {
                        //Toast.makeText(appContext, "karte "+i+": "+hand[i], Toast.LENGTH_SHORT).show();
                        kartenSpielbar.add(i, spielbar1[i].equals("1") ? true : false);
                    }
                    handKartenKlickbar();
                    if (messageParts2[3].equals("1")) {
                        eigenerZug();
                        if (messageParts2[4].equals("1")) {
                            hat40er = true;
                        } else {
                            hat40er = false;
                        }
                        if (messageParts2[5].equals("1")) {
                            hat20er = true;
                            String[] meine20er = message.substring(8).split(" ");
                            for (String farbe: meine20er) {
                                hab20er.add(farbe);
                            }
                        } else {
                            hat20er = false;
                        }
                    }
                }
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
                p1 = Integer.decode(message.split(":")[1]);
                break;
            case SPIELRUNDENENDE: boolean win = message.substring(2,3).equals("1") ? true : false;
                if (spielerNummer==3) win = !win;
                String rundenAusgang = "";
                if (win) {
                    rundenAusgang = "Sieg";
                } else {
                    rundenAusgang = "Niederlage";
                }
                Toast.makeText(appContext, rundenAusgang, Toast.LENGTH_SHORT).show();
                bummerl = new Bummerl2(message.split(":")[2]);
                break;
            case DISCONNECT: Toast.makeText(appContext, "Verbindungsverlust eines Spielers - Das Spiel wird beendet...", Toast.LENGTH_SHORT).show();
                // Execute some code after 2 seconds have passed
                Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
                break;
            case TRUMPFFARBE: angesagteFarbe = message.split(":")[1];
                imageView_trumpfIcon.setImageResource(Karte.getIconResourceId(angesagteFarbe));
                break;
            case SPIELANSAGEN: for(String ansagbar: message.split(":")[1].split(" ")) {
                    spieleAnsagbar.add(ansagbar);
                }
                buttonSpielAnsagen.setVisibility(View.VISIBLE);
                buttonWeiter.setVisibility(View.VISIBLE);
                break;
            case TRUMPFANSAGEN: buttonAufdrehen.setVisibility(View.VISIBLE);
                break;
            case FLECKEN: if(message.split(":")[2].equals(Integer.toString(spielerNummer))) {
                    if (message.split(":")[1].equals("0")) {
                        buttonFlecken.setVisibility(View.VISIBLE);
                    } else buttonGegenflecken.setVisibility(View.VISIBLE);
                    buttonWeiter.setVisibility(View.VISIBLE);
                }
                break;
            case SPIEL: String spiel = message.split(":")[2];
                Toast.makeText(appContext, spiel+" wird gespielt", Toast.LENGTH_SHORT).show();
                if (message.split(":")[1].equals(Integer.toString(spielerNummer))) {
                    buttonFlecken.setVisibility(View.VISIBLE);
                    buttonWeiter.setVisibility(View.VISIBLE);
                }
                break;
            default: break;
        }
    }

    public void abbrechenSpiel(View v){
        //Screen Lock aktivieren
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

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

    class Zugende implements Runnable {

        @Override
        public void run() {
            imageView_eigeneKarte.setImageDrawable(null);
            imageView_karteGegner1.setImageDrawable(null);
            imageView_karteMitspieler.setImageDrawable(null);
            imageView_karteGegner2.setImageDrawable(null);
            punkteAktualisieren();
            gegnerischeKarte = null;
        }
    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to NearbyConnectionService, cast the IBinder and get NearbyConnectionService instance
            NearbyConnectionService.NearbyConnectionBinder binder = (NearbyConnectionService.NearbyConnectionBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}
