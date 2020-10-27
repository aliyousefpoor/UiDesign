package com.example.uidesign.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.uidesign.R

class SearchFragment : Fragment() {
    private lateinit var searchEditText: EditText
    private lateinit var findButton: Button
    private lateinit var searchText: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchEditText = view.findViewById(R.id.searchEditText)
        findButton = view.findViewById(R.id.findButton)
        searchText = view.findViewById(R.id.searchText)

    }
}