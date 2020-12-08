import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TouroHashMap<K,T> {

    public static final int BUCKET_SIZE = 1024;

    static class Entry<K,T> {
        K key;
        T value;

        public Entry(K key, T value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry entry = (Entry) o;
            return Objects.equals(key, entry.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    List<Entry> buckets[];

    public TouroHashMap() {
        buckets = new List[BUCKET_SIZE];
        for (int i=0 ; i< BUCKET_SIZE; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    public void put(K key, T value) {
        Entry entry = new Entry(key, value);
        int hashCode = entry.hashCode();
        int index = Math.abs(hashCode) % BUCKET_SIZE;
        buckets[index].add(entry);
    }

    public T get(K key) {
        int hashCode = Objects.hash(key);
        int bucketIndex = Math.abs(hashCode) % BUCKET_SIZE;
        for ( Entry entry : buckets[bucketIndex] ) {
            if (entry.key.equals(key)) {
                return (T) entry.value;
            }
        }
        return null;
    }

}
