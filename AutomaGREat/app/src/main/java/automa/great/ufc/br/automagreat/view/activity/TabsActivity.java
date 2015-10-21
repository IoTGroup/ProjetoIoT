package automa.great.ufc.br.automagreat.view.activity;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import automa.great.ufc.br.automagreat.R;
import automa.great.ufc.br.automagreat.context.ContextKeys;
import automa.great.ufc.br.automagreat.view.layout.SlidingTabLayout;
import automa.great.ufc.br.automagreat.view.adapter.ViewPagerAdapter;
import br.ufc.great.loccamlib.LoccamListener;
import br.ufc.great.loccamlib.LoccamManager;
import br.ufc.great.syssu.base.Tuple;
import br.ufc.great.syssu.base.interfaces.ISysSUService;

public class TabsActivity extends ActionBarActivity implements LoccamListener{
    private Toolbar toolbar;
    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private SlidingTabLayout tabs;
    private CharSequence Titles[]={"Lamps","Air Conditioning"};
    private int Numboftabs =2;

    private LoccamManager loccam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.DKGRAY);
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);

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

        loccam = new LoccamManager(this, "automagreat");
        loccam.connect(this);



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
    public void onServiceConnected(ISysSUService iSysSUService) {

        loccam.init(ContextKeys.HUE_LIGHT);
        Log.d("Automa","onServiceConnected aeeee");

    }

    @Override
    public void onServiceDisconnected() {

        loccam.finishAll();
        Log.d("Automa","onServiceDisconnected");

    }

    @Override
    public void onLoccamException(Exception e) {
        Log.d("Automa","Erro: "+e.toString());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        loccam.disconnect();

    }

    public void turnOn(View view) {
        Tuple tuple = (Tuple) new Tuple().addField("ControlKey", ContextKeys.HUE_LIGHT).addField("State", "on");
        loccam.setASync(tuple);
        Log.d("Automa","Ligando");
    }

    public void turnOn() {
        Tuple tuple = (Tuple) new Tuple().addField("ControlKey", ContextKeys.HUE_LIGHT).addField("State", "on");
        loccam.setASync(tuple);
    }

    public void turnOff() {
        Tuple tuple = (Tuple) new Tuple().addField("ControlKey", ContextKeys.HUE_LIGHT).addField("State", "off");
        loccam.setASync(tuple);
    }

    public void setColor(String color) {
        Tuple tuple = (Tuple) new Tuple().addField("ControlKey", ContextKeys.HUE_LIGHT).addField("Color", color);
        loccam.setASync(tuple);
    }

    public void setBright(String bright) {
        Tuple tuple = (Tuple) new Tuple().addField("ControlKey", ContextKeys.HUE_LIGHT).addField("Brightness", bright);
        loccam.setASync(tuple);
    }

    public void connect(String lampId) {
        Tuple tuple = (Tuple) new Tuple().addField("ControlKey", ContextKeys.HUE_LIGHT).addField("Light", lampId);
        loccam.setASync(tuple);
    }

}
