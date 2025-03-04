package ru.dodopizza.app.screens.product.components.bottom

import com.dodopizza.order.feature.product.card.presentation.product.compose.components.ProductButtonTags
import io.github.kakaocup.compose.node.element.KNode
import ru.dodopizza.app.screens.BaseComposeScreen

/**
 * Компонент кнопки покупки для карточки продукта
 */
object ProductButtonComponent : BaseComposeScreen<ProductButtonComponent>(
    viewBuilderAction = { hasTestTag(ProductButtonTags.SELF) }
) {

  private val dodoCoinsImage: KNode = child {
    useUnmergedTree = true
    hasTestTag(ProductButtonTags.LOYALTY_ICON)
  }

  private val fullPriceText: KNode = child {
    useUnmergedTree = true
    hasTestTag(ProductButtonTags.FULL_PRICE_TEXT)
  }

  private val discountText: KNode = child {
    useUnmergedTree = true
    hasTestTag(ProductButtonTags.DISCOUNT_TEXT)
  }

  fun clickOnAddToCartButton() = step(
      description = "Нажать на кнопку 'В корзину'"
  ) { performClick() }

  fun verifyFullPrice(expected: String) = step(
      description = "Проверить, что полная стоимость '$expected'"
  ) { fullPriceText { assertTextEquals(expected) } }

  fun verifyDiscountPrice(expected: String) = step(
      description = "Проверить, что стоимость со скидкой '$expected'"
  ) { discountText { assertTextEquals(expected) } }

  fun verifyDiscountPriceDoesNotExist() = step(
      description = "Проверить, что стоимость со скидкой не отображается"
  ) { discountText { assertDoesNotExist() } }

  fun verifyAddToCartButtonIsDisabled() = step(
      description = "Проверить, что кнопка 'Добавить в корзину' неактивна"
  ) { assertIsNotEnabled() }

  fun verifyCoinImageIsDisplayed() = step(
      description = "Проверить, что иконка додокоинов отображается"
  ) { dodoCoinsImage { assertIsDisplayed() } }
}
