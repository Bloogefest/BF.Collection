/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.bloogefest.collection;

import com.bloogefest.annotation.analysis.Contract;
import com.bloogefest.annotation.analysis.NotNull;
import com.bloogefest.annotation.analysis.Range;
import com.bloogefest.collection.iteration.iterator.ImmutableIterator;
import com.bloogefest.common.function.Handler;
import com.bloogefest.common.function.Predicate;
import com.bloogefest.common.validation.NullException;
import com.bloogefest.common.validation.Validator;

/**
 * Неизменяемая коллекция.
 *
 * @param <T> тип элемента.
 *
 * @since 1.0.0-RC1
 */
public interface ImmutableCollection<T> {

    /**
     * Итерирует переданный обработчик по текущей коллекции.
     *
     * @param handler обработчик.
     *
     * @return Текущая коллекция.
     *
     * @throws NullException исключение проверки нулевого объекта (обработчика).
     * @since 1.0.0-RC1
     */
    @Contract("!null -> this; _ -> fail")
    default @NotNull ImmutableCollection<T> iterate(final @NotNull Handler<T> handler) throws NullException {
        Validator.notNull(handler, "handler");
        for (final var element : external()) handler.handle(element);
        return this;
    }

    /**
     * Итерирует переданный предикат по текущей коллекции, пока тот возвращает истину.
     *
     * @param predicate предикат.
     *
     * @return Текущая коллекция.
     *
     * @throws NullException исключение проверки нулевого объекта (предиката).
     * @since 1.0.0-RC1
     */
    @Contract("!null -> this; _ -> fail")
    default @NotNull ImmutableCollection<T> iterate(final @NotNull Predicate<T> predicate) throws NullException {
        Validator.notNull(predicate, "predicate");
        for (final var element : external()) if (!predicate.evaluate(element)) break;
        return this;
    }

    /**
     * Создаёт неизменяемый итератор.
     *
     * @return Неизменяемый итератор.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> new")
    @NotNull ImmutableIterator<T> iterator();

    /**
     * Создаёт внешнюю неизменяемую коллекцию на основе текущей.
     *
     * @return Внешняя неизменяемая коллекция.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> new")
    default @NotNull Iterable<T> external() {
        return iterator()::external;
    }

    /**
     * @return Текущий размер.
     *
     * @since 1.0.0-RC1
     */
    @Range(from = 0, to = Integer.MAX_VALUE) int size();

    /**
     * @return Является ли пустой.
     *
     * @since 1.0.0-RC1
     */
    default boolean empty() {
        return size() == 0;
    }

    /**
     * @return Является ли нулевой.
     *
     * @since 1.0.0-RC1
     */
    boolean nullable();

    /**
     * @return Является ли изменяемой.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> const")
    default boolean mutable() {
        return false;
    }

}
