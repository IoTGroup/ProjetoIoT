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
public class AirFragment extends Fragment {
    private Context context;
    private ListView lv_air;
    private Switch switch_air;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_air,container,false);

        Resource air0 = new Resource("All A/C", "", Resource.AIR);
        Resource air1 = new Resource("Air1", "close to the door", Resource.AIR);
        Resource air2 = new Resource("Air2", "far from the door", Resource.AIR);

        final ArrayList<Resource> resources = new ArrayList<>();
        resources.add(air0);
        resources.add(air1);
        resources.add(air2);

        this.context = getActivity();
        
        lv_air = (ListView) v.findViewById(R.id.lv_air);

        lv_air.setAdapter(new OnOffListAdapter(context, resources));
        lv_air.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                resources.get(position).setStatus(!switch_air.isChecked());
                switch_air.setChecked(!switch_air.isChecked());
                Log.i("Resource", resources.get(position).getType() + " is checked = " + resources.get(position).getStatus());
            }
        });
        return v;
    }
}
