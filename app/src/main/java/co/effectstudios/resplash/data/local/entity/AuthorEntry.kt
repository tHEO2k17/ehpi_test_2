package co.effectstudios.resplash.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import co.effectstudios.resplash.data.remote.response.AuthorResponse
import com.google.gson.annotations.SerializedName

@Entity(tableName = "authors_table")
data class AuthorEntry(
    val author: String,
    @SerializedName("download_url")
    val downloadUrl: String,
    val height: Int,
    @PrimaryKey
    val id: String,
    val url: String,
    val width: Int
)