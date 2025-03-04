package ru.dodopizza.app.screens.main

import com.dodopizza.order.feature.mainscreen.presentation.MainScreenFragment
import ru.dodopizza.app.R
import ru.dodopizza.app.screens.AbstractScreen
import ru.dodopizza.app.screens.common.ProfileAvatarComponent

/**
 * Главный экран приложения, на котором отображается меню, профиль и тд
 */
object MainScreen : AbstractScreen<MainScreen>(
    layoutId = R.layout.fragment_main,
    viewClass = MainScreenFragment::class.java,
) {

  val profileAvatar = ProfileAvatarComponent

  fun goToProfile() = flakyStep(
      description = "Перейти в профиль пользователя",
      expected = { profileAvatar { assertDoesNotExist() } }
  ) { profileAvatar { clickOnSelf() } }
}
