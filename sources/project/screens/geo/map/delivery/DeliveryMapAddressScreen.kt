package ru.dodopizza.app.screens.geo.map.delivery

import com.dodopizza.geo.R
import com.dodopizza.geo.feature.address.presentation.DeliveryAddressFragment
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import ru.dodopizza.app.kaspresso.actions.childByText
import ru.dodopizza.app.models.enums.DeliveryAddress
import ru.dodopizza.app.screens.AbstractScreen
import ru.dodopizza.app.screens.delivery.list.DeliveryAddressedItem

/**
 *  Экран выбора адреса доставки (с картой)
 */
object DeliveryMapAddressScreen : AbstractScreen<DeliveryMapAddressScreen>(
    layoutId = R.layout.fragment_delivery_address,
    viewClass = DeliveryAddressFragment::class.java
) {

  private val warning: KView
    get() = bind(R.id.warning)
  private val switcherDelivery: KView
    get() = bind(R.id.switcher_delivery)
  private val countryButton: KButton
    get() = bind {
      withId(R.id.country_select_button)
      isVisible()
    }

  private val addressInput: KEditText
    get() = bind(R.id.address_map_edit_text)
  private val saveAddressButton: KButton
    get() = bind(R.id.save_address_button)

  private val searchAddresses: KRecyclerView
    get() = bind(R.id.delivery_location_list) { itemType { DeliveryLocationItem(it) } }
  private val savedAddresses: KRecyclerView
    get() = bind(R.id.addresses_recycler_view) { itemType { DeliveryAddressedItem(it) } }

  fun verifyIsLoaded() = step(
      description = "Проверить, что экран выбора адреса доставки успешно загружен"
  ) { warning { isDisplayed() } } // Ожидание позиционирования

  fun switchToDelivery() =
    step(description = "Выбрать тип доставки 'Доставка'") {
      switcherDelivery { click() }
      verifyIsLoaded()
    }

  fun clickOnCountryButton() = flakyStep(
      description = "Нажать на кнопку с выбранной страной",
      expected = { countryButton { doesNotExist() } }
  ) { countryButton { click() } }

  fun searchAddress(address: DeliveryAddress) =
    step("Выполнить поиск адреса '$address'") {
      addressInput {
        click()
        replaceText(address.address)
      }
    }

  fun clickOnSaveAddressButton() = flakyStep(
      description = "Нажать на кнопку 'Сохранить адрес'",
      expected = { saveAddressButton { doesNotExist() } },
      expectedTimeoutMs = 10_000
  ) { saveAddressButton { click() } }

  fun searchedAddress(
    address: DeliveryAddress,
    actions: DeliveryLocationItem.() -> Unit,
  ) = step("Получить адрес доставки (поиск) '${address.fullAddress}'") {
    searchAddresses {
      childWith<DeliveryLocationItem> {
        withDescendant { withText(address.address) }
        withDescendant { containsText(address.city.toString()) }
      }.actions()
    }
  }

  fun savedAddress(
    address: DeliveryAddress,
    actions: DeliveryAddressedItem.() -> Unit,
  ) = step("Получить сохраненный адрес '${address.fullAddress}'") {
    savedAddresses { childByText<DeliveryAddressedItem>(address.fullAddress, actions) }
  }
}
