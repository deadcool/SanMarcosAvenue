package com.example.luiz.sanmarcosavenue.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luiz.sanmarcosavenue.R;
import com.example.luiz.sanmarcosavenue.Model.NoticiasModel;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USUARIO on 14/05/2016.
 */
public class Noticias extends Fragment {
    ListView miListaNoticias;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_noticias,container,false);

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity().getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config); // Do it on Application start

        miListaNoticias = (ListView)rootView.findViewById(R.id.listaNoticias);
        new JSONTask().execute("http://52.36.38.235:9988/noticias");

        return rootView;
    }

    public class JSONTask extends AsyncTask<String, String,List<NoticiasModel>> {

        @Override
        protected List<NoticiasModel> doInBackground(String... params) {
            HttpURLConnection httpURLConnection = null;
            BufferedReader bufferedReader = null;

            try {
                URL url = new URL(params[0]);
                httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer = new StringBuffer();

                String line;
                while((line = bufferedReader.readLine()) != null){
                    stringBuffer.append(line);
                }

                String finalJSON = stringBuffer.toString();
                JSONArray parentArray = new JSONArray(finalJSON);

                List<NoticiasModel> NoticiasModelList = new ArrayList<>();
                for (int i = 0; i < parentArray.length(); i++) {
                    NoticiasModel NoticiasModel = new NoticiasModel();
                    JSONObject finalJSONObject = parentArray.getJSONObject(i);
                    NoticiasModel.setTitulo(finalJSONObject.getString("Titulo"));
                    NoticiasModel.setDescripcion(finalJSONObject.getString("Descripcion"));
                    NoticiasModel.setFecha(finalJSONObject.getString("Fecha"));
                    //NoticiasModel.setImagen(finalJSONObject.getString("URLFoto"));
                    NoticiasModelList.add(NoticiasModel);
                }

                return NoticiasModelList;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (httpURLConnection != null){
                    httpURLConnection.disconnect();
                }
                try {
                    if (bufferedReader != null){
                        bufferedReader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<NoticiasModel>result){
            super.onPostExecute(result);
            UbicacionAdaptor adaptor = new UbicacionAdaptor(getActivity().getApplicationContext(), R.layout.fila_noticias, result);
            miListaNoticias.setAdapter(adaptor);
        }
    }

    public class UbicacionAdaptor extends ArrayAdapter {
        private List<NoticiasModel> noticiasModelList;
        private int resource;
        LayoutInflater inflater;

        public UbicacionAdaptor(Context context, int resource, List objects) {
            super(context, resource, objects);
            noticiasModelList = objects;
            this.resource = resource;
            inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null){
                viewHolder = new ViewHolder();
                convertView =inflater.inflate(resource,null);
                viewHolder.ivnoticia = (ImageView)convertView.findViewById(R.id.ivnoticia);
                viewHolder.tvtitulo = (TextView)convertView.findViewById(R.id.tvnoticiatitulo);
                viewHolder.tvdescripcion = (TextView)convertView.findViewById(R.id.tvnoticiadescripcion);
                viewHolder.tvfecha = (TextView)convertView.findViewById(R.id.tvnoticiafecha);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder)convertView.getTag();
            }

            viewHolder.tvtitulo.setText(noticiasModelList.get(position).getTitulo());
            viewHolder.tvdescripcion.setText(noticiasModelList.get(position).getDescripcion());
            viewHolder.tvfecha.setText(noticiasModelList.get(position).getFecha());

            return convertView;
        }

        class ViewHolder{
            private ImageView ivnoticia;
            private TextView tvtitulo;
            private TextView tvdescripcion;
            private TextView tvfecha;
        }
    }
}