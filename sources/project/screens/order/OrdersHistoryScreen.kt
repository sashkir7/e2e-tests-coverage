package ru.dodopizza.app.screens.order

import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import com.dodopizza.modifiers.LazyListItemPosition
import com.dodopizza.orderhistory.feature.orderhistory.presentation.orderhistory.OrderHistoryScreen
import io.github.kakaocup.compose.node.element.KNode
import io.github.kakaocup.compose.node.element.lazylist.KLazyListItemNode
import io.github.kakaocup.compose.node.element.lazylist.KLazyListNode
import ru.dodopizza.app.screens.BaseComposeScreen

object OrdersHistoryScreen : BaseComposeScreen<OrdersHistoryScreen>(
    viewBuilderAction = { hasTestTag(OrderHistoryScreen.SCREEN_TAG) }
) {

  private val contentNode: KNode = child { hasTestTag(OrderHistoryScreen.CONTENT_TAG) }

  private val ordersList = KLazyListNode(
      semanticsProvider = composeTestRule,
      viewBuilderAction = { hasTestTag(OrderHistoryScreen.ORDERS_LIST_TAG) },
      itemTypeBuilder = { itemType(::OrderHistoryItem) },
      positionMatcher = { position -> SemanticsMatcher.expectValue(LazyListItemPosition, position) }
  )

  @OptIn(ExperimentalTestApi::class)
  fun clickOnOrder(
    position: Int,
  ) = step("Нажать на детали заказа по позиции '$position'") {
    ordersList.childAt<OrderHistoryItem>(position) { performClick() }
  }

  fun clickOnFirstOrder() = clickOnOrder(position = 0)

  fun verifyIsLoaded() = step(
      description = "Проверить, что экран истории заказов успешно загружен"
  ) { contentNode { assertIsDisplayed() } }
}

private class OrderHistoryItem(
  semanticsNode: SemanticsNode,
  semanticProvider: SemanticsNodeInteractionsProvider,
) : KLazyListItemNode<OrderHistoryItem>(semanticsNode, semanticProvider)
