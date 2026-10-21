package com.example.practicalfive;

import android.app.Activity;
import android.os.Bundle;
import android.content.res.ColorStateList;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

public class Municipality extends Activity {
    private String selectedName;
    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        selectedName = new TextProcessor().processMunicipality(
                getIntent().getStringExtra(MainActivity.EXTRA_MUNICIPALITY));
        if (selectedName == null) {
            Toast.makeText(this, R.string.invalid_extra, Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        setContentView(R.layout.activity_municipality);
        WindowInsetsHelper.apply(this);
        int index, colourId, imageId;
        switch (selectedName) {
            case "Beijing": index = 0; colourId = R.color.municipality_beijing; imageId = R.drawable.beijing; break;
            case "Shanghai": index = 1; colourId = R.color.municipality_shanghai; imageId = R.drawable.shanghai; break;
            case "Chongqing": index = 2; colourId = R.color.municipality_chongqing; imageId = R.drawable.chongqing; break;
            case "Tianjin": index = 3; colourId = R.color.municipality_tianjin; imageId = R.drawable.tianjin; break;
            default: finish(); return;
        }
        int colour = getColor(colourId);
        int foreground = getColor(selectedName.equals("Chongqing")
                ? R.color.color_text_dark : R.color.color_text_light);
        TextView title = findViewById(R.id.municipality_name);
        title.setText(selectedName);
        title.setBackgroundColor(colour);
        title.setTextColor(foreground);
        TextView information = findViewById(R.id.municipality_information);
        information.setText(getResources().getStringArray(R.array.municipalities_array)[index]);
        information.setTextColor(colour);
        ImageView image = findViewById(R.id.municipality_image);
        image.setImageResource(imageId);
        image.setContentDescription(getString(R.string.city_image_description, selectedName));
        Button visit = findViewById(R.id.visit_site);
        visit.setBackgroundTintList(ColorStateList.valueOf(colour));
        visit.setTextColor(foreground);
        findViewById(R.id.back_button).setOnClickListener(view -> finish());
    }
}
