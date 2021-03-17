package com.mgcoco.changethemesample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mgcoco.changetheme.AttributeName;
import com.mgcoco.changetheme.AttributeTypeName;
import com.mgcoco.changetheme.SkinManager;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;

    private ArrayList<ItemSample> data;

    public MyAdapter(Context context, ArrayList<ItemSample> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        SkinManager.getInstance().inflate(inflater);
        View view = inflater.inflate(R.layout.item_sample, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.icon = view.findViewById(R.id.icon);
        holder.text = view.findViewById(R.id.text);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText(data.get(position).text);
        SkinManager.getInstance().putDynamicGroup(holder.icon, AttributeName.ATTRIBUTE_SRC, AttributeTypeName.TYPE_DRAWABLE, data.get(position).icon);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView icon;
        public TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}