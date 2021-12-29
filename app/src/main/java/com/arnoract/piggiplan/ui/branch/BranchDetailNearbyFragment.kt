package com.arnoract.piggiplan.ui.branch

import androidx.core.os.bundleOf
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.base.BaseFragment
import com.arnoract.piggiplan.base.viewBinding
import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.databinding.FragmentBranchDetailNearbyBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class BranchDetailNearbyFragment : BaseFragment(R.layout.fragment_branch_detail_nearby) {

    private val binding by viewBinding(FragmentBranchDetailNearbyBinding::bind)
    private val mViewModel: BranchDetailNearbyViewModel by viewModel {
        parametersOf(requireArguments().getLong(EXTRA_BRANCH_ID))
    }

    companion object {
        private const val EXTRA_BRANCH_ID = "extra-branch-id"
    }

    fun newInstance(branchId: BranchId): BranchDetailNearbyFragment {
        return BranchDetailNearbyFragment().apply {
            arguments = bundleOf(EXTRA_BRANCH_ID to branchId)
        }
    }
}