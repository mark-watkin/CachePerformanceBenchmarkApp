package nz.ac.aucklanduni.cachePerformaceBenchmarkApp.activities;

import android.net.http.HttpResponseCache;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

import nz.ac.aucklanduni.cachePerformaceBenchmarkApp.R;
import nz.ac.aucklanduni.cachePerformaceBenchmarkApp.listeners.DiskCacheImgListener;
import nz.ac.aucklanduni.cachePerformaceBenchmarkApp.listeners.HttpCacheListener;
import nz.ac.aucklanduni.cachePerformaceBenchmarkApp.listeners.MemCacheListener;
import nz.ac.aucklanduni.cachePerformaceBenchmarkApp.listeners.NoCacheImgListener;
import nz.ac.aucklanduni.cachePerformaceBenchmarkApp.listeners.NoHttpCacheListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button noCacheImgBtn = (Button) findViewById(R.id.noCacheImg);
        noCacheImgBtn.setOnClickListener(new NoCacheImgListener(this));

        Button diskCacheImgBtn = (Button) findViewById(R.id.diskCacheImg);
        diskCacheImgBtn.setOnClickListener(new DiskCacheImgListener(this));

        Button memCacheImgBtn = (Button) findViewById(R.id.memCacheImg);
        memCacheImgBtn.setOnClickListener(new MemCacheListener(this));

        Button httpCacheBtn = (Button) findViewById(R.id.cachehttp);
        httpCacheBtn.setOnClickListener(new HttpCacheListener(this));

        Button noHttpCacheBtn = (Button) findViewById(R.id.noCacheHttp);
        noHttpCacheBtn.setOnClickListener(new NoHttpCacheListener(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void installCache() {
        try {
            File httpCacheDir = new File(this.getCacheDir(), "http");
            long httpCacheSize = 10 * 1024 * 1024; // 10 MiB
            HttpResponseCache.install(httpCacheDir, httpCacheSize);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("CachePerformance", "HTTP response cache installation failed:" + e);
        }
    }
}
