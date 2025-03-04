package ru.dodopizza.app.screens.product.card.regular.components

import com.dodopizza.order.feature.product.card.presentation.product.compose.contents.RemoveIngredientsTags
import io.github.kakaocup.compose.node.element.KNode
import ru.dodopizza.app.models.enums.Topping
import ru.dodopizza.app.screens.BaseComposeScreen

object RemoveIngredientsComponent : BaseComposeScreen<RemoveIngredientsComponent>(
    viewBuilderAction = { hasTestTag(RemoveIngredientsTags.SELF) }
) {

  fun clickOnTopping(topping: Topping) = step(
      description = "Удалить топпинг '$topping'"
  ) { tab(topping).performClick() }

  private fun tab(topping: Topping): KNode = child {
    useUnmergedTree = true
    hasText(topping.title)
  }
}
