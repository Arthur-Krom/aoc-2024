package nl.krom.arthur;


import reactor.core.publisher.Flux;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Day 1: Historian Hysteria
 */
public class Main {
    private static final Path INPUT_FILE_PATH = Path.of("day-1/src/main/resources/input.txt");
    public static void main(String[] args) throws IOException {
        var c = new BufferedReader(new FileReader(INPUT_FILE_PATH.toFile()));
        var leftNrs = new ArrayList<Integer>();
        var rightNrs = new ArrayList<Integer>();
        var similarityScore = 0;

        Flux.fromStream(c.lines()).subscribe(s ->{
            var leftStr = s.substring(0,s.indexOf(" ")).trim();
            var rightStr = s.substring(s.indexOf(" ")).trim();
            var leftNr = Integer.parseInt(leftStr);
            var rightNr = Integer.parseInt(rightStr);

            leftNrs.add(leftNr);
            rightNrs.add(rightNr);

        });


        for(Integer i: leftNrs){
            var count = 0;
            for(Integer j: rightNrs){
                if(Objects.equals(j, i)){
                    count++;
                }
            }

            similarityScore += (i * count);
        }

        System.out.println("Similarity score: " + similarityScore);
    }



    public static void partOne() throws FileNotFoundException {
        var c = new BufferedReader(new FileReader(INPUT_FILE_PATH.toFile()));
        var leftNrs = new ArrayList<Integer>();
        var rightNrs = new ArrayList<Integer>();

        Flux.fromStream(c.lines()).subscribe(s ->{

            var leftStr = s.substring(0,s.indexOf(" ")).trim();
            var rightStr = s.substring(s.indexOf(" ")).trim();
            var leftNr = Integer.parseInt(leftStr);
            var rightNr = Integer.parseInt(rightStr);

            leftNrs.add(leftNr);
            rightNrs.add(rightNr);
        });

        leftNrs.sort(Comparator.comparingInt(a -> a));
        rightNrs.sort(Comparator.comparingInt(a -> a));
        Flux.zip(Flux.fromIterable(leftNrs), Flux.fromIterable(rightNrs), (left, right) -> Math.abs(left - right))
                .reduce(Integer::sum).subscribe(r -> {
                    System.out.println("r: " +r);
                });
    }
}