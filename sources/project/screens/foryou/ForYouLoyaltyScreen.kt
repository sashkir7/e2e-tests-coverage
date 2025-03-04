package ru.dodopizza.app.screens.foryou

import com.dodopizza.order.feature.foryou.presentation.compose.dodocoins.ForYouDodoCoinsMenuTags
import ru.dodopizza.app.screens.BaseComposeScreen
import ru.dodopizza.app.screens.foryou.component.LoyaltyMenuComponent

object ForYouLoyaltyScreen : BaseComposeScreen<ForYouLoyaltyScreen>(
    viewBuilderAction = { hasTestTag(ForYouDodoCoinsMenuTags.SELF) }
) {

  val loyaltyInfo = LoyaltyMenuComponent

  fun verifyIsLoaded() = step(
      description = "Проверить, что экран лояльности успешно загружен"
  ) { assertIsDisplayed() }

  fun scrollToTopPicksSection() = step(
      description = "Пролистать до раздела 'Вам понравится'"
  ) { longSwipeUp() }
}
