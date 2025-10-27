package org.example;

public class ContainerDemo {
    public static void main(String[] args) {
        // Создаем контейнер для строк
        MyContainer<String> container = new MyContainer<>();

        System.out.println("=== Демонстрация работы контейнера ===");

        // Проверяем пустой контейнер
        System.out.println("Контейнер пуст? " + container.isEmpty());
        System.out.println("Размер: " + container.size());

        // Добавляем элементы
        System.out.println("\n--- Добавление элементов ---");
        container.add("Первый");
        container.add("Второй");
        container.add("Третий");

        System.out.println("После добавления: " + container);
        System.out.println("Размер: " + container.size());
        System.out.println("Контейнер пуст? " + container.isEmpty());

        // Получаем элементы по индексу
        System.out.println("\n--- Получение элементов ---");
        System.out.println("Элемент с индексом 0: " + container.get(0));
        System.out.println("Элемент с индексом 1: " + container.get(1));
        System.out.println("Элемент с индексом 2: " + container.get(2));

        // Проверяем наличие элементов
        System.out.println("\n--- Проверка наличия элементов ---");
        System.out.println("Содержит 'Второй'? " + container.contains("Второй"));
        System.out.println("Содержит 'Пятый'? " + container.contains("Пятый"));

        // Удаляем элементы
        System.out.println("\n--- Удаление элементов ---");
        String removed = container.remove(1);  // Удаляем второй элемент
        System.out.println("Удален элемент: " + removed);
        System.out.println("После удаления: " + container);
        System.out.println("Размер: " + container.size());

        // Добавляем еще элементов
        System.out.println("\n--- Добавляем еще элементов ---");
        container.add("Четвертый");
        container.add("Пятый");
        System.out.println("После добавления: " + container);

        // Очищаем контейнер
        System.out.println("\n--- Очистка контейнера ---");
        container.clear();
        System.out.println("После очистки: " + container);
        System.out.println("Размер: " + container.size());
        System.out.println("Контейнер пуст? " + container.isEmpty());

        // Тестируем с числами
        System.out.println("\n=== Тестируем с числами ===");
        MyContainer<Integer> numberContainer = new MyContainer<>();

        numberContainer.add(10);
        numberContainer.add(20);
        numberContainer.add(30);

        System.out.println("Числовой контейнер: " + numberContainer);
        System.out.println("Сумма первого и последнего: " + (numberContainer.get(0) + numberContainer.get(2)));
    }
}
