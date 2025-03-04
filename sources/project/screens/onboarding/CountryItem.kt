package ru.dodopizza.app.screens.onboarding

import android.view.View
import com.dodopizza.onboarding.R
import com.dodopizza.onboarding.feature.selectcountry.presentation.CountryVH
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import ru.dodopizza.app.screens.AbstractRecyclerItem

/**
 * Карточка конкретной страны
 */
class CountryItem(
  parent: Matcher<View>,
) : AbstractRecyclerItem<CountryItem>(
    parent = parent,
    layoutId = R.layout.item_country,
    viewHolderClass = CountryVH::class.java
) {

  private val name: KTextView
    get() = bind(parent) { withId(R.id.country_name) }

  fun verifyName(expected: String) = step(
      description = "Проверить, что название страны '$expected'"
  ) { name { hasText(expected) } }
}
