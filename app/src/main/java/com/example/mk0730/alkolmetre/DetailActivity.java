package com.example.mk0730.alkolmetre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mk0730.alkolmetre.alcohol.AlcoholAdapter;
import com.example.mk0730.alkolmetre.base.BaseActivity;
import com.example.mk0730.alkolmetre.lcbo.LcboApiResponseResult;
import com.example.mk0730.alkolmetre.tasks.DownloadImageTask;

public class DetailActivity extends BaseActivity  {

    ImageView imgAlcohol;
    TextView txtAlcoholName;
    TextView txtAlcoholOrigin;
    TextView txtAlcoholDescription;
    TextView txtReleaseDate;
    TextView txtSugarContent;
    TextView txtAlcoholPercentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imgAlcohol = (ImageView) findViewById(R.id.img_alcohol);
        txtAlcoholName = (TextView) findViewById(R.id.txt_alcohol_name);
        txtAlcoholOrigin = (TextView) findViewById(R.id.txt_alcohol_origin);
        txtAlcoholDescription = (TextView) findViewById(R.id.txt_alcohol_description);
        txtReleaseDate = (TextView) findViewById(R.id.txt_release_date);
        txtSugarContent = (TextView) findViewById(R.id.txt_sugar_content);
        txtAlcoholPercentage = (TextView) findViewById(R.id.txt_alcohol_percentage);

        Intent intent = getIntent();
        if (intent.hasExtra(Intent.EXTRA_TEXT)) {
            try {
                int clickedIndex = intent.getIntExtra(Intent.EXTRA_TEXT, -1);
                LcboApiResponseResult lcboApiResponseResult = AlcoholAdapter.getItem(clickedIndex);

                /*Load Details*/
                String alcoholContent = getString(R.string.detail_activity_alcohol_percentage)
                        + (lcboApiResponseResult.getAlcoholContent() / 100.0) + "%";

                String releaseDate = getString(R.string.detail_activity_release_date);
                if (lcboApiResponseResult.getReleasedOn() != null) {
                    releaseDate += lcboApiResponseResult.getReleasedOn().toString();
                }

                String sugarContent = getString(R.string.detail_activity_sugar_content);
                if (lcboApiResponseResult.getSugarContent() != null) {
                    sugarContent += lcboApiResponseResult.getSugarContent().toString();
                }

                txtAlcoholName.setText(lcboApiResponseResult.getName());
                txtAlcoholOrigin.setText(lcboApiResponseResult.getOrigin());
                txtAlcoholDescription.setText(lcboApiResponseResult.getTastingNote());
                txtReleaseDate.setText(releaseDate);
                txtSugarContent.setText(sugarContent);
                txtAlcoholPercentage.setText(alcoholContent);

                new DownloadImageTask(this.imgAlcohol).execute(lcboApiResponseResult.getImageUrl());
            } catch (Throwable e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                Log.v("AlcoholActivity", e.getMessage());
            }
        }
    }

}
