package ru.dodopizza.app.screens.product.card.regular.components

import com.dodopizza.order.feature.product.card.presentation.product.compose.components.RecipeCustomizationTags

import io.github.kakaocup.compose.node.element.KNode
import ru.dodopizza.app.screens.BaseComposeScreen

/**
 * Компонент отображения кастомизации
 */
object RecipeCustomizationComponent : BaseComposeScreen<RecipeCustomizationComponent>(
    viewBuilderAction = { hasTestTag(RecipeCustomizationTags.SELF) }
) {

  private val title: KNode = child {
    useUnmergedTree = true
    hasTestTag(RecipeCustomizationTags.TITLE)
  }

  fun verifyTitle(expected: String) = step(
      description = "Проверить, что название компонента кастомизации '$expected'"
  ) { title { assertTextEquals(expected) } }

  fun clickOnSelf() = step(
      description = "Нажать на компонент кастомизации"
  ) { performClick() }
}
