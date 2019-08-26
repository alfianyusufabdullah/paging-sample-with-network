package alfianyusufabdullah.paging.entity

import com.google.gson.annotations.SerializedName

data class MainResponse(
    @SerializedName("page") var page: Int = 1,
    @SerializedName("total_pages") val totalPages: Int? = 1,
    @SerializedName("results") val movies: List<MovieItem> = listOf()
)