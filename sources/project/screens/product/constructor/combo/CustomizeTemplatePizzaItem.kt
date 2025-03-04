package ru.dodopizza.app.screens.product.constructor.combo

import android.view.View
import com.dodopizza.order.feature.product.card.combo.constructor.presentation.ProductV5ViewHolder
import com.google.android.material.chip.Chip
import io.github.kakaocup.kakao.check.KCheckBox
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import ru.dodopizza.app.R
import ru.dodopizza.app.kaspresso.actions.childByText
import ru.dodopizza.app.kaspresso.matchers.withAnyId
import ru.dodopizza.app.models.enums.Topping
import ru.dodopizza.app.screens.AbstractRecyclerItem
import ru.dodopizza.app.screens.foodmenu.components.ProductInfoPopupComponent

/**
 * Карточка конструктора комбо по шаблонам (RecyclerViewItem)
 */
class CustomizeTemplatePizzaItem(
  parent: Matcher<View>,
) : AbstractRecyclerItem<CustomizeTemplatePizzaItem>(
    parent = parent,
    layoutId = R.layout.item_customize_template_product_v5,
    viewHolderClass = ProductV5ViewHolder::class.java,
) {

  val productInfo = ProductInfoPopupComponent
  val pizzaVariation = PizzaVariationComponent(parent)

  private val extraPrice: KTextView
    get() = bind(parent) { withId(R.id.combo_template_extra_price) }
  private val ingredientsTitle: KTextView
    get() = bind(parent) { withId(R.id.add_ingredients_title) }
  private val saveButton: KButton
    get() = bind(parent) { withId(R.id.save_receipt_button) }
  private val customizeReceiptButton: KButton
    get() = bind(parent) { withId(R.id.combo_customize_receipt) }
  private val toppingsSection: KView
    get() = bind(parent) { withId(R.id.add_toppings_container) }
  private val info: KView
    get() = bind(parent) { withId(R.id.combo_template_slot_foodvalue_info) }
  private val selectButton: KButton
    get() = bind(parent) { withAnyId(R.id.combo_template_select_product_button) }

  private val products: KRecyclerView
    get() = bind(
        parent = parent,
        builder = { withId(R.id.products_to_add_list) },
        itemTypeBuilder = { itemType { CustomizeToppingItem(it) } }
    )

  fun openInfo() = step(description = "Открыть детали продукта") { info { click() } }

  fun clickOnSelectButton() = step(
      description = "Нажать на кнопку 'Выбрать'"
  ) { selectButton { click() } }

  fun clickOnSaveButton() = step(
      description = "Нажать на кнопку 'Сохранить'"
  ) { saveButton { click() } }

  fun addIngredient(ingredient: Topping) = step(
      description = "Добавить ингредиент '$ingredient'"
  ) { ingredientAction(ingredient) { click() } }

  fun verifyProductIsUnavailable() = step(description = "Проверить, что продукт недоступен") {
    selectButton {
      isDisabled()
      hasText("Раскупили")
    }
  }

  fun verifyCustomizeButtonIsNotVisible() = step(
      description = "Проверить, что кнопка 'Изменить составк' скрыта"
  ) { customizeReceiptButton { isGone() } }

  fun verifyExtraPrice(expected: String) = step(
      description = "Проверить, что дополнительная цена '$expected'"
  ) { extraPrice { hasText(expected) } }

  fun verifyExtraPriceDoesNotExist() = step(
      description = "Проверить, что дополнительная цена не отображается"
  ) { extraPrice { doesNotExist() } }

  fun verifyIngredientIsAdded(expected: Topping) = step(
      description = "Проверить, что добавлен ингредиент '$expected'"
  ) { ingredientAction(expected) { isChecked() } }

  fun verifyIngredientIsRemoved(expected: Topping) = step(
      description = "Проверить, что удален ингредиент '$expected'"
  ) { expected.chip { isNotChecked() } }

  fun verifyIngredientDoesNotExist(expected: Topping) = flakySafely(
      description = "Проверить, что топпинг '$expected' отсутствует",
      timeoutMs = 2_500
  ) { ingredientAction(expected) { doesNotExist() } }

  fun verifyToppingsSectionDoesNotExist() = step(
      description = "Проверить, что контейнер с добавлением топпингов не отображается"
  ) { toppingsSection { doesNotExist() } }

  fun verifyIngredientTitleIsNotVisible() = step(
      description = "Проверить, что заголовок 'Добавить по вкусу' скрыт"
  ) { ingredientsTitle { isGone() } }

  fun changeIngredients(
    remove: List<Topping> = listOf(),
    add: List<Topping> = listOf(),
  ) = step("Изменить состав ингредиентов") {
    clickOnCustomizeReceiptButton()
    remove.forEach { removeIngredient(it) }
    add.forEach { addIngredient(it) }
  }

  fun clickOnCustomizeReceiptButton() = step(
      description = "Нажать на кнопку 'Изменить состав'"
  ) { customizeReceiptButton { click() } }

  private fun removeIngredient(ingredient: Topping) = step(
      description = "Удалить ингредиент '$ingredient'"
  ) { ingredient.chip { click() } }

  private fun ingredientAction(
    ingredient: Topping,
    action: CustomizeToppingItem.() -> Unit,
  ) = step(description = "Выполнить действие с ингредиентом '$ingredient'") {
    products { childByText<CustomizeToppingItem>(ingredient, action) }
  }
}

private val Topping.chip: KCheckBox
  get() = KCheckBox {
    withText(this@chip.toString())
    isAssignableFrom(Chip::class.java)
  }
