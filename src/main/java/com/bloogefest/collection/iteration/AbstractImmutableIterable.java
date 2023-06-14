/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.bloogefest.collection.iteration;

import com.bloogefest.annotation.analysis.Contract;
import com.bloogefest.annotation.analysis.NotNull;
import com.bloogefest.common.validation.NullException;
import com.bloogefest.common.validation.Validator;

import java.util.concurrent.locks.Lock;

/**
 * Абстрактное неизменяемое итерируемое.
 *
 * @see ImmutableIterable
 * @since 1.0.0-RC1
 */
public abstract class AbstractImmutableIterable<T> implements ImmutableIterable<T> {

    /**
     * {@linkplain Lock Инструмент для управления доступом} к текущему
     * {@linkplain AbstractImmutableIterable абстрактному неизменяемому итерируемому}.
     *
     * @see #lock()
     * @since 1.0.0-RC1
     */
    private final @NotNull Lock lock;

    /**
     * Параметр поддержки нулевых элементов.
     *
     * @see #nullable()
     * @since 1.0.0-RC1
     */
    private final boolean nullable;

    /**
     * Создаёт {@linkplain AbstractImmutableIterable абстрактное неизменяемое итерируемое} на основе переданного
     * {@linkplain Lock инструмента для управления доступом} к текущему
     * {@linkplain AbstractImmutableIterable абстрактному неизменяемому итерируемому} и параметра поддержки нулевых
     * элементов.
     *
     * @param lock {@linkplain Lock инструмент для управления доступом} к текущему
     * {@linkplain AbstractImmutableIterable абстрактному неизменяемому итерируемому}.
     * @param nullable параметр поддержки нулевых элементов.
     *
     * @throws NullException исключение нулевого объекта (переданного
     * {@linkplain Lock инструмента для управления доступом} к текущему
     * {@linkplain AbstractImmutableIterable абстрактному неизменяемому итерируемому}).
     * @since 1.0.0-RC1
     */
    @Contract("-> new")
    public AbstractImmutableIterable(final @NotNull Lock lock, final boolean nullable) throws NullException {
        this.lock = Validator.notNull(lock, "The lock");
        this.nullable = nullable;
    }

    /**
     * Возвращает {@linkplain Lock инструмент для управления доступом} к текущему
     * {@linkplain AbstractImmutableIterable абстрактному неизменяемому итерируемому}.
     *
     * @return {@linkplain Lock Инструмент для управления доступом} к текущему
     * {@linkplain AbstractImmutableIterable абстрактному неизменяемому итерируемому}.
     *
     * @see #lock
     * @since 1.0.0-RC1
     */
    @Override
    @Contract("-> const")
    public @NotNull Lock lock() {
        return lock;
    }

    /**
     * Возвращает параметр поддержки нулевых элементов.
     *
     * @return Параметр поддержки нулевых элементов.
     *
     * @see #nullable
     * @since 1.0.0-RC1
     */
    @Override
    @Contract("-> const")
    public boolean nullable() {
        return nullable;
    }

}
