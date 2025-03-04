package ru.dodopizza.app.screens.foodmenu.components

import com.dodopizza.order.feature.menu.ordertypeswitcher.presentation.OrderTypeSwitcherFragment
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.text.KTextView
import ru.dodopizza.app.R
import ru.dodopizza.app.models.enums.AddressAlias
import ru.dodopizza.app.models.enums.DeliveryAddress
import ru.dodopizza.app.models.enums.Pizzeria
import ru.dodopizza.app.screens.AbstractComponent

/**
 * Компонент отображения адреса на экране меню
 */
object AddressContainerFoodMenuComponent : AbstractComponent<AddressContainerFoodMenuComponent>(
    layoutId = R.layout.fragment_food_menu_order_brick,
    viewClass = OrderTypeSwitcherFragment::class.java
) {

  private val self: KView
    get() = bind(R.id.address_container)
  private val address: KTextView
    get() = bind(R.id.address_text_brick)
  private val averageTime: KTextView
    get() = bind(R.id.delivery_average_time_text_brick)

  fun clickOnSelf() = step(
      description = "Нажать на компонент отображения адреса"
  ) { self { click() } }

  fun verifyAddress(expected: String) = step(
      description = "Проверить, что отображается адрес '$expected'"
  ) { address { hasText(expected) } }

  fun verifyAverageTime(expected: String) = step(
      description = "Проверить, что заголовок стоимости доставки содержит '$expected'"
  ) { averageTime { containsText(expected) } }

  fun verifyAverageTimeIsEmpty() = step(
      description = "Проверить, что заголовок стоимости доставки - пустой"
  ) { averageTime { hasEmptyText() } }

  fun verifyAddress(expected: DeliveryAddress) = verifyAddress(expected.fullAddress)

  fun verifyAddress(expected: Pizzeria) = verifyAddress(expected.shortAddress)

  fun verifyAddress(expected: AddressAlias) = verifyAddress(expected.toString())
}
