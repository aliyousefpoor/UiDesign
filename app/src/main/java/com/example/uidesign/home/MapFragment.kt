package com.example.uidesign.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.uidesign.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var myMap: GoogleMap
    private lateinit var mapFragment: View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.map_fragment, container, false)
//        val mapFragment = fragmentManager?.findFragmentById(R.id.map) as SupportMapFragment
//        mapFragment.getMapAsync(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment = view.findViewById(R.id.map)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        myMap = googleMap
        val iran = LatLng(32.4279,53.6880)
        myMap.addMarker(MarkerOptions().position(iran).title("Marker in Iran"))
        myMap.moveCamera(CameraUpdateFactory.newLatLng(iran))
    }
}