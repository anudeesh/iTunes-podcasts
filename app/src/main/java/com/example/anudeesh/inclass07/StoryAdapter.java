package com.example.anudeesh.inclass07;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anudeesh on 10/3/2016.
 */
public class StoryAdapter extends ArrayAdapter<Story> {
    List<Story> mData;
    Context mContext;
    int mResource;

    public StoryAdapter(Context context, int resource, List<Story> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.mData = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource,parent,false);
        }
        Story st = mData.get(position);
        ImageView iv = (ImageView) convertView.findViewById(R.id.imageViewThumb);
        TextView storyTitle = (TextView) convertView.findViewById(R.id.textViewItemTitle);
        storyTitle.setText(st.getTitle());
        Picasso.with(mContext)
                .load(st.getThumbnail())
                .into(iv);
        if(position<MainActivity.count) {
            convertView.setBackgroundColor(Color.GREEN);
        } else {
            convertView.setBackgroundColor(Color.WHITE);
        }
        return convertView;
    }
}
