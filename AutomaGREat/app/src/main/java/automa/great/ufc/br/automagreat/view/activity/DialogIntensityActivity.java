package automa.great.ufc.br.automagreat.view.activity;

import android.app.Activity;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.os.Bundle;
import automa.great.ufc.br.automagreat.R;
import automa.great.ufc.br.automagreat.model.control.Lights;
import automa.great.ufc.br.automagreat.util.Config;
import automa.great.ufc.br.automagreat.model.context.ContextKeys;
import automa.great.ufc.br.automagreat.model.context.ContextManager;
import automa.great.ufc.br.automagreat.model.listeners.ContextListener;
import automa.great.ufc.br.automagreat.view.fragment.LampFragment;

public class DialogIntensityActivity extends Activity  implements ContextListener {

    private SeekBar seekBar;
    private TextView textView;
    private int position;
    private LampFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_slider);

        Bundle params = getIntent().getExtras();
        position = params.getInt("position");

        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        textView = (TextView) findViewById(R.id.textView1);

        // Initialize the textview with '0'.
        textView.setText("Covered: " + seekBar.getProgress() + "/" + seekBar.getMax());

        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {;

                if(position == 0){
                    Lights.setIntensity(1, String.valueOf(progressValue));
                    Lights.setIntensity(2, String.valueOf(progressValue));
                    Lights.setIntensity(3, String.valueOf(progressValue));
                }else
                    Lights.setIntensity(position, String.valueOf(progressValue));
                Log.d(Config.TAG, "SeekBar: onProgressChanged");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(Config.TAG, "SeekBar: onStart");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView.setText("Covered: " + progress + "/" + seekBar.getMax());
                Log.d(Config.TAG, "SeekBar: onStop");
            }
        });

        // registra esta activity no Loccam
        ContextManager.getInstance().registerListener(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ContextManager.getInstance().unregisterListener(this);
    }

    @Override
    public void onContextReady(String data) {

    }

    @Override
    public String getContextKey() {
        return ContextKeys.HUE_LIGHT;
    }
}
