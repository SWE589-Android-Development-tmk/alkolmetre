package com.example.mk0730.alkolmetre.alcohol;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mk0730.alkolmetre.R;
import com.example.mk0730.alkolmetre.tasks.DownloadImageTask;

public class AlcoholViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {
    private Integer id;
    private ImageView img;
    private TextView txtName;
    private TextView txtOrigin;

    private ListItemClickListener listener;

    public AlcoholViewHolder(@NonNull View itemView, ListItemClickListener listener) {
        super(itemView);

        img = itemView.findViewById(R.id.img_alcohol_list_item);
        txtName = itemView.findViewById(R.id.txt_alcohol_list_item_name);
        txtOrigin = itemView.findViewById(R.id.txt_alcohol_list_item_origin);

        this.listener = listener;

        itemView.setOnClickListener(this);
    }

    void bind(Alcohol alcohol) {
        this.id = alcohol.getId();
        this.txtName.setText(alcohol.getName());
        this.txtOrigin.setText(alcohol.getOrigin());

        new DownloadImageTask(this.img).execute(alcohol.getImageUri());
    }

    @Override
    public void onClick(View view) {
        int clickedPosition = getAdapterPosition();
        this.listener.onListItemClick(clickedPosition);
    }
}
