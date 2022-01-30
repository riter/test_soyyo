package com.desarrollandoapp.testsoyyo.ui.picture_detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.desarrollandoapp.testsoyyo.R
import com.desarrollandoapp.testsoyyo.data.entity.AstronomyPicture

class PictureDetailActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var previewImage: ImageView
    private lateinit var titleText: EditText
    private lateinit var dateText: EditText
    private lateinit var explanationText: EditText
    private lateinit var copyrightText: EditText

    companion object {
        private const val EXTRA_TITLE = "extra_title"
        private const val EXTRA_THUMBNAIL_URL = "extra_thumbnail"
        private const val EXTRA_DATE = "extra_date"
        private const val EXTRA_EXPLANATION = "extra_explanation"
        private const val EXTRA_COPYRIGHT = "extra_copyright"

        fun newInstance(context: Context, picture: AstronomyPicture) : Intent {
            val intent = Intent(context, PictureDetailActivity::class.java)
            intent.apply {
                putExtra(EXTRA_TITLE, picture.title)
                putExtra(EXTRA_THUMBNAIL_URL, picture.url)
                putExtra(EXTRA_DATE, picture.date)
                putExtra(EXTRA_EXPLANATION, picture.explanation)
                putExtra(EXTRA_COPYRIGHT, picture.copyright)
            }
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_detail)

        setupViews()
        showPicture()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupViews() {
        toolbar = findViewById(R.id.toolbar)
        previewImage = findViewById(R.id.image)
        titleText = findViewById(R.id.text_title)
        dateText = findViewById(R.id.text_date)
        explanationText = findViewById(R.id.text_explanation)
        copyrightText = findViewById(R.id.text_copyright)
    }

    private fun showPicture() {
        Glide
            .with(this)
            .load(intent.getStringExtra(EXTRA_THUMBNAIL_URL))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(previewImage)

        titleText.setText(intent.getStringExtra(EXTRA_TITLE))
        dateText.setText(intent.getStringExtra(EXTRA_DATE))
        explanationText.setText(intent.getStringExtra(EXTRA_EXPLANATION))
        copyrightText.setText(intent.getStringExtra(EXTRA_COPYRIGHT))

        toolbar.title = intent.getStringExtra(EXTRA_TITLE)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }
    }
}