package alfianyusufabdullah.paging.screen

import alfianyusufabdullah.paging.data.pagesource.MainPageDataSource
import alfianyusufabdullah.paging.data.pagesource.MainPageDataSourceFactory
import alfianyusufabdullah.paging.data.source.MainDataSource
import alfianyusufabdullah.paging.entity.MovieItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import java.util.concurrent.Executors

class MainViewModel(mainDataSource: MainDataSource) : ViewModel() {

    private var movies: LiveData<PagedList<MovieItem>>? = null
    private var loadingState: LiveData<Boolean>? = null
    private var loadMoreLoadingState: LiveData<Boolean>? = null

    init {
        val mainPageDataSource = MainPageDataSource(mainDataSource)
        val factory = MainPageDataSourceFactory(mainPageDataSource)

        val dataBuilder = LivePagedListBuilder<Int, MovieItem>(factory, createConfig())
        movies = dataBuilder.setFetchExecutor(Executors.newFixedThreadPool(5)).build()

        loadingState = mainPageDataSource.getLoadingState()
        loadMoreLoadingState = mainPageDataSource.getLoadMoreLoadingState()
    }

    private fun createConfig(): PagedList.Config {
        val configBuilder = PagedList.Config.Builder()
        return configBuilder
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(1)
            .setPrefetchDistance(10)
            .setPageSize(10)
            .build()
    }

    fun getNowPlayingMovies() = movies

    fun getLoadingState() = loadingState

    fun getLoadMoreLoadingState() = loadMoreLoadingState
}