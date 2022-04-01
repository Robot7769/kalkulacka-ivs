package tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class RunnerTests {

    public static void main(String[] args) {
        Result testResults = JUnitCore.runClasses(TestMathLib.class);
        System.out.println();

        if (testResults.getFailureCount() > 0) {
            System.out.println("#### Neúspěšné testy: ####");
        }

        testResults.getFailures().forEach(failure -> {
            System.out.println("[Neúspěšný test] " + failure.toString());
            System.out.println();
        });

        System.out.println("Úspěšné testy: " + (testResults.getRunCount() - testResults.getFailureCount()));
        System.out.println("Neúspěšné testy: " + testResults.getFailureCount());
        double success = ((((double) testResults.getRunCount() - (double) testResults.getFailureCount()) / (double) testResults.getRunCount()) * 100);
        System.out.println("Úspěšnost: " + Math.round(success*100.0)/100.0 + " %");
        System.out.println();

    }
}
