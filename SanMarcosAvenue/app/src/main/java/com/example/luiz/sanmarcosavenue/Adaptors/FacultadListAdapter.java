package com.example.luiz.sanmarcosavenue.Adaptors;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luiz.sanmarcosavenue.Fragments.Facultad;
import com.example.luiz.sanmarcosavenue.Model.FacultadModel;
import com.example.luiz.sanmarcosavenue.R;

import java.util.List;

/**
 * Created by luiz on 7/15/2016.
 */

public class FacultadListAdapter extends BaseAdapter {
    private Context mConext;
    private List<FacultadModel> mFacultadList;

    public FacultadListAdapter(Context mConext, List<FacultadModel> mFacultadList) {
        this.mConext = mConext;
        this.mFacultadList = mFacultadList;
    }

    @Override
    public int getCount() {
        return mFacultadList.size();
    }

    @Override
    public Object getItem(int position) {
        return mFacultadList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mConext, R.layout.fila_facultad, null);
        TextView nombreFacultad = (TextView) v.findViewById(R.id.tvFacultadNombre);
        TextView nombreAutoridad = (TextView) v.findViewById(R.id.tvFacultadAutoridad);
        ImageView facultadView = (ImageView) v.findViewById(R.id.ivFacultad);

        nombreFacultad.setText(mFacultadList.get(position).getNombre());
        nombreAutoridad.setText(mFacultadList.get(position).getAutoridad());

        return null;
    }
}
