package appsolutegamesgmbh.schnaps2gether.GUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import appsolutegamesgmbh.schnaps2gether.R;


public class Spielregeln extends ActionBarActivity {

    WebView spielregelnHTML;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spielregeln);
        spielregelnHTML = (WebView)findViewById(R.id.wv_spielregeln);
        spielregelnHTML.loadUrl("file:///android_asset/spielregeln_schnapsen.htm");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_spielregeln, menu);
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

    public void abbrechenSpielregeln(View v){
        Intent i = new Intent(this, Startmenue.class);
        startActivity(i);
        finish();
    }
}
