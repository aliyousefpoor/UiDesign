package com.example.uidesign.home

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.uidesign.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task


class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var myFragment: SupportMapFragment
    private lateinit var client: FusedLocationProviderClient
    private val code = 101
    private lateinit var currentLocation: LatLng
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.map_fragment, container, false)

        myFragment = childFragmentManager.findFragmentById(R.id.googleMap) as SupportMapFragment
        myFragment.getMapAsync(this)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        client = LocationServices.getFusedLocationProviderClient(requireActivity())
        fetchLastLocation()
    }

    private fun fetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                code
            )
            return
        }
        val task: Task<Location> = client.lastLocation
        task.addOnSuccessListener {
            if (it != null) {
                currentLocation = LatLng(it.latitude, it.longitude)
                Toast.makeText(
                    requireContext(),
                    "${currentLocation.latitude}" + "" + "${currentLocation.longitude}",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val tehran = LatLng(35.736486, 51.477111)
        val markerOptions = MarkerOptions().position(tehran).title("I'm Here")
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(tehran))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(tehran, 5F))
        googleMap.addMarker(markerOptions)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == code) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchLastLocation()
            }
        }
    }
}