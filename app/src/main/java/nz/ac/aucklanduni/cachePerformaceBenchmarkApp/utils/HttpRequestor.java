package nz.ac.aucklanduni.cachePerformaceBenchmarkApp.utils;

import android.net.http.HttpResponseCache;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

public class HttpRequestor {
    public static String getNoCacheResponse() {
        URL url = null;
        StringBuffer sb = new StringBuffer();
        try {
            url = new URL(getUrl());
            URLConnection conn = url.openConnection();
            InputStream in = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while((line = br.readLine()) != null)
                sb.append(line);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String getCacheResponse() {
        URL url = null;
        StringBuffer sb = new StringBuffer();
        try {
            url = new URL(getUrl());
            URLConnection conn = url.openConnection();
            conn.addRequestProperty("Cache-Control", "max-stale=" + 60 * 60);
            conn.setUseCaches(true);
            InputStream in = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while((line = br.readLine()) != null)
                sb.append(line);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String getUrl() {
        return "Url";
    }
}
