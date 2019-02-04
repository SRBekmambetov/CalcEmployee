import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Read {

    private static List<Employee> listEmployee = new ArrayList<>();
    private static Map<String, List<Integer>> averSalaryMap = new HashMap<>();

    private static void reader() {
        File file = new File("sample.txt");
        FileReader fileReader = null; // поток который подключается к текстовому файлу
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e1) {
            System.out.println("Файл не найден");
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader); // соединяем FileReader с BufferedReader
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                Employee newEmployee = new Employee();
                String[] arr = line.split("\\|");
                newEmployee.setId(Integer.parseInt(arr[0]));
                newEmployee.setFirstName(arr[1]);
                newEmployee.setSecondName(arr[2]);
                newEmployee.setMiddleName(arr[3]);
                newEmployee.setDepartment(arr[4]);
                newEmployee.setSalary(Integer.parseInt(arr[5]));
                listEmployee.add(newEmployee);
            }
            bufferedReader.close(); // закрываем поток
        } catch (IOException e) {
            System.out.println("Файл не доступен");
        }
    }
    
    private static void salaryDepartment() {
        for (Employee employee: listEmployee) {
            String department = employee.getDepartment();
            if (averSalaryMap.containsKey(department)) {
                List<Integer> countAndSummSalaryList = averSalaryMap.get(department);
                countAndSummSalaryList.set(0, countAndSummSalaryList.get(0) + 1);
                countAndSummSalaryList.set(1, countAndSummSalaryList.get(1) + employee.getSalary());
                averSalaryMap.put(department, countAndSummSalaryList);
            } else {
                List<Integer> countAndSummSalaryList = new ArrayList<>();
                countAndSummSalaryList.add(1);
                countAndSummSalaryList.add(employee.getSalary());
                averSalaryMap.put(department, countAndSummSalaryList);
            }
        }
        calculateAndPrintAverageSalary();
    }

    private static void calculateAndPrintAverageSalary() {
        for (Map.Entry entry : averSalaryMap.entrySet()) {
            System.out.print("Department: " + entry.getKey() + ", ");
            List<Integer> countAndSummSalaryList = (List<Integer>) entry.getValue();
            int average = countAndSummSalaryList.get(1) / countAndSummSalaryList.get(0);
            System.out.println("Average Salary: " + average);
        }
    }

    public static void main(String[] args) {
        reader();
        for (Employee employee: listEmployee) {
            System.out.println(employee);
        }
        System.out.println();
        salaryDepartment();
    }
}
