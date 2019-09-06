package pl.droidsonrioids.glidesharedtransition

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ImageAdapter(private val images: List<Image>, private val onClick: (Int, View) -> Unit) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount() = images.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) = holder.bind(images[position])

    inner class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(image: Image) {
            (itemView as ImageView).apply {
                load(image.thumbUrl)
                transitionName = image.thumbUrl
                setOnClickListener { onClick(adapterPosition, it) }
            }
        }
    }

}