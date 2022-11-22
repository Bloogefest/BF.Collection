package com.bloogefest.collection.iteration;

import com.bloogefest.common.validation.NullException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор неизменяемых коллекций.
 *
 * @param <E> тип элемента.
 *
 * @author Bloogefest
 * @version 0.0
 * @since 0.0.0
 */
@SuppressWarnings("unused")
public interface ImmutableIterator<E> {

    /**
     * @return Текущий элемент.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Contract(pure = true)
    @Nullable E element();

    /**
     * @return Кэшированный элемент.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Contract(pure = true)
    @Nullable E cached();

    /**
     * Кэширует текущий элемент.
     *
     * @return Текущий итератор.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Contract(value = "-> this", pure = true)
    @NotNull ImmutableIterator<E> cache();

    /**
     * Кэширует переданный элемент.
     *
     * @param element элемент.
     *
     * @return Текущий итератор.
     *
     * @throws NullException элемент не должен быть нулевым.
     * @author Bloogefest
     * @since 0.0.0
     */
    @Contract(value = "_ -> this", pure = true)
    @NotNull ImmutableIterator<E> cache(final @Nullable E element) throws NullException;

    /**
     * Переходит к следующему элементу, когда тот существует.
     *
     * @return Текущий итератор.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Contract(value = "-> this", pure = true)
    @NotNull ImmutableIterator<E> next();

    /**
     * Переходит к предыдущему элементу, когда тот существует.
     *
     * @return Текущий итератор.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Contract(value = "-> this", pure = true)
    @NotNull ImmutableIterator<E> previous();

    /**
     * Переходит к первому элементу, когда тот существует.
     *
     * @return Текущий итератор.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Contract(value = "-> this", pure = true)
    @NotNull ImmutableIterator<E> first();

    /**
     * Переходит к последнему элементу, когда тот существует.
     *
     * @return Текущий итератор.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Contract(value = "-> this", pure = true)
    @NotNull ImmutableIterator<E> last();

    /**
     * Переходит к начальной позиции.
     *
     * @return Текущий итератор.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Contract(value = "-> this", pure = true)
    @NotNull ImmutableIterator<E> start();

    /**
     * Переходит к конечной позиции.
     *
     * @return Текущий итератор.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Contract(value = "-> this", pure = true)
    @NotNull ImmutableIterator<E> end();

    /**
     * Создаёт внешний неизменяемый итератор на основе текущего.
     *
     * @return Внешний неизменяемый итератор.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Contract(value = "-> new", pure = true)
    default @NotNull Iterator<E> external() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return ImmutableIterator.this.hasNext();
            }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                return ImmutableIterator.this.next().element();
            }
        };
    }

    /**
     * @return Существует ли текущий элемент.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Contract(pure = true)
    boolean hasElement();

    /**
     * @return Существует ли кэшированный элемент.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Contract(pure = true)
    boolean hasCached();

    /**
     * @return Существует ли следующий элемент.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Contract(pure = true)
    boolean hasNext();

    /**
     * @return Существует ли предыдущий элемент.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Contract(pure = true)
    boolean hasPrevious();

    /**
     * @return Существует ли первый элемент.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Contract(pure = true)
    boolean hasFirst();

    /**
     * @return Существует ли последний элемент.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Contract(pure = true)
    boolean hasLast();

    /**
     * @return Находится ли в начальной позиции.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Contract(pure = true)
    boolean starting();

    /**
     * @return Находится ли в итерируемой позиции.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Contract(pure = true)
    boolean iterating();

    /**
     * @return Находится ли в конечной позиции.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Contract(pure = true)
    boolean ending();

    /**
     * @return Является ли нулевым.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Contract(pure = true)
    boolean nullable();

    /**
     * @return Является ли изменяемым.
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    @Contract(value = "-> false", pure = true)
    default boolean mutable() {
        return false;
    }

}
