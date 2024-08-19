package com.example.projectakhirvsga

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.projectakhirvsga.databinding.FragmentBeritaBinding

class MyBeritaRecyclerViewAdapter(
    private val values: List<NewsItem>
) : RecyclerView.Adapter<MyBeritaRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentBeritaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.title.text = item.title
        holder.content.text = item.content

        // Load the image from drawable resource
        val imageResource = when (item.pathImage) {
            "drawable/hairdryer" -> R.drawable.hairdryer
            "drawable/nuxe" -> R.drawable.nuxe
            "drawable/babtac" -> R.drawable.babtac
            "drawable/msn" -> R.drawable.msn
            "drawable/elle" -> R.drawable.elle
            "drawable/amazon" -> R.drawable.amazon
            "drawable/fashion" -> R.drawable.fashion
            "drawable/skin" -> R.drawable.skin
            "drawable/hair" -> R.drawable.hair
            "drawable/eye" -> R.drawable.eye
            else -> R.drawable.ic_news // Placeholder or default image
        }
        holder.image.setImageResource(imageResource)

        // Set up click listener
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailNewsActivity::class.java).apply {
                putExtra("EXTRA_TITLE", item.title)
                putExtra("EXTRA_CONTENT", item.content)
                putExtra("EXTRA_IMAGE_PATH", item.pathImage)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentBeritaBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.title
        val content = binding.content
        val image = binding.itemImage

        override fun toString(): String {
            return super.toString() + " '" + content.text + "'"
        }
    }
}