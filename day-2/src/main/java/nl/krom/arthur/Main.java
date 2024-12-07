package nl.krom.arthur;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Main {
    private static final Path INPUT_FILE_PATH = Path.of("day-2/src/main/resources/input.txt");
    private static final int MAX_DIFFERENCE = 3;
    private static final int MIN_DIFFERENCE = 1;
    public static void main(String[] args) throws IOException {
        var bufferedReader = new BufferedReader(new FileReader(INPUT_FILE_PATH.toFile()));

        Predicate<List<Integer>> isGradualIncrease = (a) -> {
            BiPredicate<Integer, Integer> compareTwo = (b,c) -> b >= c - MAX_DIFFERENCE && b <= c - MIN_DIFFERENCE;

            for (var i = 0; i < a.size(); i++) {
                if(i + 1 == a.size()){
                    break;
                }

                if(!compareTwo.test(a.get(i), a.get(i+1))){
                    return false;
                }
            }

            return true;
        };

        Predicate<List<Integer>> isGradualDecrease = (a) -> {
            BiPredicate<Integer, Integer> compareTwo = (b,c) -> c + MAX_DIFFERENCE >= b && c <= b - MIN_DIFFERENCE;

            for (var i = 0; i < a.size(); i++) {
                if(i + 1 == a.size()){
                    break;
                }

                if(!compareTwo.test(a.get(i), a.get(i+1))){
                    return false;
                }
            }

            return true;
        };

        Predicate<List<Integer>> isSafe = (a) -> isGradualIncrease.test(a) || isGradualDecrease.test(a);


        var result = bufferedReader
                .lines()
                .reduce(0, ((currentTotal, next) -> {

                    var nrs = Arrays
                            .stream(next.split(" "))
                            .mapToInt(Integer::parseInt)
                            .boxed()
                            .toList();


                    return isSafe.test(nrs) ? currentTotal + 1 : currentTotal;
                }), Integer::sum);

        System.out.println("Result: " + result);
    }

}