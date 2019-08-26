package alfianyusufabdullah.paging

import alfianyusufabdullah.paging.data.source.MainDataSource
import alfianyusufabdullah.paging.entity.MovieItem
import alfianyusufabdullah.paging.screen.MainViewModel
import android.app.Application
import android.view.View
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }
    }
}

val appModule = module {
    single { MainDataSource() }

    viewModel { MainViewModel(get()) }
}

val List<MovieItem>.filterData: List<MovieItem>
    get() {
        return this.filter {
            (it.posterPath?.isNotEmpty() == true)
        }
    }

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}