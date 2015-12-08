package automa.great.ufc.br.automagreat.view.activity;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import automa.great.ufc.br.automagreat.R;
import automa.great.ufc.br.automagreat.model.context.ContextKeys;
import automa.great.ufc.br.automagreat.model.context.ContextManager;
import automa.great.ufc.br.automagreat.model.control.MotionSensor;
import automa.great.ufc.br.automagreat.model.listeners.ContextListener;
import automa.great.ufc.br.automagreat.util.Config;
import automa.great.ufc.br.automagreat.view.adapter.TabPagerAdapter;
import automa.great.ufc.br.automagreat.view.layout.SlidingTabLayout;
import br.ufc.great.syssu.base.Tuple;


public class TabsActivity extends ActionBarActivity implements ContextListener{
    private Toolbar toolbar;
    private ViewPager pager;
    private TabPagerAdapter adapter;
    private SlidingTabLayout tabs;
    private CharSequence Titles[] = {"Lamps","Airs Conditioning"};
    private int Numboftabs = 2;
    public static final String PREFS_SHOW_DIALOG = "ShowDialogSelectMode";
    public static final String PREFS_MODE = "Mode";

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



    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        final SharedPreferences settings = getSharedPreferences(PREFS_SHOW_DIALOG, 0);
        boolean dialogShow = settings.getBoolean("dialogShowNever", false);
        if(!dialogShow) {
            callDialog();
        }
    }

    private void callDialog() {
        // Selecionando entre manual ou automatico
        final SharedPreferences settings = getSharedPreferences(PREFS_SHOW_DIALOG, 0);
        final SharedPreferences settings2 = getSharedPreferences(PREFS_MODE, 0);
        boolean dialogShown = settings.getBoolean("dialogShow", false);

        if (!dialogShown) {
            View welcomeDialog = View.inflate(TabsActivity.this, R.layout.dialog_select_mode, null);
            Button btManual = (Button) welcomeDialog.findViewById(R.id.bt_manual);
            Button btAuto = (Button) welcomeDialog.findViewById(R.id.bt_auto);
            final CheckBox cb_not_show_again = (CheckBox) welcomeDialog.findViewById(R.id.cb_not_show_again);

            AlertDialog.Builder builder = new AlertDialog.Builder(TabsActivity.this);
            builder.setTitle(null);
            builder.setView(welcomeDialog);
            builder.setCancelable(false);
            final AlertDialog dialog = builder.create();
            dialog.show();

            btManual.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Ligar modo manual
                    if (!(settings2.getBoolean("ModoManual", true))) {
                        SharedPreferences.Editor editor = settings2.edit();
                        editor.putBoolean("ModoManual", true);
                        editor.commit();
                    }
                    if (cb_not_show_again.isChecked()) {
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putBoolean("dialogShowNever", true);
                        editor.commit();
                    }
                    Toast.makeText(TabsActivity.this, getString(R.string.manual_selected), Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            });
            btAuto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Ligar modo automatico
                    if (!(settings2.getBoolean("ModoManual", true))) {
                        SharedPreferences.Editor editor = settings2.edit();
                        editor.putBoolean("ModoManual", false);
                        editor.commit();
                    }
                    if(cb_not_show_again.isChecked()) {
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putBoolean("dialogShowNever", true);
                        editor.commit();
                    }
                    Toast.makeText(TabsActivity.this, getString(R.string.auto_selected), Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            });

            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("dialogShow", true);
            editor.commit();
        }
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
        //Log.d(Config.TAG, "Sensor de presença ativado!" + data);

        SharedPreferences shared = getSharedPreferences(TabsActivity.PREFS_MODE, 0);
        boolean modeManual = (shared.getBoolean("ModoManual", false));


        MotionSensor.setSensorData(data);
        MotionSensor.verifyMotion(modeManual);
    }

    @Override
    public String getContextKey() {
        return ContextKeys.MOTION_SENSOR;
    }
}
