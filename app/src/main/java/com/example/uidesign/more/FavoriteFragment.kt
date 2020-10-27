package com.example.uidesign.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uidesign.PictureModel
import com.example.uidesign.R
import java.util.ArrayList

class FavoriteFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        val pictureList = fillData()
        val adapter =
            FavoriteAdapter(
                requireContext(),
                pictureList
            )
        recyclerView.adapter = adapter
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.layoutManager = gridLayoutManager
    }

    private fun fillData(): ArrayList<PictureModel> {
        val picList: ArrayList<PictureModel> = ArrayList()
        picList.add(
            PictureModel(
                "image 1",
                R.drawable.download
            )
        )
        picList.add(
            PictureModel(
                "image 2",
                R.drawable.one
            )
        )
        picList.add(
            PictureModel(
                "image 3",
                R.drawable.dtwo
            )
        )
        picList.add(
            PictureModel(
                "image 4",
                R.drawable.dthree
            )
        )
        picList.add(
            PictureModel(
                "image 5",
                R.drawable.download
            )
        )
        picList.add(
            PictureModel(
                "image 6",
                R.drawable.one
            )
        )
        picList.add(
            PictureModel(
                "image 7",
                R.drawable.dtwo
            )
        )
        picList.add(
            PictureModel(
                "image 8",
                R.drawable.dthree
            )
        )
        picList.add(
            PictureModel(
                "image 9",
                R.drawable.download
            )
        )
        picList.add(
            PictureModel(
                "image 10",
                R.drawable.one
            )
        )
        picList.add(
            PictureModel(
                "image 11",
                R.drawable.dtwo
            )
        )
        picList.add(
            PictureModel(
                "image 12",
                R.drawable.dthree
            )
        )
        return picList
    }
}