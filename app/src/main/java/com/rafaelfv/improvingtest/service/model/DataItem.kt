package com.rafaelfv.improvingtest.service.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataItem(
    val loan: Loan?,
    val locale: String,
    val timestamp: Long,
    val username: String
): Parcelable