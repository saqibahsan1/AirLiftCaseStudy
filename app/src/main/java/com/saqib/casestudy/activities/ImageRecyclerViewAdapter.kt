package com.saqib.casestudy.activities

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saqib.casestudy.R
import com.saqib.casestudy.model.Hit
import com.saqib.casestudy.util.DataProvider
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_image.view.*
import java.util.*


class ImageRecyclerViewAdapter(var context: Context) :
    RecyclerView.Adapter<ImageRecyclerViewAdapter.CustomViewHolder?>() {

    private val imageList: MutableList<Hit>?
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.row_image, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val image: Hit = imageList!![position]
        with(holder.itemView) {
            Picasso.get().load(image.webformatURL)
                .error(android.R.drawable.picture_frame)
                .placeholder(R.drawable.ic_baseline_image_24)
                .into(picture)
        }
    }

     fun clear() {
        if (imageList!!.size > 0) {
            imageList.clear()
            notifyDataSetChanged()
        }
    }

    fun addAll(imageList: List<Hit>?) {
        this.imageList!!.addAll(imageList!!)
        notifyDataSetChanged()
    }

    @Suppress("DEPRECATION")
    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        override fun onClick(view: View) {
            val position: Int = adapterPosition
            val image: Hit = imageList!![position]
            DataProvider.hits = image
            context.startActivity(Intent(context,ImageActivity::class.java))
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

    init {
        imageList = ArrayList<Hit>()
    }

    override fun getItemCount(): Int {
        return imageList?.size ?: 0
    }
}
