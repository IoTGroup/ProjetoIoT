package automa.great.ufc.br.automagreat.view.activity;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import automa.great.ufc.br.automagreat.R;
import automa.great.ufc.br.automagreat.model.context.ContextKeys;
import automa.great.ufc.br.automagreat.model.context.ContextManager;
import automa.great.ufc.br.automagreat.model.control.MotionSensor;
import automa.great.ufc.br.automagreat.model.listeners.ContextListener;
import automa.great.ufc.br.automagreat.util.Config;
import automa.great.ufc.br.automagreat.view.adapter.TabPagerAdapter;
import automa.great.ufc.br.automagreat.view.layout.SlidingTabLayout;


public class TabsActivity extends ActionBarActivity implements ContextListener{
    private Toolbar toolbar;
    private ViewPager pager;
    private TabPagerAdapter adapter;
    private SlidingTabLayout tabs;
    private CharSequence Titles[]={"Lamps","Airs Conditioning"};
    private int Numboftabs =2;

    public ContextListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        // registra este ContextListener no ContextManager do Loccam
        ContextManager.getInstance().registerListener(this);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.DKGRAY);
        adapter =  new TabPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.colorAccent);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);

        MotionSensor.getSensorData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings :
                Toast.makeText(TabsActivity.this, "Settings", Toast.LENGTH_LONG).show();
                Log.i("Resource", "TESTANDO TESTANDO TESTANDO...");
                return true;
            case R.id.action_refresh:
                Toast.makeText(TabsActivity.this, "Refresh", Toast.LENGTH_LONG).show();
                Log.i("Resource", "TESTANDO TESTANDO TESTANDO...");
                return true;
            default:
                Log.i("Resource", "TESTANDO TESTANDO TESTANDO...");
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ContextManager.getInstance().unregisterListener(this);
    }

    @Override
    public void onContextReady(String data) {
        Log.d(Config.TAG, "Sensor de presença ativado!");

    }

    @Override
    public String getContextKey() {
        return ContextKeys.MOTION_SENSOR;
    }
}
