package ru.dodopizza.app.screens.onboarding

import com.dodopizza.onboarding.R
import com.dodopizza.onboarding.feature.selectcountry.presentation.SelectCountryFragment
import io.github.kakaocup.kakao.recycler.KRecyclerView
import ru.dodopizza.app.models.enums.Country
import ru.dodopizza.app.screens.AbstractScreen

/**
 * Экран выбора страны
 */
object SelectCountryScreen : AbstractScreen<SelectCountryScreen>(
    layoutId = R.layout.fragment_select_country,
    viewClass = SelectCountryFragment::class.java
) {

  private val countriesRecycler: KRecyclerView
    get() = bind(R.id.country_list) { itemType { CountryItem(it) } }

  fun verifyCountries(
    expectedCountries: List<Country>,
  ) = step("Проверить список доступных стран") {
    verifyCountriesSize(expectedCountries.size)
    expectedCountries.sortedBy { it.nameRu }
        .forEachIndexed { index, country ->
          verifyCountryItemByIndex(index, country)
        }
  }

  private fun verifyCountriesSize(expected: Int) = step(
      description = "Проверить, что количество доступных стран '$expected'"
  ) { countriesRecycler { hasSize(expected) } }

  private fun verifyCountryItemByIndex(
    index: Int,
    expected: Country,
  ) = step(description = "Проверить наличие страны '$expected' под индексом '$index'") {
    countriesRecycler { childAt<CountryItem>(index) { verifyName(expected.nameRu) } }
  }
}
