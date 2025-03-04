package ru.dodopizza.app.screens.product.card.regular.components

import com.dodopizza.order.feature.product.card.presentation.product.compose.components.DoughSelectorTags
import io.github.kakaocup.compose.node.element.KNode
import ru.dodopizza.app.models.enums.DoughType
import ru.dodopizza.app.screens.BaseComposeScreen

/**
 * Компонент для выбора размера (редизайн карточки продукта)
 */
object DoughControlComponent : BaseComposeScreen<DoughControlComponent>(
    viewBuilderAction = { hasTestTag(DoughSelectorTags.SELF) }
) {
  fun selectDoughType(dough: DoughType) = step(
      description = "Выбрать тип теста '$dough'"
  ) { tab(dough).performClick() }

  private fun tab(dough: DoughType): KNode = child {
    useUnmergedTree = true
    hasText(dough.description)
  }
}
