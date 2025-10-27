package org.example;

/**
 * Pеализация контейнера на основе связанного списка.
 * Поддерживает хранение произвольного количества объектов любых типов.
 *
 * <p><b>Особенности реализации:</b>
 * <ul>
 *   <li>Использует связанный список для хранения элементов</li>
 *   <li>Не использует встроенные коллекции Java</li>
 * </ul>
 *
 * @param <T> тип элементов в контейнере
 * @author ШишкинаАнастасия Алексеевна
 * @version 1.0
 * @see java.util.List
 */
public class MyContainer<T> {

    /**
     * Внутренний класс, представляющий узел связанного списка.
     * Каждый узел содержит данные и ссылку на следующий узел.
     */
    private static class Node<T> {
        /** Данные, хранящиеся в узле */
        final T data;

        /** Ссылка на следующий узел в списке */
        Node<T> next;

        /**
         * Создает новый узел с указанными данными.
         *
         * @param data данные для хранения в узле
         * @throws NullPointerException если data is null (опционально)
         */
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    /** Первый узел в списке (голова) */
    private Node<T> head;

    /** Последний узел в списке (хвост) */
    private Node<T> tail;

    /** Количество элементов в контейнере */
    private int size;

    /**
     * Создает пустой контейнер.
     * Инициализирует все поля значениями по умолчанию.
     */
    public MyContainer() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Добавляет указанный элемент в конец контейнера.
     *
     * @param element элемент для добавления
     * @throws NullPointerException если элемент равен null
     */
    public boolean add(T element) {
        Node<T> newNode = new Node<>(element);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
        return true;
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index индекс элемента (отсчет с 0)
     * @return элемент по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за границы
     * <p><b>Временная сложность:</b> O(n) в худшем случае
     */
    public T get(int index) {
        checkIndexBounds(index);

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

    /**
     * Удаляет элемент по указанному индексу.
     *
     * @param index индекс элемента для удаления
     * @return удаленный элемент
     * @throws IndexOutOfBoundsException если индекс выходит за границы
     *
     * <p><b>Особые случаи:</b>
     * <ul>
     *   <li>Удаление первого элемента (O(1))</li>
     *   <li>Удаление последнего элемента (O(n))</li>
     *   <li>Удаление из середины (O(n))</li>
     * </ul>
     */
    public T remove(int index) {
        checkIndexBounds(index);

        T removedData;

        if (index == 0) {
            // Удаление первого элемента
            removedData = head.data;
            head = head.next;

            if (head == null) {
                tail = null;
            }
        } else {
            // Удаление из середины или конца
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            removedData = current.next.data;
            current.next = current.next.next;

            if (current.next == null) {
                tail = current;
            }
        }

        size--;
        return removedData;
    }

    /**
     * Проверяет, содержит ли контейнер указанный элемент.
     *
     * @param element элемент для поиска
     * @return {@code true} если элемент найден, {@code false} в противном случае
     *
     * <p><b>Особенности:</b> поддерживает поиск {@code null} элементов
     */
    public boolean contains(T element) {
        Node<T> current = head;

        while (current != null) {
            if (element == null) {
                if (current.data == null) {
                    return true;
                }
            } else {
                if (element.equals(current.data)) {
                    return true;
                }
            }
            current = current.next;
        }

        return false;
    }

    /**
     * Возвращает количество элементов в контейнере.
     *
     * @return количество элементов
     */
    public int size() {
        return size;
    }

    /**
     * Проверяет, пуст ли контейнер.
     *
     * @return {@code true} если контейнер пуст, {@code false} в противном случае
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Удаляет все элементы из контейнера.
     * После вызова этого метода контейнер будет пуст.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Проверяет корректность индекса.
     *
     * @param index проверяемый индекс
     * @throws IndexOutOfBoundsException если индекс некорректен
     */
    private void checkIndexBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    String.format("Index: %d, Size: %d", index, size));
        }
    }

    /**
     * Возвращает строковое представление контейнера.
     *
     * @return строку в формате [element1, element2, ...]
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }

        sb.append("]");
        return sb.toString();
    }
}