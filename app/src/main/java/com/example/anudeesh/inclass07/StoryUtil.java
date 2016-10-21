package com.example.anudeesh.inclass07;

import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Anudeesh on 10/3/2016.
 */
public class StoryUtil {
    static public class StoryPullParser extends DefaultHandler {
        static public ArrayList<Story> parseStories(InputStream in) throws XmlPullParserException, IOException {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in,"UTF-8");
            ArrayList<Story> storiesList = new ArrayList<Story>();
            Story story = null;
            int event = parser.getEventType();

            while(event!=XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.START_TAG :
                        if(parser.getName().equals("entry")) {
                            story = new Story();
                        } else if(parser.getName().equals("title")) {
                            if(story!=null) {
                                story.setTitle(parser.nextText().trim());
                            }
                        } else if(parser.getName().equals("im:releaseDate")) {
                            if(story!=null) {
                                story.setDate(parser.getAttributeValue(null,"label"));
                            }
                        }else if(parser.getName().equals("summary")) {
                            if(story!=null) {
                                story.setSummary(parser.nextText().trim());
                            }
                        } else if(parser.getName().equals("im:image")) {
                            String h = parser.getAttributeValue(null,"height");
                            if(h.equals("55")) {
                                story.setThumbnail(parser.nextText().trim());
                            } else if(h.equals("170")){
                                story.setMainimage(parser.nextText().trim());
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("entry")) {
                            storiesList.add(story);
                            story=null;
                        }
                        break;
                    default:
                        break;
                }
                event = parser.next();
            }
            return storiesList;
        }

    }
}
