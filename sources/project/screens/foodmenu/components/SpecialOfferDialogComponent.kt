package ru.dodopizza.app.screens.foodmenu.components

import com.dodopizza.order.R
import com.dodopizza.order.feature.promoaction.presentation.SpecialOfferDialogFragment
import io.github.kakaocup.kakao.text.KTextView
import ru.dodopizza.app.screens.AbstractComponent

/**
 * Компонент с деталями по специальному предложению
 */
object SpecialOfferDialogComponent : AbstractComponent<SpecialOfferDialogComponent>(
    layoutId = R.layout.dialog_special_offer,
    viewClass = SpecialOfferDialogFragment::class.java
) {

  private val title: KTextView
    get() = bind(R.id.special_offer_fragment_title)

  fun verifyTitle(expected: String) = step(
      description = "Проверить, что заголовок специального предложения '$expected'"
  ) { title { hasText(expected) } }
}
