package ru.dodopizza.app.screens.foodmenu.components

import com.dodopizza.order.feature.product.card.presentation.view.ExpandableFoodValueInfoIconView
import io.github.kakaocup.kakao.text.KTextView
import ru.dodopizza.app.R
import ru.dodopizza.app.screens.AbstractComponent

/**
 * Компонент отображения всплывающей информации о продукте
 */
object ProductInfoPopupComponent : AbstractComponent<ProductInfoPopupComponent>(
    layoutId = R.layout.food_value_content_layout,
    viewClass = ExpandableFoodValueInfoIconView::class.java,
) {

  private val foodLabel: KTextView
    get() = bind<KTextView> {
      withId(R.id.label)
      withIndex(0) {}
    }.also { it.inRoot { isPlatformPopup() } }

  fun verifyLabel(expected: String) = step(
      description = "Проверить, что информация о пищевой ценности '$expected'"
  ) { foodLabel { hasText(expected) } }
}
