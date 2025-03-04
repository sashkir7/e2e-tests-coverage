package ru.dodopizza.app.screens.product.card.regular.components

import com.dodopizza.order.feature.product.card.presentation.product.compose.contents.ToppingsTags
import io.github.kakaocup.compose.node.element.KNode
import ru.dodopizza.app.models.enums.Topping
import ru.dodopizza.app.screens.BaseComposeScreen

/**
 * Компонент для отображения дополнительных топпингов
 */
object ProductToppingsComponent : BaseComposeScreen<ProductToppingsComponent>(
    viewBuilderAction = { hasTestTag(ToppingsTags.SELF) }
) {

  fun verifyDoesNotExist(expected: Topping) = step(
      description = "Проверить, что топпинг '$expected' не отображается"
  ) { topping(expected) { assertDoesNotExist() } }

  fun verifyToppingIsAdded(topping: Topping) = step(
      description = "Проверить, что топпинг '$topping' добавлен",
  ) {
    topping(topping) {
      child<KNode> {
        useUnmergedTree = true
        hasTestTag(ToppingsTags.IS_ADDED)
      }.assertIsDisplayed()
    }
  }

  fun verifyToppingIsNotAdded(topping: Topping) = step(
      description = "Проверить, что топпинг '$topping' не добавлен",
  ) {
    topping(topping) {
      child<KNode> {
        useUnmergedTree = true
        hasTestTag(ToppingsTags.IS_NOT_ADDED)
      }.assertDoesNotExist()
    }
  }

  fun clickOnTopping(target: Topping) = step(
      description = "Нажать на топпинг '$target'"
  ) { topping(target) { performClick() } }

  private fun topping(
    target: Topping,
    action: KNode.() -> Unit,
  ) = child<KNode> {
    useUnmergedTree = true
    hasTestTag(ToppingsTags.getToppingTag(target.title))
  }.action()
}
