package com.example.anudeesh.inclass07;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Anudeesh on 10/3/2016.
 */
public class Story implements Serializable, Comparable<Story>{
    String title, date, summary, thumbnail, mainimage;

    public Story(String title, String date, String summary, String thumbnail, String mainimage) {
        this.title = title;
        this.date = date;
        this.summary = summary;
        this.thumbnail = thumbnail;
        this.mainimage = mainimage;
    }

    public Story() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getMainimage() {
        return mainimage;
    }

    public void setMainimage(String mainimage) {
        this.mainimage = mainimage;
    }

    @Override
    public String toString() {
        return "Story{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", summary='" + summary + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", mainimage='" + mainimage + '\'' +
                '}';
    }

    public int compareTo(Story another) {
        Date thisDate = new Date(this.getDate());
        Date anotherDate = new Date(another.getDate());
        if(thisDate.compareTo(anotherDate)>0) return 1;
        else if(thisDate.compareTo(anotherDate)<0) return -1;
        return 0;
    }

}
