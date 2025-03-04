package ru.dodopizza.app.screens.product.card.regular.components

import com.dodopizza.order.feature.product.card.presentation.product.compose.contents.ProductCompositionTags
import io.github.kakaocup.compose.node.element.KNode
import ru.dodopizza.app.screens.BaseComposeScreen

/**
 * Компонент для отображения веса и кастомизации
 */
object ProductCompositionComponent : BaseComposeScreen<ProductCompositionComponent>(
    viewBuilderAction = { hasTestTag(ProductCompositionTags.SELF) }
) {

  private val weight: KNode = child {
    useUnmergedTree = true
    hasTestTag(ProductCompositionTags.WEIGHT)
  }

  private val ingredients: KNode = child {
    useUnmergedTree = true
    hasTestTag(ProductCompositionTags.INGREDIENTS_TITLE)
  }

  fun verifyWeight(expected: String) = step(
      description = "Проверить, что вес '$expected'"
  ) { weight { assertTextEquals(expected) } }

  fun verifyIngredients(expected: String) = step(
      description = "Проверить, что заголовок ингредиентов '$expected'"
  ) { ingredients { assertTextEquals(expected) } }
}
