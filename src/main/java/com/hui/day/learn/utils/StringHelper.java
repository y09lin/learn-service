package com.hui.day.learn.utils;

/**
 * @author huim_lin
 * */
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

    public static Long object2Long(Object object){
        if (object == null){
            return null;
        }
        return Long.parseLong(""+object.toString());
    }

    public static Integer object2Integer(Object object){
        if (object == null){
            return null;
        }
        return Integer.parseInt(""+object.toString());
    }

    public static String object2String(Object object){
        if (object == null){
            return "";
        }
        return object.toString();
    }
}
