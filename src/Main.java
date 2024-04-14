import com.sun.jdi.Value;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        System.out.println("Люди младше 18 лет.");
        long stream = persons.stream()
                .filter(value -> value.getAge() < 18)
                .count();
        System.out.println(stream);
        System.out.println("Список мужчин от 18 до 27 лет:");
        List<String> list = persons.stream()
                .filter(value -> value.getAge() <= 27)
                .filter(value -> value.getAge() >= 18)
                .filter(value -> value.getSex() == Sex.MAN)
                .map(value -> value.getFamily())
                .collect(Collectors.toList());
        System.out.println(list);

        List<Person> list1 = persons.stream()
                .filter(value -> value.getAge() >= 18)
                .filter(value -> value.getSex() == Sex.MAN && value.getAge() < 65 || value.getSex() == Sex.WOMAN && value.getAge() < 60)
                .filter(value -> value.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(value -> value.getFamily()))
                .collect(Collectors.toList());
        System.out.println(list1);
    }
}