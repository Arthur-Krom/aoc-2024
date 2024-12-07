package nl.krom.arthur;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
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


        BiPredicate<Integer, Integer> gradualIncrease = (b,c) -> b >= c - MAX_DIFFERENCE && b <= c - MIN_DIFFERENCE;
        BiPredicate<Integer, Integer> gradualDecrease = (b,c) -> c + MAX_DIFFERENCE >= b && c <= b - MIN_DIFFERENCE;
        Predicate<List<Integer>> isSafe = (a) -> gradualChange(a, gradualDecrease) || gradualChange(a, gradualIncrease);


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

    private static boolean gradualChange(List<Integer> listToTest, BiPredicate<Integer, Integer> changePredicate){
        var mutableList = new ArrayList<>(listToTest);

        for (var i = 1; i < mutableList.size(); i++) {
            if(!changePredicate.test(mutableList.get(i-1), mutableList.get(i))){
                return false;
            }
        }

        return true;
    }

}