package automa.great.ufc.br.automagreat.model.control;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import automa.great.ufc.br.automagreat.model.context.ContextKeys;
import automa.great.ufc.br.automagreat.model.context.ContextManager;
import br.ufc.great.syssu.base.Tuple;
import br.ufc.great.syssu.base.interfaces.IClientReaction;

/**
 * Created by lana on 06/12/15.
 */
public class MotionSensor {

    public static boolean getSensorData(){
        Tuple tuple = ContextManager.getInstance().subscribeSync(ContextKeys.MOTION_SENSOR,5000);
       // Log.i("TAG", "REsultado: " + tuple.getField("data").getValue());
        //todo receber aqui o valor de presença

        return false;
    }
}
