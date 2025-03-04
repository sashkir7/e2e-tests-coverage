package ru.dodopizza.app.screens.cart.components

import com.dodopizza.activeorder.R
import com.dodopizza.order.feature.shoppingcart.presentation.ShoppingCartFragmentWithInfiniteUpsell
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.text.KTextView
import ru.dodopizza.app.screens.AbstractComponent

object FeeDeliveryInfoComponent : AbstractComponent<FeeDeliveryInfoComponent>(
    layoutId = R.layout.fragment_shopping_cart_with_infinite_upsell,
    viewClass = ShoppingCartFragmentWithInfiniteUpsell::class.java,
) {

  private val text: KTextView
    get() = bind(R.id.fee_delivery_text)
  private val self: KView
    get() = bind(R.id.shopping_cart_fee_delivery)

  fun verifyIsLoaded() = step(
      description = "Проверить, что компонент динамической доставки успешно загружен"
  ) { self { isDisplayed() } }

  fun clickOnSelf() = step(
      description = "Нажать на компонент динамической доставки"
  ) { self { click() } }

  fun verifyInfo(expected: String) = step(
      description = "Проверить, что информация о доставке '$expected'"
  ) { text { containsText(expected) } }
}
