package com.arnoract.piggiplan.ui.branch

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.base.BaseFragment
import com.arnoract.piggiplan.base.viewBinding
import com.arnoract.piggiplan.databinding.FragmentBranchDetailBinding
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class BranchDetailFragment : BaseFragment(R.layout.fragment_branch_detail) {

    private val binding by viewBinding(FragmentBranchDetailBinding::bind)
    private val args: BranchDetailFragmentArgs by navArgs()
    private val mViewModel: BranchDetailViewModel by viewModel {
        parametersOf(args.branchId)
    }

    override fun init() {
        binding.branchDetailTabLayout.selectTab(binding.branchDetailTabLayout.getTabAt(0))
    }

    override fun setUpView() {
        binding.toolbarLayout.backImageButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.toolbarLayout.drawableEndImageButton.setOnClickListener {
            mViewModel.updateFavorite()
        }
        binding.branchDetailTabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                replaceFragment(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // Do nothing
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                replaceFragment(tab.position)
            }

            private fun replaceFragment(position: Int) {
                val fragment = when (position) {
                    0 -> BranchDetailInformationFragment().newInstance(args.branchId)
                    1 -> BranchDetailNearbyFragment().newInstance(args.branchId)
                    else -> null
                }
                fragment?.let {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.branchDetailLayout, it)
                        .commit()
                }
            }
        })
    }

    override fun observeViewModel() {
        mViewModel.branchName.observe(viewLifecycleOwner) {
            binding.toolbarLayout.toolbarTitleTextView.text = it
        }
        mViewModel.isFavorite.observe(viewLifecycleOwner) {
            binding.toolbarLayout.drawableEndImageButton.apply {
                val drawable =
                    if (it == true) R.drawable.ic_favorite else R.drawable.ic_favorite_border
                setImageResource(drawable)
                visibility = View.VISIBLE
            }
        }
    }
}