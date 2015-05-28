package appsolutegamesgmbh.schnaps2gether.GUI;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import appsolutegamesgmbh.schnaps2gether.R;


public class Startmenue extends Activity implements View.OnClickListener {

    private Button spielen;
    private Button statistik;
    private Button spielregeln;
    private Button beenden;
    private Button name;
    private Button schnellesSpiel;
    public static String SpielerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startmenue);

        spielen = (Button) findViewById(R.id.cmd_spielen);
        spielen.setOnClickListener(this);

        statistik = (Button) findViewById(R.id.cmd_statistik);
        statistik.setOnClickListener(this);

        spielregeln = (Button) findViewById(R.id.cmd_spielregeln);
        spielregeln.setOnClickListener(this);

        beenden = (Button) findViewById(R.id.cmd_Beenden);
        beenden.setOnClickListener(this);

        name = (Button) findViewById(R.id.cmd_name);
        name.setOnClickListener(this);

        schnellesSpiel = (Button) findViewById(R.id.cmd_schnellesSpiel);
        schnellesSpiel.setOnClickListener(this);

        SharedPreferences settings = this.getPreferences(this.MODE_PRIVATE);
        SpielerName = settings.getString("nickname", "Nickname");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_startmenue, menu);
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
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.cmd_name:
                DialogFragment nameFragment = new ChangeNickname();
                nameFragment.show(getFragmentManager(), "ChangeNickname");
                break;
            case R.id.cmd_Beenden:
                finish();
                break;
            case R.id.cmd_spielen:
                startActivity(new Intent(Startmenue.this, Lobby.class));
                finish();
                break;
            case R.id.cmd_schnellesSpiel:
                startActivity(new Intent(Startmenue.this,Spielfeld2.class));
                finish();
                break;
            default:;
        }
    }


}
