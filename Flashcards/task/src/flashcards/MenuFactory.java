package flashcards;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

class MenuFactory {
    private String menuList;
    private FlashCard flashCard = new FlashCard();
    private String pathFileImport;
    private String pathFileExport;

    MenuFactory(Arguments arguments) {
        pathFileImport = arguments.getPathFileImport();
        pathFileExport = arguments.getPathFileExport();
        createMenuAction();
    }

    void executeAction(MenuAction menuAction) {
        switch (menuAction) {
            case ADD:
                flashCard.addCard();
                break;
            case REMOVE:
                flashCard.removeCard();
                break;
            case EXIT:
                flashCard.exitProgram();
                break;
            case EXPORT:
                flashCard.exportCards(pathFileExport);
                break;
            case IMPORT:
                flashCard.importCards(pathFileImport);
                break;
            case PRINT:
                flashCard.printCards();
                break;
            case ASK:
                flashCard.askMe();
                break;
            case LOG:
                flashCard.exportLog();
                break;
            case RESET_STATS:
                flashCard.resetStats();
                break;
            case HARDEST_CARD:
                flashCard.printHardestCard();
                break;
        }
    }

    MenuAction chooseAction(String inputString) {
        MenuAction menuAction = null;
        for (MenuAction value : MenuAction.values()) {
            if (Objects.equals(
                    value.toString().toLowerCase(),
                    inputString.toLowerCase().replaceAll(" ", "_"))) {
                menuAction = value;
                break;
            }
        }
        return menuAction;
    }

    void printMenuAction() {
        SystemIO.printf("Input the action (%s):%n", menuList);
    }

    private void createMenuAction() {
        menuList = Arrays.stream(MenuAction.values())
                .map(MenuAction::name)
                .map(String::toLowerCase)
                .collect(Collectors.joining(", "))
                .replaceAll("_", " ");
    }
}
