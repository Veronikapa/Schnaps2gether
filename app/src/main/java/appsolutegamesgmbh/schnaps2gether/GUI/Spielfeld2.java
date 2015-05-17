package appsolutegamesgmbh.schnaps2gether.GUI;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spieler;
import appsolutegamesgmbh.schnaps2gether.R;


public class Spielfeld2 extends Activity implements GameEnd.GameEndDialogListener, PopupMenu.OnMenuItemClickListener {

    private Spiel2 spiel;
    /*private Button buttonKarte1;
    private Button buttonKarte2;
    private Button buttonKarte3;
    private Button buttonKarte4;
    private Button buttonKarte5;
    private ArrayList<Button> handkartenButtons;
*/
    /*private Button buttonEigeneKarte;
    private Button buttonGegnerischeKarte;
    private Button buttonStapel;
    private Button buttonTrumpfkarte;*/

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

    private Button buttonZudrehen;
    private Button button20er;
    private Button button40er;
    private Button buttonTrumpfTauschen;
    private Button buttonGKarte1I;
    private Button buttonGKarte2I;
    private Button buttonGestochenI;
    private Button buttonGKarte1G;
    private Button buttonGKarte2G;
    private Button buttonGestochenG;
    private MenuItem herz20er;
    private MenuItem karo20er;
    private MenuItem pik20er;
    private MenuItem kreuz20er;
    private Spieler selbst;
    private Spieler gegner;
    private Karte karte1;
    private Karte karte2;
    private Karte karte3;
    private Karte karte4;
    private Karte karte5;
    private Karte gegnerischeKarte;
    private Karte trumpfkarte;
    private TextView punkteGegner;
    private TextView punkteSelbst;
    private TextView txtSelbst;
    private TextView txtGegner;
    private Bummerl2 bummerl;
    private boolean pruefegestochenG;
    private boolean pruefegestochenI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spielfeld2);

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


