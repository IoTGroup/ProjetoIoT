package automa.great.ufc.br.automagreat.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import automa.great.ufc.br.automagreat.R;
import automa.great.ufc.br.automagreat.model.context.ContextKeys;
import automa.great.ufc.br.automagreat.model.context.ContextManager;
import automa.great.ufc.br.automagreat.model.listeners.ContextListener;
import automa.great.ufc.br.automagreat.model.listeners.LoccamConnectedListener;

public class SplashActivity extends AppCompatActivity implements ContextListener {

    private static final int MAX_SPLASH_TIME = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // conecta a aplicação ao loccam
        ContextManager.getInstance().connect(getApplicationContext(), getResources().getString(R.string.app_name), new LoccamConnectedListener() {
            @Override
            public void onLoccamConnected() {
                ContextManager.getInstance().registerListener(SplashActivity.this);
            }
        });

        start();
    }

    // carregar posteriormente este método no SplashActivity
    private void start() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (ContextManager.getInstance() == null)
                    error();
                else {
                    Intent it = new Intent(getApplicationContext(), PinActivity.class);
                    startActivity(it);
                    Log.d("Automa", "Serviço Loccam conectado");
                }

            }
        }, MAX_SPLASH_TIME);
    }


    @Override
    public void onContextReady(String data) {

        if (ContextManager.getInstance() == null)
            error();
        else {
            Log.d("Automa", "Serviço Loccam conectado");
        }
    }

    private void error() {
        CharSequence text = "ERROR: Loccam service is not connected.";
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
        toast.show();
        Log.i("Automa", "Erro ao conectar com Loccam");
    }

    @Override
    public String getContextKey() {
        return ContextKeys.getContextkeyGetCacs();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ContextManager.getInstance().unregisterListener(this);
    }
}
