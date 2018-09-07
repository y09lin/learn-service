package com.hui.day.learn.utils;

import com.hui.day.learn.controller.params.ParagraphParams;
import com.hui.day.learn.controller.params.VoaBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VOAUtils {
    public static VoaBean getVOA(String text){
        VoaBean bean=new VoaBean();
        List<String> tags=new ArrayList<>();
        tags.add("strong");
        tags.add("em");
        for (String tag:tags){
            text=StringHelper.removeTag(text,tag);
        }
        String ptag="p";
        List<ParagraphParams> pList=new ArrayList<>();
        while (text.contains("<p>")){
            String p=StringHelper.getTagContent(text,ptag);
            text=StringHelper.removeTagContent(text,ptag);
            ParagraphParams paragraph=new ParagraphParams();
            String[] sentences=p.split("\\. ");
            paragraph.setSentenceList(Arrays.asList(sentences));
            pList.add(paragraph);
        }
        bean.setPList(pList);
        return bean;
    }
}
