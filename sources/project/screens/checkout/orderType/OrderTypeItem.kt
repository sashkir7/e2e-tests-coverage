package ru.dodopizza.app.screens.checkout.orderType

import android.view.View
import com.dodopizza.order.R
import com.dodopizza.order.feature.checkout.presentation.OrderReceivingTypeHolder
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import ru.dodopizza.app.screens.AbstractRecyclerItem
import ru.dodopizza.app.screens.checkout.orderType.OrderTypeItem.ExpectedAction.ORDER_TYPE_IS_SELECTED

/**
 * Карточка способа получения заказа
 */
class OrderTypeItem(
  parent: Matcher<View>,
) : AbstractRecyclerItem<OrderTypeItem>(
    parent = parent,
    layoutId = R.layout.item_order_receiving_type,
    viewHolderClass = OrderReceivingTypeHolder::class.java
) {

  private val title: KTextView
    get() = bind(parent) { withId(R.id.text) }

  fun clickOnSelf(
    expected: ExpectedAction = ORDER_TYPE_IS_SELECTED,
  ) = flakyStep(
      description = "Выбрать способ получения заказа",
      expected = expected.action
  ) { click() }

  fun verifyIsSelected() = step(
      description = "Проверить, что выбрана карточка"
  ) { isSelected() }

  fun verifyTitle(expected: String) = step(
      description = "Проверить, что заголовок '$expected'"
  ) { title { hasText(expected) } }

  fun verifyTitle(expected: OrderType) = verifyTitle(expected.toString())

  enum class ExpectedAction(
    val action: OrderTypeItem.() -> Unit,
  ) {
    ORDER_TYPE_IS_SELECTED(action = { isSelected() }),
    TABLE_SCREEN_IS_LOADED(action = { doesNotExist() }),
  }
}
