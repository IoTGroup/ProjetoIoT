package automa.great.ufc.br.automagreat.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import automa.great.ufc.br.automagreat.R;
import automa.great.ufc.br.automagreat.model.control.Lights;

public class DialogColorActivity extends Activity implements View.OnClickListener {

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_color);

        Bundle params = getIntent().getExtras();
        position = params.getInt("position");

        Button bRed = (Button) findViewById(R.id.button1);
        bRed.setBackgroundColor(Color.RED);
        bRed.setOnClickListener(this);
        Button bGreen = (Button)findViewById(R.id.button2);
        bGreen.setBackgroundColor(Color.GREEN);
        bGreen.setOnClickListener(this);
        Button bBlue = (Button)findViewById(R.id.button3);
        bBlue.setBackgroundColor(Color.BLUE);
        bBlue.setOnClickListener(this);
        Button bYellow = (Button) findViewById(R.id.button4);
        bYellow.setBackgroundColor(Color.YELLOW);
        bYellow.setOnClickListener(this);

        Button bMagenta = (Button) findViewById(R.id.button5);
        bMagenta.setBackgroundColor(Color.MAGENTA);
        bMagenta.setOnClickListener(this);

        Button bWhite = (Button) findViewById(R.id.button6);
        bWhite.setBackgroundColor(Color.WHITE);
        bWhite.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.button1:
                Lights.color(position, Color.RED);
                break;
            case R.id.button2:
                Lights.color(position, Color.GREEN);
                break;
            case R.id.button3:
                Lights.color(position, Color.BLUE);
                break;
            case R.id.button4:
                Lights.color(position, Color.YELLOW);
                break;
            case R.id.button5:
                Lights.color(position, Color.MAGENTA);
                break;
            case R.id.button6:
                Lights.color(position, Color.WHITE);
        }
    }
}