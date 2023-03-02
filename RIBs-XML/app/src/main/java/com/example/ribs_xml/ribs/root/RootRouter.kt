package com.example.ribs_xml.ribs.root

import com.example.ribs_xml.ribs.screen.ScreenBuilder
import com.example.ribs_xml.ribs.screen.ScreenRouter
import com.uber.rib.core.ViewRouter

class RootRouter internal constructor(
    view: RootView,
    interactor: RootInteractor,
    component: RootBuilder.Component,
    private val screenBuilder: ScreenBuilder
) : ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component) {

    private var screenRouter: ScreenRouter? = null

    internal fun attachLoggedIn() {
        screenRouter = screenBuilder.build(view)
        attachChild(screenRouter)
    }
}