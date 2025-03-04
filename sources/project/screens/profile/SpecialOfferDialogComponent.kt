package ru.dodopizza.app.screens.profile

import com.dodopizza.order.feature.promoaction.presentation.SpecialOfferDialogFragment
import io.github.kakaocup.kakao.text.KTextView
import ru.dodopizza.app.R
import ru.dodopizza.app.screens.AbstractComponent

object SpecialOfferDialogComponent : AbstractComponent<SpecialOfferDialogComponent>(
    layoutId = R.layout.dialog_special_offer,
    viewClass = SpecialOfferDialogFragment::class.java,
) {

  private val applyButton: KTextView
    get() = bind(R.id.special_offer_fragment_apply_button)

  fun clickOnApplyButton() = step(
      description = "Нажать на кнопку 'Применить промоакцию'"
  ) { applyButton { click() } }

  fun verifyActionButtonText(expected: String) = step(
      description = "Проверить, что текст кнопки действия '$expected'"
  ) { applyButton { hasText(expected) } }
}
