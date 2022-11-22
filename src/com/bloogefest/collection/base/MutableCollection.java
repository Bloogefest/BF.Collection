package com.bloogefest.collection.base;

import com.bloogefest.collection.iteration.MutableIterator;
import com.bloogefest.common.base.Predicates;
import com.bloogefest.common.function.Consumer;
import com.bloogefest.common.function.Predicate;
import com.bloogefest.common.validation.NullException;
import com.bloogefest.common.validation.Validator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

/**
 * Изменяемая коллекция.
 *
 * @param <E> тип элемента.
 *
 * @author Bloogefest
 * @version 0.0
 * @since 0.0.0
 */
@SuppressWarnings("unused")
public interface MutableCollection<E> extends ImmutableCollection<E> {

    /**
     * Итерирует потребитель по всей коллекции.
     *
     * @param consumer потребитель.
     *
     * @return Текущая коллекция.
     *
     * @throws NullException потребитель не должен быть нулевым.
     * @author Bloogefest
     * @since 0.0.0
     */
    @Override
    @Contract(value = "_ -> this", pure = true)
    default @NotNull MutableCollection<E> iterate(final @NotNull Consumer<E> consumer) throws NullException {
        Validator.notNull(consumer, "consumer");
        for (final var element : external()) consumer.consume(element);
        return this;
    }

    /**
     * Постепенно итерирует предикат по всей коллекции, пока тот требует следующий элемент.
     *
     * @param predicate предикат.
     *
     * @return Текущая коллекция.
     *
     * @throws NullException предикат не должен быть нулевым.
     * @author Bloogefest
     * @since 0.0.0
     */
    @Override
    @Contract(value = "_ -> this", pure = true)
    default @NotNull MutableCollection<E> iterate(final @NotNull Predicate<E> predicate) throws NullException {
        Validator.notNull(predicate, "predicate");
        for (final var element : external()) if (!predicate.evaluate(element)) break;
        return this;
    }

    /**
     * Создаёт изменяемый итератор.
     *
     * @return Изменяемый итератор.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Override
    @Contract(value = "-> new", pure = true)
    @NotNull MutableIterator<E> iterator();

    /**
     * Создаёт внешнюю изменяемую коллекцию на основе текущей.
     *
     * @return Внешняя изменяемая коллекция.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Override
    @Contract(value = "-> new", pure = true)
    default @NotNull Iterable<E> external() {
        return iterator()::external;
    }

    /**
     * @return Текущий размер.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Override
    @Range(from = 0, to = Integer.MAX_VALUE)
    @Contract(pure = true)
    int size();

    /**
     * @return Является ли пустой.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Override
    @Contract(pure = true)
    default boolean empty() {
        return Predicates.equals(size(), 0);
    }

    /**
     * @return Является ли нулевой.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Override
    @Contract(pure = true)
    boolean nullable();

    /**
     * @return Является ли изменяемой.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Override
    @Contract(value = "-> true", pure = true)
    default boolean mutable() {
        return true;
    }

}
