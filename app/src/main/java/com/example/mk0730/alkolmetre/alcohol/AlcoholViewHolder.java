package com.example.mk0730.alkolmetre.alcohol;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mk0730.alkolmetre.R;
import com.example.mk0730.alkolmetre.lcbo.LcboApiResponseResult;
import com.example.mk0730.alkolmetre.tasks.DownloadImageTask;
import com.example.mk0730.alkolmetre.data.FavoriteContract.FavoriteEntry;
import com.example.mk0730.alkolmetre.data.FavoriteContract.FavoriteDetailEntry;

public class AlcoholViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {
    private Integer id;
    private ImageView img;
    private ImageView favoriteImage;
    private TextView txtName;
    private TextView txtOrigin;
    private LcboApiResponseResult viewHolderItem;

    private ListItemClickListener listener;

    private ContentResolver contentResolver;

    public AlcoholViewHolder(@NonNull View itemView, ContentResolver contentResolver, ListItemClickListener listener) {
        super(itemView);

        this.contentResolver = contentResolver;

        img = itemView.findViewById(R.id.img_alcohol_list_item);
        txtName = itemView.findViewById(R.id.txt_alcohol_list_item_name);
        txtOrigin = itemView.findViewById(R.id.txt_alcohol_list_item_origin);
        favoriteImage = itemView.findViewById(R.id.img_favorite_status);
        favoriteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolderItem.setFavorite(!viewHolderItem.getFavorite());
                setFavoriteIcon();
                updateFavoriteInDb();
            }
        });

        this.listener = listener;

        itemView.setOnClickListener(this);
    }

    private void setFavoriteIcon(){
        if (viewHolderItem.getFavorite()) {
            this.favoriteImage.setImageResource(R.mipmap.favorite_img);
        } else {
            this.favoriteImage.setImageResource(R.mipmap.favorite_normal_img);
        }
    }

    private void updateFavoriteInDb(){
        if (viewHolderItem.getFavorite()) {
            insertFavorite();
        } else {
            deleteFavorite();
        }
    }

    private void insertFavorite() {
        ContentValues values = new ContentValues();
        values.put(FavoriteEntry.COLUMN_API_ID, viewHolderItem.getId());
        values.put(FavoriteEntry.COLUMN_IMAGE_URL, viewHolderItem.getImageUrl());
        values.put(FavoriteEntry.COLUMN_NAME, viewHolderItem.getName());
        values.put(FavoriteEntry.COLUMN_ORIGIN, viewHolderItem.getOrigin());

        Uri insertUri = contentResolver.insert(FavoriteEntry.CONTENT_URI, values);
        viewHolderItem.setFavoriteId(Integer.parseInt(insertUri.getPathSegments().get(1)));

        ContentValues values_details = new ContentValues();
        values_details.put(FavoriteDetailEntry.COLUMN_FAVORITE_ID, viewHolderItem.getFavoriteId());
        values_details.put(FavoriteDetailEntry.COLUMN_ALCOHOL_CONTENT, viewHolderItem.getAlcoholContent());
        values_details.put(FavoriteDetailEntry.COLUMN_RELEASE_DATE, viewHolderItem.getReleasedOn() == null
                ? null
                : viewHolderItem.getReleasedOn().toString());
        values_details.put(FavoriteDetailEntry.COLUMN_SUGAR_CONTENT, viewHolderItem.getSugarContent() == null
                ? null
                : viewHolderItem.getSugarContent().toString());
        values_details.put(FavoriteDetailEntry.COLUMN_TASTING_NOTE, viewHolderItem.getTastingNote());

        contentResolver.insert(FavoriteDetailEntry.CONTENT_URI, values_details);
    }

    private void deleteFavorite() {
        contentResolver.delete(FavoriteDetailEntry.buildFavoriteWithFavoriteId(viewHolderItem.getFavoriteId()),
                null, null);
        contentResolver.delete(FavoriteEntry.buildFavoriteWithAPIId(viewHolderItem.getId()),
                null, null);
    }

    public void bind(LcboApiResponseResult alcohol) {
        viewHolderItem = alcohol;

        this.id = viewHolderItem.getId();
        this.txtName.setText(viewHolderItem.getName());
        this.txtOrigin.setText(viewHolderItem.getOrigin());

        setFavoriteIcon();

        new DownloadImageTask(this.img).execute(alcohol.getImageUrl());
    }

    @Override
    public void onClick(View view) {
        int clickedPosition = getAdapterPosition();
        this.listener.onListItemClick(clickedPosition);
    }
}
