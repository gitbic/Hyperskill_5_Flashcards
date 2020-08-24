package flashcards;

class LogBuilder {
    private static StringBuilder sbLog = new StringBuilder();

    void add(String str) {
        sbLog.append(str);
    }

    @Override
    public String toString() {
        return sbLog.toString();
    }
}
