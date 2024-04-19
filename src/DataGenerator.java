import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DataGenerator {

    public static void main(String[] args) {
        generateAndSaveDataSets(50, 100, 10000);
    }

    public static void generateAndSaveDataSets(int numSets, int minSize, int maxSize) {
        for (int i = 1; i <= numSets; i++) {
            int size = new Random().nextInt(maxSize - minSize + 1) + minSize;
            int[] dataSet = generateRandomDataSet(size);
            String filepath = "/Users/gul/IdeaProjects/Semestrovka/src/dataSets/data_set_" + i + ".txt";
            saveDataSetToFile(dataSet, filepath);
        }
    }

    public static int[] generateRandomDataSet(int size) {
        int[] dataSet = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            dataSet[i] = random.nextInt();
        }
        return dataSet;
    }

    public static void saveDataSetToFile(int[] dataSet, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int data : dataSet) {
                writer.write(String.valueOf(data));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}