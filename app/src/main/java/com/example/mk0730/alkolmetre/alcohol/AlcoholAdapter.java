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

    private List<LcboApiResponseResult> alcohols = new ArrayList<>();
    private ListItemClickListener onClickListener;

//    public AlcoholAdapter(ListItemClickListener listener) {
//        onClickListener = listener;
//    }

    @NonNull
    @Override
    public AlcoholViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutId = R.layout.alcohol_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutId, viewGroup, false);
        //AlcoholViewHolder viewHolder = new AlcoholViewHolder(view, onClickListener);
        AlcoholViewHolder viewHolder = new AlcoholViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlcoholViewHolder alcoholViewHolder, int i) {
        alcoholViewHolder.bind(alcohols.get(i));
    }

    @Override
    public int getItemCount() {
        return alcohols.size();
    }

    public void setAlcohols(LcboApiResponse response) {
        if (response == null){
            alcohols.clear();
        }
        else {
            alcohols = response.getResult();
        }

        notifyDataSetChanged();
    }
}
