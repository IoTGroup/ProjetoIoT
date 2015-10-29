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
import android.widget.Switch;

import java.util.ArrayList;

import automa.great.ufc.br.automagreat.context.ContextKeys;
import automa.great.ufc.br.automagreat.context.ContextManager;
import automa.great.ufc.br.automagreat.listeners.ContextListener;
import automa.great.ufc.br.automagreat.view.activity.DialogSliderActivity;
import automa.great.ufc.br.automagreat.view.adapter.FieldListAdapter;
import automa.great.ufc.br.automagreat.R;
import automa.great.ufc.br.automagreat.model.Resource;
import br.ufc.great.syssu.base.Tuple;

/**
 * Created by Thae on 05/10/2015.
 */
public class LampFragment extends Fragment implements ContextListener {
    private Context context;
    private ListView lv_lamp;
    private Switch switch_lamp;

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

        //FieldListAdapter adapter = new FieldListAdapter(context, resources);
        FieldListAdapter adapter = new FieldListAdapter(this, context, resources);

        lv_lamp.setAdapter(adapter);
        //lv_lamp.setItemsCanFocus(false);

        lv_lamp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(getActivity(), DialogSliderActivity.class));
            }
        });

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
        Log.d("Automa", "Lampadas prontas");

    }

    @Override
    public String getContextKey() {
        return ContextKeys.HUE_LIGHT;
    }

    private void turnOn() {
        Tuple tuple = (Tuple) new Tuple().addField("ControlKey", ContextKeys.HUE_LIGHT).addField("State", "on");
        ContextManager.getInstance().sendCommand(tuple);
    }

    private void turnOff() {
        Tuple tuple = (Tuple) new Tuple().addField("ControlKey", ContextKeys.HUE_LIGHT).addField("State", "off");
        ContextManager.getInstance().sendCommand(tuple);
    }

    private void setBright(String bright) {
        Tuple tuple = (Tuple) new Tuple().addField("ControlKey", ContextKeys.HUE_LIGHT).addField("Brightness", bright);
        ContextManager.getInstance().sendCommand(tuple);
    }

    private void connect(String lampId) {
        Tuple tuple = (Tuple) new Tuple().addField("ControlKey", ContextKeys.HUE_LIGHT).addField("Light", lampId);
        ContextManager.getInstance().sendCommand(tuple);
    }

    public void on(int id) {
        connect(String.valueOf(id));
        turnOn();
    }

    public void off(int id) {
        connect(String.valueOf(id));
        turnOff();
    }

    private void setColor(String color) {
        Tuple tuple = (Tuple) new Tuple().addField("ControlKey", ContextKeys.HUE_LIGHT).addField("Color", color);
        ContextManager.getInstance().sendCommand(tuple);
    }

    public void setIntensity(String id ,String vInts) {
        connect(id);
        setBright(vInts);
    }
}