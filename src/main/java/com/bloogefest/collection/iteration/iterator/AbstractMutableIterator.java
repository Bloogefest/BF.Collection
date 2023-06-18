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

import java.util.concurrent.locks.Lock;

/**
 * Абстрактный итератор изменяемого.
 *
 * @see MutableIterator
 * @since 1.0.0-RC1
 */
public abstract class AbstractMutableIterator<T> extends AbstractImmutableIterator<T> implements MutableIterator<T> {

    /**
     * Создаёт абстрактный итератор изменяемого на основе переданного инструмента для управления доступом и параметра
     * поддержки нулевых элементов.
     *
     * @param lock инструмент для управления доступом.
     * @param nullable параметр поддержки нулевых элементов.
     *
     * @throws NullException исключение проверки нулевого объекта (переданного инструмента для управления доступом).
     * @since 1.0.0-RC1
     */
    @Contract("!null, _ -> new; _, _ -> fail")
    public AbstractMutableIterator(final @NotNull Lock lock, final boolean nullable) throws NullException {
        super(lock, nullable);
    }

}
