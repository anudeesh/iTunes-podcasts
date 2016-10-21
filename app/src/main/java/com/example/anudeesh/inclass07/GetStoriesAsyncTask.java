package com.example.anudeesh.inclass07;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Anudeesh on 10/3/2016.
 */
public class GetStoriesAsyncTask extends AsyncTask<String, Void, ArrayList<Story>> {

    MainActivity activity;
    ProgressDialog progressDialog;

    public GetStoriesAsyncTask(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected ArrayList<Story> doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            int statuscode = con.getResponseCode();

            if(statuscode == HttpURLConnection.HTTP_OK) {
                InputStream in = con.getInputStream();
                return StoryUtil.StoryPullParser.parseStories(in);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Loading News ...");
        progressDialog.setMax(100);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(ArrayList<Story> stories) {
        super.onPostExecute(stories);
        progressDialog.dismiss();
        activity.onTaskCompleted(stories);
    }
}
