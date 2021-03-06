
package appsolutegamesgmbh.schnaps2gether.GUI;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spieler;
import appsolutegamesgmbh.schnaps2gether.R;


public class Spielfeld2 extends Activity implements GameEnd.GameEndDialogListener, PopupMenu.OnMenuItemClickListener {

    private Spiel2 spiel;

    private ImageView imageView_karte1;
    private ImageView imageView_karte2;
    private ImageView imageView_karte3;
    private ImageView imageView_karte4;
    private ImageView imageView_karte5;

    private ArrayList<ImageView> handkartenImages;

    private ImageView imageView_trumpf;
    private ImageView imageView_deck;
    private ImageView imageView_eigeneKarte;
    private ImageView imageView_karteGegner;
    private ImageView imageView_trumpfIcon;

   private ImageView stichEigeneKarteG;
    private ImageView stichGegnerKarteG;
    private ImageView stichDeckG;

    private Button buttonZudrehen;
    private Button button20er;
    private Button button40er;
    private Button buttonTrumpfTauschen;

    private MenuItem herz20er;
    private MenuItem karo20er;
    private MenuItem pik20er;
    private MenuItem kreuz20er;
    private Spieler selbst;

    private Karte gegnerischeKarte;
    private Karte trumpfkarte;

    private boolean schummelnAktiv = false;
    private int handkartenNummerZumSchummeln;
    private Bummerl2 bummerl;
    private boolean pruefegestochenG;

    private TextView txt_BummerlSelbst;
    private TextView txt_BummerlGegner;
    private TextView txt_PunkteSelbst;

   private ArrayList<ImageView> stichImages;
    private ImageView stichK1;
    private ImageView stichK2;
    private ImageView stichK3;
    private ImageView stichK4;
    private ImageView stichK5;
    private ImageView stichK6;
    private ImageView stichK7;
    private ImageView stichK8;
    private ImageView stichK9;
    private ImageView stichK10;
    private ImageView stichK11;
    private ImageView stichK12;
    private ImageView stichK13;
    private ImageView stichK14;
    private ImageView stichK15;
    private ImageView stichK16;

