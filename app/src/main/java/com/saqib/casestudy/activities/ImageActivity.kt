package com.saqib.casestudy.activities

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.saqib.casestudy.R
import com.saqib.casestudy.model.Hit
import com.saqib.casestudy.util.DataProvider
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.activity_image.*
import java.io.IOException

class ImageActivity : AppCompatActivity() {
    private var image: Hit? = null
    private var wallpaperManager: WallpaperManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        wallpaperManager = WallpaperManager.getInstance(this)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.image_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_set_wallpaper -> {
                onClickSetWallpaperMenu()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun init() {
        image = DataProvider.hits
        Picasso.get().load(image?.webformatURL).into(imageView)
    }

    private fun onClickSetWallpaperMenu() {
        if (image != null) {
            Picasso.get().load(image?.webformatURL).into(object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
                    try {
                        wallpaperManager?.setBitmap(bitmap)
                        displaySetWallpaperSuccess()
                    } catch (e: IOException) {
                        displaySetWallpaperError()
                    }
                }

                override fun onBitmapFailed(e: Exception, errorDrawable: Drawable) {
                    displaySetWallpaperError()
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable) {}
            })
        }
    }

    private fun displaySetWallpaperError() {
        Toast.makeText(this, "Unable to set wallpaper.", Toast.LENGTH_SHORT).show()
    }

    private fun displaySetWallpaperSuccess() {
        Toast.makeText(this, "Wallpaper has been changed.", Toast.LENGTH_SHORT).show()
    }
}
