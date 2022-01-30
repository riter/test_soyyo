package com.desarrollandoapp.testsoyyo.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.desarrollandoapp.testsoyyo.R
import com.desarrollandoapp.testsoyyo.data.entity.AstronomyPicture
import com.desarrollandoapp.testsoyyo.data.remote.AstronomyPicturesApiService
import com.desarrollandoapp.testsoyyo.data.remote.AstronomyPicturesRepositoryImpl
import com.desarrollandoapp.testsoyyo.data.retrofit.RetrofitBuilder
import com.desarrollandoapp.testsoyyo.ui.DateManager
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var toolbar: Toolbar
    private lateinit var progress: ProgressBar
    private lateinit var errorView: TextView
    private lateinit var rangeDateTextView: TextView
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: PicturesAdapter

    private lateinit var presenter: MainPresenter

    companion object {
        fun newInstance(context: Context) : Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()

        presenter = MainPresenter(this,
            AstronomyPicturesRepositoryImpl(
                RetrofitBuilder.createServiceWithAuth(AstronomyPicturesApiService::class.java
                )))

        showRangeDateText(DateManager.getLastDayDate(7), DateManager.getCurrentDate())
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    private fun setupViews() {
        toolbar = findViewById(R.id.toolbar)
        errorView = findViewById(R.id.error)
        rangeDateTextView = findViewById(R.id.range_date)
        progress = findViewById(R.id.progress)
        recycler = findViewById(R.id.list)

        toolbar.title = resources.getString(R.string.main_title)
        setSupportActionBar(toolbar)

        rangeDateTextView.setOnClickListener {
            showRangeDateDialogs()
        }

        adapter = PicturesAdapter(applicationContext)
        adapter.onItemClick = {
            presenter.openPictureDetail(it)
        }

        recycler.layoutManager = GridLayoutManager(applicationContext,2)
        recycler.adapter = adapter
    }

    override fun showProgress(active: Boolean) {
        progress.visibility = if (active) View.VISIBLE else View.GONE
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun showItems(items: List<AstronomyPicture>) {
        recycler.visibility = View.VISIBLE
        errorView.visibility = View.GONE

        adapter.setDataList(items)
        adapter.notifyDataSetChanged()
    }

    override fun launchPictureUi(picture: AstronomyPicture) {

    }

    override fun showErrorList() {
        recycler.visibility = View.GONE
        errorView.visibility = View.VISIBLE
    }

    private fun showRangeDateDialogs() {
        DateManager.showRangeDateDialogUi(this@MainActivity) { startDate, endDate ->
            showRangeDateText(startDate, endDate)
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun showRangeDateText(startDate: Calendar, endDate: Calendar) {
        val simpleDateFormat = SimpleDateFormat("dd/MMM")
        rangeDateTextView.text = String.format("%s - %s", simpleDateFormat.format(startDate.time), simpleDateFormat.format(endDate.time))

        showItems(emptyList())
        presenter.loadPictures(DateManager.dateToStringYYYYMMDD(startDate), DateManager.dateToStringYYYYMMDD(endDate))
    }
}