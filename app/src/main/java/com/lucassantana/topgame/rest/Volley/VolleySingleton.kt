package com.lucassantana.topgame.rest.Volley

import com.android.volley.toolbox.ImageLoader
import com.android.volley.RequestQueue
import android.graphics.Bitmap
import android.util.LruCache
//import javax.swing.UIManager.put
//import sun.awt.AppContext.getAppContext
import com.android.volley.toolbox.Volley

//import android.support.v4.util.LruCache;
import com.lucassantana.MyApplication


/**
 * Created by Lucas Santana on 18/03/2019.
 */

class VolleySingleton private constructor() {
    val requestQueue: RequestQueue
    val imageLoader: ImageLoader

    init {
        requestQueue = Volley.newRequestQueue(MyApplication.appContext)
        imageLoader = ImageLoader(this.requestQueue, object : ImageLoader.ImageCache {
            override fun putBitmap(url: String, bitmap: Bitmap) {
            }

            override fun getBitmap(url: String): Bitmap? {
                return null
            }
        })
    }

    companion object {
        private var mInstance: VolleySingleton? = null

        val instance: VolleySingleton
            get() {
                if (mInstance == null) {
                    mInstance = VolleySingleton()
                }
                return mInstance as VolleySingleton
            }
    }

}