package ru.dodopizza.app.screens.foodmenu.self

import android.view.View
import com.dodopizza.order.R
import com.dodopizza.order.feature.menu.adapters.promotion.FoodMenuPromotingProductViewHolder
import org.hamcrest.Matcher
import ru.dodopizza.app.screens.AbstractRecyclerItem

/**
 * Карточка продукта в блоке "Вам понравится" в меню
 */
class MenuPromotionItem(
  parent: Matcher<View>,
) : AbstractRecyclerItem<MenuPromotionItem>(
    parent = parent,
    layoutId = R.layout.food_menu_promoting_product_item,
    viewHolderClass = FoodMenuPromotingProductViewHolder::class.java
) {

  fun clickOnSelf() = step(description = "Выбрать предлагаемый продукт") { click() }

}
