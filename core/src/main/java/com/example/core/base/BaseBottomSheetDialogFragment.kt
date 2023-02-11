package com.example.core.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.core.extentions.bindView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import timber.log.Timber

//abstract class BaseBottomSheetDialogFragment<B : ViewBinding, VM : ViewModel> :
//    BottomSheetDialogFragment() {
//    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
//
//    protected abstract val mViewModel: VM
//
//    var binding: B? = null
//
//    abstract fun onFragmentCreated()
//    abstract fun slideCallBack(): BottomSheetBehavior.BottomSheetCallback
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = bindView()
//        onFragmentCreated()
//        return binding?.root
//    }
//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val dialog = BottomSheetDialog(requireContext(), theme)
//        dialog.setOnShowListener {
//            val bottomSheetDialog = it as BottomSheetDialog
//            val parentLayout =
//                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
//            parentLayout?.let { view ->
//                bottomSheetBehavior = BottomSheetBehavior.from(view)
//                setupFullHeight(view)
//                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
//                bottomSheetBehavior.addBottomSheetCallback(slideCallBack())
//            }
//        }
//        return dialog
//    }
//
//    fun baseCallBack(
//        obsShowHeader: ObservableBoolean,
//        onStateHiddenCallBack: () -> Unit
//    ) = object : BottomSheetBehavior.BottomSheetCallback() {
//        override fun onStateChanged(bottomSheet: View, newState: Int) {
//            Timber.e("$newState")
//            handleStates(obsShowHeader, newState, onStateHiddenCallBack)
//        }
//
//        override fun onSlide(bottomSheet: View, slideOffset: Float) {
//            obsShowHeader.set(false)
//        }
//    }
//
////    fun showErrorDialog(message: String?) {
////        context?.showErrorDialog(message)
////    }
//
//    fun handleStates(
//        obsShowHeader: ObservableBoolean,
//        newState: Int,
//        onStateHiddenCallBack: () -> Unit
//    ) {
//        when (newState) {
//            BottomSheetBehavior.STATE_EXPANDED -> {
//                obsShowHeader.set(true)
//            }
//            BottomSheetBehavior.STATE_COLLAPSED -> {
//
//            }
//            BottomSheetBehavior.STATE_HALF_EXPANDED -> {
//                obsShowHeader.set(false)
//            }
//            BottomSheetBehavior.STATE_HIDDEN -> {
//                onStateHiddenCallBack.invoke()
//            }
//
//        }
//    }
//
//    private fun setupFullHeight(bottomSheet: View) {
//        val layoutParams = bottomSheet.layoutParams
//        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
//        bottomSheet.layoutParams = layoutParams
//    }
//
////    fun closeFragment() {
////        hideKeyboard()
////        dismiss()
////    }
////
////    fun showProgress(show: Boolean = true) {
////        castToActivity<BaseActivity<*, *>> {
////            it?.baseShowProgress?.set(show)
////        }
////    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding = null
//    }
//}