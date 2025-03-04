package ru.dodopizza.app.screens.email

import com.dodopizza.order.feature.checkout.emailforchecks.presentation.EmailForChecksFragment
import io.github.kakaocup.kakao.text.KTextView
import ru.dodopizza.app.R
import ru.dodopizza.app.screens.AbstractScreen

object EmailForChecksScreen : AbstractScreen<EmailForChecksScreen>(
    layoutId = R.layout.fragment_email_for_checks,
    viewClass = EmailForChecksFragment::class.java,
) {

  private val title: KTextView
    get() = bind(R.id.title_label)

  fun verifyEmailTitleIsDisplayed() = step(
      description = "Проверить, что отображается текст 'Почта для чеков'"
  ) { title { hasText("Почта для чеков") } }
}
