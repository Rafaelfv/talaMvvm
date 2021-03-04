package com.rafaelfv.improvingtest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rafaelfv.improvingtest.R
import com.rafaelfv.improvingtest.service.model.Data
import com.rafaelfv.improvingtest.service.model.DataItem
import com.rafaelfv.improvingtest.service.model.Locales
import com.rafaelfv.improvingtest.utils.*
import com.rafaelfv.improvingtest.viewModel.MainViewModel
import kotlinx.android.synthetic.main.card_item_status_approved.*
import kotlinx.android.synthetic.main.card_item_status_due.*
import kotlinx.android.synthetic.main.card_item_status_paid.*
import kotlinx.android.synthetic.main.fragment_main.*

class FragmentMain : Fragment() {

    private lateinit var viewModel: MainViewModel
    private var data: Data? = null
    private lateinit var locales: Locales
    private lateinit var progress: ProgresDialogApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        progress = ProgresDialogApp(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.data.observe(viewLifecycleOwner, Observer<Data> {
            data = it
        })

        viewModel.locales.observe(viewLifecycleOwner, Observer<Locales> {
            locales = it
        })

        viewModel.loadingVisibility
            .observe(viewLifecycleOwner, Observer<Int> {
                when (it) {
                    View.VISIBLE -> {
                        try {
                            if (!progress.isShowing) {
                                progress.show()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                    View.GONE -> {
                        progress.hide()
                    }
                }
            })

        statusCardApproved.setOnClickListener {
            launchFragmentData(STATUS_APPROVED)
        }

        statusCardDue.setOnClickListener {
            launchFragmentData(STATUS_DUE)
        }

        statusCardPaid.setOnClickListener {
            launchFragmentData(STATUS_PAID)
        }

    }

    private fun launchFragmentData(status: String) {
        if(data == null) {
            return
        }
        val fragmentData = FragmentData()
        val bundle = Bundle()
        val listApproved: ArrayList<DataItem> = data?.filter { it.loan != null }
            ?.filter { item -> item.loan?.status == status } as ArrayList<DataItem>
        bundle.putParcelableArrayList(EXTRA_DATA_LIST, listApproved)
        fragmentData.arguments = bundle
        requireActivity().supportFragmentManager.addFragment(
            fragmentData, R.id.content_main,
            FRAGMENT_TAG_DATA
        )
    }
}