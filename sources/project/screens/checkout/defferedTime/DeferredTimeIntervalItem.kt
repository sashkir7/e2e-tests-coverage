package ru.dodopizza.app.screens.checkout.defferedTime

import android.view.View
import com.dodopizza.order.feature.checkout.deferredtime.presentation.adapter.DeferredTimeIntervalViewHolder
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import ru.dodopizza.app.R
import ru.dodopizza.app.screens.AbstractRecyclerItem

/**
 * Карточка выбора времени доставки (при открытии всех доступных слотов)
 * Располагается на экране выбора времени
 */
class DeferredTimeIntervalItem(
  parent: Matcher<View>,
) : AbstractRecyclerItem<DeferredTimeIntervalItem>(
    parent = parent,
    layoutId = R.layout.deferred_time_interval_item,
    viewHolderClass = DeferredTimeIntervalViewHolder::class.java
) {

  private val comment: KTextView
    get() = bind(parent) { withId(R.id.deferred_time_comment_text) }

  fun clickOnSelf() = step(description = "Нажать на карточку времени") { click() }

  fun verifyComment(expected: String) = step(
      description = "Проверить, что комментарий '$expected'"
  ) { comment { hasText(expected) } }
}
