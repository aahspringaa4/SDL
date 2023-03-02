package com.example.ribs_xml.ribs.screen

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.example.ribs_xml.ribs.root.RootInteractor


class ScreenView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : FrameLayout(context, attrs, defStyle), ScreenInteractor.ScreenPresenter {
    override fun setString(msg: String) {

    }
}