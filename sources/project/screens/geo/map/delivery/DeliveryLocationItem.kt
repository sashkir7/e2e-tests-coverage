package ru.dodopizza.app.screens.geo.map.delivery

import android.view.View
import com.dodopizza.geo.R
import com.dodopizza.geo.feature.suggestions.adapter.DeliveryLocationVH
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import ru.dodopizza.app.screens.AbstractRecyclerItem
import ru.dodopizza.app.screens.delivery.list.DeliveryAddressedItem

/**
 * Единичный адрес доставки
 */
class DeliveryLocationItem(
  parent: Matcher<View>,
) : AbstractRecyclerItem<DeliveryAddressedItem>(
    parent = parent,
    layoutId = R.layout.item_address_list,
    viewHolderClass = DeliveryLocationVH::class.java
) {

  private val distanceToUser: KTextView
    get() = bind(parent) { withId(R.id.distance_to_user) }

  fun clickOnSelf() = step(description = "Выбрать адрес доставки") { click() }

  fun verifyDistanceToUser(containsExpected: String) = step(
      description = "Проверить, что расстояние до пользователя содержит '$containsExpected'"
  ) { distanceToUser { containsText(containsExpected) } }
}
