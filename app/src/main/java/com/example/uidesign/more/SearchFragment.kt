package com.example.uidesign.more

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uidesign.R
import java.util.ArrayList

class SearchFragment : Fragment() {
    private lateinit var searchEditText: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var arrayList: MutableList<SearchModel>
    private lateinit var adapter: SearchAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchEditText = view.findViewById(R.id.search)
        recyclerView = view.findViewById(R.id.searchRecyclerView)

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterList(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })


        val list = loadData()

        adapter = SearchAdapter(list, requireContext())
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

    }

    private fun filterList(filterItem: String) {
        val tempList: MutableList<SearchModel> = ArrayList()

        for (d in arrayList) {
            if (filterItem in d.toString()) {
                tempList.add(d)
            }
        }
        adapter.updateList(tempList)
    }

    private fun loadData(): MutableList<SearchModel> {
        arrayList = ArrayList()

        arrayList.add(SearchModel("JohnWick",2013,R.drawable.dthree))
        arrayList.add(SearchModel("Revenant",2014,R.drawable.dtwo))
        arrayList.add(SearchModel("BobMar",2010,R.drawable.download))
        arrayList.add(SearchModel("HoseinAzizi",1993,R.drawable.dthree))
        arrayList.add(SearchModel("SaharTaheri",2001,R.drawable.dtwo))
        arrayList.add(SearchModel("June",2004,R.drawable.download))
        arrayList.add(SearchModel("July",2013,R.drawable.dthree))
        arrayList.add(SearchModel("August",2020,R.drawable.dtwo))
        arrayList.add(SearchModel("September",2013,R.drawable.dthree))
        arrayList.add(SearchModel("October",2015,R.drawable.dtwo))
        arrayList.add(SearchModel("November",2019,R.drawable.download))
        arrayList.add(SearchModel("December",2015,R.drawable.dthree))
        arrayList.add(SearchModel("January",1892,R.drawable.dtwo))
        arrayList.add(SearchModel("February",1998,R.drawable.download))
        arrayList.add(SearchModel("March",2014,R.drawable.dthree))
        arrayList.add(SearchModel("April",2020,R.drawable.dtwo))
        arrayList.add(SearchModel("May",2006,R.drawable.download))
        arrayList.add(SearchModel("June",2007,R.drawable.dthree))
        arrayList.add(SearchModel("July",1989,R.drawable.dtwo))
        arrayList.add(SearchModel("August",2012,R.drawable.download))
        arrayList.add(SearchModel("September",2016,R.drawable.dthree))
        arrayList.add(SearchModel("October",1990,R.drawable.dtwo))
        arrayList.add(SearchModel("November",1978,R.drawable.download))
        arrayList.add(SearchModel("December",2021,R.drawable.dthree))

        return arrayList
    }
}