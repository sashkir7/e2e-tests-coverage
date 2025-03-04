package ru.dodopizza.app.screens.checkout.orderType

enum class OrderType(
  val positionOnScreen: Int,
  private val description: String,
) {
  DINE_IN(0, "Выдать на подносе"),
  TAKEAWAY(1, "Упаковать с собой"),
  TABLE_DELIVERY(2, "Принести к столику"),
  ;

  override fun toString() = description
}
