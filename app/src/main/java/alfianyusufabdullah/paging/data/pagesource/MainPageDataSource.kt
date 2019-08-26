package alfianyusufabdullah.paging.data.pagesource

import alfianyusufabdullah.paging.data.source.MainDataSource
import alfianyusufabdullah.paging.data.source.MainDataSourceCallback
import alfianyusufabdullah.paging.entity.MovieItem
import alfianyusufabdullah.paging.entity.MainResponse
import alfianyusufabdullah.paging.filterData
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource

class MainPageDataSource(private val mainDataSource: MainDataSource) :
    PageKeyedDataSource<Int, MovieItem>() {

    private val loadingState = MutableLiveData<Boolean>()
    private val loadMoreLoadingState = MutableLiveData<Boolean>()

    fun getLoadingState() = loadingState
    fun getLoadMoreLoadingState() = loadMoreLoadingState

    override fun loadInitial(
        params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, MovieItem>
    ) {
        loadingState.postValue(true)

        mainDataSource.loadNowPlayingMovie(mainDataSourceCallback = object :
            MainDataSourceCallback {
            override fun onSuccess(mainResponse: MainResponse) {
                callback.onResult(mainResponse.movies.filterData, null, mainResponse.page + 1)
                loadingState.postValue(false)
            }

            override fun onFailed(throwable: Throwable) {
                callback.onResult(mutableListOf(), null, -1)
                loadingState.postValue(false)
            }
        })
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, MovieItem>
    ) {
        if (params.key == -1) return

        loadMoreLoadingState.postValue(true)

        mainDataSource.loadNowPlayingMovie(page = params.key, mainDataSourceCallback = object :
            MainDataSourceCallback {
            override fun onSuccess(mainResponse: MainResponse) {
                callback.onResult(mainResponse.movies.filterData, mainResponse.page + 1)
                loadMoreLoadingState.postValue(false)
            }

            override fun onFailed(throwable: Throwable) {
                callback.onResult(mutableListOf(), params.key + 1)
                loadMoreLoadingState.postValue(false)
            }
        })
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, MovieItem>
    ) {
        // TODO: do something if user scroll to top
    }
}