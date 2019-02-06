package com.lowesta.puochat;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends AppCompatActivity {

    private Toolbar actionbar;
    private ViewPager vpMain;
    private TabLayout tabsMain;
    private TabsAdapter tabsAdapter;
    private FirebaseAuth auth;

    private FirebaseUser currentUser;

    public void init(){
        actionbar = (Toolbar)findViewById(R.id.actionBar);
        setSupportActionBar(actionbar);
        getSupportActionBar().setTitle(R.string.app_name);

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

        vpMain = (ViewPager)findViewById(R.id.vpMain);
        tabsAdapter = new TabsAdapter(getSupportFragmentManager());
        vpMain.setAdapter(tabsAdapter);


        tabsMain = (TabLayout) findViewById(R.id.tapsMain);
        tabsMain.setupWithViewPager(vpMain);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
    }

    @Override
    protected void onStart() {
        if (currentUser == null){
            Intent welcomeIntent = new Intent (Main2Activity.this,MainActivity.class);
            startActivity(welcomeIntent);
            finish();
        }
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);

         if (item.getItemId()==R.id.mainlogout){

             auth.signOut();
             Intent LoginIntent = new Intent(Main2Activity.this,LoginActivity.class);
             startActivity(LoginIntent);
             finish();
         }



         return true;
    }
}
