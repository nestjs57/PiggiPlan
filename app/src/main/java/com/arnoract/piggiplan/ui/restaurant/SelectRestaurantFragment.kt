package com.arnoract.piggiplan.ui.restaurant

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.base.BaseFragment
import com.arnoract.piggiplan.base.viewBinding
import com.arnoract.piggiplan.databinding.FragmentSelectRestaurantBinding

class SelectRestaurantFragment : BaseFragment(R.layout.fragment_select_restaurant) {

    private val binding by viewBinding(FragmentSelectRestaurantBinding::bind)
    private val args: SelectRestaurantFragmentArgs by navArgs()

    override fun setUpView() {
        binding.toolbarLayout.backImageButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}