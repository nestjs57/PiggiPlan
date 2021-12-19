package com.arnoract.piggiplan.ui.create

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.base.BaseFragment
import com.arnoract.piggiplan.base.viewBinding
import com.arnoract.piggiplan.core.setDebounceOnClickListener
import com.arnoract.piggiplan.core.toast
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
            navigateToSelectAddressFragment()
        }
        binding.addressNameTextview.setDebounceOnClickListener {
            navigateToSelectAddressFragment()
        }
        binding.saveFriendButton.setDebounceOnClickListener {
            val name = binding.nameEditText.text.toString().trim()
            mViewModel.save(name)
        }
    }

    override fun observeViewModel() {
        mViewModel.name.observe(viewLifecycleOwner) {
            it ?: return@observe
            binding.nameEditText.setText(it)
        }
        mViewModel.addressName.observe(viewLifecycleOwner) {
            it ?: return@observe
            binding.addressViewFlipper.displayedChild = if (it.isBlank()) 0 else 1
            binding.addressNameTextview.text = it
        }
        mViewModel.saveFriendSuccessEvent.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
        mViewModel.isBlankFriendNameEvent.observe(viewLifecycleOwner) {
            binding.textInputLayout.error =
                if (it) getString(R.string.create_party_edit_friend_blank_name_error) else null
        }
        mViewModel.isBlankAddressNameEvent.observe(viewLifecycleOwner) {
            val color = if (it) R.color.dark_red else R.color.gray700
            binding.selectAddressTextView.setTextColor(requireContext().getColor(color))
        }
        mViewModel.saveFailExceptionEvent.observe(viewLifecycleOwner) {
            requireContext().toast(it)
        }
    }

    private fun navigateToSelectAddressFragment() {
        findNavController().navigate(R.id.action_editFriendFragment_to_selectAddressFragment)
    }
}