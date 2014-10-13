package unosquare.actionbarnavigationdrawerlab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SettingsActivity extends Activity {
    EditText edtTxtTitle;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initUI();
        setClick();

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setClick() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edtTxtTitle.getText().toString();

                if(text != null && text.length() > 0){
                    Intent intent = new Intent();
                    intent.putExtra("title",text);
                    setResult(200,intent); //200 número que indica si el resultado fue satisfactorio
                    finish();
                }
                else {
                    edtTxtTitle.setError("Error");
                }
            }
        });
    }

    private void initUI() {
        edtTxtTitle = (EditText) findViewById(R.id.edtTxtTitle);
        btnSend = (Button) findViewById(R.id.btnSendTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
