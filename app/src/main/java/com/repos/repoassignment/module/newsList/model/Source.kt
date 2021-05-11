package com.repos.repoassignment.module.newsList.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Source(
    var id: String? = "",
    var name: String? = ""
) : Parcelable