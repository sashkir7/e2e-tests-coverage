package ru.dodopizza.app.screens.product.card.regular.components

import com.dodopizza.order.feature.product.card.presentation.product.compose.components.ProductFoodValueTags
import io.github.kakaocup.compose.node.element.KNode
import ru.dodopizza.app.screens.BaseComposeScreen

/**
 * Компонент отображения информации о пищевой ценности продукта
 */
object ProductFoodValueComponent : BaseComposeScreen<ProductFoodValueComponent>(
    viewBuilderAction = { hasTestTag(testTag = ProductFoodValueTags.SELF) }
) {

  private val kcal: KNode = child {
    useUnmergedTree = true
    hasTestTag(ProductFoodValueTags.foodValueText(title = "ккал"))
  }

  private val proteins: KNode = child {
    useUnmergedTree = true
    hasTestTag(ProductFoodValueTags.foodValueText(title = "белки"))
  }

  private val fats: KNode = child {
    useUnmergedTree = true
    hasTestTag(ProductFoodValueTags.foodValueText(title = "жиры"))
  }

  private val carbohydrates: KNode = child {
    useUnmergedTree = true
    hasTestTag(ProductFoodValueTags.foodValueText(title = "углеводы"))
  }

  fun verifyKcal(expected: String) = step(
      description = "Проверить, что калорийность '$expected'"
  ) { kcal { assertTextEquals(expected) } }

  fun verifyProteins(expected: String) = step(
      description = "Проверить, что кол-во белков '$expected'"
  ) { proteins { assertTextEquals(expected) } }

  fun verifyFats(expected: String) = step(
      description = "Проверить, что кол-во жиров '$expected'"
  ) { fats { assertTextEquals(expected) } }

  fun verifyCarbohydrates(expected: String) = step(
      description = "Проверить, что кол-во углеводов '$expected'"
  ) { carbohydrates { assertTextEquals(expected) } }
}
