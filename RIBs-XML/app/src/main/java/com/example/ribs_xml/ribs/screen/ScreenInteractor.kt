package com.example.ribs_xml.ribs.screen

import com.uber.rib.core.Interactor

class ScreenInteractor: Interactor<ScreenInteractor.ScreenPresenter, ScreenRouter>() {

    interface ScreenPresenter {
        fun setString(msg: String)
    }
}