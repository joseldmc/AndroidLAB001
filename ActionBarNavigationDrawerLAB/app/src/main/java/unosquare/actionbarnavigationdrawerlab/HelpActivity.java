package unosquare.actionbarnavigationdrawerlab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class HelpActivity extends Activity {
    EditText edtTxtSubtitle;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        initUI();
        setClick();

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setClick() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edtTxtSubtitle.getText().toString();

                if(text != null && text.length() > 0){
                    Intent intent = new Intent();
                    intent.putExtra("subtitle",text);
                    setResult(200,intent); //200 número que indica si el resultado fue satisfactorio
                    finish();
                }
                else {
                    edtTxtSubtitle.setError("Error");
                }
            }
        });
    }

    private void initUI() {
        edtTxtSubtitle = (EditText) findViewById(R.id.edtTxtSubtitle);
        btnSend = (Button) findViewById(R.id.btnSend);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.help, menu);
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
