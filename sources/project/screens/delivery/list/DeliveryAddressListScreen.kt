package ru.dodopizza.app.screens.delivery.list

import com.dodopizza.geo.feature.deliveryaddresslist.presentation.DeliveryAddressListFragment
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import ru.dodopizza.app.R
import ru.dodopizza.app.kaspresso.actions.childByText
import ru.dodopizza.app.kaspresso.matchers.withAnyId
import ru.dodopizza.app.models.enums.DeliveryAddress
import ru.dodopizza.app.screens.AbstractScreen

/**
 * Экран адресов доставки
 */
object DeliveryAddressListScreen : AbstractScreen<DeliveryAddressListScreen>(
    layoutId = R.layout.fragment_delivery_address_list,
    viewClass = DeliveryAddressListFragment::class.java,
) {

  private val self: KView
    get() = bind { withAnyId(R.id.delivery_address_list_container, R.id.map_container) }

  private val closeButton: KButton
    get() = bind(R.id.close_button)
  private val orderHereButton: KButton
    get() = bind(R.id.order_here_button)
  private val newAddressButton: KButton
    get() = bind(R.id.add_new_address_button)
  private val newAddressLabel: KTextView
    get() = bind(R.id.add_new_address_label)
  private val emptyAddress: KTextView
    get() = bind(R.id.delivery_address_list_empty_address_title)

  private val addresses: KRecyclerView
    get() = bind(R.id.addresses_recycler_view) { itemType { DeliveryAddressedItem(it) } }

  fun verifyIsLoaded() = step(
      description = "Проверить, что экран адресов доставки успешно загружен"
  ) { self { isDisplayed() } }

  fun clickOnCloseButton() = step(
      description = "Нажать на кнопку 'X'"
  ) { closeButton { click() } }

  fun clickOnOrderHereButton() = step(
      description = "Нажать на кнопку 'Доставить сюда'"
  ) { orderHereButton { click() } }

  fun clickOnAddButton() = step(
      description = "Нажать на кнопку 'Добавить'"
  ) { newAddressButton { click() } }

  fun clickOnNewAddressButton() = step(
      description = "Нажать на кнопку 'Добавить новый адрес'"
  ) { newAddressLabel { click() } }

  fun verifyAddressesListIsEmpty() =
    step(description = "Проверить, что список адресов пуст") {
      emptyAddress { hasText("Добавьте свой адрес") }
      newAddressButton { isDisplayed() }
      addresses { hasSize(size = 0) }
    }

  fun addressSlot(
    address: String,
    actions: DeliveryAddressedItem.() -> Unit,
  ) = step("Получить пункт адреса доставки '$address'") {
    addresses { childByText<DeliveryAddressedItem>(address, actions) }
  }

  fun addressSlot(
    address: DeliveryAddress,
    actions: DeliveryAddressedItem.() -> Unit,
  ) = addressSlot(address.fullAddress, actions)
}
