package com.example.myslash.BreakingAPI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myslash.R;

import java.io.Serializable;
import java.util.List;

public class MyAdapterBreaking extends BaseAdapter implements Serializable {
    private List<BreakingFrases> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private int []imagenes = {R.drawable.editbutton,R.drawable.removebutton};

    public MyAdapterBreaking(List<BreakingFrases> list, Context context) {
        this.list = list;
        this.context = context;
        if( context != null)
        {
            layoutInflater = LayoutInflater.from(context);
        }
    }

    public boolean isEmptyOrNull( ) {
        return list == null || list.size() == 0;
    }

    @Override
    public int getCount() {
        if(isEmptyOrNull())
        {
            return 0;
        }
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        if(isEmptyOrNull())
        {
            return null;
        }
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView1 = null;
        TextView textView2 = null;
        ImageView imageView = null;
        view = layoutInflater.inflate(R.layout.activity_list_view_breaking, null );
        textView1 = view.findViewById(R.id.textViewLVB1);
        textView2 = view.findViewById(R.id.textViewLVB2);
        imageView = view.findViewById(R.id.imageViewLVBUser);
        textView1.setText(list.get(i).getFrase());
        textView2.setText(list.get(i).getAutor());
        imageView.setImageResource(list.get(i).getImagen());
        return view;
    }
}