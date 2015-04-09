package appsolutegamesgmbh.schnaps2gether.GUI;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spieler;
import appsolutegamesgmbh.schnaps2gether.R;


public class Spielfeld2 extends Activity implements View.OnClickListener, GameEnd.GameEndDialogListener {

    /* TODO: Hand von Spieler1 auslesen und anzeigen; Trumpf Karte anzeigen;
    * TODO: Klick auf Karte Spieler 1
    * * Ist Spieler1 dran (zu Spielbeginn immer) und spiel.DarfKarteAusspielen() = true -->  spiel.Ausspielen() aufrufen, spiel.AusspielenComputer(), spiel.ZugAuswerten aufrufen()
    * * Ist danach Spieler1 wieder dran. Tu nix.
    * * Ist Spieler2 dran. Triggere AusspielenComputer
    *
    * TODO: Klick auf Zudrehen --> spiel.Zudrehen aufrufen, tbd..
    * TODO: Punkte auslesen von Spieler 1 nach Zugauswerten und anzeigen
    * TODO: Überprüfen ob Spiel weitergeht oder neues Spiel begonnen wird; Auslesen wer Spiel gewonnen hat
    * */
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
    private Spieler s1;
    private Spieler s2;
    private Karte k1;
    private Karte k2;
    private Karte k3;
    private Karte k4;
    private Karte k5;
    private Karte e1;
    private Karte t;
    private TextView punkte;
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

        punkte = (TextView) findViewById(R.id.pointsText);
        i = (TextView) findViewById(R.id.I);
        enemy = (TextView) findViewById(R.id.Enemy);

        spielStart();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.main_button1:
                zugAusführen(0);
                button1.setText("");
                break;
            case R.id.main_button2:
                zugAusführen(1);
                button2.setText("");
                break;
            case R.id.main_button3:
                zugAusführen(2);
                button3.setText("");
                break;
            case R.id.main_button4:
                zugAusführen(3);
                button4.setText("");
                break;
            case R.id.main_button5:
                zugAusführen(4);
                button5.setText("");
                break;
            case R.id.main_buttonZ:
                spiel.Zudrehen();
                buttonZ.setActivated(false);
                break;
        }
    }

    private void zugAusführen(int i) {
        Karte k = s1.Hand.get(i);
        if (spiel.DarfKarteAuswaehlen(e1, k)) {
            spiel.Auspielen(k);
            gespielteKarteEntfernen(i);
            buttonI.setText(k.getFarbe()+k.getWertigkeit());
            if (e1 == null) {
                zugWechsel(k1);
            }
            spiel.ZugAuswerten(k1, e1);
            punkteAktualisieren();
            e1 = null;
            if (spiel.istSpielzuEnde(bummerl)) {
                DialogFragment newFragment = new GameEnd();
                newFragment.show(getFragmentManager(), "GameEnd");
            }
            karteGezogen();
        }
    }

    private void zugWechsel(Karte karteS1) {
        boolean temp = s1.isIstdran();
        button1.setClickable(temp);
        button2.setClickable(temp);
        button3.setClickable(temp);
        button4.setClickable(temp);
        button5.setClickable(temp);
        if (temp) {
            i.setTextColor(0xff0000);
            enemy.setTextColor(0x555555);
        } else {
            i.setTextColor(0x555555);
            enemy.setTextColor(0xff0000);
            button1.setClickable(false);
            button2.setClickable(false);
            button3.setClickable(false);
            button4.setClickable(false);
            button5.setClickable(false);
            e1 = spiel.AuspielenComputer(karteS1);
            buttonE.setText(e1.getFarbe()+e1.getWertigkeit());
            zugWechsel(null);
        }
    }

    private void karteGezogen() {
        buttonD.setText(Integer.toString(spiel.AnzahlKartenStapel()));
        k1 = s1.Hand.get(0);
        k2 = s1.Hand.get(1);
        k3 = s1.Hand.get(2);
        k4 = s1.Hand.get(3);
        k5 = s1.Hand.get(4);
        button1.setText(k1.getFarbe()+k1.getWertigkeit());
        button2.setText(k2.getFarbe()+k2.getWertigkeit());
        button3.setText(k3.getFarbe()+k3.getWertigkeit());
        button4.setText(k4.getFarbe()+k4.getWertigkeit());
        button5.setText(k5.getFarbe()+k5.getWertigkeit());
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);
    }

    private void punkteAktualisieren() {
        buttonI.setText("");
        buttonE.setText("");
        int p1 = s1.getPunkte();
        int p2 = s2.getPunkte();
        punkte.setText(p1+":"+p2);
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

    private void spielStart() {
        spiel = new Spiel2();
        s1 = spiel.getS1();
        s2 = spiel.getS2();

        buttonD.setText("20");
        t = spiel.getAufgedeckterTrumpf();
        buttonT.setText(t.getFarbe()+t.getWertigkeit());

        buttonZ.setActivated(true);
        i.setTextColor(0xff0000);
        enemy.setTextColor(0x555555);
        buttonI.setText("");
        buttonE.setText("");
        e1 = null;
        karteGezogen();
        zugWechsel(null);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        spielStart();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        finish();
    }
}
