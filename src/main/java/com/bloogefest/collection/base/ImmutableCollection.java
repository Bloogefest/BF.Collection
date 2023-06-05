package com.bloogefest.collection.base;

import com.bloogefest.collection.iteration.ImmutableIterator;
import com.bloogefest.common.base.Predicates;
import com.bloogefest.common.function.Consumer;
import com.bloogefest.common.function.Predicate;
import com.bloogefest.common.validation.NullException;
import com.bloogefest.common.validation.Validator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

/**
 * Неизменяемая коллекция.
 *
 * @param <E> тип элемента.
 *
 * @author Bloogefest
 * @version 0.0
 * @since 0.0.0
 */
@SuppressWarnings("unused")
public interface ImmutableCollection<E> {

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
    @Contract(value = "_ -> this", pure = true)
    default @NotNull ImmutableCollection<E> iterate(final @NotNull Consumer<E> consumer) throws NullException {
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
    @Contract(value = "_ -> this", pure = true)
    default @NotNull ImmutableCollection<E> iterate(final @NotNull Predicate<E> predicate) throws NullException {
        Validator.notNull(predicate, "predicate");
        for (final var element : external()) if (!predicate.evaluate(element)) break;
        return this;
    }

    /**
     * Создаёт неизменяемый итератор.
     *
     * @return Неизменяемый итератор.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Contract(value = "-> new", pure = true)
    @NotNull ImmutableIterator<E> iterator();

    /**
     * Создаёт внешнюю неизменяемую коллекцию на основе текущей.
     *
     * @return Внешняя неизменяемая коллекция.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
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
    @Range(from = 0, to = Integer.MAX_VALUE)
    @Contract(pure = true)
    int size();

    /**
     * @return Является ли пустой.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
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
    @Contract(pure = true)
    boolean nullable();

    /**
     * @return Является ли изменяемой.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Contract(value = "-> false", pure = true)
    default boolean mutable() {
        return false;
    }

}
