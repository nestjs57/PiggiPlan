package com.arnoract.piggiplan.ui.branch

import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.base.BaseFragment
import com.arnoract.piggiplan.base.viewBinding
import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.core.findNavControllerSafety
import com.arnoract.piggiplan.databinding.FragmentBranchDetailNearbyBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class BranchDetailNearbyFragment : BaseFragment(R.layout.fragment_branch_detail_nearby),
    BranchDetailNearbyViewHolder.OnBranchItemClickListener {

    private val binding by viewBinding(FragmentBranchDetailNearbyBinding::bind)
    private val mViewModel: BranchDetailNearbyViewModel by viewModel {
        parametersOf(requireArguments().getLong(EXTRA_BRANCH_ID))
    }
    private var _mAdapter: BranchDetailNearbyAdapter? = null
    private val mAdapter
        get() = _mAdapter!!

    companion object {
        private const val EXTRA_BRANCH_ID = "extra-branch-id"
    }

    fun newInstance(branchId: BranchId): BranchDetailNearbyFragment {
        return BranchDetailNearbyFragment().apply {
            arguments = bundleOf(EXTRA_BRANCH_ID to branchId)
        }
    }

    override fun setUpView() {
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        _mAdapter = BranchDetailNearbyAdapter(this)
        binding.branchesNearbyRecyclerView.layoutManager = LinearLayoutManager(
            requireContext()
        )
        binding.branchesNearbyRecyclerView.adapter = mAdapter
    }

    override fun observeViewModel() {
        mViewModel.uiBranchDetailNearby.observe(viewLifecycleOwner) {
            mAdapter.submitList(it)
        }
        mViewModel.getBranchByBranchIdFailException.observe(viewLifecycleOwner) {
            MaterialAlertDialogBuilder(requireContext())
                .setMessage(it)
                .setPositiveButton(resources.getString(R.string.action_confirm)) { _, _ ->
                    //Do Nothing
                }
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mAdapter = null
    }

    override fun onBranchItemClick(id: BranchId) {
        val action =
            BranchDetailFragmentDirections.actionBranchesNearbyFragmentToBranchesNearbyFragment(
                id
            )
        findNavControllerSafety(R.id.branchDetailFragment)?.navigate(action)
    }
}