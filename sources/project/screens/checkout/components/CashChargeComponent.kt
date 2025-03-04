package ru.dodopizza.app.screens.checkout.components

import com.dodopizza.order.feature.checkout.cashcharge.presentation.CashChargeFragment
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import ru.dodopizza.app.R
import ru.dodopizza.app.screens.AbstractComponent

object CashChargeComponent : AbstractComponent<CashChargeComponent>(
    layoutId = R.layout.fragment_cash_charge,
    viewClass = CashChargeFragment::class.java,
) {

  private val cashCharge: KTextView
    get() = bind(R.id.cash_charge_title)
  private val haveExactMoneyButton: KButton
    get() = bind(R.id.have_exact_money_button)

  fun verifyIsLoaded() = step(
      description = "Проверить, что компонент 'С какой суммы подготовить сдачу?' успешно загружен"
  ) { cashCharge { containsText("С какой суммы подготовить сдачу?") } }

  fun clickOnHaveExactMoneyButton() = flakyStep(
      description = "Нажать на кнопку 'Без сдачи'",
      expected = { haveExactMoneyButton { doesNotExist() } }
  ) { haveExactMoneyButton { click() } }
}
