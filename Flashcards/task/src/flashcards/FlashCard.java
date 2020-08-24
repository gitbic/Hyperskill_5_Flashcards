package flashcards;

import java.util.*;

class FlashCard {

    private HashMapStats<String, String> mapCards = new HashMapStats<>();
    private FileIO fileIO = new FileIO();
    private Random random = new Random();
    private LogBuilder logBuilder = new LogBuilder();
    private String key;
    private String value;
    private Integer mistake;

    void exitProgram() {
        SystemIO.println("Bye bye!");
    }

    void addCard() {

        SystemIO.println("The card:");
        key = SystemIO.scan();
        if (mapCards.containsKey(key)) {
            SystemIO.printf("The card \"%s\" already exists.%n", key);
            return;
        }

        SystemIO.println("The definition of the card:");
        value = SystemIO.scan();
        if (mapCards.containsValue(value)) {
            SystemIO.printf("The definition \"%s\" already exists.%n", value);
            return;
        }

        mapCards.put(key, value);
        SystemIO.printf("The pair (\"%s\":\"%s\") has been added.%n", key, value);
    }

    void removeCard() {
        SystemIO.println("The card:");
        key = SystemIO.scan();

        if (mapCards.containsKey(key)) {
            mapCards.remove(key);
            SystemIO.println("The card has been removed.");
        } else {
            SystemIO.printf("Can't remove \"%s\": there is no such card.%n", key);
        }
    }

    void importCards(String filePath) {
        fileIO.setFile(filePath);
        String str = fileIO.read();
        if (!"-1".equals(str)) {
            int count = addCardsFromString(str);
            SystemIO.println(count + " cards have been loaded.");
        }
    }

    private int addCardsFromString(String str) {
        String[] sCards = str.split(System.lineSeparator());

        for (String card : sCards) {
            key = card.substring(0, card.indexOf("|"));
            value = card.substring(card.indexOf("|") + 1, card.lastIndexOf("|"));
            mistake = Integer.parseInt(card.substring(card.lastIndexOf("|") + 1));
            mapCards.put(key, value, mistake);
        }
        return sCards.length;
    }

    void printCards() {
        SystemIO.println(mapCards.toString());
    }

    void askMe() {
        SystemIO.println("How many times to ask?");
        int number = Integer.parseInt(SystemIO.scan());

        for (int i = 0; i < number; i++) {
            chooseRandomCard();
            SystemIO.printf("Print the definition of \"%s\":%n", key);
            String answer = SystemIO.scan();

            if (Objects.equals(answer, value)) {
                SystemIO.println("Correct answer");
            } else {
                mapCards.addMistake(key);
                SystemIO.printf("Wrong answer. The correct one is \"%s\". ", value);
                if (mapCards.containsValue(answer)) {
                    SystemIO.printf("You've just written the definition of %s.%n",
                            mapCards.getKeysByValue(answer, false).get(0));
                }
                SystemIO.println();
            }
        }
    }

    private void chooseRandomCard() {
        int rand = random.nextInt(mapCards.size());
        this.key = mapCards.keySet().stream().skip(rand).findFirst().get();
        this.value = mapCards.get(key);
    }

    void exportCards(String filePath) {
        fileIO.setFile(filePath);
        fileIO.write(mapCards.toString());
        SystemIO.println(mapCards.size() + " cards have been saved.");
    }

    void exportLog() {
        fileIO.setFile(null);
        fileIO.write(logBuilder.toString());
        SystemIO.println("The log has been saved.");
    }

    void resetStats() {
        mapCards.resetMistake();
        SystemIO.println("Card statistics has been reset.");
    }

    void printHardestCard() {

        if (mapCards.size() == 0) {
            SystemIO.println("There are no cards with errors.");
            return;
        }

        int maxMistake = Collections.max(mapCards.mistakeSet());

        if (maxMistake == 0) {
            SystemIO.println("There are no cards with errors.");
        } else {
            List<String> hardestKeys = mapCards.getKeysByValue(Integer.toString(maxMistake), true);

            boolean isOne = hardestKeys.size() == 1;
            String someCards = isOne ? "card is" : "cards are";
            SystemIO.printf("The hardest %s %s. You have %s errors answering them.%n",
                    someCards, hardestKeys.toString(), Integer.toString(maxMistake));
        }
    }
}