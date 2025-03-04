package ru.dodopizza.app.screens.loyalty.products

import com.dodopizza.loyalty.R
import com.dodopizza.loyalty.products.presentation.LoyaltyProductsFragment
import io.github.kakaocup.kakao.recycler.KRecyclerView
import ru.dodopizza.app.kaspresso.actions.childByText
import ru.dodopizza.app.models.enums.MetaProduct
import ru.dodopizza.app.screens.AbstractScreen
import ru.dodopizza.app.screens.common.DComposeButton

/**
 * Экран продуктов в программе лояльности
 */
object LoyaltyProductsScreen : AbstractScreen<LoyaltyProductsScreen>(
    layoutId = R.layout.fragment_loyalty_products,
    viewClass = LoyaltyProductsFragment::class.java
) {

  private val productsRecycler: KRecyclerView
    get() = bind(R.id.loyalty_products_list) { itemType { LoyaltyProductItem(it) } }

  private val goToCartButton = DComposeButton(R.id.loyalty_snackbar_button)

  fun clickOnGoToCartButton() = step(
      description = "Нажать на кнопку 'В корзину'"
  ) { goToCartButton { performClick() } }

  fun productSlot(
    product: MetaProduct,
    actions: LoyaltyProductItem.() -> Unit,
  ) = step("Получить позицию списка продуктов '$product'") {
    productsRecycler.childByText<LoyaltyProductItem>(product)
        .apply {
          this@LoyaltyProductsScreen.softDownSwipe()
          actions()
        }
  }
}
