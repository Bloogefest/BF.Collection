/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.bloogefest.collection;

import com.bloogefest.annotation.analysis.NotNls;
import com.bloogefest.annotation.analysis.Nullable;
import com.bloogefest.common.SoftException;

/**
 * Мягкое исключение коллекций, не требующее обязательного перехвата.
 *
 * @since 1.0.0-RC1
 */
public class CollectionException extends SoftException {

    /**
     * Создаёт экземпляр по умолчанию.
     *
     * @since 1.0.0-RC1
     */
    public CollectionException() {}

    /**
     * Создаёт экземпляр, используя переопределённое сообщение.
     *
     * @param message сообщение.
     *
     * @since 1.0.0-RC1
     */
    public CollectionException(@NotNls @Nullable final String message) {
        super(message);
    }

    /**
     * Создаёт экземпляр, используя переопределённую причину.
     *
     * @param cause причина.
     *
     * @since 1.0.0-RC1
     */
    public CollectionException(@Nullable final Throwable cause) {
        super(cause);
    }

    /**
     * Создаёт экземпляр, используя переопределённое сообщение и причину.
     *
     * @param message сообщение.
     * @param cause причина.
     *
     * @since 1.0.0-RC1
     */
    public CollectionException(@NotNls @Nullable final String message, @Nullable final Throwable cause) {
        super(message, cause);
    }

    /**
     * Создаёт экземпляр, используя переопределённое сообщение, причину, параметр подавления и записи трассировки стека
     * по умолчанию.
     *
     * @param message сообщение.
     * @param cause причина.
     * @param suppression параметр подавления.
     * @param writable параметр записи трассировки стека.
     *
     * @since 1.0.0-RC1
     */
    protected CollectionException(@NotNls @Nullable final String message, @Nullable final Throwable cause,
                                  final boolean suppression, final boolean writable) {
        super(message, cause, suppression, writable);
    }

}
