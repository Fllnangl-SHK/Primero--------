
class MovieService {
    private static int counter = 0;
    private final int id;
    private String title;
    private String genre;
    private int durationMinutes;
    private double price;
    private String description;

    public MovieService(String title, String genre, int durationMinutes, double price, String description) {
        this.id = ++counter;
        this.title = title;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    // Тут короче геттеры и сеттеры
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void printDetails() {
        System.out.println("ID: " + id);
        System.out.println("Название: " + title);
        System.out.println("Жанр: " + genre);
        System.out.println("продолжительность: " + durationMinutes);
        System.out.println("Цена: $" + price); //Я ХЗ КАК ПОСТАВИТЬ РУБЛИ
        System.out.println("Описание: " + description);
    }
}