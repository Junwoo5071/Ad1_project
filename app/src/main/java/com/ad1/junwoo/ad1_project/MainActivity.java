package com.ad1.junwoo.ad1_project;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView textView;
    Document doc = null;
    LinearLayout layout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        textView = (TextView)findViewById(R.id.textView1);

        GetXMLTask task = new GetXMLTask();
        task.execute("http://openapi.seoul.go.kr:8088/6d4f4b76736879753431786e6c6e4e/xml/ForecastWarningMinuteParticleOfDustService/1/1/");
    }

    private class GetXMLTask extends AsyncTask<String, Void, Document>{
        @Override
        protected void onPostExecute(Document doc) {
            String s = "";

            s += "날짜 : ";
            NodeList nodeList = doc.getElementsByTagName("APPLC_DT");
            Node node = nodeList.item(0);
            Element fstElmnt = (Element)node;
            nodeList = fstElmnt.getChildNodes();
            s += ((Node)nodeList.item(0)).getNodeValue();

            s += "\n오염도 : ";
            NodeList pollutant = doc.getElementsByTagName("POLLUTANT");
            Node node2 = pollutant.item(0);
            Element fstElmnt2 = (Element)node2;
            pollutant = fstElmnt2.getChildNodes();
            s += ((Node)pollutant.item(0)).getNodeValue();

            s += "\n상태 : ";
            NodeList condition = doc.getElementsByTagName("CAISTEP");
            Node node3 = condition.item(0);
            Element fstElmnt3 = (Element)node3;
            condition = fstElmnt3.getChildNodes();
            s += ((Node)condition.item(0)).getNodeValue();

            textView.setText(s);
            super.onPostExecute(doc);
        }

        @Override
        protected Document doInBackground(String... urls) {
            URL url;
            try{
                url = new URL(urls[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();

            } catch(Exception e){
                Toast.makeText(getBaseContext(),"Parsing Error",Toast.LENGTH_LONG).show();
            }
            return doc;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu_1) {
            // Handle the camera action
        } else if (id == R.id.nav_menu_2) {

        } else if (id == R.id.nav_menu_3) {

        } else if (id == R.id.nav_menu_4) {

        } else if (id == R.id.nav_menu_5) {

        } else if (id == R.id.nav_submenu_1) {

        } else if (id == R.id.nav_submenu_2) {

        } else if (id == R.id.nav_submenu_3) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }
    public void login_onClick(View v){
        Intent loginPage_intent = new Intent(this, LoginActivity.class);
        startActivity(loginPage_intent);
    }
}