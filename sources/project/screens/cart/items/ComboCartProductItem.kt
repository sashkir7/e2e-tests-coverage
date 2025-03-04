package ru.dodopizza.app.screens.cart.items

import android.view.View
import com.dodopizza.order.R
import com.dodopizza.order.feature.shoppingcart.adapter.good.combo.ComboCartProductViewHolder
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import ru.dodopizza.app.kaspresso.matchers.withVisibleId
import ru.dodopizza.app.models.enums.MetaProduct
import ru.dodopizza.app.models.enums.Topping
import ru.dodopizza.app.screens.AbstractRecyclerItem

/**
 * Карточка продукта комбо в корзине (RecyclerViewItem)
 * Располагается на экране корзины
 */
class ComboCartProductItem(
  parent: Matcher<View>,
) : AbstractRecyclerItem<ComboCartProductItem>(
    parent = parent,
    layoutId = R.layout.shopping_cart_combo_product_item,
    viewHolderClass = ComboCartProductViewHolder::class.java
) {

  private val title: KTextView
    get() = bind(parent) { withId(R.id.shopping_product_combo_title) }
  private val leftActionButton: KButton
    get() = bind(parent) { withId(R.id.left_action_button) }
  private val toppings: KTextView
    get() = bind(parent) { withVisibleId(R.id.shopping_product_toppings) }

  fun clickOnChangeComboButton() = step(
      description = "Нажать на кнопку 'Изменить'"
  ) { leftActionButton { click() } }

  fun verifyTitle(expected: MetaProduct) = step(
      description = "Проверить, что заголовок '$expected'"
  ) { title { hasText(expected.toString()) } }

  fun verifyIngredientIsAdded(expected: Topping) = step(
      description = "Проверить, что добавлен ингредиент '$expected'"
  ) { toppings { containsText(expected.toString()) } }
}
