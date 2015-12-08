package automa.great.ufc.br.automagreat.model.control;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import automa.great.ufc.br.automagreat.util.Config;

/**
 * Created by lana on 06/12/15.
 */
public class MotionSensor {

    static int window = 1000; // janela de espera para verificar a presença na sala
    public static boolean movimentDetected = false;
    public static boolean lastMoviment = false;
    public static boolean autoOn = false;
    public static Timer timer;

    private static int count = 0;
    private static Handler handler;

    public static void setSensorData(String data) {
        String value = new String();
        try {
            JSONObject object = new JSONObject(data);
            value = object.getString("Data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        lastMoviment = movimentDetected;
        if (value.equals("1"))
            movimentDetected = true;
        else
            movimentDetected = false;

        Log.i(Config.TAG, "MotionSensor:" + movimentDetected);

    }

    public static boolean getSensorData() {

        return movimentDetected;
    }


    public static void verifyMotion(boolean modeManual) {

        if ((lastMoviment && !movimentDetected) && !modeManual) {
            Log.d(Config.TAG, "Modo Automatico iniciando!");
            handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.d(Config.TAG, "Modo Automatico: Desligando as luzes!");
                    Lights.off(1);
                    Lights.off(2);
                    Lights.off(3);
                }
            }, window);

        }
    }


}
