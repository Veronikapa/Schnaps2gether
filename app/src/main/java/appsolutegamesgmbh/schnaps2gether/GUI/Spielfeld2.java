package appsolutegamesgmbh.schnaps2gether.GUI;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spieler;
import appsolutegamesgmbh.schnaps2gether.R;


public class Spielfeld2 extends ActionBarActivity implements View.OnClickListener, Observer {

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
    private Spieler s1;
    private Spieler s2;
    private Karte k1;
    private Karte k2;
    private Karte k3;
    private Karte k4;
    private Karte k5;
    private TextView punkte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spielfeld2);

        spiel = new Spiel2();
        s1 = spiel.getS1();
        s2 = spiel.getS2();

        button1 = (Button) findViewById(R.id.main_button1);
        button1.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.main_button2);
        button2.setOnClickListener(this);
        button3 = (Button) findViewById(R.id.main_button3);
        button3.setOnClickListener(this);
        button4 = (Button) findViewById(R.id.main_button4);
        button4.setOnClickListener(this);
        button5 = (Button) findViewById(R.id.main_button5);
        button5.setOnClickListener(this);
        buttonI = (Button) findViewById(R.id.main_buttonI);
        buttonE = (Button) findViewById(R.id.main_buttonE);

        punkte = (TextView) findViewById(R.id.pointsText);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spielfeld, menu);
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

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.main_button1:
                String b1 = button1.getText().toString();
                button1.setText("gespielt");
                buttonI.setText(b1);
                break;
            case R.id.main_button2:
                String b2 = button2.getText().toString();
                button2.setText("gespielt");
                buttonI.setText(b2);
                break;
            case R.id.main_button3:
                String b3 = button3.getText().toString();
                button3.setText("gespielt");
                buttonI.setText(b3);
                break;
            case R.id.main_button4:
                String b4 = button4.getText().toString();
                button4.setText("gespielt");
                buttonI.setText(b4);
                break;
            case R.id.main_button5:
                String b5 = button5.getText().toString();
                button5.setText("gespielt");
                buttonI.setText(b5);
                break;
        }
        button1.setActivated(false);
        button2.setActivated(false);
        button3.setActivated(false);
        button4.setActivated(false);
        button5.setActivated(false);
        while(!s1.isIstdran()) {}
        button1.setActivated(true);
        button2.setActivated(true);
        button3.setActivated(true);
        button4.setActivated(true);
        button5.setActivated(true);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o.equals("Zug")) {
            boolean temp = !button1.isActivated();
            button1.setActivated(temp);
            button2.setActivated(temp);
            button3.setActivated(temp);
            button4.setActivated(temp);
            button5.setActivated(temp);
        } else if (o.equals("Ziehen")) {
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
        } else if (o.equals("Stichauswertung")) {
            int p1 = s1.getPunkte();
            int p2 = s2.getPunkte();
            punkte.setText(p1+":"+p2);
        }
    }
}
