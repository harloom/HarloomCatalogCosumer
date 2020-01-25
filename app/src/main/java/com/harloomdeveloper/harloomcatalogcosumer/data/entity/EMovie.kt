package com.harloomdeveloper.harloomcatalogcosumer.data.entity

import android.os.Parcelable


import kotlinx.android.parcel.Parcelize


@Parcelize
data class EMovie(
    val id: Int?,
    val posterPath: String?,
    val title: String?


) : Parcelable