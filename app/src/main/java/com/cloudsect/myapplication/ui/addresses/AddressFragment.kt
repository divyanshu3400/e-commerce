package com.cloudsect.myapplication.ui.addresses

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.databinding.FragmentAddressBinding
import com.cloudsect.myapplication.ui.addresses.adapter.AddressAdapter
import com.cloudsect.myapplication.ui.addresses.listener.AddressListener
import com.cloudsect.myapplication.ui.addresses.model.AddressModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddressFragment : Fragment(), AddressListener {


    private lateinit var binding: FragmentAddressBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddressBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.addAddressBtn.setOnClickListener {

            val bottomSheetFragment = AddressLayoutBottomSheet()
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
        }
        setAddresses()
    }

    private fun setAddresses() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = context?.let { AddressAdapter(it, getItemList()) }
    }

    private fun getItemList(): List<AddressModel> {
        val itemList = mutableListOf<AddressModel>(
        )
        return itemList
    }

    override fun deleteAddress(modal: AddressModel) {
        TODO("Not yet implemented")
    }

    override fun updateAddress(modal: AddressModel) {
        TODO("Not yet implemented")
    }
}