package syn;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 〈〉
 * create by zgw on 2019/7/11
 */
public class TestFuture {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("xx: buy goods.");
            try {
                return "goo";
            }catch (Exception w){
                w.printStackTrace();
            }finally {
                return "I'm dead .";
            }
        },executor);


    }







}
