package com.arnoract.piggiplan.ui.favorite

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.base.BaseFragment
import com.arnoract.piggiplan.base.viewBinding
import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.core.findNavControllerSafety
import com.arnoract.piggiplan.core.setDebounceOnClickListener
import com.arnoract.piggiplan.databinding.FragmentFavoriteBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment(R.layout.fragment_favorite),
    FavoriteViewHolder.OnBranchItemClickListener {

    private val binding by viewBinding(FragmentFavoriteBinding::bind)
    private val mViewModel: FavoriteViewModel by viewModel()
    private var _mAdapter: FavoriteAdapter? = null
    private val mAdapter
        get() = _mAdapter!!

    override fun setUpView() {
        setUpRecyclerView()
        binding.toolbarLayout.backImageButton.setDebounceOnClickListener {
            findNavController().popBackStack()
        }
        binding.toolbarLayout.drawableEndImageButton.apply {
            setImageResource(R.drawable.ic_bin)
            visibility = View.VISIBLE
        }
        binding.toolbarLayout.drawableEndImageButton.setOnClickListener {
            mViewModel.showConfirmDeleteAllFavorite()
        }
    }

    private fun setUpRecyclerView() {
        _mAdapter = FavoriteAdapter(this)
        binding.favoriteRecyclerView.layoutManager = LinearLayoutManager(
            requireContext()
        )
        binding.favoriteRecyclerView.adapter = mAdapter
    }

    override fun observeViewModel() {
        mViewModel.uiFavorite.observe(viewLifecycleOwner) {
            it ?: return@observe
            mAdapter.submitList(it)
        }
        mViewModel.showAlertConfirmDeleteAllFavoriteEvent.observe(viewLifecycleOwner) {
            MaterialAlertDialogBuilder(requireContext())
                .setMessage(resources.getString(R.string.my_favorite_confirm_dialog_delete_all_favorite_description))
                .setNegativeButton(resources.getString(R.string.action_cancel)) { _, _ ->
                    //Do Nothing
                }
                .setPositiveButton(resources.getString(R.string.action_confirm)) { _, _ ->
                    mViewModel.deleteAllFavorite()
                }
                .show()
        }
        mViewModel.isEmptyFavorites.observe(viewLifecycleOwner) {
            binding.toolbarLayout.drawableEndImageButton.visibility =
                if (it) View.GONE else View.VISIBLE
            binding.favoriteTitleTextView.visibility = if (it) View.GONE else View.VISIBLE
            binding.viewFlipper.displayedChild = if (it) 1 else 0
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mAdapter = null
    }

    override fun onBranchItemClick(id: BranchId) {
        val action =
            FavoriteFragmentDirections.actionFavoriteFragmentToBranchesDetailFragment(id)
        findNavControllerSafety(R.id.favoriteFragment)?.navigate(action)
    }

    override fun onFavoriteClick(branchId: BranchId) {
        mViewModel.updateFavorite(branchId)
    }
}