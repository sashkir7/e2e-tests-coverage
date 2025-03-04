package ru.dodopizza.app.screens.external

import com.kaspersky.components.kautomator.component.text.UiButton
import ru.dodopizza.app.screens.BaseUiScreen

/**
 * Системный компонент подтверждения транзакции
 * (во время онлайн-оплаты заказа)
 */
object TransactionComponent : BaseUiScreen<TransactionComponent>() {

  private val successButton = UiButton { withText("Успех") }

  fun clickOnSuccessButton() = step(
      description = "Нажать на кнопку 'Успех' (тестовая транзакция)"
  ) { successButton { click() } }
}
