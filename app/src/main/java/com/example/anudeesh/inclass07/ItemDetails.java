package com.example.anudeesh.inclass07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ItemDetails extends AppCompatActivity {

    private TextView title, date, summary;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        Story current = (Story) getIntent().getExtras().getSerializable("story");
        title = (TextView) findViewById(R.id.textViewStoryTitle);
        date = (TextView) findViewById(R.id.textViewPubDate);
        summary = (TextView) findViewById(R.id.textViewSummary);
        img = (ImageView) findViewById(R.id.imageView);

        Date date1 = new Date(current.getDate());
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
        String time1 = sdf.format(date1);

        title.setText(current.getTitle());
        date.setText(time1);
        summary.setText(current.getSummary());
        Picasso.with(this)
                .load(current.getMainimage())
                .into(img);

    }
}
