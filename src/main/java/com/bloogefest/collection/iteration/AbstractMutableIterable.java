/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.bloogefest.collection.iteration;

import com.bloogefest.annotation.analysis.Contract;
import com.bloogefest.annotation.analysis.NotNull;
import com.bloogefest.common.validation.NullException;

import java.util.concurrent.locks.Lock;

/**
 * Абстрактное изменяемое итерируемое.
 *
 * @see MutableIterable
 * @since 1.0.0-RC1
 */
public abstract class AbstractMutableIterable<T> extends AbstractImmutableIterable<T> implements MutableIterable<T> {

    /**
     * Создаёт {@linkplain AbstractMutableIterable абстрактное изменяемое итерируемое} на основе переданного
     * {@linkplain Lock инструмента для управления доступом} к текущему
     * {@linkplain AbstractMutableIterable абстрактному изменяемому итерируемому} и параметра поддержки нулевых
     * элементов.
     *
     * @param lock {@linkplain Lock инструмент для управления доступом} к текущему
     * {@linkplain AbstractMutableIterable абстрактному изменяемому итерируемому}.
     * @param nullable параметр поддержки нулевых элементов.
     *
     * @throws NullException исключение нулевого объекта (переданного
     * {@linkplain Lock инструмента для управления доступом} к текущему
     * {@linkplain AbstractMutableIterable абстрактному изменяемому итерируемому}).
     * @since 1.0.0-RC1
     */
    @Contract("-> new")
    public AbstractMutableIterable(final @NotNull Lock lock, final boolean nullable) throws NullException {
        super(lock, nullable);
    }

    /**
     * Возвращает {@linkplain Lock инструмент для управления доступом} к текущему
     * {@linkplain AbstractMutableIterable абстрактному изменяемому итерируемому}.
     *
     * @return {@linkplain Lock Инструмент для управления доступом} к текущему
     * {@linkplain AbstractMutableIterable абстрактному изменяемому итерируемому}.
     *
     * @since 1.0.0-RC1
     */
    @Override
    @Contract("-> const")
    public @NotNull Lock lock() {
        return super.lock();
    }

}
