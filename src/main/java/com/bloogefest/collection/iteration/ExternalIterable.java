/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.bloogefest.collection.iteration;

import com.bloogefest.annotation.analysis.Contract;
import com.bloogefest.annotation.analysis.NotNull;
import com.bloogefest.collection.iteration.iterator.ExternalIterator;
import com.bloogefest.common.function.Supplier;
import com.bloogefest.common.validation.NullException;
import com.bloogefest.common.validation.Validator;

/**
 * Внешнее итерируемое.
 *
 * @since 1.0.0-RC1
 */
public interface ExternalIterable<T> extends Iterable<T> {

    /**
     * Создаёт и возвращает {@linkplain ExternalIterable внешнее итерируемое} на основе переданного
     * {@linkplain Supplier поставщика} {@linkplain ExternalIterator внешнего итератора}.
     *
     * @param supplier {@linkplain Supplier поставщик} {@linkplain ExternalIterator внешнего итератора}.
     *
     * @return {@linkplain ExternalIterable Внешнее итерируемое}.
     *
     * @throws NullException исключение проверки нулевого объекта (переданного {@linkplain Supplier поставщика}
     * {@linkplain ExternalIterator внешнего итератора}).
     * @since 1.0.0-RC1
     */
    @Contract("-> new")
    static <T> @NotNull ExternalIterable<T> of(
            final @NotNull Supplier<ExternalIterator<T>> supplier) throws NullException {
        Validator.notNull(supplier, "The supplier");
        return () -> Validator.notNull(supplier.supply(), "The iterator");
    }

}
