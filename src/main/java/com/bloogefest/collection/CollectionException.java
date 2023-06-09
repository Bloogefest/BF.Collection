/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.bloogefest.collection;

import com.bloogefest.annotation.analysis.Contract;
import com.bloogefest.annotation.analysis.NotNls;
import com.bloogefest.annotation.analysis.Null;
import com.bloogefest.annotation.analysis.Nullable;
import com.bloogefest.common.SoftException;

/**
 * Исключение коллекции.
 *
 * @since 1.0.0-RC1
 */
public class CollectionException extends SoftException {

    /**
     * Сообщение по умолчанию.
     *
     * @since 1.0.0-RC1
     */
    public static final @NotNls @Null String DEFAULT_MESSAGE = null;

    /**
     * Создаёт исключение коллекции по умолчанию.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> new")
    public CollectionException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Создаёт исключение коллекции на основе сообщения.
     *
     * @param message сообщение.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_ -> new")
    public CollectionException(final @NotNls @Nullable String message) {
        super(message);
    }

    /**
     * Создаёт исключение коллекции на основе причины.
     *
     * @param cause причина.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_ -> new")
    public CollectionException(final @Nullable Throwable cause) {
        super(cause);
    }

    /**
     * Создаёт исключение коллекции на основе сообщения и причины.
     *
     * @param message сообщение.
     * @param cause причина.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_, _ -> new")
    public CollectionException(final @NotNls @Nullable String message, final @Nullable Throwable cause) {
        super(message, cause);
    }

    /**
     * Создаёт исключение коллекции на основе параметров подавления и трассировки стека.
     *
     * @param suppression параметр подавления.
     * @param writable параметр трассировки стека.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_, _ -> new")
    public CollectionException(final boolean suppression, final boolean writable) {
        super(suppression, writable);
    }

    /**
     * Создаёт исключение коллекции на основе сообщения, параметров подавления и трассировки стека.
     *
     * @param message сообщение.
     * @param suppression параметр подавления.
     * @param writable параметр трассировки стека.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_, _, _ -> new")
    public CollectionException(final @NotNls @Nullable String message, final boolean suppression,
                               final boolean writable) {
        super(message, suppression, writable);
    }

    /**
     * Создаёт исключение коллекции на основе причины, параметров подавления и трассировки стека.
     *
     * @param cause причина.
     * @param suppression параметр подавления.
     * @param writable параметр трассировки стека.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_, _, _ -> new")
    public CollectionException(final @Nullable Throwable cause, final boolean suppression, final boolean writable) {
        super(cause, suppression, writable);
    }

    /**
     * Создаёт исключение коллекции на основе сообщения, причины, параметров подавления и трассировки стека.
     *
     * @param message сообщение.
     * @param cause причина.
     * @param suppression параметр подавления.
     * @param writable параметр трассировки стека.
     *
     * @since 1.0.0-RC1
     */
    @Contract("_, _, _, _ -> new")
    protected CollectionException(final @NotNls @Nullable String message, final @Nullable Throwable cause,
                                  final boolean suppression, final boolean writable) {
        super(message, cause, suppression, writable);
    }

}
