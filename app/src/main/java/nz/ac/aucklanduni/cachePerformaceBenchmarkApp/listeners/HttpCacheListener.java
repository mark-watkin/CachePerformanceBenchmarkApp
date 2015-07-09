package nz.ac.aucklanduni.cachePerformaceBenchmarkApp.listeners;

import android.content.Context;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import nz.ac.aucklanduni.cachePerformaceBenchmarkApp.activities.MainActivity;
import nz.ac.aucklanduni.cachePerformaceBenchmarkApp.utils.HttpRequestor;

public class HttpCacheListener implements View.OnClickListener {

    private final Context context;

    public HttpCacheListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                ((MainActivity) context).installCache();
                HttpRequestor.getCacheResponse();

                long t1 = System.nanoTime();
                HttpRequestor.getCacheResponse();
                long t2 = System.nanoTime();

                try {
                    HttpResponseCache.getInstalled().delete();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.i("CachePerformance", "Http cache time: " + (t2 - t1));
                return null;
            }
        }.execute();
    }
}
