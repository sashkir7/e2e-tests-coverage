package ru.dodopizza.app.screens.foodmenu.self

import android.view.View
import com.dodopizza.order.R
import com.dodopizza.order.feature.menu.adapters.menuitem.HeroMenuItemVH
import io.github.kakaocup.kakao.common.views.KView
import org.hamcrest.Matcher
import ru.dodopizza.app.screens.AbstractRecyclerItem

/**
 * Карточка hero-продукта в меню
 * Выделяется укрупненной ячейкой и дизайном
 */
class HeroMenuItem(
  parent: Matcher<View>,
) : AbstractRecyclerItem<HeroMenuItem>(
    parent = parent,
    layoutId = R.layout.hero_menu_item_product_list,
    viewHolderClass = HeroMenuItemVH::class.java
) {

  private val self: KView
    get() = bind(parent) { withId(R.id.hero_menu_item_card) }

  fun verifyIsLoaded() = step(
      description = "Проверить, что карточка Hero-продукта успешно загружена"
  ) { self { isDisplayed() } }
}
