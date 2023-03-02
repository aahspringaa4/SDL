package com.example.ribs_xml.ribs.root

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.ribs_xml.R
import com.example.ribs_xml.ribs.screen.ScreenBuilder
import com.example.ribs_xml.storage.LocalStorage
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Named
import javax.inject.Scope

class RootBuilder(dependency: ParentComponent): ViewBuilder<RootView, RootRouter, RootBuilder.ParentComponent>(dependency) {
    fun build(parentViewGroup: ViewGroup): RootRouter {
        val view = createView(parentViewGroup)
        val interactor = RootInteractor()
        val component = DaggerRootBuilder_Component.builder()
            .parentComponent(dependency)
            .view(view)
            .interactor(interactor)
            .build()
        return component.rootRouter()
    }

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): RootView {
        return inflater.inflate(R.layout.activity_main, parentViewGroup, false) as RootView
    }

    interface ParentComponent {
        @Named("local_memory")
        fun localMemory(): LocalStorage
    }

    @dagger.Module
    abstract class Module {
        @RootScope
        @Binds
        internal abstract fun presenter(view: RootView): RootInteractor.RootPresenter

        @dagger.Module
        companion object {
            @JvmStatic
            @Provides
            @RootScope
            internal fun router(component: Component, view: RootView, interactor: RootInteractor): RootRouter {
                return RootRouter(
                    view,
                    interactor,
                    component,
                    ScreenBuilder(object : ScreenBuilder.ParentComponent {
                        override fun rootView(): RootView {
                            return view
                        }

                        override fun localMemory(): LocalStorage {
                            return interactor.localMemory!!
                        }
                    }))
            }
        }
    }

    @RootScope
    @dagger.Component(modules = [Module::class], dependencies = [ParentComponent::class])
    interface Component : InteractorBaseComponent<RootInteractor>, BuilderComponent, ScreenBuilder.ParentComponent {

        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: RootInteractor): Builder

            @BindsInstance
            fun view(view: RootView): Builder

            fun parentComponent(component: ParentComponent): Builder

            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun rootRouter(): RootRouter
    }

    @Scope
    @Retention(RetentionPolicy.CLASS)
    internal annotation class RootScope
}