package ru.dodopizza.app.screens.foodmenu.components

import com.dodopizza.order.feature.widgets.inappnotification.presentation.InAppNotification
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.text.KTextView
import ru.dodopizza.app.R
import ru.dodopizza.app.screens.AbstractComponent

/**
 * Компонент отображения нотификаций на экране меню
 */
object InAppNotificationComponent : AbstractComponent<InAppNotificationComponent>(
    layoutId = R.layout.main_screen_notification_item,
    viewClass = InAppNotification::class.java
) {

  private val self: KView
    get() = bind(R.id.in_app_notification_card)
  private val icon: KImageView
    get() = bind(R.id.in_app_notification_icon)
  private val title: KTextView
    get() = bind(R.id.in_app_notification_title)

  fun clickOnSelf() = step(
      description = "Нажать на компонент нотификации"
  ) { self { click() } }

  fun verifyTitle(expected: String) = step(
      description = "Проверить, что отображается заголовок нотификации '$expected'"
  ) { title { hasText(expected) } }

  fun verifyIsLoaded() = step(
      description = "Проверить, что компонент нотификации успешно загружен"
  ) { self { isDisplayed() } }

  fun verifyIsNotLoaded() = step(
      description = "Проверить, что компонент нотификации не был загружен"
  ) { self { isGone() } }

  fun verifyIconIsDisplayed() = step(
      description = "Проверить, что отображается иконка нотификации"
  ) { icon { isDisplayed() } }
}
