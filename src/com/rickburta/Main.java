package com.rickburta;

class Main {
    private static class SimpleExecutionTimer {
        private final long executionStartTime;

        SimpleExecutionTimer() {
            this.executionStartTime = System.currentTimeMillis();
        }

        double getExecutionTime() {
            return (double) (System.currentTimeMillis() - this.executionStartTime) / 1000;
        }
    }

    public static void main(String[] args) {
        SimpleExecutionTimer totalTimer = new SimpleExecutionTimer();

        String plainText = "I'm sorry Dave, I can't let you do that.";

        SimpleExecutionTimer encodeTimer = new SimpleExecutionTimer();
        printExecutionReport(
                "encodeInput()",
                MorseTranslator.encodeInput(plainText),
                encodeTimer.getExecutionTime()
        );

        String morseText = ".. .---. --  ... --- .-. .-. -.--  -.. .- ...- . --..--  ..  -.-. .- -. .---. -  .-.. . -  -.-- --- ..-  -.. ---  - .... .- - .-.-.-";

        SimpleExecutionTimer decodeTimer = new SimpleExecutionTimer();
        printExecutionReport(
                "encodeInput()",
                MorseTranslator.decodeInput(morseText),
                decodeTimer.getExecutionTime()
        );

        printExecutionReport(
                "main()",
                null,
                totalTimer.getExecutionTime()
        );
    }

    private static void printExecutionReport(String methodName, String methodValue, double executionTime) {
        System.out.println("Method: " + methodName);
        System.out.println("Return Value: " + methodValue);
        System.out.println("Execution Time: " + executionTime + " seconds\n");
    }
}
