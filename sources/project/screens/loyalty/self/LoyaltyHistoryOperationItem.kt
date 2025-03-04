package ru.dodopizza.app.screens.loyalty.self

import android.view.View
import com.dodopizza.loyalty.R
import com.dodopizza.loyalty.history.adapter.LoyaltyHistoryOperationViewHolder
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import ru.dodopizza.app.screens.AbstractRecyclerItem

/**
 * Позиция операции в истории программы лояльности (RecyclerViewItem)
 * Например, 'Начислили за заказ +42D'
 */
class LoyaltyHistoryOperationItem(
  parent: Matcher<View>,
) : AbstractRecyclerItem<LoyaltyHistoryOperationItem>(
    parent = parent,
    layoutId = R.layout.item_loyalty_history,
    viewHolderClass = LoyaltyHistoryOperationViewHolder::class.java
) {

  private val title: KTextView
    get() = bind(parent) { withId(R.id.loyalty_history_item_title) }
  private val amount: KTextView
    get() = bind(parent) { withId(R.id.loyalty_history_item_amount) }

  fun verifyTitle(expected: String) = step(
      description = "Проверить, что описание операции '$expected'"
  ) { title { hasText(expected) } }

  fun verifyAmount(expected: String) = step(
      description = "Проверить, что сумма операции '$expected'"
  ) { amount { hasText(expected) } }
}
