package com.cloudsect.myapplication.ui.addresses

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.databinding.FragmentAddressBinding
import com.cloudsect.myapplication.databinding.FragmentAddressMapsBinding
import com.cloudsect.myapplication.ui.addresses.model.AddressModel
import com.cloudsect.myapplication.util.IconUtil
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar

class AddressMapsFragment : Fragment() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var mMap: GoogleMap
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var _binding: FragmentAddressMapsBinding
    private val binding get() = _binding

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1
    }

    private val callback = OnMapReadyCallback { googleMap ->
        mMap = googleMap
        mMap.setMinZoomPreference(15f)
        checkLocationPermission()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View { _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_address_maps, container, false)
        fusedLocationClient =
            activity?.let { LocationServices.getFusedLocationProviderClient(it) }!!

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        Snackbar.make(view, "Press and Hold marker to move ", Snackbar.LENGTH_LONG).show()
        mapFragment?.getMapAsync(callback)
        val bottomSheetLayout = view.findViewById<LinearLayout>(R.id.bottom_sheet)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout)
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                // Handle state changes (expanded, collapsed, hidden, etc.)
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // Handle slide offset if needed
            }
        })


    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkLocationPermission()
            } else {
                requireActivity().finish()
            }
        }
    }

    private fun checkLocationPermission() {
        if (context?.let {
                ActivityCompat.checkSelfPermission(it, Manifest.permission.ACCESS_FINE_LOCATION)
            } != PackageManager.PERMISSION_GRANTED
            && context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        } else {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                location?.let { getUpdateLocation(location.latitude, location.longitude) }
            }
        }

    }

    private fun getUpdateLocation(latitude: Double, longitude: Double) {
        val drawable: Drawable? =
            context?.let { ContextCompat.getDrawable(it, R.drawable.map_marker2) }
        val bitmap: Bitmap? = drawable?.let { IconUtil.drawableToBitmap(it) }
        val customIcon: BitmapDescriptor? = bitmap?.let { BitmapDescriptorFactory.fromBitmap(it) }
        val initialPosition = LatLng(latitude, longitude)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(initialPosition))
        mMap.addMarker(
            MarkerOptions().position(initialPosition)
                .icon(customIcon)
                .title("Press and hold the marker to move the destination").draggable(true)
        )
        mMap.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
            override fun onMarkerDragStart(marker: Marker) {}

            override fun onMarkerDrag(marker: Marker) {}

            override fun onMarkerDragEnd(updatedMarker: Marker) {
                val newPosition = updatedMarker.position
                getAddress(newPosition.latitude, newPosition.longitude)
            }
        })
    }

    private fun getAddress(latitude: Double, longitude: Double) {
        val geocoder = context?.let { Geocoder(it) }
        try {
            val addresses: List<Address> = geocoder?.getFromLocation(latitude, longitude, 1)!!
            if (addresses.isNotEmpty()) {
                val address: Address = addresses[0]
                val addressText = address.getAddressLine(0)
                val modal = AddressModel()
                modal.subLocality = addresses[0].subLocality
                modal.locality = addresses[0].locality
                modal.state = addresses[0].adminArea
                modal.country = addresses[0].countryName
                modal.pinCode = addresses[0].postalCode
                binding.modal = modal
                Toast.makeText(context, "Current $addressText", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}