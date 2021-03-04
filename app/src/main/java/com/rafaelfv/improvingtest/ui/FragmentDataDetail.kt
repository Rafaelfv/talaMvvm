package com.rafaelfv.improvingtest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rafaelfv.improvingtest.R
import com.rafaelfv.improvingtest.service.model.DataItem
import com.rafaelfv.improvingtest.service.model.Locale
import com.rafaelfv.improvingtest.service.model.Locales
import com.rafaelfv.improvingtest.utils.EXTRA_DATA
import com.rafaelfv.improvingtest.utils.STATUS_APPROVED
import com.rafaelfv.improvingtest.utils.STATUS_DUE
import com.rafaelfv.improvingtest.utils.STATUS_PAID
import com.rafaelfv.improvingtest.viewModel.MainViewModel
import kotlinx.android.synthetic.main.card_header_data.*
import kotlinx.android.synthetic.main.card_read_more.*
import kotlinx.android.synthetic.main.card_status_detail.*

class FragmentDataDetail : Fragment() {

    private lateinit var dataItem: DataItem
    private lateinit var viewModel: MainViewModel
    private lateinit var currentLocale: Locale
    private lateinit var locales: Locales

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataItem = arguments?.getParcelable<DataItem>(EXTRA_DATA) as DataItem
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_data_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.locales.observe(viewLifecycleOwner, Observer<Locales> {
            locales = it
            currentLocale  = getLocale(dataItem.locale, it)
            setupViews()
        })
    }

    /**
     * Function to get Locale from Locales for an specific Data
     * @param locale keyLocale
     * @param locales Locales collection
     * @return locale
     */
    private fun getLocale(locale: String, locales: Locales): Locale =
        when (locale) {
            "ph" -> locales.ph
            "mx" -> locales.mx
            "ke" -> locales.ke
            else -> locales.ke
        }


    private fun String.getLevelDrawable(): Int =
        when (this) {
            "silver" -> R.drawable.img_silver_badge_large
            "gold" -> R.drawable.img_gold_badge_large
            "bronze" -> R.drawable.img_bronze_badge_large
            else -> R.drawable.img_bronze_badge_large
        }

    private fun setupViews() {
        dataItem.loan?.level?.let { statusImageLevel.setImageResource(it.getLevelDrawable()) }
        image_readme.setImageResource(getImageStory())
        when(dataItem.loan?.status){
            STATUS_APPROVED -> {
                imageHeader.setImageResource(R.drawable.img_loan_status_approved)
                headerTitle.text = getString(R.string.headerTitleApproved)
                headerSubTitle.text = getString(R.string.headerSubTitleApproved) + " ${currentLocale.currency} ${dataItem.loan?.approved}"
                headerBtn.text = getString(R.string.headerBtnHeaderApproved)
                statusSubTitle.text = getString(R.string.status_subtitle_approved) + " ${currentLocale.currency} ${currentLocale.loanLimit}"
            }
            STATUS_DUE -> {
                imageHeader.setImageResource(R.drawable.img_loan_status_apply)
                headerTitle.text = getString(R.string.headerTitleApplyLoan)
                headerSubTitle.text = getString(R.string.headerSubTitleLoan) + " ${currentLocale.currency} ${currentLocale.loanLimit} over time"
                headerBtn.text = getString(R.string.headerBtnHeaderLoan)
                statusSubTitle.text = getString(R.string.status_subtitle_loan)

            }
            STATUS_PAID -> {
                imageHeader.setImageResource(R.drawable.img_loan_status_paidoff)
                headerTitle.text = getString(R.string.headerTitlePaidOff)
                headerSubTitle.text = getString(R.string.headerSubTitlePaidOff)
                headerBtn.text = getString(R.string.headerBtnHeaderPaidOff)
                statusSubTitle.text = getString(R.string.status_subtitle_approved) + " ${currentLocale.currency} ${currentLocale.loanLimit}"
            }
        }
    }

    private fun getImageStory(): Int {
        return when(currentLocale){
            locales.mx -> R.drawable.img_story_card_mx
            locales.ke -> R.drawable.img_story_card_ke
            locales.ph -> R.drawable.img_story_card_ph
            else -> R.drawable.img_story_card_mx
        }
    }
}