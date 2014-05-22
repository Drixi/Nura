package com.example.training;

import java.io.InputStream;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore.Images;
import android.widget.ImageView;

//package com.example.training;
//
//import java.io.InputStream;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.client.methods.HttpGet;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.http.AndroidHttpClient;
//import android.os.AsyncTask;
//import android.util.Log;
//
//public class DownloadImage extends AsyncTask<Void, Void, Bitmap> {
//
//		
//		@Override
//		protected Bitmap doInBackground(Void... params) {
//		
//			Bitmap bitmap = null;
//			bitmap = downloadBitmap("http://188.226.221.153/nurses/2.jpg");
//			
//			
//			return bitmap;
//		}
//		
//		@Override
//		protected void onPostExecute(Bitmap result) {
//		
//			HeaderFragment header = new HeaderFragment();
//			header.profile_img.setImageBitmap(result);
//			
//		
//		}
//		
//		public Bitmap downloadBitmap(String url) {
//		final AndroidHttpClient client = AndroidHttpClient
//		        .newInstance("Android");
//		final HttpGet getRequest = new HttpGet(url);
//		
//		try {
//		    HttpResponse response = client.execute(getRequest);
//		    final int statusCode = response.getStatusLine().getStatusCode();
//		    if (statusCode != HttpStatus.SC_OK) {
//		        Log.w("ImageDownloader", "Error " + statusCode
//		                + " while retrieving bitmap from " + url);
//		        return null;
//		    }
//		
//		    final HttpEntity entity = response.getEntity();
//		    if (entity != null) {
//		        InputStream inputStream = null;
//		        try {
//		            inputStream = entity.getContent();
//		            final Bitmap bitmap = BitmapFactory
//		                    .decodeStream(inputStream);
//		
//		
//		            return bitmap;
//		        } finally {
//		            if (inputStream != null) {
//		                inputStream.close();
//		            }
//		            entity.consumeContent();
//		        }
//		    }
//		} catch (Exception e) {
//		    // Could provide a more explicit error message for IOException
//		    // or
//		    // IllegalStateException
//		    getRequest.abort();
//		    Log.w("ImageDownloader", "Error while retrieving bitmap from "
//		            + url);
//		} finally {
//		    if (client != null) {
//		        client.close();
//		    }
//		}
//		return null;
//		}
//
//}

public class DownloadImage {

    HeaderFragment HeaderFragment = new HeaderFragment();

    public void getimage() {

        try {
            URL url = new URL("http://188.226.221.153/nurses/3.jpg");
            HttpGet httpRequest = null;

            httpRequest = new HttpGet(url.toURI());

            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = (HttpResponse) httpclient
                    .execute(httpRequest);

            HttpEntity entity = response.getEntity();
            BufferedHttpEntity b_entity = new BufferedHttpEntity(entity);
            InputStream input = b_entity.getContent();

            Bitmap bitmap = BitmapFactory.decodeStream(input);

            HeaderFragment.profile_img.setImageBitmap(bitmap);

        } catch (Exception ex) {

        }
    }

    public void rundownload(){
        new Thread(new Runnable() {
            public void run() {
                getimage();
            }
        }).start();
    }

}