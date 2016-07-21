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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.luiz.sanmarcosavenue.Model.InstitucionModel;
import com.example.luiz.sanmarcosavenue.R;
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

public class Institucion extends Fragment {
    ListView miListaInstituciones;
    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_institucion,container,false);

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

        miListaInstituciones = (ListView)rootView.findViewById(R.id.listaInstituciones);
        new JSONTask().execute("http://52.36.38.235:9988/areasuniversidad");

        return rootView;
    }

    public class JSONTask extends AsyncTask<String, String,List<InstitucionModel>> {

        @Override
        protected List<InstitucionModel> doInBackground(String... params) {
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

                List<InstitucionModel> InstitucionModelList = new ArrayList<>();
                for (int i = 0; i < parentArray.length(); i++) {
                    InstitucionModel InstitucionModel = new InstitucionModel();
                    JSONObject finalJSONObject = parentArray.getJSONObject(i);
                    InstitucionModel.setId(finalJSONObject.getString("Id"));
                    InstitucionModel.setNombre(finalJSONObject.getString("Nombre"));
                    InstitucionModel.setURL(finalJSONObject.getString("URL"));
                    InstitucionModelList.add(InstitucionModel);
                }
                return InstitucionModelList;

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
        protected void onPostExecute(List<InstitucionModel>result){
            super.onPostExecute(result);
            InstitucionAdaptor adaptor = new InstitucionAdaptor(getActivity().getApplicationContext(), R.layout.fila_institucion, result);
            miListaInstituciones.setAdapter(adaptor);
        }
    }

    public class InstitucionAdaptor extends ArrayAdapter {
        private List<InstitucionModel> InstitucionModelList;
        private int resource;
        LayoutInflater inflater;

        public InstitucionAdaptor(Context context, int resource, List objects) {
            super(context, resource, objects);
            InstitucionModelList = objects;
            this.resource = resource;
            inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null){
                viewHolder = new ViewHolder();
                convertView =inflater.inflate(resource,null);
                viewHolder.ivinstitucion = (ImageView)convertView.findViewById(R.id.ivInstitucion);
                viewHolder.tvnombre = (TextView)convertView.findViewById(R.id.nombreInstitucion);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder)convertView.getTag();
            }

            final ProgressBar progressBar = (ProgressBar)convertView.findViewById(R.id.progressBar);
            ImageLoader.getInstance().displayImage(InstitucionModelList.get(position).getURL(), viewHolder.ivinstitucion, new ImageLoadingListener() {
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
            viewHolder.tvnombre.setText(InstitucionModelList.get(position).getNombre());

            return convertView;
        }

        class ViewHolder{
            private ImageView ivinstitucion;
            private TextView tvnombre;
        }
    }
}
