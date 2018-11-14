package com.example.mk0730.alkolmetre.alcohol;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mk0730.alkolmetre.R;
import com.example.mk0730.alkolmetre.lcbo.LcboApiResponse;
import com.example.mk0730.alkolmetre.lcbo.LcboApiResponseResult;

import java.util.ArrayList;
import java.util.List;

public class AlcoholAdapter extends RecyclerView.Adapter<AlcoholViewHolder> {

    private LcboApiResponse response;
    private List<Alcohol> alcohols;
    private ListItemClickListener onClickListener;

    public AlcoholAdapter(ListItemClickListener listener) {
        onClickListener = listener;
    }

    @NonNull
    @Override
    public AlcoholViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutId = R.layout.alcohol_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutId, viewGroup, false);
        AlcoholViewHolder viewHolder = new AlcoholViewHolder(view, onClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlcoholViewHolder alcoholViewHolder, int i) {
        alcoholViewHolder.bind(alcohols.get(i));
    }

    @Override
    public int getItemCount() {
        return alcohols != null ? alcohols.size() : 0;
    }

    public void setAlcohols(LcboApiResponse response) {
        this.response = response;

        alcohols = new ArrayList<>();
        for (LcboApiResponseResult item : response.getResult()) {
            Alcohol alcohol = new Alcohol(item.getId(), item.getImageUrl(), item.getName(), item.getOrigin());
            alcohols.add(alcohol);
        }

        notifyDataSetChanged();
    }
}
