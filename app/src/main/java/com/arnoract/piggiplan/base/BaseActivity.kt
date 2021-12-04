package com.arnoract.piggiplan.base

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.View.*
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentLayout())

        intent?.extras?.let {
            extractExtras(it)
        }

        setUpInstance()
        setUpView()
        observeViewModel()

        if (savedInstanceState == null) {
            init()
        } else {
            restoreView(savedInstanceState)
        }

        window.decorView.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            window.decorView.getWindowVisibleDisplayFrame(rect)
            val screenHeight = window.decorView.rootView.height
            val keyboardHeight = screenHeight - rect.bottom

            if (keyboardHeight > screenHeight * 0.15) setToImmersiveMode()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) setToImmersiveMode()
    }

    private fun setToImmersiveMode() {
        window.decorView.systemUiVisibility =
            (SYSTEM_UI_FLAG_IMMERSIVE_STICKY or SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or SYSTEM_UI_FLAG_HIDE_NAVIGATION or SYSTEM_UI_FLAG_FULLSCREEN)
    }

    abstract fun getContentLayout(): View

    /**
     * Extract extras from fragment's arguments
     * called when fragment has arguments set.
     */
    open fun extractExtras(arguments: Bundle) {
        //Optional
    }

    /**
     * Instantiate object (e.g., Adapter, Handler)
     */
    open fun setUpInstance() {
        //Optional
    }

    /**
     * Setup view related things (e.g., View.setOnClickListener, recyclerView setUp)
     */
    open fun setUpView() {
        //Optional
    }

    /**
     * Use to observe ViewModel's LiveData.
     */
    open fun observeViewModel() {
        //Optional
    }

    /**
     * Do things when fragment first created. (Load data)
     */
    open fun init() {
        //Optional
    }

    /**
     * Restore view's state from savedInstanceState.
     * Called when configuration change.
     */
    open fun restoreView(savedInstanceState: Bundle) {
        // implement in sub class
    }
}
