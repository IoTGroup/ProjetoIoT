package automa.great.ufc.br.automagreat.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import automa.great.ufc.br.automagreat.R;

public class PinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        final EditText edPin = (EditText) findViewById(R.id.et_pin);
        Button bPin = (Button) findViewById(R.id.bt_pin);
        bPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pin = edPin.getText().toString();

                if(pin.equals(getResources().getString(R.string.pin))){
                    startActivity(new Intent(PinActivity.this, TabsActivity.class));
                } else if (pin.equals("")){
                    edPin.setError(getString(R.string.error_empty_pin));
                } else {
                    edPin.setError(getString(R.string.error_wrong_pin));
                }
            }
        });

    }
}