package appsolutegamesgmbh.schnaps2gether.GUI;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel2;
import appsolutegamesgmbh.schnaps2gether.R;

public class NeuesSpiel extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neues_spiel);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_neues_spiel, menu);
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

    public void zweierSchnapsen(View v){
        Intent i = new Intent(this, Lobby.class);
        i.putExtra("Spieltyp",2);
        setResult(Activity.RESULT_OK,i);
        finish();
    }

    public void dreierSchnapsen(View v){
        Intent i = new Intent(this, Lobby.class);
        i.putExtra("Spieltyp",3);
        setResult(Activity.RESULT_OK,i);
        finish();
    }

    public void viererSchnapsen(View v){
        Intent i = new Intent(this, Lobby.class);
        i.putExtra("Spieltyp",4);
        setResult(Activity.RESULT_OK,i);
        finish();
    }


    public void abbrechen(View v){
        Intent i = new Intent(this, Lobby.class);
        setResult(Activity.RESULT_CANCELED,i);
        finish();
    }
}
