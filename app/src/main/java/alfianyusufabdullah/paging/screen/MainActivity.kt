package alfianyusufabdullah.paging.screen

import alfianyusufabdullah.paging.R
import alfianyusufabdullah.paging.entity.MovieItem
import alfianyusufabdullah.paging.gone
import alfianyusufabdullah.paging.visible
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel by inject<MainViewModel>()
    private val mainAdapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        user_list.hasFixedSize()
        user_list.layoutManager = LinearLayoutManager(this)
        user_list.adapter = mainAdapter

        viewModel.getNowPlayingMovies()?.observe(this, observerUser)
        viewModel.getLoadingState()?.observe(this, observerLoadingState)
        viewModel.getLoadMoreLoadingState()?.observe(this, observerLoadModeLoadingState)
    }

    private val observerUser = Observer<PagedList<MovieItem>> {
        mainAdapter.submitList(it)
    }

    private val observerLoadingState = Observer<Boolean> {
        if (it) pb_loading.visible() else pb_loading.gone()
    }

    private val observerLoadModeLoadingState = Observer<Boolean> {
        if (it) tv_loadmore_message.visible() else tv_loadmore_message.gone()
    }
}