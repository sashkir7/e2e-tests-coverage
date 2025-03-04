package ru.dodopizza.app.screens.checkout.components

import com.dodopizza.order.feature.checkout.paymentwaylist.presentation.PaymentMethodListFragment
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import ru.dodopizza.app.R
import ru.dodopizza.app.kaspresso.actions.childByText
import ru.dodopizza.app.models.enums.PaymentMethod
import ru.dodopizza.app.screens.AbstractComponent

object PaymentMethodComponent : AbstractComponent<PaymentMethodComponent>(
    layoutId = R.layout.fragment_payment_method_list,
    viewClass = PaymentMethodListFragment::class.java,
) {

  private val paymentMethods: KRecyclerView
    get() = bind(R.id.payment_method_recycler_view) { itemType { KRecyclerItem<Any>(it) } }

  fun selectPaymentMethod(payment: PaymentMethod) = flakyStep(
      description = "Выбрать оплату '$payment'",
      expected = { paymentMethods { doesNotExist() } }
  ) { paymentMethod(payment) { click() } }

  fun selectEmailForReceipts() = step(
      description = "Выбрать поле 'Почта для чеков'"
  ) { paymentMethod("Почта для чеков") { click() } }

  private fun paymentMethod(
    text: Any,
    actions: KRecyclerItem<Any>.() -> Unit,
  ) = paymentMethods { childByText<KRecyclerItem<Any>>(text, actions) }
}
