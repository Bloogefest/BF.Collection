/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.bloogefest.collection.iteration.iterator;

import com.bloogefest.annotation.analysis.Contract;
import com.bloogefest.annotation.analysis.NotNull;
import com.bloogefest.common.validation.NullException;
import com.bloogefest.common.validation.Validator;

import java.util.concurrent.locks.Lock;

/**
 * Абстрактный итератор неизменяемого.
 *
 * @see ImmutableIterator
 * @since 1.0.0-RC1
 */
public abstract class AbstractImmutableIterator<T> implements ImmutableIterator<T> {

    /**
     * Инструмент для управления доступом к текущему
     * {@linkplain AbstractImmutableIterator абстрактному итератору неизменяемого}.
     *
     * @since 1.0.0-RC1
     */
    private final @NotNull Lock lock;

    /**
     * Параметр поддержки нулевых элементов.
     *
     * @since 1.0.0-RC1
     */
    private final boolean nullable;

    /**
     * Создаёт абстрактный итератор неизменяемого на основе переданного инструмента для управления доступом и параметра
     * поддержки нулевых элементов.
     *
     * @param lock инструмент для управления доступом.
     * @param nullable параметр поддержки нулевых элементов.
     *
     * @throws NullException исключение проверки нулевого объекта (переданного инструмента для управления доступом).
     * @since 1.0.0-RC1
     */
    @Contract("!null, _ -> new; _, _ -> fail")
    public AbstractImmutableIterator(final @NotNull Lock lock, final boolean nullable) throws NullException {
        this.lock = Validator.notNull(lock, "The lock");
        this.nullable = nullable;
    }

    /**
     * Возвращает
     * {@linkplain #lock инструмент для управления доступом к текущему абстрактному итератору неизменяемого}.
     *
     * @return {@linkplain #lock Инструмент для управления доступом к текущему абстрактному итератору неизменяемого}.
     *
     * @since 1.0.0-RC1
     */
    @Override
    @Contract("-> const")
    public @NotNull Lock lock() {
        return lock;
    }

    /**
     * Возвращает {@linkplain #nullable параметр поддержки нулевых элементов}.
     *
     * @return {@linkplain #nullable Параметр поддержки нулевых элементов}.
     *
     * @since 1.0.0-RC1
     */
    @Override
    @Contract("-> const")
    public boolean nullable() {
        return nullable;
    }

}
