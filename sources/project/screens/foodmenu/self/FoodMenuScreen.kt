package ru.dodopizza.app.screens.foodmenu.self

import com.dodopizza.order.feature.foodmenu.presentation.FoodMenuFragment
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.tabs.KTabLayout
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import ru.dodopizza.app.R
import ru.dodopizza.app.kaspresso.actions.childByText
import ru.dodopizza.app.kaspresso.actions.manualChildWith
import ru.dodopizza.app.kaspresso.actions.forceScrollTo
import ru.dodopizza.app.kaspresso.actions.isClickableAndDoClick
import ru.dodopizza.app.kaspresso.assertions.isNotEmpty
import ru.dodopizza.app.kaspresso.matchers.withProduct
import ru.dodopizza.app.models.enums.DeliveryAddress
import ru.dodopizza.app.models.enums.MenuCollection
import ru.dodopizza.app.models.enums.MetaProduct
import ru.dodopizza.app.models.enums.Pizzeria
import ru.dodopizza.app.models.enums.ProductCategory
import ru.dodopizza.app.models.enums.ProductCategory.PIZZA_HALVES
import ru.dodopizza.app.screens.AbstractScreen
import ru.dodopizza.app.screens.foodmenu.components.ActiveOrderWidgetComponent
import ru.dodopizza.app.screens.foodmenu.components.AddressContainerFoodMenuComponent
import ru.dodopizza.app.screens.foodmenu.components.FeeDeliveryInfoComponent
import ru.dodopizza.app.screens.foodmenu.components.InAppNotificationComponent
import ru.dodopizza.app.screens.foodmenu.components.SpecialOfferDialogComponent
import ru.dodopizza.app.screens.profile.self.components.OfferDialogComponent

/**
 * Экран меню
 */
