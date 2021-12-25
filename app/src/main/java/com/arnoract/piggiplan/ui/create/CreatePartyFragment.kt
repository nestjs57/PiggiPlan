package com.arnoract.piggiplan.ui.create

import android.view.View
import androidx.navigation.fragment.findNavController
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.base.BaseFragment
import com.arnoract.piggiplan.base.viewBinding
import com.arnoract.piggiplan.core.findNavControllerSafety
import com.arnoract.piggiplan.core.setDebounceOnClickListener
import com.arnoract.piggiplan.databinding.FragmentCreatePartyBinding
import com.arnoract.piggiplan.ui.custom.FriendAddressItemView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreatePartyFragment : BaseFragment(R.layout.fragment_create_party),
    FriendAddressItemView.OnFriendAddressItemListener {

    private val binding by viewBinding(FragmentCreatePartyBinding::bind)
    private val mViewModel: CreatePartyViewModel by viewModel()

    override fun setUpView() {
        binding.toolbarLayout.backImageButton.setDebounceOnClickListener {
            findNavController().popBackStack()
        }
        binding.editFriendImageButton.setDebounceOnClickListener {
            navigateToEditFriendFragment(0L)
        }
        binding.selectRestaurantImageButton.setDebounceOnClickListener {
            navigateToSelectRestaurantFragment(0L)
        }
        binding.restaurantNameTitleTextView.setDebounceOnClickListener {
            mViewModel.navigateToSelectRestaurant()
        }
        binding.searchRestaurantButton.setDebounceOnClickListener {
            mViewModel.searchRestaurantNearByFriends()
        }
    }

    override fun observeViewModel() {
        mViewModel.friends.observe(viewLifecycleOwner) { friends ->
            binding.friendAddressView.visibility =
                if (friends.isNotEmpty()) View.VISIBLE else View.GONE
            binding.friendAddressView.removeAllViews()
            friends.forEach {
                val v = FriendAddressItemView(requireContext())
                v.fillData(it)
                v.setOnFriendAddressItemListener(this)
                binding.friendAddressView.addView(v)
            }
        }
        mViewModel.restaurantSelected.observe(viewLifecycleOwner) {
            if (it == null) {
                binding.restaurantNameTitleTextView.visibility = View.GONE
                binding.selectRestaurantImageButton.visibility = View.VISIBLE
            } else {
                binding.restaurantNameTitleTextView.visibility = View.VISIBLE
                binding.selectRestaurantImageButton.visibility = View.GONE
                binding.restaurantNameTitleTextView.text = it.name
            }
        }
        mViewModel.onNavigateToSelectRestaurant.observe(viewLifecycleOwner) {
            navigateToSelectRestaurantFragment(it)
        }
        mViewModel.isIncompleteDataEvent.observe(viewLifecycleOwner) {
            MaterialAlertDialogBuilder(requireContext())
                .setMessage(resources.getString(R.string.create_party_incomplete_data_dialog_description))
                .setPositiveButton(resources.getString(R.string.action_confirm)) { _, _ ->
                    //Do Nothing
                }
                .show()
        }
    }

    private fun navigateToEditFriendFragment(id: Long) {
        val action =
            CreatePartyFragmentDirections.actionCreatePartyFragmentToEditFriendFragment(id)
        findNavControllerSafety(R.id.createPartyFragment)?.navigate(action)
    }

    private fun navigateToSelectRestaurantFragment(id: Long) {
        val action =
            CreatePartyFragmentDirections.actionCreatePartyFragmentToSelectRestaurantFragment(id)
        findNavControllerSafety(R.id.createPartyFragment)?.navigate(action)
    }

    override fun onFriendAddressClick(id: Long) {
        navigateToEditFriendFragment(id)
    }
}