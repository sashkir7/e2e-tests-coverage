package ru.dodopizza.app.screens.product.card.halves

import com.dodopizza.order.R
import com.dodopizza.order.feature.halves.productcard.presentation.HalvesProductCardFragment
import io.github.kakaocup.kakao.common.views.KView
import ru.dodopizza.app.screens.AbstractScreen

/**
 * Карточка с деталями по продукту пицца-половики
 */
object HalvesProductCardScreen : AbstractScreen<HalvesProductCardScreen>(
    layoutId = R.layout.fragment_halves_product_card,
    viewClass = HalvesProductCardFragment::class.java
) {

  private val self: KView
    get() = bind(R.id.halves_card_item_navigation_bar)

  fun verifyIsLoaded() = step(
      description = "Проверить, что экран деталей для половинок успешно загружен"
  ) { self { isDisplayed() } }
}
