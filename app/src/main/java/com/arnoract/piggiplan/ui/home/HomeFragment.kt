package com.arnoract.piggiplan.ui.home

import androidx.navigation.fragment.findNavController
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.base.BaseFragment
import com.arnoract.piggiplan.base.viewBinding
import com.arnoract.piggiplan.core.setDebounceOnClickListener
import com.arnoract.piggiplan.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun setUpView() {
        binding.emptyStateLayout.createPartyButton.setDebounceOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_CreatePartyFragment)
        }
    }
}