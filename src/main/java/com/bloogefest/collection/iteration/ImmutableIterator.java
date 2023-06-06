/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.bloogefest.collection.iteration;

import com.bloogefest.annotation.analysis.Contract;
import com.bloogefest.annotation.analysis.NotNull;
import com.bloogefest.annotation.analysis.Nullable;
import com.bloogefest.common.validation.NullException;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор неизменяемых коллекций.
 *
 * @param <T> тип элемента.
 *
 * @since 1.0.0-RC1
 */
public interface ImmutableIterator<T> {

    /**
     * @return Текущий элемент.
     *
     * @since 1.0.0-RC1
     */
    @Nullable T element();

    /**
     * @return Кэшированный элемент.
     *
     * @since 1.0.0-RC1
     */
    @Nullable T cached();

    /**
     * Кэширует текущий элемент.
     *
     * @return Текущий итератор.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> this")
    @NotNull ImmutableIterator<T> cache();

    /**
     * Кэширует переданный элемент.
     *
     * @param element элемент.
     *
     * @return Текущий итератор.
     *
     * @throws NullException элемент не должен быть нулевым.
     * @since 1.0.0-RC1
     */
    @Contract("_ -> this")
    @NotNull ImmutableIterator<T> cache(final @Nullable T element) throws NullException;

    /**
     * Переходит к следующему элементу, когда тот существует.
     *
     * @return Текущий итератор.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> this")
    @NotNull ImmutableIterator<T> next();

    /**
     * Переходит к предыдущему элементу, когда тот существует.
     *
     * @return Текущий итератор.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> this")
    @NotNull ImmutableIterator<T> previous();

    /**
     * Переходит к первому элементу, когда тот существует.
     *
     * @return Текущий итератор.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> this")
    @NotNull ImmutableIterator<T> first();

    /**
     * Переходит к последнему элементу, когда тот существует.
     *
     * @return Текущий итератор.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> this")
    @NotNull ImmutableIterator<T> last();

    /**
     * Переходит к начальной позиции.
     *
     * @return Текущий итератор.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> this")
    @NotNull ImmutableIterator<T> start();

    /**
     * Переходит к конечной позиции.
     *
     * @return Текущий итератор.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> this")
    @NotNull ImmutableIterator<T> end();

    /**
     * Создаёт внешний неизменяемый итератор на основе текущего.
     *
     * @return Внешний неизменяемый итератор.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> new")
    default @NotNull Iterator<T> external() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return ImmutableIterator.this.hasNext();
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return ImmutableIterator.this.next().element();
            }
        };
    }

    /**
     * @return Существует ли текущий элемент.
     *
     * @since 1.0.0-RC1
     */
    boolean hasElement();

    /**
     * @return Существует ли кэшированный элемент.
     *
     * @since 1.0.0-RC1
     */
    boolean hasCached();

    /**
     * @return Существует ли следующий элемент.
     *
     * @since 1.0.0-RC1
     */
    boolean hasNext();

    /**
     * @return Существует ли предыдущий элемент.
     *
     * @since 1.0.0-RC1
     */
    boolean hasPrevious();

    /**
     * @return Существует ли первый элемент.
     *
     * @since 1.0.0-RC1
     */
    boolean hasFirst();

    /**
     * @return Существует ли последний элемент.
     *
     * @since 1.0.0-RC1
     */
    boolean hasLast();

    /**
     * @return Находится ли в начальной позиции.
     *
     * @since 1.0.0-RC1
     */
    boolean starting();

    /**
     * @return Находится ли в итерируемой позиции.
     *
     * @since 1.0.0-RC1
     */
    boolean iterating();

    /**
     * @return Находится ли в конечной позиции.
     *
     * @since 1.0.0-RC1
     */
    boolean ending();

    /**
     * @return Является ли нулевым.
     *
     * @since 1.0.0-RC1
     */
    boolean nullable();

    /**
     * @return Является ли изменяемым.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> false")
    default boolean mutable() {
        return false;
    }

}
