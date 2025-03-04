package ru.dodopizza.app.screens.cart

import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.text.KButton
import ru.dodopizza.app.R
import com.dodopizza.order.feature.promocode.presentation.PromoCodeDialog
import ru.dodopizza.app.screens.AbstractComponent

/**
 * Компонент для ввода промокода
 * Располагается на экране корзины
 */
object PromocodeComponent : AbstractComponent<PromocodeComponent>(
    layoutId = R.layout.dialog_promocode,
    viewClass = PromoCodeDialog::class.java,
) {

  private val input: KEditText
    get() = bind(R.id.promocode_input_field)
  private val applyButton: KButton
    get() = bind(R.id.promocode_apply_button)
  private val inputError: KEditText
    get() = bind(R.id.textinput_error)

  fun applyPromocode(promocode: String) =
    step("Применить промокод '$promocode'") {
      input { typeText(promocode) }
      applyButton { click() }
    }

  fun verifyInvalidPromocodeMessageIsDisplayed() = step(
      description = "Проверить, что отображается ошибка 'Промокод введен неверно.'"
  ) { inputError { hasText("Промокод введен неверно.") } }
}
