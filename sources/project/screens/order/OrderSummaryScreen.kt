package ru.dodopizza.app.screens.order

import com.dodopizza.activeorder.R
import com.dodopizza.activeorder.feature.ordersummary.presentation.OrderSummaryFragment
import io.github.kakaocup.kakao.common.views.KView
import ru.dodopizza.app.screens.AbstractScreen

object OrderSummaryScreen : AbstractScreen<OrderSummaryScreen>(
    layoutId = R.layout.fragment_order,
    viewClass = OrderSummaryFragment::class.java,
) {

  private val self: KView
    get() = bind(R.id.order_summary_toolbar_container)

  fun verifyIsLoaded() = step(
      description = "Проверить, что экран заказа успешно загружен"
  ) { self { isDisplayed() } }

}
