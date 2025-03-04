package ru.dodopizza.app.screens.checkout

import androidx.test.espresso.matcher.ViewMatchers.withId
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.scroll.KScrollView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import ru.dodopizza.app.R
import com.dodopizza.order.feature.checkout.details.presentation.CheckoutDetailsFragment
import ru.dodopizza.app.screens.AbstractScreen
import ru.dodopizza.app.models.enums.DeferredTime
import ru.dodopizza.app.models.enums.DeferredTime.CUSTOM
import ru.dodopizza.app.models.enums.DeliveryAddress
import ru.dodopizza.app.models.enums.Pizzeria
import ru.dodopizza.app.screens.checkout.components.CashChargeComponent
import ru.dodopizza.app.screens.checkout.components.SelectPackagingComponent
import ru.dodopizza.app.screens.checkout.components.PaymentMethodComponent
import ru.dodopizza.app.models.enums.PaymentMethod
import ru.dodopizza.app.screens.checkout.defferedTime.DeferredTimeItem
import ru.dodopizza.app.screens.checkout.defferedTime.DeferredTimeItem.ExpectedAction.DEFERRED_TIME_SCREEN_IS_LOADED
import ru.dodopizza.app.screens.checkout.orderType.OrderType
import ru.dodopizza.app.screens.checkout.orderType.OrderTypeItem
import ru.dodopizza.app.screens.common.DComposeButton

/**
 * Checkout-экран
 */
