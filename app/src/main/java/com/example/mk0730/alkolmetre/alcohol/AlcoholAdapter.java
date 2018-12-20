package com.example.mk0730.alkolmetre.alcohol;

import android.content.Context;
import android.database.Cursor;
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
import java.util.StringTokenizer;

public class AlcoholAdapter extends RecyclerView.Adapter<AlcoholViewHolder> {

    private static Cursor cursor;
    //private static List<LcboApiResponseResult> alcohols = new ArrayList<>();
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

    private static LcboApiResponseResult getLcboApiResponseResult(int index) {
        cursor.moveToPosition(index);

        long id = cursor.getLong(0);
        int productId = cursor.getInt(1);
        String name = cursor.getString(2);
        String origin = cursor.getString(3);
        String tastingNote = cursor.getString(4);
        Integer date = cursor.getInt(5);
        String sugarContent = cursor.getString(6);
        Integer alcoholContent = cursor.getInt(7);
        Integer isVqa = cursor.getInt(8);
        Integer isDiscontinued = cursor.getInt(9);
        Integer priceInCents = cursor.getInt(10);
        Integer pricePerLiterInCents = cursor.getInt(11);
        String imageUrl = cursor.getString(12);
        //Integer page = cursor.getInt(13)

        LcboApiResponseResult result = new LcboApiResponseResult();
        result.setId(productId);
        result.setName(name);
        result.setOrigin(origin);
        result.setTastingNote(tastingNote);
        result.setReleasedOn(date);
        result.setSugarContent(sugarContent);
        result.setAlcoholContent(alcoholContent);
        result.setIsVqa(isVqa == 1);
        result.setIsDiscontinued(isDiscontinued == 1);
        result.setPriceInCents(priceInCents);
        result.setPricePerLiterInCents(pricePerLiterInCents);
        result.setImageUrl(imageUrl);

        return result;
    }

    @Override
    public void onBindViewHolder(@NonNull AlcoholViewHolder alcoholViewHolder, int i) {
        if (cursor == null || cursor.isClosed()){
            return;
        }
        if (i == cursor.getCount() - 1 && !isFinalPage) {
            onBottomReachedListener.onBottomReached();
        }

        alcoholViewHolder.bind(getLcboApiResponseResult(i));
    }

    @Override
    public int getItemCount() {
        if (cursor == null || cursor.isClosed()) {
            return 0;
        } else {
            return cursor.getCount();
        }
    }

    public static LcboApiResponseResult getItem(int index) {
        return getLcboApiResponseResult(index);
    }

    public void swapCursor(Cursor newCursor, Boolean isFinalPage, int totalItemCount) {
        cursor = newCursor;
        this.isFinalPage = isFinalPage;
        this.totalItemCount = totalItemCount;
        notifyDataSetChanged();
    }


    public int getTotalItemCount() {
        return totalItemCount;
    }

    public void clear() {
        cursor.close();
        notifyDataSetChanged();
    }
}
