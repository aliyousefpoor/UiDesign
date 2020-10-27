package com.example.uidesign.more

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uidesign.PictureModel
import com.example.uidesign.R
import java.util.ArrayList

class FavoriteAdapter(private val context: Context, private var picList: ArrayList<PictureModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.favorite_adapter, parent, false)
        return PictureViewHolder(view)
    }

    override fun getItemCount(): Int {
        return picList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val pictureViewHolder: PictureViewHolder = holder as PictureViewHolder
        pictureViewHolder.onBind(picList[position], context)
    }

    class PictureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardView: CardView = itemView.findViewById(R.id.cardView)
        var imageView: ImageView = itemView.findViewById(R.id.imgView)
        var textView: TextView = itemView.findViewById(R.id.title)

        fun onBind(
            pictureModel: PictureModel,
            context: Context
        ) {
            textView.text = pictureModel.title
            Glide.with(context).load(pictureModel.imageId).into(imageView)
        }
    }
}