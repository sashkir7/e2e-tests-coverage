package ru.dodopizza.app.screens.history.details

import com.dodopizza.orderhistory.R
import com.dodopizza.orderhistory.feature.orderhistory.presentation.orderhistorydetails.OrderHistoryDetailsFragment
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import ru.dodopizza.app.models.enums.MetaProduct
import ru.dodopizza.app.screens.AbstractScreen
import ru.dodopizza.app.screens.history.details.items.ComboOrderHistoryItem
import ru.dodopizza.app.screens.history.details.items.SimpleOrderHistoryItem

object OrderHistoryDetailsScreen : AbstractScreen<OrderHistoryDetailsScreen>(
    layoutId = R.layout.fragment_order_history_details,
    viewClass = OrderHistoryDetailsFragment::class.java,
) {

  private val products: KRecyclerView
    get() = bind(R.id.recycler_view) {
      itemType { SimpleOrderHistoryItem(it) }
      itemType { ComboOrderHistoryItem(it) }
    }

  private val repeatOrderButton: KButton
    get() = bind(R.id.repeat_order_button)

  fun clickOnRepeatOrderButton() = step(
      description = "Нажать на кнопку 'Повторить заказ'"
  ) { repeatOrderButton { click() } }

  fun verifyProducts(vararg expected: MetaProduct) =
    step(description = "Проверить содержимое заказа") {
      verifyProductsSize(expected.size)
      expected.forEachIndexed { position, product ->
        verifyProductByPosition(position, product)
      }
    }

  fun verifyProductsSize(expected: Int) = step(
      description = "Проверить, что заказ содержит '$expected' позиций"
  ) { products { hasSize(expected) } }

  fun comboSlot(actions: ComboOrderHistoryItem.() -> Unit) = step(
      description = "Получить позицию комбо-заказа"
  ) { products { childAt<ComboOrderHistoryItem>(position = 0, actions) } }

  private fun verifyProductByPosition(
    position: Int,
    expected: MetaProduct,
  ) = step(description = "Проверить продукт '$expected' на позиции '$position'") {
    products { childAt<SimpleOrderHistoryItem>(position) { verifyTitle(expected) } }
  }
}
