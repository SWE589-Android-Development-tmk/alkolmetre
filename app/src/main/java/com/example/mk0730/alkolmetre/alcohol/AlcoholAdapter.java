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
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.ArrayList;
import java.util.List;

public class AlcoholAdapter extends RecyclerView.Adapter<AlcoholViewHolder> {

    private static List<LcboApiResponseResult> alcohols = new ArrayList<>();
    private ListItemClickListener onClickListener;
    private int totalItemCount;
    private OnBottomReachedListener onBottomReachedListener;
    private Boolean isFinalPage = false;

    public AlcoholAdapter(ListItemClickListener onClickListener,
                          OnBottomReachedListener onBottomReachedListener) {
        this.onClickListener = onClickListener;
        this.onBottomReachedListener = onBottomReachedListener;
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
        if (i == alcohols.size() - 1 && !isFinalPage){
            onBottomReachedListener.onBottomReached();
        }
        alcoholViewHolder.bind(alcohols.get(i));
    }

    @Override
    public int getItemCount() {
        return alcohols.size();
    }

    public static LcboApiResponseResult getItem(int index){
        return alcohols.get(index);
    }

    public void setAlcohols(LcboApiResponse response) {
        if (response == null){
            alcohols.clear();
        }
        else {
            alcohols = response.getResult();
        }
        this.totalItemCount = response.getPager().getTotalRecordCount();
        this.isFinalPage = response.getPager().getIsFinalPage();

        notifyDataSetChanged();
    }

    public int getTotalItemCount() {
        return totalItemCount;
    }

    public void clear(){
        alcohols.clear();
        notifyDataSetChanged();
    }
}
