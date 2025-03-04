package ru.dodopizza.app.screens.loyalty.self

import com.dodopizza.loyalty.menu.LoyaltyFragment
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KTextView
import ru.dodopizza.app.R
import ru.dodopizza.app.kaspresso.actions.childByText
import ru.dodopizza.app.models.enums.LoyaltyCategory
import ru.dodopizza.app.screens.AbstractScreen

/**
 * Экран программы лояльности
 */
object LoyaltyScreen : AbstractScreen<LoyaltyScreen>(
    layoutId = R.layout.fragment_loyalty,
    viewClass = LoyaltyFragment::class.java,
) {

  private val balanceTitle: KTextView
    get() = bind(R.id.loyalty_coin_balance)
  private val coinsExpirations: KView
    get() = bind(R.id.loyalty_coins_expirations_container)

  private val expirationFirstOperationTitle: KTextView
    get() = bind(R.id.loyalty_coins_expiration_first_operation_title)
  private val expirationFirstOperationAmount: KTextView
    get() = bind(R.id.loyalty_coins_expiration_first_operation_amount)
  private val expirationSecondOperationTitle: KTextView
    get() = bind(R.id.loyalty_coins_expiration_second_operation_title)
  private val expirationSecondOperationAmount: KTextView
    get() = bind(R.id.loyalty_coins_expiration_second_operation_amount)

  private val categories: KRecyclerView
    get() = bind(R.id.loyalty_categories_list) { itemType { LoyaltyCategoryItem(it) } }

  private val historyOperations: KRecyclerView
    get() = bind(R.id.loyalty_history_operations_list) {
      itemType { LoyaltyHistoryOperationItem(it) }
    }

  fun verifyIsLoaded() = step(
      description = "Проверить, что экран лояльности успешно загружен"
  ) { categories { isDisplayed() } }

  fun clickOnCategory(category: LoyaltyCategory) = flakyStep(
      description = "Нажать на категорию '$category'",
      expected = { categories { doesNotExist() } }
  ) { categories { childByText<LoyaltyCategoryItem>(category).click() } }

  fun verifyBalanceTitle(expected: String) = step(
      description = "Проверить, что баланс '$expected'"
  ) { balanceTitle { hasText(expected) } }

  fun verifyCoinsExpirationsBlockIsDisplayed() = step(
      description = "Проверить, что отображается блок 'Следующие сгорания'"
  ) { coinsExpirations { isDisplayed() } }

  fun verifyExpirationFirstTitle(expected: String) = step(
      description = "Проверить, что заголовок первой операции '$expected'"
  ) { expirationFirstOperationTitle { hasText(expected) } }

  fun verifyExpirationFirstAmount(expected: String) = step(
      description = "Проверить, что сумма первой операции '$expected'"
  ) { expirationFirstOperationAmount { hasText(expected) } }

  fun verifyExpirationSecondTitle(expected: String) = step(
      description = "Проверить, что заголовок второй операции '$expected'"
  ) { expirationSecondOperationTitle { hasText(expected) } }

  fun verifyExpirationSecondAmount(expected: String) = step(
      description = "Проверить, что сумма второй операции '$expected'"
  ) { expirationSecondOperationAmount { hasText(expected) } }

  fun historyOperation(
    position: Int,
    actions: LoyaltyHistoryOperationItem.() -> Unit,
  ) = step("Получить операцию в истории программы лояльности на позиции '$position'") {
    historyOperations { childAt<LoyaltyHistoryOperationItem>(position, actions) }
  }
}
