package ru.dodopizza.app.screens.profile.self.components

import com.dodopizza.order.R
import com.dodopizza.order.feature.offerdialog.presentation.OfferDialogFragment
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import ru.dodopizza.app.screens.AbstractComponent
import ru.dodopizza.app.screens.common.DComposeButton

/**
 * Компонент отображения информации по спецпредложению (акции)
 */
object OfferDialogComponent : AbstractComponent<OfferDialogComponent>(
    layoutId = R.layout.dialog_personal_offer,
    viewClass = OfferDialogFragment::class.java
) {

  private val title: KTextView
    get() = bind(R.id.personal_offer_fragment_title)
  private val alertTitle: KTextView
    get() = bind(ru.dodopizza.app.R.id.alertTitle)
  private val alertMessage: KTextView
    get() = bind(android.R.id.message)
  private val alertReplaceButton: KButton
    get() = bind(android.R.id.button1)

  private val mainButton = DComposeButton(R.id.offer_fragment_main_button)

  fun verifyTitle(expected: String) = step(
      description = "Проверить, что заголовок диалога акции '$expected'"
  ) { title { hasText(expected) } }

  fun clickOnApplyButton() = step(
      description = "Нажать на кнопку 'Применить сейчас'"
  ) { mainButton { performClick() } }

  fun clickOnReplaceButton() = step(
      description = "Нажать на кнопку 'Да, заменить'"
  ) { alertReplaceButton { click() } }

  fun closeSelf() = step(
      description = "Закрыть диалог с информацией об акции"
  ) { pressBack() }

  fun verifyButtonText(expected: String) = step(
      description = "Проверить, что текст кнопки '$expected'"
  ) { mainButton { verifyText(expected) } }

  fun verifyChangeOfferAlertIsDisplayed() =
    step(description = "Проверить, что отображается модальное окно замены акции") {
      alertTitle { hasText("Заменить акцию?") }
      alertMessage { hasText("Вы уже применили одну") }
    }
}
