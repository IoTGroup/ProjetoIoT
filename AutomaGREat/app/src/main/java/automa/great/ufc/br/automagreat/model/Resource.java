package automa.great.ufc.br.automagreat.model;

import android.util.Log;

/**
 * Created by Thae on 05/10/2015.
 */
public class Resource {
    public final static int AIR = 0;
    public final static int LAMP = 1;

    private String name;
    private String description;
    private boolean status;
    private int type;

    public Resource (String name, int type) {
        if ((type == 0) || (type == 1)) {
            this.name = name;
            this.type = type;
            this.status = false;
            this.description = "";
        } else {
            Log.i("Resourses", "wrong type");
            return;
        }
    }

    public Resource (String name, String description, int type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public String getName () { return this.name; }

    public String getDescription () { return this.description; }

    public boolean getStatus () { return this.status; }

    public int getType () { return this.type; }

    public void setName (String name) {this.name = name; }

    public void setDescription (String description) { this.description = description; }

    public void setStatus (boolean status) { this.status = status; }
}

