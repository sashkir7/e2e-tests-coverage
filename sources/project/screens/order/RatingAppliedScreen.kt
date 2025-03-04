package ru.dodopizza.app.screens.order

import ru.dodopizza.app.screens.AbstractScreen
import com.dodopizza.controlling.R
import com.dodopizza.controlling.feature.ratingapplied.presentation.RatingAppliedFragment
import io.github.kakaocup.kakao.text.KTextView

/**
 * Экран успешной отправки оценки заказа
 */
object RatingAppliedScreen : AbstractScreen<RatingAppliedScreen>(
    layoutId = R.layout.fragment_rating_applied,
    viewClass = RatingAppliedFragment::class.java,
) {

  private val thankTitle: KTextView
    get() = bind(R.id.rating_applied_thank_you_title)

  fun verifyIsLoaded() = step(
      description = "Проверить, что страница 'Спасибо' успешно загрузилась"
  ) { thankTitle { isDisplayed() } }

}
