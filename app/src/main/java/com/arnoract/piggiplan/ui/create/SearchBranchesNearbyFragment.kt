package com.arnoract.piggiplan.ui.create

import android.graphics.Paint
import android.view.View
import androidx.navigation.fragment.findNavController
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.base.BaseFragment
import com.arnoract.piggiplan.base.viewBinding
import com.arnoract.piggiplan.core.findNavControllerSafety
import com.arnoract.piggiplan.core.setDebounceOnClickListener
import com.arnoract.piggiplan.databinding.FragmentSearchBranchNearbyBinding
import com.arnoract.piggiplan.ui.custom.FriendAddressItemView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchBranchesNearbyFragment : BaseFragment(R.layout.fragment_search_branch_nearby),
    FriendAddressItemView.OnFriendAddressItemListener {

    private val binding by viewBinding(FragmentSearchBranchNearbyBinding::bind)
    private val mViewModel: SearchBranchesNearbyViewModel by viewModel()

    override fun setUpView() {
        binding.toolbarLayout.backImageButton.setDebounceOnClickListener {
            findNavController().popBackStack()
        }
        binding.editFriendImageButton.setDebounceOnClickListener {
            navigateToEditFriendFragment(0L)
        }
        binding.selectRestaurantTextView.setDebounceOnClickListener {
            navigateToSelectRestaurantFragment(0L)
        }
        binding.restaurantNameTitleTextView.setDebounceOnClickListener {
            mViewModel.navigateToSelectRestaurant()
        }
        binding.searchRestaurantButton.setDebounceOnClickListener {
            mViewModel.searchRestaurantNearByFriends()
        }
        binding.addFriendTextView.setDebounceOnClickListener {
            navigateToEditFriendFragment(0L)
        }
    }

    override fun observeViewModel() {
        mViewModel.friends.observe(viewLifecycleOwner) { friends ->
            if (friends.isNotEmpty()) {
                binding.editFriendImageButton.visibility = View.VISIBLE
                binding.friendViewFlipper.displayedChild = 0
            } else {
                binding.editFriendImageButton.visibility = View.INVISIBLE
                binding.friendViewFlipper.displayedChild = 1
            }

            binding.friendAddressView.removeAllViews()
            friends.forEach {
                val v = FriendAddressItemView(requireContext())
                v.fillData(it)
                v.setOnFriendAddressItemListener(this)
                binding.friendAddressView.addView(v)
            }
        }
        mViewModel.restaurantSelected.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.restaurantViewFlipper.displayedChild = 0
                binding.restaurantNameTitleTextView.apply {
                    text = it.name
                    paintFlags = this.paintFlags or Paint.UNDERLINE_TEXT_FLAG
                }
            } else {
                binding.restaurantViewFlipper.displayedChild = 1
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
        mViewModel.calculateBranchesNearbySuccessEvent.observe(viewLifecycleOwner) {
            navigateToBranchesNearByFragment()
        }
    }

    private fun navigateToEditFriendFragment(id: Long) {
        val action =
            SearchBranchesNearbyFragmentDirections.actionSearchBranchesNearbyFragmentToEditFriendFragment(
                id
            )
        findNavControllerSafety(R.id.searchBranchesNearbyFragment)?.navigate(action)
    }

    private fun navigateToSelectRestaurantFragment(id: Long) {
        val action =
            SearchBranchesNearbyFragmentDirections.actionSearchBranchesNearbyFragmentToSelectRestaurantFragment(
                id
            )
        findNavControllerSafety(R.id.searchBranchesNearbyFragment)?.navigate(action)
    }

    private fun navigateToBranchesNearByFragment() {
        val action =
            SearchBranchesNearbyFragmentDirections.actionSearchBranchesNearbyFragmentToBranchesNearbyFragment()
        findNavControllerSafety(R.id.searchBranchesNearbyFragment)?.navigate(action)
    }

    override fun onFriendAddressClick(id: Long) {
        navigateToEditFriendFragment(id)
    }
}