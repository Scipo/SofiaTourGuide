package com.example.home.sofiatourguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import model.Places;

public class PlaceDetails extends AppCompatActivity {
    Places model;
    ImageView img;
    TextView title;
    TextView schedule;
    TextView price;
    TextView address;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);

        model = (Places) getIntent().getSerializableExtra("place");
        img = findViewById(R.id.imageView2);
        title = findViewById(R.id.title_text_view);
        schedule = findViewById(R.id.schedule_textView);
        price = findViewById(R.id.price_textView);
        address = findViewById(R.id.address_textView);
        description = findViewById(R.id.description_textView);

        img.setImageResource(model.getmImageResourceId());
        title.setText(model.getTitle());
        schedule.setText(model.getSchedule());
        price.setText(String.format("%.2f", model.getPrice()).concat(" leva"));
        address.setText(model.getAddress());
        description.setText(model.getDescription());
    }
}
