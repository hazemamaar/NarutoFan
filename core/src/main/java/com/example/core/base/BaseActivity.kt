package com.example.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.core.extentions.bindView


abstract class BaseActivity<B : ViewBinding, VM : ViewModel> : AppCompatActivity() {
    companion object {
        const val SCREEN_ID = "SCREEN_ID"
    }

    protected abstract val mViewModel: VM
    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindView()
    }

//    fun setOpacityBackground(view: View, @ColorRes color: Int) {
//        view.setBackgroundColor(
//            try {
//                ContextCompat.getColor(this, color)
//            } catch (e: Resources.NotFoundException) {
//                0
//            }
//        )
//    }

}
