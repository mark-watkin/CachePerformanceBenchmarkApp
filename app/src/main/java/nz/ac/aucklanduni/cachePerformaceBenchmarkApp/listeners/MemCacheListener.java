package nz.ac.aucklanduni.cachePerformaceBenchmarkApp.listeners;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import nz.ac.aucklanduni.cachePerformaceBenchmarkApp.model.Properties;
import nz.ac.aucklanduni.cachePerformaceBenchmarkApp.utils.ImageLruCache;
import nz.ac.aucklanduni.cachePerformaceBenchmarkApp.utils.S3ImageAdapter;

public class MemCacheListener implements View.OnClickListener {
    private final Context context;
    private final ImageLruCache cache;

    public MemCacheListener(Context context) {
        this.context = context;
        this.cache = ImageLruCache.getInstance(context);
    }

    @Override
    public void onClick(View v) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                MemCacheListener.this.cache.clearMemCache();
                Properties properties = Properties.getInstance(context);

                cache.addBitmapToMemoryCache("test", S3ImageAdapter.getImage(properties));

                long t1 = System.nanoTime();
                cache.getBitmapFromMemoryCache("test");
                long t2 = System.nanoTime();

                Log.i("CachePerformance", "Memory cache image time: " + (t2 - t1));

                MemCacheListener.this.cache.clearMemCache();
                return null;
            }
        }.execute();
    }
}
