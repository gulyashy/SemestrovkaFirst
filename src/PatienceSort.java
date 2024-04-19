import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PatienceSort {
    public static void main(String[] args) {
        try {
            FileWriter fileWriter = new FileWriter("Results.csv");

            for(int i=1;i<51;i++){
                String filename = "/Users/gul/IdeaProjects/Semestrovka/src/dataSets/data_set_"+i+".txt";
                List<Integer> dataList = readDataFromFile(filename);

                int length = dataList.size();

                long startTime = System.nanoTime();
                int iterations = patienceSort(dataList);
                long endTime = System.nanoTime();
                double time = (endTime-startTime)/1e6;
                fileWriter.write( i + "; " + length + ";" + iterations + ";" + time +"\n");
            }
            fileWriter.close();
        }catch (IOException e) {System.out.println("Ошибка при работе с файлом: " + e.getMessage());}
    }

    private static List<Integer> readDataFromFile(String filename) {
        List<Integer> dataList = new ArrayList<>();

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                int number = Integer.parseInt(scanner.nextLine());
                dataList.add(number);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + filename);
        }

        return dataList;
    }

    public static Integer patienceSort(List<Integer> arr) {
        List<List<Integer>> piles = new ArrayList<>();
        int iterations = 0;

        for (int i = 0; i < arr.size(); i++) {
            iterations++;
            if (piles.isEmpty()) {
                List<Integer> temp = new ArrayList<>();
                temp.add(arr.get(i));
                piles.add(temp);
            } else {
                int flag = 1;
                for (int j = 0; j < piles.size(); j++) {
                    iterations++;
                    if (arr.get(i) < piles.get(j).get(piles.get(j).size() - 1)) {
                        piles.get(j).add(arr.get(i));
                        flag = 0;
                        break;
                    }
                }
                if (flag == 1) {
                    List<Integer> temp = new ArrayList<Integer>();
                    temp.add(arr.get(i));
                    piles.add(temp);
                }
            }
        }
        List<Integer> sortedList = new ArrayList<Integer>();
        while (true) {
            int minInt = Integer.MAX_VALUE;
            int index = -1;
            for (int i = 0; i < piles.size(); i++) {
                iterations++;
                if (!piles.get(i).isEmpty() && minInt > piles.get(i).get(piles.get(i).size() - 1)) {
                    minInt = piles.get(i).get(piles.get(i).size() - 1);
                    index = i;
                }
            }
            if (index == -1) {
                break;
            }

            sortedList.add(minInt);
            piles.get(index).remove(piles.get(index).size() - 1);

            if (piles.get(index).isEmpty()) {
                piles.remove(index);
            }
        }

        return iterations;
    }
}