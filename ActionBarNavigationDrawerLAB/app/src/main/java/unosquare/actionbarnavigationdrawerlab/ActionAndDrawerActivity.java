package unosquare.actionbarnavigationdrawerlab;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

//Actividad Principal
public class ActionAndDrawerActivity extends Activity implements MyFragment.onFragmentInteraction{

    private ListView list;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private Intent intent;

    //
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer_layout); //referencia a un layout drawer layout

		initUI(); //inicializa drawer y lista del drawer_layout
        setAdapter(); //crea instancia de drawer adapter y la asigna
        setClicks();
        setToggle();
	}

    private void initUI() {
        list = (ListView) findViewById(R.id.left_drawer);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
	}

    private void setAdapter() {
        DrawerAdapter adapter = new DrawerAdapter(this);
        list.setAdapter(adapter);
    }

    private void setClicks() {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ActionAndDrawerActivity.this,"Menu ITem: " + DrawerAdapter.menu[i],Toast.LENGTH_SHORT).show();
                drawer.closeDrawers(); //cerrar despues del click
                addFragment(getColorByPosition(i), DrawerAdapter.menu[i]);
                //getActionBar().setTitle(DrawerAdapter.menu[i]);

            }
        });
    }

    private int getColorByPosition(int postion) {
        int color = R.color.black;
        switch (postion){
            case 0:
                color = R.color.blue;
                break;
            case 1:
                color = R.color.red;
                break;
            case 2:
                color = R.color.green;
                break;
            case 3:
                color = R.color.fuchsia;
                break;
            default:
                color = R.color.black;
        }

        return color;
    }

    private void addFragment(int color, String title) {
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.content_frame,MyFragment.getInstance(color,title,"Sub" + title)).commit();
    }

    private void setToggle() {
        mDrawerToggle = new ActionBarDrawerToggle(this,
                                                  drawer,
                                                  R.drawable.ic_drawer,
                                                  R.string.action_settings,
                                                  R.string.action_settings);
        drawer.setDrawerListener(mDrawerToggle);

        //getActionBar().setTitle("Main title");
        //getActionBar().setSubtitle("Subtitle");
        //Habilitar action bar
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //PAra que se sincronice y sette la imagen ---
    @Override
    protected void onResume() {
        super.onResume();
        mDrawerToggle.syncState();
    }

    //Es parte del ciclo de vida de la actividad.. verifica si hay algun menu que crear
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //al hacer clic a algùn item de la funciòn entrarà ahì
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if(mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        switch (item.getItemId()){
            case R.id.menu_help:
                Toast.makeText(ActionAndDrawerActivity.this,"click Help",Toast.LENGTH_SHORT).show();
                intent = new Intent(ActionAndDrawerActivity.this,HelpActivity.class);
                startActivityForResult(intent,100);
                /*
                startActivityForResult(intent,200); //200 codigo cuando una actividad regrese saber desde donde lo mandé-- regresa onActivityResult
                * */
 //             addFragment(R.color.olive,"Help");
                break;
            case R.id.menu_settings:
                Toast.makeText(ActionAndDrawerActivity.this,"click Settings",Toast.LENGTH_SHORT).show();
                //setContentView(R.layout.activity_settings);
                intent = new Intent(ActionAndDrawerActivity.this,SettingsActivity.class);
                startActivityForResult(intent,101);
 //             addFragment(R.color.lime,"Settings");
                break;
            default:
                startActivity(intent);
        }


        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    public void setTitle(String title, String subtitle) {
        getActionBar().setTitle(title);
        getActionBar().setSubtitle(subtitle);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == 200){
            String subtitle = (String) data.getExtras().get("subtitle");
            Toast.makeText(ActionAndDrawerActivity.this,subtitle,Toast.LENGTH_LONG).show();
            getActionBar().setSubtitle(subtitle);
        }
        else if(requestCode == 101 && resultCode == 200){
            String title = (String) data.getExtras().get("title");
            Toast.makeText(ActionAndDrawerActivity.this,title,Toast.LENGTH_LONG).show();
            getActionBar().setTitle(title);
        }
        else{
            Toast.makeText(ActionAndDrawerActivity.this,"Error request",Toast.LENGTH_LONG).show();
        }

    }
}