package fi.aiqbal.iqbalcoroutinenet

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.io.IOException
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = URL("https://placekitten.com/200/300")
        lifecycleScope.launch(Dispatchers.Main) {
            val myImage = async (Dispatchers.IO) { getImage(url) }
            myImage.await()?.let {showImage(it)}
        }
    }


    private suspend fun getImage(url: URL): Bitmap?{
        return try{
            BitmapFactory.decodeStream(url.openStream())
        } catch (e: IOException){
            null
        }

    }
    private fun showImage(serverImage: Bitmap){
        imageView.setImageBitmap((serverImage))
    }
}