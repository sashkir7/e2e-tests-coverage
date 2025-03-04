package ru.dodopizza.app.screens.login

import com.dodopizza.auth.features.phoneprefixlist.presentation.PhonePrefixListFragment
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import ru.dodopizza.app.R
import ru.dodopizza.app.kaspresso.actions.childByText
import ru.dodopizza.app.kaspresso.assertions.isNotEmpty
import ru.dodopizza.app.models.enums.Country
import ru.dodopizza.app.models.enums.Country.RUSSIA
import ru.dodopizza.app.screens.AbstractScreen

/**
 *  Экран для выбора префикса страны (авторизация)
 */
object PhonePrefixListScreen : AbstractScreen<PhonePrefixListScreen>(
    layoutId = R.layout.fragment_phone_prefix_list,
    viewClass = PhonePrefixListFragment::class.java,
) {

  val DEFAULT_COUNTRY_PREFIX = RUSSIA

  private val phonePrefixListRecycler: KRecyclerView
    get() = bind(R.id.phone_prefix_list) { itemType { KRecyclerItem<Any>(it) } }

  fun setCountryPrefix(country: Country) =
    step(description = "Выбрать префикс страны '${country.nameRu}'") {
      phonePrefixListRecycler {
        isNotEmpty()
        childByText<KRecyclerItem<*>>(country.nameRu) { click() }
      }
    }
}
