package automa.great.ufc.br.automagreat.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.PopupMenu.OnMenuItemClickListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import automa.great.ufc.br.automagreat.view.activity.DialogColorActivity;
import automa.great.ufc.br.automagreat.R;
import automa.great.ufc.br.automagreat.model.control.Airs;
import automa.great.ufc.br.automagreat.model.control.Lights;
import automa.great.ufc.br.automagreat.model.control.Resource;
import automa.great.ufc.br.automagreat.util.Config;
import automa.great.ufc.br.automagreat.view.activity.DialogIntensityActivity;

/**
 * Created by Thae on 05/10/2015.
 */
public class FieldListAdapter extends BaseAdapter {
    private Context activity;
    private List<Resource> resources;
    private List<Switch> listSwitches;
    private int position;
    private String type;


    public FieldListAdapter(Context context, ArrayList<Resource> resources, String type) {
        this.activity = context;
        this.resources = resources;
        this.type = type;
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

        this.position = position;


        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
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


                if (type.equals(Config.type_airs)) {
                    Airs.onOff();
                    Log.d(Config.TAG,"Air onOff");
                } else {
                    // Para o tipo Light

                    if (position == 0) {
                        if (isChecked) {
                            Iterator<Switch> iterator = listSwitches.iterator();
                            while (iterator.hasNext()) {
                                iterator.next().setChecked(true);
                            }

                            Lights.on(1);
                            Lights.on(2);
                            Lights.on(3);

                        } else {
                            Iterator<Switch> iterator = listSwitches.iterator();
                            while (iterator.hasNext()) {
                                iterator.next().setChecked(false);
                            }
                            Lights.off(1);
                            Lights.off(2);
                            Lights.off(3);
                        }
                    } else {

                        if (isChecked)
                            Lights.on(position);
                        else
                            Lights.off(position);
                    }
                }
            }
        });

        View overflow = convertView.findViewById(R.id.menu_overflow);
        overflow.setTag(position);
        overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int pos = (Integer) v.getTag();

                PopupMenu popupMenu = new PopupMenu(activity, v);
                popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.overflow_intensity:
                                Intent intent = new Intent(activity, DialogIntensityActivity.class);
                                Bundle params = new Bundle();
                                params.putInt("position", pos);
                                intent.putExtras(params);

                                activity.startActivity(intent);
                                return true;

                            case R.id.overflow_color:
                                Intent intent2 = new Intent(activity, DialogColorActivity.class);
                                Bundle params2 = new Bundle();
                                params2.putInt("position", pos);
                                intent2.putExtras(params2);

                                activity.startActivity(intent2);
                                return true;
                        }
                        return true;
                    }
                });
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.show();
            }
        });

        name.setText(r.getName());

        if (r.getType() == Resource.AIR) {
            icon.setImageDrawable(activity.getResources().getDrawable(R.mipmap.ic_air));
        } else if (r.getType() == Resource.LAMP) {
            icon.setImageDrawable(activity.getResources().getDrawable(R.mipmap.ic_lamp));
        }

        onoff.setChecked(r.getStatus());

        try {
            description.setText(r.getDescription());
        } catch (NullPointerException e) {
            Log.i("OnOffAdapter", "no description");
        }

        return convertView;
    }

}
