package com.rafaelfv.improvingtest.utils

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.rafaelfv.improvingtest.R
import com.rafaelfv.improvingtest.service.model.DataItem
import java.text.SimpleDateFormat
import java.util.*

fun FragmentManager.setFragment(
    fragment: Fragment,
    id: Int,
    tag: String
) {
    this.beginTransaction()
        .replace(id, fragment, tag)
        .commit()
}

fun FragmentManager.addFragment(
    fragment: Fragment,
    id: Int,
    tag: String
) {
    this.beginTransaction()
        .setCustomAnimations(
            R.animator.enter_anim,
            R.animator.exit_anim,
            R.animator.enter_anim,
            R.animator.exit_anim
        )
        .add(id, fragment, tag)
        .addToBackStack(tag)
        .commit()
}

fun Long.toDate(): String =
    try {
        val sdf = SimpleDateFormat("MM/dd/yyyy")
        val netDate = Date(this)
        sdf.format(netDate)
    } catch (ex: Exception) {
        "xx"
    }

fun DataItem.getColorBackGround(context: Context): Int {
    return when (this.loan?.status) {
        STATUS_APPROVED -> context.resources.getColor(R.color.green_light)
        STATUS_DUE -> context.resources.getColor(R.color.green_dark)
        STATUS_PAID -> context.resources.getColor(R.color.orange)
        else -> context.resources.getColor(R.color.green_light)
    }
}