object FoodMenuScreen : AbstractScreen<FoodMenuScreen>(
    layoutId = R.layout.fragment_food_menu,
    viewClass = FoodMenuFragment::class.java,
) {

  val offerDialog = OfferDialogComponent
  val activeOrder = ActiveOrderWidgetComponent
  val feeDeliveryInfo = FeeDeliveryInfoComponent
  val inAppNotification = InAppNotificationComponent
  val specialOfferDialog = SpecialOfferDialogComponent
  val addressContainer = AddressContainerFoodMenuComponent

  private val self: KView
    get() = bind(R.id.food_menu_container)
  private val categories: KTabLayout
    get() = bind(R.id.food_menu_categories)

  private val cartButton: KButton
    get() = bind(R.id.food_menu_button_cart)
  private val menuCategoriesSearchContainer: KTabLayout
    get() = bind(R.id.food_menu_categories_search_container)

  private val avatarTitle: KTextView
    get() = bind<KTextView>(R.id.avatar_tooltip_title)
        .also { it.inRoot { isPlatformPopup() } }

  private val products: KRecyclerView
    get() = bind(R.id.food_menu_products) {
      itemType { MenuRegularItem(it) }
      itemType { MenuPizzaHalvesItem(it) }
      itemType { HeroMenuItem(it) }
    }

  private val favoriteProducts: KRecyclerView
    get() = bind(R.id.food_menu_promotion_products) { itemType { MenuPromotionItem(it) } }

  private val bonusActions: KRecyclerView
    get() = bind(R.id.food_menu_special_offer) { itemType { SpecialOfferItem(it) } }

  fun verifyIsLoaded() = step(
      description = "Проверить, что экран меню успешно загружен"
  ) { self { isDisplayed() } }

  fun verifyMenuIsLoaded() = flakySafely(
      description = "Проверить, что меню успешно загружено",
      timeoutMs = 25_000
  ) { products { isNotEmpty() } }

  fun verifyAddress(expected: String) = addressContainer { verifyAddress(expected) }
  fun verifyAddress(expected: Pizzeria) = addressContainer { verifyAddress(expected) }
  fun verifyAddress(expected: DeliveryAddress) = addressContainer { verifyAddress(expected) }

  fun clickOnCartButton() = step(
      description = "Нажать на кнопку 'Корзина'"
  ) { cartButton { isClickableAndDoClick() } }

  fun openProduct(product: MetaProduct) = step(
      description = "Открыть карточку продукта '$product'"
  ) {
    products { forceScrollTo() }
    when (product.category) {
      PIZZA_HALVES -> menuPizzaHalvesSlot(product) { open() }
      else -> menuRegularSlot(product) { open() }
    }
  }

  fun verifyYourDiscountTabIsDisplayed() = step(
      description = "Проверить, что отображается категория 'Ваши скидки'"
  ) { menuCategoriesSearchContainer { hasDescendant { withText("Ваши скидки") } } }

  fun verifyYourDiscountTabIsNotDisplayed() = step(
      description = "Проверить, что категория 'Ваши скидки' не отображается"
  ) { menuCategoriesSearchContainer { hasNotDescendant { withText("Ваши скидки") } } }

  fun selectCategory(category: ProductCategory) = step(
      description = "Выбрать категорию '$category'"
  ) { categories { selectTabByText(category.toString()) } }

  fun selectCollection(collection: MenuCollection) =
    step(description = "Выбрать подборку '$collection'") {
      KTextView {
        withId(R.id.menu_picks_button_text)
        withText(collection.toString())
      }.click()
    }

  fun verifyCategoryIsSelected(category: ProductCategory) = step(
      description = "Проверить, что выбрана категория '$category'"
  ) { categories { hasSelectedText(category.toString()) } }

  fun verifyCategoriesSize(expected: Int) = step(
      description = "Проверить, что кол-во категорий '$expected'"
  ) { categories { hasTabCount(expected) } }

  fun verifyCategories(categories: List<ProductCategory>) =
    step("Проверить меню категорий") {
      verifyCategoriesSize(categories.size)
      categories.forEachIndexed { index, category ->
        step("Проверить категорию '$category' под индексом '$index'") {
          categories { hasText(category.toString(), index) }
        }
      }
    }

  fun verifyHasHeroDesign(expected: MetaProduct) = step(
      description = "Проверить, что продукт '$expected' имеет Hero-дизайн"
  ) { menuHeroSlot(expected) { verifyIsLoaded() } }

  fun verifyAvatarTitle(expected: String) = step(
      description = "Проверить, что на аватарке отображается текст '$expected'"
  ) { avatarTitle { hasText(expected) } }

  fun bannerSlot(
    position: Int,
    actions: SpecialOfferItem.() -> Unit,
  ) = step("Получить карточку спец. предложения на позиции '$position'") {
    bonusActions { childAt<SpecialOfferItem>(position, actions) }
  }

  fun menuRegularSlot(
    product: MetaProduct,
    actions: MenuRegularItem.() -> Unit,
  ) = flakySafely(description = "Получить карточку продукта '$product' из меню") {
    products.manualChildWith<MenuRegularItem> {
      withId(R.id.menu_item_product_title)
      withProduct(product)
    }.actions()
  }

  fun menuPizzaHalvesSlot(
    product: MetaProduct,
    actions: MenuPizzaHalvesItem.() -> Unit,
  ) = step("Получить карточку продукта '$product' из меню") {
    products { childByText<MenuPizzaHalvesItem>(product, actions) }
  }

  fun menuHeroSlot(
    product: MetaProduct,
    actions: HeroMenuItem.() -> Unit,
  ) = step("Получить карточку hero-продукта '$product' из меню") {
    products { childByText<HeroMenuItem>(product, actions) }
  }

  fun favoriteSlot(
    product: MetaProduct,
    actions: MenuPromotionItem.() -> Unit,
  ) = step("Получить карточку продукта '$product' из блока 'Вам понравится'") {
    favoriteProducts { childByText<MenuPromotionItem>(product, actions) }
  }
}
