import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Destination {
	private static final Map<Integer, Destination> store = new LinkedHashMap<>();
    private static final AtomicInteger idCounter = new AtomicInteger(0);

    private int id;
    private String name;
    private String type;

    public Destination(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public static Destination create(String name, String type) {
        int id = idCounter.incrementAndGet();
        Destination d = new Destination(id, name, type);
        store.put(id, d);
        return d;
    }

    public static List<Destination> findAll() {
        return new ArrayList<>(store.values());
    }

    public static Optional<Destination> findById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    public static boolean update(int id, String newName, String newType) {
        Destination d = store.get(id);
        if (d == null) return false;
        if (newName != null && !newName.trim().isEmpty()) d.setName(newName);
        if (newType != null) d.setType(newType);
        return true;
    }

    public static boolean delete(int id) {
        return store.remove(id) != null;
    }

    public static int count() {
        return store.size();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    @Override
    public String toString() {
        return "Destination{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    static {
        create("Abha", "Mountain Tourist City");
        create("AlUla", "Historic Desert City");
    }

}
