package com.example.movieapplication.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(

	@SerializedName("Response")
	val response: String?,

	@SerializedName("totalResults")
	val totalResults: String?,

	@SerializedName("Search")
	val movieOverviews: List<MovieOverview>? = null
) : Parcelable

@Parcelize
data class MovieOverview(

	@SerializedName("Type")
	val type: String? = null,

	@SerializedName("Year")
	val year: String? = null,

	@SerializedName("imdbID")
	val imdbID: String? = null,

	@SerializedName("Poster")
	val poster: String? = null,

	@SerializedName("Title")
	val title: String? = null
) : Parcelable
