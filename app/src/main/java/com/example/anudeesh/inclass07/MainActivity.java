package com.example.anudeesh.inclass07;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ListView myView;
    EditText key;
    static int count=0;
    int flag =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myView = (ListView) findViewById(R.id.listView1);
        key = (EditText) findViewById(R.id.editTextSearch);
        if(isConnectedOnline()) {
            new GetStoriesAsyncTask(this).execute("https://itunes.apple.com/us/rss/toppodcasts/limit=30/xml");
        } else {
            Toast.makeText(MainActivity.this,"Cannot access internet",Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isConnectedOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void onTaskCompleted(final ArrayList<Story> slist) {
        final ArrayList<Story> finalList = new ArrayList<Story>();
        Collections.sort(slist);
        for (Story s:slist) {
            finalList.add(s);
        }
        final StoryAdapter adapter = new StoryAdapter(MainActivity.this,R.layout.row_item_layout,slist);
        myView.setAdapter(adapter);
        adapter.setNotifyOnChange(true);
        //final ListView list = (ListView) findViewById(R.id.listView1);
        //final int count =0;


        findViewById(R.id.buttonGo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(key.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,"Enter some search key",Toast.LENGTH_SHORT).show();
                } else {
                    count=0;
                    flag = 1;
                    String entered = key.getText().toString();
                    for (int i=0;i<slist.size();i++) {
                        if(slist.get(i).getTitle().toLowerCase().contains(entered.toLowerCase())) {
                            Story st = slist.get(i);
                            slist.remove(i);
                            slist.add(0,st);
                            count++;
                        }
                    }

                    final StoryAdapter adapter = new StoryAdapter(MainActivity.this,R.layout.row_item_layout,slist);
                    myView.setAdapter(adapter);
                    adapter.setNotifyOnChange(true);
                }
            }
        });

        findViewById(R.id.buttonClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                key.setText("");
                count=0;
                final StoryAdapter adapter = new StoryAdapter(MainActivity.this,R.layout.row_item_layout,finalList);
                myView.setAdapter(adapter);
            }
        });

        myView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,ItemDetails.class);
                if(flag==0) {
                    intent.putExtra("story",finalList.get(position));
                } else {
                    intent.putExtra("story",slist.get(position));
                }
                startActivity(intent);
            }
        });
    }
}
