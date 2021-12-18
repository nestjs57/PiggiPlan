package com.arnoract.piggiplan.ui.create

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.base.BaseFragment
import com.arnoract.piggiplan.base.viewBinding
import com.arnoract.piggiplan.core.setDebounceOnClickListener
import com.arnoract.piggiplan.databinding.FragmentSelectAddressBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectAddressFragment : BaseFragment(R.layout.fragment_select_address) {

    private lateinit var mMap: GoogleMap
    private val callback = OnMapReadyCallback { googleMap ->
        mMap = googleMap
        mViewModel.initialData()
        binding.chooseLocationTextView.visibility = View.VISIBLE
    }
    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                if (data != null) {
                    val place = Autocomplete.getPlaceFromIntent(data)
                    val id = place.id ?: ""
                    val addressName = place.address
                        ?: getString(R.string.create_party_select_address_blank_address_name_label)
                    val latLng = place.latLng
                    mViewModel.setAddressSelected(id, addressName, latLng)
                }
            }
        }
    private val binding by viewBinding(FragmentSelectAddressBinding::bind)
    private val mViewModel: SelectAddressViewModel by viewModel()

    override fun setUpView() {
        binding.toolbarLayout.backImageButton.setDebounceOnClickListener {
            findNavController().popBackStack()
        }
        binding.chooseLocationTextView.setDebounceOnClickListener {
            openSearchLocation()
        }
        binding.saveAddressButton.setDebounceOnClickListener {
            mViewModel.saveAddress()
            findNavController().popBackStack()
        }
    }

    override fun setUpMap() {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mapLayout) as SupportMapFragment
        mapFragment.getMapAsync(callback)
    }

    override fun observeViewModel() {
        mViewModel.latLngSelected.observe(viewLifecycleOwner) {
            it ?: return@observe
            clearMarker()
            addMarker(it)
            moveCamera(it)
        }
        mViewModel.addressNameSelected.observe(viewLifecycleOwner) {
            it ?: return@observe
            binding.chooseLocationTextView.text = it
        }
    }

    private fun openSearchLocation() {
        val intent = Autocomplete.IntentBuilder(
            AutocompleteActivityMode.OVERLAY,
            listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG)
        )
            .build(requireContext())
        resultLauncher.launch(intent)
    }

    private fun addMarker(latLng: LatLng?) {
        mMap.addMarker(
            MarkerOptions().position(latLng ?: LatLng(0.0, 0.0))
        )
    }

    private fun moveCamera(latLng: LatLng?, zoomLevel: Float = 12.0f) {
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    latLng?.latitude ?: 0.0,
                    latLng?.longitude ?: 0.0
                ), zoomLevel
            )
        )
    }

    private fun clearMarker() {
        mMap.clear()
    }
}