package alfianyusufabdullah.paging.screen

import alfianyusufabdullah.paging.R
import alfianyusufabdullah.paging.entity.MovieItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import kotlinx.android.synthetic.main.item_movie.view.*

class MainAdapter : PagedListAdapter<MovieItem, MainAdapter.MainHolder>(callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(getItem(position) ?: MovieItem())
    }

    companion object {
        val callback = object : DiffUtil.ItemCallback<MovieItem>() {
            override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
                return oldItem.originalTitle == newItem.originalTitle
            }

            override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    class MainHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movieItem: MovieItem) {
            itemView.item_title.text = movieItem.originalTitle
            itemView.item_decs.text = movieItem.overview
            itemView.item_vote_count.text = movieItem.voteCount.toString()
            itemView.item_vote_average.text = movieItem.voteAverage.toString()

            itemView.item_poster.load("https://image.tmdb.org/t/p/w500" + movieItem.posterPath) {
                transformations(RoundedCornersTransformation(50f))
                crossfade(true)
            }
        }
    }
}