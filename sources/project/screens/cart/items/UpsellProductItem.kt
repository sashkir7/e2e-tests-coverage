package ru.dodopizza.app.screens.cart.items

import android.view.View
import com.dodopizza.order.R
import com.dodopizza.order.feature.menu.adapters.promotion.VerticalPromotionProductViewHolder
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import ru.dodopizza.app.models.enums.MetaProduct
import ru.dodopizza.app.screens.AbstractRecyclerItem

/**
 * Карточка продукта апсейла
 * Располагается на экране корзины
 */
class UpsellProductItem(
  parent: Matcher<View>,
) : AbstractRecyclerItem<UpsellProductItem>(
    parent = parent,
    layoutId = R.layout.promouting_product_item_redesign,
    viewHolderClass = VerticalPromotionProductViewHolder::class.java
) {

  private val title: KTextView
    get() = bind(parent) { withId(R.id.promoting_product_title) }
  private val timeRemain: KTextView
    get() = bind(parent) { withId(R.id.personal_price_time_remain) }
  private val finalPriceButton: KButton
    get() = bind(parent) { withId(R.id.promoting_product_button) }
  private val rawPrice: KTextView
    get() = bind(parent) { withId(R.id.promoting_product_raw_price) }

  fun clickOnSelf() = step(description = "Выбрать upsell-продукт") { click() }

  fun verifyTitle(expected: String) = step(
      description = "Проверить, что заголовок '$expected'"
  ) { title { hasText(expected) } }

  fun verifyTitle(expected: MetaProduct) = verifyTitle(expected.toString())

  fun verifyTimeRemain(expected: String) = step(
      description = "Проверить, что до завершения скидки осталось '$expected'"
  ) { timeRemain { hasText(expected) } }

  fun verifyFinalPriceButtonText(expected: String) = step(
      description = "Проверить, что персональная цена на кнопке '$expected'"
  ) { finalPriceButton { hasText(expected) } }

  fun verifyRawPrice(expected: String) = step(
      description = "Проверить, что изначальная цена '$expected'"
  ) { rawPrice { hasText(expected) } }
}
