package com.arnoract.piggiplan.ui.create

import androidx.navigation.fragment.findNavController
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.base.BaseFragment
import com.arnoract.piggiplan.base.viewBinding
import com.arnoract.piggiplan.core.findNavControllerSafety
import com.arnoract.piggiplan.core.setDebounceOnClickListener
import com.arnoract.piggiplan.databinding.FragmentCreatePartyBinding

class CreatePartyFragment : BaseFragment(R.layout.fragment_create_party) {

    private val binding by viewBinding(FragmentCreatePartyBinding::bind)

    override fun setUpView() {
        binding.toolbarLayout.backImageButton.setDebounceOnClickListener {
            findNavController().popBackStack()
        }
        binding.editFriendImageButton.setDebounceOnClickListener {
            val action =
                CreatePartyFragmentDirections.actionCreatePartyFragmentToEditFriendFragment(0L)
            findNavControllerSafety(R.id.createPartyFragment)?.navigate(action)
        }
    }
}