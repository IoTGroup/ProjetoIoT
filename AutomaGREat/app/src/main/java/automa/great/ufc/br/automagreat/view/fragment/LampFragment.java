package automa.great.ufc.br.automagreat.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import automa.great.ufc.br.automagreat.util.Config;
import automa.great.ufc.br.automagreat.model.context.ContextKeys;
import automa.great.ufc.br.automagreat.model.context.ContextManager;
import automa.great.ufc.br.automagreat.model.listeners.ContextListener;
import automa.great.ufc.br.automagreat.view.activity.DialogIntensityActivity;
import automa.great.ufc.br.automagreat.view.adapter.FieldListAdapter;
import automa.great.ufc.br.automagreat.R;
import automa.great.ufc.br.automagreat.model.control.Resource;

/**
 * Created by Thae on 05/10/2015.
 */
public class LampFragment extends Fragment implements ContextListener {
    private Context context;
    private ListView lv_lamp;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.fragment_lamp,container,false);

        Resource lamp0 = new Resource("All lamps", "", Resource.LAMP);
        Resource lamp1 = new Resource("Lamp 1", "Datashow", Resource.LAMP);
        Resource lamp2 = new Resource("Lamp 2", "Door", Resource.LAMP);
        Resource lamp3 = new Resource("Lamp 3", "Blackboard", Resource.LAMP);

        final ArrayList<Resource> resources = new ArrayList<>();
        resources.add(lamp0);
        resources.add(lamp1);
        resources.add(lamp2);
        resources.add(lamp3);

        this.context = getActivity();
        Log.i("Resource", context.toString());

        lv_lamp = (ListView) v.findViewById(R.id.lv_lamp);

        FieldListAdapter adapter = new FieldListAdapter(context, resources, Config.type_lights);

        lv_lamp.setAdapter(adapter);

        lv_lamp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(getActivity(), DialogIntensityActivity.class));
            }
        });

        // registra este ContextListener no ContextManager do Loccam
        ContextManager.getInstance().registerListener(this);

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ContextManager.getInstance().unregisterListener(this);
        //ContextManager.getInstance().disconnect();
    }

    @Override
    public void onContextReady(String data) {
        Log.d(Config.TAG, "Lampadas prontas");

    }

    @Override
    public String getContextKey() {
        return ContextKeys.HUE_LIGHT;
    }

}