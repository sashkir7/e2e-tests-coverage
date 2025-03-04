package ru.dodopizza.app.screens.product.constructor.combo

import android.view.View
import com.dodopizza.order.R
import com.dodopizza.order.feature.product.card.presentation.pizza.view.PizzaVariationControlViewLite
import io.github.kakaocup.kakao.check.KCheckBox
import org.hamcrest.Matcher
import ru.dodopizza.app.models.enums.DoughType
import ru.dodopizza.app.models.enums.DoughType.THIN
import ru.dodopizza.app.models.enums.DoughType.TRADITIONAL
import ru.dodopizza.app.screens.AbstractComponent

/**
 * Компонент для выбора теста и размера пиццы
 */
class PizzaVariationComponent(
  val parent: Matcher<View>,
) : AbstractComponent<PizzaVariationComponent>(
    layoutId = R.layout.view_pizza_variation_control_lite,
    viewClass = PizzaVariationControlViewLite::class.java
) {

  fun selectDough(expected: DoughType) = step(
      description = "Выбрать тип теста '$expected'"
  ) { expected.checkbox(parent) { click() } }

  fun verifyAvailableDoughType(expected: DoughType) = step(
      description = "Проверить доступность теста '$expected'"
  ) { expected.checkbox(parent) { isDisplayed() } }
}

private fun DoughType.checkbox(
  parent: Matcher<View>,
  actions: KCheckBox.() -> Unit,
) {
  val id = when (this) {
    TRADITIONAL -> R.id.traditional_dough
    THIN -> R.id.thin_dough
  }
  KCheckBox(parent) { withId(id) }.actions()
}
