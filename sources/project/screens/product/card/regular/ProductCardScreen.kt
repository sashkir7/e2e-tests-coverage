package ru.dodopizza.app.screens.product.card.regular

import com.dodopizza.order.feature.product.card.presentation.product.compose.screens.ProductCardTags
import io.github.kakaocup.compose.node.element.KNode
import ru.dodopizza.app.models.enums.MetaProduct
import ru.dodopizza.app.models.enums.PizzaSize
import ru.dodopizza.app.screens.BaseComposeScreen
import ru.dodopizza.app.screens.loyalty.components.LoyaltySwitchComponent
import ru.dodopizza.app.screens.product.card.regular.components.RecipeCustomizationComponent
import ru.dodopizza.app.screens.product.card.regular.components.SizeControlComponent
import ru.dodopizza.app.screens.product.components.bottom.ProductButtonComponent

/**
 * Экран с информацией о продукте
 */
object ProductCardScreen : BaseComposeScreen<ProductCardScreen>(
    viewBuilderAction = { hasTestTag(ProductCardTags.SELF) }
) {

  val productButton = ProductButtonComponent
  val loyaltySwitcher = LoyaltySwitchComponent
  val sizeVariation = SizeControlComponent
  val recipeCustomization = RecipeCustomizationComponent

  private val title: KNode = child {
    useUnmergedTree = true
    hasTestTag(ProductCardTags.TITLE)
  }

  private val description: KNode = child {
    useUnmergedTree = true
    hasTestTag(ProductCardTags.DESCRIPTION)
  }

  fun verifyTitle(expected: MetaProduct) = step(
      description = "Проверить, что заголовок карточки продукта '$expected'"
  ) { title { assertTextEquals(expected.toString()) } }

  fun verifyDescription(expected: String) = step(
      description = "Проверить, что описание пиццы '$expected'"
  ) { description { assertTextEquals(expected) } }

  fun verifyIsLoaded() = step(
      description = "Проверить, что экран карточки продукта успешно загружен"
  ) { assertIsDisplayed() }

  fun clickOnAddToCartButton() = productButton.clickOnAddToCartButton()

  fun clickOnDetailsButton() = recipeCustomization.clickOnSelf()

  fun selectSize(size: PizzaSize) = sizeVariation.selectSize(size)
}
