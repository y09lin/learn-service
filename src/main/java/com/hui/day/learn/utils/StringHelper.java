package com.hui.day.learn.utils;

public class StringHelper {

    public static String removeTag(String text,String tag){
        text = text.replaceAll("<"+tag+">","");
        text = text.replaceAll("</"+tag+">","");
        return text;
    }

    public static String getTagContent(String text,String tag){
        int index=text.indexOf("<"+tag+">")+("<"+tag+">").length();
        return text.substring(index,text.indexOf("</"+tag+">"));
    }

    public static String removeTagContent(String text,String tag){
        int index=text.indexOf("</"+tag+">")+("</"+tag+">").length();
        return text.substring(index,text.length());
    }
}
