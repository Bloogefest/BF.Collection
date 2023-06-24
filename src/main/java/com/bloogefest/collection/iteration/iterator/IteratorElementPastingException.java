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
 * Исключение вставки элемента итератора.
 *
 * @since 1.0.0-RC1
 */
public class IteratorElementPastingException extends IteratorElementException {

    /**
     * Сообщение по умолчанию.
     *
     * @since 1.0.0-RC1
     */
    public static final @NotNls @NotNull String DEFAULT_MESSAGE = "Failed to paste the iterator element";

    /**
     * Шаблонное сообщение.
     *
     * @since 1.0.0-RC1
     */
    public static final @NotNls @NotNull String TEMPLATE_MESSAGE = "Failed to paste %s";

    /**
     * Создаёт исключение вставки элемента итератора на основе {@linkplain #DEFAULT_MESSAGE сообщения},
     * {@linkplain #DEFAULT_CAUSE причины}, {@linkplain #DEFAULT_SUPPRESSION параметров подавления} и
     * {@linkplain #DEFAULT_WRITABLE трассировки стека по умолчанию}.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> new")
    public IteratorElementPastingException() {
        this(DEFAULT_MESSAGE, DEFAULT_CAUSE, DEFAULT_SUPPRESSION, DEFAULT_WRITABLE);
    }

    /**
     * Создаёт исключение вставки элемента итератора на основе переданного сообщения,
     * {@linkplain #DEFAULT_CAUSE причины}, {@linkplain #DEFAULT_SUPPRESSION параметров подавления} и
     * {@linkplain #DEFAULT_WRITABLE трассировки стека по умолчанию}.
     *
     * @param message сообщение.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_ -> new")
    public IteratorElementPastingException(final @NotNls @Nullable String message) {
        this(message, DEFAULT_CAUSE, DEFAULT_SUPPRESSION, DEFAULT_WRITABLE);
    }

    /**
     * Создаёт исключение вставки элемента итератора на основе {@linkplain #DEFAULT_MESSAGE сообщения по умолчанию},
     * переданной причины, {@linkplain #DEFAULT_SUPPRESSION параметров подавления} и
     * {@linkplain #DEFAULT_WRITABLE трассировки стека по умолчанию}.
     *
     * @param cause причина.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_ -> new")
    public IteratorElementPastingException(final @Nullable Throwable cause) {
        this(DEFAULT_MESSAGE, cause, DEFAULT_SUPPRESSION, DEFAULT_WRITABLE);
    }

    /**
     * Создаёт исключение вставки элемента итератора на основе переданного сообщения и причины,
     * {@linkplain #DEFAULT_SUPPRESSION параметров подавления} и
     * {@linkplain #DEFAULT_WRITABLE трассировки стека по умолчанию}.
     *
     * @param message сообщение.
     * @param cause причина.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_, _ -> new")
    public IteratorElementPastingException(final @NotNls @Nullable String message, final @Nullable Throwable cause) {
        this(message, cause, DEFAULT_SUPPRESSION, DEFAULT_WRITABLE);
    }

    /**
     * Создаёт исключение вставки элемента итератора на основе {@linkplain #DEFAULT_MESSAGE сообщения} и
     * {@linkplain #DEFAULT_CAUSE причины по умолчанию}, переданных параметров подавления и трассировки стека.
     *
     * @param suppression параметр подавления.
     * @param writable параметр трассировки стека.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_, _ -> new")
    public IteratorElementPastingException(final boolean suppression, final boolean writable) {
        this(DEFAULT_MESSAGE, DEFAULT_CAUSE, suppression, writable);
    }

    /**
     * Создаёт исключение вставки элемента итератора на основе переданного сообщения,
     * {@linkplain #DEFAULT_CAUSE причины по умолчанию}, переданных параметров подавления и трассировки стека.
     *
     * @param message сообщение.
     * @param suppression параметр подавления.
     * @param writable параметр трассировки стека.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_, _, _ -> new")
    public IteratorElementPastingException(final @NotNls @Nullable String message, final boolean suppression,
                                           final boolean writable) {
        this(message, DEFAULT_CAUSE, suppression, writable);
    }

    /**
     * Создаёт исключение вставки элемента итератора на основе {@linkplain #DEFAULT_MESSAGE сообщения по умолчанию},
     * переданной причины, параметров подавления и трассировки стека.
     *
     * @param cause причина.
     * @param suppression параметр подавления.
     * @param writable параметр трассировки стека.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_, _, _ -> new")
    public IteratorElementPastingException(final @Nullable Throwable cause, final boolean suppression,
                                           final boolean writable) {
        this(DEFAULT_MESSAGE, cause, suppression, writable);
    }

    /**
     * Создаёт исключение вставки элемента итератора на основе переданного сообщения, причины, параметров подавления и
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
    protected IteratorElementPastingException(final @NotNls @Nullable String message, final @Nullable Throwable cause,
                                              final boolean suppression, final boolean writable) {
        super(message, cause, suppression, writable);
    }

}
