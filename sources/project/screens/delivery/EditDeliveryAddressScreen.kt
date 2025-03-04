package ru.dodopizza.app.screens.delivery

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.dodopizza.geo.feature.editdeliveryaddressmap.presentation.EditDeliveryAddressMapFragment
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import ru.dodopizza.app.R
import ru.dodopizza.app.models.enums.AddressAlias
import ru.dodopizza.app.models.enums.City
import ru.dodopizza.app.models.enums.DeliveryAddress
import ru.dodopizza.app.screens.AbstractScreen
import ru.dodopizza.app.screens.external.AlertDialogComponent

/**
 * Экран выбора нового адреса доставки
 */
object EditDeliveryAddressScreen : AbstractScreen<EditDeliveryAddressScreen>(
    layoutId = R.layout.fragment_delivery_edit_address_map,
    viewClass = EditDeliveryAddressMapFragment::class.java,
) {

  val alertDialog = AlertDialogComponent

  private val addressMap: KEditText
    get() = bind(withId(R.id.address_map_container_layout)) { withId(R.id.address_map_edit_text) }
  private val addressLocation: KEditText
    get() = bind(withId(R.id.delivery_location_address)) { withId(R.id.address_map_edit_text) }

  private val saveAddressButton: KButton
    get() = bind(R.id.save_address_button)
  private val deleteAddressButton: KButton
    get() = bind(R.id.delete_address_button)

  private val locationRecycler: KRecyclerView
    get() = bind(R.id.delivery_location_list) { itemType { KRecyclerItem<Any>(it) } }

  fun verifyIsLoaded() = step(
      description = "Проверить, что экран добавления нового адреса успешно загружен"
  ) { addressMap { isDisplayed() } }

  fun selectAddress(
    city: City,
    address: String,
  ) = step(description = "Выбрать адрес '$address'") {
    addressMap { click() }
    addressLocation { replaceText(address) }
    locationRecycler.childWith<KRecyclerItem<Any>> {
      withDescendant { withText(address) }
      withDescendant { containsText(city.toString()) }
    }
        .click()
  }

  fun selectAlias(alias: String) =
    step(description = "Ввести алиас адреса '$alias'") {
      KEditText { withHint("Название места — видно только вам") }.replaceText(alias)
      closeSoftKeyboard()
    }

  fun clickOnAlias(alias: AddressAlias) = flakyStep(
      description = "Нажать на алиас адреса '$alias'",
      expected = { alias.chip.isChecked() }
  ) { alias.chip { click() } }

  fun selectAddress(input: DeliveryAddress) =
    selectAddress(city = input.city, address = input.address)

  fun clickOnSaveAddressButton() = step(
      description = "Нажать на кнопку 'Сохранить адрес'"
  ) { saveAddressButton { click() } }

  fun clickOnDeleteAddressButton() = step(
      description = "Нажать на кнопку 'Удалить'"
  ) { deleteAddressButton { click() } }
}
