package ru.dodopizza.app.screens.foryou

import com.dodopizza.order.feature.foryou.presentation.compose.recommendations.ForYouRecommendationsMenuTags
import io.github.kakaocup.compose.node.element.KNode
import ru.dodopizza.app.screens.BaseComposeScreen

object ForYouRecommendationsScreen : BaseComposeScreen<ForYouRecommendationsScreen>(
    viewBuilderAction = { hasTestTag(ForYouRecommendationsMenuTags.SELF) }
) {

  private val title: KNode = child { hasTestTag(ForYouRecommendationsMenuTags.TITLE) }

  fun verifyIsLoaded() = step(
      description = "Проверить, что экран 'Подобрали для вас' успешно загружен"
  ) { assertIsDisplayed() }

  fun verifyTitle(expected: String) = step(
      description = "Проверить, что отображается заголовок '$expected'"
  ) { title { assertTextEquals(expected) } }

  fun verifyIsNotDisplayed() = step(
      description = "Проверить, что экран 'Подобрали для вас' не отображается"
  ) { assertDoesNotExist() }
}
