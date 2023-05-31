package ba.etf.rma23.projekat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rma23_19079_videogames.R

class GameImpressionAdapter ( 
    private var impressions : List<UserImpression> ) : RecyclerView.Adapter<GameImpressionAdapter.UserImpressionHolder> ()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserImpressionHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.rating_review_item, parent, false)
        return UserImpressionHolder(view)
    }

    override fun getItemCount(): Int = impressions.size

    override fun onBindViewHolder(holder: UserImpressionHolder, position: Int) {
        if(impressions[position] is UserRating){
            var userRating = impressions[position] as UserRating
            holder.username.text = userRating.username
            holder.ratingBar.rating = userRating.rating.toFloat()
            var userReview = holder.review
            userReview.visibility = View.INVISIBLE
        }
        else{
            var userReview = impressions[position] as UserReview
            holder.username.text = userReview.username
            holder.review.text = userReview.review
            var userRating = holder.ratingBar
            userRating.visibility = View.INVISIBLE
        }

    }


    fun updateGames(impressions: ArrayList<UserImpression>) {
        this.impressions = impressions
        notifyDataSetChanged()
    }

    inner class UserImpressionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val username: TextView = itemView.findViewById(R.id.username_textview)
        val ratingBar: RatingBar = itemView.findViewById(R.id.rating_bar)
        val review: TextView = itemView.findViewById(R.id.review_textview)

    }
}