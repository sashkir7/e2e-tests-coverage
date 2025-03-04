package ru.dodopizza.app.screens

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.kaspersky.kaspresso.testcases.models.info.StepInfo
import io.github.kakaocup.compose.node.builder.ViewBuilder
import io.github.kakaocup.compose.node.element.ComposeScreen
import ru.dodopizza.app.kaspresso.actions.longSwipeUp
import ru.dodopizza.app.models.ContextStorage

abstract class BaseComposeScreen<T : BaseComposeScreen<T>>(
  viewBuilderAction: ViewBuilder.() -> Unit,
) : ComposeScreen<T>(
    semanticsProvider = composeTestRule,
    viewBuilderAction = viewBuilderAction
) {

  companion object {
    val composeTestRule: ComposeTestRule = createEmptyComposeRule()
  }

  private val context: TestContext<Unit> by lazy { ContextStorage.get() }

  protected fun step(
    description: String,
    actions: (StepInfo) -> Unit,
  ) = context.step(description, actions)

  protected fun <T> continuously(
    timeoutMs: Long? = null,
    intervalMs: Long? = null,
    failureMessage: String? = null,
    action: () -> T,
  ) = context.continuously(timeoutMs, intervalMs, failureMessage, action)

  protected fun longSwipeUp() = context.longSwipeUp()

}
