import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    static List<Long> factorialNumbers = new ArrayList<>();
    static List<Integer> primeNumbers = new ArrayList<>();


    public static void main(String[] args) {

        // TASK 1
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Write down path to the file!");
//
////        String path = scanner.next();
//        String path = "example.txt";
//        CountDownLatch countDownLatch = new CountDownLatch(1);
//        Thread thread1 = new Thread(() -> fillWithRandomNumberFile(path, countDownLatch));
//        Thread thread2 = new Thread(() -> findPrimeNumbers(path, countDownLatch));
//        Thread thread3 = new Thread(() -> findFactorialOfNumbers(path, countDownLatch));
//
//        thread1.start();
//        thread2.start();
//        thread3.start();
//        try {
//            thread2.join();
//            thread3.join();
//
//            for (Integer number: primeNumbers){
//                System.out.print(number + " ");
//            }
//            for (Long number: factorialNumbers){
//                System.out.print(number + " ");
//            }
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }



        // Task 2

        String sourceDirectoryPath = "C:\\Users\\Nurgissa\\IdeaProjects\\practice10";
        String targetDirectoryPath = "C:\\Users\\Nurgissa\\Desktop\\Web development\\WEBASS";

        File sourceDirectory = new File(sourceDirectoryPath);
        File pathDirectory = new File(targetDirectoryPath);

        AtomicInteger fileCount = new AtomicInteger(0);

        try {
            if (sourceDirectory.exists() && sourceDirectory.isDirectory()){
                if (!pathDirectory.exists()){
                    pathDirectory.mkdir();
                }
                copyDirectory(sourceDirectory, pathDirectory, fileCount);

                System.out.println("Done");
            }
            else {
                System.out.println("Not working directory");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public static void copyDirectory(File sourceDirectory, File pathDirectory, AtomicInteger fileCounter){
        if (sourceDirectory.isDirectory()) {
            if (!pathDirectory.exists()) {
                pathDirectory.mkdirs();
            }

            String[] files = sourceDirectory.list();
            if (files != null) {
                for (String file : files) {
                    File sourceFile = new File(sourceDirectory, file);
                    File targetFile = new File(pathDirectory, file);

                    if (sourceFile.isDirectory()) {
                        copyDirectory(sourceFile, targetFile, fileCounter);
                    } else {
                        try {
                            Files.copy(sourceFile.toPath(), targetFile.toPath());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        fileCounter.incrementAndGet();
                    }
                }
            }
        }
    }


    public static void findPrimeNumbers(String path, CountDownLatch countDownLatch){
        List<Integer> primeNumbers = new ArrayList<>();
        try {
            countDownLatch.await();
            BufferedReader fileReader = new BufferedReader(new FileReader(path));


            String line;
            while ((line = fileReader.readLine()) != null) {
                int number = Integer.parseInt(line);
                if (isPrime(number)) {
                    primeNumbers.add(number);
                }
            }
            fileReader.close();
            FileWriter primeWriter = new FileWriter("primes.txt");
            for (int prime : primeNumbers) {
                primeWriter.write(prime + "\n");
            }
            primeWriter.close();
        }
        catch (IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    public static void findFactorialOfNumbers(String path, CountDownLatch countDownLatch){

        try {
            countDownLatch.await();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            FileWriter writer = new FileWriter("output.txt");

            String line;

            while ((line = bufferedReader.readLine()) != null){
                int number = Integer.parseInt(line);

                long factorial = calculateFactorial(number);
                factorialNumbers.add(factorial);
                writer.write(number + " : " + factorial + "\n");
            }

            bufferedReader.close();
            writer.close();

        }
        catch (InterruptedException | IOException exception){
            System.out.println(exception.getMessage());
        }

    }

    public static void fillWithRandomNumberFile(String path, CountDownLatch countDownLatch){
        Random random = new Random();

        try {
            FileWriter fileWriter = new FileWriter(path);
            for (int i = 0; i < 10; i++){
                fileWriter.write(random.nextInt(100) + "\n");
            }
            fileWriter.close();
            countDownLatch.countDown();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }


    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static long calculateFactorial(int number) {
        if (number == 0) {
            return 1;
        }
        long factorial = 1;
        for (int i = 1; i <= number; i++) {
            factorial *= i;
        }
        return factorial;
    }
}