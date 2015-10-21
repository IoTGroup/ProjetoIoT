package automa.great.ufc.br.automagreat.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.PopupMenu.OnMenuItemClickListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import automa.great.ufc.br.automagreat.R;
import automa.great.ufc.br.automagreat.model.Resource;
import automa.great.ufc.br.automagreat.view.activity.DialogSliderActivity;

/**
 * Created by Thae on 05/10/2015.
 */
public class OnOffListAdapter extends BaseAdapter implements OnMenuItemClickListener {
    private Context context;
    private List<Resource> resources;
    private List<Switch> listSwitches;

    public OnOffListAdapter (Context context, ArrayList<Resource> resources) {
        this.context = context;
        this.resources = resources;
        listSwitches = new ArrayList<Switch>();
    }

    @Override
    public int getCount() {

        return resources.size();
    }

    @Override
    public Object getItem(int position) {

        return resources.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.item_onoff, null);
        }

        Resource r = this.resources.get(position);
        ImageView icon = (ImageView) convertView.findViewById(R.id.iv_icon);
        TextView name = (TextView) convertView.findViewById(R.id.tv_object_name);
        TextView description = (TextView) convertView.findViewById(R.id.tv_description);

        Switch onoff = (Switch) convertView.findViewById(R.id.switch_onoff_list);
        listSwitches.add(onoff);

        onoff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Guardar os status dos objetos
                if (position == 0) {
                    if (isChecked) {
                        Iterator<Switch> iterator = listSwitches.iterator();
                        while (iterator.hasNext()) {
                            iterator.next().setChecked(true);
                            //ligar cada lampada
                        }
                        Log.i("Resource", "testando... " + position);
                    } else {
                        Iterator<Switch> iterator = listSwitches.iterator();
                        while (iterator.hasNext()) {
                            iterator.next().setChecked(false);
                            //desligar as lampadas
                        }
                            Log.i("Resource", "testando..." + position);
                        }
                } else {

                    if(isChecked){

                        if(position == 1){
                            //ligar lampada 1
                        }else if(position == 2){
                            //ligar lampada 2
                        }else{
                            //ligar lampada 3
                        }

                    }else{

                        if(position == 1){
                            //desligar lampada 1
                        }else if(position == 2){
                            //desligar lampada 2
                        }else{
                            //desligar lampada 3
                        }
                    }
                }
            }
        });

        View overflow = convertView.findViewById(R.id.menu_overflow);
        overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.setOnMenuItemClickListener(OnOffListAdapter.this);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.show();
            }
        });

        name.setText(r.getName());

        if (r.getType() == Resource.AIR) {
            icon.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_air));
        }else if (r.getType() == Resource.LAMP) {
            icon.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_lamp));
        }

        onoff.setChecked(r.getStatus());

        try {
            description.setText(r.getDescription());
        } catch (NullPointerException e){
            Log.i("OnOffAdapter", "no description");
        }

        return convertView;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.overflow_intensity:
                context.startActivity(new Intent(context, DialogSliderActivity.class));
                return true;

            case R.id.overflow_color:
                Log.i("Resource", "TESTANDO COR...");
                return true;
        }
        return true;
    }
}
