/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.bloogefest.collection.iteration;

import com.bloogefest.annotation.analysis.Contract;
import com.bloogefest.annotation.analysis.NotNull;
import com.bloogefest.collection.iteration.iterator.ImmutableIterator;
import com.bloogefest.common.function.Handler;
import com.bloogefest.common.function.Predicate;
import com.bloogefest.common.validation.NullException;
import com.bloogefest.common.validation.Validator;

import java.util.concurrent.locks.Lock;

/**
 * Неизменяемое итерируемое.
 *
 * @param <T> тип элемента.
 *
 * @since 1.0.0-RC1
 */
public interface ImmutableIterable<T> {

    /**
     * Последовательно итерирует переданный {@linkplain Handler обработчик} в
     * {@linkplain SequentialIterationDirection#DEFAULT направлении последовательной итерации по умолчанию}.
     *
     * @param handler {@linkplain Handler обработчик}.
     *
     * @return Текущее {@linkplain ImmutableIterable неизменяемое итерируемое}.
     *
     * @throws NullException исключение проверки нулевого объекта (переданного {@linkplain Handler обработчика}).
     * @throws SequentialIterationException исключение последовательной итерации.
     * @see #iterate(Handler, SequentialIterationDirection)
     * @since 1.0.0-RC1
     */
    @Contract("!null -> this; _ -> fail")
    default @NotNull ImmutableIterable<T> iterate(
            final @NotNull Handler<T> handler) throws NullException, SequentialIterationException {
        return iterate(handler, SequentialIterationDirection.DEFAULT);
    }

