package ru.dodopizza.app.screens.profile.settings

import com.dodopizza.profile.feature.profilesettings.presentation.ProfileSettingsFragment
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.text.KButton
import ru.dodopizza.app.R
import ru.dodopizza.app.screens.AbstractComponent

/**
 * Компонент подтверждения удаления профиля
 */
object ConfirmDeletionProfileComponent : AbstractComponent<ConfirmDeletionProfileComponent>(
    layoutId = R.layout.fragment_profile_settings,
    viewClass = ProfileSettingsFragment::class.java
) {

  private val self: KView
    get() = bind(R.id.profile_settings_deletion_status_container)
  private val cancelButton: KButton
    get() = bind(R.id.profile_settings_cancel_deletion_button)

  fun verifyIsLoaded() = step(
      description = "Проверить, что баннер подтверждения удаления аккаунта успешно загружен"
  ) { self { isDisplayed() } }

  fun verifyIsNotLoaded() = step(
      description = "Проверить, что баннер подтверждения удаления аккаунта не загружен"
  ) { self { isNotDisplayed() } }

  fun clickOnCancelButton() = step(
      description = "На баннере подтверждения удаления аккаунта нажать на кнопку 'Отменить'"
  ) { cancelButton { click() } }
}
