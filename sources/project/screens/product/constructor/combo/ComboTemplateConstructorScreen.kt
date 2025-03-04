package ru.dodopizza.app.screens.product.constructor.combo

import com.dodopizza.order.feature.product.card.combo.constructor.presentation.ComboTemplateConstructorFragment
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.recycler.KRecyclerView
import ru.dodopizza.app.R
import ru.dodopizza.app.kaspresso.actions.childByText
import ru.dodopizza.app.models.enums.MetaProduct
import ru.dodopizza.app.screens.AbstractScreen

/**
 * Экран конструктора комбо по шаблонам
 */
object ComboTemplateConstructorScreen : AbstractScreen<ComboTemplateConstructorScreen>(
    layoutId = R.layout.combo_template_constructor,
    viewClass = ComboTemplateConstructorFragment::class.java
) {

  private val slotsIndicator: KView
    get() = bind(R.id.combo_template_slots_pager_indicator)

  private val comboSlotRecycler: KRecyclerView
    get() = bind(R.id.combo_template_slot_products_recycler) {
      itemType { CustomizeTemplatePizzaItem(it) }
    }

  fun comboSlot(
    product: MetaProduct,
    actions: CustomizeTemplatePizzaItem.() -> Unit,
  ) = step(description = "Получить позицию конструктора комбо '$product'") {
    comboSlotRecycler.childByText<CustomizeTemplatePizzaItem>(product)
        .apply {
          this@ComboTemplateConstructorScreen.softRightSwipe()
          actions()
        }
  }

  fun close() = step(
      description = "Закрыть экран конструктора комбо по шаблонам"
  ) { slotsIndicator { click() } }
}
