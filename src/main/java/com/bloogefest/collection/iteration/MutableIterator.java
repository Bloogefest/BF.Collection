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
 * Итератор изменяемых коллекций.
 *
 * @param <E> тип элемента.
 *
 * @since 1.0.0-RC1
 */
public interface MutableIterator<E> extends ImmutableIterator<E> {

    /**
     * @return Текущий элемент.
     *
     * @since 1.0.0-RC1
     */
    @Override
    @Nullable E element();

    /**
     * Заменяет текущий элемент переданным.
     *
     * @param element элемент.
     *
     * @return Текущий итератор.
     *
     * @throws NullException элемент не должен быть нулевым.
     * @since 1.0.0-RC1
     */
    @Contract(value = "_ -> this")
    @NotNull MutableIterator<E> change(final @Nullable E element) throws NullException;

    /**
     * Удаляет текущий элемент.
     *
     * @return Текущий итератор.
     *
     * @since 1.0.0-RC1
     */
    @Contract(value = "-> this")
    @NotNull MutableIterator<E> delete();

    /**
     * @return Кэшированный элемент.
     *
     * @since 1.0.0-RC1
     */
    @Override
    @Nullable E cached();

    /**
     * Кэширует текущий элемент.
     *
     * @return Текущий итератор.
     *
     * @since 1.0.0-RC1
     */
    @Override
    @Contract(value = "-> this")
    @NotNull MutableIterator<E> cache();

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
    @Override
    @Contract(value = "_ -> this")
    @NotNull MutableIterator<E> cache(final @Nullable E element) throws NullException;

    /**
     * Заменяет текущий элемент кэшированным.
     *
     * @return Текущий итератор.
     *
     * @since 1.0.0-RC1
     */
    @Contract(value = "-> this")
    @NotNull MutableIterator<E> paste();

    /**
     * Кэширует и удаляет текущий элемент.
     *
     * @return Текущий итератор.
     *
     * @since 1.0.0-RC1
     */
    @Contract(value = "-> this")
    @NotNull MutableIterator<E> cut();

    /**
     * Переходит к следующему элементу, когда тот существует.
     *
     * @return Текущий итератор.
     *
     * @since 1.0.0-RC1
     */
    @Override
    @Contract(value = "-> this")
    @NotNull MutableIterator<E> next();

    /**
     * Переходит к предыдущему элементу, когда тот существует.
     *
     * @return Текущий итератор.
     *
     * @since 1.0.0-RC1
     */
    @Override
    @Contract(value = "-> this")
    @NotNull MutableIterator<E> previous();

    /**
     * Переходит к первому элементу, когда тот существует.
     *
     * @return Текущий итератор.
     *
     * @since 1.0.0-RC1
     */
    @Override
    @Contract(value = "-> this")
    @NotNull MutableIterator<E> first();

    /**
     * Переходит к последнему элементу, когда тот существует.
     *
     * @return Текущий итератор.
     *
     * @since 1.0.0-RC1
     */
    @Override
    @Contract(value = "-> this")
    @NotNull MutableIterator<E> last();

    /**
     * Переходит к первому элементу, когда тот существует.
     *
     * @return Текущий итератор.
     *
     * @since 1.0.0-RC1
     */
    @Override
    @Contract(value = "-> this")
    @NotNull MutableIterator<E> start();

    /**
     * Переходит к конечному элементу, когда тот существует.
     *
     * @return Текущий итератор.
     *
     * @since 1.0.0-RC1
     */
    @Override
    @Contract(value = "-> this")
    @NotNull MutableIterator<E> end();

    /**
     * Создаёт внешний неизменяемый итератор на основе текущего.
     *
     * @return Внешний неизменяемый итератор.
     *
     * @since 1.0.0-RC1
     */
    @Override
    @Contract(value = "-> new")
    default @NotNull Iterator<E> external() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return MutableIterator.this.hasNext();
            }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                return MutableIterator.this.next().element();
            }

            @Override
            public void remove() {
                if (!hasElement()) throw new IllegalStateException();
                delete();
            }
        };
    }

    /**
     * @return Существует ли текущий элемент.
     *
     * @since 1.0.0-RC1
     */
    @Override
    boolean hasElement();

    /**
     * @return Существует ли кэшированный элемент.
     *
     * @since 1.0.0-RC1
     */
    @Override
    boolean hasCached();

    /**
     * @return Существует ли следующий элемент.
     *
     * @since 1.0.0-RC1
     */
    @Override
    boolean hasNext();

    /**
     * @return Существует ли предыдущий элемент.
     *
     * @since 1.0.0-RC1
     */
    @Override
    boolean hasPrevious();

    /**
     * @return Существует ли первый элемент.
     *
     * @since 1.0.0-RC1
     */
    @Override
    boolean hasFirst();

    /**
     * @return Существует ли последний элемент.
     *
     * @since 1.0.0-RC1
     */
    @Override
    boolean hasLast();

    /**
     * @return Находится ли в начальной позиции.
     *
     * @since 1.0.0-RC1
     */
    @Override
    boolean starting();

    /**
     * @return Находится ли в итерируемой позиции.
     *
     * @since 1.0.0-RC1
     */
    @Override
    boolean iterating();

    /**
     * @return Находится ли в конечной позиции.
     *
     * @since 1.0.0-RC1
     */
    @Override
    boolean ending();

    /**
     * @return Является ли нулевым.
     *
     * @since 1.0.0-RC1
     */
    @Override
    boolean nullable();

    /**
     * @return Является ли изменяемым.
     *
     * @since 1.0.0-RC1
     */
    @Override
    @Contract(value = "-> true")
    default boolean mutable() {
        return true;
    }

}
