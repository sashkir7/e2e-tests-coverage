package ru.dodopizza.app.screens.profile.self

import androidx.test.espresso.matcher.ViewMatchers.withId
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import ru.dodopizza.app.R
import ru.dodopizza.app.kaspresso.actions.childByText
import com.dodopizza.profile.feature.profile.presentation.ProfileFragment
import io.github.kakaocup.kakao.common.views.KView
import ru.dodopizza.app.screens.AbstractScreen
import ru.dodopizza.app.models.enums.PersonalOffer
import ru.dodopizza.app.screens.profile.PersonalOffersComponent
import ru.dodopizza.app.screens.profile.SpecialOfferDialogComponent
import ru.dodopizza.app.screens.profile.self.components.ActiveOrderListComponent
import ru.dodopizza.app.screens.profile.self.components.CollaborationPromoDialogComponent
import ru.dodopizza.app.screens.profile.self.components.OfferDialogComponent
import ru.dodopizza.app.screens.profile.self.components.ProfileMenuComponent
import ru.dodopizza.app.screens.profile.self.components.SupportComponent
import ru.dodopizza.app.screens.profile.settings.SubscriptionNotificationComponent

/**
 * Экран с пользовательскими данными
 */
object ProfileScreen : AbstractScreen<ProfileScreen>(
    layoutId = R.layout.fragment_profile,
    viewClass = ProfileFragment::class.java,
) {

  val profileMenu = ProfileMenuComponent
  val supportComponent = SupportComponent
  val personalOffers = PersonalOffersComponent
  val offerDialog = OfferDialogComponent
  val specialOfferDialog = SpecialOfferDialogComponent
  val collaborationDialog = CollaborationPromoDialogComponent
  val subscriptionNotification = SubscriptionNotificationComponent
  val activeOrderList = ActiveOrderListComponent

  private val self: KView
    get() = bind(R.id.profile_fragment)
  private val supportButton: KButton
    get() = bind(R.id.profile_support)
  private val unauthSupportButton: KButton
    get() = bind(R.id.profile_unauth_support)
  private val settingsButton: KButton
    get() = bind(R.id.profile_customer_settings)
  private val profileExitButton: KButton
    get() = bind(R.id.profile_exit)
  private val notAuthorizedExitButton: KButton
    get() = bind(R.id.profile_unauth_back)

  private val setPhoneButton: KButton
    get() = bind(withId(R.id.unauth_profile_bottom_container)) {
      withId(R.id.profile_enter_phone_btn)
    }
  private val legalDocumentsButton: KTextView
    get() = bind(withId(R.id.unauth_profile_bottom_container)) {
      withId(R.id.profile_legal_doc_unauthorized)
    }
  private val notAuthorizedAppVersion: KTextView
    get() = bind(withId(R.id.unauth_profile_bottom_container)) {
      withId(R.id.profile_version_unauthorized)
    }

  private val missionsRecycler: KRecyclerView
    get() = bind(R.id.profile_loyalty_missions) { itemType { LoyaltyMissionItem(it) } }

  fun verifyIsLoaded() = step(
      description = "Проверить, что экран профиля пользователя успешно загружен"
  ) { self { isDisplayed() } }

  fun clickOnSupportButton() = step(
      description = "Нажать на кнопку 'Поддержка'"
  ) { supportButton { click() } }

  fun clickOnUnauthSupportButton() = step(
      description = "Нажать на кнопку 'Поддержка'"
  ) { unauthSupportButton { click() } }

  fun applyOffer(offer: PersonalOffer) =
    step(description = "Применить акцию '$offer'") {
      personalOffers { openOfferDetails(offer) }
      offerDialog {
        clickOnApplyButton()
        closeSelf()
      }
    }

  fun clickOnSetPhoneButton() = flakyStep(
      description = "Нажать на кнопку 'Указать телефон'",
      expected = { setPhoneButton { doesNotExist() } }
  ) { setPhoneButton { click() } }

  fun verifyUserIsAuthenticated() = step(
      description = "Проверить, что пользователь авторизован"
  ) { settingsButton { isDisplayed() } }

  fun verifyUserIsNotAuthenticated() =
    step(description = "Проверить, что пользователь не авторизован") {
      setPhoneButton { isDisplayed() }
      settingsButton { isGone() }
    }

  fun clickOnSettingsButton() = flakyStep(
      description = "Нажать на кнопку 'Настройки'",
      expected = { settingsButton { doesNotExist() } }
  ) { settingsButton { click() } }

  fun clickOnLegalDocumentsButton() = step(
      description = "Нажать на кнопку 'Правовые документы'"
  ) { legalDocumentsButton { click() } }

  fun clickOnExitButton() = flakyStep(
      description = "Выйти из профиля",
      expectedTimeoutMs = 5_000,
      expected = { settingsButton { doesNotExist() } }
  ) { profileExitButton { click() } }

  fun clickOnNotAuthorizedExitButton() = step(
      description = "Выйти из профиля"
  ) { notAuthorizedExitButton { click() } }

  fun verifyApplicationVersion() =
    step(description = "Проверить, что отображается версия приложения") {
      notAuthorizedAppVersion {
        isDisplayed()
        containsText("Версия")
        containsText("сборка")
      }
    }

  fun verifyActiveOrderIsNotDisplayed() = step(
      description = "Проверить, что виджет активного заказа не отображается"
  ) { activeOrderList { assertIsNotDisplayed() } }

  fun verifyMissionsSize(expected: Int) = step(
      description = "Проверить, что количество миссий '$expected'"
  ) { missionsRecycler { hasSize(expected) } }

  fun mission(
    title: String,
    actions: LoyaltyMissionItem.() -> Unit,
  ) = step("Получить из списка миссий '$title'") {
    missionsRecycler { childByText<LoyaltyMissionItem>(title, actions) }
  }
}
