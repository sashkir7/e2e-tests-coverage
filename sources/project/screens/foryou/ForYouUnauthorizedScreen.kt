package ru.dodopizza.app.screens.foryou

import com.dodopizza.order.feature.foryou.presentation.compose.dodocoins.ForYouUnauthorizedMenuItemTags
import io.github.kakaocup.compose.node.element.KNode
import ru.dodopizza.app.screens.BaseComposeScreen

object ForYouUnauthorizedScreen : BaseComposeScreen<ForYouUnauthorizedScreen>(
    viewBuilderAction = { hasTestTag(ForYouUnauthorizedMenuItemTags.SELF) }
) {

  private val title: KNode = child { hasTestTag(ForYouUnauthorizedMenuItemTags.TITLE) }
  private val subtitle: KNode = child { hasTestTag(ForYouUnauthorizedMenuItemTags.SUBTITLE) }
  private val button: KNode = child { hasTestTag(ForYouUnauthorizedMenuItemTags.BUTTON) }

  fun verifyTitle(expected: String) = step(
      description = "Проверить, что заголовок '$expected'"
  ) { title { assertTextEquals(expected) } }

  fun verifySubtitle(expected: String) = step(
      description = "Проверить, что подзаголовок '$expected'"
  ) { subtitle { assertTextEquals(expected) } }

  fun verifyButtonTitle(expected: String) = step(
      description = "Проверить, что заголовок кнопки '$expected'"
  ) { button { assertTextEquals(expected) } }
}
