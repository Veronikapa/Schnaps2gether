package appsolutegamesgmbh.schnaps2gether.GUI;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spieler;
import appsolutegamesgmbh.schnaps2gether.R;


public class Spielfeld2 extends Activity implements View.OnClickListener, GameEnd.GameEndDialogListener, PopupMenu.OnMenuItemClickListener {

    private Spiel2 spiel;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button buttonI;
    private Button buttonE;
    private Button buttonD;
    private Button buttonT;
    private Button buttonZ;
    private Button button20er;
    private Button button40er;
    private MenuItem herz;
    private MenuItem karo;
    private MenuItem pik;
    private MenuItem kreuz;
    private Spieler s1;
    private Spieler s2;
    private Karte k1;
    private Karte k2;
    private Karte k3;
    private Karte k4;
    private Karte k5;
    private Karte e1;
    private Karte t;
    private TextView punkteE;
    private TextView punkteI;
    private TextView i;
    private TextView enemy;
    private Bummerl2 bummerl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spielfeld2);

        bummerl = new Bummerl2();

        button1 = (Button) findViewById(R.id.main_button1);
        button1.setOnClickListener(this);
        button1.setClickable(true);
        button2 = (Button) findViewById(R.id.main_button2);
        button2.setOnClickListener(this);
        button2.setClickable(true);
        button3 = (Button) findViewById(R.id.main_button3);
        button3.setOnClickListener(this);
        button3.setClickable(true);
        button4 = (Button) findViewById(R.id.main_button4);
        button4.setOnClickListener(this);
        button4.setClickable(true);
        button5 = (Button) findViewById(R.id.main_button5);
        button5.setOnClickListener(this);
        button5.setClickable(true);
        buttonZ = (Button) findViewById(R.id.main_buttonZ);
        buttonZ.setOnClickListener(this);
        buttonZ.setClickable(true);
        buttonI = (Button) findViewById(R.id.main_buttonI);
        buttonE = (Button) findViewById(R.id.main_buttonE);
        buttonT = (Button) findViewById(R.id.main_buttonT);
        buttonD = (Button) findViewById(R.id.main_buttonD);
        button20er = (Button) findViewById(R.id.main_button20er);
        button20er.setOnClickListener(this);
        button40er = (Button) findViewById(R.id.main_button40er);
        button40er.setOnClickListener(this);

        nichtKlickbar();

        punkteE = (TextView) findViewById(R.id.pointsText);
        punkteI = (TextView) findViewById(R.id.pointsText2);
        i = (TextView) findViewById(R.id.I);
        enemy = (TextView) findViewById(R.id.Enemy);

        spielStart();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.main_button1:
                zugAusführen(0);
                break;
            case R.id.main_button2:
                zugAusführen(1);
                break;
            case R.id.main_button3:
                zugAusführen(2);
                break;
            case R.id.main_button4:
                zugAusführen(3);
                break;
            case R.id.main_button5:
                zugAusführen(4);
                break;
            case R.id.main_buttonZ:
                spiel.Zudrehen();
                buttonZ.setEnabled(false);
                buttonZ.setText("Zugedreht");
                break;
            case R.id.main_button20er:
                spiel.Zudrehen();
                PopupMenu popup = new PopupMenu(Spielfeld2.this, button20er);
                popup.inflate(R.menu.popup_menu_20er);
                herz = (MenuItem) popup.getMenu().getItem(0);
                karo = (MenuItem) popup.getMenu().getItem(1);
                pik = (MenuItem) popup.getMenu().getItem(2);
                kreuz = (MenuItem) popup.getMenu().getItem(3);
                ArrayList<String> a = spiel.hat20er(s1);
                herz.setVisible(false);
                karo.setVisible(false);
                pik.setVisible(false);
                kreuz.setVisible(false);
                for(int i=0; i<a.size(); i++) {
                    switch(a.get(i)) {
                        case "Herz":
                            herz.setVisible(true);
                            break;
                        case "Karo":
                            karo.setVisible(true);
                            break;
                        case "Pik":
                            pik.setVisible(true);
                            break;
                        case "Kreuz":
                            kreuz.setVisible(true);
                            break;
                        default:;
                    }
                }
                popup.setOnMenuItemClickListener(this);
                popup.show();
                spiel.istSpielzuEnde(bummerl);
                break;
            case R.id.main_button40er:
                spiel.Ansagen20er(spiel.getTrumpf(), s1);
                if (spiel.istSpielzuEnde(bummerl)) spielEnde();
                punkteAktualisieren();
                button40er.setEnabled(false);
                kartenKlickbar();
                break;
            default:;
        }
    }

    private void zugAusführen(int i) {
        final Karte k = s1.Hand.get(i);
        kartenNichtKlickbar();
        spiel.Auspielen(k);
        gespielteKarteEntfernen(i);
        buttonI.setText(k.getFarbe() + k.getWertigkeit());
        if (e1 == null) {
            zugWechsel(k1);
        }
        // Execute some code after 2 seconds have passed
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                spiel.ZugAuswerten(k, e1);
                punkteAktualisieren();
                e1 = null;
                if (spiel.istSpielzuEnde(bummerl)) {
                    spielEnde();
                } else {
                    handAktualisieren();
                    zugWechsel(null);
                    kartenKlickbar();
                }
            }
        }, 2000);
    }

    private void zugWechsel(Karte karteS1) {
        boolean temp = s1.isIstdran();
        if (!temp) {
            e1 = spiel.AuspielenComputer(karteS1);

            System.out.print(e1.getFarbe() + e1.getWertigkeit());
            buttonE.setText(e1.getFarbe() + e1.getWertigkeit());
            if (spiel.isZugedreht()) {
                buttonZ.setEnabled(false);
                buttonZ.setText("Zugedreht");
            }
            zugWechsel(null);

        }
        else {
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

    private void handAktualisieren() {
        buttonD.setText(Integer.toString(spiel.AnzahlKartenStapel()));
        switch (s1.Hand.size()) {
            case 5: k5 = s1.Hand.get(4);
                button5.setText(k5.getFarbe()+k5.getWertigkeit());
                button5.setVisibility(View.VISIBLE);
            case 4: k4 = s1.Hand.get(3);
                button4.setText(k4.getFarbe()+k4.getWertigkeit());
                button4.setVisibility(View.VISIBLE);
            case 3: k3 = s1.Hand.get(2);
                button3.setText(k3.getFarbe()+k3.getWertigkeit());
                button3.setVisibility(View.VISIBLE);
            case 2: k2 = s1.Hand.get(1);
                button2.setText(k2.getFarbe()+k2.getWertigkeit());
                button2.setVisibility(View.VISIBLE);
            case 1: k1 = s1.Hand.get(0);
                button1.setText(k1.getFarbe()+k1.getWertigkeit());
                button1.setVisibility(View.VISIBLE);
            default: ;
        }
        switch (s1.Hand.size()) {
            case 1:
                button2.setVisibility(View.INVISIBLE);
            case 2:
                button3.setVisibility(View.INVISIBLE);
            case 3:
                button4.setVisibility(View.INVISIBLE);
            case 4:
                button5.setVisibility(View.INVISIBLE);
            default: ;
        }
    }

    private void punkteAktualisieren() {
        buttonI.setText("");
        buttonE.setText("");
        int p1 = s1.getPunkte();
        int p2 = s2.getPunkte();
        punkteE.setText(Integer.toString(p2));
        punkteI.setText(Integer.toString(p1));
    }

    private void gespielteKarteEntfernen(int i) {
        switch (i) {
            case 0: button1.setVisibility(View.INVISIBLE); break;
            case 1: button2.setVisibility(View.INVISIBLE); break;
            case 2: button3.setVisibility(View.INVISIBLE); break;
            case 3: button4.setVisibility(View.INVISIBLE); break;
            case 4: button5.setVisibility(View.INVISIBLE); break;
            default: break;
        }
    }

    private boolean hat20er() {
        if ((spiel.hat20er(s1).size()>0 && !hat40er()) || spiel.hat20er(s1).size()>1)
            return true;
        return false;
    }

    private boolean hat40er() {
        if(spiel.hat20er(s1).contains(spiel.getTrumpf()))
            return true;
        return false;
    }

    private void spielStart() {
        spiel = new Spiel2();
        s1 = spiel.getS1();
        s2 = spiel.getS2();

        buttonD.setText("20");
        t = spiel.getAufgedeckterTrumpf();
        buttonT.setText(t.getFarbe()+t.getWertigkeit());

        kartenKlickbar();
        buttonZ.setEnabled(true);
        buttonZ.setText(R.string.buttonZ);
        buttonI.setText("");
        buttonE.setText("");
        punkteI.setText("0");
        punkteE.setText("0");
        e1 = null;
        handAktualisieren();
        zugWechsel(null);
    }

    private void kartenNichtKlickbar() {
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        button5.setEnabled(false);
    }

    private void kartenKlickbar() {
        if (s1.Hand.size()>0 && spiel.DarfKarteAuswaehlen(e1, s1.Hand.get(0))) {
            button1.setEnabled(true);
        } else {
            button1.setEnabled(false);
        }
        if (s1.Hand.size()>1 && spiel.DarfKarteAuswaehlen(e1, s1.Hand.get(1))) {
            button2.setEnabled(true);
        } else {
            button2.setEnabled(false);
        }
        if (s1.Hand.size()>2 && spiel.DarfKarteAuswaehlen(e1, s1.Hand.get(2))) {
            button3.setEnabled(true);
        } else {
            button3.setEnabled(false);
        }
        if (s1.Hand.size()>3 && spiel.DarfKarteAuswaehlen(e1, s1.Hand.get(3))) {
            button4.setEnabled(true);
        } else {
            button4.setEnabled(false);
        }
        if (s1.Hand.size()>4 && spiel.DarfKarteAuswaehlen(e1, s1.Hand.get(4))) {
            button5.setEnabled(true);
        } else {
            button5.setEnabled(false);
        }
    }

    private void nichtKlickbar() {
        buttonD.setEnabled(false);
        buttonT.setEnabled(false);
        buttonI.setEnabled(false);
        buttonE.setEnabled(false);
    }

    private void spielEnde() {
        boolean win = true;
        if (s1.getPunkte()<66) {
            win = false;
        }
        Bundle args = new Bundle();
        args.putBoolean("win", win);
        DialogFragment newFragment = new GameEnd();
        newFragment.setArguments(args);
        newFragment.show(getFragmentManager(), "GameEnd");
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
        button20er.setEnabled(false);
        switch (menuItem.getItemId()) {
            case R.id.herz_20er:
                spiel.Ansagen20er("Herz", s1);
                if (spiel.istSpielzuEnde(bummerl)) spielEnde();
                punkteAktualisieren();
                kartenKlickbar();
                return true;
            case R.id.karo_20er:
                spiel.Ansagen20er("Karo", s1);
                if (spiel.istSpielzuEnde(bummerl)) spielEnde();
                punkteAktualisieren();
                kartenKlickbar();
                return true;
            case R.id.pik_20er:
                spiel.Ansagen20er("Pik", s1);
                if (spiel.istSpielzuEnde(bummerl)) spielEnde();
                punkteAktualisieren();
                kartenKlickbar();
                return true;
            case R.id.kreuz_20er:
                spiel.Ansagen20er("Kreuz", s1);
                if (spiel.istSpielzuEnde(bummerl)) spielEnde();
                punkteAktualisieren();
                kartenKlickbar();
                return true;
            default:
                return false;
        }
    }
}
