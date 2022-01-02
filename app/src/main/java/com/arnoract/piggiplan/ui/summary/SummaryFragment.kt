package com.arnoract.piggiplan.ui.summary

import androidx.navigation.fragment.findNavController
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.base.BaseFragment
import com.arnoract.piggiplan.base.viewBinding
import com.arnoract.piggiplan.core.setDebounceOnClickListener
import com.arnoract.piggiplan.databinding.FragmentSummaryBinding
import com.arnoract.piggiplan.util.bitmapDescriptorFromVector
import com.arnoract.piggiplan.util.updateCameraLatLngZoom
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SummaryFragment : BaseFragment(R.layout.fragment_summary) {

    private lateinit var mMap: GoogleMap
    private val callback = OnMapReadyCallback { googleMap ->
        mMap = googleMap
        mViewModel.onMapReady()
    }
    private val binding by viewBinding(FragmentSummaryBinding::bind)
    private val mViewModel: SummaryViewModel by viewModel()

    override fun setUpView() {
        binding.toolbarLayout.backImageButton.setDebounceOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun setUpMap() {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mapLayout) as SupportMapFragment
        mapFragment.getMapAsync(callback)
    }

    override fun observeViewModel() {
        mViewModel.allFriend.observe(viewLifecycleOwner) {
            it ?: return@observe
            it.forEach { friend ->
                addMarkerFriend(friend.latLng)
            }
        }
        mViewModel.allBranches.observe(viewLifecycleOwner) {
            it ?: return@observe
            it.forEach { branch ->
                addMarkerBranch(LatLng(branch.latitude, branch.longitude))
            }
        }
        mViewModel.moveCameraEvent.observe(viewLifecycleOwner) {
            mMap.animateCamera(updateCameraLatLngZoom(it))
        }
    }

    private fun addMarkerFriend(latLng: LatLng?) {
        mMap.addMarker(
            MarkerOptions().position(latLng ?: LatLng(0.0, 0.0))
                .icon(requireContext().bitmapDescriptorFromVector(R.drawable.ic_people_yellow))
        )
    }

    private fun addMarkerBranch(latLng: LatLng?) {
        mMap.addMarker(
            MarkerOptions().position(latLng ?: LatLng(0.0, 0.0))
                .icon(requireContext().bitmapDescriptorFromVector(R.drawable.ic_pin_purple700))
        )
    }
}