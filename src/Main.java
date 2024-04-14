import com.sun.jdi.Value;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons= new ArrayList<>();
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
                .filter(value -> value.getSex() == Sex.MAN)
                .filter(value -> value.getAge() >= 18)
                .filter(value -> value.getAge() < 65)
                .filter(value -> value.getEducation() == Education.HIGHER)
                .collect(Collectors.toList());



        List<Person> list2 = persons.stream()
                .filter(value -> value.getSex() == Sex.WOMAN)
                .filter(value -> value.getAge() >= 18)
                .filter(value -> value.getAge() < 60)
                .filter(value -> value.getEducation() == Education.HIGHER)
                .collect(Collectors.toList());
        List<Person> list3 = new ArrayList<Person>();
        list3.addAll(list1);
        list3.addAll(list2);
        list3.stream()
        .sorted(Comparator.comparing(value -> value.getFamily()))
        .collect(Collectors.toList());
        System.out.println(list3);
        











    }
}