package ru.dodopizza.app.screens.dynamic

import androidx.compose.ui.test.hasText
import com.dodopizza.order.feature.personaldelivery.compose.DynamicDeliveryTags
import io.github.kakaocup.compose.node.element.KNode
import ru.dodopizza.app.screens.BaseComposeScreen

object DynamicDeliveryScreen : BaseComposeScreen<DynamicDeliveryScreen>(
    viewBuilderAction = { hasTestTag(DynamicDeliveryTags.SELF) }
) {

  private val content: KNode = child { hasTestTag(DynamicDeliveryTags.CONTENT) }
  private val infoButton: KNode = child { hasTestTag(DynamicDeliveryTags.FEE_LEVELS_INFO_BUTTON) }

  fun verifyIsLoaded() = step(
      description = "Проверить, что экран динамической доставки успешно загружен"
  ) { content { assertIsDisplayed() } }

  fun verifyContentText(expected: String) = step(
      description = "Проверить, что текст содержимого '$expected'"
  ) { content { hasText(expected) } }

  fun clickOnInfoButton() = step(
      description = "Нажать на кнопку 'Понятно'"
  ) { infoButton { performClick() } }

  fun verifyIsNotLoaded() = step(
      description = "Проверить, что экран динамической доставки не был загружен"
  ) { continuously(timeoutMs = 1_500) { content { assertDoesNotExist() } } }
}
