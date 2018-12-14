package pe.com.lucky.prueba;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView texto = findViewById(R.id.texto);

        for (int i=0;i<=5;i++) {
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            Boolean restoredText = prefs.getBoolean("value-Edit n#"+i, false);
            if (restoredText != null) {
                if (prefs.getBoolean("value-Edit n#"+i, false) == true) {
                    value = value + "\n" + "value-Edit n#" + i + " - " + String.valueOf(prefs.getBoolean("value-Edit n#" + i, false));

                } }
        }
        texto.setText(value);
    }
}
