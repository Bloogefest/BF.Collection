/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.bloogefest.collection.iteration.iterator;

import com.bloogefest.annotation.analysis.Contract;
import com.bloogefest.annotation.analysis.NotNull;
import com.bloogefest.collection.iteration.SequentialIterationDirection;
import com.bloogefest.common.function.BiOptional;
import com.bloogefest.common.function.Optional;
import com.bloogefest.common.validation.NullException;
import com.bloogefest.common.validation.Validator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Внешний итератор.
 *
 * @since 1.0.0-RC1
 */
public interface ExternalIterator<T> extends Iterator<T> {

    /**
     * Создаёт и возвращает {@linkplain ExternalIterator внешний итератор} на основе переданного
     * {@linkplain ImmutableIterator итератора неизменяемого}.
     *
     * @param iterator {@linkplain ImmutableIterator итератор неизменяемого}.
     *
     * @return {@linkplain ExternalIterator Внешний итератор} на основе переданного
     * {@linkplain ImmutableIterator итератора неизменяемого}.
     *
     * @throws NullException исключение проверки нулевого объекта (переданного
     * {@linkplain ImmutableIterator итератора неизменяемого}).
     * @since 1.0.0-RC1
     */
    static <T> ExternalIterator<T> immutable(final @NotNull ImmutableIterator<T> iterator) throws NullException {
        return () -> BiOptional.unchecked(Validator.notNull(iterator, "The iterator"), null);
    }

    /**
     * Создаёт и возвращает {@linkplain ExternalIterator внешний итератор} на основе переданного
     * {@linkplain MutableIterator итератора изменяемого}.
     *
     * @param iterator {@linkplain MutableIterator итератор изменяемого}.
     *
     * @return {@linkplain ExternalIterator Внешний итератор} на основе переданного
     * {@linkplain MutableIterator итератора изменяемого}.
     *
     * @throws NullException исключение проверки нулевого объекта (переданного
     * {@linkplain MutableIterator итератора изменяемого}).
     * @since 1.0.0-RC1
     */
    static <T> ExternalIterator<T> mutable(final @NotNull MutableIterator<T> iterator) throws NullException {
        return () -> BiOptional.unchecked(Validator.notNull(iterator, "The iterator"), iterator);
    }

    /**
     * Возвращает параметр существования следующего элемента.
     *
     * @return Параметр существования следующего элемента.
     *
     * @since 1.0.0-RC1
     */
    @Override
    default boolean hasNext() {
        return iterator().first().hasNext();
    }

    /**
     * Если {@linkplain #hasNext() параметр существования следующего элемента} истинный, возвращает следующий элемент, в
     * противном случае генерирует {@linkplain NoSuchElementException исключение отсутствия элемента} (следующего
     * элемента).
     *
     * @return Следующий элемент.
     *
     * @throws NoSuchElementException исключение отсутствия элемента (следующего элемента).
     * @throws IteratorAuxiliaryException вспомогательное исключение итератора.
     * @since 1.0.0-RC1
     */
    @Override
    default @NotNull T next() throws NoSuchElementException, IteratorAuxiliaryException {
        final @NotNull var iterator = iterator().first();
        final @NotNull var lock = iterator.lock();
        try {
            lock.lockInterruptibly();
            if (!hasNext()) throw new NoSuchElementException();
            return iterator.next().current();
        } catch (final @NotNull NoSuchElementException failure) {
            throw failure;
        } catch (final @NotNull Exception failure) {
            throw new IteratorAuxiliaryException(failure);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Если текущий {@linkplain ExternalIterator внешний итератор} основан на
     * {@linkplain MutableIterator итераторе изменяемого}, {@linkplain #mutable() параметр изменяемости} и
     * {@linkplain #hasCurrent() параметр существования текущего элемента} истинные, удаляет текущий элемент, в
     * противном случае генерирует {@linkplain UnsupportedOperationException исключение неподдерживаемой операции}
     * (удаления элемента).
     *
     * @throws UnsupportedOperationException исключение неподдерживаемой операции (удаления элемента).
     * @since 1.0.0-RC1
     */
    @Override
    default void remove() throws UnsupportedOperationException {
        iterator().asSecond().failure(UnsupportedOperationException::new).delete();
    }

    /**
     * Последовательно итерирует переданный {@linkplain Consumer потребитель} в
     * {@linkplain SequentialIterationDirection#DEFAULT направлении последовательной итерации по умолчанию}.
     *
     * @param consumer {@linkplain Consumer потребитель}.
     *
     * @throws NullPointerException исключение проверки нулевого объекта (переданного
     * {@linkplain Consumer потребителя}).
     * @throws IteratorAuxiliaryException вспомогательное исключение итератора.
     * @since 1.0.0-RC1
     */
    @Override
    @Contract("!null ->; _ -> fail")
    default void forEachRemaining(
            final @NotNull Consumer<? super T> consumer) throws NullPointerException, IteratorAuxiliaryException {
        Objects.requireNonNull(consumer);
        final @NotNull var lock = iterator().first().lock();
        try {
            lock.lockInterruptibly();
            while (hasNext()) consumer.accept(next());
        } catch (final @NotNull Exception failure) {
            throw new IteratorAuxiliaryException(failure);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Возвращает параметр существования текущего элемента.
     *
     * @return Параметр существования текущего элемента.
     *
     * @since 1.0.0-RC1
     */
    default boolean hasCurrent() {
        return iterator().first().hasCurrent();
    }

    /**
     * Возвращает параметр существования предыдущего элемента.
     *
     * @return Параметр существования предыдущего элемента.
     *
     * @since 1.0.0-RC1
     */
    default boolean hasPrevious() {
        return iterator().first().hasPrevious();
    }

    /**
     * Если {@linkplain #hasPrevious() параметр существования предыдущего элемента} истинный, возвращает предыдущий
     * элемент, в противном случае генерирует
     * {@linkplain NoSuchElementException исключение отсутствия элемента (предыдущего элемента)}.
     *
     * @return Предыдущий элемент.
     *
     * @throws NoSuchElementException исключение отсутствия элемента (предыдущего элемента).
     * @throws IteratorAuxiliaryException вспомогательное исключение итератора.
     * @since 1.0.0-RC1
     */
    default @NotNull T previous() throws NoSuchElementException, IteratorAuxiliaryException {
        final @NotNull var iterator = iterator().first();
        final @NotNull var lock = iterator.lock();
        try {
            lock.lockInterruptibly();
            if (!hasPrevious()) throw new NoSuchElementException();
            return iterator.previous().current();
        } catch (final @NotNull NoSuchElementException failure) {
            throw failure;
        } catch (final @NotNull Exception failure) {
            throw new IteratorAuxiliaryException(failure);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Возвращает {@linkplain Optional обёртку двух обнуляемых объектов} (текущего неизменяемого и изменяемого
     * итератора).
     *
     * @return {@linkplain Optional Обёртку двух обнуляемых объектов} (текущего неизменяемого и изменяемого итератора).
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> const")
    @NotNull BiOptional<ImmutableIterator<T>, MutableIterator<T>> iterator();

    /**
     * Возвращает параметр изменяемости.
     *
     * @return Параметр изменяемости.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> const")
    default boolean mutable() {
        return iterator().first().mutable();
    }

}
