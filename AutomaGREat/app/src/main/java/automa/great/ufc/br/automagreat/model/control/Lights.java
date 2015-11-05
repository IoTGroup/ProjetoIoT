package automa.great.ufc.br.automagreat.model.control;

import android.graphics.Color;
import android.util.Log;

import automa.great.ufc.br.automagreat.util.Config;
import automa.great.ufc.br.automagreat.model.context.ContextKeys;
import automa.great.ufc.br.automagreat.model.context.ContextManager;
import br.ufc.great.syssu.base.Tuple;

/**
 * Created by lana on 03/11/15.
 */
public class Lights {

    // Comandos Default da lampada privados a esta classe

    static private void turnOn() {
        Tuple tuple = (Tuple) new Tuple().addField("ControlKey", ContextKeys.HUE_LIGHT).addField("State", "on");
        ContextManager.getInstance().sendCommand(tuple);

        // inicializa a com valores predefinidos
        Tuple tupleC = (Tuple) new Tuple().addField("ControlKey", ContextKeys.HUE_LIGHT).addField("Color", Color.YELLOW);
        ContextManager.getInstance().sendCommand(tupleC);
        Tuple tupleB = (Tuple) new Tuple().addField("ControlKey", ContextKeys.HUE_LIGHT).addField("Brightness", "100");
        ContextManager.getInstance().sendCommand(tupleB);
    }

    static private void turnOff() {
        Tuple tuple = (Tuple) new Tuple().addField("ControlKey", ContextKeys.HUE_LIGHT).addField("State", "off");
        ContextManager.getInstance().sendCommand(tuple);
    }

    static private void connect(String lampId) {
        Tuple tuple = (Tuple) new Tuple().addField("ControlKey", ContextKeys.HUE_LIGHT).addField("Lights", lampId);
        ContextManager.getInstance().sendCommand(tuple);
    }

    static private void setColor(String color) {
        Tuple tuple = (Tuple) new Tuple().addField("ControlKey", ContextKeys.HUE_LIGHT).addField("Color", color);
        ContextManager.getInstance().sendCommand(tuple);
    }

    static private void setBright(String bright) {
        Tuple tuple = (Tuple) new Tuple().addField("ControlKey", ContextKeys.HUE_LIGHT).addField("Brightness", bright);
        ContextManager.getInstance().sendCommand(tuple);
    }

    // Comandos para serem chamados

    static public void on(int id) {
        Log.d(Config.TAG, "Lights On");
        connect(String.valueOf(id));
        turnOn();
    }

    static public void off(int id) {
        Log.d(Config.TAG, "Lights Off");
        connect(String.valueOf(id));
        turnOff();
    }

    static public void setIntensity(int id, String vInts) {
        Log.d(Config.TAG, "Set intensity to " + vInts);
        connect(String.valueOf(id));
        setBright(vInts);
    }
}
