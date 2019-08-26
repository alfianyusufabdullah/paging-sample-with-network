package alfianyusufabdullah.paging.data.route

import alfianyusufabdullah.paging.entity.MainResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("3/movie/now_playing?api_key=a5410b62d253b5a06fd82196cc013dca")
    fun doSearch(
        @Query("page") page: Int = 1
    ): Call<MainResponse>
}