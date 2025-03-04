package ru.dodopizza.app.screens.history.details.items

import android.view.View
import com.dodopizza.orderhistory.R
import com.dodopizza.orderhistory.feature.orderhistory.presentation.orderhistorydetails.adapter.SimpleOrderItemVH
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import ru.dodopizza.app.models.enums.MetaProduct
import ru.dodopizza.app.screens.AbstractRecyclerItem

/**к
 * Карточа обычного товара (не комбо) в истории
 */
class SimpleOrderHistoryItem(
  parent: Matcher<View>,
) : AbstractRecyclerItem<SimpleOrderHistoryItem>(
    parent = parent,
    layoutId = R.layout.product_order_card_item2,
    viewHolderClass = SimpleOrderItemVH::class.java
) {

  private val title: KTextView
    get() = bind(parent) { withId(R.id.label_title) }

  fun verifyTitle(expected: MetaProduct) = step(
      description = "Проверить, что заголовок '$expected'"
  ) { title { hasText(expected.toString()) } }
}
