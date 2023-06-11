/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.bloogefest.collection.iteration.iterator;

import com.bloogefest.annotation.analysis.Contract;
import com.bloogefest.annotation.analysis.NotNls;
import com.bloogefest.annotation.analysis.NotNull;
import com.bloogefest.annotation.analysis.Nullable;

/**
 * Исключение смены местами элементов итератора.
 *
 * @since 1.0.0-RC1
 */
public class IteratorElementSwappingException extends IteratorElementException {

    /**
     * Сообщение по умолчанию.
     *
     * @since 1.0.0-RC1
     */
    public static final @NotNls @NotNull String DEFAULT_MESSAGE = "Failed to swap iterator elements";

    /**
     * Шаблонное сообщение.
     *
     * @since 1.0.0-RC1
     */
    public static final @NotNls @NotNull String TEMPLATE_MESSAGE = "Failed to swap %s";

    /**
     * Создаёт исключение смены местами элементов итератора по умолчанию.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> new")
    public IteratorElementSwappingException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Создаёт исключение смены местами элементов итератора на основе сообщения.
     *
     * @param message сообщение.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_ -> new")
    public IteratorElementSwappingException(final @NotNls @Nullable String message) {
        super(message);
    }

    /**
     * Создаёт исключение смены местами элементов итератора на основе причины.
     *
     * @param cause причина.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_ -> new")
    public IteratorElementSwappingException(final @Nullable Throwable cause) {
        super(cause);
    }

    /**
     * Создаёт исключение смены местами элементов итератора на основе сообщения и причины.
     *
     * @param message сообщение.
     * @param cause причина.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_, _ -> new")
    public IteratorElementSwappingException(final @NotNls @Nullable String message, final @Nullable Throwable cause) {
        super(message, cause);
    }

    /**
     * Создаёт исключение смены местами элементов итератора на основе параметров подавления и трассировки стека.
     *
     * @param suppression параметр подавления.
     * @param writable параметр трассировки стека.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_, _ -> new")
    public IteratorElementSwappingException(final boolean suppression, final boolean writable) {
        super(suppression, writable);
    }

    /**
     * Создаёт исключение смены местами элементов итератора на основе сообщения, параметров подавления и трассировки
     * стека.
     *
     * @param message сообщение.
     * @param suppression параметр подавления.
     * @param writable параметр трассировки стека.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_, _, _ -> new")
    public IteratorElementSwappingException(final @NotNls @Nullable String message, final boolean suppression,
                                            final boolean writable) {
        super(message, suppression, writable);
    }

    /**
     * Создаёт исключение смены местами элементов итератора на основе причины, параметров подавления и трассировки
     * стека.
     *
     * @param cause причина.
     * @param suppression параметр подавления.
     * @param writable параметр трассировки стека.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_, _, _ -> new")
    public IteratorElementSwappingException(final @Nullable Throwable cause, final boolean suppression,
                                            final boolean writable) {
        super(cause, suppression, writable);
    }

    /**
     * Создаёт исключение смены местами элементов итератора на основе сообщения, причины, параметров подавления и
     * трассировки стека.
     *
     * @param message сообщение.
     * @param cause причина.
     * @param suppression параметр подавления.
     * @param writable параметр трассировки стека.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_, _, _, _ -> new")
    protected IteratorElementSwappingException(final @NotNls @Nullable String message, final @Nullable Throwable cause,
                                               final boolean suppression, final boolean writable) {
        super(message, cause, suppression, writable);
    }

}
