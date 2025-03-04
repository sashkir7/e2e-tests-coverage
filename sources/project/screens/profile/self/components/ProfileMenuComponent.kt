package ru.dodopizza.app.screens.profile.self.components

import android.view.View
import com.dodopizza.profile.feature.profile.presentation.adapter.widget.PersonalWidgetViewHolder
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import ru.dodopizza.app.R
import ru.dodopizza.app.screens.AbstractComponent

object ProfileMenuComponent : AbstractComponent<ProfileMenuComponent>(
    layoutId = R.layout.item_personal_widget,
    viewClass = PersonalWidgetViewHolder::class.java,
) {

  private val loyaltyTitle: KTextView
    get() = bind(R.id.loyalty_widget_alert_title)
  private val menu: KRecyclerView
    get() = bind(R.id.new_profile_customer_menu_container) { itemType(::ProfileMenuItem) }

  fun verifyLoyaltyTitle(expected: String) = step(
      description = "Проверить, что заголовок уведомления программы лояльности '$expected'"
  ) { loyaltyTitle { hasText(expected) } }

  fun clickOnSection(
    section: ProfileMenuSection,
  ) = flakyStep(
      description = "Нажать на секцию '$section'",
      expected = { menu { doesNotExist() } }
  ) { menu { childAt<ProfileMenuItem>(section.positionOnScreen) { click() } } }
}

enum class ProfileMenuSection(
  private val title: String,
  val positionOnScreen: Int,
) {
  DODOCOINS(title = "Додокоины", positionOnScreen = 0),
  HISTORY(title = "Мои заказы", positionOnScreen = 1),
  ADDRESSES(title = "Адреса доставки", positionOnScreen = 2),
  ;

  override fun toString() = title
}

private class ProfileMenuItem(
  parent: Matcher<View>,
) : KRecyclerItem<ProfileMenuItem>(parent)
