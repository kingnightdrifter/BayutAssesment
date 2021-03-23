package com.bayut.bayutassignemnt.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bayut.bayutassignemnt.R
import com.bayut.bayutassignemnt.app.ConstantValues.Companion.URL
import com.bayut.bayutassignemnt.interfaces.OnGlideListener
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.katch.hi5.utils.interfaces.GlideBitmapListener
import okhttp3.OkHttpClient
import java.io.InputStream
import java.util.concurrent.TimeUnit

class GlideApp(context: Context) : AppGlideModule() {

    private var context: Context? = context

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
        registry.replace(
            GlideUrl::class.java,
            InputStream::class.java, OkHttpUrlLoader.Factory(client)
        )
    }

    fun loadImageFromPath(url: String, imageView: ImageView) {
        context?.let {
            Glide.with(it)
                .load(URL + url)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(imageView)
        }
    }

    fun preLoadSimpleImage(url: String?, onGlideListener: OnGlideListener) {
        Glide.with(context!!)
            .load(url).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .thumbnail(0.1f)
            .error(R.drawable.ic_placeholder)
            .dontAnimate()
            .addListener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<Drawable?>,
                    isFirstResource: Boolean
                ): Boolean {
                    onGlideListener.onLoadFailed()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any,
                    target: Target<Drawable?>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    onGlideListener.onResourceReady()
                    return false
                }
            })
            .preload()
    }

    fun loadBitmapFromPath(url: String, param: GlideBitmapListener<Bitmap>) {
        context?.let {
            Glide.with(it)
                .asBitmap()
                .load(URL + url)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.color.black)
                .error(R.color.purple_200)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        param.onResourceReady(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        TODO("Not yet implemented")
                    }

                })
        }
    }

}