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

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.Connections;

import java.util.ArrayList;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl4;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel4;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spieler;
import appsolutegamesgmbh.schnaps2gether.R;


public class Spielfeld4Host extends Activity implements PopupMenu.OnMenuItemClickListener, GameEnd.GameEndDialogListener,
        Connections.MessageListener {

    /*TODO: gewünschter Spielablauf:
            - Spiel anlegen (als Parameter AnzahlSpiele von Klasse Bummerl mitgebeben) - jeder Spieler bekommt automatisch die ersten 3 Karten
            - nun ist die Vorhand dran den Trumpf anzusagen - durch aufdrehen (fürs aufdrehen ist Methode implementiert) oder direkt
            - Methode Trumpfansagen aufrufen (es werden automatisch die restlichen Karten ausgeteilt)
            - über DarfSpielAnsagen und Spielansagen nach der Reihe jedem Spieler fragen, ob er was spielen will (beginnend bei der Vorhand)
            - ist SpielzuEnde auch schon vor dem ersten Ausspielen abprüfen, da bei Herrenjodler und Farbenjodler spiel sofort beendet !!!

     */
    // TODO: alle verwendeten layoutelemente aktualisieren

    //Konstanten für das Kennzeichnen und Parsen von Nachrichten
    private static final String KARTEGESPIELT = "0";
    private static final String WEITER = "1";
    private static final String PUNKTE = "2";
    private static final String TRUMPFANSAGEN = "3";
    private static final String ANGESAGT20ER = "4";
    private static final String ANGESAGT40ER = "5";
    //private static final String TRUMPFGETAUSCHT = "6";
    private static final String SPIELENDE = "7";
    private static final String BUMMERL = "8";
    private static final String HANDKARTEN = "9";
    private static final String TRUMPFFARBE = "10";
    private static final String ZUGENDE = "11";
    private static final String DISCONNECT = "12";

    private static GoogleApiClient mGoogleApiClient;
    private static ArrayList<String> endpointIDs;

    private static Context appContext;

    private static Spiel4 spiel;
   /* private Button buttonKarte1;
    private Button buttonKarte2;
    private Button buttonKarte3;
    private Button buttonKarte4;
    private Button buttonKarte5;
    private static ArrayList<Button> handkartenButtons;
    private static Button buttonEigeneKarte;
    private static Button buttonGegnerischeKarte;
    private static Button buttonStapel;
    private static Button buttonTrumpfkarte;*/

    private static ImageView imageView_karte1;
    private static ImageView imageView_karte2;
    private static ImageView imageView_karte3;
    private static ImageView imageView_karte4;
    private static ImageView imageView_karte5;

    private static ArrayList<ImageView> handkartenImages;

    private static ImageView imageView_eigeneKarte;
    private static ImageView imageView_karteGegner;
    private static ImageView imageView_trumpfIcon;




    private static Button button20er;
    private static Button button40er;
    private MenuItem herz20er;
    private MenuItem karo20er;
    private MenuItem pik20er;
    private MenuItem kreuz20er;
    private static Spieler selbst;
    private static Spieler gegner1;
    private static Spieler gegner2;
    private static Spieler mitspieler;
    private static ArrayList<Spieler> andereSpieler;
    private static Karte eigeneKarte;
    private static Karte gegnerischeKarte1;
    private static Karte gegnerischeKarte2;
    private static Karte verbuendeteKarte;
    private static TextView punkteGegner1;
    private static TextView punkteGegner2;
    private static TextView punkteSelbst;
    private static TextView punkteMitspieler;
    private TextView txtSelbst;
    private TextView txtMitspieler;
    private TextView txtGegner1;
    private TextView txtGegner2;
    private static Bummerl4 bummerl;
    private static Boolean angesagt;

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
        setContentView(R.layout.activity_spielfeld4);

        mGoogleApiClient = Lobby.m_GoogleApiClient;
        endpointIDs = Lobby.endpointIds;
        endpointIDs.remove(Nearby.Connections.getLocalEndpointId(mGoogleApiClient));

        appContext = this.getApplicationContext();

        angesagt = false;
        andereSpieler = new ArrayList<Spieler>();
        andereSpieler.add(gegner1);
        andereSpieler.add(mitspieler);
        andereSpieler.add(gegner2);

        bummerl = new Bummerl4();
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (BUMMERL+":"+bummerl.toString()).getBytes());

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

        punkteGegner1 = (TextView) findViewById(R.id.pointsText);
        punkteGegner2 = (TextView) findViewById(R.id.pointsText);
        punkteSelbst = (TextView) findViewById(R.id.pointsText2);
        punkteMitspieler = (TextView) findViewById(R.id.pointsText2);
        txtSelbst = (TextView) findViewById(R.id.I);
        txtMitspieler = (TextView) findViewById(R.id.I);
        txtGegner1 = (TextView) findViewById(R.id.Enemy);
        txtGegner2 = (TextView) findViewById(R.id.Enemy);

        imageView_eigeneKarte = (ImageView) findViewById(R.id.imageView_eigeneKarte);
        // findViewById(R.id.imageView_eigeneKarte).setOnDragListener(new MyDragListener());
        imageView_karteGegner = (ImageView) findViewById(R.id.imageView_karteGegner);
        imageView_trumpfIcon = (ImageView) findViewById(R.id.imageView_trumpfIcon);

        handkartenImages = new ArrayList<ImageView>();
        handkartenImages.add(0, imageView_karte1);
        handkartenImages.add(1, imageView_karte2);
        handkartenImages.add(2, imageView_karte3);
        handkartenImages.add(3, imageView_karte4);
        handkartenImages.add(4, imageView_karte5);


        spielStart();
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
            for (Spieler s: andereSpieler) andereHandAktualisieren(s);
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
        spiel.ZugAuswerten(eigeneKarte, gegnerischeKarte1, verbuendeteKarte, gegnerischeKarte2);
        eigeneKarte = gegnerischeKarte1 = verbuendeteKarte = gegnerischeKarte2 = null;
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (ZUGENDE + ":").getBytes());
        // Execute some code after 2 seconds have passed
        Handler handler = new Handler();
        handler.postDelayed(new Zugende(), 2000);
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
            for (Spieler s: andereSpieler) andereHandAktualisieren(s);
        }
    }

    private static void andereHandAktualisieren(Spieler andererSpieler) {
        String andereHand = "";
        String gegKartenSpielBar = "";
        int andereHandkartenAnz = andererSpieler.Hand.size();
        for (int i=0;i<andereHandkartenAnz;i++) {
            andereHand += ","+andererSpieler.Hand.get(i).toString();
            gegKartenSpielBar += " "+(spiel.DarfKarteAuswaehlen(andererSpieler.Hand.get(i), andererSpieler) ? 1 : 0);
        }
        String recipientID = "";
        if (andererSpieler.equals(andereSpieler.get(0))) {
            recipientID=endpointIDs.get(0);
        } else if (andererSpieler.equals(andereSpieler.get(1))) {
            recipientID=endpointIDs.get(1);
        } else if (andererSpieler.equals(andereSpieler.get(2))) {
            recipientID=endpointIDs.get(2);
        }
        //TODO Stapelkartenanzahl aus messagelistener entfernen
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, recipientID, (HANDKARTEN + ":" + andereHand + ":" + gegKartenSpielBar).getBytes());
    }

    private static void punkteAktualisieren() {
//        buttonEigeneKarte.setText("");
//        buttonGegnerischeKarte.setText("");
        imageView_eigeneKarte.setVisibility(View.INVISIBLE);
        imageView_karteGegner.setVisibility(View.INVISIBLE);
        /*imageView_verbuendeteKarte.setVisibility(View.INVISIBLE);
        imageView_karteGegner2.setVisibility(View.INVISIBLE);*/
        int p1 = selbst.getPunkte();
        int p2 = gegner1.getPunkte();
        punkteGegner1.setText(Integer.toString(p2));
        punkteSelbst.setText(Integer.toString(p1));
        /*punkteGegner2.setText(Integer.toString(p2));
        punkteMitspieler.setText(Integer.toString(p1));*/
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (PUNKTE + ":" + Integer.toString(p1) + " " + Integer.toString(p2)).getBytes());
    }

    private void gespielteKarteEntfernen(int i) {
        handkartenImages.get(i).setVisibility(View.INVISIBLE);
    }

    private static boolean hat20er(Spieler s) {
        if ((spiel.hat20er(s).size()>0 && !hat40er(s)) || spiel.hat20er(s).size()>1)
            return true;
        return false;
    }

    private static boolean hat40er(Spieler s) {
        if(spiel.hat20er(s).contains(spiel.getAngesagteFarbe()))
            return true;
        return false;
    }

    private static void mitspielerHat20er(Spieler mitspieler) {
        int hast20er = hat20er(mitspieler) ? 1 : 0;
        int hast40er = hat40er(mitspieler) ? 1 : 0;
        String hastDie20er = "";
        ArrayList<String> geg20er = spiel.hat20er(mitspieler);
        for(String farbe: geg20er) {
            hastDie20er += " " + farbe;
        }
        // TODO: nur an entsprechenden spieler senden
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (WEITER + ":" + 1 + " " + hast20er + " " + hast40er + hastDie20er).getBytes());
    }

    private void spielStart() {
        Handler handler0 = new Handler();
        handler0.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    spiel = new Spiel4(bummerl.getAnzahlSpiele());
                } catch (Exception e) {

                }
                selbst = spiel.getS1();
                gegner1 = spiel.getS2();
                mitspieler = spiel.getS3();
                gegner2 = spiel.getS4();

                /*imageView_trumpfIcon.setImageResource(Karte.getIconResourceId(spiel.getAngesagteFarbe()));

                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TRUMPFFARBE + ":" + spiel.getAngesagteFarbe()).getBytes());

                handKartenKlickbar();*/
                imageView_karteGegner.setVisibility(View.INVISIBLE);
                imageView_eigeneKarte.setVisibility(View.INVISIBLE);
                punkteSelbst.setText("0");
                punkteGegner1.setText("0");
                punkteMitspieler.setText("0");
                punkteGegner2.setText("0");
                gegnerischeKarte1 = eigeneKarte = gegnerischeKarte2 = verbuendeteKarte = null;
                handAktualisieren();
                if (selbst.isIstdran()) {
                    handKartenKlickbar();
                    //aufdrehenButton.setVisibility(View.VISIBLE);
                } else if (gegner1.isIstdran()) {
                    Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs.get(0), (TRUMPFANSAGEN + ":").getBytes());
                } else if (mitspieler.isIstdran()) {
                    Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs.get(1), (TRUMPFANSAGEN + ":").getBytes());
                } else if (gegner2.isIstdran()) {
                    Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs.get(2), (TRUMPFANSAGEN + ":").getBytes());
                }
                //eigenerZug();
            }
        }, 2000);
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

    private static void handKartenKlickbar() {
        int handkartenAnz = selbst.Hand.size();
        for (int i=0;i<5;i++) {
            ImageView imageViewK = handkartenImages.get(i);
            //ImageButton imagebuttonK = handkartenImageButtons.get(i);
            //Button

            if (i<handkartenAnz && spiel.DarfKarteAuswaehlen(selbst.Hand.get(i), selbst)) {

                imageViewK.setEnabled(true);
                imageViewK.setAlpha(1f);
            } else {

                imageViewK.setEnabled(false);
                imageViewK.setAlpha(0.2f);
            }
        }
    }

    private void spielEnde() {
        boolean win = true;
        if (selbst.getPunkte()<66) {
            win = false;
        }
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIELENDE + ":" + (win ? 0 : 1)).getBytes());
        Bundle args = new Bundle();
        args.putBoolean("win", win);
        DialogFragment gameEndDialogFragment = new GameEnd();
        gameEndDialogFragment.setArguments(args);
        gameEndDialogFragment.show(getFragmentManager(), "GameEnd");
    }

    public void popup20er(View view) {
        PopupMenu popup = new PopupMenu(Spielfeld4Host.this, button20er);
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
        spiel.Ansagen20er(spiel.getAngesagteFarbe(), selbst);
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (ANGESAGT40ER + ":").getBytes());
        if (spiel.istSpielzuEnde(bummerl)) spielEnde();
        punkteAktualisieren();
        button40er.setEnabled(false);
        button20er.setEnabled(false);
        handKartenKlickbar();
    }

    public void karte1OnClick(View view) {
        if (spiel.getAngesagteFarbe()==null) {
            spiel.Trumpfansagen(selbst.Hand.get(0).getFarbe(),bummerl.getAnzahlSpiele());
            for (Spieler s: andereSpieler) andereHandAktualisieren(s);
        }
        else zugAusführen(0);
    }

    public void karte2OnClick(View view) {
        if (spiel.getAngesagteFarbe()==null) spiel.Trumpfansagen(selbst.Hand.get(1).getFarbe(),bummerl.getAnzahlSpiele());
        else zugAusführen(1);
    }

    public void karte3OnClick(View view) {
        if (spiel.getAngesagteFarbe()==null) spiel.Trumpfansagen(selbst.Hand.get(2).getFarbe(),bummerl.getAnzahlSpiele());
        else zugAusführen(2);
    }

    public void karte4OnClick(View view) {
        if (spiel.getAngesagteFarbe()==null) spiel.Trumpfansagen(selbst.Hand.get(3).getFarbe(),bummerl.getAnzahlSpiele());
        else zugAusführen(3);
    }

    public void karte5OnClick(View view) {
        if (spiel.getAngesagteFarbe()==null) spiel.Trumpfansagen(selbst.Hand.get(4).getFarbe(),bummerl.getAnzahlSpiele());
        else zugAusführen(4);
    }

    public void aufdrehenOnClick(View view) {
        spiel.Trumpfansagen(spiel.Aufdrehen().getFarbe(),bummerl.getAnzahlSpiele());
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TRUMPFFARBE+":"+spiel.getAngesagteFarbe()).getBytes());
        //aufdrehenButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        spielStart();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        startActivity(new Intent(Spielfeld4Host.this, Startmenue.class));
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

    public static void receiveFromLobby(String endpointID, byte[] payload, boolean isReliable)
    {
        String message = new String(payload);
        switch (((message.split(":")[0]))) {
            case KARTEGESPIELT: gegnerischeKarte1 = new Karte(message.split(":")[1]);
                //Toast.makeText(appContext,"gegnerischeHand.contains("+gegnerischeKarte.toString()+") "+Boolean.toString(gegner.Hand.contains(gegnerischeKarte)),Toast.LENGTH_SHORT).show();
                spiel.Auspielen(gegnerischeKarte1, gegner1);
                //Toast.makeText(appContext,gegnerischeKarte.toString()+" entfernt"+Boolean.toString(gegner.Hand.contains(gegnerischeKarte)),Toast.LENGTH_SHORT).show();


                // buttonGegnerischeKarte.setText(gegnerischeKarte.getFarbe() + gegnerischeKarte.getWertigkeit());
                imageView_karteGegner.setImageResource(gegnerischeKarte1.getImageResourceId());

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
            case ANGESAGT40ER: //TODO trumpffarbe
                //spiel.Ansagen20er(spiel.getTrumpf(), gegner);
                punkteAktualisieren();
                if (spiel.istSpielzuEnde(bummerl)) {
                    //spielEnde();
                }
                //TODO: anderer Spieler durch Absender ersetzen
                Spieler andererSpieler = new Spieler();
                andereHandAktualisieren(andererSpieler);
                angesagt = true;
                Toast.makeText(appContext, "40er angesagt", Toast.LENGTH_SHORT).show();
                break;
            case ANGESAGT20ER: String farbe = message.substring(2);
                spiel.Ansagen20er(farbe, gegner1);
                punkteAktualisieren();
                if (spiel.istSpielzuEnde(bummerl)) {
                    //spielEnde();
                }
                //TODO: anderer Spieler durch Absender ersetzen
                /*Spieler andererSpieler = new Spieler();
                andereHandAktualisieren(andererSpieler);*/
                angesagt = true;
                Toast.makeText(appContext, farbe+" 20er angesagt", Toast.LENGTH_SHORT).show();
                break;
            default: break;
        }
    }

    @Override
    public void onMessageReceived(String s, byte[] bytes, boolean b) {
        receiveFromLobby(s, bytes, b);
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

    static class Zugende implements Runnable {

        @Override
        public void run() {
            punkteAktualisieren();
            gegnerischeKarte1 = eigeneKarte = null;
            if (spiel.istSpielzuEnde(bummerl)) {
                //spielEnde();
            } else {
                handAktualisieren();
                if (selbst.isIstdran()) {
                    eigenerZug();
                    handKartenKlickbar();
                } else {
                    mitspielerHat20er(gegner1);
                }
            }
        }
    }
}
