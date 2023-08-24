package com.cloudsect.myapplication.ui.personal_detail

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.databinding.FragmentPersonalDetailBinding


class PersonalDetailFragment : Fragment(), OnItemClickListener {

    private lateinit var viewModel: PersonalDetailViewModel
    private lateinit var binding:FragmentPersonalDetailBinding

    companion object {
        const val GALLERY_REQUEST_CODE = 123 // Use any unique integer value
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.fragment_personal_detail,container,false)
        if (context?.let { ContextCompat.checkSelfPermission(it, android.Manifest.permission.READ_EXTERNAL_STORAGE) }
            != PackageManager.PERMISSION_GRANTED
        ) {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    GALLERY_REQUEST_CODE
                )
            }
        }
        binding.onItemClickListener = this
        return binding.root
    }

    override fun radioButtonClickEvent(view: View) {
        val isChecked = (view as RadioButton).isChecked
        when (view.getId()) {
            R.id.rbtMale -> if (isChecked) {
                Toast.makeText(context, "Male is Selected.", Toast.LENGTH_SHORT).show()
            }

            R.id.rbtFemale -> if (isChecked) {
                Toast.makeText(context, "Female is Selected.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun openMedia(view: View) {
        Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show()
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val selectedImageUri = data.data
            Log.d("TAG", "onActivityResult: $selectedImageUri")
            context?.let { Glide.with(it).load(selectedImageUri).into(binding.profileImage) }
        }
        else{
            Toast.makeText(context,"Not Granted",Toast.LENGTH_SHORT).show()
        }
    }

}