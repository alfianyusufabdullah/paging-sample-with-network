package alfianyusufabdullah.paging.entity

import com.google.gson.annotations.SerializedName

data class MovieItem(
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("original_title") val originalTitle: String? = null,
    @SerializedName("vote_average") val voteAverage: Double? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("vote_count") val voteCount: Int? = null,
    @SerializedName("poster_path") val posterPath: String? = null
)
