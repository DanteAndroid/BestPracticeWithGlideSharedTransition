package pl.droidsonrioids.glidesharedtransition

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

const val IMAGE_URL_KEY = "url"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        images.apply {
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            adapter = ImageAdapter(photos(), ::goToDetails)
        }
    }

    fun goToDetails(url: String, imageView: View) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageView, imageView.transitionName).toBundle()
        Intent(this, DetailActivity::class.java)
                .putExtra(IMAGE_URL_KEY, url)
                .let {
                    startActivity(it, options)
                }
    }

    private fun photos() = (169..216)
            .map { "https://picsum.photos/1000/700?image=$it" }
}