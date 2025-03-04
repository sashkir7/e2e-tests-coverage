package ru.dodopizza.app.screens.profile.settings

import com.dodopizza.profile.feature.profilesettings.presentation.ProfileSettingsFragment
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.text.KButton
import ru.dodopizza.app.R
import ru.dodopizza.app.screens.AbstractComponent

/**
 * Компонент блока подписки
 */
object SubscriptionNotificationComponent : AbstractComponent<SubscriptionNotificationComponent>(
    layoutId = R.layout.fragment_profile_settings,
    viewClass = ProfileSettingsFragment::class.java
) {

  private val self: KView
    get() = bind(R.id.profile_subscription)
  private val positiveButton: KButton
    get() = bind(R.id.subscription_positive_button)
  private val negativeButton: KButton
    get() = bind(R.id.subscription_negative_button)

  fun verifyIsLoaded() = step(
      description = "Проверить, что элемент подписки успешно загружен"
  ) { self { isDisplayed() } }

  fun verifyIsNotLoaded() = step(
      description = "Проверить, что элемент подписки не загружен"
  ) { self { isNotDisplayed() } }

  fun clickOnPositiveButton() = flakyStep(
      description = "Нажать на кнопку 'Конечно'",
      expected = { self { isGone() } }
  ) { positiveButton { click() } }

  fun clickOnNegativeButton() = flakyStep(
      description = "Нажать на кнопку 'Не интересно'",
      expected = { self { isGone() } }
  ) { negativeButton { click() } }
}
