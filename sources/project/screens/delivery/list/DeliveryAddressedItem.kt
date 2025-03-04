package ru.dodopizza.app.screens.delivery.list

import android.view.View
import com.dodopizza.geo.R
import com.dodopizza.geo.feature.deliveryaddresslist.adapter.DeliveryAddressWithSelectionVH
import io.github.kakaocup.kakao.check.KCheckBox
import io.github.kakaocup.kakao.text.KButton
import org.hamcrest.Matcher
import ru.dodopizza.app.screens.AbstractRecyclerItem

/**
 * Единичный пункт адреса доставки
 */
class DeliveryAddressedItem(
  parent: Matcher<View>,
) : AbstractRecyclerItem<DeliveryAddressedItem>(
    parent = parent,
    layoutId = R.layout.address_delivery_with_selection_item,
    viewHolderClass = DeliveryAddressWithSelectionVH::class.java
) {

  private val selectedFlag: KCheckBox
    get() = bind(parent) { withId(R.id.address_delivery_with_selection_is_selected_flag) }
  private val editButton: KButton
    get() = bind(parent) { withId(R.id.address_delivery_with_selection_edit_button) }

  fun clickOnSelf() = step(description = "Нажать на пункт адреса доставки") { click() }

  fun clickOnEditButton() = flakyStep(
      description = "Нажать на кнопку редактирования",
      expected = { doesNotExist() }
  ) { editButton { click() } }

  fun verifyIsChecked() = step(
      description = "Проверить, что адрес выбран"
  ) { selectedFlag { isChecked() } }

  fun verifyIsNotChecked() = step(
      description = "Проверить, что адрес не выбран"
  ) { selectedFlag { isNotChecked() } }
}
