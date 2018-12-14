package pe.com.lucky.prueba;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    TableLayout tableLayout;
    TextView count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableLayout=(TableLayout)findViewById(R.id.tableLayout);
        count = findViewById(R.id.count);
        //get
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        //
        for (int i=0;i<5;i++){
            View tableRow = LayoutInflater.from(this).inflate(R.layout.molde,null,false);
            EditText history_display_no  = (EditText) tableRow.findViewById(R.id.textView);
            CheckBox history_display_date  = (CheckBox) tableRow.findViewById(R.id.checkBox);

            history_display_no.setText("Edit n#"+(i+1));
            Boolean restoredText = prefs.getBoolean("value-"+history_display_no.getText().toString(), false);
            if (restoredText != null) {
                history_display_date.setChecked(prefs.getBoolean("value-"+history_display_no.getText().toString(), false));
            }
            history_display_date.setOnClickListener(onClick(history_display_no,history_display_date));
            tableLayout.addView(tableRow);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
    }

    View.OnClickListener onClick(final EditText tc, final CheckBox row) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                if (row.isChecked()){
                    editor.putBoolean("value-"+tc.getText().toString(), row.isChecked() ? true : false);
                    editor.apply();
                }else{
                    editor.remove("value-"+tc.getText().toString()).commit();
                }
                    //Toast toast = Toast.makeText(getApplicationContext(), tc.getText().toString() +" - "+String.valueOf(row.isChecked() ? true : false), Toast.LENGTH_LONG);
                    //toast.show();
            }
            };

    }

}