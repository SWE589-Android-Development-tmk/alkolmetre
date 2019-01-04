package com.example.mk0730.alkolmetre.favorite;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mk0730.alkolmetre.R;
import com.example.mk0730.alkolmetre.alcohol.ListItemClickListener;
import com.example.mk0730.alkolmetre.alcohol.AlcoholViewHolder;
import com.example.mk0730.alkolmetre.lcbo.LcboApiResponseResult;

public class FavoriteAdapter extends RecyclerView.Adapter<AlcoholViewHolder> {

    private static Cursor cursor;
    private ListItemClickListener favoriteOnClickListener;
    private int totalItemCount;
    private Context context;

    public FavoriteAdapter(ListItemClickListener favoriteOnClickListener, Context context) {
        this.favoriteOnClickListener = favoriteOnClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public AlcoholViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutId = R.layout.alcohol_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutId, viewGroup, false);
        AlcoholViewHolder viewHolder = new AlcoholViewHolder(view, context, favoriteOnClickListener);
        return viewHolder;
    }

    private static LcboApiResponseResult getLcboApiResponseResult(int index) {
        cursor.moveToPosition(index);

        int apiId = cursor.getInt(0);
        String imageUrl = cursor.getString(1);
        String name = cursor.getString(2);
        String origin = cursor.getString(3);
        Integer alcoholContent = cursor.getInt(4);
        String sugarContent = cursor.getString(5);
        Integer releaseDate = cursor.getInt(6);
        String tastingNote = cursor.getString(7);

        LcboApiResponseResult result = new LcboApiResponseResult();
        result.setId(apiId);
        result.setName(name);
        result.setOrigin(origin);
        result.setImageUrl(imageUrl);
        result.setFavorite(true);
        result.setAlcoholContent(alcoholContent);
        result.setSugarContent(sugarContent);
        result.setReleasedOn(releaseDate);
        result.setTastingNote(tastingNote);

        return result;
    }

    @Override
    public void onBindViewHolder(@NonNull AlcoholViewHolder alcoholViewHolder, int i) {
        if (cursor == null || cursor.isClosed()){
            return;
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

    public void swapCursor(Cursor newCursor, int totalItemCount) {
        cursor = newCursor;
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
