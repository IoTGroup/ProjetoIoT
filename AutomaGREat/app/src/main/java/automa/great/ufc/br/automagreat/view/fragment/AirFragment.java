package automa.great.ufc.br.automagreat.view.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import automa.great.ufc.br.automagreat.model.control.Airs;
import automa.great.ufc.br.automagreat.model.control.Resource;
import automa.great.ufc.br.automagreat.util.Config;
import automa.great.ufc.br.automagreat.model.context.ContextKeys;
import automa.great.ufc.br.automagreat.model.context.ContextManager;
import automa.great.ufc.br.automagreat.model.listeners.ContextListener;
import automa.great.ufc.br.automagreat.R;
import automa.great.ufc.br.automagreat.view.adapter.FieldListAdapter;

/**
 * Created by Thae on 05/10/2015.
 */
public class AirFragment extends Fragment implements ContextListener {
    private Context context;
    private ListView lv_air;
    private TextView temperature, temperature_unavailable;
    private Button bt_up, bt_down;
    private LinearLayout ll_temperature;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_air, container, false);

        // registra este ContextListener no ContextManager do Loccam
        ContextManager.getInstance().registerListener(this);

        // recuperando as views da tela
        temperature = (TextView) v.findViewById(R.id.temperatureTV);
        temperature_unavailable = (TextView) v.findViewById(R.id.tv_unavailable);
        bt_up = (Button) v.findViewById(R.id.upTempButton);
        bt_down = (Button) v.findViewById(R.id.downTempButton);
        ll_temperature = (LinearLayout) v.findViewById(R.id.temperatureLL);

        setTypeface(v);

        // setando a list view do Fragment
        this.context = getActivity();
        Resource air1 = new Resource("Controling Air1", "", Resource.AIR);
        final ArrayList<Resource> resources = new ArrayList<>();
        resources.add(air1);
        lv_air = (ListView) v.findViewById(R.id.lv_air);
        lv_air.setAdapter(new FieldListAdapter(context, resources, Config.type_airs, this));
        lv_air.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(Config.TAG, "onClick ListView");
            }
        });

        // setando os cliques da tela
        bt_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Airs.plus();
                Log.d(Config.TAG, "TEmperatura: " + Airs.temperature);
                temperature.setText("" + Airs.temperature);
            }
        });

        bt_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Airs.minus();
                Log.d(Config.TAG, "TEmperatura: " + Airs.temperature);
                temperature.setText("" + Airs.temperature);
            }
        });

        setOffFragment();

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ContextManager.getInstance().unregisterListener(this);
    }

    @Override
    public void onContextReady(String data) {
        Log.d(Config.TAG, "Ar-condicionado pronto");

    }

    @Override
    public String getContextKey() {
        return ContextKeys.CONTROL_AC;
    }

    private void setTypeface(View v) {
        Typeface custom_font = Typeface.createFromAsset(getContext().getAssets(), "fonts/LCDM2B__.TTF");
        temperature.setTypeface(custom_font);
    }

    public void setOnFragment() {
        ll_temperature.setVisibility(View.VISIBLE);
        temperature_unavailable.setVisibility(View.INVISIBLE);
        bt_up.setBackgroundResource(R.drawable.button_up);
        bt_down.setBackgroundResource(R.drawable.button_down);
    }

    public void setOffFragment() {
        ll_temperature.setVisibility(View.INVISIBLE);
        temperature_unavailable.setVisibility(View.VISIBLE);
        bt_up.setBackgroundResource(R.mipmap.btn_plus_unavailable);
        bt_down.setBackgroundResource(R.mipmap.btn_minus_unavailable);
    }
}