    private int istdran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spielfeld2);

        //Screen Lock deaktivieren
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        bummerl = new Bummerl2();

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

        txt_BummerlSelbst= (TextView) findViewById(R.id.txt_BummerlZahlI);
        txt_BummerlSelbst.setText("0");
        txt_BummerlGegner = (TextView) findViewById(R.id.txt_BummerlZahlG1);
        txt_BummerlGegner.setText("0");
        txt_PunkteSelbst = (TextView) findViewById(R.id.txt_PunkteSelbst);
        txt_PunkteSelbst.setText("0");

        ((TextView)findViewById(R.id.txt_BummerlNameI)).setText(Startmenue.SpielerName);

        imageView_deck = (ImageView) findViewById(R.id.imageView_deck);
        imageView_eigeneKarte = (ImageView) findViewById(R.id.imageView_eigeneKarte);
        imageView_karteGegner = (ImageView) findViewById(R.id.imageView_karteGegner);
        imageView_trumpf = (ImageView) findViewById(R.id.imageView_trumpf);
        imageView_trumpfIcon = (ImageView) findViewById(R.id.imageView_trumpfIcon);

        stichEigeneKarteG =(ImageView) findViewById(R.id.stichEigeneKarteG);
        stichGegnerKarteG =(ImageView) findViewById(R.id.stichGegnerKarteG);
        stichDeckG = (ImageView) findViewById (R.id.stichDeckG);

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

        stichImages= new ArrayList<ImageView>();
        stichImages.add(0,stichK1);
        stichImages.add(1,stichK2);
        stichImages.add(2,stichK3);
        stichImages.add(3,stichK4);
        stichImages.add(4,stichK5);
        stichImages.add(5,stichK6);
        stichImages.add(6,stichK7);
        stichImages.add(7,stichK8);
        stichImages.add(8,stichK9);
        stichImages.add(9,stichK10);
        stichImages.add(10,stichK11);
        stichImages.add(11,stichK12);
        stichImages.add(12,stichK13);
        stichImages.add(13,stichK14);
        stichImages.add(14,stichK15);
        stichImages.add(15,stichK16);

        spielStart();
    }

    private void zugAusfuehren(int i) {

        if(schummelnAktiv)
        {
            handkartenNummerZumSchummeln = i;
            try {
                karteTauschenPopupZeigen();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        else {
            final Karte k = selbst.Hand.get(i);
            buttonsNichtKlickbar();
            spiel.Auspielen(k, selbst);
            gespielteKarteEntfernen(i);

            imageView_eigeneKarte.setImageResource(k.getImageResourceId());

            if (gegnerischeKarte == null) {
                gegnerischerZug(k);
            }
            spiel.ZugAuswerten(k, gegnerischeKarte, istdran);

            if (selbst.isIstdran())
                istdran = 0;
            else
                istdran = 1;

            // Execute some code after 2 seconds have passed
            Handler handler = new Handler();
            handler.postDelayed(new Zugende(), 2000);
        }
    }

    //Öffnet Popup mit allen bereits gestochenen Karten des Spielers
    private void karteTauschenPopupZeigen() throws InterruptedException {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        int gestocheneKartenAnz = spiel.getS1().Gestochen.size();

        if(gestocheneKartenAnz == 0)
        {
            Toast.makeText(this.getApplicationContext(),"Schummeln nicht möglich - keine Stiche vorhanden",Toast.LENGTH_SHORT).show();
            schummelnAktiv = false;
            return;
        }

        AlertDialog alert = null;
        for (int i=0;i<gestocheneKartenAnz;i++) {
                Karte k = spiel.getS1().Gestochen.get(i);

                final ImageView imageViewK = new ImageView(this);
                imageViewK.setClickable(true);
                imageViewK.setId(i);
                imageViewK.setImageResource(k.getImageResourceId());
                layout.addView(imageViewK);
                imageViewK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int kartenNummerGestochen = imageViewK.getId();
                        Karte karteHandTauschen = spiel.getS1().Hand.get(handkartenNummerZumSchummeln);

                        spiel.getS1().Hand.set(handkartenNummerZumSchummeln,spiel.getS1().Gestochen.get(kartenNummerGestochen));
                        spiel.getS1().Gestochen.set(kartenNummerGestochen,karteHandTauschen);
                        handAktualisieren();
                        schummelnAktiv= false;
                    }
                });
        }
        builder.setView(layout);
        builder.setInverseBackgroundForced(true);
        alert=builder.create();
        alert.show();
    }

    private void eigenerZug() {
        if (!spiel.isZugedreht()) {
            buttonZudrehen.setVisibility(View.VISIBLE);
            buttonZudrehen.setEnabled(true);

            //buttonZudrehen.setAlpha(1);

            if (selbst.Hand.contains(new Karte(spiel.getTrumpf(), "Bube", 2))) {
                buttonTrumpfTauschen.setVisibility(View.VISIBLE);
                buttonTrumpfTauschen.setEnabled(true);
                //buttonTrumpfTauschen.setAlpha(1f);
            } else {
                buttonTrumpfTauschen.setVisibility(View.INVISIBLE);
                buttonTrumpfTauschen.setEnabled(false);
                //buttonTrumpfTauschen.setAlpha(0.4f);
            }
        }
            if(hat20er()) {
                button20er.setEnabled(true);
                button20er.setAlpha(1f);
            }
            else {
                button20er.setEnabled(false);
                button20er.setAlpha(0.4f);
            }
            if(hat40er()) {
                button40er.setEnabled(true);
                button40er.setAlpha(1f);
            }
            else {
                button40er.setEnabled(false);
                button40er.setAlpha(0.4f);
        }
    }

    private void gegnerischerZug(Karte karteS1) {
        gegnerischeKarte = spiel.AuspielenComputer(karteS1);
        imageView_karteGegner.setImageResource(gegnerischeKarte.getImageResourceId());

        if (spiel.isZugedreht()) {
            //buttonZudrehen.setVisibility(View.INVISIBLE);
            buttonZudrehen.setEnabled(false);
            buttonZudrehen.setAlpha(0.4f);
            buttonZudrehen.setText("Zugedreht");

            imageView_trumpf.setImageResource(R.drawable.deck);
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

    private void stichAktualisieren() {

        int stichAnzahl = spiel.getS1().Gestochen.size();
        for (int i=0;i<16;i++) {


            ImageView imageViewK = stichImages.get(i);

            if (i<stichAnzahl) {
                // Karte k = selbst.Hand.get(i);
                Karte j = spiel.getS1().Gestochen.get(i);

                imageViewK.setImageResource(j.getImageResourceId());
                imageViewK.setVisibility(View.VISIBLE);

            } else {

                imageViewK.setVisibility(View.INVISIBLE);
            }
        }
    }


    private void punkteAktualisieren() {

        int p1 = selbst.getPunkte();
        txt_PunkteSelbst.setText(Integer.toString(p1));
    }

    private void gespielteKarteEntfernen(int i) {

        handkartenImages.get(i).setVisibility(View.INVISIBLE);
    }

    private boolean hat20er() {
        if ((spiel.hat20er(selbst).size()>0 && !hat40er()) || spiel.hat20er(selbst).size()>1)
            return true;
        return false;
    }

    private boolean hat40er() {
        if(spiel.hat20er(selbst).contains(spiel.getTrumpf()))
            return true;
        return false;
    }

    private void spielStart() {
        spiel = new Spiel2(bummerl.getAnzahlSpiele());
        selbst = spiel.getS1();

        trumpfkarte = spiel.getAufgedeckterTrumpf();
        imageView_trumpf.setImageResource(trumpfkarte.getImageResourceId());
        imageView_trumpfIcon.setImageResource(trumpfkarte.getIconResourceId());
        imageView_deck.setImageResource(R.drawable.deck_full);
        imageView_deck.setVisibility(View.VISIBLE);

        istdran = 0;
        pruefegestochenG = true;
        spiel.ZudrehenAufheben();
        buttonZudrehen.setText("zudrehen");

        handKartenKlickbar();
        buttonZudrehen.setVisibility(View.VISIBLE);
        buttonZudrehen.setEnabled(true);

        txt_PunkteSelbst.setText("0");
        gegnerischeKarte = null;
        handAktualisieren();
        stichAktualisieren();
        eigenerZug();
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

    private void handKartenKlickbar() {
        int handkartenAnz = selbst.Hand.size();
        for (int i=0;i<5;i++) {
            ImageView imageViewK = handkartenImages.get(i);

            if (i<handkartenAnz && spiel.DarfKarteAuswaehlen(gegnerischeKarte, selbst.Hand.get(i), selbst)) {
                imageViewK.setEnabled(true);
                imageViewK.setAlpha(1f);
            } else {
                imageViewK.setEnabled(false);
                imageViewK.setAlpha(0.6f);

            }
        }
    }

    private void spielEnde() {
        boolean win = true;
        if (selbst.getPunkte()<66 && !spiel.getS1().isIstdran()) {
            win = false;
        }
        Bundle args = new Bundle();
        imageView_karteGegner.setImageDrawable(null);
        imageView_eigeneKarte.setImageDrawable(null);

        stichEigeneKarteG.setImageDrawable(null);
        stichGegnerKarteG.setImageDrawable(null);

        stichDeckG.setVisibility(View.INVISIBLE);
        args.putBoolean("win", win);
        DialogFragment gameEndDialogFragment = new GameEnd();
        gameEndDialogFragment.setArguments(args);
        gameEndDialogFragment.show(getFragmentManager(), "GameEnd");

        txt_BummerlSelbst.setText(Integer.toString(bummerl.getPunkteS1()));
        txt_BummerlGegner.setText(Integer.toString(bummerl.getPunkteS2()));

        spielStatistikSpeichern(win, selbst.getPunkte(), bummerl.getPunkteS1());
    }

    private void spielStatistikSpeichern(boolean gewonnen, int punkte, int bummerlPunkte)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        int gewonnenBisher = Integer.parseInt(sharedPreferences.getString("spieleGewonnen", "0"));
        int verlorenBisher = Integer.parseInt(sharedPreferences.getString("spieleVerloren", "0"));
        int bummerl = Integer.parseInt(sharedPreferences.getString("Bummerl", "0"));
        int maxPunkte = Integer.parseInt(sharedPreferences.getString("maxPunkte", "0"));

        if(gewonnen)
            gewonnenBisher++;

        else
            verlorenBisher++;

        bummerl+= bummerlPunkte;

        if(punkte>maxPunkte)
            maxPunkte = punkte;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("spieleGewonnen", String.valueOf(gewonnenBisher));
        editor.putString("spieleVerloren", String.valueOf(verlorenBisher));
        editor.putString("Bummerl", String.valueOf(bummerl));
        editor.putString("maxPunkte", String.valueOf(maxPunkte));
        editor.commit();
    }

    public void zudrehen(View view) {
        spiel.Zudrehen(selbst);
        //buttonZudrehen.setVisibility(View.INVISIBLE);
        buttonZudrehen.setEnabled(false);
       buttonZudrehen.setAlpha(0.4f);
        buttonZudrehen.setText("Zugedreht");
    }

    public void popup20er(View view) {
        ArrayList<String> a = spiel.hat20er(selbst);

        //Wenn nur 1 20er möglich, wird keine Popup angezeigt
        if(a.size() == 1) {
            switch (a.get(0)) {
                case "Herz":
                    spiel.Ansagen20er("Herz", selbst);
                    break;
                case "Karo":
                    spiel.Ansagen20er("Karo", selbst);
                    break;
                case "Pik":
                    spiel.Ansagen20er("Pik", selbst);
                    break;
                case "Kreuz":
                    spiel.Ansagen20er("Kreuz", selbst);
                    break;
                default:
                    ;
            }
            button20er.setEnabled(false);
            button20er.setAlpha(0.4f);
            button40er.setEnabled(false);
            button40er.setAlpha(0.4f);
            if (spiel.istSpielzuEnde(bummerl)) spielEnde();
            punkteAktualisieren();
            handKartenKlickbar();
        }

        else
        {
            PopupMenu popup = new PopupMenu(Spielfeld2.this, button20er);
            popup.inflate(R.menu.popup_menu_20er);
            herz20er = (MenuItem) popup.getMenu().getItem(0);
            karo20er = (MenuItem) popup.getMenu().getItem(1);
            pik20er = (MenuItem) popup.getMenu().getItem(2);
            kreuz20er = (MenuItem) popup.getMenu().getItem(3);
            herz20er.setVisible(false);
            karo20er.setVisible(false);
            pik20er.setVisible(false);
            kreuz20er.setVisible(false);
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
                }
            }
            popup.setOnMenuItemClickListener(this);
            popup.show();
        }
    }

    public void ansagen40er(View view) {
        spiel.Ansagen20er(spiel.getTrumpf(), selbst);
        if (spiel.istSpielzuEnde(bummerl)) spielEnde();
        punkteAktualisieren();
        button40er.setEnabled(false);
        button40er.setAlpha(0.4f);
        button20er.setEnabled(false);
        button20er.setAlpha(0.4f);
        handKartenKlickbar();
    }

    public void trumpfkarteTauschen(View view) {
        spiel.TrumpfkarteAustauschen(new Karte(spiel.getTrumpf(), "Bube", 2), selbst);
        trumpfkarte = spiel.getAufgedeckterTrumpf();
        imageView_trumpf.setImageResource(trumpfkarte.getImageResourceId());
        imageView_trumpfIcon.setImageResource(trumpfkarte.getIconResourceId());

        handAktualisieren();
       buttonTrumpfTauschen.setVisibility(View.INVISIBLE);
        buttonTrumpfTauschen.setEnabled(false);
       // buttonTrumpfTauschen.setAlpha(0.4f);

        eigenerZug();
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

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        spielStart();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        //Screen Lock aktivieren
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Intent i = new Intent(Spielfeld2.this, Startmenue.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
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
        button20er.setAlpha(0.4f);
        button40er.setEnabled(false);
        button40er.setAlpha(0.4f);
        if (spiel.istSpielzuEnde(bummerl)) spielEnde();
        punkteAktualisieren();
        handKartenKlickbar();
        return true;
    }

    public void kartenSchauen(View view)
    {
        //3 Sekunden Wartezeit damit Gegner abwähren kann --> Bei Computer Zufallszahl 0 oder 1
        // 0... Schummeln erlaubt, sonst Schummeln nicht erlaubt.
         double schummelnAbgewehrt = Math.round(Math.random() * 2); // *2, da sonst immer auf 0 gerundet wird!

        //Schummeln wurde per Zufall abgewehrt, Spieler darf nicht in die Karten des Gegners schauen
        if(schummelnAbgewehrt >= 1.0)
            Toast.makeText(this.getApplicationContext(),"Schummelversuch wurde abgewehrt.",Toast.LENGTH_SHORT).show();

        //Schummeln wurde per Zufall nicht abgewehrt, in die Karten des Gegners schauen erlaubt
        else{
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setCancelable(true);
            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            int handkartenAnz = spiel.getS2().Hand.size();

            for (int i=0;i<5;i++) {
                    if (i<handkartenAnz) {
                        Karte k = spiel.getS2().Hand.get(i);

                        ImageView imageViewK = new ImageView(this);
                        imageViewK.setImageResource(k.getImageResourceId());
                        layout.addView(imageViewK);
                    }
            }
            builder.setView(layout);
            builder.setInverseBackgroundForced(true);
            final AlertDialog alert=builder.create();
            alert.show();

            // Hide after some seconds
            final Handler handler  = new Handler();
            final Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    if (alert.isShowing()) {
                        alert.dismiss();
                    }
                }
            };

            alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    handler.removeCallbacks(runnable);
                }
            });

            handler.postDelayed(runnable, 1000);
        }
    }

    public void kartenTauschen(View view)
    {
        //3 Sekunden Wartezeit damit Gegner abwähren kann --> Bei Computer Zufallszahl 0 oder 1
        // 0... Schummeln erlaubt, sonst Schummeln nicht erlaubt.
        double schummelnAbgewehrt = Math.round(Math.random() * 2); // *2, da sonst immer auf 0 gerundet wird!

        //Schummeln wurde per Zufall abgewehrt, Spieler darf nicht in die Karten des Gegners schauen
        if(schummelnAbgewehrt >= 1.0) {
            Toast.makeText(this.getApplicationContext(), "Schummelversuch wurde abgewehrt.", Toast.LENGTH_SHORT).show();
            return;
        }

        schummelnAktiv = true;
        Toast.makeText(this.getApplicationContext(),"Wähle Handkarte zum Tauschen",Toast.LENGTH_SHORT).show();
    }

    public void abbrechenSpiel(View view)
    {
        Intent i = new Intent(this, Startmenue.class);
        startActivity(i);
        onStop();
        finish();
    }

    class Zugende implements Runnable {

        @Override
        public void run() {
            punkteAktualisieren();
            stichAktualisieren();

            gegnerischeKarte = null;
            imageView_eigeneKarte.setImageDrawable(null);
            imageView_karteGegner.setImageDrawable(null);
            if (spiel.istSpielzuEnde(bummerl)) {
                spielEnde();
            } else {
                handAktualisieren();
                if(pruefegestochenG)
                {
                    if (spiel.getS2().Gestochen.size() == 2) {
                        stichEigeneKarteG.setImageResource(spiel.getS2().Gestochen.get(0).getImageResourceId());
                        stichGegnerKarteG.setImageResource(spiel.getS2().Gestochen.get(1).getImageResourceId());

                    } else if ((spiel.getS2().Gestochen.size() > 2) && (spiel.getS2().Gestochen.size() <= 4)) {
                        stichDeckG.setVisibility(View.VISIBLE);
                        stichDeckG.setImageResource(R.drawable.deck_v);
                        pruefegestochenG = false;

                    } else if ((spiel.getS2().Gestochen.size() > 4) && (spiel.getS2().Gestochen.size() <= 6)) {
                        stichDeckG.setImageResource(R.drawable.deck_2_v);
                        //stichDeckG.setVisibility(View.VISIBLE);
                        pruefegestochenG = false;

                    } else if (spiel.getS2().Gestochen.size() > 7) {
                        stichDeckG.setImageResource(R.drawable.deck_3_v);
                        pruefegestochenG = false;
                    }

                }

                if(spiel.AnzahlKartenStapel()==0) {
                    imageView_deck.setVisibility(View.INVISIBLE);

                }else if ((spiel.AnzahlKartenStapel()<=2) && (spiel.AnzahlKartenStapel()>0))
                    imageView_deck.setImageResource(R.drawable.deck);
                else if((spiel.AnzahlKartenStapel()<=4) && (spiel.AnzahlKartenStapel()>2))
                    imageView_deck.setImageResource(R.drawable.deck_2);
                else if((spiel.AnzahlKartenStapel()<=6) && (spiel.AnzahlKartenStapel()>4))
                    imageView_deck.setImageResource(R.drawable.deck_3);
                else if((spiel.AnzahlKartenStapel()<=8) && (spiel.AnzahlKartenStapel()>6))
                    imageView_deck.setImageResource(R.drawable.deck_4);
                else if((spiel.AnzahlKartenStapel()<=10) && (spiel.AnzahlKartenStapel()>8))
                    imageView_deck.setImageResource(R.drawable.deck_5);
                else
                    imageView_deck.setImageResource(R.drawable.deck_full);


                if (selbst.isIstdran()) {
                    eigenerZug();
                } else {
                    gegnerischerZug(null);
                }
                handKartenKlickbar();
            }
        }
    }
}

