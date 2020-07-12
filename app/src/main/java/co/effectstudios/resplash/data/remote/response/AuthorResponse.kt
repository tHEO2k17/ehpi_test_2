package co.effectstudios.resplash.data.remote.response

import co.effectstudios.resplash.data.local.entity.AuthorEntry
import com.google.gson.annotations.SerializedName

data class AuthorResponse(
    val author: String,
    @SerializedName("download_url")
    val downloadUrl: String,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)