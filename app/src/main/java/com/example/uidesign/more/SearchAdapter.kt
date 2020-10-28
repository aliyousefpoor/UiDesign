package com.example.uidesign.more

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uidesign.R

class SearchAdapter(private var arrayList: MutableList<SearchModel>, private var context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.search_adapter, parent, false)
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val searchViewHolder: SearchViewHolder = holder as SearchViewHolder

        searchViewHolder.onBind(arrayList[position], context)
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var searchTitle: TextView = itemView.findViewById(R.id.title)
        var searchYear: TextView = itemView.findViewById(R.id.year)
        var avatar: ImageView = itemView.findViewById(R.id.avatar)

        fun onBind(
            arrayList: SearchModel,
            context: Context
        ) {
            searchTitle.text = arrayList.title
            searchYear.text = arrayList.releaseYear.toString()
            Glide.with(context).load(arrayList.image).into(avatar)
        }
    }

    fun updateList(list: MutableList<SearchModel>) {
        arrayList = list
        notifyDataSetChanged()
    }
}