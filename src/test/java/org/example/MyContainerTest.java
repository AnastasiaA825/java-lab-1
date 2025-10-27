package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit-тесты для класса MyContainer.
 *
 * <p>Тестируются все основные операции контейнера:
 * <ul>
 *   <li>Добавление элементов</li>
 *   <li>Получение элементов</li>
 *   <li>Удаление элементов</li>
 *   <li>Поиск элементов</li>
 *   <li>Проверка размера и пустоты</li>
 *   <li>Очистка контейнера</li>
 * </ul>
 */

@DisplayName("Тестирование кастомного контейнера")
class MyContainerTest {

    private MyContainer<String> container;
    private MyContainer<Integer> intContainer;

    @BeforeEach
    void setUp() {
        container = new MyContainer<>();
        intContainer = new MyContainer<>();
    }

    @Test
    @DisplayName("Должен создавать пустой контейнер")
    void shouldCreateEmptyContainer() {
        assertTrue(container.isEmpty());
        assertEquals(0, container.size());
    }

    @Test
    @DisplayName("Должен добавлять элементы и увеличивать размер")
    void shouldAddElementsAndIncreaseSize() {
        container.add("first");
        container.add("second");

        assertFalse(container.isEmpty());
        assertEquals(2, container.size());
        assertEquals("first", container.get(0));
        assertEquals("second", container.get(1));
    }

    @Test
    @DisplayName("Должен получать элементы по корректному индексу")
    void shouldGetElementByValidIndex() {
        container.add("A");
        container.add("B");
        container.add("C");

        assertEquals("A", container.get(0));
        assertEquals("B", container.get(1));
        assertEquals("C", container.get(2));
    }

    @Test
    @DisplayName("Должен бросать исключение при получении по неверному индексу")
    void shouldThrowExceptionWhenGettingByInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> container.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> container.get(-1));

        container.add("single");

        assertThrows(IndexOutOfBoundsException.class, () -> container.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> container.get(-1));
    }

    @Test
    @DisplayName("Должен удалять элементы по индексу")
    void shouldRemoveElementsByIndex() {
        container.add("first");
        container.add("second");
        container.add("third");

        String removed = container.remove(1);

        assertEquals("second", removed);
        assertEquals(2, container.size());
        assertEquals("first", container.get(0));
        assertEquals("third", container.get(1));
    }

    @Test
    @DisplayName("Должен удалять первый элемент")
    void shouldRemoveFirstElement() {
        container.add("first");
        container.add("second");

        String removed = container.remove(0);

        assertEquals("first", removed);
        assertEquals(1, container.size());
        assertEquals("second", container.get(0));
    }

    @Test
    @DisplayName("Должен удалять последний элемент")
    void shouldRemoveLastElement() {
        container.add("first");
        container.add("second");
        container.add("third");

        String removed = container.remove(2);

        assertEquals("third", removed);
        assertEquals(2, container.size());
        assertEquals("first", container.get(0));
        assertEquals("second", container.get(1));
    }

    @Test
    @DisplayName("Должен бросать исключение при удалении по неверному индексу")
    void shouldThrowExceptionWhenRemovingByInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> container.remove(0));
        assertThrows(IndexOutOfBoundsException.class, () -> container.remove(-1));
    }

    @Test
    @DisplayName("Должен проверять наличие элементов")
    void shouldCheckElementPresence() {
        container.add("existing");

        assertTrue(container.contains("existing"));
        assertFalse(container.contains("nonexistent"));
    }

    @Test
    @DisplayName("Должен работать с null элементами")
    void shouldWorkWithNullElements() {
        // When
        container.add(null);
        container.add("not null");

        // Then
        assertTrue(container.contains(null));
        assertEquals(null, container.get(0));
        assertEquals("not null", container.get(1));
    }

    @Test
    @DisplayName("Должен очищать контейнер")
    void shouldClearContainer() {
        // Given
        container.add("A");
        container.add("B");

        // When
        container.clear();

        // Then
        assertTrue(container.isEmpty());
        assertEquals(0, container.size());
    }

    @Test
    @DisplayName("Должен работать с целыми числами")
    void shouldWorkWithIntegers() {
        // When
        intContainer.add(1);
        intContainer.add(2);
        intContainer.add(3);

        // Then
        assertEquals(3, intContainer.size());
        assertEquals(Integer.valueOf(1), intContainer.get(0));
        assertEquals(Integer.valueOf(2), intContainer.get(1));
        assertEquals(Integer.valueOf(3), intContainer.get(2));
    }

    @Test
    @DisplayName("Должен возвращать корректное строковое представление")
    void shouldReturnCorrectStringRepresentation() {

        container.add("A");
        container.add("B");
        container.add("C");

        assertEquals("[A, B, C]", container.toString());

        container.clear();
        assertEquals("[]", container.toString());
    }

    @Test
    @DisplayName("Должен корректно работать с одним элементом")
    void shouldWorkWithSingleElement() {
        container.add("single");

        assertFalse(container.isEmpty());
        assertEquals(1, container.size());
        assertEquals("single", container.get(0));

        String removed = container.remove(0);

        assertEquals("single", removed);
        assertTrue(container.isEmpty());
        assertEquals(0, container.size());
    }
}
