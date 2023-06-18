/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.bloogefest.collection.iteration.iterator;

import com.bloogefest.annotation.analysis.Contract;
import com.bloogefest.annotation.analysis.NotNull;
import com.bloogefest.annotation.analysis.Nullable;
import com.bloogefest.common.function.Optional;
import com.bloogefest.common.validation.NullException;

import java.util.concurrent.locks.Lock;

/**
 * Итератор неизменяемого.
 *
 * @param <T> тип элемента.
 *
 * @since 1.0.0-RC1
 */
public interface ImmutableIterator<T> {

    /**
     * Если {@linkplain #hasCurrent() параметр существования текущего элемента} истинный, возвращает текущий элемент, в
     * противном случае генерирует {@linkplain IteratorElementGettingException исключение получения элемента итератора}
     * (текущего элемента).
     *
     * @return Текущий элемент.
     *
     * @throws IteratorElementGettingException исключение получения элемента итератора (текущего элемента).
     * @see #optionalCurrent()
     * @since 1.0.0-RC1
     */
    @Nullable T current() throws IteratorElementGettingException;

    /**
     * Если {@linkplain #hasCached() параметр существования кэшированного элемента} истинный, возвращает кэшированный
     * элемент, в противном случае генерирует
     * {@linkplain IteratorElementGettingException исключение получения элемента итератора} (кэшированного элемента).
     *
     * @return Кэшированный элемент.
     *
     * @throws IteratorElementGettingException исключение получения элемента итератора (кэшированного элемента).
     * @see #optionalCached()
     * @since 1.0.0-RC1
     */
    @Nullable T cached() throws IteratorElementGettingException;

