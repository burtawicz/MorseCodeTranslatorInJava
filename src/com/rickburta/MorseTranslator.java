package com.rickburta;

import java.util.Map;

class MorseTranslator {
    private static final Map<Character, String> MORSE_CHAR_MAP = populateMap();
    private static final char SPACER = ' ';

    static String encodeInput(String plainTextMessage) {
        StringBuilder encodedMessage = new StringBuilder();

        for (char isoChar : plainTextMessage.toUpperCase().toCharArray()) {
            if (isoChar != SPACER && MORSE_CHAR_MAP.containsKey(isoChar)) {
                encodedMessage.append(MORSE_CHAR_MAP.get(isoChar));
            }
            encodedMessage.append(SPACER);
        }

        return encodedMessage.toString();
    }

    static String decodeInput(String morseCodeMessage) {
        // append empty space to input
        morseCodeMessage = morseCodeMessage + SPACER;

        // create expandable strings for decoded message and varying length morse chars
        StringBuilder decodedMessage = new StringBuilder();
        StringBuilder complexChar = new StringBuilder();

        // create varying length morse char position index
        int charPositionIndex = 0;

        for (char isoChar : morseCodeMessage.toCharArray()) {
            if (isoChar != SPACER) {
                charPositionIndex = 0;
                complexChar.append(isoChar);
            } else {
                charPositionIndex++;
                if (charPositionIndex == 2) {
                    decodedMessage.append(SPACER);
                } else {
                    MORSE_CHAR_MAP.entrySet()
                            .stream()
                            .filter(e -> e.getValue().equals(complexChar.toString()))
                            .findFirst()
                            .map(Map.Entry::getKey)
                            .ifPresent(c -> {
                                decodedMessage.append(c);
                                complexChar.delete(0, complexChar.length());
                            });
                }
            }
        }

        return decodedMessage.toString();
    }

    private static Map<Character, String> populateMap() {
        return Map.ofEntries(
                Map.entry('A', ".-"),
                Map.entry('B', "-..."),
                Map.entry('C', "-.-."),
                Map.entry('D', "-.."),
                Map.entry('E', "."),
                Map.entry('F', "..-."),
                Map.entry('G', "--."),
                Map.entry('H', "...."),
                Map.entry('I', ".."),
                Map.entry('J', ".---"),
                Map.entry('K', "-.-"),
                Map.entry('L', ".-.."),
                Map.entry('M', "--"),
                Map.entry('N', "-."),
                Map.entry('O', "---"),
                Map.entry('P', ".--."),
                Map.entry('Q', "--.-"),
                Map.entry('R', ".-."),
                Map.entry('S', "..."),
                Map.entry('T', "-"),
                Map.entry('U', "..-"),
                Map.entry('V', "...-"),
                Map.entry('W', ".--"),
                Map.entry('X', "-..-"),
                Map.entry('Y', "-.--"),
                Map.entry('Z', "--.."),

                Map.entry('0', "-----"),
                Map.entry('1', ".----"),
                Map.entry('2', "..---"),
                Map.entry('3', "...--"),
                Map.entry('4', "....-"),
                Map.entry('5', "....."),
                Map.entry('6', "-...."),
                Map.entry('7', "--..."),
                Map.entry('8', "---.."),
                Map.entry('9', "----."),

                Map.entry(',', "--..--"),
                Map.entry('\'', ".---."),
                Map.entry('.', ".-.-.-"),
                Map.entry('?', "..--.."),
                Map.entry('/', "-..-."),
                Map.entry('-', "-....-"),
                Map.entry('(', "-.--."),
                Map.entry(')', "-.--.-")
        );
    }
}
