package nl.krom.arthur;

import reactor.core.publisher.Mono;

public class Main {
    public static void main(String[] args) {
        Mono.just("Hello world from day one!").subscribe(s -> {
            System.out.println(s);
        });
    }
}