    /**
     * Если {@linkplain #hasCurrent() параметр существования текущего элемента} истинный, создаёт и возвращает
     * {@linkplain Optional обёртку обнуляемого объекта} (текущего элемента), в противном случае создаёт и возвращает
     * {@linkplain Optional обёртку обнуляемого объекта} (нулевого объекта).
     *
     * @return {@linkplain Optional Обёртка обнуляемого объекта} (текущего элемента или нулевого объекта).
     *
     * @throws IteratorElementOptionalGettingException исключение необязательного получения элемента итератора (текущего
     * элемента).
     * @throws IteratorAuxiliaryException вспомогательное исключение итератора.
     * @see #current()
     * @since 1.0.0-RC1
     */
    @Contract("-> new")
    default @NotNull Optional<T> optionalCurrent() throws IteratorElementOptionalGettingException, IteratorAuxiliaryException {
        final @NotNull var lock = lock();
        try {
            lock.lockInterruptibly();
            return hasCurrent() ? Optional.nullable(current()) : Optional.empty();
        } catch (final @NotNull IteratorException failure) {
            throw new IteratorElementOptionalGettingException(failure);
        } catch (final @NotNull Exception failure) {
            throw new IteratorAuxiliaryException(failure);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Если {@linkplain #hasCached() параметр существования кэшированного элемента} истинный, создаёт и возвращает
     * {@linkplain Optional обёртку обнуляемого объекта} (кэшированного элемента), в противном случае создаёт и
     * возвращает {@linkplain Optional обёртку обнуляемого объекта} (нулевого объекта).
     *
     * @return {@linkplain Optional Обёртка обнуляемого объекта} (кэшированного элемента или нулевого объекта).
     *
     * @throws IteratorElementOptionalGettingException исключение необязательного получения элемента итератора
     * (кэшированного элемента).
     * @throws IteratorAuxiliaryException вспомогательное исключение итератора.
     * @see #cached()
     * @since 1.0.0-RC1
     */
    @Contract("-> new")
    default @NotNull Optional<T> optionalCached() throws IteratorElementOptionalGettingException, IteratorAuxiliaryException {
        final @NotNull var lock = lock();
        try {
            lock.lockInterruptibly();
            return hasCached() ? Optional.nullable(cached()) : Optional.empty();
        } catch (final @NotNull IteratorException failure) {
            throw new IteratorElementOptionalGettingException(failure);
        } catch (final @NotNull Exception failure) {
            throw new IteratorAuxiliaryException(failure);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Если {@linkplain #hasCurrent() параметр существования текущего элемента} истинный, кэширует текущий элемент, в
     * противном случае генерирует {@linkplain IteratorElementGettingException исключение получения элемента итератора}
     * (текущего элемента). Возвращает текущий итератор.
     *
     * @return Текущий итератор.
     *
     * @throws IteratorElementGettingException исключение получения элемента итератора (текущего элемента).
     * @throws IteratorElementCachingException исключение кэширования элемента итератора (текущего элемента).
     * @throws IteratorAuxiliaryException вспомогательное исключение итератора.
     * @see #cache(Object)
     * @since 1.0.0-RC1
     */
    @Contract("-> this")
    default @NotNull ImmutableIterator<T> cache() throws IteratorElementGettingException, IteratorElementCachingException, IteratorAuxiliaryException {
        final @NotNull var lock = lock();
        try {
            lock.lockInterruptibly();
            return cache(current());
        } catch (final @NotNull IteratorElementCachingException failure) {
            throw failure;
        } catch (final @NotNull IteratorException failure) {
            throw new IteratorElementCachingException(failure);
        } catch (final @NotNull Exception failure) {
            throw new IteratorAuxiliaryException(failure);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Если переданный элемент нулевой и {@linkplain #nullable() параметр поддержки нулевых элементов} ложный,
     * генерирует {@linkplain NullException исключение проверки нулевого объекта} (переданного элемента). Кэширует
     * переданный элемент. Возвращает текущий итератор.
     *
     * @param element элемент.
     *
     * @return Текущий итератор.
     *
     * @throws NullException исключение проверки нулевого объекта (переданного элемента).
     * @throws IteratorElementCachingException исключение кэширования элемента итератора (переданного элемента).
     * @see #cache()
     * @since 1.0.0-RC1
     */
    @Contract("_ -> this")
    @NotNull ImmutableIterator<T> cache(
            final @Nullable T element) throws NullException, IteratorElementCachingException;

    /**
     * Если {@linkplain #hasNext() параметр существования следующего элемента} истинный, изменяет текущую позицию
     * итератора на следующую, в противном случае генерирует
     * {@linkplain IteratorPositionChangingException исключение изменения позиции итератора} (текущей позиции).
     * Возвращает текущий итератор.
     *
     * @return Текущий итератор.
     *
     * @throws IteratorPositionChangingException исключение изменения позиции итератора (текущей позиции).
     * @since 1.0.0-RC1
     */
    @Contract("-> this")
    @NotNull ImmutableIterator<T> next() throws IteratorPositionChangingException;

    /**
     * Если {@linkplain #hasPrevious() параметр существования предыдущего элемента} истинный, изменяет текущую позицию
     * итератора на предыдущую, в противном случае генерирует
     * {@linkplain IteratorPositionChangingException исключение изменения позиции итератора} (текущей позиции).
     * Возвращает текущий итератор.
     *
     * @return Текущий итератор.
     *
     * @throws IteratorPositionChangingException исключение изменения позиции итератора (текущей позиции).
     * @since 1.0.0-RC1
     */
    @Contract("-> this")
    @NotNull ImmutableIterator<T> previous() throws IteratorPositionChangingException;

    /**
     * Если {@linkplain #hasFirst() параметр существования первого элемента} истинный, изменяет текущую позицию
     * итератора на первую, в противном случае генерирует
     * {@linkplain IteratorPositionChangingException исключение изменения позиции итератора} (текущей позиции).
     * Возвращает текущий итератор.
     *
     * @return Текущий итератор.
     *
     * @throws IteratorPositionChangingException исключение изменения позиции итератора (текущей позиции).
     * @throws IteratorAuxiliaryException вспомогательное исключение итератора.
     * @since 1.0.0-RC1
     */
    @Contract("-> this")
    default @NotNull ImmutableIterator<T> first() throws IteratorPositionChangingException, IteratorAuxiliaryException {
        final @NotNull var lock = lock();
        try {
            lock.lockInterruptibly();
            if (!hasFirst()) throw new IteratorPositionChangingException();
            if (starting()) return next();
            while (hasPrevious()) previous();
            return this;
        } catch (final @NotNull IteratorPositionChangingException failure) {
            throw failure;
        } catch (final @NotNull IteratorException failure) {
            throw new IteratorPositionChangingException(failure);
        } catch (final @NotNull Exception failure) {
            throw new IteratorAuxiliaryException(failure);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Если {@linkplain #hasLast() параметр существования последнего элемента} истинный, изменяет текущую позицию
     * итератора на последнюю, в противном случае генерирует
     * {@linkplain IteratorPositionChangingException исключение изменения позиции итератора} (текущей позиции).
     * Возвращает текущий итератор.
     *
     * @return Текущий итератор.
     *
     * @throws IteratorPositionChangingException исключение изменения позиции итератора (текущей позиции).
     * @throws IteratorAuxiliaryException вспомогательное исключение итератора.
     * @since 1.0.0-RC1
     */
    @Contract("-> this")
    default @NotNull ImmutableIterator<T> last() throws IteratorPositionChangingException, IteratorAuxiliaryException {
        final @NotNull var lock = lock();
        try {
            lock.lockInterruptibly();
            if (!hasLast()) throw new IteratorPositionChangingException();
            if (ending()) return previous();
            while (hasNext()) next();
            return this;
        } catch (final @NotNull IteratorPositionChangingException failure) {
            throw failure;
        } catch (final @NotNull IteratorException failure) {
            throw new IteratorPositionChangingException(failure);
        } catch (final @NotNull Exception failure) {
            throw new IteratorAuxiliaryException(failure);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Изменяет текущую позицию итератора на начальную. Возвращает текущий итератор.
     *
     * @return Текущий итератор.
     *
     * @throws IteratorPositionChangingException исключение изменения позиции итератора (текущей позиции).
     * @throws IteratorAuxiliaryException вспомогательное исключение итератора.
     * @since 1.0.0-RC1
     */
    @Contract("-> this")
    default @NotNull ImmutableIterator<T> start() throws IteratorPositionChangingException, IteratorAuxiliaryException {
        final @NotNull var lock = lock();
        try {
            lock.lockInterruptibly();
            while (!starting()) previous();
            return this;
        } catch (final @NotNull IteratorPositionChangingException failure) {
            throw failure;
        } catch (final @NotNull IteratorException failure) {
            throw new IteratorPositionChangingException(failure);
        } catch (final @NotNull Exception failure) {
            throw new IteratorAuxiliaryException(failure);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Изменяет текущую позицию итератора на конечную. Возвращает текущий итератор.
     *
     * @return Текущий итератор.
     *
     * @throws IteratorPositionChangingException исключение изменения позиции итератора (текущей позиции).
     * @throws IteratorAuxiliaryException вспомогательное исключение итератора.
     * @since 1.0.0-RC1
     */
    @Contract("-> this")
    default @NotNull ImmutableIterator<T> end() throws IteratorPositionChangingException, IteratorAuxiliaryException {
        final @NotNull var lock = lock();
        try {
            lock.lockInterruptibly();
            while (!ending()) next();
            return this;
        } catch (final @NotNull IteratorPositionChangingException failure) {
            throw failure;
        } catch (final @NotNull IteratorException failure) {
            throw new IteratorPositionChangingException(failure);
        } catch (final @NotNull Exception failure) {
            throw new IteratorAuxiliaryException(failure);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Создаёт {@linkplain ExternalIterator внешний итератор} на основе текущего.
     *
     * @return {@linkplain ExternalIterator Внешний итератор} на основе текущего.
     *
     * @throws IteratorAuxiliaryException вспомогательное исключение итератора.
     * @see ExternalIterator#immutable(ImmutableIterator)
     * @since 1.0.0-RC1
     */
    @Contract("-> new")
    default @NotNull ExternalIterator<T> external() throws IteratorAuxiliaryException {
        final @NotNull var lock = lock();
        try {
            lock.lockInterruptibly();
            return ExternalIterator.immutable(this);
        } catch (final @NotNull Exception failure) {
            throw new IteratorAuxiliaryException(failure);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Возвращает {@linkplain Lock инструмент для управления доступом}.
     *
     * @return {@linkplain Lock Инструмент для управления доступом}.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> const")
    @NotNull Lock lock();

    /**
     * Возвращает параметр существования текущего элемента.
     *
     * @return Параметр существования текущего элемента.
     *
     * @since 1.0.0-RC1
     */
    boolean hasCurrent();

    /**
     * Возвращает параметр существования кэшированного элемента.
     *
     * @return Параметр существования кэшированного элемента.
     *
     * @since 1.0.0-RC1
     */
    boolean hasCached();

    /**
     * Возвращает параметр существования следующего элемента.
     *
     * @return Параметр существования следующего элемента.
     *
     * @since 1.0.0-RC1
     */
    boolean hasNext();

    /**
     * Возвращает параметр существования предыдущего элемента.
     *
     * @return Параметр существования предыдущего элемента.
     *
     * @since 1.0.0-RC1
     */
    boolean hasPrevious();

    /**
     * Возвращает параметр существования первого элемента.
     *
     * @return Параметр существования первого элемента.
     *
     * @since 1.0.0-RC1
     */
    boolean hasFirst();

    /**
     * Возвращает параметр существования центрального элемента.
     *
     * @return Параметр существования центрального элемента.
     *
     * @since 1.0.0-RC1
     */
    boolean hasCentral();

    /**
     * Возвращает параметр существования последнего элемента.
     *
     * @return Параметр существования последнего элемента.
     *
     * @since 1.0.0-RC1
     */
    boolean hasLast();

    /**
     * Возвращает параметр нахождения в начальной позиции.
     *
     * @return Параметр нахождения в начальной позиции.
     *
     * @since 1.0.0-RC1
     */
    boolean starting();

    /**
     * Возвращает параметр нахождения в итерируемой позиции.
     *
     * @return Параметр нахождения в итерируемой позиции.
     *
     * @since 1.0.0-RC1
     */
    boolean iterating();

    /**
     * Возвращает параметр нахождения в конечной позиции.
     *
     * @return Параметр нахождения в конечной позиции.
     *
     * @since 1.0.0-RC1
     */
    boolean ending();

    /**
     * Возвращает параметр поддержки нулевых элементов.
     *
     * @return Параметр поддержки нулевых элементов.
     *
     * @since 1.0.0-RC1
     */
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
