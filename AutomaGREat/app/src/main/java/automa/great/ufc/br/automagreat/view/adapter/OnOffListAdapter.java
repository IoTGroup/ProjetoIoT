package automa.great.ufc.br.automagreat.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import automa.great.ufc.br.automagreat.R;
import automa.great.ufc.br.automagreat.model.Resource;

/**
 * Created by Thae on 05/10/2015.
 */
public class OnOffListAdapter extends BaseAdapter {
    private Context context;
    private List<Resource> resources;

    public OnOffListAdapter (Context context, ArrayList<Resource> resources) {
        this.context = context;
        this.resources = resources;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.item_onoff, null);
        }

        Resource r = this.resources.get(position);
        ImageView icon = (ImageView) convertView.findViewById(R.id.iv_icon);
        TextView name = (TextView) convertView.findViewById(R.id.tv_object_name);
        TextView description = (TextView) convertView.findViewById(R.id.tv_description);
        Switch onoff = (Switch) convertView.findViewById(R.id.switch_onoff_list);

        name.setText(r.getName());
        if (r.getType() == Resource.AIR) {
            icon.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_air));
        }
        if (r.getType() == Resource.LAMP) {
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
}
