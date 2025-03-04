package ru.dodopizza.app.screens.product.card.regular

import androidx.compose.ui.test.swipeUp
import com.dodopizza.order.feature.product.card.presentation.product.compose.screens.ProductCardDetailsScreenTags
import ru.dodopizza.app.models.enums.DoughType
import ru.dodopizza.app.models.enums.Topping
import ru.dodopizza.app.screens.BaseComposeScreen
import ru.dodopizza.app.screens.loyalty.components.LoyaltySwitchComponent
import ru.dodopizza.app.screens.product.card.regular.components.CloseButtonComponent
import ru.dodopizza.app.screens.product.card.regular.components.DoughControlComponent
import ru.dodopizza.app.screens.product.card.regular.components.ProductCompositionComponent
import ru.dodopizza.app.screens.product.card.regular.components.ProductFoodValueComponent
import ru.dodopizza.app.screens.product.card.regular.components.ProductToppingsComponent
import ru.dodopizza.app.screens.product.card.regular.components.RemoveIngredientsComponent
import ru.dodopizza.app.screens.product.components.bottom.ProductButtonComponent

/**
 * Экран детальной информации карточки продукта
 */
object ProductCardDetailsScreen : BaseComposeScreen<ProductCardDetailsScreen>(
    viewBuilderAction = { hasTestTag(ProductCardDetailsScreenTags.SELF) }
) {

  val foodValue = ProductFoodValueComponent
  val compositionValue = ProductCompositionComponent
  val productButton = ProductButtonComponent
  val loyaltySwitcher = LoyaltySwitchComponent
  val toppings = ProductToppingsComponent
  val removeIngredients = RemoveIngredientsComponent
  val doughControl = DoughControlComponent
  val closeButton = CloseButtonComponent

  fun verifyIsLoaded() = step(
      description = "Проверить, что экран детальной информации успешно загружен"
  ) { assertIsDisplayed() }

  fun scrollToValueDetails() = step(
      description = "Прокрутить вверх для просмотра информации"
  ) { performTouchInput { swipeUp() } }

  fun clickOnAddToCartButton() = productButton.clickOnAddToCartButton()

  fun clickOnTopping(topping: Topping) = toppings.clickOnTopping(topping)

  fun selectDoughType(doughType: DoughType) = doughControl.selectDoughType(doughType)

  fun clickOnRemovableIngredient(vararg ingredients: Topping) =
    step("Удалить ингредиент '$ingredients' и сохранить изменения") {
      ingredients.forEach { ingredient -> removeIngredients.clickOnTopping(ingredient) }
    }

  fun clickOnBackButton() = step(
      description = "Закрыть экран"
  ) { closeButton { clickOnSelf() } }
}
