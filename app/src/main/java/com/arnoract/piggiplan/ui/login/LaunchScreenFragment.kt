package com.arnoract.piggiplan.ui.login

import android.annotation.SuppressLint
import androidx.navigation.fragment.findNavController
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.base.BaseFragment
import com.arnoract.piggiplan.core.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class LaunchScreenFragment : BaseFragment(R.layout.fragment_launch_screen) {

    private val mViewModel: LaunchScreenViewModel by viewModel()

    override fun observeViewModel() {
        mViewModel.navigateToHomePageEvent.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_launchScreenFragment_to_HomeFragment)
        }
        mViewModel.initialFetchDataFailEvent.observe(viewLifecycleOwner) {
            requireContext().toast(it)
        }
    }
}