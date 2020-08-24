package flashcards;

import java.util.*;
import java.util.stream.Collectors;

class HashMapStats<K, V> extends HashMap<K, V> {
    private static final long serialVersionUID = 1L;
    private Map<K, Integer> mistakes = new HashMap<>();

    @Override
    public V put(K key, V value) {
        mistakes.put(key, 0);
        return super.put(key, value);
    }

    V put(K key, V value, Integer number) {
        mistakes.put(key, number);
        return super.put(key, value);
    }

    Integer getMistake(Object key) {
        return mistakes.get(key);
    }

    @Override
    public V remove(Object key) {
        mistakes.remove(key);
        return super.remove(key);
    }

    void addMistake(K key) {
        mistakes.put(key, mistakes.get(key) + 1);
    }

    void resetMistake() {
        mistakes.keySet().forEach(key -> mistakes.put(key, 0));
    }

    Set<Entry<K, Integer>> entrySetMistake() {
        return mistakes.entrySet();
    }

    Collection<Integer> mistakeSet() {
        return mistakes.values();
    }

    List<String> getKeysByValue(String str, boolean isMapMistake) {
        var map = isMapMistake ? mistakes : this;
        return map.entrySet().stream()
                .filter(e -> Objects.equals(e.getValue().toString(), str))
                .map(k -> k.getKey().toString())
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.forEach((key, value) -> sb
                .append(key)
                .append("|")
                .append(value)
                .append("|")
                .append(mistakes.get(key))
                .append(System.lineSeparator()));
        return sb.toString();
    }
}
