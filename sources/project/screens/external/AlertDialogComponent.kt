package ru.dodopizza.app.screens.external

import android.app.AlertDialog
import io.github.kakaocup.kakao.dialog.KAlertDialog
import ru.dodopizza.app.screens.AbstractComponent

/**
 * Системный компонент подтверждения действия
 */
object AlertDialogComponent : AbstractComponent<AlertDialogComponent>(
    layoutId = null,
    viewClass = AlertDialog::class.java
) {

  private val self: KAlertDialog
    get() = KAlertDialog()

  fun clickOnPositiveButton() = step(
      description = "Нажать на кнопку подтверждения действия"
  ) { self { positiveButton { click() } } }

  fun clickOnNegativeButton() = step(
      description = "Нажать на кнопку отмены действия"
  ) { self { negativeButton { click() } } }

  fun verifyTitle(expected: String) = step(
      description = "Проверить, что текст заголовка '$expected'"
  ) { self { title { hasText(expected) } } }

  fun verifyMessage(expected: String) = step(
      description = "Проверить, что текст сообщения '$expected'"
  ) { self { message { hasText(expected) } } }
}
