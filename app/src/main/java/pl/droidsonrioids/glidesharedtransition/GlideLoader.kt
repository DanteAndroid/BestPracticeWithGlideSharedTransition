package pl.droidsonrioids.glidesharedtransition

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

fun ImageView.load(url: String, thumbnail: RequestBuilder<Bitmap>? = null) {
    val requestOptions = RequestOptions.placeholderOf(R.drawable.placeholder)
            .dontTransform()
            .apply {
                if (thumbnail != null) {
                    // load original picture
                    override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                }
            }
    Glide.with(this)
            .asBitmap()
            .load(url)
            .thumbnail(thumbnail)
            .apply(requestOptions)
            .into(this)
}

fun <T> PictureDetailFragment.getDelayedTransitionListener(): RequestListener<T> {
    return object : RequestListener<T> {
        override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<T>?,
                isFirstResource: Boolean
        ): Boolean {
            activity?.supportStartPostponedEnterTransition()
            return false
        }

        override fun onResourceReady(
                resource: T?,
                model: Any?,
                target: Target<T>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
        ): Boolean {
            activity?.supportStartPostponedEnterTransition()
            return false
        }
    }
}