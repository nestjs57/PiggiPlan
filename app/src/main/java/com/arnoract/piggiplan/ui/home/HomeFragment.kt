package com.arnoract.piggiplan.ui.home

import androidx.navigation.fragment.findNavController
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.base.BaseFragment
import com.arnoract.piggiplan.base.viewBinding
import com.arnoract.piggiplan.core.setDebounceOnClickListener
import com.arnoract.piggiplan.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val mViewModel: HomeViewModel by viewModel()

    override fun setUpView() {
        binding.emptyStateLayout.searchBranchesButton.setDebounceOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_SearchBranchesNearbyFragment)
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
    }
}