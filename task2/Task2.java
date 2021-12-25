import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Task2 {

    private static float x0, y0, r;

    private static int getResult(float x, float y) {
        float deltaX = x - x0;
        float deltaY = y - y0;
        float distance = deltaX * deltaX + deltaY * deltaY;

        if (distance < r * r) return 1;
        if (distance > r * r) return 2;
        return 0;
    }

    public static void main(String[] args) throws IOException {

        File file1 = new File(args[0]);
        Scanner scanner = new Scanner(file1);

        x0 = scanner.nextFloat();
        y0 = scanner.nextFloat();
        r  = scanner.nextFloat();

        File file2 = new File(args[1]);
        scanner = new Scanner(file2);

        while (scanner.hasNextFloat()) {
            float x = scanner.nextFloat();
            float y = scanner.nextFloat();
            System.out.println(getResult(x, y));
        }
    }

}
