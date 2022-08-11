package com.ovadyah.nestedscrolling.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ovadyah.nestedscrolling.R;
import com.ovadyah.nestedscrolling.bean.DataBean;

import java.util.List;


public class NestedScrollRecyclerViewAdapter extends RecyclerView.Adapter<NestedScrollRecyclerViewAdapter.ViewHoder> {
    public static final String TAG = "hfy+NestedScrollTest";
    private final Context context;
    private final List<DataBean> list;

    public NestedScrollRecyclerViewAdapter(Context context, List<DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_nested_scroll_view, viewGroup, false);
        Log.i(TAG, "onCreateViewHolder: ");
        return new ViewHoder(itemView);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHoder holder) {
        super.onViewAttachedToWindow(holder);
        Log.i(TAG, "onViewAttachedToWindow: "+holder.getAdapterPosition());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder viewHoder, int i) {
        DataBean dataBean = list.get(i);
        viewHoder.textView.setText(dataBean.text);
//        ImageLoader.with(context).loadBitmapAsync(dataBean.url,viewHoder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {

        private final TextView textView;
        private final ImageView imageView;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
