
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

    private ImageView stichEigeneKarteI;
    private ImageView stichGegnerKarteI;
    private ImageView stichDeckI;

    private ImageView stichEigeneKarteG;
    private ImageView stichGegnerKarteG;
    private ImageView stichDeckG;



    private Button buttonZudrehen;
    private Button button20er;
    private Button button40er;
    private Button buttonTrumpfTauschen;
    /* private Button buttonGKarte1I;
     private Button buttonGKarte2I;
     private Button buttonGestochenI;*/
    /*private Button buttonGKarte1G;
    private Button buttonGKarte2G;
    private Button buttonGestochenG;*/
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




        //buttonGKarte1I = (Button) findViewById(R.id.cmd_1K_I);
        //buttonGKarte2I = (Button) findViewById(R.id.cmd_2K_I);
        //buttonGestochenI = (Button) findViewById(R.id.cmd_gestochen_I);

        stichEigeneKarteI = (ImageView) findViewById(R.id.stichEigeneKarteI);
        stichGegnerKarteI = (ImageView) findViewById(R.id.stichGegnerKarteI);
        stichDeckI =(ImageView) findViewById(R.id.stichDeckI);

        stichEigeneKarteG =(ImageView) findViewById(R.id.stichEigeneKarteG);
        stichGegnerKarteG =(ImageView) findViewById(R.id.stichGegnerKarteG);
        stichDeckG = (ImageView) findViewById (R.id.stichDeckG);


      /*  buttonGKarte1G = (Button) findViewById(R.id.cmd_1K_G);
        buttonGKarte2G = (Button) findViewById(R.id.cmd_2K_G);
        buttonGestochenG = (Button) findViewById(R.id.cmd_gestochen_G);*/


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
        buttonsNichtKlickbar();
        spiel.Auspielen(k, selbst);
        gespielteKarteEntfernen(i);

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
            buttonZudrehen.setAlpha(1);

            if (selbst.Hand.contains(new Karte(spiel.getTrumpf(),"Bube",2))) {
                buttonTrumpfTauschen.setEnabled(true);
                buttonTrumpfTauschen.setAlpha(1f);
            }
            else {
                buttonTrumpfTauschen.setEnabled(false);
                buttonTrumpfTauschen.setAlpha(0.4f);
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
    }

    private void gegnerischerZug(Karte karteS1) {
        gegnerischeKarte = spiel.AuspielenComputer(karteS1);



        imageView_karteGegner.setImageResource(gegnerischeKarte.getImageResourceId());

        if (spiel.isZugedreht()) {
            buttonZudrehen.setEnabled(false);
            buttonZudrehen.setAlpha(0.4f);
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

                imageViewK.setImageResource(k.getImageResourceId());
                imageViewK.setVisibility(View.VISIBLE);

            } else {

                imageViewK.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void punkteAktualisieren() {

        int p1 = selbst.getPunkte();
        int p2 = gegner.getPunkte();
        punkteGegner.setText(Integer.toString(p2));
        punkteSelbst.setText(Integer.toString(p1));
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
        gegner = spiel.getS2();


        trumpfkarte = spiel.getAufgedeckterTrumpf();

        imageView_trumpf.setImageResource(trumpfkarte.getImageResourceId());
        imageView_trumpfIcon.setImageResource(trumpfkarte.getIconResourceId());


        pruefegestochenG = true;
        pruefegestochenI = true;

        handKartenKlickbar();
        buttonZudrehen.setEnabled(true);
        buttonZudrehen.setAlpha(1f);
        buttonZudrehen.setText(R.string.buttonZ);
        punkteSelbst.setText("0");
        punkteGegner.setText("0");
        gegnerischeKarte = null;
        handAktualisieren();
        eigenerZug();
    }

    private void buttonsNichtKlickbar() {

        imageView_karte1.setEnabled(false);
        imageView_karte1.setAlpha(0.4f);
        imageView_karte2.setEnabled(false);
        imageView_karte2.setAlpha(0.4f);
        imageView_karte3.setEnabled(false);
        imageView_karte3.setAlpha(0.4f);
        imageView_karte4.setEnabled(false);
        imageView_karte4.setAlpha(0.4f);
        imageView_karte5.setEnabled(false);
        imageView_karte5.setAlpha(0.4f);


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
            // Button buttonK = handkartenButtons.get(i);
            ImageView imageViewK = handkartenImages.get(i);


            if (i<handkartenAnz && spiel.DarfKarteAuswaehlen(gegnerischeKarte, selbst.Hand.get(i), selbst)) {
                //buttonK.setEnabled(true);
                imageViewK.setEnabled(true);
                imageViewK.setAlpha(1f);
            } else {
                imageViewK.setEnabled(false);
                imageViewK.setAlpha(0.4f);
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

        stichGegnerKarteI.setImageDrawable(null);
        stichEigeneKarteI.setImageDrawable(null);

        stichEigeneKarteG.setImageDrawable(null);
        stichGegnerKarteG.setImageDrawable(null);


        stichDeckI.setVisibility(View.INVISIBLE);
        stichDeckG.setVisibility(View.INVISIBLE);
        args.putBoolean("win", win);
        DialogFragment gameEndDialogFragment = new GameEnd();
        gameEndDialogFragment.setArguments(args);
        gameEndDialogFragment.show(getFragmentManager(), "GameEnd");
    }

    public void zudrehen(View view) {
        spiel.Zudrehen();
        buttonZudrehen.setEnabled(false);
        buttonZudrehen.setAlpha(0.4f);
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
        button40er.setAlpha(0.4f);
        button20er.setEnabled(false);
        button20er.setAlpha(0.4f);
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
        button20er.setAlpha(0.4f);
        button40er.setEnabled(false);
        button40er.setAlpha(0.4f);
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
            imageView_eigeneKarte.setImageDrawable(null);
            imageView_karteGegner.setImageDrawable(null);
            if (spiel.istSpielzuEnde(bummerl)) {
                spielEnde();
            } else {
                handAktualisieren();
                if(pruefegestochenG)
                {
                    if(spiel.getS2().Gestochen.size()==2)
                    {
                        stichEigeneKarteG.setImageResource(spiel.getS2().Gestochen.get(0).getImageResourceId());
                        stichGegnerKarteG.setImageResource(spiel.getS2().Gestochen.get(1).getImageResourceId());
                        /*buttonGKarte1G.setText(spiel.getS2().Gestochen.get(0).getFarbe() + spiel.getS2().Gestochen.get(0).getWertigkeit());
                        buttonGKarte2G.setText(spiel.getS2().Gestochen.get(1).getFarbe() + spiel.getS2().Gestochen.get(1).getWertigkeit());
*/
                    }
                    else if(spiel.getS2().Gestochen.size()>2)
                    {
                        stichDeckG.setVisibility(View.VISIBLE);

                        pruefegestochenG = false;
                    }
                }
                if(pruefegestochenI)
                {
                    if(spiel.getS1().Gestochen.size()==2)
                    {
                        stichEigeneKarteI.setImageResource(spiel.getS1().Gestochen.get(0).getImageResourceId());
                        stichGegnerKarteI.setImageResource(spiel.getS1().Gestochen.get(1).getImageResourceId());
                        /*buttonGKarte1I.setText(spiel.getS1().Gestochen.get(0).getFarbe() + spiel.getS1().Gestochen.get(0).getWertigkeit());
                        buttonGKarte2I.setText(spiel.getS1().Gestochen.get(1).getFarbe() + spiel.getS1().Gestochen.get(1).getWertigkeit());*/
                    }
                    else if(spiel.getS1().Gestochen.size()>2)
                    {

                        // buttonGestochenI.setVisibility(View.VISIBLE);
                        stichDeckI.setVisibility(View.VISIBLE);
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

