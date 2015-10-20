package automa.great.ufc.br.automagreat.view.fragment;

import android.content.Context;
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

import automa.great.ufc.br.automagreat.view.adapter.OnOffListAdapter;
import automa.great.ufc.br.automagreat.R;
import automa.great.ufc.br.automagreat.model.Resource;

/**
 * Created by Thae on 05/10/2015.
 */
public class LampFragment extends Fragment {
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

        lv_lamp = (ListView) v.findViewById(R.id.lv_lamp);

        lv_lamp.setAdapter(new OnOffListAdapter(context, resources));

        lv_lamp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Resource", resources.get(position).getType() + " is checked = " + resources.get(position).getStatus());
            }
        });

        return v;
    }
}