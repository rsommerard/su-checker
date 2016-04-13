package fr.inria.rsommerard.suchecker;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import eu.chainfire.libsuperuser.Shell;

public class MainActivity extends AppCompatActivity {

    private TextView isSUAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isSUAvailable = (TextView) findViewById(R.id.is_su_available);

        Button checkSUButton = (Button) findViewById(R.id.check_su);
        assert checkSUButton != null;
        checkSUButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSU();
            }
        });
    }

    private void checkSU() {
        if (Shell.SU.available()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    isSUAvailable.setText("SU is available\n" + Shell.SU.version(false));
                    isSUAvailable.setTextColor(Color.parseColor("#4CAF50"));
                }
            });
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    isSUAvailable.setText("SU is not available");
                    isSUAvailable.setTextColor(Color.parseColor("#F44336"));
                }
            });
        }
    }
}
