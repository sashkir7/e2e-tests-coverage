package ru.dodopizza.app.screens.loyalty.components

import androidx.compose.ui.test.hasContentDescription
import com.dodopizza.order.feature.product.card.presentation.view.LoyaltySwitchTags
import ru.dodopizza.app.screens.BaseComposeScreen

/**
 * Компонент выбора режима лояльности (за рубли либо додокоины)
 */
object LoyaltySwitchComponent : BaseComposeScreen<LoyaltySwitchComponent>(
    viewBuilderAction = { hasTestTag(LoyaltySwitchTags.SELF) }
) {

  fun verifyIsLoaded() = step(
      description = "Проверить, что свитчер лояльности загружен"
  ) { assertIsDisplayed() }

  fun verifyDoesNotExist() = step(
      description = "Проверить, что свитчер лояльности не отображается"
  ) { assertDoesNotExist() }

  fun verifyIsRubState() = step(
      description = "Проверить, что в свитчере лояльности отключена оплата за додокоины"
  ) { hasContentDescription(LoyaltySwitchTags.RUB_STATE_DESCRIPTION) }

  fun verifyIsDodocoinsState() = step(
      description = "Проверить, что в свитчере лояльности включена оплата за додокоины"
  ) { hasContentDescription(LoyaltySwitchTags.DODOCOINS_STATE_DESCRIPTION) }

  fun clickOnSelf() = step("Нажать на свитчер лояльности") { performClick() }
}
