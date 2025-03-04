package ru.dodopizza.app.screens.product.card.combo

import com.dodopizza.order.feature.product.card.presentation.combo.ComboProductCardFragment
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import ru.dodopizza.app.R
import ru.dodopizza.app.kaspresso.actions.childByText
import ru.dodopizza.app.kaspresso.actions.isClickableAndDoClick
import ru.dodopizza.app.models.enums.MetaProduct
import ru.dodopizza.app.screens.AbstractScreen
import ru.dodopizza.app.screens.foodmenu.components.ProductInfoPopupComponent

/**
 * Экран с деталями комбо-продукта
 */
object ComboProductCardScreen : AbstractScreen<ComboProductCardScreen>(
    layoutId = R.layout.fragment_combo_product_card,
    viewClass = ComboProductCardFragment::class.java
) {

  val productInfo = ProductInfoPopupComponent

  private val self: KView
    get() = bind(R.id.combo_card_layout)
  private val title: KTextView
    get() = bind(R.id.product_title)

  private val chooseProductPrompt: KTextView
    get() = bind(R.id.textViewShowCaseText)
  private val comboImage: KImageView
    get() = bind(R.id.combo_card_image)
  private val addToCartButton: KButton
    get() = bind(R.id.bottom_shopping_bar_add_to_cart_button)

  private val info: KView
    get() = bind(R.id.combo_card_info_icon)
  private val fullPrice: KTextView
    get() = bind(R.id.bottom_shopping_bar_full_price_value)
  private val discountPrice: KTextView
    get() = bind(R.id.bottom_shopping_bar_discount_price_value)

  private val comboRecycler: KRecyclerView
    get() = bind(R.id.combo_card_entity_list) { itemType { DefaultComboSlotItem(it) } }

  fun verifyIsLoaded() = step(
      description = "Проверить, что экран с деталями комбо-продукта успешно загружен"
  ) { self { isDisplayed() } }

  fun verifyTitle(expected: MetaProduct) = step(
      description = "Проверить, что заголовок карточки комбо '$expected'"
  ) { title { hasText(expected.toString()) } }

  fun closeChooseProductPrompt() =
    step(description = "Закрыть подсказку 'Выберите любимые продукты'") {
      chooseProductPrompt { hasText("Выберите любимые продукты") }
      comboImage { click() }
    }

  fun clickOnAddToCartButton() = step(
      description = "Нажать на кнопку 'В корзину'"
  ) { addToCartButton { isClickableAndDoClick() } }

  fun openInfo() = step(description = "Открыть детали комбо") { info { click() } }

  fun verifyFullPrice(expected: String) = step(
      description = "Проверить, что полная стоимость '$expected'"
  ) { fullPrice { hasText(expected) } }

  fun verifyDiscountPrice(expected: String) = step(
      description = "Проверить, что стоимость по скидке '$expected'"
  ) { discountPrice { hasText(expected) } }

  fun verifyComboProducts(vararg products: MetaProduct) =
    step("Проверить содержимое комбо-набора") {
      comboRecycler { hasSize(products.size) }
      products.forEach { comboSlot(it) { verifyTitle(it.toString()) } }
    }

  fun verifyChooseProductPromptIsDisplayed() = step(
      description = "Проверить, что подсказка 'Выберите любимые продукты' отображается"
  ) { chooseProductPrompt { isDisplayed() } }

  fun comboSlot(
    product: MetaProduct,
    actions: DefaultComboSlotItem.() -> Unit,
  ) = step("Получить карточку комбо-продукта '$product'") {
    comboRecycler { childByText<DefaultComboSlotItem>(product, actions) }
  }
}
