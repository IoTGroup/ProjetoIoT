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
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import automa.great.ufc.br.automagreat.util.Config;
import automa.great.ufc.br.automagreat.model.context.ContextKeys;
import automa.great.ufc.br.automagreat.model.context.ContextManager;
import automa.great.ufc.br.automagreat.model.listeners.ContextListener;
import automa.great.ufc.br.automagreat.view.adapter.FieldListAdapter;
import automa.great.ufc.br.automagreat.R;
import automa.great.ufc.br.automagreat.model.control.Resource;

/**
 * Created by Thae on 05/10/2015.
 */
public class AirFragment extends Fragment implements ContextListener {
    private Context context;
    private ListView lv_air;
    private Switch switch_air;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_air, container, false);

        //Resource air0 = new Resource("All A/C", "", Resource.AIR);
        //Resource air1 = new Resource("Air1", "close to the door", Resource.AIR);
        //Resource air2 = new Resource("Air2", "far from the door", Resource.AIR);


        // this.context = getActivity();

        //lv_air = (ListView) v.findViewById(R.id.lv_air);

        //lv_air.setAdapter(new FieldListAdapter(context, resources,Config.type_airs));

//        lv_air.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //
//            }
//        });

        setTypeface(v);

        // registra este ContextListener no ContextManager do Loccam
        ContextManager.getInstance().registerListener(this);

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
        TextView tx = (TextView) v.findViewById(R.id.temperatureTV);
        Typeface custom_font = Typeface.createFromAsset(getContext().getAssets(), "fonts/LCDM2B__.TTF");
        tx.setTypeface(custom_font);
    }
}
