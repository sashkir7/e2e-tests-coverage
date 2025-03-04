package ru.dodopizza.app.screens.geo.map.pizzeria

import com.dodopizza.geo.R
import com.dodopizza.geo.feature.combinedmap.presentation.CombinedSelectionMapFragment
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import ru.dodopizza.app.kaspresso.actions.childByText
import ru.dodopizza.app.kaspresso.assertions.isNotEmpty
import ru.dodopizza.app.models.enums.Pizzeria
import ru.dodopizza.app.screens.AbstractScreen

/**
 *  Экран выбора пиццерии (с картой)
 */
object PizzeriaMapAddressScreen : AbstractScreen<PizzeriaMapAddressScreen>(
    layoutId = R.layout.fragment_search_pizzeria,
    viewClass = CombinedSelectionMapFragment::class.java
) {

  val pizzeriaCard = PizzeriaCardComponent

  private val switcherPizzeria: KView
    get() = bind(R.id.switcher_pizzeria)
  private val searchInput: KEditText
    get() = bind(R.id.search_edit_text)
  private val addressInput: KEditText
    get() = bind(R.id.pizzeria_address)
  private val orderHereButton: KButton
    get() = bind(R.id.order_here_button)

  private val filters: KRecyclerView
    get() = bind(R.id.filters_list) { itemType { KRecyclerItem<Any>(it) } }
  private val pizzeriasList: KRecyclerView
    get() = bind(R.id.pizzerias_list) { itemType { KRecyclerItem<Any>(it) } }

  fun verifyIsLoaded() = step(
      description = "Проверить, что экран выбора пиццерии успешно загружен"
  ) { filters { isNotEmpty() } } // Ожидание позиционирования

  fun switchToPizzeria() =
    step(description = "Выбрать тип доставки 'В пиццерии'") {
      switcherPizzeria { click() }
      verifyIsLoaded()
    }

  fun selectAddress(address: String) =
    step(description = "Выбрать адрес пиццерии '$address'") {
      searchInput { click() }

      pizzeriasList { isDisplayed() }
      addressInput { replaceText(address) }
      pizzeriasList { childByText<KRecyclerItem<*>>(address) { click() } }
    }

  fun selectAddress(address: Pizzeria) = selectAddress(address.shortAddress)

  fun clickOnOrderHereButton() = step(
      description = "Нажать на кнопку 'Заказать здесь'"
  ) { orderHereButton { click() } }

  fun verifyFiltersSize(expected: Int) = step(
      description = "Проверить, что кол-во фильтров на карте '$expected'"
  ) { filters { hasSize(expected) } }
}
