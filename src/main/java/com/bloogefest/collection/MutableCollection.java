/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.bloogefest.collection;

import com.bloogefest.annotation.analysis.Contract;
import com.bloogefest.annotation.analysis.NotNull;
import com.bloogefest.annotation.analysis.Range;
import com.bloogefest.collection.iteration.MutableIterator;
import com.bloogefest.common.function.Handler;
import com.bloogefest.common.function.Predicate;
import com.bloogefest.common.validation.NullException;
import com.bloogefest.common.validation.Validator;

/**
 * Изменяемая коллекция.
 *
 * @param <E> тип элемента.
 *
 * @since 1.0.0-RC1
 */
public interface MutableCollection<E> extends ImmutableCollection<E> {

    /**
     * Итерирует обработчик по всей коллекции.
     *
     * @param handler обработчик.
     *
     * @return Текущая коллекция.
     *
     * @throws NullException обработчик не должен быть нулевым.
     * @since 1.0.0-RC1
     */
    @Contract(value = "_ -> this")
    default @NotNull MutableCollection<E> iterate(final @NotNull Handler<E> handler) throws NullException {
        Validator.notNull(handler, "handler");
        for (final var element : external()) handler.handle(element);
        return this;
    }

    /**
     * Постепенно итерирует предикат по всей коллекции, пока тот требует следующий элемент.
     *
     * @param predicate предикат.
     *
     * @return Текущая коллекция.
     *
     * @throws NullException предикат не должен быть нулевым.
     * @since 1.0.0-RC1
     */
    @Override
    @Contract(value = "_ -> this")
    default @NotNull MutableCollection<E> iterate(final @NotNull Predicate<E> predicate) throws NullException {
        Validator.notNull(predicate, "predicate");
        for (final var element : external()) if (!predicate.evaluate(element)) break;
        return this;
    }

    /**
     * Создаёт изменяемый итератор.
     *
     * @return Изменяемый итератор.
     *
     * @since 1.0.0-RC1
     */
    @Override
    @Contract(value = "-> new")
    @NotNull MutableIterator<E> iterator();

    /**
     * Создаёт внешнюю изменяемую коллекцию на основе текущей.
     *
     * @return Внешняя изменяемая коллекция.
     *
     * @since 1.0.0-RC1
     */
    @Override
    @Contract(value = "-> new")
    default @NotNull Iterable<E> external() {
        return iterator()::external;
    }

    /**
     * @return Текущий размер.
     *
     * @since 1.0.0-RC1
     */
    @Override
    @Range(from = 0, to = Integer.MAX_VALUE) int size();

    /**
     * @return Является ли пустой.
     *
     * @since 1.0.0-RC1
     */
    @Override
    default boolean empty() {
        return size() == 0;
    }

    /**
     * @return Является ли нулевой.
     *
     * @since 1.0.0-RC1
     */
    @Override
    boolean nullable();

    /**
     * @return Является ли изменяемой.
     *
     * @since 1.0.0-RC1
     */
    @Override
    @Contract(value = "-> true")
    default boolean mutable() {
        return true;
    }

}
