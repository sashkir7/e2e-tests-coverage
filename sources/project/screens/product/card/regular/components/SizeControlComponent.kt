package ru.dodopizza.app.screens.product.card.regular.components

import com.dodopizza.order.feature.product.card.presentation.product.compose.components.SizeControlTags
import io.github.kakaocup.compose.node.element.KNode
import ru.dodopizza.app.models.enums.PizzaSize
import ru.dodopizza.app.screens.BaseComposeScreen

/**
 * Компонент для выбора размера (редизайн карточки продукта)
 */
object SizeControlComponent : BaseComposeScreen<SizeControlComponent>(
    viewBuilderAction = { hasTestTag(SizeControlTags.SELF) }
) {

  fun verifyIsLoaded() = step(
      description = "Проверить, что компонент выбора размера пиццы загружен"
  ) { assertIsDisplayed() }

  fun selectSize(size: PizzaSize) = step(
      description = "Выбрать размер пиццы '$size'"
  ) { tab(size).performClick() }

  fun verifyDiscountBadgeIsDisplayed(size: PizzaSize) = step(
      description = "Проверить, что у размера '$size' отображается значок скидки"
  ) { discountImage(size).assertIsDisplayed() }

  private fun tab(size: PizzaSize): KNode = child {
    useUnmergedTree = true
    hasTestTag(SizeControlTags.tab(size.toString()))
  }

  private fun discountImage(size: PizzaSize): KNode =
    tab(size).child {
      useUnmergedTree = true
      hasTestTag(SizeControlTags.DISCOUNT_IMAGE)
    }
}
