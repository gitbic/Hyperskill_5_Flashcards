package flashcards;

public class Main {
    public static void main(String[] args) {
        Arguments arguments = new Arguments();
        arguments.setArguments(args);

        MenuFactory menuFactory = new MenuFactory(arguments);
        MenuAction menuAction = null;

        if (arguments.getPathFileImport() != null) {
            menuFactory.executeAction(MenuAction.IMPORT);
        }

        while (menuAction != MenuAction.EXIT) {
            menuFactory.printMenuAction();
            menuAction = menuFactory.chooseAction(SystemIO.scan());

            if (menuAction == null) {
                SystemIO.println("Incorrect action!");
                continue;
            }
            menuFactory.executeAction(menuAction);
            SystemIO.println();
        }

        if (arguments.getPathFileExport() != null) {
            menuFactory.executeAction(MenuAction.EXPORT);
        }
    }
}









