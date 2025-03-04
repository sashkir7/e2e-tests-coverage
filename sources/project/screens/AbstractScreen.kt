package ru.dodopizza.app.screens

/**
 * Базовый класс для экранов приложения пиццы
 * Взаимодействие осуществляется посредством Espresso
 */
@Suppress("UnnecessaryAbstractClass")
abstract class AbstractScreen<T : AbstractScreen<T>>(
  override val layoutId: Int?,
  override val viewClass: Class<*>?,
) : KBaseScreen<T>(
    layoutId = layoutId,
    viewClass = viewClass
)