object CheckoutDetailsScreen : AbstractScreen<CheckoutDetailsScreen>(
    layoutId = R.layout.fragment_checkout_details,
    viewClass = CheckoutDetailsFragment::class.java,
) {

  val paymentMethod = PaymentMethodComponent
  val cashCharge = CashChargeComponent
  val selectPackaging = SelectPackagingComponent

  private val self: KView
    get() = bind(R.id.checkout_details_container)
  private val paymentMethodView: KView
    get() = bind(R.id.payment_method_view)
  private val paymentMethodName: KTextView
    get() = bind(withId(R.id.payment_method_view)) { withId(R.id.payment_method_name) }

  private val orderTypes: KRecyclerView
    get() = bind(R.id.order_receiving_list) { itemType { OrderTypeItem(it) } }
  private val deferredTimes: KRecyclerView
    get() = bind(R.id.deferred_time_list) { itemType { DeferredTimeItem(it) } }

  private val addDeliveryLocationButton: KButton
    get() = bind(R.id.add_delivery_location_button)
  private val deliveryFeeAmount: KTextView
    get() = bind(R.id.delivery_fee_amount)
  private val deliveryFeeContainer: KView
    get() = bind(R.id.delivery_fee_container)

  private val placeOrderButton = DComposeButton(R.id.place_order_button)

  private val disabledPlaceOrderButton: KButton
    get() = bind(R.id.disabled_place_order_button)
  private val createOrderButton: KButton
    get() = bind(R.id.add_notes_button)

  private val snackBarTextView: KTextView
    get() = bind(R.id.snackbar_text)
  private val deferredTimeError: KTextView
    get() = bind(R.id.deferred_time_error)
  private val deliveryAddressTextView: KTextView
    get() = bind(withId(R.id.address_view)) { withId(R.id.address_label) }
  private val pizzeriaAddressTextView: KTextView
    get() = bind(withId(R.id.pizzeria_view)) { withId(R.id.address_label) }
  private val addressErrorLabel: KTextView
    get() = bind(withId(R.id.pizzeria_view)) { withId(R.id.error_label) }

  private val overloadWarning: KTextView
    get() = bind(R.id.overload_mode_warning)
  private val overloadModeButton: KTextView
    get() = bind(R.id.overload_mode_button)

  private val containerScrollView: KScrollView
    get() = bind(R.id.groupCheckoutSettings)

  fun verifyIsLoaded() = step(
      description = "Проверить, что экран оформления заказа успешно загружен"
  ) { continuously(timeoutMs = 2_500) { self { isDisplayed() } } }

  fun openPaymentsMethods() = flakyStep(
      description = "Открыть окно выбора оплаты",
      expected = { paymentMethodView { doesNotExist() } }
  ) { paymentMethodView { click() } }

  fun selectPaymentMethod(paymentMethod: PaymentMethod) =
    step(description = "Указать способ оплаты '$paymentMethod'") {
      openPaymentsMethods()
      paymentMethod { selectPaymentMethod(paymentMethod) }
    }

  fun verifyPaymentMethodIsSelected(paymentMethod: PaymentMethod) = step(
      description = "Проверить, что выбран способ оплаты '$paymentMethod'"
  ) { paymentMethodName { hasText(paymentMethod.toString()) } }

  fun verifyDeliveryFeeIsDisplayed() = step(
      description = "Проверить, что отображается блок стоимости доставки"
  ) { deliveryFeeContainer { isDisplayed() } }

  fun verifyDeliveryFee(expected: String) = step(
      description = "Проверить, что стоимость доставки '$expected'"
  ) { deliveryFeeAmount { hasText(expected) } }

  fun clickOnAddDeliveryAddressButton() = flakyStep(
      description = "Нажать на кнопку 'Добавить адрес доставки'",
      expected = { addDeliveryLocationButton { doesNotExist() } }
  ) { addDeliveryLocationButton { click() } }

  fun clickOnCreateOrderButton() = flakyStep(
      description = "Нажать на кнопку 'Оформить заказ'",
      expected = { createOrderButton { doesNotExist() } }
  ) { createOrderButton { click() } }

  fun clickOnPlaceOrderButton() = step(
      description = "Нажать на кнопку 'Оформить заказ'"
  ) { placeOrderButton { performClick() } }

  fun clickOnPizzeriaAddress() = flakyStep(
      description = "Нажать на адрес пиццерии",
      expected = { pizzeriaAddressTextView { doesNotExist() } }
  ) { pizzeriaAddressTextView { click() } }

  fun clickOnDeliveryAddress() = flakyStep(
      description = "Нажать на адрес доставки",
      expected = { deliveryAddressTextView { doesNotExist() } }
  ) { deliveryAddressTextView { click() } }

  fun verifyPizzeriaAddress(expected: String) = step(
      description = "Проверить, что адрес пиццерии '$expected'"
  ) { pizzeriaAddressTextView { hasText(expected) } }

  fun verifyPizzeriaAddress(expected: Pizzeria) =
    verifyPizzeriaAddress(expected.shortAddress)

  fun verifyDeliveryAddress(expected: String) = step(
      description = "Проверить, что адрес доставки '$expected'"
  ) { deliveryAddressTextView { hasText(expected) } }

  fun verifyDeliveryAddress(expected: DeliveryAddress) =
    verifyDeliveryAddress(expected.fullAddress)

  fun verifyAddressError(containsExpected: String) = step(
      description = "Проверить, что информация об адресе содержит ошибку '$containsExpected'"
  ) { addressErrorLabel { containsText(containsExpected) } }

  fun hideOrderDetails() = flakyStep(
      description = "Скрыть информацию о заказе",
      expected = { containerScrollView { doesNotExist() } }
  ) { containerScrollView { swipeDown() } }

  fun orderType(
    type: OrderType,
    actions: OrderTypeItem.() -> Unit,
  ) = step(description = "Получить карточку типа заказа '$type'") {
    orderTypes.childAt<OrderTypeItem>(type.positionOnScreen, actions)
  }

  fun verifyAvailableOrderTypes(expected: List<OrderType>) =
    step(description = "Проверить список доступных способов получения") {
      orderTypes { hasSize(expected.size) }
      expected.forEachIndexed { position, type ->
        orderTypes.childAt<OrderTypeItem>(position) { verifyTitle(type) }
      }
    }

  fun deferredTime(
    deferredTime: DeferredTime,
    actions: DeferredTimeItem.() -> Unit,
  ) = step(description = "Получить карточку отложенного времени '$deferredTime'") {
    deferredTimes { childAt<DeferredTimeItem>(deferredTime.positionOnScreen, actions) }
  }

  fun clickOnCustomDeferredTime() =
    deferredTime(CUSTOM) { clickOnSelf(expected = DEFERRED_TIME_SCREEN_IS_LOADED) }

  fun verifyNotificationIsDisplayed(expected: String) =
    step(description = "Проверить, что отображается уведомление '$expected'") {
      snackBarTextView { hasText(expected) }
      verifyIsLoaded()
    }

  fun verifyDeferredTimeError(expected: String) = step(
      description = "Проверить, что текст ошибки отложенного времени '$expected'"
  ) { deferredTimeError { hasText(expected) } }

  fun verifyPlaceOrderButtonIsDisabled() = step(
      description = "Проверить, что кнопка 'Оформить заказ' неактивна"
  ) { disabledPlaceOrderButton { isDisabled() } }

  fun verifyOverload(expected: String) = step(
      description = "Проверить, что информация о перегрузке '$expected'"
  ) { overloadWarning { hasText(expected) } }

  fun clickOnOverloadModeButton() = flakyStep(
      description = "Нажать на кнопку 'Выбрать время'",
      expected = { overloadModeButton { doesNotExist() } }
  ) { overloadModeButton { click() } }
}
