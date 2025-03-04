package ru.dodopizza.app.screens.payment

import com.dodopizza.order.R
import com.dodopizza.order.feature.payment.card.presentation.CardPaymentFragment
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.text.KButton
import ru.dodopizza.app.models.enums.BankCard
import ru.dodopizza.app.screens.AbstractScreen
import ru.dodopizza.app.screens.external.TransactionComponent

/**
 * Экран для заполенния данных кредитной карты
 */
object CardPaymentScreen : AbstractScreen<CardPaymentScreen>(
    layoutId = R.layout.fragment_card,
    viewClass = CardPaymentFragment::class.java
) {

  val transaction = TransactionComponent
  val paymentErrors = PaymentErrorsComponent

  private val payButton: KButton
    get() = bind(R.id.card_number)
  private val cardNumber: KEditText
    get() = bind(R.id.card_number)
  private val cardExpiry: KEditText
    get() = bind(R.id.card_expiry)
  private val cardCvc: KEditText
    get() = bind(R.id.card_CVC)

  fun typeCardInfo(card: BankCard) =
    step(description = "Указать данные карты") {
      typeNumber(card.number)
      typeExpiry(card.expiry)
      typeCvc(card.cvc)
    }

  fun typeNumber(number: String) = step(
      description = "Указать карту '$number'"
  ) { cardNumber { typeText(number) } }

  fun typeExpiry(expiry: String) = step(
      description = "Указать срок действия карты '$expiry'"
  ) {
    cardExpiry { typeText(expiry) }
  }

  fun typeCvc(cvc: String) = step(
      description = "Указать cvc код '$cvc'"
  ) { cardCvc { typeText(cvc) } }

  fun clickOnPayButton() = flakyStep(
      description = "Нажать на кнопку 'Оплатить'",
      timeoutMs = 7_500,
      expected = { payButton { doesNotExist() } }
  ) { payButton { click() } }
}
