package nz.ac.aucklanduni.cachePerformaceBenchmarkApp.listeners;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import nz.ac.aucklanduni.cachePerformaceBenchmarkApp.model.Properties;
import nz.ac.aucklanduni.cachePerformaceBenchmarkApp.utils.ImageLruCache;
import nz.ac.aucklanduni.cachePerformaceBenchmarkApp.utils.S3ImageAdapter;

public class NoCacheImgListener implements View.OnClickListener {

    private final Context context;

    public NoCacheImgListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                Properties properties = Properties.getInstance(context);

                long t1 = System.nanoTime();
                S3ImageAdapter.getImage(properties);
                long t2 = System.nanoTime();

                Log.i("CachePerformance", "No cache image time: " + (t2 - t1));
                return null;
            }
        }.execute();
    }
}
