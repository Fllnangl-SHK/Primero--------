import java.util.*;

public class MainApp {
    private static Scanner scanner = new Scanner(System.in);
    private static UserManager userManager = new UserManager();
    private static MovieServiceManager serviceManager = new MovieServiceManager();
    private static User currentUser = null;

//создадим акк для админ админыча
    public static void main(String[] args) {
        userManager.register("admin", "admin", "Admin 1.1", "admin@mail.com", true);

        while (true) {
            if (currentUser == null) {
                showAuthMenu();
            } else {
                showMainMenu();
            }
        }
    }

// 10к неправильных функций 
    private static void showAuthMenu() {
        System.out.println("Добро подаловать в КИНОТЕАТР СОЮЗ");
        System.out.println("1. Регистрация");
        System.out.println("2. Вход");
        System.out.println("0. Выход из приложения");
        System.out.print("Ваш выбор: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                register();
                break;
            case "2":
                login();
                break;
            case "0":
                System.out.println("ПРИХОДИТЕ ЕЩЁ!");
                System.exit(0);
                break;
            default:
                System.out.println("Неверный выбор :(");
        }
    }

    private static void register() {
        System.out.print("Введите логин: ");
        String username = scanner.nextLine().trim();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine().trim();
        System.out.print("Полное имя: ");
        String fullName = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        boolean success = userManager.register(username, password, fullName, email, false);
        if (success) {
            System.out.println("Регистрация успешно завершена <3");
        } else {
            System.out.println("Такой логин уже занят:(");
        }
    }

    private static void login() {
        System.out.print("Логин: ");
        String username = scanner.nextLine().trim();
        System.out.print("Пароль: ");
        String password = scanner.nextLine().trim();

        User user = userManager.login(username, password);
        if (user != null) {
            currentUser = user;
            System.out.println("Добро пожаловать, " + currentUser.getFullName() + "!");
        } else {
            System.out.println("Неверный логин или пароль Т.Т");
        }
    }

    private static void showMainMenu() {
        System.out.println("\nГлавное меню");
        System.out.println("1. Добавить новый фильм");
        System.out.println("2. Просмотр всех доступных фильмов");
        System.out.println("3. Редактировать фильм");
        System.out.println("4. Удалить фильм");
        System.out.println("5. Выход из аккаунта");
        System.out.println("0. Выход");
        System.out.print("Ващ выбор: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                addService();
                break;
            case "2":
                viewServices();
                break;
            case "3":
                editService();
                break;
            case "4":
                deleteService();
                break;
            case "5":
                currentUser = null;
                System.out.println("Произведён выход из аккаунта");
                break;
            case "0":
                System.out.println("Приходите ещё!");
                System.exit(0);
                break;
            default:
                System.out.println("Неверный выбор :(");
        }
    }

    private static void addService() {
        System.out.println("Введите детали фильма:");
        System.out.print("Название: ");
        String title = scanner.nextLine().trim();
        System.out.print("Жанр: ");
        String genre = scanner.nextLine().trim();
        System.out.print("Продолжительность фильма: ");
        int duration = readInt();
        System.out.print("Цена: ");
        double price = readDouble();
        System.out.print("Описание: ");
        String description = scanner.nextLine().trim();

        MovieService service = new MovieService(title, genre, duration, price, description);
        serviceManager.addService(service);
        System.out.println("Добавлен филь с ID: " + service.getId());
    }

    private static void viewServices() {
        List<MovieService> services = serviceManager.getAllServices();
        if (services.isEmpty()) {
            System.out.println("Недоступно Т.Т");
        } else {
            for (MovieService s : services) {
                s.printDetails();
            }
        }
    }

    private static void editService() {
        System.out.print("Введите ID фильма для редактирования: ");
        int id = readInt();
        MovieService service = serviceManager.getService(id);
        if (service == null) {
            System.out.println("Похоже, что фильма с таким ID не нашлось :(");
            return;
        }

        System.out.println("Редактирование: " + service.getTitle());

        System.out.print("Новое название (" + service.getTitle() + "): ");
        String title = scanner.nextLine().trim();
        if (!title.isEmpty()) service.setTitle(title);

        System.out.print("Новый жанр (" + service.getGenre() + "): ");
        String genre = scanner.nextLine().trim();
        if (!genre.isEmpty()) service.setGenre(genre);

        System.out.print("Новая продолжительность (" + service.getDurationMinutes() + "): ");
        String durationStr = scanner.nextLine().trim();
        if (!durationStr.isEmpty()) {
            int duration = parseIntOrDefault(durationStr, service.getDurationMinutes());
            service.setDurationMinutes(duration);
        }

        System.out.print("Новая цена (" + service.getPrice() + "): ");
        String priceStr = scanner.nextLine().trim();
        if (!priceStr.isEmpty()) {
            double price = parseDoubleOrDefault(priceStr, service.getPrice());
            service.setPrice(price);
        }

        System.out.print("Новое описание (" + service.getDescription() + "): ");
        String desc = scanner.nextLine().trim();
        if (!desc.isEmpty()) service.setDescription(desc);

        System.out.println("Обновлено :)");
    }

    private static void deleteService() {
        System.out.print("Введите ID фильма для удаления: ");
        int id = readInt();
        boolean removed = serviceManager.removeService(id);
        if (removed) {
            System.out.println("Фильм удалён из нашей солнечной системы ;)");
        } else {
            System.out.println("Фильм не найден :(");
        }
    }

    private static int readInt() {
        while (true) {
            try {
                String line = scanner.nextLine().trim();
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Пождалуйста введите корректное число: ");
            }
        }
    }

    private static double readDouble() {
        while (true) {
            try {
                String line = scanner.nextLine().trim();
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.print("Поджалуйста введите коректный номер: ");
            }
        }
    }

    private static int parseIntOrDefault(String s, int def) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    private static double parseDoubleOrDefault(String s, double def) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return def;
        }
    }
}