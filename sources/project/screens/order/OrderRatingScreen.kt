package ru.dodopizza.app.screens.order

import com.dodopizza.controlling.feature.orderrating.presentation.OrderRatingFragment
import ru.dodopizza.app.screens.AbstractScreen
import com.dodopizza.controlling.R
import com.google.android.material.chip.Chip
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

/**
 * Экран оценки заказа
 */
object OrderRatingScreen : AbstractScreen<OrderRatingScreen>(
    layoutId = R.layout.fragment_order_rating,
    viewClass = OrderRatingFragment::class.java,
) {

  private val comment: KTextView
    get() = bind(R.id.order_rating_comment)
  private val commentInput: KEditText
    get() = bind(R.id.rating_comment_input)
  private val sendButton: KButton
    get() = bind(R.id.order_rating_send_order_rating)
  private val detailsContainer: KView
    get() = bind(R.id.order_rating_details_container)

  fun verifyIsLoaded() = step(
      description = "Проверить, что экран оценки заказа успешно загружен"
  ) { detailsContainer { isDisplayed() } }

  fun addComment(value: String) =
    step(description = "Указать комментарий '$value'") {
      comment { click() }
      commentInput {
        typeText(value)
        pressImeAction()
      }
    }

  fun clickOnTag(tag: String) = step(
      description = "Нажать на тег '$tag'"
  ) { chipWithText(tag).click() }

  fun clickOnSendButton() = step(
      description = "Нажать на кнопку 'Отправить'"
  ) { sendButton { click() } }

  private fun chipWithText(tag: String) = KView {
    withText(tag)
    isAssignableFrom(Chip::class.java)
  }
}