/*
        buttonKarte1 = (Button) findViewById(R.id.main_button1);
        buttonKarte2 = (Button) findViewById(R.id.main_button2);
        buttonKarte3 = (Button) findViewById(R.id.main_button3);
        buttonKarte4 = (Button) findViewById(R.id.main_button4);
        buttonKarte5 = (Button) findViewById(R.id.main_button5);

       */

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


       /* buttonEigeneKarte = (Button) findViewById(R.id.main_buttonI);
        buttonGegnerischeKarte = (Button) findViewById(R.id.main_buttonE);
        buttonTrumpfkarte = (Button) findViewById(R.id.main_buttonT);
        buttonStapel = (Button) findViewById(R.id.main_buttonD);*/

        buttonGKarte1I = (Button) findViewById(R.id.cmd_1K_I);
        buttonGKarte2I = (Button) findViewById(R.id.cmd_2K_I);
        buttonGestochenI = (Button) findViewById(R.id.cmd_gestochen_I);
        buttonGKarte1G = (Button) findViewById(R.id.cmd_1K_G);
        buttonGKarte2G = (Button) findViewById(R.id.cmd_2K_G);
        buttonGestochenG = (Button) findViewById(R.id.cmd_gestochen_G);


        handkartenImages = new ArrayList<ImageView>();
        handkartenImages.add(0, imageView_karte1);
        handkartenImages.add(1, imageView_karte2);
        handkartenImages.add(2, imageView_karte3);
        handkartenImages.add(3, imageView_karte4);
        handkartenImages.add(4, imageView_karte5);



       /* handkartenButtons = new ArrayList<Button>();
        handkartenButtons.add(0, buttonKarte1);
        handkartenButtons.add(1, buttonKarte2);
        handkartenButtons.add(2, buttonKarte3);
        handkartenButtons.add(3, buttonKarte4);
        handkartenButtons.add(4, buttonKarte5);*/

        spielStart();
    }

    private void zugAusführen(int i) {
        final Karte k = selbst.Hand.get(i);
        buttonsNichtKlickbar();
        spiel.Auspielen(k, selbst);
        gespielteKarteEntfernen(i);
        //buttonEigeneKarte.setText(k.getFarbe() + k.getWertigkeit());
        imageView_eigeneKarte.setImageResource(k.getImageResourceId());

        if (gegnerischeKarte == null) {
            gegnerischerZug(karte1);
        }
        spiel.ZugAuswerten(k, gegnerischeKarte);
        // Execute some code after 2 seconds have passed
        Handler handler = new Handler();
        handler.postDelayed(new Zugende(), 2000);
    }

    private void eigenerZug() {
        if (!spiel.isZugedreht()) {
            buttonZudrehen.setEnabled(true);

            if (selbst.Hand.contains(new Karte(spiel.getTrumpf(),"Bube",2))) {
                buttonTrumpfTauschen.setEnabled(true);
            }
            else {
                buttonTrumpfTauschen.setEnabled(false);
            }
            if(hat20er()) {
                button20er.setEnabled(true);
            }
            else {
                button20er.setEnabled(false);
            }
            if(hat40er()) {
                button40er.setEnabled(true);
            }
            else {
                button40er.setEnabled(false);
            }
        }
    }

    private void gegnerischerZug(Karte karteS1) {
        gegnerischeKarte = spiel.AuspielenComputer(karteS1);


       // buttonGegnerischeKarte.setText(gegnerischeKarte.getFarbe() + gegnerischeKarte.getWertigkeit());
        imageView_karteGegner.setImageResource(gegnerischeKarte.getImageResourceId());

        if (spiel.isZugedreht()) {
            buttonZudrehen.setEnabled(false);
            buttonZudrehen.setText("Zugedreht");
        }
    }

    private void handAktualisieren() {
        int handkartenAnz = selbst.Hand.size();
        for (int i=0;i<5;i++) {

            //Button buttonK = handkartenButtons.get(i);
            ImageView imageViewK = handkartenImages.get(i);

            if (i<handkartenAnz) {
                Karte k = selbst.Hand.get(i);
                //buttonK.setText(k.getFarbe() + k.getWertigkeit());
                //buttonK.setVisibility(View.VISIBLE);
                imageViewK.setImageResource(k.getImageResourceId());
                imageViewK.setVisibility(View.VISIBLE);

            } else {
                //buttonK.setVisibility(View.INVISIBLE);
                imageViewK.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void punkteAktualisieren() {
       /* buttonEigeneKarte.setText("");
        buttonGegnerischeKarte.setText("");*/
        int p1 = selbst.getPunkte();
        int p2 = gegner.getPunkte();
        punkteGegner.setText(Integer.toString(p2));
        punkteSelbst.setText(Integer.toString(p1));
    }

    private void gespielteKarteEntfernen(int i) {

        //handkartenButtons.get(i).setVisibility(View.INVISIBLE);
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
        gegner = spiel.getS2();

        //buttonStapel.setText("20");
        trumpfkarte = spiel.getAufgedeckterTrumpf();
        //buttonTrumpfkarte.setText(trumpfkarte.getFarbe() + trumpfkarte.getWertigkeit());
        imageView_trumpf.setImageResource(trumpfkarte.getImageResourceId());
        imageView_trumpfIcon.setImageResource(trumpfkarte.getIconResourceId());


        pruefegestochenG = true;
        pruefegestochenI = true;

        handKartenKlickbar();
        buttonZudrehen.setEnabled(true);
        buttonZudrehen.setText(R.string.buttonZ);
        //buttonEigeneKarte.setText("");
       // buttonGegnerischeKarte.setText("");
        punkteSelbst.setText("0");
        punkteGegner.setText("0");
        gegnerischeKarte = null;
        handAktualisieren();
        eigenerZug();
    }

    private void buttonsNichtKlickbar() {
        /*buttonKarte1.setEnabled(false);
        buttonKarte2.setEnabled(false);
        buttonKarte3.setEnabled(false);
        buttonKarte4.setEnabled(false);
        buttonKarte5.setEnabled(false);*/

        imageView_karte1.setEnabled(false);
        imageView_karte2.setEnabled(false);
        imageView_karte3.setEnabled(false);
        imageView_karte4.setEnabled(false);
        imageView_karte5.setEnabled(false);


        button20er.setEnabled(false);
        button40er.setEnabled(false);
        buttonZudrehen.setEnabled(false);
        buttonTrumpfTauschen.setEnabled(false);
    }

    private void handKartenKlickbar() {
        int handkartenAnz = selbst.Hand.size();
        for (int i=0;i<5;i++) {
           // Button buttonK = handkartenButtons.get(i);
            ImageView imageViewK = handkartenImages.get(i);


            if (i<handkartenAnz && spiel.DarfKarteAuswaehlen(gegnerischeKarte, selbst.Hand.get(i), selbst)) {
                //buttonK.setEnabled(true);
                imageViewK.setEnabled(true);
                imageViewK.setAlpha(1f);
            } else {
                imageViewK.setEnabled(false);
                imageViewK.setAlpha(0.2f);
                //buttonK.setEnabled(false);
            }
        }
    }

    private void spielEnde() {
        boolean win = true;
        if (selbst.getPunkte()<66) {
            win = false;
        }
        Bundle args = new Bundle();
        imageView_karteGegner.setImageDrawable(null);
        imageView_eigeneKarte.setImageDrawable(null);
        args.putBoolean("win", win);
        DialogFragment gameEndDialogFragment = new GameEnd();
        gameEndDialogFragment.setArguments(args);
        gameEndDialogFragment.show(getFragmentManager(), "GameEnd");
    }

    public void zudrehen(View view) {
        spiel.Zudrehen();
        buttonZudrehen.setEnabled(false);
        buttonZudrehen.setText("Zugedreht");
    }

    public void popup20er(View view) {
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
        spiel.Ansagen20er(spiel.getTrumpf(), selbst);
        if (spiel.istSpielzuEnde(bummerl)) spielEnde();
        punkteAktualisieren();
        button40er.setEnabled(false);
        button20er.setEnabled(false);
        handKartenKlickbar();
    }

    public void trumpfkarteTauschen(View view) {
        spiel.TrumpfkarteAustauschen(new Karte(spiel.getTrumpf(),"Bube",2), selbst);
        trumpfkarte = spiel.getAufgedeckterTrumpf();
        //buttonTrumpfkarte.setText(trumpfkarte.getFarbe() + trumpfkarte.getWertigkeit());
        imageView_trumpf.setImageResource(trumpfkarte.getImageResourceId());
        imageView_trumpfIcon.setImageResource(trumpfkarte.getIconResourceId());
        handAktualisieren();
        buttonTrumpfTauschen.setEnabled(false);
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
        spielStart();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        startActivity(new Intent(Spielfeld2.this, Startmenue.class));
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
        button40er.setEnabled(false);
        if (spiel.istSpielzuEnde(bummerl)) spielEnde();
        punkteAktualisieren();
        handKartenKlickbar();
        return true;
    }

    class Zugende implements Runnable {

        @Override
        public void run() {
            punkteAktualisieren();
            gegnerischeKarte = null;
            if (spiel.istSpielzuEnde(bummerl)) {
                spielEnde();
            } else {
                handAktualisieren();
                if(pruefegestochenG)
                {
                   if(spiel.getS2().Gestochen.size()==2)
                   {
                       buttonGKarte1G.setText(spiel.getS2().Gestochen.get(0).getFarbe() + spiel.getS2().Gestochen.get(0).getWertigkeit());
                       buttonGKarte2G.setText(spiel.getS2().Gestochen.get(1).getFarbe() + spiel.getS2().Gestochen.get(1).getWertigkeit());

                   }
                   else if(spiel.getS2().Gestochen.size()>2)
                   {
                       buttonGestochenG.setVisibility(View.VISIBLE);
                       pruefegestochenG = false;
                   }
                }
                if(pruefegestochenI)
                {
                    if(spiel.getS1().Gestochen.size()==2)
                    {
                        buttonGKarte1I.setText(spiel.getS1().Gestochen.get(0).getFarbe() + spiel.getS1().Gestochen.get(0).getWertigkeit());
                        buttonGKarte2I.setText(spiel.getS1().Gestochen.get(1).getFarbe() + spiel.getS1().Gestochen.get(1).getWertigkeit());
                    }
                    else if(spiel.getS1().Gestochen.size()>2)
                    {
                        buttonGestochenI.setVisibility(View.VISIBLE);
                        pruefegestochenI = false;
                    }
                }
//                if(spiel.AnzahlKartenStapel()!=0)
//                    buttonStapel.setText(Integer.toString(spiel.AnzahlKartenStapel()+1));
//                else
//                    buttonStapel.setText("0");
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
