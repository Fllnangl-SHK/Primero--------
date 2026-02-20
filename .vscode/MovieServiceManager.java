import java.util.*;

class MovieServiceManager {
    private Map<Integer, MovieService> services = new HashMap<>();

    public void addService(MovieService service) {
        services.put(service.getId(), service);
    }

    public MovieService getService(int id) {
        return services.get(id);
    }

    public List<MovieService> getAllServices() {
        return new ArrayList<>(services.values());
    }

    public boolean removeService(int id) {
        return services.remove(id) != null;
    }
}