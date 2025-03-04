package ru.dodopizza.app.screens.foodmenu.components

import com.dodopizza.activeorder.R
import com.dodopizza.order.feature.feedelivery.FeeDeliveryInfoView
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.text.KTextView
import ru.dodopizza.app.screens.AbstractComponent

object FeeDeliveryInfoComponent : AbstractComponent<FeeDeliveryInfoComponent>(
    layoutId = R.layout.view_fee_delivery_info,
    viewClass = FeeDeliveryInfoView::class.java,
) {

  private val text: KTextView
    get() = bind(R.id.fee_delivery_text)
  private val self: KView
    get() = bind(R.id.menu_min_delivery_price_container)

  fun verifyIsLoaded() = step(
      description = "Проверить, что компонент динамической доставки успешно загружен"
  ) { self { isDisplayed() } }

  fun clickOnSelf() = step(
      description = "Нажать на компонент динамической доставки"
  ) { self { click() } }

  fun verifyInfo(expected: String) = step(
      description = "Проверить, что отображается информация о доставке '$expected'"
  ) { text { containsText(expected) } }
}
