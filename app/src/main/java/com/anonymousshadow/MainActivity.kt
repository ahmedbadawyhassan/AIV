package com.anonymousshadow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.opencv.android.OpenCVLoader
import org.opencv.core.Mat
import org.opencv.videoio.VideoCapture
import kotlinx.coroutines.*
import android.widget.ImageView
import android.graphics.Bitmap
import com.anonymousshadow.ObjectDetector

class MainActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private var ipCamUrl = "http://192.169.43.216/stream"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.HackerDarkTheme)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)

        if (OpenCVLoader.initDebug()) {
            startStream()
        }
    }

    private fun startStream() {
        CoroutineScope(Dispatchers.IO).launch {
            val cap = VideoCapture(ipCamUrl)
            val mat = Mat()
            val detector = ObjectDetector(assets)

            while (cap.isOpened) {
                cap.read(mat)
                val bmp = Bitmap.createBitmap(mat.cols(), mat.rows(), Bitmap.Config.ARGB_8888)
                val labeledBmp = detector.detectAndLabel(bmp)
                withContext(Dispatchers.Main) {
                    imageView.setImageBitmap(labeledBmp)
                }
            }
        }
    }
}
