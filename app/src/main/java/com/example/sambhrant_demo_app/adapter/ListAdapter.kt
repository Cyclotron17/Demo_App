package com.example.sambhrant_demo_app.adapter

import android.widget.Adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sambhrant_demo_app.R
import com.example.sambhrant_demo_app.model.Listing
import com.example.sambhrant_demo_app.model.Result
import com.example.sambhrant_demo_app.repository.ListRepository

class ListAdapter(val context: Context, val lists: List<Result>) : RecyclerView.Adapter<ListAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var name=itemView.findViewById<TextView>(R.id.listingTitle)
        var image=itemView.findViewById<ImageView>(R.id.listingImage)
        var imgurl=itemView.findViewById<ImageView>(R.id.thumbImage)
        var price=itemView.findViewById<TextView>(R.id.listingPrice)
        var dateAdded=itemView.findViewById<TextView>(R.id.listingDate)
        var currency=itemView.findViewById<TextView>(R.id.listingPrice)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {


        val view= LayoutInflater.from(context).inflate(R.layout.adapter_list,parent,false)

        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val list = lists[position]
        holder.name.text = list.name
        holder.currency.text=list.price
        holder.dateAdded.text=list.createdAt
        holder.price.text=list.price
        Glide.with(context)
            .load(list.imageUrlsThumbnails) // Replace with the URL of the image
            .centerCrop()

            .into(holder.image) // Replace with the ImageView you want to bind the image to
        Glide.with(context).load(list.imageUrlsThumbnails[0])
    }

    override fun getItemCount(): Int {
        return lists.size
    }


}