package ru.dodopizza.app.screens.payment

import com.dodopizza.order.feature.paymenterrors.presentation.compose.PaymentErrorTag
import io.github.kakaocup.compose.node.element.KNode
import ru.dodopizza.app.screens.BaseComposeScreen

object PaymentErrorsComponent : BaseComposeScreen<PaymentErrorsComponent>(
    viewBuilderAction = { hasTestTag(PaymentErrorTag.SELF) }
) {

  private val title: KNode = child { hasTestTag(PaymentErrorTag.TITLE) }
  private val subtitle: KNode = child { hasTestTag(PaymentErrorTag.SUBTITLE) }

  fun verifyTitle(expected: String) = step(
      description = "Проверить, что заголовок '$expected'"
  ) { title { assertTextEquals(expected) } }

  fun verifySubtitle(expected: String) = step(
      description = "Проверить, что подзаголовок '$expected'"
  ) { subtitle { assertTextEquals(expected) } }
}
