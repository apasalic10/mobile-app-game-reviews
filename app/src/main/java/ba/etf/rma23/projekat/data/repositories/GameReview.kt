package ba.etf.rma23.projekat.data.repositories

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.lang.reflect.Constructor

@Entity
data class GameReview(
    @PrimaryKey var id:Int,
    @SerializedName("rating") @ColumnInfo(name = "rating") var rating:Int?,
    @SerializedName("review") @ColumnInfo(name = "review") var review: String?,
    @ColumnInfo(name = "igdb_id") var igdb_id:Int,
    @ColumnInfo(name = "online") var online: Boolean,
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "timestamp") var timestamp: String



){
    constructor(
        rating: Int?,
        review: String?,
        igdb_id: Int,
        online: Boolean,
        username: String,
        timestamp: String
    ) : this(0, rating, review, igdb_id, online, username,timestamp)
}
