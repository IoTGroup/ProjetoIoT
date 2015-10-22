package automa.great.ufc.br.automagreat.view.activity;

import android.app.Activity;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.os.Bundle;
import automa.great.ufc.br.automagreat.R;
import automa.great.ufc.br.automagreat.interfaces.ILamp;

public class DialogSliderActivity extends Activity {

    private SeekBar seekBar;
    private TextView textView;
    private ILamp iLamp;
    private String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_slider);

        Bundle params = getIntent().getExtras();
        position = params.getString("position");

        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        textView = (TextView) findViewById(R.id.textView1);

        // Initialize the textview with '0'.
        textView.setText("Covered: " + seekBar.getProgress() + "/" + seekBar.getMax());

        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                //Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
                iLamp.setIntensity(position, String.valueOf(progress));
                Log.i("Resource", "Mudança em progresso 000...");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
                Log.i("Resource", "Inicio do progresso 000...");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView.setText("Covered: " + progress + "/" + seekBar.getMax());
                //Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
                Log.i("Resource", "Parada do progresso 000...");
            }
        });
    }
}
