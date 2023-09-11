package com.cloudsect.myapplication.ui.addresses


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.cloudsect.myapplication.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class AddressLayoutBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView =
            inflater.inflate(R.layout.fragment_address_layout_bottom_sheet, container, false)

        rootView.findViewById<Button>(R.id.useCurrentLocationBtn).setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.addressMapsFragment)
            dismiss() // Close the dialog
        }


        return rootView
    }

}
