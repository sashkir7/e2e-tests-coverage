package ru.dodopizza.app.screens.history.details.items

import android.view.View
import com.dodopizza.orderhistory.R
import com.dodopizza.orderhistory.feature.orderhistory.presentation.orderhistorydetails.adapter.ComboProductViewHolder
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import ru.dodopizza.app.models.enums.MetaProduct
import ru.dodopizza.app.screens.AbstractRecyclerItem

/**к
 * Карточка конкретного товара в комбо (в истории)
 */
class ComboProductItem(
  parent: Matcher<View>,
) : AbstractRecyclerItem<ComboProductItem>(
    parent = parent,
    layoutId = R.layout.product_order_card_item2,
    viewHolderClass = ComboProductViewHolder::class.java
) {

  private val title: KTextView
    get() = bind(parent) { withId(R.id.label_title) }

  fun verifyTitle(expected: MetaProduct) = step(
      description = "Проверить, что заголовок '$expected'"
  ) { title { hasText(expected.toString()) } }
}
