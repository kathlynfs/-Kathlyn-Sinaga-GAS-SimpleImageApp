package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var newImageButton: Button
    private lateinit var imageDescriptionTextView: TextView
    private lateinit var sharedPreferences: SharedPreferences

    private val images = arrayOf(
        R.drawable.thelittlemermaid,
        R.drawable.thesnowqueen,
        R.drawable.thelittleprince
    )

    private val imageDescriptions = arrayOf(
        R.string.little_mermaid_text_desc,
        R.string.snow_queen_text_desc,
        R.string.little_prince_text_desc
    )

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)

        imageView = findViewById(R.id.image)
        imageDescriptionTextView = findViewById(R.id.imageDescription)
        newImageButton = findViewById(R.id.newImageButton)
        currentIndex = sharedPreferences.getInt("index", 0)

        updateImageAndText()

        newImageButton.setOnClickListener {
            var randomIndex = (0 .. (images.size-1)).random()
            while (randomIndex == currentIndex) {
                randomIndex = (0 .. (images.size-1)).random()
            }
            currentIndex = randomIndex
            updateImageAndText()
        }
    }

    private fun updateImageAndText() {
        imageView.setImageResource(images[currentIndex])
        imageDescriptionTextView.text = getString(imageDescriptions[currentIndex])
    }

    override fun onDestroy() {
        sharedPreferences.edit().putInt("index", currentIndex).apply()
        super.onDestroy()
    }

}