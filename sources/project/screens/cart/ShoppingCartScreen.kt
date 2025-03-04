package ru.dodopizza.app.screens.cart

import android.widget.ImageView
import com.dodopizza.order.feature.shoppingcart.presentation.ShoppingCartFragment
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import ru.dodopizza.app.R
import ru.dodopizza.app.kaspresso.actions.childByText
import ru.dodopizza.app.models.enums.PersonalOffer
import ru.dodopizza.app.models.enums.MetaProduct
import ru.dodopizza.app.models.enums.ProductCategory.COMBO
import ru.dodopizza.app.models.enums.ProductCategory.PIZZA
import ru.dodopizza.app.screens.AbstractScreen
import ru.dodopizza.app.screens.cart.items.ComboCartProductItem
import ru.dodopizza.app.screens.cart.items.PizzaCartProductItem
import ru.dodopizza.app.screens.cart.items.SnackCartProductItem
import ru.dodopizza.app.screens.cart.components.FeeDeliveryInfoComponent
import ru.dodopizza.app.screens.cart.items.UpsellProductItem
import ru.dodopizza.app.screens.profile.PersonalOffersComponent

/**
 * Экран корзины
 */
object ShoppingCartScreen : AbstractScreen<ShoppingCartScreen>(
    layoutId = R.layout.fragment_shopping_cart,
    viewClass = ShoppingCartFragment::class.java,
) {

  val promocode = PromocodeComponent
  val personalOffers = PersonalOffersComponent
  val feeDeliveryInfo = FeeDeliveryInfoComponent

  private val minPriceButton: KButton
    get() = bind(R.id.shopping_cart_min_price_button)
  private val placeOrderButton: KButton
    get() = bind(R.id.shopping_cart_place_an_order_button)

  private val activePromocode: KTextView
    get() = bind(R.id.active_promocode_text)
  private val activePromocodeTitle: KTextView
    get() = bind(R.id.title_promo)
  private val wrongConditions: KTextView
    get() = bind(R.id.wrong_conditions)
  private val discountAmount: KTextView
    get() = bind(R.id.discount_amount)

  private val deliveryFeeInfo: KView
    get() = bind(R.id.delivery_fee_info)
  private val deliveryFeeAmount: KTextView
    get() = bind(R.id.delivery_fee_amount)

  private val enterPromocodeButton: KButton
    get() = bind(R.id.enter_promocode_button)
  private val resetPromocodeButton: KButton
    get() = bind(R.id.reset_promocode_button)
  private val minDeliveryTitle: KTextView
    get() = bind(R.id.shopping_cart_min_delivery_title)
  private val loyaltyRewardSum: KTextView
    get() = bind(R.id.shopping_cart_loyalty_reward_sum)
  private val upsellTitle: KTextView
    get() = bind(R.id.upsell_title)

  private val closeButton: KView
    get() = bind {
      withParent { withId(R.id.shopping_cart_toolbar) }
      isInstanceOf(ImageView::class.java)
    }

  private val cartProducts: KRecyclerView
    get() = bind(R.id.shopping_cart_recycler_view) {
      itemType { PizzaCartProductItem(it) }
      itemType { SnackCartProductItem(it) }
      itemType { ComboCartProductItem(it) }
    }

  private val upsellProducts: KRecyclerView
    get() = bind(R.id.upsale_products1) { itemType { UpsellProductItem(it) } }

  fun verifyIsLoaded() = step(
      description = "Проверить, что экран корзины успешно загружен"
  ) { cartProducts { isDisplayed() } }

  fun applyPromocode(promocode: String) =
    step(description = "Применить промокод '$promocode'") {
      enterPromocodeButton { click() }
      promocode { applyPromocode(promocode) }
    }

  fun resetPromocode() = step(
      description = "Отменить промоакцию"
  ) { resetPromocodeButton { click() } }

  fun clickOnCloseButton() = step(
      description = "Нажать на кнопку 'X'"
  ) { closeButton { click() } }

  fun clickOnPlaceOrderButton() = flakyStep(
      description = "Нажать на кнопку 'Оформить'",
      expectedTimeoutMs = 5_000,
      expected = { KView { withId(R.id.checkout_details_container) }.isDisplayed() }
  ) { placeOrderButton { click() } }

  fun clickOnMinPriceButton() = flakyStep(
      description = "Нажать на кнопку 'Добавить товары'",
      expected = { minPriceButton { doesNotExist() } }
  ) { minPriceButton { click() } }

  fun clickOnDeliveryFeeInfo() = step(
      description = "Нажать на иконку информации о доставке 'i'"
  ) { deliveryFeeInfo { click() } }

  fun verifyDeliveryFeeInfo(expected: String) = step(
      description = "Проверить, что содержание подсказки доставки '$expected'"
  ) { KTextView { withId(R.id.text) }.hasText(expected) }

  fun verifyMinPriceButtonText(expected: String) = step(
      description = "Проверить, что текст кнопки содержит '$expected'"
  ) { minPriceButton { containsText(expected) } }

  fun verifyDiscountAmount(expected: String) = step(
      description = "Проверить наличие скидки '$expected'"
  ) { discountAmount { hasText(expected) } }

  fun verifyDeliveryFeeAmount(expected: String) = step(
      description = "Проверить, что стоимость доставки '$expected'"
  ) { deliveryFeeAmount { hasText(expected) } }

  fun verifyOfferHasBeenApplied(offer: PersonalOffer) =
    step("Проверить, что применена акция '$offer'") {
      activePromocode { hasText("Акция применена") }
      activePromocodeTitle { hasText(offer.toString()) }
    }

  fun verifyOfferConditionsAreNotMet() =
    verifyOfferConditions(expected = "Условия акции не выполнены.")

  fun verifyOfferConditionsNotMetAtSelectedVenue() =
    verifyOfferConditions(
        expected = "Акция не работает в выбранном заведении. Воспользуйтесь другой акцией"
    )

  fun verifyOfferConditionsNotMetAtApp() =
    verifyOfferConditions(expected = "Акция работает только в приложении")

  fun verifyPromoCodeText(expected: String) = step(
      description = "Проверить, что акция применена с текстом 'Активен код $expected'"
  ) { activePromocode { hasText("Активен код $expected") } }

  fun verifyProducts(vararg expected: MetaProduct) =
    step("Проверить содержимое корзины") {
      verifyProductsSize(expected.size)
      expected.forEachIndexed { index, product ->
        when (product.category) {
          PIZZA -> cartProducts.childAt<PizzaCartProductItem>(index) { verifyTitle(product) }
          COMBO -> cartProducts.childAt<ComboCartProductItem>(index) { verifyTitle(product) }
          else -> cartProducts.childAt<SnackCartProductItem>(index) { verifyTitle(product) }
        }
      }
    }

  fun verifyProductsSize(expected: Int) = step(
      description = "Проверить, что кол-во позиций в корзине '$expected'"
  ) { cartProducts { hasSize(expected) } }

  fun verifyLoyaltyRewardSum(expected: String) = step(
      description = "Проверить, что сумма вознаграждения '$expected' (додокоины)"
  ) { loyaltyRewardSum { hasText(expected) } }

  fun verifyMinDeliveryTitle(expected: String) = step(
      description = "Проверить, что отображается заголовок '$expected'"
  ) { minDeliveryTitle { containsText(expected) } }

  fun verifyUpsellTitle(expected: String) = step(
      description = "Проверить, что заголовок upsell-блока '$expected'"
  ) { upsellTitle { hasText(expected) } }

  fun verifyPlaceOrderButtonText(expected: String) = step(
      description = "Проверить, что текст кнопки 'Оформить заказ' содержит '$expected'"
  ) { placeOrderButton { containsText(expected) } }

  fun verifyUpsellProductsSize(expected: Int) = step(
      description = "Проверить, что размер upsell-блока '$expected'"
  ) { upsellProducts { hasSize(expected) } }

  fun verifyDeliveryFeeIsNotVisible() = step(
      description = "Проверить, что стоимость доставки не отображается"
  ) { deliveryFeeAmount { isGone() } }

  fun pizzaSlot(
    product: MetaProduct,
    actions: PizzaCartProductItem.() -> Unit,
  ) = step("Получить из корзины позицию '$product'") {
    cartProducts { childByText<PizzaCartProductItem>(product, actions) }
  }

  fun snackSlot(
    product: MetaProduct,
    actions: SnackCartProductItem.() -> Unit,
  ) = step("Получить из корзины позицию '$product'") {
    cartProducts { childByText<SnackCartProductItem>(product, actions) }
  }

  fun comboSlot(
    product: MetaProduct,
    actions: ComboCartProductItem.() -> Unit,
  ) = step("Получить из корзины позицию '$product'") {
    cartProducts { childByText<ComboCartProductItem>(product, actions) }
  }

  fun upsellSlot(
    product: MetaProduct,
    actions: UpsellProductItem.() -> Unit,
  ) = step("Получить upsell-продукт '$product'") {
    upsellProducts { childByText<UpsellProductItem>(product, actions) }
  }

  private fun verifyOfferConditions(expected: String) = step(
      description = "Проверить, что отображается сообщение '$expected'"
  ) { wrongConditions { hasText(expected) } }
}
