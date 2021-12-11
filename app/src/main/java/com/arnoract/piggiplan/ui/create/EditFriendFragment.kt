package com.arnoract.piggiplan.ui.create

import androidx.navigation.fragment.findNavController
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.base.BaseFragment
import com.arnoract.piggiplan.base.viewBinding
import com.arnoract.piggiplan.core.setDebounceOnClickListener
import com.arnoract.piggiplan.databinding.FragmentEditFriendBinding

class EditFriendFragment : BaseFragment(R.layout.fragment_edit_friend) {

    private val binding by viewBinding(FragmentEditFriendBinding::bind)

    override fun setUpView() {
        binding.toolbarLayout.backImageButton.setDebounceOnClickListener {
            findNavController().popBackStack()
        }
    }
}