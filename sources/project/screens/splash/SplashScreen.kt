package ru.dodopizza.app.screens.splash

import com.dodopizza.core.R
import com.dodopizza.core.feature.splash.presentation.SplashFragment
import io.github.kakaocup.kakao.common.views.KView
import ru.dodopizza.app.screens.AbstractScreen

/**
 * Экран, который отображается во время загрузки приложения
 */
object SplashScreen : AbstractScreen<SplashScreen>(
    layoutId = R.layout.activity_splash,
    viewClass = SplashFragment::class.java
) {

  private val self: KView
    get() = bind(R.id.splash_fragment)

  fun verifyIsNotLoaded() = step(
      description = "Проверить, что splash-экран не отображается"
  ) { self { doesNotExist() } }
}
