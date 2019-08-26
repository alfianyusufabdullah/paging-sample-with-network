package alfianyusufabdullah.paging.data.pagesource

import alfianyusufabdullah.paging.entity.MovieItem
import androidx.paging.DataSource

class MainPageDataSourceFactory (private val  mainPageDataSource: MainPageDataSource) : DataSource.Factory<Int, MovieItem>() {

    override fun create(): DataSource<Int, MovieItem> {
        return mainPageDataSource
    }
}