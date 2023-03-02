package com.example.ribs_xml.ribs.screen

import android.view.ViewGroup
import com.uber.rib.core.Router
import com.uber.rib.core.ViewRouter

class ScreenRouter(
    view: ScreenView,
    interactor: ScreenInteractor,
    component: ScreenBuilder.Component) : ViewRouter<ScreenView, ScreenInteractor, ScreenBuilder.Component>(view, interactor, component)