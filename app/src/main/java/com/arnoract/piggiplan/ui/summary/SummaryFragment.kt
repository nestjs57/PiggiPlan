package com.arnoract.piggiplan.ui.summary

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.base.BaseFragment
import com.arnoract.piggiplan.base.viewBinding
import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.core.findNavControllerSafety
import com.arnoract.piggiplan.core.setDebounceOnClickListener
import com.arnoract.piggiplan.databinding.FragmentSummaryBinding
import com.arnoract.piggiplan.domain.model.branch.Branch
import com.arnoract.piggiplan.ui.create.model.UiFriendAddress
import com.arnoract.piggiplan.util.bitmapDescriptorFromVector
import com.arnoract.piggiplan.util.updateCameraLatLngBound
import com.arnoract.piggiplan.util.updateCameraLatLngZoom
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SummaryFragment : BaseFragment(R.layout.fragment_summary),
    SummaryBranchViewHolder.OnBranchItemClickListener, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private val callback = OnMapReadyCallback { googleMap ->
        mMap = googleMap
        mMap.setOnMarkerClickListener(this)
        mViewModel.onMapReady()
    }
    private lateinit var linearLayoutManager: LinearLayoutManager

    private val binding by viewBinding(FragmentSummaryBinding::bind)
    private val mViewModel: SummaryViewModel by viewModel()
    private var _mAdapter: SummaryBranchAdapter? = null
    private val mAdapter
        get() = _mAdapter!!

    override fun setUpView() {
        binding.toolbarLayout.backImageButton.setDebounceOnClickListener {
            findNavController().popBackStack()
        }
        binding.branchRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == SCROLL_STATE_IDLE) {
                    mViewModel.moveCamera(linearLayoutManager.findFirstVisibleItemPosition())
                }
            }
        })
    }

    override fun setUpRecyclerView() {
        _mAdapter = SummaryBranchAdapter(this)
        linearLayoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false
        )
        binding.branchRecyclerView.apply {
            onFlingListener = null
            this.layoutManager = linearLayoutManager
            adapter = mAdapter
            PagerSnapHelper().attachToRecyclerView(binding.branchRecyclerView)
        }
        binding.allBoundsActionButton.setDebounceOnClickListener {
            mViewModel.moveCameraAllBounds()
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
                addMarkerFriend(friend)
            }
        }
        mViewModel.allBranches.observe(viewLifecycleOwner) {
            it ?: return@observe
            it.forEach { branch ->
                addMarkerBranch(branch)
            }
            mViewModel.moveCamera(0)
        }
        mViewModel.moveCameraEvent.observe(viewLifecycleOwner) {
            mMap.animateCamera(updateCameraLatLngZoom(it))
        }
        mViewModel.moveCameraAllBoundsEvent.observe(viewLifecycleOwner) {
            mMap.animateCamera(updateCameraLatLngBound(it, 200))
        }
        mViewModel.uiSummaryBranch.observe(viewLifecycleOwner) {
            mAdapter.submitList(it)
        }
        mViewModel.onBranchScrollToPositionEvent.observe(viewLifecycleOwner) {
            binding.branchRecyclerView.smoothScrollToPosition(it)
        }
    }

    private fun addMarkerFriend(uiFriendAddress: UiFriendAddress) {
        mMap.addMarker(
            MarkerOptions().position(uiFriendAddress.latLng)
                .title(uiFriendAddress.name)
                .icon(requireContext().bitmapDescriptorFromVector(R.drawable.ic_people_yellow))
        )
    }

    private fun addMarkerBranch(branch: Branch?) {
        mMap.addMarker(
            MarkerOptions().position(LatLng(branch?.latitude ?: 0.0, branch?.longitude ?: 0.0))
                .icon(requireContext().bitmapDescriptorFromVector(R.drawable.ic_pin_purple700))
        ).apply {
            tag = branch?.branchId
        }
    }

    override fun onBranchItemClick(branchId: BranchId) {
        val action =
            SummaryFragmentDirections.actionSummaryFragmentToBranchesDetailFragment(branchId)
        findNavControllerSafety(R.id.summaryFragment)?.navigate(action)
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        p0?.tag ?: return false
        mViewModel.onMarkerSelected(p0.tag as BranchId)
        return false
    }
}