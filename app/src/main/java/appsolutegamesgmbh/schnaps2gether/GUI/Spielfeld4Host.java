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
import appsolutegamesgmbh.schnaps2gether.DataStructure.Rufspiel;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel4;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spieler;
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

    //Konstanten für das Kennzeichnen und Parsen von Nachrichten
    private static final String KARTEGESPIELT = "0";
    private static final String WEITER = "1";
    private static final String PUNKTE = "2";
    private static final String TRUMPFANSAGEN = "3";
    private static final String ANGESAGT20ER = "4";
    private static final String ANGESAGT40ER = "5";
    private static final String SPIELANSAGEN = "6";
    private static final String SPIELENDE = "7";
    private static final String SPIELSTART = "8";
    private static final String HANDKARTEN = "9";
    private static final String TRUMPFFARBE = "10";
    private static final String ZUGENDE = "11";
    private static final String DISCONNECT = "12";
    private static final String AUFDREHEN = "13";
    private static final String FLECKEN = "14";
    private static final String SPIEL = "15";

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
    private static Button buttonSpielAnsagen;
    private static Button buttonAufdrehen;
    private static Button buttonFlecken;
    private static Button buttonGegenflecken;
    private static Button buttonWeiter;
    private static Spieler selbst;
    private static Spieler gegner1;
    private static Spieler gegner2;
    private static Spieler mitspieler;
    private static ArrayList<Spieler> andereSpieler;
    private static Spieler rufer; //Rufer des gespielten Rufspiels
    private static Karte eigeneKarte;
    private static Karte gegnerischeKarte1;
    private static Karte gegnerischeKarte2;
    private static Karte mitspielerKarte;
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
    private static String  gegner1ID;
    private static String  mitspielerID;
    private static String  gegner2ID;
    private static int anzSpieleAngesagt;
    private static int anzFleckZüge;

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
        gegner1ID = endpointIDs.get(0);
        mitspielerID = endpointIDs.get(1);
        gegner2ID = endpointIDs.get(2);

        appContext = this.getApplicationContext();

        angesagt = false;
        andereSpieler = new ArrayList<Spieler>();
        andereSpieler.add(gegner1);
        andereSpieler.add(mitspieler);
        andereSpieler.add(gegner2);

        bummerl = new Bummerl4();
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, gegner1ID, (SPIELSTART +":"+"2,"+bummerl.toString()).getBytes());
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, mitspielerID, (SPIELSTART +":"+"3,"+bummerl.toString()).getBytes());
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, gegner2ID, (SPIELSTART +":"+"4,"+bummerl.toString()).getBytes());

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

        punkteGegner1 = (TextView) findViewById(R.id.pointsText);
        punkteGegner2 = (TextView) findViewById(R.id.pointsText);
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


        spielStart();
    }
    private void zugAusfuehren(int i) {
        final Karte k = selbst.Hand.get(i);
        eigeneKarte = k;
        buttonsNichtKlickbar();
        spiel.Auspielen(k, selbst);
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (KARTEGESPIELT + ":"+ "1," + k.toString()).getBytes());
        gespielteKarteEntfernen(i);

        //buttonEigeneKarte.setText(k.getFarbe() + k.getWertigkeit());
        imageView_eigeneKarte.setImageResource(k.getImageResourceId());


        if (gegnerischeKarte1 == null) {
            for (Spieler s: andereSpieler) andereHandAktualisieren(s);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Nearby.Connections.sendReliableMessage(mGoogleApiClient, gegner1ID, (WEITER + ":" + 0).getBytes());
                }
            }, 1000);
        } else {
            zugEnde();
        }
    }

    private static void zugEnde() {
        spiel.ZugAuswerten(eigeneKarte, gegnerischeKarte1, mitspielerKarte, gegnerischeKarte2);
        eigeneKarte = gegnerischeKarte1 = mitspielerKarte = gegnerischeKarte2 = null;
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
        if (andererSpieler.equals(gegner1)) {
            recipientID=gegner1ID;
        } else if (andererSpieler.equals(mitspieler)) {
            recipientID=mitspielerID;
        } else if (andererSpieler.equals(gegner2)) {
            recipientID=gegner2ID;
        }
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, recipientID, (HANDKARTEN + ":" + andereHand + ":" + gegKartenSpielBar).getBytes());
    }

    private static void punkteAktualisieren() {
        imageView_eigeneKarte.setVisibility(View.INVISIBLE);
        imageView_karteGegner1.setVisibility(View.INVISIBLE);
        imageView_karteMitspieler.setVisibility(View.INVISIBLE);
        imageView_karteGegner2.setVisibility(View.INVISIBLE);
        int p1 = selbst.getPunkte();
        int p2 = gegner1.getPunkte();
        punkteSelbst.setText(Integer.toString(p1));
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (PUNKTE + ":" + Integer.toString(p2)).getBytes());
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

    private static void andererSpielerAmZug(Spieler andererSpieler) {
        int hast20er = hat20er(andererSpieler) ? 1 : 0;
        int hast40er = hat40er(andererSpieler) ? 1 : 0;
        String hastDie20er = "";
        ArrayList<String> geg20er = spiel.hat20er(andererSpieler);
        for(String farbe: geg20er) {
            hastDie20er += " " + farbe;
        }
        String recipientID = "";
        if (andererSpieler.equals(gegner1)) {
            recipientID=gegner1ID;
        } else if (andererSpieler.equals(mitspieler)) {
            recipientID=mitspielerID;
        } else if (andererSpieler.equals(gegner2)) {
            recipientID=gegner2ID;
        }
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, recipientID, (WEITER + ":" + 1 + " " + hast20er + " " + hast40er + hastDie20er).getBytes());
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

                anzSpieleAngesagt = 0;
                anzFleckZüge = 0;

                imageView_karteGegner1.setVisibility(View.INVISIBLE);
                imageView_eigeneKarte.setVisibility(View.INVISIBLE);
                imageView_karteGegner2.setVisibility(View.INVISIBLE);
                imageView_karteMitspieler.setVisibility(View.INVISIBLE);
                punkteSelbst.setText("0");
                punkteGegner1.setText("0");
                punkteMitspieler.setText("0");
                punkteGegner2.setText("0");
                gegnerischeKarte1 = eigeneKarte = gegnerischeKarte2 = mitspielerKarte = null;
                handAktualisieren();
                if (selbst.isIstdran()) {
                    handKartenKlickbar();
                    buttonAufdrehen.setVisibility(View.VISIBLE);
                } else if (gegner1.isIstdran()) {
                    Nearby.Connections.sendReliableMessage(mGoogleApiClient, gegner1ID, (TRUMPFANSAGEN + ":").getBytes());
                } else if (mitspieler.isIstdran()) {
                    Nearby.Connections.sendReliableMessage(mGoogleApiClient, mitspielerID, (TRUMPFANSAGEN + ":").getBytes());
                } else if (gegner2.isIstdran()) {
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

    private static void handKartenKlickbar() {
        int handkartenAnz = selbst.Hand.size();
        for (int i=0;i<5;i++) {
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

    private void spielEnde() {
        boolean win = true;
        int winners = 0;
        if (selbst.getPunkte()<66) {
            win = false;
            winners = 1;
        }
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIELENDE + ":" + winners).getBytes());
        Bundle args = new Bundle();
        args.putBoolean("win", win);
        DialogFragment gameEndDialogFragment = new GameEnd();
        gameEndDialogFragment.setArguments(args);
        gameEndDialogFragment.show(getFragmentManager(), "GameEnd");
    }

    private void trumpfansagen(String farbe) {
        spiel.Trumpfansagen(farbe, bummerl.getAnzahlSpiele());
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TRUMPFFARBE + ":" + spiel.getAngesagteFarbe()).getBytes());
        imageView_trumpfIcon.setImageResource(Karte.getIconResourceId(spiel.getAngesagteFarbe()));
        for (Spieler s: andereSpieler) andereHandAktualisieren(s);
        buttonsNichtKlickbar();
        buttonAufdrehen.setVisibility(View.INVISIBLE);
        buttonSpielAnsagen.setVisibility(View.VISIBLE);
        buttonWeiter.setVisibility(View.VISIBLE);
    }

    private static void andererSpielerKannSpielAnsagen(Spieler andererSpieler) {
        String spieleAnsagbar = "";
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
                spieleAnsagbar += "1 ";
            else spieleAnsagbar += "0 ";
            if (spiel.DarfSpielAnsagen(new Rufspiel("Farbenjodler"), andererSpieler))
                spieleAnsagbar += "1 ";
            else spieleAnsagbar += "0 ";
            if (spiel.DarfSpielAnsagen(new Rufspiel("Herrenjodler"), andererSpieler))
                spieleAnsagbar += "1";
            else spieleAnsagbar += "0";
        } catch (Exception e) {

        }
        String recipientID = "";
        if (andererSpieler.equals(gegner1)) {
            recipientID=gegner1ID;
        } else if (andererSpieler.equals(mitspieler)) {
            recipientID=mitspielerID;
        } else if (andererSpieler.equals(gegner2)) {
            recipientID=gegner2ID;
        }
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, recipientID, (SPIELANSAGEN + ":" + spieleAnsagbar).getBytes());
    }

    private static void andererSpielerAmFlecken(Spieler andererSpieler, boolean gegenflecken) {
        String recipientID = gegner1ID;
        if (andererSpieler.equals(gegner2)) recipientID = gegner2ID;
        else if (andererSpieler.equals(mitspieler)) recipientID = mitspielerID;
        String gflecken = gegenflecken ? "1" : "0";
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, recipientID, (FLECKEN + ":" + gflecken).getBytes());
    }

    private static void fleckRunde() {
        if (rufer.equals(mitspieler)) {
            Spieler andererSpieler = gegner1;
            if (bummerl.getAnzahlSpiele() % 4 == 2 || bummerl.getAnzahlSpiele() % 4 == 3) {
                andererSpieler = gegner2;
            }
            andererSpielerAmFlecken(andererSpieler, false);
            buttonWeiter.setVisibility(View.INVISIBLE);
        } else {
            if (bummerl.getAnzahlSpiele() % 4 == 0 || bummerl.getAnzahlSpiele() % 4 == 3) {
                buttonWeiter.setVisibility(View.VISIBLE);
                buttonFlecken.setVisibility(View.VISIBLE);
            } else {
                andererSpielerAmFlecken(mitspieler, false);
                buttonWeiter.setVisibility(View.INVISIBLE);
            }
        }
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
        try {
            if (spiel.DarfSpielAnsagen(new Rufspiel("Schnapser"), selbst)) schnapser.setVisible(true);
            if (spiel.DarfSpielAnsagen(new Rufspiel("Land"), selbst)) land.setVisible(true);
            if (spiel.DarfSpielAnsagen(new Rufspiel("Kontraschnapser"), selbst)) kontraschnapser.setVisible(true);
            if (spiel.DarfSpielAnsagen(new Rufspiel("Bauernschnapser"), selbst)) bauernschnapser.setVisible(true);
            if (spiel.DarfSpielAnsagen(new Rufspiel("Kontrabauernschnapser"), selbst)) kontrabauernschnapser.setVisible(true);
            if (spiel.DarfSpielAnsagen(new Rufspiel("Farbenjodler"), selbst)) farbenjodler.setVisible(true);
            if (spiel.DarfSpielAnsagen(new Rufspiel("Herrenjodler"), selbst)) herrenjodler.setVisible(true);
        } catch (Exception e) {

        }
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                try {
                switch (menuItem.getTitle().toString()) {
                    case "Schnapser": spiel.SpielAnsagen(new Rufspiel("Schnapser"), selbst);
                        break;
                    case "Land": spiel.SpielAnsagen(new Rufspiel("Land"), selbst);
                        break;
                    case "Kontraschnapser": spiel.SpielAnsagen(new Rufspiel("Kontraschnapser"), selbst);
                        break;
                    case "Bauernschnapser": spiel.SpielAnsagen(new Rufspiel("Bauernschnapser"), selbst);
                        break;
                    case "Kontrabauernschnapser": spiel.SpielAnsagen(new Rufspiel("Kontrabauernschnapser"), selbst);
                        break;
                    case "Farbenjodler": spiel.SpielAnsagen(new Rufspiel("Farbenjodler"), selbst);
                        if (spiel.istSpielzuEnde(bummerl)) {
                            //TODO: Spielende
                        }
                        break;
                    case "Herrenjodler": spiel.SpielAnsagen(new Rufspiel("Herrenjodler"), selbst);
                        if (spiel.istSpielzuEnde(bummerl)) {
                            //TODO: Spielende
                        }
                        break;
                    default: return false;
                }
                } catch (Exception e) {

                }
                rufer = selbst;
                anzSpieleAngesagt++;
                if (anzSpieleAngesagt<4) andererSpielerKannSpielAnsagen(gegner1);
                else {
                    andererSpielerAmZug(gegner1);
                    Toast.makeText(appContext, spiel.getSpiel().getSpiel()+" wird gespielt", Toast.LENGTH_SHORT).show();
                    Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIEL + ":" + spiel.getSpiel().getSpiel()).getBytes());
                }
                buttonSpielAnsagen.setVisibility(view.INVISIBLE);
                buttonWeiter.setVisibility(view.INVISIBLE);
                return true;
            }
        });
        popup.show();
    }

    public void fleckenOnClick(View view) {
        anzFleckZüge++;
        spiel.Flecken();
        if (bummerl.getAnzahlSpiele() % 4 < 2)  {
            andererSpielerAmFlecken(gegner1, true);
        } else {
            andererSpielerAmFlecken(gegner2, true);
        }
    }

    public void weiterOnClick(View view) {
        if (anzSpieleAngesagt<4) {
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
        }
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
        if (spiel.getAngesagteFarbe()==null) trumpfansagen(selbst.Hand.get(0).getFarbe());
        else zugAusfuehren(0);
    }

    public void karte2OnClick(View view) {
        if (spiel.getAngesagteFarbe()==null) trumpfansagen(selbst.Hand.get(1).getFarbe());
        else zugAusfuehren(1);
    }

    public void karte3OnClick(View view) {
        if (spiel.getAngesagteFarbe()==null) trumpfansagen(selbst.Hand.get(2).getFarbe());
        else zugAusfuehren(2);
    }

    public void karte4OnClick(View view) {
        if (spiel.getAngesagteFarbe()==null) trumpfansagen(selbst.Hand.get(3).getFarbe());
        else zugAusfuehren(3);
    }

    public void karte5OnClick(View view) {
        if (spiel.getAngesagteFarbe()==null) trumpfansagen(selbst.Hand.get(4).getFarbe());
        else zugAusfuehren(4);
    }

    public void aufdrehenOnClick(View view) {
        trumpfansagen(spiel.Aufdrehen().getFarbe());
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
        ArrayList<String> endpointIDsWithoutSender = new ArrayList<String>(endpointIDs);
        endpointIDsWithoutSender.remove(endpointID);
        Spieler sender;
        if (endpointID.equals(gegner1ID)) sender=gegner1;
        else if (endpointID.equals(mitspielerID)) sender=mitspieler;
        else sender=gegner2;
        String message = new String(payload);
        switch (((message.split(":")[0]))) {
            case KARTEGESPIELT: Karte gespielteKarte = new Karte(message.split(":")[1]);
                String ausspieler = "";
                if (sender.equals(gegner1)) {
                    gegnerischeKarte1 = gespielteKarte;
                    ausspieler = "2,";
                }
                else if (sender.equals(mitspieler)) {
                    mitspielerKarte = gespielteKarte;
                    ausspieler = "3,";
                }
                else {
                    gegnerischeKarte2 = gespielteKarte;
                    ausspieler = "4,";
                }
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (KARTEGESPIELT + ":" + ausspieler + gespielteKarte.toString()).getBytes());
                //Toast.makeText(appContext,"gegnerischeHand.contains("+gegnerischeKarte.toString()+") "+Boolean.toString(gegner.Hand.contains(gegnerischeKarte)),Toast.LENGTH_SHORT).show();
                spiel.Auspielen(gespielteKarte, sender);
                //Toast.makeText(appContext,gegnerischeKarte.toString()+" entfernt"+Boolean.toString(gegner.Hand.contains(gegnerischeKarte)),Toast.LENGTH_SHORT).show();


                // buttonGegnerischeKarte.setText(gegnerischeKarte.getFarbe() + gegnerischeKarte.getWertigkeit());
                switch (ausspieler) {
                    case "2": imageView_karteGegner1.setImageResource(gespielteKarte.getImageResourceId());
                        break;
                    case "3": imageView_karteMitspieler.setImageResource(gespielteKarte.getImageResourceId());
                        break;
                    case "4": imageView_karteGegner2.setImageResource(gespielteKarte.getImageResourceId());
                        break;
                    default:;
                }

                if (eigeneKarte!=null && gegnerischeKarte1!=null && mitspielerKarte!=null && gegnerischeKarte2!=null) {
                    zugEnde();
                }
                break;
            case WEITER: String recipientID = "";
                if (sender.equals(gegner2)) {
                handKartenKlickbar();
                } else {
                    if (sender.equals(gegner1)) recipientID = mitspielerID;
                    else recipientID = gegner2ID;
                    Nearby.Connections.sendReliableMessage(mGoogleApiClient, recipientID, (WEITER+":"+0).getBytes());
                }
                Toast.makeText(appContext, "Weiter", Toast.LENGTH_SHORT).show();
                break;
            case ANGESAGT40ER:
                spiel.Ansagen20er(spiel.getAngesagteFarbe(), sender);
                punkteAktualisieren();
                if (spiel.istSpielzuEnde(bummerl)) {
                    //spielEnde();
                }
                andereHandAktualisieren(sender);
                angesagt = true;
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDsWithoutSender, (ANGESAGT40ER+":").getBytes());
                Toast.makeText(appContext, "40er angesagt", Toast.LENGTH_SHORT).show();
                break;
            case ANGESAGT20ER: String farbe = message.substring(2);
                spiel.Ansagen20er(farbe, sender);
                punkteAktualisieren();
                if (spiel.istSpielzuEnde(bummerl)) {
                    //spielEnde();
                }
                andereHandAktualisieren(sender);
                angesagt = true;
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDsWithoutSender, (ANGESAGT20ER+":"+farbe).getBytes());
                Toast.makeText(appContext, farbe+" 20er angesagt", Toast.LENGTH_SHORT).show();
                break;
            case SPIELANSAGEN: String angesagtesSpiel = message.substring(2);
                try {
                    if (!angesagtesSpiel.equals("0")) {
                        spiel.SpielAnsagen(new Rufspiel(angesagtesSpiel), sender);
                        if (spiel.istSpielzuEnde(bummerl)) {
                            //spielEnde();
                        }
                        rufer = sender;
                    }
                } catch (Exception e) {

                }
                anzSpieleAngesagt++;
                if (anzSpieleAngesagt==4) {

                    if (sender.equals(gegner1)) andererSpielerAmZug(mitspieler);
                    else if (sender.equals(mitspieler)) andererSpielerAmZug(gegner2);
                    else {
                        handKartenKlickbar();
                        eigenerZug();
                    }
                    Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (SPIEL + ":" + spiel.getSpiel().getSpiel()).getBytes());
                    Toast.makeText(appContext, spiel.getSpiel().getSpiel()+" wird gespielt", Toast.LENGTH_SHORT).show();
                } else {
                    if (sender.equals(gegner1)) andererSpielerKannSpielAnsagen(mitspieler);
                    else if (sender.equals(mitspieler)) andererSpielerKannSpielAnsagen(gegner2);
                    else {
                        buttonSpielAnsagen.setVisibility(View.VISIBLE);
                        buttonWeiter.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case TRUMPFFARBE: String trumpffarbe = message.split(":")[1];
                spiel.Trumpfansagen(trumpffarbe, bummerl.getAnzahlSpiele());
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDsWithoutSender, (TRUMPFFARBE + ":" + spiel.getAngesagteFarbe()).getBytes());
                imageView_trumpfIcon.setImageResource(Karte.getIconResourceId(spiel.getAngesagteFarbe()));
                for (Spieler s: andereSpieler) andereHandAktualisieren(s);
                andererSpielerKannSpielAnsagen(sender);
                break;
            case AUFDREHEN: spiel.Trumpfansagen(spiel.Aufdrehen().getFarbe(),bummerl.getAnzahlSpiele());
                Nearby.Connections.sendReliableMessage(mGoogleApiClient, endpointIDs, (TRUMPFFARBE+":"+spiel.getAngesagteFarbe()).getBytes());
                andererSpielerKannSpielAnsagen(sender);
                break;
            case FLECKEN: if (message.split(":")[1].equals("0")) {
                    anzFleckZüge++;
                    if (anzFleckZüge==1) {
                        if (sender.equals(gegner1))
                            andererSpielerAmFlecken(gegner2, spiel.isGefleckt());
                        else if (sender.equals(gegner2))
                            andererSpielerAmFlecken(gegner1, spiel.isGefleckt());
                        else if (sender.equals(mitspieler)) {
                            if (spiel.isGefleckt())
                                buttonFlecken.setVisibility(View.VISIBLE);
                            else buttonGegenflecken.setVisibility(View.VISIBLE);
                            buttonWeiter.setVisibility(View.VISIBLE);
                        }
                    } else if (anzFleckZüge==2)  {
                        for (Spieler andererSpieler: andereSpieler)
                            if (andererSpieler.isIstdran()) andererSpielerAmZug(andererSpieler);
                        if (selbst.isIstdran()) {
                            handKartenKlickbar();
                            eigenerZug();
                        }
                    }
                } else if (spiel.isGefleckt()) {
                    spiel.Flecken();
                    for (Spieler andererSpieler: andereSpieler)
                        if (andererSpieler.isIstdran()) andererSpielerAmZug(andererSpieler);
                    if (selbst.isIstdran()) {
                        handKartenKlickbar();
                        eigenerZug();
                    }
                } else if (!spiel.isGefleckt()) {
                    anzFleckZüge=0;
                    spiel.Flecken();
                    if (sender.equals(mitspieler)) {
                        Spieler andererSpieler = gegner1;
                        if (bummerl.getAnzahlSpiele() % 4 == 2 || bummerl.getAnzahlSpiele() % 4 == 3) {
                            andererSpieler = gegner2;
                        }
                        andererSpielerAmFlecken(andererSpieler, true);
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
            gegnerischeKarte1 = mitspielerKarte = gegnerischeKarte2 = eigeneKarte = null;
            if (spiel.istSpielzuEnde(bummerl)) {
                //spielEnde();
            } else {
                handAktualisieren();
                if (selbst.isIstdran()) {
                    eigenerZug();
                    handKartenKlickbar();
                } else if (gegner1.isIstdran()) {
                    andererSpielerAmZug(gegner1);
                } else if (mitspieler.isIstdran()) {
                    andererSpielerAmZug(mitspieler);
                } else {
                    andererSpielerAmZug(gegner2);
                }
            }
        }
    }
}
