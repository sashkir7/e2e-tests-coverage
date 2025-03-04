package ru.dodopizza.app.screens.foodmenu.components

import com.dodopizza.activeorder.feature.compose.components.ActiveOrderWidgetTag
import io.github.kakaocup.compose.node.element.KNode
import ru.dodopizza.app.models.enums.OrderStatus
import ru.dodopizza.app.screens.BaseComposeScreen

/**
 * Компонент активного заказа (виджет статуса)
 */
object ActiveOrderWidgetComponent : BaseComposeScreen<ActiveOrderWidgetComponent>(
    viewBuilderAction = { hasTestTag(ActiveOrderWidgetTag.SELF) }
) {

  private val status: KNode = child {
    useUnmergedTree = true
    hasTestTag(ActiveOrderWidgetTag.STATUS)
  }

  fun verifyIsNotDisplayed() = step(
      description = "Проверить, что компонент активного заказа скрыт"
  ) { assertDoesNotExist() }

  fun verifyStatus(expected: OrderStatus) = step(
      description = "Проверить, что статус заказа '$expected'"
  ) { status { assertTextContains(expected.title, substring = true) } }

  fun clickOnSelf() = step(
      description = "Нажать на виджет заказа"
  ) { performClick() }
}
