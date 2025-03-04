package ru.dodopizza.app.screens

import com.kaspersky.components.kautomator.screen.UiScreen
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.kaspersky.kaspresso.testcases.models.info.StepInfo
import ru.dodopizza.app.models.ContextStorage

/**
 * Базовый класс для сторонних экранов, которые не относятся к приложению пиццы
 * Взаимодействие осуществляется посредством UI Automator
 */
@Suppress("UnnecessaryAbstractClass")
abstract class BaseUiScreen<T : BaseUiScreen<T>>(
  override val packageName: String = "",
) : UiScreen<T>() {

  private val context: TestContext<Unit> by lazy { ContextStorage.get() }

  protected fun step(
    description: String,
    actions: (StepInfo) -> Unit,
  ) = context.step(description, actions)

}
