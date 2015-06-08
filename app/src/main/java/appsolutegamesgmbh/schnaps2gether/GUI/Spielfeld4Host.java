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

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.Connections;

import java.util.ArrayList;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl4;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel4;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spieler;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spielfeld4Logik;
import appsolutegamesgmbh.schnaps2gether.R;


public class Spielfeld4Host extends Activity implements PopupMenu.OnMenuItemClickListener, GameEnd.GameEndDialogListener,
        Connections.MessageListener {

    /*gewünschter Spielablauf:
            - Spiel anlegen (als Parameter AnzahlSpiele von Klasse Bummerl mitgebeben) - jeder Spieler bekommt automatisch die ersten 3 Karten
            - nun ist die Vorhand dran den Trumpf anzusagen - durch aufdrehen (fürs aufdrehen ist Methode implementiert) oder direkt
            - Methode Trumpfansagen aufrufen (es werden automatisch die restlichen Karten ausgeteilt)
            - über DarfSpielAnsagen und Spielansagen nach der Reihe jedem Spieler fragen, ob er was spielen will (beginnend bei der Vorhand)
            - ist SpielzuEnde auch schon vor dem ersten Ausspielen abprüfen, da bei Herrenjodler und Farbenjodler spiel sofort beendet !!!

     */

    //Konstanten für das Kennzeichnen und Parsen von Nachrichtentypen
    private static final String KARTEGESPIELT = "0";
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
    private static final String SPIELENDE = "11";
    private static final String DISCONNECT = "12";
    private static final String AUFDREHEN = "13";
    private static final String FLECKEN = "14";
    private static final String SPIEL = "15";

    //Konstanten für Spielernummern
    private static final int SPIELER1 = 0;
    private static final int SPIELER2 = 1;
    private static final int SPIELER3 = 2;
    private static final int SPIELER4 = 3;

    //Konstanten für Farben
    private static final int HERZ = 0;
    private static final int KARO = 1;
    private static final int PIK = 2;
    private static final int KREUZ = 3;

    //Konstanten für "Spiele"
    private static final int SCHNAPSER = 0;
    private static final int LAND = 1;
    private static final int KONTRASCHNAPSER = 2;
    private static final int BAUERNSCHNAPSER = 3;
    private static final int KONTRABAUERNSCHNAPSER = 4;
    private static final int FARBENJODLER = 5;
    private static final int HERRENJODLER = 6;

    private static GoogleApiClient mGoogleApiClient;
    private static ArrayList<String> endpointIDs;

    private static Context appContext;

    private static Spiel4 spiel;

    private static ImageView imageView_karte1;
    private static ImageView imageView_karte2;
    private static ImageView imageView_karte3;
    private static ImageView imageView_karte4;
    private static ImageView imageView_karte5;

    private static ArrayList<ImageView> handkartenImages;

    private static ImageView imageView_eigeneKarte;
    private static ImageView imageView_karteGegner1;
    private static ImageView imageView_karteMitspieler;
    private static ImageView imageView_karteGegner2;
    private static ImageView imageView_trumpfIcon;




    private static Button button20er;
    private static Button button40er;
    private static Button buttonTrumpfAnsagen;
    private static Button buttonSpielAnsagen;
    private static Button buttonAufdrehen;
    private static Button buttonFlecken;
    private static Button buttonGegenflecken;
    private static Button buttonWeiter;
    /*private static Spieler selbst;
    private static Spieler gegner1;
    private static Spieler gegner2;
    private static Spieler mitspieler;
    private static ArrayList<Spieler> andereSpieler;
    private static Spieler rufer; //Rufer des gespielten Rufspiels
    private static Karte eigeneKarte;
    private static Karte gegnerischeKarte1;
    private static Karte gegnerischeKarte2;
    private static Karte mitspielerKarte;*/
    private static TextView BpunkteGegner1;
    private static TextView BpunkteGegner2;

    private static TextView punkteSelbst;
    private static TextView punkteMitspieler;
    private static TextView txtSelbst;
    private static TextView txtMitspieler;
    private static TextView txtGegner1;
    private static TextView txtGegner2;
    /*private static Bummerl4 bummerl;
    private static Boolean angesagt;*/
    private static String  gegner1ID;
    private static String  mitspielerID;
    private static String  gegner2ID;
    /*private static int anzSpieleAngesagt;
    private static int anzFleckZüge;*/
    private static Spielfeld4Logik spielfeldlogik;

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

        //Screen Lock deaktivieren
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mGoogleApiClient = Lobby.m_GoogleApiClient;
        endpointIDs = Lobby.endpointIds;
        endpointIDs.remove(Nearby.Connections.getLocalEndpointId(mGoogleApiClient));
        gegner1ID = endpointIDs.get(0);
        mitspielerID = endpointIDs.get(1);
        gegner2ID = endpointIDs.get(2);

        appContext = this.getApplicationContext();

        spielfeldlogik = new Spielfeld4Logik(this);

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
        buttonTrumpfAnsagen = (Button) findViewById(R.id.main_buttonTrumpfAnsagen);
        buttonSpielAnsagen = (Button) findViewById(R.id.main_buttonSpielAnsagen);
        buttonAufdrehen = (Button) findViewById(R.id.main_buttonAufdrehen);
        buttonFlecken = (Button) findViewById(R.id.main_buttonFlecken);
        buttonGegenflecken = (Button) findViewById(R.id.main_buttonGegenFlecken);
        buttonWeiter = (Button) findViewById(R.id.main_buttonWeiter);

        //punkteGegner1 = (TextView) findViewById(R.id.pointsText);
        //punkteGegner2 = (TextView) findViewById(R.id.pointsText);
        punkteSelbst = (TextView) findViewById(R.id.txt_PunkteZahlI);
        //punkteMitspieler = (TextView) findViewById(R.id.pointsText2);
  /*      txtSelbst = (TextView) findViewById(R.id.I);
        txtMitspieler = (TextView) findViewById(R.id.I);
        txtGegner1 = (TextView) findViewById(R.id.Enemy);
        txtGegner2 = (TextView) findViewById(R.id.Enemy);*/

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


        spielRundenStart();
    }

    public void sendStartMessage() {
        Bummerl4 bummerl = spielfeldlogik.getBummerl();
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, gegner1ID, (SPIELSTART + ":" + SPIELER2 + "," + bummerl.toString()).getBytes());
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, mitspielerID, (SPIELSTART + ":" + SPIELER3 + "," + bummerl.toString()).getBytes());
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, gegner2ID, (SPIELSTART + ":" + SPIELER4 + "," + bummerl.toString()).getBytes());
    }

    private void zugAusfuehren(int i) {
        Karte k = spielfeldlogik.karteAusspielen(i);
        weiterNachricht(k, endpointIDs);
        buttonsNichtKlickbar();
        handkartenImages.get(i).setVisibility(View.INVISIBLE);

        imageView_eigeneKarte.setImageResource(k.getImageResourceId());

        if (spielfeldlogik.isZugBeginn()) {
            // Execute some code after 2 seconds have passed
            Handler handler = new Handler();
            handler.postDelayed(new Zugende(), 2000);
        }
    }

    private static String spielbareKartenNachrichtZusammenstellen() {
        String spielbareKarten = "";
        for (boolean spielbar: spielfeldlogik.getHandkartenSpielbar()) {
            spielbareKarten += " " + (spielbar ? "1" : "0");
        }
        spielbareKarten.trim();
        return spielbareKarten;
    }

    private static void weiterNachricht(Karte gespielteKarte, ArrayList<String> recipients) {
        int neuerZug = spielfeldlogik.isZugBeginn() ? 1 : 0;
        int spielerAmAusspielen = spielfeldlogik.getAusspielenderSpielerNr();
        String spielbareKarten = spielbareKartenNachrichtZusammenstellen();
        int hatZwanziger = spielfeldlogik.hasHatZwanziger() ? 1 : 0;
        int hatVierziger = spielfeldlogik.hasHatVierziger() ? 1 : 0;
        String verfuegbareZwanziger = "";
        if (spielfeldlogik.getVerfuegbareZwanziger().contains("Herz")) {
            verfuegbareZwanziger += " " + HERZ;
        }
        if (spielfeldlogik.getVerfuegbareZwanziger().contains("Karo")) {
            verfuegbareZwanziger += " " + KARO;
        }
        if (spielfeldlogik.getVerfuegbareZwanziger().contains("Pik")) {
            verfuegbareZwanziger += " " + PIK;
        }
        if (spielfeldlogik.getVerfuegbareZwanziger().contains("Kreuz")) {
            verfuegbareZwanziger += " " + KREUZ;
        }
        verfuegbareZwanziger.trim();
        if (gespielteKarte==null) {
            Nearby.Connections.sendReliableMessage(mGoogleApiClient, recipients, (KARTEGESPIELT + ":" + (spielerAmAusspielen)
                    + ":" + spielbareKarten + ":" + neuerZug + ":" +
                    hatVierziger + ":" + hatZwanziger + ":" + verfuegbareZwanziger).getBytes());
        }
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, recipients, (KARTEGESPIELT + ":" + ((spielerAmAusspielen + 3) % 4)
                + ":" + gespielteKarte.toString() + ":" + spielbareKarten + ":" + neuerZug + ":" +
                hatVierziger + ":" + hatZwanziger + ":" + verfuegbareZwanziger).getBytes());
    }

    private static void eigenerZug() {
        if(spielfeldlogik.hasHatZwanziger()) {
            button20er.setEnabled(true);
        }
        else {
            button20er.setEnabled(false);
        }
        if(spielfeldlogik.hasHatVierziger()) {
            button40er.setEnabled(true);
        }
        else {
            button40er.setEnabled(false);
        }
    }

    private static void handAktualisieren() {
        Spieler selbst = spielfeldlogik.getSelbst();
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
            ArrayList<Spieler> andereSpieler = spielfeldlogik.getAlleSpieler();
            andereSpieler.remove(selbst);
            for (Spieler s: andereSpieler) andereHandAktualisieren(s);
        }
    }

    private static void andereHandAktualisieren(Spieler andererSpieler) {
        String andereHand = "";
        int andereHandkartenAnz = andererSpieler.Hand.size();
        for (int i=0;i<andereHandkartenAnz;i++) {
            andereHand += ","+andererSpieler.Hand.get(i).toString();
        }
        String recipientID = "";
        if (andererSpieler.equals(spielfeldlogik.getGegner1())) {
            recipientID=gegner1ID;
        } else if (andererSpieler.equals(spielfeldlogik.getMitspieler())) {
            recipientID=mitspielerID;
        } else if (andererSpieler.equals(spielfeldlogik.getGegner2())) {
            recipientID=gegner2ID;
        }
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, recipientID, (HANDKARTEN + ":" + andereHand).getBytes());
    }

    private static void punkteAktualisieren() {
        imageView_eigeneKarte.setVisibility(View.INVISIBLE);
        imageView_karteGegner1.setVisibility(View.INVISIBLE);
        imageView_karteMitspieler.setVisibility(View.INVISIBLE);
        imageView_karteGegner2.setVisibility(View.INVISIBLE);
        int p1 = spielfeldlogik.getSelbst().getPunkte();
        int p2 = spielfeldlogik.getGegner1().getPunkte();
        punkteSelbst.setText(Integer.toString(p1));
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (PUNKTE + ":" + Integer.toString(p2)).getBytes());
    }

    private void spielRundenStart() {
        Handler handler0 = new Handler();
        handler0.postDelayed(new Runnable() {
            @Override
            public void run() {
                spielfeldlogik.neueSpielRunde();
                imageView_karteGegner1.setVisibility(View.INVISIBLE);
                imageView_eigeneKarte.setVisibility(View.INVISIBLE);
                imageView_karteGegner2.setVisibility(View.INVISIBLE);
                imageView_karteMitspieler.setVisibility(View.INVISIBLE);
                punkteSelbst.setText("0");
                handAktualisieren();
                if (spielfeldlogik.getAmZugSpielerNr() == SPIELER1) {
                    handKartenAusspielbar();
                    buttonAufdrehen.setVisibility(View.VISIBLE);
                    buttonTrumpfAnsagen.setVisibility(View.VISIBLE);
                } else if (spielfeldlogik.getAmZugSpielerNr() == SPIELER2) {
                    Nearby.Connections.sendReliableMessage(mGoogleApiClient, gegner1ID, (TRUMPFANSAGEN + ":").getBytes());
                } else if (spielfeldlogik.getAmZugSpielerNr() == SPIELER3) {
                    Nearby.Connections.sendReliableMessage(mGoogleApiClient, mitspielerID, (TRUMPFANSAGEN + ":").getBytes());
                } else if (spielfeldlogik.getAmZugSpielerNr() == SPIELER4) {
                    Nearby.Connections.sendReliableMessage(mGoogleApiClient, gegner2ID, (TRUMPFANSAGEN + ":").getBytes());
                }
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

    private static void handKartenAusspielbar() {
        ArrayList<Boolean> handkartenSpielbar = spielfeldlogik.getHandkartenSpielbar();
        for (int i=0;i<5;i++) {
            ImageView imageViewK = handkartenImages.get(i);
            if (handkartenSpielbar.get(i)) {
                imageViewK.setEnabled(true);
                imageViewK.setAlpha(1f);
            } else {
                imageViewK.setEnabled(false);
                imageViewK.setAlpha(0.2f);
            }
        }
    }

    private static void spielRundenEnde() {
        if (spielfeldlogik.isSpielZuEnde()) {
            spielEnde();
        } else {
            int winners = spielfeldlogik.getWinners();
            boolean win = winners == 0 ? true : false;
            Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIELRUNDENENDE + ":" + winners).getBytes());
            String rundenAusgang = "";
            if (win) {
                rundenAusgang = "Sieg";
            } else {
                rundenAusgang = "Niederlage";
            }
            Toast.makeText(appContext, rundenAusgang, Toast.LENGTH_SHORT).show();
        }
    }

    private static void spielEnde() {
        int finalWinners = spielfeldlogik.getFinalWinners();
        boolean finalWin = finalWinners == 0 ? true : false;
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIELENDE + ":" + finalWinners).getBytes());
        /*Bundle args = new Bundle();
        args.putBoolean("win", finalWin);
        DialogFragment gameEndDialogFragment = new GameEnd();
        gameEndDialogFragment.setArguments(args);
        gameEndDialogFragment.show(getFragmentManager(), "GameEnd");*/
        String spielAusgang = "";
        if (finalWin) {
            spielAusgang = "Sieg";
        } else {
            spielAusgang = "Niederlage";
        }
        Toast.makeText(appContext, spielAusgang, Toast.LENGTH_SHORT).show();
    }

    private void trumpfansagen(String farbe) {
        spielfeldlogik.trumpfAnsagen(farbe);
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TRUMPFFARBE + ":" + spielfeldlogik.getAngesagteFarbe()).getBytes());
        imageView_trumpfIcon.setImageResource(Karte.getIconResourceId(spielfeldlogik.getAngesagteFarbe()));
        ArrayList<Spieler> andereSpieler = spielfeldlogik.getAlleSpieler();
        andereSpieler.remove(0);
        for (Spieler s: andereSpieler) andereHandAktualisieren(s);
        buttonsNichtKlickbar();
        buttonAufdrehen.setVisibility(View.INVISIBLE);
        buttonTrumpfAnsagen.setVisibility(View.INVISIBLE);
        buttonSpielAnsagen.setVisibility(View.VISIBLE);
        buttonWeiter.setVisibility(View.VISIBLE);
    }

    private static void andererSpielerKannSpielRufen() {
        String spieleAnsagbar = "";
        if (spielfeldlogik.getSpieleAnsagbar().contains("Schnapser"))
            spieleAnsagbar += " " + SCHNAPSER;
        if (spielfeldlogik.getSpieleAnsagbar().contains("Land"))
            spieleAnsagbar += " " + LAND;
        if (spielfeldlogik.getSpieleAnsagbar().contains("Kontraschnapser"))
            spieleAnsagbar += " " + KONTRASCHNAPSER;
        if (spielfeldlogik.getSpieleAnsagbar().contains("Bauernschnapser"))
            spieleAnsagbar += " " + BAUERNSCHNAPSER;
        if (spielfeldlogik.getSpieleAnsagbar().contains("Kontrabauernschnapser"))
            spieleAnsagbar += " " + KONTRABAUERNSCHNAPSER;
        if (spielfeldlogik.getSpieleAnsagbar().contains("Farbenjodler"))
            spieleAnsagbar += " " + FARBENJODLER;
        if (spielfeldlogik.getSpieleAnsagbar().contains("Herrenjodler"))
            spieleAnsagbar += " " + HERRENJODLER;
        spieleAnsagbar.trim();
        String recipientID = "";
        int spielerNr = spielfeldlogik.getAmZugSpielerNr();
        if (spielerNr == SPIELER2) {
            recipientID=gegner1ID;
        } else if (spielerNr == SPIELER3) {
            recipientID=mitspielerID;
        } else if (spielerNr == SPIELER4) {
            recipientID=gegner2ID;
        }
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, recipientID, (SPIELANSAGEN + ":" + spieleAnsagbar).getBytes());
    }

    private static void andererSpielerAmFlecken() {
        int gflecken = -1;
        if (spielfeldlogik.isGegenFleckRunde()) gflecken = 1;
        else gflecken = 0;
        String recipientID = endpointIDs.get(spielfeldlogik.getAmZugSpielerNr()-1);
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, recipientID, (FLECKEN + ":" + gflecken).getBytes());
    }

    public void popup20er(View view) {
        PopupMenu popup = new PopupMenu(Spielfeld4Host.this, button20er);
        popup.inflate(R.menu.popup_menu_20er);
        MenuItem herz20er = (MenuItem) popup.getMenu().getItem(0);
        MenuItem karo20er = (MenuItem) popup.getMenu().getItem(1);
        MenuItem pik20er = (MenuItem) popup.getMenu().getItem(2);
        MenuItem kreuz20er = (MenuItem) popup.getMenu().getItem(3);
        herz20er.setVisible(false);
        karo20er.setVisible(false);
        pik20er.setVisible(false);
        kreuz20er.setVisible(false);
        ArrayList<String> verfuegbareZwanziger = spielfeldlogik.getVerfuegbareZwanziger();
        for (int i = 0; i < verfuegbareZwanziger.size(); i++) {
            switch (verfuegbareZwanziger.get(i)) {
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
        PopupMenu popup = new PopupMenu(Spielfeld4Host.this, buttonTrumpfAnsagen);
        popup.inflate(R.menu.popup_menu_trumpfansagen);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                trumpfansagen(menuItem.getTitle().toString());
                return true;
            }
        });
        popup.show();
    }

    public void popupSpielAnsagen(final View view) {
        PopupMenu popup = new PopupMenu(Spielfeld4Host.this, buttonSpielAnsagen);
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
        ArrayList<String> ansagbareSpiele = spielfeldlogik.getSpieleAnsagbar();
        try {
            if (ansagbareSpiele.contains("Schnapser")) schnapser.setVisible(true);
            if (ansagbareSpiele.contains("Land")) land.setVisible(true);
            if (ansagbareSpiele.contains("Kontraschnapser")) kontraschnapser.setVisible(true);
            if (ansagbareSpiele.contains("Bauernschnapser")) bauernschnapser.setVisible(true);
            if (ansagbareSpiele.contains("Kontrabauernschnapser")) kontrabauernschnapser.setVisible(true);
            if (ansagbareSpiele.contains("Farbenjodler")) farbenjodler.setVisible(true);
            if (ansagbareSpiele.contains("Herrenjodler")) herrenjodler.setVisible(true);
        } catch (Exception e) {

        }
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                spielfeldlogik.spielRufen(menuItem.getTitle().toString());
                if (spielfeldlogik.isSpielRundenEnde()) {
                    spielRundenEnde();
                } else if (spielfeldlogik.isSpielRufRunde()) andererSpielerKannSpielRufen();
                else {
                    Toast.makeText(appContext, spielfeldlogik.getSpiel() + " wird gespielt", Toast.LENGTH_SHORT).show();
                    if (spielfeldlogik.getAmZugSpielerNr()==SPIELER1) {
                        buttonFlecken.setVisibility(View.VISIBLE);
                        buttonWeiter.setVisibility(View.VISIBLE);
                    } else {
                        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIEL + ":" + spielfeldlogik.getAmZugSpielerNr() + ":" + spielfeldlogik.getSpiel()).getBytes());
                    }
                }
                buttonSpielAnsagen.setVisibility(view.INVISIBLE);
                buttonWeiter.setVisibility(view.INVISIBLE);
                return true;
            }
        });
        popup.show();
    }

    public void fleckenOnClick(View view) {
        spielfeldlogik.flecken();
        andererSpielerAmFlecken();
        buttonWeiter.setVisibility(View.INVISIBLE);
        buttonFlecken.setVisibility(View.INVISIBLE);
    }

    public void weiterOnClick(View view) {
        if (spielfeldlogik.isSpielRufRunde()) {
            spielfeldlogik.spielRufen(null);
            if (spielfeldlogik.isSpielRufRunde())
                andererSpielerKannSpielRufen();
            else {
                Toast.makeText(appContext, spielfeldlogik.getSpiel() + " wird gespielt",
                        Toast.LENGTH_SHORT).show();
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIEL + ":" + SPIELER2 + ":"
                        + spielfeldlogik.getSpiel()).getBytes());
            }
            buttonSpielAnsagen.setVisibility(view.INVISIBLE);
            buttonWeiter.setVisibility(view.INVISIBLE);
        }
        else if (spielfeldlogik.isFleckRunde() || spielfeldlogik.isGegenFleckRunde()){
            spielfeldlogik.nichtFlecken();
            buttonWeiter.setVisibility(View.INVISIBLE);
            buttonFlecken.setVisibility(View.INVISIBLE);
            if (spielfeldlogik.isFleckRunde() || spielfeldlogik.isGegenFleckRunde()) {
                andererSpielerAmFlecken();
            } else {
                if (spielfeldlogik.getAmZugSpielerNr()==SPIELER1) {
                    eigenerZug();
                    handKartenAusspielbar();
                } else {
                    weiterNachricht(null, endpointIDs);
                }
            }
        }
    }

    public void ansagen40er(View view) {
        spielfeldlogik.vierzigerAnsagen();
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (ANGESAGT40ER + ":" + spielfeldlogik.getAmZugSpielerNr()).getBytes());
        punkteAktualisieren();
        button40er.setEnabled(false);
        button20er.setEnabled(false);
    }

    public void karte1OnClick(View view) {
        zugAusfuehren(0);
    }

    public void karte2OnClick(View view) {
        zugAusfuehren(1);
    }

    public void karte3OnClick(View view) {
        zugAusfuehren(2);
    }

    public void karte4OnClick(View view) {
        zugAusfuehren(3);
    }

    public void karte5OnClick(View view) {
        zugAusfuehren(4);
    }

    public void aufdrehenOnClick(View view) {
        trumpfansagen(null);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        //Screen Lock aktivieren
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Intent intent = new Intent(Spielfeld4Host.this, Lobby.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        //Screen Lock aktivieren
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        startActivity(new Intent(Spielfeld4Host.this, Startmenue.class));
        finish();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.herz_20er:
                spielfeldlogik.zwanzigerAnsagen("Herz");
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (ANGESAGT20ER + ":" + "Herz").getBytes());
                break;
            case R.id.karo_20er:
                spielfeldlogik.zwanzigerAnsagen("Karo");
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (ANGESAGT20ER + ":" + "Karo").getBytes());
                break;
            case R.id.pik_20er:
                spielfeldlogik.zwanzigerAnsagen("Pik");
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (ANGESAGT20ER + ":" + "Pik").getBytes());
                break;
            case R.id.kreuz_20er:
                spielfeldlogik.zwanzigerAnsagen("Kreuz");
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (ANGESAGT20ER + ":" + "Kreuz").getBytes());
                break;
            default:
                return false;
        }
        button20er.setEnabled(false);
        button40er.setEnabled(false);
        if (spielfeldlogik.isSpielRundenEnde()) spielRundenEnde();
        punkteAktualisieren();
        handKartenAusspielbar();
        return true;
    }

    public static void receiveFromLobby(String endpointID, byte[] payload, boolean isReliable)
    {
        ArrayList<String> endpointIDsWithoutSender = new ArrayList<String>(endpointIDs);
        endpointIDsWithoutSender.remove(endpointID);
        int sender;
        if (endpointID.equals(gegner1ID)) sender=SPIELER2;
        else if (endpointID.equals(mitspielerID)) sender=SPIELER3;
        else sender=SPIELER4;
        String message = new String(payload);
        switch (((message.split(":")[0]))) {
            case KARTEGESPIELT: int gespielteKarteNr = Integer.decode(message.split(":")[1]);
                Karte gespielteKarte = spielfeldlogik.karteAusspielen(gespielteKarteNr);
                //Toast.makeText(appContext,"gegnerischeHand.contains("+gegnerischeKarte.toString()+") "+Boolean.toString(gegner.Hand.contains(gegnerischeKarte)),Toast.LENGTH_SHORT).show();
                spielfeldlogik.karteAusspielen(gespielteKarteNr);
                weiterNachricht(gespielteKarte, endpointIDsWithoutSender);
                //Toast.makeText(appContext,gegnerischeKarte.toString()+" entfernt"+Boolean.toString(gegner.Hand.contains(gegnerischeKarte)),Toast.LENGTH_SHORT).show();


                // buttonGegnerischeKarte.setText(gegnerischeKarte.getFarbe() + gegnerischeKarte.getWertigkeit());
                switch (sender) {
                    case SPIELER2: imageView_karteGegner1.setImageResource(gespielteKarte.getImageResourceId());
                        break;
                    case SPIELER3: imageView_karteMitspieler.setImageResource(gespielteKarte.getImageResourceId());
                        break;
                    case SPIELER4: imageView_karteGegner2.setImageResource(gespielteKarte.getImageResourceId());
                        break;
                    default:;
                }

                if (spielfeldlogik.isZugBeginn()) {
                    // Execute some code after 2 seconds have passed
                    Handler handler = new Handler();
                    handler.postDelayed(new Zugende(), 2000);
                }
                break;
            case ANGESAGT40ER:
                spielfeldlogik.vierzigerAnsagen();
                punkteAktualisieren();
                if (spielfeldlogik.isSpielRundenEnde()) {
                    spielRundenEnde();
                }
                String spielbareKarten = spielbareKartenNachrichtZusammenstellen();
                String recipientID = endpointIDs.get(spielfeldlogik.getAmZugSpielerNr() - 1);
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, recipientID, (KARTENSPIELBAR + ":" + spielbareKarten).getBytes());
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDsWithoutSender, (ANGESAGT40ER + ":").getBytes());
                Toast.makeText(appContext, "40er angesagt", Toast.LENGTH_SHORT).show();
                break;
            case ANGESAGT20ER: String farbe = message.substring(2);
                spielfeldlogik.zwanzigerAnsagen(farbe);
                punkteAktualisieren();
                if (spielfeldlogik.isSpielRundenEnde()) {
                    spielRundenEnde();
                }
                String spielbareKarten1 = spielbareKartenNachrichtZusammenstellen();
                String recipientID1 = endpointIDs.get(spielfeldlogik.getAmZugSpielerNr() - 1);
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, recipientID1, (KARTENSPIELBAR + ":" + spielbareKarten1).getBytes());
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDsWithoutSender, (ANGESAGT20ER + ":" + farbe).getBytes());
                Toast.makeText(appContext, farbe+" 20er angesagt", Toast.LENGTH_SHORT).show();
                break;
            case SPIELANSAGEN: String angesagtesSpiel = message.substring(2);
                if (!angesagtesSpiel.equals("0")) {
                    spielfeldlogik.spielRufen(angesagtesSpiel);
                    if (spielfeldlogik.isSpielRundenEnde()) {
                        spielRundenEnde();
                    }
                }
                if (spielfeldlogik.isSpielRufRunde()) {
                    if (spielfeldlogik.getAmZugSpielerNr() == SPIELER1) {
                        buttonSpielAnsagen.setVisibility(View.VISIBLE);
                        buttonWeiter.setVisibility(View.VISIBLE);
                    } else {
                        andererSpielerKannSpielRufen();
                    }
                } else {
                    if (spielfeldlogik.getAmZugSpielerNr() == SPIELER1) {
                        buttonFlecken.setVisibility(View.VISIBLE);
                        buttonWeiter.setVisibility(View.VISIBLE);
                    } else {
                        String recipientID2 = endpointIDs.get(spielfeldlogik.getAmZugSpielerNr() - 1);
                        Nearby.Connections.sendReliableMessage(mGoogleApiClient, recipientID2, (FLECKEN + ":" + 0).getBytes());
                    }
                }
                break;
            case TRUMPFFARBE: String trumpffarbe = message.split(":")[1];
                spielfeldlogik.trumpfAnsagen(trumpffarbe);
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDsWithoutSender, (TRUMPFFARBE + ":" + spielfeldlogik.getAngesagteFarbe()).getBytes());
                imageView_trumpfIcon.setImageResource(Karte.getIconResourceId(spielfeldlogik.getAngesagteFarbe()));

                ArrayList<Spieler> andereSpieler = spielfeldlogik.getAlleSpieler();
                andereSpieler.remove(0);
                for (Spieler s: andereSpieler) andereHandAktualisieren(s);
                andererSpielerKannSpielRufen();
                break;
            case AUFDREHEN: spielfeldlogik.trumpfAnsagen(null);
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TRUMPFFARBE + ":" + spielfeldlogik.getAngesagteFarbe()).getBytes());
                imageView_trumpfIcon.setImageResource(Karte.getIconResourceId(spielfeldlogik.getAngesagteFarbe()));
                ArrayList<Spieler> andereSpieler1 = spielfeldlogik.getAlleSpieler();
                andereSpieler1.remove(0);
                for (Spieler s: andereSpieler1) andereHandAktualisieren(s);
                andererSpielerKannSpielRufen();
                break;
            case FLECKEN: if (message.split(":").length>1) {
                    spielfeldlogik.nichtFlecken();
                } else {
                    spielfeldlogik.flecken();
                }
                if (spielfeldlogik.isFleckRunde() || spielfeldlogik.isGegenFleckRunde()) {
                    int gflecken = spielfeldlogik.isGegenFleckRunde() ? 1 : 0;
                    if (spielfeldlogik.getAmZugSpielerNr() == SPIELER1) {
                        if (spielfeldlogik.isGegenFleckRunde()) buttonGegenflecken.setVisibility(View.VISIBLE);
                        else buttonFlecken.setVisibility(View.VISIBLE);
                        buttonWeiter.setVisibility(View.VISIBLE);
                    } else {
                        String recipientID3 = endpointIDs.get(spielfeldlogik.getAmZugSpielerNr() - 1);
                        Nearby.Connections.sendReliableMessage(mGoogleApiClient, recipientID3, (FLECKEN + ":" + gflecken).getBytes());
                    }
                } else {
                    if (spielfeldlogik.getAmZugSpielerNr() == SPIELER1) {
                        eigenerZug();
                        handKartenAusspielbar();
                    } else {
                        weiterNachricht(null, endpointIDs);
                    }
                }
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
            if (spielfeldlogik.isSpielRundenEnde()) {
                spielRundenEnde();
            } else {
                handAktualisieren();
                if (spielfeldlogik.getAmZugSpielerNr() == SPIELER1) {
                    eigenerZug();
                    handKartenAusspielbar();
                }
            }
        }
    }
}
