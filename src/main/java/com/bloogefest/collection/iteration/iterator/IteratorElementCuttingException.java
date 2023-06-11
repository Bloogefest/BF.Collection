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
 * Исключение вырезания элемента итератора.
 *
 * @since 1.0.0-RC1
 */
public class IteratorElementCuttingException extends IteratorElementException {

    /**
     * Сообщение по умолчанию.
     *
     * @since 1.0.0-RC1
     */
    public static final @NotNls @NotNull String DEFAULT_MESSAGE = "Failed to cut iterator element";

    /**
     * Шаблонное сообщение.
     *
     * @since 1.0.0-RC1
     */
    public static final @NotNls @NotNull String TEMPLATE_MESSAGE = "Failed to cut %s";

    /**
     * Создаёт исключение вырезания элемента итератора по умолчанию.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> new")
    public IteratorElementCuttingException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Создаёт исключение вырезания элемента итератора на основе сообщения.
     *
     * @param message сообщение.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_ -> new")
    public IteratorElementCuttingException(final @NotNls @Nullable String message) {
        super(message);
    }

    /**
     * Создаёт исключение вырезания элемента итератора на основе причины.
     *
     * @param cause причина.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_ -> new")
    public IteratorElementCuttingException(final @Nullable Throwable cause) {
        super(cause);
    }

    /**
     * Создаёт исключение вырезания элемента итератора на основе сообщения и причины.
     *
     * @param message сообщение.
     * @param cause причина.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_, _ -> new")
    public IteratorElementCuttingException(final @NotNls @Nullable String message, final @Nullable Throwable cause) {
        super(message, cause);
    }

    /**
     * Создаёт исключение вырезания элемента итератора на основе параметров подавления и трассировки стека.
     *
     * @param suppression параметр подавления.
     * @param writable параметр трассировки стека.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_, _ -> new")
    public IteratorElementCuttingException(final boolean suppression, final boolean writable) {
        super(suppression, writable);
    }

    /**
     * Создаёт исключение вырезания элемента итератора на основе сообщения, параметров подавления и трассировки
     * стека.
     *
     * @param message сообщение.
     * @param suppression параметр подавления.
     * @param writable параметр трассировки стека.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_, _, _ -> new")
    public IteratorElementCuttingException(final @NotNls @Nullable String message, final boolean suppression,
                                           final boolean writable) {
        super(message, suppression, writable);
    }

    /**
     * Создаёт исключение вырезания элемента итератора на основе причины, параметров подавления и трассировки стека.
     *
     * @param cause причина.
     * @param suppression параметр подавления.
     * @param writable параметр трассировки стека.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_, _, _ -> new")
    public IteratorElementCuttingException(final @Nullable Throwable cause, final boolean suppression,
                                           final boolean writable) {
        super(cause, suppression, writable);
    }

    /**
     * Создаёт исключение вырезания элемента итератора на основе сообщения, причины, параметров подавления и
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
    protected IteratorElementCuttingException(final @NotNls @Nullable String message, final @Nullable Throwable cause,
                                              final boolean suppression, final boolean writable) {
        super(message, cause, suppression, writable);
    }

}
