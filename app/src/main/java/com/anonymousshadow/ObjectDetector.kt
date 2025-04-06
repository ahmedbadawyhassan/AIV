package com.anonymousshadow

import android.content.res.AssetManager
import android.graphics.Bitmap
import org.tensorflow.lite.task.vision.detector.ObjectDetector
import org.tensorflow.lite.support.image.TensorImage

class ObjectDetector(assetManager: AssetManager) {
    private val detector = ObjectDetector.createFromFileAndOptions(
        assetManager,
        "mobilenet_v3.tflite.tflite",
        ObjectDetector.ObjectDetectorOptions.builder()
            .setMaxResults(3)
            .setScoreThreshold(0.5f)
            .build()
    )

    fun detectAndLabel(bitmap: Bitmap): Bitmap {
        val image = TensorImage.fromBitmap(bitmap)
        val results = detector.detect(image)
        return bitmap
    }
}
