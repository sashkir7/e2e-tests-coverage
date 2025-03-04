package ru.dodopizza.app.screens.foryou.component

import com.dodopizza.order.feature.foryou.presentation.compose.dodocoins.ForYouDodoContentTags
import io.github.kakaocup.compose.node.element.KNode
import ru.dodopizza.app.screens.BaseComposeScreen

object LoyaltyMenuComponent : BaseComposeScreen<LoyaltyMenuComponent>(
    viewBuilderAction = { hasTestTag(ForYouDodoContentTags.SELF) }
) {

  private val title: KNode = child { hasTestTag(ForYouDodoContentTags.TITLE) }
  private val amount: KNode = child { hasTestTag(ForYouDodoContentTags.AMOUNT) }
  private val button: KNode = child { hasTestTag(ForYouDodoContentTags.BUTTON) }

  fun verifyTitle(expected: String) = step(
      description = "Проверить, что заголовок '$expected'"
  ) { title { assertTextEquals(expected) } }

  fun verifyAmount(expected: String) = step(
      description = "Проверить, что количество коинов '$expected'"
  ) { amount { assertTextEquals(expected) } }

  fun verifyButtonTitle(expected: String) = step(
      description = "Проверить, что заголовок кнопки '$expected'"
  ) { button { assertTextEquals(expected) } }

  fun clickOnCoinsStoreButton() = step(
      description = "Нажать на кнопку 'В коинстор'"
  ) { button { performClick() } }
}
