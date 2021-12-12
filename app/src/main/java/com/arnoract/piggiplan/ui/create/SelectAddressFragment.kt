package com.arnoract.piggiplan.ui.create

import androidx.navigation.fragment.findNavController
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.base.BaseFragment
import com.arnoract.piggiplan.base.viewBinding
import com.arnoract.piggiplan.core.setDebounceOnClickListener
import com.arnoract.piggiplan.databinding.FragmentSelectAddressBinding

class SelectAddressFragment : BaseFragment(R.layout.fragment_select_address) {

    private val binding by viewBinding(FragmentSelectAddressBinding::bind)

    override fun setUpView() {
        binding.toolbarLayout.backImageButton.setDebounceOnClickListener {
            findNavController().popBackStack()
        }
    }

//    private val callback = OnMapReadyCallback { googleMap ->
//        val sydney = LatLng(-34.0, 151.0)
//        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
//    }

//    mapFragment?.getMapAsync(callback)
}