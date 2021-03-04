package com.rafaelfv.improvingtest.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rafaelfv.improvingtest.R
import com.rafaelfv.improvingtest.service.model.DataItem
import com.rafaelfv.improvingtest.ui.adapters.AdapterDataItem
import com.rafaelfv.improvingtest.utils.EXTRA_DATA
import com.rafaelfv.improvingtest.utils.EXTRA_DATA_LIST
import com.rafaelfv.improvingtest.utils.FRAGMENT_TAG_DATA
import com.rafaelfv.improvingtest.utils.addFragment
import kotlinx.android.synthetic.main.fragment_data.*

class FragmentData : Fragment() {

    private lateinit var listDataItem: ArrayList<DataItem>
    private lateinit var adapterDataItem: AdapterDataItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listDataItem =
            arguments?.getParcelableArrayList<DataItem>(EXTRA_DATA_LIST) as ArrayList<DataItem>
        adapterDataItem = AdapterDataItem(listDataItem, object :
            AdapterDataItem.AdapterDataItemEvent {
            override fun onItemClick(position: Int) {
                launchDetailDataFragment(listDataItem[position])
            }
        })
    }

    private fun launchDetailDataFragment(dataItem: DataItem) {
        val fragmentDataDetail = FragmentDataDetail()
        val bundle = Bundle()
        bundle.putParcelable(EXTRA_DATA, dataItem)
        fragmentDataDetail.arguments = bundle
        requireActivity().supportFragmentManager.addFragment(
            fragmentDataDetail, R.id.content_main,
            FRAGMENT_TAG_DATA
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerData.adapter = adapterDataItem
    }

}