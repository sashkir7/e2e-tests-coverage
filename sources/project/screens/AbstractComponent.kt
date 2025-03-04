package ru.dodopizza.app.screens

/**
 * Базовый класс для компонентов экранов приложения пиццы
 * Взаимодействие осуществляется посредством Espresso
 */
@Suppress("UnnecessaryAbstractClass")
abstract class AbstractComponent<T : AbstractComponent<T>>(
  override val layoutId: Int?,
  override val viewClass: Class<*>?,
) : KBaseScreen<T>(
    layoutId = layoutId,
    viewClass = viewClass
)
