package ru.dodopizza.app.screens.common

import com.dodopizza.components.buttons.DButtonTag
import io.github.kakaocup.compose.node.element.KNode
import ru.dodopizza.app.screens.BaseComposeScreen

/**
 * Компонент кнопки DButton (compose)
 */
class DComposeButton(id: Int) : BaseComposeScreen<DComposeButton>(
    viewBuilderAction = { hasTestTag(DButtonTag.self(id)) }
) {

  private val text: KNode = child {
    useUnmergedTree = true
    hasTestTag(DButtonTag.BUTTON_TEXT)
  }

  fun verifyText(expected: String) {
    text { assertTextEquals(expected) }
  }
}
