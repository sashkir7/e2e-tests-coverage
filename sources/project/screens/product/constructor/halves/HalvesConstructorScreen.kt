package ru.dodopizza.app.screens.product.constructor.halves

import com.dodopizza.order.R
import io.github.kakaocup.kakao.text.KButton
import com.dodopizza.order.feature.halves.constructor.presentation.HalvesConstructorFragment
import io.github.kakaocup.kakao.image.KImageView
import ru.dodopizza.app.screens.AbstractScreen

/**
 * Экран конструктора пиицы из половинок
 */
object HalvesConstructorScreen : AbstractScreen<HalvesConstructorScreen>(
    layoutId = R.layout.fragment_halfs_constructor,
    viewClass = HalvesConstructorFragment::class.java,
) {

  private val buildHalvesButton: KButton
    get() = bind(R.id.build_halves)
  private val addToCartButton: KButton
    get() = bind(R.id.bottom_shopping_bar_add_to_cart_button)
  private val rightTasteTag: KImageView
    get() = bind { withParent { withId(R.id.r_tags_container) } }

  fun verifyIsLoaded() = step(
      description = "Проверить, что экран конструктора пиицы из половинок успешно загружен"
  ) { buildHalvesButton { isDisplayed() } }

  fun verifyRightTasteTagIsDisplayed() = step(
      description = "Проверить, что вкусовой тег для правой половинки пиццы отображается"
  ) { rightTasteTag { isDisplayed() } }

  fun clickOnBuildHalvesButton() = step(
      description = "Нажать на кнопку 'Объединить половинки'"
  ) { buildHalvesButton { click() } }

  fun clickOnAddToCartButton() = flakyStep(
      description = "Нажать на кнопку 'В корзину'",
      expected = { addToCartButton { doesNotExist() } }
  ) { addToCartButton { click() } }
}
