package ru.dodopizza.app.screens.geo.map.pizzeria

import com.dodopizza.geo.R
import com.dodopizza.geo.feature.combinedmap.presentation.PizzeriaCard
import io.github.kakaocup.kakao.text.KTextView
import ru.dodopizza.app.screens.AbstractComponent

/**
 *  Карточка с деталями пиццерии
 */
object PizzeriaCardComponent : AbstractComponent<PizzeriaCardComponent>(
    layoutId = R.layout.pizzeria_info_card,
    viewClass = PizzeriaCard::class.java
) {

  private val status: KTextView
    get() = bind(R.id.status_pizzeria_text)
  private val distanceToUser: KTextView
    get() = bind(R.id.pizzeria_distance)

  fun verifyStatus(expected: String) = step(
      description = "Проверить, что статус пиццерии '$expected'"
  ) { status { hasText(expected) } }

  fun verifyDistanceToUser(containsExpected: String) = step(
      description = "Проверить, что расстояние до пользователя содержит '$containsExpected'"
  ) { distanceToUser { containsText(containsExpected) } }
}
