package com.example.ribs_xml.ribs.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.ribs_xml.R
import com.example.ribs_xml.ribs.root.RootView
import com.example.ribs_xml.storage.LocalStorage
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.BindsInstance
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Scope

class ScreenBuilder (dependency: ParentComponent) : ViewBuilder<ScreenView, ScreenRouter, ScreenBuilder.ParentComponent>(dependency) {
    val interactor = ScreenInteractor()

    fun build(parentViewGroup: ViewGroup): ScreenRouter {
        val view = createView(parentViewGroup)
        val component = DaggerScreenBuilder_Component.builder()
            .parentComponent(dependency)
            .view(view)
            .interactor(interactor)
            .build()
        return component.screenRouter()
    }

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): ScreenView? {
        return inflater.inflate(R.layout.screen_layout, parentViewGroup, false) as ScreenView
    }

    interface ParentComponent {
        @Named("local_memory")
        fun localMemory(): LocalStorage
        fun rootView(): RootView
    }

    @ChatListScope
    @dagger.Component(
        dependencies = [ParentComponent::class]
    )
    interface Component : InteractorBaseComponent<ScreenInteractor>, BuilderComponent {

        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: ScreenInteractor): Builder

            @BindsInstance
            fun view(view: ScreenView): Builder

            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun screenRouter(): ScreenRouter
    }

    @Scope
    @kotlin.annotation.Retention(AnnotationRetention.BINARY)
    internal annotation class ChatListScope

    @Qualifier
    @kotlin.annotation.Retention(AnnotationRetention.BINARY)
    internal annotation class ChatListInternal
}