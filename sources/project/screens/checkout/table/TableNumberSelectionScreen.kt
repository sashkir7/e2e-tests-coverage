package ru.dodopizza.app.screens.checkout.table

import com.dodopizza.order.feature.deliverytotable.presentation.TableNumberSelectionFragment
import io.github.kakaocup.kakao.edit.KEditText
import ru.dodopizza.app.R
import ru.dodopizza.app.screens.AbstractScreen

/**
 * Экран ввода номера столика
 */
object TableNumberSelectionScreen : AbstractScreen<TableNumberSelectionScreen>(
    layoutId = R.layout.fragment_table_number,
    viewClass = TableNumberSelectionFragment::class.java,
) {

  private val tableNumber: KEditText
    get() = bind(R.id.table_number_view)

  fun verifyIsNotLoaded() = step(
      description = "Проверить, что экран ввода номера столика не был загружен"
  ) { tableNumber { doesNotExist() } }

  fun setTableNumber(number: Int) = flakyStep(
      description = "Указать номер столика '$number'",
      expected = { tableNumber { doesNotExist() } }
  ) {
    tableNumber {
      typeText(number.toString())
      pressImeAction()
    }
  }
}
