package pardo.joseangel.gps;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private TextView tvLocation=null;
    private ProgressDialog pd =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLocation=(TextView) findViewById(R.id.tvLocation);
        pd = ProgressDialog.show(this,"Location","Esperando coordenadas...");//creo el popaud para que muestre un mensaje de carga
        configGPS();
    }

    private void configGPS()
    {
        LocationManager mLocarionManager;
        LocationListener mLocationListener;

        mLocarionManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocationListener= new MyLocationListener();

        mLocarionManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,//proveedor de gps
                5000,//tiempo refrescar
                10,//distancia minima variada
                mLocationListener);//se ejecuta un codigo cuando se cumple las consdiciones anteriores
    }

    private void updateScreen(Location location)
    {
        tvLocation.setText("Latitud="+String.valueOf(location.getLatitude()) + "\n" +
                           "Longitud="+String.valueOf(location.getLongitude()));
        pd.dismiss();//cierro el dialogo de cargando...

    }
    //creacion de la clase listener
    private class MyLocationListener implements LocationListener
    {

        @Override
        public void onLocationChanged(Location location) {//se ejecuta cuando se cumplan las condiciones anteriores

            Log.d("Hello GPS","Latitud="+String.valueOf(location.getLatitude()));
            Log.d("Hello GPS","Longitud="+String.valueOf(location.getLongitude()));

            updateScreen(location);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {//se ejecuta cuadno varia el estado del gps

        }

        @Override
        public void onProviderEnabled(String provider) {//se ejecuta cuando se activa el gps

        }

        @Override
        public void onProviderDisabled(String provider) {//se ejecuta cuando se desabilita el gps

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
