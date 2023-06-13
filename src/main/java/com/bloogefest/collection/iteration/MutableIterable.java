/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.bloogefest.collection.iteration;

import com.bloogefest.annotation.analysis.Contract;
import com.bloogefest.annotation.analysis.NotNull;
import com.bloogefest.collection.iteration.iterator.MutableIterator;
import com.bloogefest.common.function.Handler;
import com.bloogefest.common.function.Predicate;
import com.bloogefest.common.validation.NullException;

import java.util.concurrent.locks.Lock;

/**
 * Изменяемое итерируемое.
 *
 * @param <T> тип элемента.
 *
 * @since 1.0.0-RC1
 */
public interface MutableIterable<T> extends ImmutableIterable<T> {

    /**
     * Последовательно итерирует переданный {@linkplain Handler обработчик} в
     * {@linkplain SequentialIterationDirection#DEFAULT направлении последовательной итерации по умолчанию}.
     *
     * @param handler {@linkplain Handler обработчик}.
     *
     * @return Текущее {@linkplain MutableIterable изменяемое итерируемое}.
     *
     * @throws NullException исключение проверки нулевого объекта (переданного {@linkplain Handler обработчика}).
     * @throws SequentialIterationException исключение последовательной итерации.
     * @see #iterate(Handler, SequentialIterationDirection)
     * @since 1.0.0-RC1
     */
    @Override
    @Contract("!null -> this; _ -> fail")
    default @NotNull MutableIterable<T> iterate(
            final @NotNull Handler<T> handler) throws NullException, SequentialIterationException {
        ImmutableIterable.super.iterate(handler);
        return this;
    }

    /**
     * Последовательно итерирует переданный {@linkplain Handler обработчик} в переданном
     * {@linkplain SequentialIterationDirection направлении последовательной итерации}.
     *
     * @param handler {@linkplain Handler обработчик}.
     * @param direction {@linkplain SequentialIterationDirection направление последовательной итерации}.
     *
     * @return Текущее {@linkplain MutableIterable изменяемое итерируемое}.
     *
     * @throws NullException исключение проверки нулевого объекта (переданного {@linkplain Handler обработчика} или
     * {@linkplain SequentialIterationDirection направления последовательной итерации}).
     * @throws SequentialIterationException исключение последовательной итерации.
     * @see #iterate(Handler)
     * @since 1.0.0-RC1
     */
    @Override
    @Contract("!null, !null -> this; _, _ -> fail")
    default @NotNull MutableIterable<T> iterate(final @NotNull Handler<T> handler,
                                                final @NotNull SequentialIterationDirection direction) throws NullException, SequentialIterationException {
        ImmutableIterable.super.iterate(handler, direction);
        return this;
    }

    /**
     * Последовательно итерирует переданный {@linkplain Predicate предикат} в
     * {@linkplain SequentialIterationDirection#DEFAULT направлении последовательной итерации по умолчанию}.
     *
     * @param predicate {@linkplain Predicate предикат}.
     *
     * @return Текущее {@linkplain MutableIterable изменяемое итерируемое}.
     *
     * @throws NullException исключение проверки нулевого объекта (переданного {@linkplain Predicate предиката}).
     * @throws SequentialIterationException исключение последовательной итерации.
     * @see #iterate(Predicate, SequentialIterationDirection)
     * @since 1.0.0-RC1
     */
    @Override
    @Contract("!null -> this; _ -> fail")
    default @NotNull MutableIterable<T> iterate(
            final @NotNull Predicate<T> predicate) throws NullException, SequentialIterationException {
        ImmutableIterable.super.iterate(predicate);
        return this;
    }

    /**
     * Последовательно итерирует переданный {@linkplain Predicate предикат} в переданном
     * {@linkplain SequentialIterationDirection направлении последовательной итерации}, пока тот возвращает истину.
     *
     * @param predicate {@linkplain Predicate предикат}.
     * @param direction {@linkplain SequentialIterationDirection направление последовательной итерации}.
     *
     * @return Текущее {@linkplain MutableIterable изменяемое итерируемое}.
     *
     * @throws NullException исключение проверки нулевого объекта (переданного {@linkplain Predicate предиката} или
     * {@linkplain SequentialIterationDirection направления последовательной итерации}).
     * @throws SequentialIterationException исключение последовательной итерации.
     * @see #iterate(Predicate)
     * @since 1.0.0-RC1
     */
    @Override
    @Contract("!null, !null -> this; _, _ -> fail")
    default @NotNull MutableIterable<T> iterate(final @NotNull Predicate<T> predicate,
                                                final @NotNull SequentialIterationDirection direction) throws NullException, SequentialIterationException {
        ImmutableIterable.super.iterate(predicate, direction);
        return this;
    }

    /**
     * Создаёт и возвращает {@linkplain MutableIterator итератор} текущего
     * {@linkplain MutableIterable изменяемого итерируемого} в
     * {@linkplain SequentialIterationDirection#DEFAULT направлении последовательной итерации по умолчанию}.
     *
     * @return {@linkplain MutableIterator Итератор} текущего {@linkplain MutableIterable изменяемого итерируемого} в
     * {@linkplain SequentialIterationDirection#DEFAULT направлении последовательной итерации по умолчанию}.
     *
     * @since 1.0.0-RC1
     */
    @Override
    @Contract("-> new")
    default @NotNull MutableIterator<T> iterator() {
        return iterator(SequentialIterationDirection.DEFAULT);
    }

    /**
     * Создаёт и возвращает {@linkplain MutableIterator итератор} текущего
     * {@linkplain MutableIterable изменяемого итерируемого} в переданном
     * {@linkplain SequentialIterationDirection направлении последовательной итерации}.
     *
     * @param direction {@linkplain SequentialIterationDirection направление последовательной итерации}.
     *
     * @return {@linkplain MutableIterator Итератор} текущего {@linkplain MutableIterable изменяемого итерируемого} в
     * переданном {@linkplain SequentialIterationDirection направлении последовательной итерации}.
     *
     * @throws NullException исключение проверки нулевого объекта (переданного
     * {@linkplain SequentialIterationDirection направления последовательной итерации}).
     * @since 1.0.0-RC1
     */
    @Override
    @Contract("!null -> new; _ -> fail")
    @NotNull MutableIterator<T> iterator(final @NotNull SequentialIterationDirection direction) throws NullException;

    /**
     * Создаёт и возвращает {@linkplain ExternalIterable внешнее итерируемое} на основе текущего
     * {@linkplain MutableIterable изменяемого итерируемого}.
     *
     * @return {@linkplain ExternalIterable Внешнее итерируемое} на основе этого
     * {@linkplain MutableIterable изменяемого итерируемого}.
     *
     * @since 1.0.0-RC1
     */
    @Override
    @Contract("-> new")
    default @NotNull ExternalIterable<T> external() {
        return ImmutableIterable.super.external();
    }

    /**
     * Возвращает {@linkplain Lock инструмент для управления доступом} к текущему
     * {@linkplain MutableIterable изменяемому итерируемому}.
     *
     * @return {@linkplain Lock Инструмент для управления доступом} к текущему
     * {@linkplain MutableIterable изменяемому итерируемому}.
     *
     * @since 1.0.0-RC1
     */
    @Override
    @Contract("-> const")
    @NotNull Lock lock();

    /**
     * Возвращает параметр изменяемости.
     *
     * @return Параметр изменяемости.
     *
     * @since 1.0.0-RC1
     */
    @Override
    @Contract("-> true")
    default boolean mutable() {
        return true;
    }

}
