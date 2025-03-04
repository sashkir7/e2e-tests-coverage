package ru.dodopizza.app.screens.product.card.combo

import android.view.View
import com.dodopizza.order.feature.product.card.combo.customize.presentation.CustomizeComboSlotViewHolder

import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import ru.dodopizza.app.R
import ru.dodopizza.app.models.enums.MetaProduct
import ru.dodopizza.app.screens.AbstractRecyclerItem

/**
 * Карточка комбо-продукта (RecyclerViewItem)
 * Располагается на экране с деталями комбо-продукта
 */
class DefaultComboSlotItem(
  parent: Matcher<View>,
) : AbstractRecyclerItem<DefaultComboSlotItem>(
    parent = parent,
    layoutId = R.layout.combo_customize_product_card_slot_item,
    viewHolderClass = CustomizeComboSlotViewHolder::class.java
) {

  private val title: KTextView
    get() = bind(parent) { withId(R.id.combo_slot_title) }
  private val extraPrice: KTextView
    get() = bind(parent) { withId(R.id.combo_slot_extra_price) }
  private val description: KTextView
    get() = bind(parent) { withId(R.id.combo_slot_size_badge) }
  private val specialIngredients: KTextView
    get() = bind(parent) { withId(R.id.combo_slot_receipt) }

  fun open() = step(description = "Перейти в конструктор комбо") { click() }

  fun verifyTitle(expected: String) = step(
      description = "Проверить, что заголовок '$expected'"
  ) { title { hasText(expected) } }

  fun verifyTitle(expected: MetaProduct) = verifyTitle(expected.toString())

  fun verifyDescription(expected: String) = step(
      description = "Проверить, что описание '$expected'"
  ) { description { hasText(expected) } }

  fun verifySpecialIngredients(expected: String) = step(
      description = "Проверить, что информация о кастомных продуктах '$expected'"
  ) { specialIngredients { hasText(expected) } }

  fun verifyExtraPrice(expected: String) = step(
      description = "Проверить, что дополнительная цена '$expected'"
  ) { extraPrice { hasText(expected) } }
}
