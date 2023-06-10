/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.bloogefest.collection.iteration;

import com.bloogefest.annotation.analysis.Magical;
import com.bloogefest.annotation.analysis.NotNull;

/**
 * Направление непоследовательной итерации.
 *
 * @since 1.0.0-RC1
 */
public enum NonSequentialIterationDirection {

    /**
     * В сторону центрального элемента, начиная с первого и последнего элементов.
     *
     * @since 1.0.0-RC1
     */
    INSIDE,

    /**
     * В сторону первого и последнего элементов, начиная с центрального элемента.
     *
     * @since 1.0.0-RC1
     */
    OUTSIDE,

    /**
     * Составное направление из других: {@linkplain SequentialIterationDirection#FORWARD},
     * {@linkplain SequentialIterationDirection#BACKWARD}, {@linkplain #INSIDE} и {@linkplain #OUTSIDE}.
     *
     * @since 1.0.0-RC1
     */
    COMPOSITE;

    /**
     * Направление непоследовательной итерации по умолчанию.
     *
     * @see #INSIDE
     * @since 1.0.0-RC1
     */
    public static final @Magical
    @NotNull NonSequentialIterationDirection DEFAULT = INSIDE;

    /**
     * Порядковый номер
     * {@linkplain #INSIDE направления последовательной итерации в сторону последнего элемента, начиная с первого
     * элемента}.
     *
     * @since 1.0.0-RC1
     */
    public static final @Magical int INSIDE_ORDINAL = 0;

    /**
     * Порядковый номер
     * {@linkplain #OUTSIDE направления последовательной итерации в сторону первого элемента, начиная с последнего
     * элемента}.
     *
     * @since 1.0.0-RC1
     */
    public static final @Magical int OUTSIDE_ORDINAL = 1;

    /**
     * Порядковый номер {@linkplain #COMPOSITE составного направления из других}:
     * {@linkplain SequentialIterationDirection#FORWARD}, {@linkplain SequentialIterationDirection#BACKWARD},
     * {@linkplain #INSIDE} и {@linkplain #OUTSIDE}.
     *
     * @since 1.0.0-RC1
     */
    public static final @Magical int COMPOSITE_ORDINAL = 2;

    /**
     * Порядковый номер {@linkplain #DEFAULT направления непоследовательной итерации по умолчанию}.
     *
     * @since 1.0.0-RC1
     */
    public static final @Magical int DEFAULT_ORDINAL = 0;

}
