package ru.dodopizza.app.screens.product.card.regular.components

import com.dodopizza.order.feature.product.card.presentation.product.compose.components.CloseButtonTags
import ru.dodopizza.app.screens.BaseComposeScreen

/**
 * Компонент кнопки закрытия
 */
object CloseButtonComponent : BaseComposeScreen<CloseButtonComponent>(
    viewBuilderAction = { hasTestTag(CloseButtonTags.SELF) }
) {

  fun clickOnSelf() = step(
      description = "Нажать на кнопку закрытия"
  ) { performClick() }

}
