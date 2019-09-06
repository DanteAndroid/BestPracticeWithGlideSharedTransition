package pl.droidsonrioids.glidesharedtransition

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

const val IMAGE_URL_KEY = "thumbUrl"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        images.apply {
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            adapter = ImageAdapter(photos(), ::goToDetails)
        }
    }

    fun goToDetails(index: Int, imageView: View) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageView, imageView.transitionName).toBundle()
        Intent(this, DetailActivity::class.java)
                .putExtra(ARG_POSITION, index)
                .putParcelableArrayListExtra(ARG_DATA, photos())
                .let {
                    startActivity(it, options)
                }
    }

    private fun photos(): List<Image> = (169..216)
            .map {
                val url = "https://picsum.photos/1000/700?image=$it"
                Image(url, url)
            }
}