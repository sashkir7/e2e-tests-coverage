package ru.dodopizza.app.screens.loyalty.products

import android.view.View
import com.dodopizza.loyalty.R
import com.dodopizza.loyalty.products.presentation.adapter.LoyaltyProductViewHolder
import io.github.kakaocup.kakao.text.KButton
import org.hamcrest.Matcher
import ru.dodopizza.app.screens.AbstractRecyclerItem

/**
 * Позиция продукта за додокоины (RecyclerViewItem)
 * Например, 'Додстер за 220 баллов'
 */
class LoyaltyProductItem(
  parent: Matcher<View>,
) : AbstractRecyclerItem<LoyaltyProductItem>(
    parent = parent,
    layoutId = R.layout.item_loyalty_product_card,
    viewHolderClass = LoyaltyProductViewHolder::class.java
) {

  private val addToCartButton: KButton
    get() = bind(parent) { withId(R.id.loyalty_product_get_button) }

  fun verifyIsLoaded() = step(
      description = "Проверить, что карточка продукта загружена"
  ) { isDisplayed() }

  fun addToCart() = step(
      description = "Добавить в корзину"
  ) { addToCartButton { click() } }
}
