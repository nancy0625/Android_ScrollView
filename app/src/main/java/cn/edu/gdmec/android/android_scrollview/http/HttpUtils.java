package cn.edu.gdmec.android.android_scrollview.http;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by asus on 2017/12/12.
 */

public class HttpUtils {
    public static String sendPostMethod(String path,String jsonString,String encoding){

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(path).openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-type","application/json");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.connect();
            //将参数发送到服务器接口
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(jsonString.getBytes(encoding));
            outputStream.flush();
            InputStream is = httpURLConnection.getInputStream();

           int response = httpURLConnection.getResponseCode();
            if(response == 200){
              byte[] data = new byte[1024];
                int len =0;
                while ((len = is.read(data)) != -1){
                    bao.write(data,0,len);
                }
                is.close();
            }else{
                Log.i("-->>","失败了");
            }

        }catch (Exception e){
            e.printStackTrace();

        }

        return new String(bao.toByteArray());
    }
}
