package ru.dodopizza.app.screens.profile.self.components

import com.dodopizza.order.feature.collaboration.presentation.CollaborationPromoDialogFragment
import ru.dodopizza.app.R
import ru.dodopizza.app.screens.AbstractComponent
import ru.dodopizza.app.screens.common.DComposeButton

/**
 * Компонент выбора партнерского промокода
 */
object CollaborationPromoDialogComponent : AbstractComponent<CollaborationPromoDialogComponent>(
    layoutId = R.layout.dialog_collaboration_promo,
    viewClass = CollaborationPromoDialogFragment::class.java,
) {

  private val promoLinkButton = DComposeButton(R.id.collaboration_offer_fragment_main_button)
  private val promoCopyButton = DComposeButton(R.id.collaboration_offer_fragment_secondary_button)

  fun clickOnCopyButton() = step(
      description = "Нажать на кнопку 'Копировать промоакцию'"
  ) { promoCopyButton { performClick() } }

  fun clickOnPromoLinkButton() = step(
      description = "Нажать на кнопку 'Перейти на сайт партнера'"
  ) { promoLinkButton { performClick() } }

  fun verifyActionButtonHasCopyOfferText() = verifyActionButtonText(expected = "Скопировали!")

  private fun verifyActionButtonText(expected: String) = step(
      description = "Проверить, что текст кнопки действия '$expected'"
  ) { promoCopyButton { verifyText(expected) } }
}
