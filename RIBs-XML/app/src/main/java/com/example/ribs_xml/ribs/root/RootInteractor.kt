package com.example.ribs_xml.ribs.root

import com.example.ribs_xml.storage.LocalStorage
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject
import javax.inject.Named

@RibInteractor
open class RootInteractor: Interactor<RootInteractor.RootPresenter, RootRouter>() {
    @Inject
    @JvmField
    @field:Named("local_memory")
    internal var localMemory: LocalStorage? = null

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        router.attachLoggedIn()
    }

    interface RootPresenter
}