package com.rafaelfv.improvingtest.service.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Loan(
    val due: Int,
    val dueDate: Long,
    val level: String,
    val status: String,
    val approved: Long = 0
): Parcelable