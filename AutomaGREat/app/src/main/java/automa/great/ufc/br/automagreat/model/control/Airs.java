package automa.great.ufc.br.automagreat.model.control;

import android.util.Log;

import automa.great.ufc.br.automagreat.model.context.ContextKeys;
import automa.great.ufc.br.automagreat.model.context.ContextManager;
import automa.great.ufc.br.automagreat.util.Config;
import br.ufc.great.syssu.base.Tuple;

/**
 * Created by lana on 03/11/15.
 */
public class Airs {

    static public boolean isOn = false;
    static public int minRange;
    static public int maxRange;
    static public int temperature = 24;
    static private int countClick = 2;


    private void rangeTemperatureAC(String contextKey) {
        Tuple tupleRange = ContextManager.getInstance().setSync(contextKey, "Range", 5000);
        String range[] = tupleRange.getField(1).getValue().toString().split(", ");
        String minRange = range[0].substring(1);
        String maxRange = range[1].substring(0, range[1].length() - 1);
        this.minRange = Integer.valueOf(minRange);
        this.maxRange = Integer.valueOf(maxRange);

    }


    static public void onOff() {
        Log.d(Config.TAG, "mButtonOnOff isOn : " + isOn);

        if (isOn) {
            isOn = false;
            setState("off");
        } else {
            isOn = true;
            setCommands("temp:" + temperature);
        }
    }

    static public String plus() {
        String result = new String();
        if (isOn == true) {
            if (temperature == maxRange) {
                countClick--;
                if (countClick == 0) {
                    result = callToastTempMaxMin(temperature);
                }
            } else {
                temperature = temperature + 1;
            }
            setCommands("temp:" + temperature);
        }

        return result;
    }

    static public String minus() {
        String result = new String();
        if (isOn == true) {
            if (temperature == minRange) {
                countClick--;
                if (countClick == 0) {
                    result = callToastTempMaxMin(temperature);
                }

            } else {
                temperature = temperature - 1;
            }
            setCommands("temp:" + temperature);
        }

        return result;

    }

    static private void setState(String value) {
        Tuple tuple = (Tuple) new Tuple().addField("ControlKey", ContextKeys.CONTROL_AC).addField("Command", value);
        ContextManager.getInstance().sendCommand(tuple);
    }

    static private void setCommands(String value) {
        Tuple tuple = (Tuple) new Tuple().addField("ControlKey", ContextKeys.CONTROL_AC).addField("Command", value);
        ContextManager.getInstance().sendCommand(tuple);
    }

    static private String callToastTempMaxMin(int temperature) {
        countClick = 2;
        return temperature == minRange ? "Minimum air temperature supported" : "Maximum air temperature supported";
    }

}
