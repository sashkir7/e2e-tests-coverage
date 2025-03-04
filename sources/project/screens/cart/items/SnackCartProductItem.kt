package ru.dodopizza.app.screens.cart.items

import android.view.View
import com.dodopizza.order.R
import com.dodopizza.order.feature.shoppingcart.adapter.good.snack.SnackCartProductViewHolder
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import ru.dodopizza.app.models.enums.PersonalOffer
import ru.dodopizza.app.models.enums.MetaProduct
import ru.dodopizza.app.screens.AbstractRecyclerItem

/**
 * Карточка продукта закусок/десертов/соусов/прочего в корзине (RecyclerViewItem)
 * Располагается на экране корзины
 */
class SnackCartProductItem(
  parent: Matcher<View>,
) : AbstractRecyclerItem<SnackCartProductItem>(
    parent = parent,
    layoutId = R.layout.shopping_cart_snack_product_item,
    viewHolderClass = SnackCartProductViewHolder::class.java
) {

  private val title: KTextView
    get() = bind(parent) { withId(R.id.shopping_product_snack_title) }
  private val stopDescription: KTextView
    get() = bind(parent) { withId(R.id.shopping_product_snack_stop) }
  private val discount: KTextView
    get() = bind(parent) { withId(R.id.shopping_product_snack_discount) }
  private val timeRemain: KTextView
    get() = bind(parent) { withId(R.id.personal_price_time_remain) }

  private val finalPrice: KTextView
    get() = bind(parent) { withId(R.id.shopping_product_snack_price) }
  private val rawPrice: KTextView
    get() = bind(parent) { withId(R.id.shopping_product_snack_discount_price) }

  private val counterLabel: KTextView
    get() = bind(parent) { withId(R.id.shopping_cart_counter_label) }

  fun verifyTitle(expected: MetaProduct) = step(
      description = "Проверить, что заголовок '$expected'"
  ) { title { hasText(expected.toString()) } }

  fun verifyStopDescription(expected: String) = step(
      description = "Проверить, что описание стопа '$expected'"
  ) { stopDescription { hasText(expected) } }

  fun verifyDiscount(expected: String) = step(
      description = "Проверить, что скидка '$expected'"
  ) { discount { hasText(expected) } }

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

  fun verifyCounter(expected: Int) = step(
      description = "Проверить, что количество '$expected'"
  ) { counterLabel { hasText(expected.toString()) } }
}
