package com.example.uidesign.home

import android.Manifest
import android.app.ProgressDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.uidesign.R
import org.osmdroid.api.IMapController
import org.osmdroid.bonuspack.kml.KmlDocument
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.BoundingBox
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.compass.CompassOverlay


class OSMFragment : Fragment() {
    private lateinit var mapView: MapView
    private lateinit var mapController: IMapController
    private val code = 101


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.osm_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView = view.findViewById(R.id.mapView)
        Configuration.getInstance()
            .load(requireContext(), PreferenceManager.getDefaultSharedPreferences(requireContext()))

        if (Build.VERSION.SDK_INT >= 23) {
            if (isStoragePermissionGranted()) {

            }
        }

        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setBuiltInZoomControls(true)
        mapView.setMultiTouchControls(true)
        mapController = mapView.controller
        mapController.setZoom(15)

        val compassOverlay = CompassOverlay(requireContext(), mapView)
        compassOverlay.enableCompass()
        mapView.overlays.add(compassOverlay)

        val startPoint = GeoPoint(35.736486, 51.477111)

        val startMarker = Marker(mapView)
        startMarker.position = startPoint
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        mapView.overlays.add(startMarker)

        mapController.setCenter(startPoint)

//        loadKml()
    }

    override fun onResume() {
        super.onResume()
        if (mapView != null) {
            mapView.onResume()
        }
    }

    override fun onPause() {
        super.onPause()
        if (mapView != null) {
            mapView.onPause()
        }
    }

//    private fun loadKml() {
//        KmlLoader(requireContext()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
//    }
//
//
//    class KmlLoader(context: Context) : AsyncTask<Void, Void, Void>() {
//        val progressDialog = ProgressDialog(context)
//        val kmlDocument = KmlDocument()
//
//        override fun onPreExecute() {
//            super.onPreExecute()
//            progressDialog.setMessage("Loading Project...");
//            progressDialog.show();
//
//        }
//
//        override fun doInBackground(vararg params: Void?): Void {
//            kmlDocument.parseKMLStream()
//        }
//
//        override fun onPostExecute(result: Void?) {
//            OSMFragment().mapView.invalidate()
//            val bb: BoundingBox = kmlDocument.mKmlRoot.getBoundingBox()
//            OSMFragment().mapView.zoomToBoundingBox(bb, true)
////            mapView.getController().setCenter(bb.getCenter());
//
//            super.onPostExecute(result)
//        }
//
//    }

    private fun isStoragePermissionGranted(): Boolean {
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
            return true
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == code) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                isStoragePermissionGranted()
            }
        }
    }


}