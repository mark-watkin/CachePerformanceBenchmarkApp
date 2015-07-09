package nz.ac.aucklanduni.cachePerformaceBenchmarkApp.listeners;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import nz.ac.aucklanduni.cachePerformaceBenchmarkApp.utils.HttpRequestor;

public class NoHttpCacheListener implements View.OnClickListener {
    public NoHttpCacheListener(Context context) {
    }

    @Override
    public void onClick(View v) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                long t1 = System.nanoTime();
                HttpRequestor.getNoCacheResponse();
                long t2 = System.nanoTime();

                Log.i("CachePerformance", "No http cache time: " + (t2 - t1));
                return null;
            }
        }.execute();
    }
}
