package ru.dodopizza.app.screens.cart.items

import android.view.View
import com.dodopizza.order.R
import com.dodopizza.order.feature.shoppingcart.adapter.good.pizza.PizzaCartProductViewHolder
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import ru.dodopizza.app.models.enums.PersonalOffer
import ru.dodopizza.app.models.enums.MetaProduct
import ru.dodopizza.app.screens.AbstractRecyclerItem

/**
 * Карточка продукта пиццы в корзине (RecyclerViewItem)
 * Располагается на экране корзины
 */
class PizzaCartProductItem(
  parent: Matcher<View>,
) : AbstractRecyclerItem<PizzaCartProductItem>(
    parent = parent,
    layoutId = R.layout.shopping_cart_pizza_product_item,
    viewHolderClass = PizzaCartProductViewHolder::class.java
) {

  private val title: KTextView
    get() = bind(parent) { withId(R.id.shopping_product_pizza_title) }
  private val desc: KTextView
    get() = bind(parent) { withId(R.id.shopping_product_pizza_size_description) }
  private val toppingDesc: KTextView
    get() = bind(parent) { withId(R.id.shopping_product_pizza_toppings) }
  private val removedIngredientsDesc: KTextView
    get() = bind(parent) { withId(R.id.shopping_product_pizza_removed_ingredients) }
  private val discount: KTextView
    get() = bind(parent) { withId(R.id.shopping_product_pizza_discount) }
  private val timeRemain: KTextView
    get() = bind(parent) { withId(R.id.personal_price_time_remain) }

  private val finalPrice: KTextView
    get() = bind(parent) { withId(R.id.shopping_product_pizza_price) }
  private val rawPrice: KTextView
    get() = bind(parent) { withId(R.id.shopping_product_pizza_discount_price) }
  private val loyaltyPrice: KTextView
    get() = bind(parent) { withId(R.id.shopping_cart_pizza_dodocoins_price) }

  private val removeButton: KButton
    get() = bind(parent) { withId(R.id.shopping_cart_remove_button) }
  private val editButton: KButton
    get() = bind(parent) { withId(R.id.shopping_product_pizza_edit_button) }
  private val counterLabel: KTextView
    get() = bind(parent) { withId(R.id.shopping_cart_counter_label) }
  private val plusButton: KButton
    get() = bind(parent) { withId(R.id.shopping_cart_plus_counter_button) }
  private val minusButton: KButton
    get() = bind(parent) { withId(R.id.shopping_cart_minus_counter_button) }

  fun increaseProduct(times: Int = 1) =
    step(description = "Увеличить количество на '$times'") {
      for (i in 1..times) {
        plusButton { click() }
        Thread.sleep(750)
      }
    }

  fun reduceProduct(times: Int = 1) =
    step(description = "Уменьшить количество на '$times'") {
      for (i in 1..times) {
        minusButton { click() }
        Thread.sleep(750)
      }
    }

  fun clickOnRemoveButton() = step(
      description = "Нажать на кнопку 'Удалить'"
  ) { removeButton { click() } }

  fun verifyRemoveButtonText(expected: String) = step(
      description = "Проверить, что текст кнопки удаления '$expected'"
  ) { removeButton { hasText(expected) } }

  fun clickOnEditButton() = flakyStep(
      description = "Нажать на кнопку 'Изменить'",
      expected = { editButton { doesNotExist() } }
  ) { editButton { click() } }

  fun verifyTitle(expected: MetaProduct) = step(
      description = "Проверить, что заголовок '$expected'"
  ) { title { hasText(expected.toString()) } }

  fun verifyDescription(expected: String) = step(
      description = "Проверить, что описание '$expected'"
  ) { desc { hasText(expected) } }

  fun verifyDiscount(expected: String) = step(
      description = "Проверить, что скидка '$expected'"
  ) { discount { hasText(expected) } }

  fun verifyLoyaltyPrice(expected: String) = step(
      description = "Проверить, что скидка лояльности '$expected'"
  ) { loyaltyPrice { hasText(expected) } }

  fun verifyLoyaltyPriceIsEmpty() = verifyLoyaltyPrice(expected = "")

  fun verifyDiscount(expected: PersonalOffer) = verifyDiscount(expected.toString())

  fun verifyTimeRemain(expected: String) = step(
      description = "Проверить, что время действия скидки '$expected'"
  ) { timeRemain { hasText(expected) } }

  fun verifyFinalPrice(expected: String) = step(
      description = "Проверить, что персональная цена '$expected'"
  ) { finalPrice { hasText(expected) } }

  fun verifyRawPrice(expected: String) = step(
      description = "Проверить, что изначальная цена '$expected'"
  ) { rawPrice { hasText(expected) } }

  fun verifyCount(expected: Int) = step(
      description = "Проверить, что количество '$expected'"
  ) { counterLabel { hasText(expected.toString()) } }

  fun verifyDescriptionTopping(expected: String) = step(
      description = "Проверить, что описание ингридиента '$expected'"
  ) { toppingDesc { hasText(expected) } }

  fun verifyRemovedIngredients(expected: String) = step(
      description = "Проверить, что описание удаленного ингридиента '$expected'"
  ) { removedIngredientsDesc { hasText(expected) } }
}
