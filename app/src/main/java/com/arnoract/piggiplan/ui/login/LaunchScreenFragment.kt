package com.arnoract.piggiplan.ui.login

import androidx.navigation.fragment.findNavController
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class LaunchScreenFragment : BaseFragment(R.layout.fragment_launch_screen) {

    private val mViewModel: LaunchScreenViewModel by viewModel()

    override fun observeViewModel() {
        mViewModel.navigateToHomePageEvent.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_launchScreenFragment_to_HomeFragment)
        }
    }
}