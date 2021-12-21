package com.arnoract.piggiplan.ui.restaurant

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.base.BaseFragment
import com.arnoract.piggiplan.base.viewBinding
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantId
import com.arnoract.piggiplan.core.dimenToPx
import com.arnoract.piggiplan.core.toast
import com.arnoract.piggiplan.databinding.FragmentSelectRestaurantBinding
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SelectRestaurantFragment : BaseFragment(R.layout.fragment_select_restaurant),
    RestaurantViewHolder.OnRestaurantItemClickListener {

    companion object {
        private const val RESTAURANT_SPAN_COUNT = 2
    }

    private val binding by viewBinding(FragmentSelectRestaurantBinding::bind)
    private val args: SelectRestaurantFragmentArgs by navArgs()
    private val mViewModel: SelectRestaurantViewModel by viewModel {
        parametersOf(args.id)
    }

    private var _mAdapter: RestaurantAdapter? = null
    private val mAdapter
        get() = _mAdapter!!

    override fun setUpView() {
        _mAdapter = RestaurantAdapter(this)

        binding.restaurantRecyclerView.layoutManager =
            GridLayoutManager(context, RESTAURANT_SPAN_COUNT)
        binding.restaurantRecyclerView.addItemDecoration(
            LayoutMarginDecoration(
                RESTAURANT_SPAN_COUNT,
                requireContext().dimenToPx(
                    R.dimen.space_normal
                )
            )
        )
        binding.restaurantRecyclerView.adapter = mAdapter

        binding.toolbarLayout.backImageButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun observeViewModel() {
        mViewModel.restaurants.observe(viewLifecycleOwner) {
            mAdapter.submitList(it)
        }
        mViewModel.getRestaurantFailEvent.observe(viewLifecycleOwner) {
            requireContext().toast(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mAdapter = null
    }

    override fun onRestaurantItemClick(id: RestaurantId) {
        mViewModel.selectRestaurant(id)
    }
}