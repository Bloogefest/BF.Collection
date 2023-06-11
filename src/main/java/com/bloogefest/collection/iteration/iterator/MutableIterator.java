/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.bloogefest.collection.iteration.iterator;

import com.bloogefest.annotation.analysis.Contract;
import com.bloogefest.annotation.analysis.NotNull;
import com.bloogefest.annotation.analysis.Nullable;
import com.bloogefest.common.validation.NullException;

/**
 * Итератор изменяемого.
 *
 * @param <T> тип элемента.
 *
 * @since 1.0.0-RC1
 */
public interface MutableIterator<T> extends ImmutableIterator<T> {

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
    @Override
    @Contract("-> this")
    default @NotNull MutableIterator<T> cache() throws IteratorElementGettingException, IteratorElementCachingException {
        ImmutableIterator.super.cache();
        return this;
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
    @Override
    @Contract("_ -> this")
    @NotNull MutableIterator<T> cache(final @Nullable T element) throws NullException, IteratorElementCachingException;

    /**
     * Если {@linkplain #hasCached() параметр существования кэшированного элемента} ложный, генерирует
     * {@linkplain IteratorElementGettingException исключение получения элемента итератора} (кэшированного элемента).
     * Если {@linkplain #mutable() параметр изменяемости} и
     * {@linkplain #hasCurrent() параметр существования текущего элемента} истинные, изменяет текущий элемент на
     * кэшированный, в противном случае генерирует
     * {@linkplain IteratorElementChangingException исключение изменения элемента итератора на другой} (текущего
     * элемента на кэшированный). Возвращает текущий итератор.
     *
     * @return Текущий итератор.
     *
     * @throws IteratorElementChangingException исключение изменения элемента итератора на другой (текущего элемента на
     * кэшированный).
     * @throws IteratorAuxiliaryException вспомогательное исключение итератора. элементов).
     * @since 1.0.0-RC1
     */
    @Contract("-> this")
    default @NotNull MutableIterator<T> change() throws IteratorElementChangingException, IteratorAuxiliaryException {
        final @NotNull var lock = lock();
        try {
            lock.lockInterruptibly();
            return change(cached());
        } catch (final @NotNull IteratorElementChangingException failure) {
            throw failure;
        } catch (final @NotNull IteratorException failure) {
            throw new IteratorElementChangingException(failure);
        } catch (final @NotNull Exception failure) {
            throw new IteratorAuxiliaryException(failure);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Если переданный элемент нулевой и {@linkplain #nullable() параметр поддержки нулевых элементов} ложный,
     * генерирует {@linkplain NullException исключение проверки нулевого объекта} (переданного элемента). Если
     * {@linkplain #mutable() параметр изменяемости} и
     * {@linkplain #hasCurrent() параметр существования текущего элемента} истинные, изменяет текущий элемент на
     * переданный, в противном случае генерирует
     * {@linkplain IteratorElementChangingException исключение изменения элемента итератора на другой} (текущего
     * элемента на переданный). Возвращает текущий итератор.
     *
     * @param element элемент.
     *
     * @return Текущий итератор.
     *
     * @throws NullException исключение проверки нулевого объекта (переданного элемента).
     * @throws IteratorElementChangingException исключение изменения элемента итератора на другой (текущего элемента на
     * переданный).
     * @since 1.0.0-RC1
     */
    @Contract("_ -> this")
    @NotNull MutableIterator<T> change(
            final @Nullable T element) throws NullException, IteratorElementChangingException;

    /**
     * Если {@linkplain #mutable() параметр изменяемости} и
     * {@linkplain #hasCurrent() параметр существования текущего элемента} истинные, удаляет текущий элемент, в
     * противном случае генерирует {@linkplain IteratorElementDeletingException исключение удаления элемента итератора}
     * (текущего элемента). Возвращает текущий итератор.
     *
     * @return Текущий итератор.
     *
     * @throws IteratorElementDeletingException исключение удаления элемента итератора (текущего элемента).
     * @since 1.0.0-RC1
     */
    @Contract("-> this")
    @NotNull MutableIterator<T> delete() throws IteratorElementDeletingException;

    /**
     * Если {@linkplain #mutable() параметр изменяемости},
     * {@linkplain #hasCurrent() параметр существования текущего элемента} и
     * {@linkplain #hasCached() параметр существования кэшированного элемента} истинные, вставляет кэшированный элемент
     * перед текущим, в противном случае генерирует
     * {@linkplain IteratorElementPastingException исключение вставки элемента итератора} (кэшированного элемента).
     * Возвращает текущий итератор.
     *
     * @return Текущий итератор.
     *
     * @throws IteratorElementPastingException исключение вставки элемента итератора (кэшированного элемента).
     * @throws IteratorAuxiliaryException вспомогательное исключение итератора. элементов).
     * @since 1.0.0-RC1
     */
    @Contract("-> this")
    default @NotNull MutableIterator<T> paste() throws IteratorElementPastingException, IteratorAuxiliaryException {
        final @NotNull var lock = lock();
        try {
            lock.lockInterruptibly();
            return paste(cached());
        } catch (final @NotNull IteratorElementPastingException failure) {
            throw failure;
        } catch (final @NotNull IteratorException failure) {
            throw new IteratorElementPastingException(failure);
        } catch (final @NotNull Exception failure) {
            throw new IteratorAuxiliaryException(failure);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Если переданный элемент нулевой и {@linkplain #nullable() параметр поддержки нулевых элементов} ложный,
     * генерирует {@linkplain NullException исключение проверки нулевого объекта} (переданного элемента). Если
     * {@linkplain #mutable() параметр изменяемости} и
     * {@linkplain #hasCurrent() параметр существования текущего элемента} истинные, вставляет переданный элемент перед
     * текущим, в противном случае генерирует
     * {@linkplain IteratorElementPastingException исключение вставки элемента итератора} (переданного элемента).
     * Возвращает текущий итератор.
     *
     * @param element элемент.
     *
     * @return Текущий итератор.
     *
     * @throws NullException исключение проверки нулевого объекта (переданного элемента).
     * @throws IteratorElementPastingException исключение вставки элемента итератора (переданного элемента).
     * @since 1.0.0-RC1
     */
    @Contract("_ -> this")
    @NotNull MutableIterator<T> paste(final @Nullable T element) throws NullException, IteratorElementPastingException;

    /**
     * Если {@linkplain #mutable() параметр изменяемости},
     * {@linkplain #hasCurrent() параметр существования текущего элемента} и
     * {@linkplain #hasCached() параметр существования кэшированного элемента} истинные, меняет местами текущий элемент
     * с кэшированным, в противном случае генерирует
     * {@linkplain IteratorElementSwappingException исключение смены местами элементов итератора} (текущего элемента с
     * кэшированным). Возвращает текущий итератор.
     *
     * @return Текущий итератор.
     *
     * @throws IteratorElementSwappingException исключение смены местами элементов итератора (текущего элемента с
     * кэшированным).
     * @throws IteratorAuxiliaryException вспомогательное исключение итератора. элементов).
     * @since 1.0.0-RC1
     */
    @Contract("-> this")
    default @NotNull MutableIterator<T> swap() throws IteratorElementSwappingException, IteratorAuxiliaryException {
        final @NotNull var lock = lock();
        try {
            lock.lockInterruptibly();
            final @NotNull var element = current();
            paste();
            cache(element);
            return this;
        } catch (final @NotNull IteratorException failure) {
            throw new IteratorElementSwappingException(failure);
        } catch (final @NotNull Exception failure) {
            throw new IteratorAuxiliaryException(failure);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Если {@linkplain #mutable() параметр изменяемости} и
     * {@linkplain #hasCurrent() параметр существования текущего элемента} истинные, кэширует и удаляет текущий элемент,
     * в противном случае генерирует
     * {@linkplain IteratorElementCuttingException исключение вырезания элемента итератора} (текущего элемента).
     * Возвращает текущий итератор.
     *
     * @return Текущий итератор.
     *
     * @throws IteratorElementCuttingException исключение вырезания элемента итератора (текущего элемента).
     * @throws IteratorAuxiliaryException вспомогательное исключение итератора.
     * @since 1.0.0-RC1
     */
    @Contract("-> this")
    default @NotNull MutableIterator<T> cut() throws IteratorElementCuttingException, IteratorAuxiliaryException {
        final @NotNull var lock = lock();
        try {
            lock.lockInterruptibly();
            cache();
            delete();
            return this;
        } catch (final @NotNull IteratorException failure) {
            throw new IteratorElementCuttingException(failure);
        } catch (final @NotNull Exception failure) {
            throw new IteratorAuxiliaryException(failure);
        } finally {
            lock.unlock();
        }
    }

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
    @Override
    @Contract("-> this")
    @NotNull MutableIterator<T> next() throws IteratorPositionChangingException;

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
    @Override
    @Contract("-> this")
    @NotNull MutableIterator<T> previous() throws IteratorPositionChangingException;

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
    @Override
    @Contract("-> this")
    default @NotNull MutableIterator<T> first() throws IteratorPositionChangingException, IteratorAuxiliaryException {
        ImmutableIterator.super.first();
        return this;
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
    @Override
    @Contract("-> this")
    default @NotNull MutableIterator<T> last() throws IteratorPositionChangingException, IteratorAuxiliaryException {
        ImmutableIterator.super.last();
        return this;
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
    @Override
    @Contract("-> this")
    default @NotNull ImmutableIterator<T> start() throws IteratorPositionChangingException, IteratorAuxiliaryException {
        ImmutableIterator.super.start();
        return this;
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
    @Override
    @Contract("-> this")
    default @NotNull ImmutableIterator<T> end() throws IteratorPositionChangingException, IteratorAuxiliaryException {
        ImmutableIterator.super.end();
        return this;
    }

    /**
     * Создаёт {@linkplain ExternalIterator внешний итератор} на основе текущего.
     *
     * @return {@linkplain ExternalIterator Внешний итератор} на основе текущего.
     *
     * @throws IteratorAuxiliaryException вспомогательное исключение итератора.
     * @see ExternalIterator#mutable(MutableIterator)
     * @since 1.0.0-RC1
     */
    @Override
    @Contract("-> new")
    default @NotNull ExternalIterator<T> external() throws IteratorAuxiliaryException {
        final @NotNull var lock = lock();
        try {
            lock.lockInterruptibly();
            return ExternalIterator.mutable(this);
        } catch (final @NotNull Exception failure) {
            throw new IteratorAuxiliaryException(failure);
        } finally {
            lock.unlock();
        }
    }

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
