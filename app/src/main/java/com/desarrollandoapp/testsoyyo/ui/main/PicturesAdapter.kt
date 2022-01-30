package com.desarrollandoapp.testsoyyo.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.desarrollandoapp.testsoyyo.R
import com.desarrollandoapp.testsoyyo.data.entity.AstronomyPicture

class PicturesAdapter(var context: Context) : RecyclerView.Adapter<PicturesAdapter.ViewHolder>() {

    var onItemClick: ((AstronomyPicture) -> Unit)? = null
    private var dataList = emptyList<AstronomyPicture>()

    internal fun setDataList(dataList: List<AstronomyPicture>) {
        this.dataList = dataList
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_picture, parent, false)
        val viewHolder = ViewHolder(view)

        viewHolder.itemView.setOnClickListener {
            val pos = viewHolder.adapterPosition
            onItemClick?.invoke(dataList.get(pos))

        }

        return viewHolder;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        Glide
            .with(context)
            .load(data.url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.image)
    }

    override fun getItemCount() = dataList.size
}