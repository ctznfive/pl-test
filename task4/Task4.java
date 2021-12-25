import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Task4 {

    public static void main(String[] args) throws IOException {

        Stream<String> fileStream = Files.lines(Paths.get(args[0]));
        Integer[] array = fileStream.map(Integer::valueOf)
                .sorted()
                .toArray(size -> new Integer[size]);

        int len = array.length;
        int median = (len % 2 != 0)
                ?  array[(len - 1) / 2]
                : (array[(len - 1) / 2] + array[len / 2]) / 2;

        Stream<Integer> stream = Stream.of(array);
        int sum = stream.map(elem -> Math.abs(elem - median))
                .reduce(0, Integer::sum);
        System.out.println(sum);
    }
}
