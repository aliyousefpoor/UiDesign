package com.example.uidesign

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MoreFragment : Fragment() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.more_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = view.findViewById(R.id.viewPager)
        tabLayout = view.findViewById(R.id.tabLayout)
        setUpTab()
    }

    private fun setUpTab() {
        val adapter = ViewPagerAdapter(parentFragmentManager)
        adapter.addFragment(MusicFragment(), "Music")
        adapter.addFragment(FavoriteFragment(), "Favorite")
        adapter.addFragment(SearchFragment(), "Search")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)!!.setIcon(R.drawable.music)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.favorite)
        tabLayout.getTabAt(2)!!.setIcon(R.drawable.search)

        tabLayout.getTabAt(0)!!.icon!!.setColorFilter(
            resources.getColor(R.color.lightGray),
            PorterDuff.Mode.SRC_IN
        )
        tabLayout.getTabAt(1)!!.icon!!.setColorFilter(
            resources.getColor(R.color.lightGray),
            PorterDuff.Mode.SRC_IN
        )
        tabLayout.getTabAt(2)!!.icon!!.setColorFilter(
            resources.getColor(R.color.lightGray),
            PorterDuff.Mode.SRC_IN
        )

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
                if (tab.position == 0) {
                    tabLayout.getTabAt(0)!!.icon!!.setColorFilter(
                        resources.getColor(R.color.tab),
                        PorterDuff.Mode.SRC_IN
                    )
                    tabLayout.getTabAt(1)!!.icon!!.setColorFilter(
                        resources.getColor(R.color.lightGray),
                        PorterDuff.Mode.SRC_IN
                    )
                    tabLayout.getTabAt(2)!!.icon!!.setColorFilter(
                        resources.getColor(R.color.lightGray),
                        PorterDuff.Mode.SRC_IN
                    )
                } else if (tab.position == 1) {
                    tabLayout.getTabAt(0)!!.icon!!.setColorFilter(
                        resources.getColor(R.color.lightGray),
                        PorterDuff.Mode.SRC_IN
                    )
                    tabLayout.getTabAt(1)!!.icon!!.setColorFilter(
                        resources.getColor(R.color.tab),
                        PorterDuff.Mode.SRC_IN
                    )
                    tabLayout.getTabAt(2)!!.icon!!.setColorFilter(
                        resources.getColor(R.color.lightGray),
                        PorterDuff.Mode.SRC_IN
                    )

                } else if (tab.position == 2) {
                    tabLayout.getTabAt(0)!!.icon!!.setColorFilter(
                        resources.getColor(R.color.lightGray),
                        PorterDuff.Mode.SRC_IN
                    )
                    tabLayout.getTabAt(1)!!.icon!!.setColorFilter(
                        resources.getColor(R.color.lightGray),
                        PorterDuff.Mode.SRC_IN
                    )
                    tabLayout.getTabAt(2)!!.icon!!.setColorFilter(
                        resources.getColor(R.color.tab),
                        PorterDuff.Mode.SRC_IN
                    )

                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })

    }

}