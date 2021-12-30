package com.arnoract.piggiplan.ui.branch

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.text.Spanned
import android.text.SpannedString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.base.BaseFragment
import com.arnoract.piggiplan.base.viewBinding
import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.databinding.FragmentBranchDetailInformationBinding
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.*

class BranchDetailInformationFragment : BaseFragment(R.layout.fragment_branch_detail_information) {

    private val binding by viewBinding(FragmentBranchDetailInformationBinding::bind)
    private val mViewModel: BranchDetailInformationViewModel by viewModel {
        parametersOf(requireArguments().getLong(EXTRA_BRANCH_ID))
    }

    companion object {
        private const val EXTRA_BRANCH_ID = "extra-branch-id"
    }

    fun newInstance(branchId: BranchId): BranchDetailInformationFragment {
        return BranchDetailInformationFragment().apply {
            arguments = bundleOf(EXTRA_BRANCH_ID to branchId)
        }
    }

    override fun observeViewModel() {
        mViewModel.phoneNumber.observe(viewLifecycleOwner) {
            binding.phoneNumberTextView.apply {
                movementMethod = LinkMovementMethod.getInstance()
                highlightColor = Color.TRANSPARENT
                text = buildPhoneNumberText(it)
            }
        }
        mViewModel.latLng.observe(viewLifecycleOwner) {
            binding.locationTextView.apply {
                movementMethod = LinkMovementMethod.getInstance()
                highlightColor = Color.TRANSPARENT
                text = buildLocationText()
            }
        }
        mViewModel.branchName.observe(viewLifecycleOwner) {
            binding.branchNameTextView.text = it
        }
        mViewModel.branchDescription.observe(viewLifecycleOwner) {
            binding.branchDescription.text = it
        }
        mViewModel.photoImage.observe(viewLifecycleOwner) {
            it ?: return@observe
            binding.photoImageView.apply {
                Glide
                    .with(requireContext())
                    .load(it)
                    .centerCrop()
                    .into(this)
            }
        }
        mViewModel.openTelephoneEvent.observe(viewLifecycleOwner) {
            try {
                startActivity(Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$it")
                })
            } catch (e: Exception) {
                MaterialAlertDialogBuilder(requireContext())
                    .setMessage(e.message.toString())
                    .setPositiveButton(resources.getString(R.string.action_confirm)) { _, _ ->
                        //Do Nothing
                    }
                    .show()
            }
        }
        mViewModel.openGoogleMapEvent.observe(viewLifecycleOwner) {
            try {
                startActivity(Intent(Intent.ACTION_VIEW).apply {
                    val uri = String.format(
                        Locale.ENGLISH,
                        "http://maps.google.com/maps?q=loc:%f,%f",
                        it.latitude,
                        it.longitude
                    )
                    data = Uri.parse(uri)
                })
            } catch (e: Exception) {
                MaterialAlertDialogBuilder(requireContext())
                    .setMessage(e.message.toString())
                    .setPositiveButton(resources.getString(R.string.action_confirm)) { _, _ ->
                        //Do Nothing
                    }
                    .show()
            }
        }
    }

    private fun buildPhoneNumberText(phoneNumber: String): SpannedString {
        return buildSpannedString {
            append(phoneNumber)
            append(" ")
                .bold {
                    append(
                        getString(R.string.branch_detail_information_action_make_a_call),
                        object : ClickableSpan() {
                            override fun onClick(widget: View) {
                                mViewModel.openTelephone()
                            }

                            override fun updateDrawState(ds: TextPaint) {
                                ds.color = requireContext().getColor(R.color.purple_700)
                                ds.isUnderlineText = true
                            }
                        },
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
        }
    }

    private fun buildLocationText(): SpannedString {
        return buildSpannedString {
            append(
                getString(R.string.branch_detail_information_action_open_google_map),
                object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        mViewModel.openGoogleMap()
                    }

                    override fun updateDrawState(ds: TextPaint) {
                        ds.color = requireContext().getColor(R.color.purple_700)
                        ds.isUnderlineText = true
                    }
                },
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }

}