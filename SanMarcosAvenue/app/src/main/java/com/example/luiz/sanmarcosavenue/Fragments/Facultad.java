package com.example.luiz.sanmarcosavenue.Fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.example.luiz.sanmarcosavenue.Model.FacultadModel;

import com.google.gson.Gson;
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

public class Facultad extends Fragment {
    ListView miListaFacultades;
    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_facultad,container,false);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity().getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config); // Do it on Application start

        miListaFacultades = (ListView)rootView.findViewById(R.id.listaFacultad);
        new JSONTask().execute("http://52.36.38.235:9988/facultades");

        return rootView;
    }

    public class JSONTask extends AsyncTask<String, String,List<FacultadModel>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected List<FacultadModel> doInBackground(String... params) {
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

                List<FacultadModel> FacultadModelList = new ArrayList<>();

                Gson gson = new Gson();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalJSONObject = parentArray.getJSONObject(i);
                    //FacultadModel facultadModel = gson.fromJson(finalJSONObject.toString(), FacultadModel.class);
                    FacultadModel facultadModel = new FacultadModel();
                    facultadModel.setNombre(finalJSONObject.getString("Nombre"));
                    facultadModel.setAutoridad(finalJSONObject.getString("Autoridad"));
                    facultadModel.setUrlFoto(finalJSONObject.getString("URLFoto"));
                    FacultadModelList.add(facultadModel);
                }
                return FacultadModelList;
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
        protected void onPostExecute(List<FacultadModel>result){
            super.onPostExecute(result);
            progressDialog.dismiss();
            FacultadAdaptor adaptor = new FacultadAdaptor(getActivity().getApplicationContext(), R.layout.fila_facultad, result);
            miListaFacultades.setAdapter(adaptor);

            miListaFacultades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                    Toast.makeText(getActivity().getApplicationContext(), "Lanza", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public class FacultadAdaptor extends ArrayAdapter {
        private List<FacultadModel> facultadModelList;
        private int resource;
        LayoutInflater inflater;

        public FacultadAdaptor(Context context, int resource, List objects) {
            super(context, resource, objects);
            facultadModelList = objects;
            this.resource = resource;
            inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null){
                viewHolder = new ViewHolder();
                convertView =inflater.inflate(resource,null);
                viewHolder.ivfacultad = (ImageView)convertView.findViewById(R.id.ivFacultad);
                viewHolder.tvnombre = (TextView)convertView.findViewById(R.id.tvFacultadNombre);
                viewHolder.tvautoridad = (TextView)convertView.findViewById(R.id.tvFacultadAutoridad);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder)convertView.getTag();
            }

            final ProgressBar progressBar = (ProgressBar)convertView.findViewById(R.id.progressBar);
            ImageLoader.getInstance().displayImage(facultadModelList.get(position).getUrlFoto(), viewHolder.ivfacultad, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                    progressBar.setVisibility(View.GONE);
                }
            });

            viewHolder.tvnombre.setText(facultadModelList.get(position).getNombre());
            viewHolder.tvautoridad.setText(facultadModelList.get(position).getAutoridad());

            return convertView;
        }

        class ViewHolder{
            private ImageView ivfacultad;
            private TextView tvnombre;
            private TextView tvautoridad;
        }
    }
}
