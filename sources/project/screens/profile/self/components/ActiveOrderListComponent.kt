package ru.dodopizza.app.screens.profile.self.components

import com.dodopizza.profile.feature.compose.components.ActiveOrderListTag
import ru.dodopizza.app.screens.BaseComposeScreen

object ActiveOrderListComponent : BaseComposeScreen<ActiveOrderListComponent>(
    viewBuilderAction = { hasTestTag(ActiveOrderListTag.SELF) }
)
