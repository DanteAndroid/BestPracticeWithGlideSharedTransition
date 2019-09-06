package dante.android.glidesharedtransition

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        images.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = ImageAdapter(FakeData.getData(), ::goToDetails)
        }
    }

    private fun goToDetails(index: Int, imageView: View) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageView, imageView.transitionName).toBundle()
        Intent(this, DetailActivity::class.java)
                .putExtra(ARG_POSITION, index)
                .putParcelableArrayListExtra(ARG_DATA, FakeData.getData())
                .let {
                    startActivity(it, options)
                }
    }

}