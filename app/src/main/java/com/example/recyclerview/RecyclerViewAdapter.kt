package com.example.recyclerview

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerViewAdapter(
    private val context: Context?,
    private val contactArrayList: ArrayList<String>?
): RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_view_item, viewGroup, false)

        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contactArrayList?.size?.div(5) ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.id.text = contactArrayList?.get(position * 5)
        holder.firstName.text = contactArrayList?.get(1 +  position * 5)
        holder.secondName.text = contactArrayList?.get( 2 + position * 5)
        holder.phone.text = contactArrayList?.get(3 + position * 5)
        downloadImage(requireNotNull(context), holder.imageView, contactArrayList?.get(4 +  position * 5))
        holder.viewGroup.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }

    class RecyclerViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val viewGroup: LinearLayout = view.findViewById(R.id.first_item)
        val id: TextView = view.findViewById(R.id.item_number)
        val imageView: ImageView = view.findViewById(R.id.item_image_view)
        val firstName: TextView = view.findViewById(R.id.item_first_name)
        val secondName: TextView = view.findViewById(R.id.item_second_name)
        val phone: TextView = view.findViewById(R.id.item_phone)
    }

    private fun downloadImage(context: Context, imageView: ImageView, url: String?){
        try {
            Glide
                .with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.error)
                .into(imageView)
        }catch (e:Exception){
            Log.d(context.getString(R.string.logtag), context.getString(R.string.miss_load) + e)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}