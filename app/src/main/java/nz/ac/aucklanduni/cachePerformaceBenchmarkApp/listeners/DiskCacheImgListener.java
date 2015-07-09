package nz.ac.aucklanduni.cachePerformaceBenchmarkApp.listeners;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import nz.ac.aucklanduni.cachePerformaceBenchmarkApp.model.Properties;
import nz.ac.aucklanduni.cachePerformaceBenchmarkApp.utils.HttpRequestor;
import nz.ac.aucklanduni.cachePerformaceBenchmarkApp.utils.ImageLruCache;
import nz.ac.aucklanduni.cachePerformaceBenchmarkApp.utils.S3ImageAdapter;

public class DiskCacheImgListener implements View.OnClickListener {
    private final Context context;
    private final ImageLruCache cache;

    public DiskCacheImgListener(Context context) {
        this.context = context;
        this.cache = ImageLruCache.getInstance(context);
    }

    @Override
    public void onClick(View v) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {;
                Properties properties = Properties.getInstance(context);
                cache.addBitmapToDiskCache("test", S3ImageAdapter.getImage(properties));
                cache.flushDiskCache();

                long t1 = System.nanoTime();
                cache.getImageStringFromDiskCache("test");
                long t2 = System.nanoTime();

                Log.i("CachePerformance", "Disk cache image time: " + (t2 - t1));
                return null;
            }
        }.execute();
    }
}
