package com.arnoract.piggiplan.ui.create

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.base.BaseFragment
import com.arnoract.piggiplan.base.viewBinding
import com.arnoract.piggiplan.core.setDebounceOnClickListener
import com.arnoract.piggiplan.databinding.FragmentEditFriendBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EditFriendFragment : BaseFragment(R.layout.fragment_edit_friend) {

    private val binding by viewBinding(FragmentEditFriendBinding::bind)
    private val args: EditFriendFragmentArgs by navArgs()
    private val mViewModel: EditFriendViewModel by viewModel {
        parametersOf(args.id)
    }

    override fun setUpView() {
        binding.toolbarLayout.backImageButton.setDebounceOnClickListener {
            findNavController().popBackStack()
        }
        binding.selectAddressTextView.setDebounceOnClickListener {
            findNavController().navigate(R.id.action_editFriendFragment_to_selectAddressFragment)
        }
    }
}