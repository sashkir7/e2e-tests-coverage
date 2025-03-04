package ru.dodopizza.app.screens.checkout.defferedTime

import com.dodopizza.order.feature.checkout.deferredtime.presentation.DeferredTimeFragment
import io.github.kakaocup.kakao.recycler.KRecyclerView
import ru.dodopizza.app.R
import ru.dodopizza.app.screens.AbstractScreen

/**
 * Экран выбора времени доставки/самовывоза/получения заказа
 */
object DeferredTimeScreen : AbstractScreen<DeferredTimeScreen>(
    layoutId = R.layout.fragment_deferred_time,
    viewClass = DeferredTimeFragment::class.java
) {

  private val times: KRecyclerView
    get() = bind(R.id.deferred_time_list) { itemType { DeferredTimeIntervalItem(it) } }

  fun verifyIsLoaded() = step(
      description = "Проверить, что экран выбора времени доставки успешно загружен"
  ) { times { isDisplayed() } }

  fun selectTime(position: Int) {
    timeSlot(position) { clickOnSelf() }
  }

  fun timeSlot(
    position: Int,
    actions: DeferredTimeIntervalItem.() -> Unit,
  ) = step("Получить карточку времени на позиции '$position'") {
    times { childAt<DeferredTimeIntervalItem>(position, actions) }
  }
}
