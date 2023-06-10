/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.bloogefest.collection.iteration;

import com.bloogefest.annotation.analysis.Contract;
import com.bloogefest.annotation.analysis.NotNls;
import com.bloogefest.annotation.analysis.NotNull;
import com.bloogefest.annotation.analysis.Nullable;

/**
 * Исключение последовательной итерации.
 *
 * @since 1.0.0-RC1
 */
public class SequentialIterationException extends IterationException {

    /**
     * Сообщение по умолчанию.
     *
     * @since 1.0.0-RC1
     */
    public static final @NotNls @NotNull String DEFAULT_MESSAGE = "Failed to iterate sequentially";

    /**
     * Шаблонное сообщение.
     *
     * @since 1.0.0-RC1
     */
    public static final @NotNls @NotNull String TEMPLATE_MESSAGE = "Failed to iterate sequentially through %s";

    /**
     * Создаёт исключение последовательной итерации по умолчанию.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> new")
    public SequentialIterationException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Создаёт исключение последовательной итерации на основе сообщения.
     *
     * @param message сообщение.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_ -> new")
    public SequentialIterationException(final @NotNls @Nullable String message) {
        super(message);
    }

    /**
     * Создаёт исключение последовательной итерации на основе причины.
     *
     * @param cause причина.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_ -> new")
    public SequentialIterationException(final @Nullable Throwable cause) {
        super(cause);
    }

    /**
     * Создаёт исключение последовательной итерации на основе сообщения и причины.
     *
     * @param message сообщение.
     * @param cause причина.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_, _ -> new")
    public SequentialIterationException(final @NotNls @Nullable String message, final @Nullable Throwable cause) {
        super(message, cause);
    }

    /**
     * Создаёт исключение последовательной итерации на основе параметров подавления и трассировки стека.
     *
     * @param suppression параметр подавления.
     * @param writable параметр трассировки стека.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_, _ -> new")
    public SequentialIterationException(final boolean suppression, final boolean writable) {
        super(suppression, writable);
    }

    /**
     * Создаёт исключение последовательной итерации на основе сообщения, параметров подавления и трассировки стека.
     *
     * @param message сообщение.
     * @param suppression параметр подавления.
     * @param writable параметр трассировки стека.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_, _, _ -> new")
    public SequentialIterationException(final @NotNls @Nullable String message, final boolean suppression,
                                        final boolean writable) {
        super(message, suppression, writable);
    }

    /**
     * Создаёт исключение последовательной итерации на основе причины, параметров подавления и трассировки стека.
     *
     * @param cause причина.
     * @param suppression параметр подавления.
     * @param writable параметр трассировки стека.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_, _, _ -> new")
    public SequentialIterationException(final @Nullable Throwable cause, final boolean suppression,
                                        final boolean writable) {
        super(cause, suppression, writable);
    }

    /**
     * Создаёт исключение последовательной итерации на основе сообщения, причины, параметров подавления и трассировки
     * стека.
     *
     * @param message сообщение.
     * @param cause причина.
     * @param suppression параметр подавления.
     * @param writable параметр трассировки стека.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_, _, _, _ -> new")
    protected SequentialIterationException(final @NotNls @Nullable String message, final @Nullable Throwable cause,
                                           final boolean suppression, final boolean writable) {
        super(message, cause, suppression, writable);
    }

}
