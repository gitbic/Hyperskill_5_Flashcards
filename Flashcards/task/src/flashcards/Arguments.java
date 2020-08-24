package flashcards;

class Arguments {
    private String pathFileImport;
    private String pathFileExport;

    void setArguments(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-import":
                    pathFileImport = args[i + 1];
                    break;
                case "-export":
                    pathFileExport = args[i + 1];
                    break;
            }
        }
    }

    String getPathFileExport() {
        return pathFileExport;
    }
    String getPathFileImport() {
        return pathFileImport;
    }
}
