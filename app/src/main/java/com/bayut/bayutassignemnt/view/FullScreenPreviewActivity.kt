package com.bayut.bayutassignemnt.view

import android.os.Bundle
import android.util.Log

import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bayut.bayutassignemnt.R
import com.bayut.bayutassignemnt.databinding.ActivityFullScreenPreviewBinding
import com.bayut.bayutassignemnt.model.Result
import com.bayut.bayutassignemnt.utils.GlideApp
import com.google.gson.Gson


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class FullScreenPreviewActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var resultReceiver : Result

    lateinit var binding : ActivityFullScreenPreviewBinding

    lateinit var loadPhotoGlideModule: GlideApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFullScreenPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        binding.closeBtn.setOnClickListener(this)

        if (intent!=null) {


            resultReceiver =
                (intent.getParcelableExtra(MainActivity::class.java.simpleName))!!
        }
        loadPhotoGlideModule = GlideApp(this)

        init()


    }



    fun init () {
        binding.txtName.text = "Name : "+resultReceiver?.name
        binding.txtPrice.text =  "Price : "+resultReceiver?.price

        resultReceiver?.imageUrls?.get(0)?.let { loadPhotoGlideModule.loadImageFromPath(it,binding.image) }
    }




    override fun onClick(p0: View?) {
        if (p0 == binding.closeBtn) {
            finish()
        }
    }
}