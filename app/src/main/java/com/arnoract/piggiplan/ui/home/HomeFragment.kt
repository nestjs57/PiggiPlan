package com.arnoract.piggiplan.ui.home

import android.annotation.SuppressLint
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.base.BaseFragment
import com.arnoract.piggiplan.base.viewBinding
import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.core.db.model.history.HistoryId
import com.arnoract.piggiplan.core.findNavControllerSafety
import com.arnoract.piggiplan.core.setDebounceOnClickListener
import com.arnoract.piggiplan.databinding.FragmentHomeBinding
import com.arnoract.piggiplan.ui.branch.BranchAdapter
import com.arnoract.piggiplan.ui.branch.BranchViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.fragment_home),
    BranchViewHolder.OnBranchItemClickListener {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val mViewModel: HomeViewModel by viewModel()
    private var _mAdapter: BranchAdapter? = null
    private val mAdapter
        get() = _mAdapter!!

    override fun setUpView() {
        binding.favoriteImageButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
        }
        binding.searchBranchNameEditText.doOnTextChanged { text, _, _, _ ->
            mViewModel.filterBranch(text.toString().trim())
        }
        binding.searchBranchNearbyActionButton.setDebounceOnClickListener {
            mViewModel.navigationToSearchBranchNearby()
        }
    }

    override fun setUpRecyclerView() {
        _mAdapter = BranchAdapter(this)
        binding.branchRecyclerView.layoutManager = LinearLayoutManager(
            requireContext()
        )
        binding.branchRecyclerView.adapter = mAdapter
    }

    @SuppressLint("ResourceAsColor")
    override fun observeViewModel() {
        mViewModel.uiBranches.observe(viewLifecycleOwner) {
            mAdapter.submitList(it)
        }
        mViewModel.haveFavorite.observe(viewLifecycleOwner) { haveFavorite ->
            val drawable =
                if (haveFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
            binding.favoriteImageButton.setImageResource(drawable)
        }
        mViewModel.navigateSearchBranchNearbyEvent.observe(viewLifecycleOwner) {
            navigateToSearchBranchNearbyFragment(it)
        }
        mViewModel.hasHistories.observe(viewLifecycleOwner) {
            val drawable = if (it) R.drawable.ic_edit else R.drawable.ic_plus
            binding.searchBranchNearbyActionButton.setImageResource(drawable)
        }
        mViewModel.filterBranchNotFoundEvent.observe(viewLifecycleOwner) {
            binding.BranchesViewFlipper.displayedChild = if (it) 1 else 0
        }
    }

    private fun navigateToSearchBranchNearbyFragment(historyId: HistoryId = "") {
        val action =
            HomeFragmentDirections.actionHomeFragmentToSearchBranchesNearbyFragment(historyId)
        findNavControllerSafety(R.id.homeFragment)?.navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mAdapter = null
    }

    override fun onBranchItemClick(id: BranchId) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToBranchDetailFragment(id)
        findNavControllerSafety(R.id.homeFragment)?.navigate(action)
    }
}