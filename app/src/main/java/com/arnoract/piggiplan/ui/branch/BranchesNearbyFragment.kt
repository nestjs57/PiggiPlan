package com.arnoract.piggiplan.ui.branch

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.base.BaseFragment
import com.arnoract.piggiplan.base.viewBinding
import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.core.findNavControllerSafety
import com.arnoract.piggiplan.core.setDebounceOnClickListener
import com.arnoract.piggiplan.databinding.FragmentBranchesNearbyBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class BranchesNearbyFragment : BaseFragment(R.layout.fragment_branches_nearby),
    BranchesNearbyViewHolder.OnBranchItemClickListener {

    private val binding by viewBinding(FragmentBranchesNearbyBinding::bind)
    private val mViewModel: BranchesNearbyViewModel by viewModel()
    private var _mAdapter: BranchesNearbyAdapter? = null
    private val mAdapter
        get() = _mAdapter!!

    override fun setUpView() {
        binding.toolbarLayout.backImageButton.setDebounceOnClickListener {
            findNavController().popBackStack()
        }
        binding.summaryImageButton.setDebounceOnClickListener {
            val action =
                BranchesNearbyFragmentDirections.actionBranchNearbyFragmentToSummaryFramgnet()
            findNavControllerSafety(R.id.branchesNearbyFragment)?.navigate(action)
        }
        binding.branchNearbyTitleTextView.setDebounceOnClickListener {
            val action =
                BranchesNearbyFragmentDirections.actionBranchNearbyFragmentToSummaryFramgnet()
            findNavControllerSafety(R.id.branchesNearbyFragment)?.navigate(action)
        }
    }

    override fun setUpRecyclerView() {
        _mAdapter = BranchesNearbyAdapter(this)
        binding.branchesRecyclerView.layoutManager = LinearLayoutManager(
            requireContext()
        )
        binding.branchesRecyclerView.adapter = mAdapter
    }

    override fun observeViewModel() {
        mViewModel.uiBranchesNearBy.observe(viewLifecycleOwner) {
            mAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mAdapter = null
    }

    override fun onBranchItemClick(id: BranchId) {
        val action =
            BranchesNearbyFragmentDirections.actionBranchesNearbyFragmentToBranchesDetailFragment(id)
        findNavControllerSafety(R.id.branchesNearbyFragment)?.navigate(action)
    }

    override fun onFavoriteClick(branchId: BranchId) {
        mViewModel.updateFavorite(branchId)
    }
}