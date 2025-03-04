package ru.dodopizza.app.screens.foodmenu.self

import android.view.View
import com.dodopizza.order.R
import com.dodopizza.order.feature.menu.adapters.menuitem.HalvesAnimationMenuItemVH
import io.github.kakaocup.kakao.common.views.KView
import org.hamcrest.Matcher
import ru.dodopizza.app.screens.AbstractRecyclerItem

/**
 * Карточка продукта пицца из половинок в меню
 */
class MenuPizzaHalvesItem(
  parent: Matcher<View>,
) : AbstractRecyclerItem<MenuRegularItem>(
    parent = parent,
    layoutId = R.layout.halves_animation_menu_item,
    viewHolderClass = HalvesAnimationMenuItemVH::class.java
) {

  private val self: KView
    get() = bind(parent) { withId(R.id.halves_animation_menu_item_card) }

  fun open() = step(description = "Открыть карточку") { self { click() } }
}
