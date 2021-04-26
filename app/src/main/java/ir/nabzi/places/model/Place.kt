package ir.nabzi.places.model

import android.os.Bundle
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Place
    (
    val id: String,
    val title: String,
    val subTitle: String,
    val imageUrl: String,
    val rating: Int,
) : Parcelable