    /**
     * Последовательно итерирует переданный {@linkplain Handler обработчик} в переданном
     * {@linkplain SequentialIterationDirection направлении последовательной итерации}.
     *
     * @param handler {@linkplain Handler обработчик}.
     * @param direction {@linkplain SequentialIterationDirection направление последовательной итерации}.
     *
     * @return Текущее {@linkplain ImmutableIterable неизменяемое итерируемое}.
     *
     * @throws NullException исключение проверки нулевого объекта (переданного {@linkplain Handler обработчика} или
     * {@linkplain SequentialIterationDirection направления последовательной итерации}).
     * @throws SequentialIterationException исключение последовательной итерации.
     * @see #iterate(Handler)
     * @since 1.0.0-RC1
     */
    @Contract("!null, !null -> this; _, _ -> fail")
    default @NotNull ImmutableIterable<T> iterate(final @NotNull Handler<T> handler,
                                                  final @NotNull SequentialIterationDirection direction) throws NullException, SequentialIterationException {
        Validator.notNull(handler, "The handler");
        Validator.notNull(direction, "The direction");
        final @NotNull var lock = Validator.notNull(lock(), "The lock");
        final @NotNull var iterator = Validator.notNull(iterator(direction), "The iterator");
        try {
            lock.lockInterruptibly();
            while (iterator.hasNext()) handler.handle(iterator.next().current());
            return this;
        } catch (final Throwable failure) {
            throw new SequentialIterationException(failure);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Последовательно итерирует переданный {@linkplain Predicate предикат} в
     * {@linkplain SequentialIterationDirection#DEFAULT направлении последовательной итерации по умолчанию}.
     *
     * @param predicate {@linkplain Predicate предикат}.
     *
     * @return Текущее {@linkplain ImmutableIterable неизменяемое итерируемое}.
     *
     * @throws NullException исключение проверки нулевого объекта (переданного {@linkplain Predicate предиката}).
     * @throws SequentialIterationException исключение последовательной итерации.
     * @see #iterate(Predicate, SequentialIterationDirection)
     * @since 1.0.0-RC1
     */
    @Contract("!null -> this; _ -> fail")
    default @NotNull ImmutableIterable<T> iterate(
            final @NotNull Predicate<T> predicate) throws NullException, SequentialIterationException {
        return iterate(predicate, SequentialIterationDirection.DEFAULT);
    }

    /**
     * Последовательно итерирует переданный {@linkplain Predicate предикат} в переданном
     * {@linkplain SequentialIterationDirection направлении последовательной итерации}, пока тот возвращает истину.
     *
     * @param predicate {@linkplain Predicate предикат}.
     * @param direction {@linkplain SequentialIterationDirection направление последовательной итерации}.
     *
     * @return Текущее {@linkplain ImmutableIterable неизменяемое итерируемое}.
     *
     * @throws NullException исключение проверки нулевого объекта (переданного {@linkplain Predicate предиката} или
     * {@linkplain SequentialIterationDirection направления последовательной итерации}).
     * @throws SequentialIterationException исключение последовательной итерации.
     * @see #iterate(Predicate)
     * @since 1.0.0-RC1
     */
    @Contract("!null, !null -> this; _, _ -> fail")
    default @NotNull ImmutableIterable<T> iterate(final @NotNull Predicate<T> predicate,
                                                  final @NotNull SequentialIterationDirection direction) throws NullException, SequentialIterationException {
        Validator.notNull(predicate, "The predicate");
        Validator.notNull(direction, "The direction");
        final @NotNull var lock = Validator.notNull(lock(), "The lock");
        final @NotNull var iterator = Validator.notNull(iterator(direction), "The iterator");
        try {
            lock.lockInterruptibly();
            while (iterator.hasNext()) if (!predicate.evaluate(iterator.next().current())) break;
            return this;
        } catch (final Throwable failure) {
            throw new SequentialIterationException(failure);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Создаёт и возвращает {@linkplain ImmutableIterator итератор} текущего
     * {@linkplain ImmutableIterable неизменяемого итерируемого} в
     * {@linkplain SequentialIterationDirection#DEFAULT направлении последовательной итерации по умолчанию}.
     *
     * @return {@linkplain ImmutableIterator Итератор} текущего
     * {@linkplain ImmutableIterable неизменяемого итерируемого} в
     * {@linkplain SequentialIterationDirection#DEFAULT направлении последовательной итерации по умолчанию}.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> new")
    default @NotNull ImmutableIterator<T> iterator() {
        return iterator(SequentialIterationDirection.DEFAULT);
    }

    /**
     * Создаёт и возвращает {@linkplain ImmutableIterator итератор} текущего
     * {@linkplain ImmutableIterable неизменяемого итерируемого} в переданном
     * {@linkplain SequentialIterationDirection направлении последовательной итерации}.
     *
     * @param direction {@linkplain SequentialIterationDirection направление последовательной итерации}.
     *
     * @return {@linkplain ImmutableIterator Итератор} текущего
     * {@linkplain ImmutableIterable неизменяемого итерируемого} в переданном
     * {@linkplain SequentialIterationDirection направлении последовательной итерации}.
     *
     * @throws NullException исключение проверки нулевого объекта (переданного
     * {@linkplain SequentialIterationDirection направления последовательной итерации}).
     * @since 1.0.0-RC1
     */
    @Contract("!null -> new; _ -> fail")
    @NotNull ImmutableIterator<T> iterator(final @NotNull SequentialIterationDirection direction) throws NullException;

    /**
     * Создаёт и возвращает {@linkplain ExternalIterable внешнее итерируемое} на основе текущего
     * {@linkplain ImmutableIterable неизменяемого итерируемого}.
     *
     * @return {@linkplain ExternalIterable Внешнее итерируемое} на основе этого
     * {@linkplain ImmutableIterable неизменяемого итерируемого}.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> new")
    default @NotNull ExternalIterable<T> external() {
        return ExternalIterable.of(iterator()::external);
    }

    /**
     * Возвращает {@linkplain Lock инструмент для управления доступом} к текущему
     * {@linkplain ImmutableIterable неизменяемому итерируемому}.
     *
     * @return {@linkplain Lock Инструмент для управления доступом} к текущему
     * {@linkplain ImmutableIterable неизменяемому итерируемому}.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> const")
    @NotNull Lock lock();

    /**
     * Возвращает параметр поддержки нулевых элементов.
     *
     * @return Параметр поддержки нулевых элементов.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> const")
    boolean nullable();

    /**
     * Возвращает параметр изменяемости.
     *
     * @return Параметр изменяемости.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> false")
    default boolean mutable() {
        return false;
    }

}
