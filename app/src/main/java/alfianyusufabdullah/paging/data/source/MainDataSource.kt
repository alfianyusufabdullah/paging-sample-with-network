package alfianyusufabdullah.paging.data.source

import alfianyusufabdullah.paging.data.route.ApiClient
import alfianyusufabdullah.paging.entity.MainResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainDataSource {

    fun loadNowPlayingMovie(page: Int = 1, mainDataSourceCallback: MainDataSourceCallback) {
        val client = ApiClient.client
        client.doSearch(page = page)
            .enqueue(object : Callback<MainResponse> {
                override fun onFailure(call: Call<MainResponse>, t: Throwable) {
                    mainDataSourceCallback.onFailed(t)
                }

                override fun onResponse(
                    call: Call<MainResponse>, response: Response<MainResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()?.movies?.isEmpty() == true) {
                            mainDataSourceCallback.onFailed(Throwable("Empty response"))
                        } else {
                            mainDataSourceCallback.onSuccess(response.body() ?: MainResponse())
                        }
                    } else {
                        mainDataSourceCallback.onFailed(Throwable("Something error with response"))
                    }
                }
            })
    }
}