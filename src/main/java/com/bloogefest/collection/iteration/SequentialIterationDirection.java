/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.bloogefest.collection.iteration;

import com.bloogefest.annotation.analysis.Magical;
import com.bloogefest.annotation.analysis.NotNull;

/**
 * Направление последовательной итерации.
 *
 * @since 1.0.0-RC1
 */
public enum SequentialIterationDirection {

    /**
     * В сторону последнего элемента, начиная с первого элемента.
     *
     * @since 1.0.0-RC1
     */
    FORWARD,

    /**
     * В сторону первого элемента, начиная с последнего элемента.
     *
     * @since 1.0.0-RC1
     */
    BACKWARD;

    /**
     * Направление последовательной итерации по умолчанию.
     *
     * @see #FORWARD
     * @since 1.0.0-RC1
     */
    public static final @NotNull SequentialIterationDirection DEFAULT = FORWARD;

    /**
     * Порядковый номер
     * {@linkplain #FORWARD направления последовательной итерации в сторону последнего элемента, начиная с первого
     * элемента}.
     *
     * @since 1.0.0-RC1
     */
    public static final @Magical int FORWARD_ORDINAL = 0;

    /**
     * Порядковый номер
     * {@linkplain #BACKWARD направления последовательной итерации в сторону первого элемента, начиная с последнего
     * элемента}.
     *
     * @since 1.0.0-RC1
     */
    public static final @Magical int BACKWARD_ORDINAL = 1;

    /**
     * Порядковый номер {@linkplain #DEFAULT направления последовательной итерации по умолчанию}.
     *
     * @since 1.0.0-RC1
     */
    public static final @Magical int DEFAULT_ORDINAL = 0;

}
