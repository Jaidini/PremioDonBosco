package com.example.tehlucr.smf;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Button insertar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        setContentView(R.layout.activity_main);

        insertar = (Button) findViewById(R.id.botonInsertar);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Y aquí pondremos los datos que se enviaran a la base de datos
            }
        });
    }

    private boolean insert() {
        HttpClient httpClient;
        List<NameValuePair> nameValuePairs;
        HttpPost httpPost;
        httpClient = new DefaultHttpClient();
        //Esto nos hará el Post en nuestra base de datos
        httpPost = new HttpPost("http://smfdatabase.esy.es/smfconnect/insertar.php");
        /*ahora hay que añadir los datos que insertaremos en la base nde datos con
        /nameValuePairs = new ArrayList<NameValuePair>(numero)
        nameValuePairs.add(new BasicNameValuePair("dni", dini.getText().toString.trim() ))*/

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            httpClient.execute(httpPost);
            return true;
        } catch (Exception e) {
            final int e1 = Log.e(e);
        }
        return false;
    }

    public class Insertar extends AsyncTask<String, String, String> {

        private Activity context;

        Insertar(Activity context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... params) {
            if (insert())
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "Petición enviada", Toast.LENGHT_LONG).show();
                        //aquí irán los diferentes campos que se insertarán en la base de datos
                    }
                });
            else
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "Petición no enviada", Toast.LENGHT_LONG).show();
                    }
                });

            return null;
        }

    }
}


//http://picarcodigo.blogspot.com.es/2014/05/webservice-conexiones-base-de-datos.html
