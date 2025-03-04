package ru.dodopizza.app.screens.order

import com.dodopizza.activeorder.R
import com.dodopizza.activeorder.feature.orderdetails.presentation.OrderDetailsFragment
import io.github.kakaocup.kakao.text.KTextView
import ru.dodopizza.app.screens.AbstractScreen

/**
 * Экран детализации заказа
 */
object OrderDetailsScreen : AbstractScreen<OrderDetailsScreen>(
    layoutId = R.layout.fragment_order_card,
    viewClass = OrderDetailsFragment::class.java
) {

  private val deliveryFeeAmount: KTextView
    get() = bind(R.id.delivery_fee_amount)

  fun verifyDeliveryFeeAmount(expected: String) = step(
      description = "Проверить, что стоимость доставки '$expected'"
  ) { deliveryFeeAmount { hasText(expected) } }
}
