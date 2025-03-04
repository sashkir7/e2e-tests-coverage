package ru.dodopizza.app.screens.checkout.components

import com.dodopizza.order.feature.checkout.deliverytotable.presentation.SelectPackagingTypeDialog
import io.github.kakaocup.kakao.text.KTextView
import ru.dodopizza.app.R
import ru.dodopizza.app.screens.AbstractComponent
import ru.dodopizza.app.screens.checkout.orderType.OrderType

/**
 * Компонент выбора способа получения заказа
 */
object SelectPackagingComponent : AbstractComponent<SelectPackagingComponent>(
    layoutId = R.layout.dialog_select_packaging_type,
    viewClass = SelectPackagingTypeDialog::class.java
) {

  fun selectType(type: OrderType) = step(
      description = "Выбрать способ получения заказа '$type'"
  ) { bindNamed<KTextView>(name = "orderType") { withText(type.toString()) }.click() }
}
