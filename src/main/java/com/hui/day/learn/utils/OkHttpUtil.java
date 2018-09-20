package com.hui.day.learn.utils;

import okhttp3.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * @author huim_lin
 * */
public class OkHttpUtil {
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60,TimeUnit.SECONDS)
            .writeTimeout(60,TimeUnit.SECONDS)
            .build();

    public static String requestGet(String url,String name,String value) throws IOException {
        Request.Builder builder=new Request.Builder().addHeader(name,value);
        return requestGet(builder,url);
    }

    private static String requestGet(Request.Builder builder,String url) throws IOException{
        Request request=builder.url(url).build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        return response.body().string();
    }

    private static String requestGet(Request.Builder builder, String url, String token) throws IOException{
        Request request=builder.url(url).addHeader("token", token).build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        return response.body().string();
    }

    public static String requestGet(String url) throws IOException {
        return requestGet(new Request.Builder(), url);
    }

    public static String requestGet(String url, String token) throws IOException {
        return requestGet(new Request.Builder(), url, token);
    }

    public static String requestXmlPost(String url, String postData)throws IOException{
        String mediaType="text/xml; charset=utf-8";
        return requestPost(url,postData,mediaType);
    }
    public static String requestJsonPost(String url,String postData, String token)throws IOException{
        String mediaType="application/json; charset=utf-8";
        return requestPost(url,postData,mediaType, token);
    }


    public static String requestKeyValuePost(String url, Map<String, String> param, String token)throws IOException{
        //String mediaType="application/json; charset=utf-8";
        //MediaType type = MediaType.parse(mediaType);
        //不指定 Content-Type，默认为：application/x-www-form-urlencoded
        RequestBody formBody = buildRequestBody(param);
        Request request = new Request.Builder()
                .url(url).addHeader("token", token)
                .post(formBody)
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        return  response.body().string();
    }

    private static RequestBody buildRequestBody(Map<String, String> param) {
        if (param == null){
            return null;
        }
        Iterator<Map.Entry<String, String>> it = param.entrySet().iterator();
        FormBody.Builder builder = new FormBody.Builder();
        while (it.hasNext()){
            Map.Entry<String, String> entry = it.next();
            builder.add(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

    private static String requestPost(String url,String postData,String mediaType, String token) throws IOException{
        MediaType type = MediaType.parse(mediaType);

        Request request = new Request.Builder()
                .url(url).addHeader("token", token)
                .post(RequestBody.create(type, postData))
                .build();
        //if (token != null && !token.equals("")) {
        //    request.newBuilder().addHeader("token", token);
        //}
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        return  response.body().string();
    }

    public static String requestJsonPost(String url,String postData)throws IOException{
        String mediaType="application/json; charset=utf-8";
        return requestPost(url,postData,mediaType);
    }

    public static String requestJsonPost2(String url,String postData)throws IOException{
        String mediaType="application/json; charset=utf-8";
        return requestPost(url,postData,mediaType);
    }

    private static String requestPost(String url,String postData,String mediaType) throws IOException{
        MediaType type = MediaType.parse(mediaType);

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(type, postData))
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        return  response.body().string();
    }


    public static String requestPostDoUnzip(String url,String postData,String mediaType) throws IOException{
        MediaType type = MediaType.parse(mediaType);
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(type, postData))
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        String hds = response.headers().toString();
        String hdzip = response.header("Content-Encoding");
        if(hdzip!=null && hdzip.trim().equals("zip")) {
            byte[] rbs = response.body().bytes();
            try {
                byte[] resultBytes = decompress(rbs);
                String unzipedString = new String(resultBytes, 0, resultBytes.length, "UTF-8");
                return unzipedString;
            } catch (Exception ex) {
                Logger.getLogger(OkHttpUtil.class.getName()).log(Level.SEVERE, "requestPostDoUnzip:unzip error", ex);
            }
        }
        return  response.body().string();
    }

    public static String requestDelete(String url) throws IOException{
        Request request=new Request.Builder().url(url).delete().build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        return  response.body().string();
    }

    /**
     * 压缩
     *
     * @param data 待压缩数据
     * @return byte[] 压缩后的数据
     */
    public static byte[] compress(byte[] data) {
        byte[] output = new byte[0];

        Deflater compresser = new Deflater();

        compresser.reset();
        compresser.setInput(data);
        compresser.finish();
        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
        try {
            byte[] buf = new byte[1024];
            while (!compresser.finished()) {
                int i = compresser.deflate(buf);
                bos.write(buf, 0, i);
            }
            output = bos.toByteArray();
        } catch (Exception e) {
            output = data;
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        compresser.end();
        return output;
    }

    /**
     * 解压缩
     *
     * @param data 待压缩的数据
     * @return byte[] 解压缩后的数据
     */
    public static byte[] decompress(byte[] data) {
        byte[] output = new byte[0];

        Inflater decompresser = new Inflater();
        decompresser.reset();
        decompresser.setInput(data);

        ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);
        try {
            byte[] buf = new byte[1024];
            while (!decompresser.finished()) {
                int i = decompresser.inflate(buf);
                o.write(buf, 0, i);
            }
            output = o.toByteArray();
        } catch (Exception e) {
            output = data;
            e.printStackTrace();
        } finally {
            try {
                o.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        decompresser.end();
        return output;
    }

    public static void main(String[] args) {
    }
}

