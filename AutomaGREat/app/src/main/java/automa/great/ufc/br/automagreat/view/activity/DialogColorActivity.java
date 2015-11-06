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

        Button bRed = (Button) findViewById(R.id.bColorRed);
        bRed.setBackgroundColor(Color.RED);
        bRed.setOnClickListener(this);

        Button bGreen = (Button)findViewById(R.id.bColorGreen);
        bGreen.setBackgroundColor(Color.GREEN);
        bGreen.setOnClickListener(this);

        Button bBlue = (Button)findViewById(R.id.bColorBlue);
        bBlue.setBackgroundColor(Color.BLUE);
        bBlue.setOnClickListener(this);

        Button bYellow = (Button) findViewById(R.id.bColorYellow);
        bYellow.setBackgroundColor(Color.YELLOW);
        bYellow.setOnClickListener(this);

        Button bMagenta = (Button) findViewById(R.id.bColorMagenta);
        bMagenta.setBackgroundColor(Color.MAGENTA);
        bMagenta.setOnClickListener(this);

        Button bWhite = (Button) findViewById(R.id.bColorWhite);
        bWhite.setBackgroundColor(Color.WHITE);
        bWhite.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bColorRed:

                if(position == 0){
                    Lights.color(1, Color.RED);
                    Lights.color(2, Color.RED);
                    Lights.color(3, Color.RED);
                }else{
                    Lights.color(position, Color.RED);
                }

                break;
            case R.id.bColorGreen:

                if(position == 0){
                    Lights.color(1, Color.GREEN);
                    Lights.color(2, Color.GREEN);
                    Lights.color(3, Color.GREEN);
                }else{
                    Lights.color(position, Color.GREEN);
                }

                break;
            case R.id.bColorBlue:

                if(position == 0){
                    Lights.color(1, Color.BLUE);
                    Lights.color(2, Color.BLUE);
                    Lights.color(3, Color.BLUE);
                }else{
                    Lights.color(position, Color.BLUE);
                }

                break;
            case R.id.bColorYellow:

                if(position == 0){
                    Lights.color(1, Color.YELLOW);
                    Lights.color(2, Color.YELLOW);
                    Lights.color(3, Color.YELLOW);
                }else{
                    Lights.color(position, Color.YELLOW);
                }

                break;
            case R.id.bColorMagenta:

                if(position == 0){
                    Lights.color(1, Color.MAGENTA);
                    Lights.color(2, Color.MAGENTA);
                    Lights.color(3, Color.MAGENTA);
                }else{
                    Lights.color(position, Color.MAGENTA);
                }

                break;
            case R.id.bColorWhite:

                if(position == 0){
                    Lights.color(1, Color.WHITE);
                    Lights.color(2, Color.WHITE);
                    Lights.color(3, Color.WHITE);
                }else{
                    Lights.color(position, Color.WHITE);
                }
        }
    }
}