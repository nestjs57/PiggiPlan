package com.arnoract.piggiplan.ui.home

import androidx.navigation.fragment.findNavController
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.base.BaseFragment
import com.arnoract.piggiplan.base.viewBinding
import com.arnoract.piggiplan.core.db.model.history.HistoryId
import com.arnoract.piggiplan.core.findNavControllerSafety
import com.arnoract.piggiplan.core.setDebounceOnClickListener
import com.arnoract.piggiplan.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val mViewModel: HomeViewModel by viewModel()

    override fun setUpView() {
        binding.emptyStateLayout.searchBranchesButton.setDebounceOnClickListener {
            mViewModel.navigationToSearchBranchNearby()
        }
        binding.editBranchNearbyLayout.searchBranchesNearbyLayout.setDebounceOnClickListener {
            mViewModel.navigationToSearchBranchNearby()
        }
        binding.favoriteImageButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
        }
    }

    override fun observeViewModel() {
        mViewModel.haveFavorite.observe(viewLifecycleOwner) { haveFavorite ->
            val drawable =
                if (haveFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
            binding.favoriteImageButton.setImageResource(drawable)
        }
        mViewModel.hasHistories.observe(viewLifecycleOwner) {
            binding.viewFlipper.displayedChild = if (it) 1 else 0
        }
        mViewModel.navigateSearchBranchNearbyEvent.observe(viewLifecycleOwner) {
            navigateToSearchBranchNearbyFragment(it)
        }
    }

    private fun navigateToSearchBranchNearbyFragment(historyId: HistoryId = "") {
        val action =
            HomeFragmentDirections.actionHomeFragmentToSearchBranchesNearbyFragment(historyId)
        findNavControllerSafety(R.id.homeFragment)?.navigate(action)
    }
}