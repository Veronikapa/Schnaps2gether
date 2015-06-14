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

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.Connections;

import java.util.ArrayList;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl3;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spieler;
import appsolutegamesgmbh.schnaps2gether.R;
import appsolutegamesgmbh.schnaps2gether.Services.NearbyConnectionService;

/**
 * Created by Kerstin on 23.05.2015.
 */
public class Spielfeld3Client extends Activity implements GameEnd.GameEndDialogListener, PopupMenu.OnMenuItemClickListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        Connections.ConnectionRequestListener,
        Connections.MessageListener,
        Connections.EndpointDiscoveryListener,
        Spielfeld{

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

    private static ArrayList<String> endpointIDs;
    NearbyConnectionService mService;
    boolean mBound = false;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spielfeld3);

        //Screen Lock deaktivieren
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        appContext = this.getApplicationContext();

        imageView_karte1 =(ImageView) findViewById(R.id.imageView_karte1);
        imageView_karte2 = (ImageView) findViewById(R.id.imageView_karte2);
        imageView_karte3 = (ImageView) findViewById(R.id.imageView_karte3);
        imageView_karte4 = (ImageView) findViewById(R.id.imageView_karte4);
        imageView_karte5 = (ImageView) findViewById(R.id.imageView_karte5);
        imageView_karte5 = (ImageView) findViewById(R.id.imageView_karte6);

        button20er = (Button) findViewById(R.id.main_button20er);
        button40er = (Button) findViewById(R.id.main_button40er);
        buttonSpielAnsagen = (Button) findViewById(R.id.main_buttonSpielAnsagen);
        buttonTrumpfansagen = (Button) findViewById(R.id.cmd_trumpfansagen);
        buttonFlecken = (Button) findViewById(R.id.main_buttonFlecken);
        buttonGegenflecken = (Button) findViewById(R.id.main_buttonGegenFlecken);
        buttonWeiter = (Button) findViewById(R.id.main_buttonWeiter);
        buttonTalonTauschen = (Button) findViewById(R.id.main_buttonTtauschen);


        imageView_eigeneKarte = (ImageView) findViewById(R.id.imageView_eigeneKarte);
        imageView_karteGegner1 = (ImageView) findViewById(R.id.imageView_karteGegner1);
        imageView_karteGegner2 = (ImageView) findViewById(R.id.imageView_karteGegner2);
        imageView_trumpf = (ImageView) findViewById(R.id.imageView_trumpf);
        imageView_trumpfIcon = (ImageView) findViewById(R.id.imageView_trumpfIcon);

        //imageView_Stich1 = (ImageView) findViewById(R.id.imageView_Stich1);
        //imageView_Stich2 = (ImageView) findViewById(R.id.imageView_Stich2);
        //imageView_Stich3 = (ImageView) findViewById(R.id.imageView_Stich3);

        handkartenImages = new ArrayList<ImageView>();
        handkartenImages.add(0, imageView_karte1);
        handkartenImages.add(1, imageView_karte2);
        handkartenImages.add(2, imageView_karte3);
        handkartenImages.add(3, imageView_karte4);
        handkartenImages.add(4, imageView_karte5);
        handkartenImages.add(4, imageView_karte6);

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

    private void spielStart() {

        selbst = new Spieler();


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


        if(mService.isc1)
            SpielerID = "1";
        else if(mService.isc2)
            SpielerID = "2";


    }


    public void popupTrumpfansagen(View view) {
        PopupMenu popup = new PopupMenu(Spielfeld3Client.this, buttonTrumpfansagen);
        popup.inflate(R.menu.popup_menu_trumpfansagen);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return false;
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

        startActivity(new Intent(Spielfeld3Client.this, Startmenue.class));
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
                mService.delegateSendReliableMessage(endpointIDs, (ANGESAGT20ER+":"+"Herz").getBytes());
                break;
            case R.id.karo_20er:
                mService.delegateSendReliableMessage(endpointIDs, (ANGESAGT20ER+":"+"Karo").getBytes());
                break;
            case R.id.pik_20er:
                mService.delegateSendReliableMessage(endpointIDs, (ANGESAGT20ER+":"+"Pik").getBytes());
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
                buttonTrumpfansagen.setVisibility(View.VISIBLE);
                break;
            case TRUMPFKARTE: trumpfkarte = new Karte(message.split(":")[1]);
                imageView_trumpf.setImageResource(trumpfkarte.getImageResourceId());
                imageView_trumpfIcon.setImageResource(trumpfkarte.getIconResourceId()); // Ok wenn Icon hier auch geändert wird?
                break;
            case HANDKARTEN:
                if(messageParts[3] == SpielerID) {
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
                if(SpielerID == "1") {
                    if(messageParts[2] == "2") {
                        gegnerischeKarte1 = new Karte(message.substring(2));
                        imageView_karteGegner1.setImageResource(gegnerischeKarte1.getImageResourceId());
                    }
                    else if(messageParts[2] == "0") {
                        gegnerischeKarte2 = new Karte(message.substring(2));
                        imageView_karteGegner2.setImageResource(gegnerischeKarte2.getImageResourceId());
                    }
                }
                else if(SpielerID == "2") {
                    if(messageParts[2] == "0") {
                        gegnerischeKarte1 = new Karte(message.substring(2));
                        imageView_karteGegner1.setImageResource(gegnerischeKarte1.getImageResourceId());
                    }
                    else if(messageParts[2] == "1") {
                        gegnerischeKarte2 = new Karte(message.substring(2));
                        imageView_karteGegner2.setImageResource(gegnerischeKarte2.getImageResourceId());
                    }
                }
                break;
            case TRUMPFFARBE: angesagteFarbe = message.split(":")[1];
                imageView_trumpfIcon.setImageResource(Karte.getIconResourceId(angesagteFarbe));
                break;
            case WEITER:
                handKartenKlickbar();
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

            case ANGESAGT40ER: Toast.makeText(appContext, "40er angesagt", Toast.LENGTH_SHORT).show();
                break;
            case ANGESAGT20ER: String farbe = message.substring(2);
                Toast.makeText(appContext, farbe+" 20er angesagt", Toast.LENGTH_SHORT).show();
                break;
            case TALONGETAUSCHT: Toast.makeText(appContext, "Talonkarte ausgetauscht", Toast.LENGTH_SHORT).show();
                break;
            case ZUGENDE:
                istdran = Integer.decode(message.split(":")[1].split(" ")[2]);

                break;
            case PUNKTE:
                p2 = Integer.decode(message.split(":")[1].split(" ")[2]);
                p1 = Integer.decode(message.split(":")[1].split(" ")[1]);
                p0 = Integer.decode(message.split(":")[1].split(" ")[0]);
                punkteAktualisieren();
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

    public void karte6OnClick(View view) {
        zugAusführen(5);
    }

    public void weiterOnClick(View view) {
       /* if (anzSpieleAngesagt<4) {
            anzSpieleAngesagt++;
            if (anzSpieleAngesagt<4) {
                andererSpielerKannSpielAnsagen(gegner1);
                buttonWeiter.setVisibility(view.INVISIBLE);
            }
            else {
                fleckRunde();
            }
            buttonSpielAnsagen.setVisibility(view.INVISIBLE);
        } else {
            anzFleckZüge++;
            if (anzFleckZüge % 2 != 0)
                andererSpielerAmFlecken(mitspieler, false);
            else {
                for (Spieler andererSpieler: andereSpieler) {
                    if (andererSpieler.isIstdran()) andererSpielerAmZug(andererSpieler);
                }
                if (selbst.isIstdran()) {
                    handKartenKlickbar();
                    eigenerZug();
                }
                buttonWeiter.setVisibility(View.INVISIBLE);
                buttonFlecken.setVisibility(View.INVISIBLE);
            }
        }*/

        if (buttonWeiter.getText() == "Aufdecken"){
            mService.delegateSendReliableMessage(endpointIDs, (AUFGEDECKT).getBytes());
        }
    }

    private void zugAusführen(int i) {
        eigeneKarte = selbst.Hand.get(i);
        buttonsNichtKlickbar();
        mService.delegateSendReliableMessage(endpointIDs, (KARTEGESPIELT + ":" + eigeneKarte.toString() + ":" + SpielerID).getBytes());
        gespielteKarteEntfernen(i);

        imageView_eigeneKarte.setImageResource(eigeneKarte.getImageResourceId());

        //buttonEigeneKarte.setText(k.getFarbe() + k.getWertigkeit());
        /*if (gegnerischeKarte == null) {
            mService.delegateSendReliableMessage(endpointIDs, (WEITER+":"+0).getBytes());
        }*/
    }

    private void gespielteKarteEntfernen(int i) {
        handkartenImages.get(i).setVisibility(View.INVISIBLE);
    }

    private void punkteAktualisieren() {
        if (SpielerID == "1")
            punkteSelbst.setText(Integer.toString(p1));
        else if (SpielerID == "2")
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
