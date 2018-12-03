package com.example.mk0730.alkolmetre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.example.mk0730.alkolmetre.base.BaseActivity;
import com.example.mk0730.alkolmetre.utils.UrlUtils;

public class SearchInDetailActivity extends BaseActivity {

    Spinner categoriesSpinner;
    ToggleButton isDiscontinuedToggle;
    ToggleButton isVqaToggle;
    ToggleButton priceToggle;
    ToggleButton alcoholContentToggle;
    ToggleButton pricePerLiterToggle;
    Button searchButton;
    EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_in_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        categoriesSpinner = (Spinner) findViewById(R.id.spinner_categories);
        isDiscontinuedToggle = (ToggleButton) findViewById(R.id.toggle_button_is_discontinued);
        isVqaToggle = (ToggleButton) findViewById(R.id.toggle_button_is_vqa);
        priceToggle = (ToggleButton) findViewById(R.id.toggle_button_price);
        alcoholContentToggle = (ToggleButton) findViewById(R.id.toggle_button_alcohol_content);
        pricePerLiterToggle = (ToggleButton) findViewById(R.id.toggle_button_price_per_liter);
        searchButton = (Button) findViewById(R.id.search_in_details_button);
        searchText = (EditText) findViewById(R.id.search);

        isDiscontinuedToggle.setChecked(this.is_discontinued);
        isVqaToggle.setChecked(this.is_vqa);
        priceToggle.setChecked(this.order_price);
        alcoholContentToggle.setChecked(this.order_alcohol_content);
        pricePerLiterToggle.setChecked(this.order_price_per_liter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UrlUtils urlUtils = new UrlUtils();

                /* Set search query text */
                urlUtils = urlUtils.setSearch(searchText.getText().toString());

                /* A category was selected */
                if (categoriesSpinner.getSelectedItemPosition() > 0){
                    urlUtils = urlUtils.setCategory((String)categoriesSpinner.getSelectedItem());
                }

                /* Set the toggle button values */
                urlUtils = urlUtils
                        .setIsDiscontinued(isDiscontinuedToggle.isChecked())
                        .setIsVqa(isVqaToggle.isChecked());

                /* Set ordering selections. */
                urlUtils = urlUtils
                        .setOrderPrice(priceToggle.isChecked())
                        .setOrderAlcoholContent(alcoholContentToggle.isChecked())
                        .setOrderPricePerLiter(pricePerLiterToggle.isChecked());

                String alchoholFilter = urlUtils.build();
                Intent intent = new Intent(SearchInDetailActivity.this,
                        AlcoholListActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, alchoholFilter);
                startActivity(intent);
            }
        });
    }
}
