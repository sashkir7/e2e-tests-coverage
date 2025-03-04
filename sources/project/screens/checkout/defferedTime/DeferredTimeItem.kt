package ru.dodopizza.app.screens.checkout.defferedTime

import android.view.View
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import ru.dodopizza.app.R
import com.dodopizza.order.feature.checkout.details.presentation.adapter.DeferredTimeViewHolder
import ru.dodopizza.app.screens.AbstractRecyclerItem
import ru.dodopizza.app.screens.checkout.defferedTime.DeferredTimeItem.ExpectedAction.DEFERRED_TIME_ITEM_IS_SELECTED

/**
 * Карточка времени доставки из ресторана (RecyclerViewItem)
 * Располагается на checkout-экране
 */
class DeferredTimeItem(
  parent: Matcher<View>,
) : AbstractRecyclerItem<DeferredTimeItem>(
    parent = parent,
    layoutId = R.layout.checkout_deferred_time_item,
    viewHolderClass = DeferredTimeViewHolder::class.java
) {

  private val progress: KView
    get() = bind(parent) { withId(R.id.progress) }
  private val title: KTextView
    get() = bind(parent) { withId(R.id.deferred_time_value) }

  fun clickOnSelf(
    expected: ExpectedAction = DEFERRED_TIME_ITEM_IS_SELECTED,
  ) = flakyStep(
      description = "Нажать на карточку отложенного времени",
      expected = expected.action
  ) { click() }

  fun verifyTitle(expected: String) = step(
      description = "Проверить, что значение '$expected'"
  ) { title { hasText(expected) } }

  enum class ExpectedAction(
    val action: DeferredTimeItem.() -> Unit,
  ) {
    DEFERRED_TIME_ITEM_IS_SELECTED(
        action = {
          isChecked()
          progress { isGone() }
        }
    ),
    DEFERRED_TIME_SCREEN_IS_LOADED(
        action = { KView { withId(R.id.deferred_time_view_switcher) }.isDisplayed() }
    ),
  }
}
