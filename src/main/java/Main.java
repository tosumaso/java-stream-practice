import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    List<Person> people = getPeople();

    // Imperative approach ❌

    /*

    List<Person> females = new ArrayList<>();

    for (Person person : people) {

      if (person.getGender().equals(Gender.FEMALE)) {
        females.add(person);
      }
    }

    females.forEach(System.out::println);

    */

    // Declarative approach ✅

    // Filter 条件に当てはまったCollectionのみをstreamに再編成させる

    List<Person> females = people.stream()
    	.filter(person -> person.getGender().equals(Gender.FEMALE))
    	.collect(Collectors.toList());
//    females.forEach(s -> System.out.println(s));
    
    // Sort Comparator.comparingで順番の基準になる値を決め、thenComparingで２つ目の基準を設定。reversedで昇順から降順へ。
    List<Person> sorted =
    people.stream()
    	.sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender).reversed())
    	.collect(Collectors.toList());
//    sorted.forEach(s -> System.out.println(s));

    // All match Collection全てが条件に合う場合true
    boolean allMatch =
    people.stream()
    	.allMatch(person -> person.getAge() > 1);
//    System.out.println(allMatch);
    
    // Any match Collectionのうち１つでも条件に合う場合true
    
    boolean anyMatch =
    people.stream()
    	.anyMatch(person -> person.getAge() > 130);
//    System.out.println(anyMatch);

    // None match Collection全てが条件に合わない場合true

    boolean noneMatch =
    people.stream()
    	.noneMatch(person -> person.getAge() < 6);
//    System.out.println(noneMatch);
    
    // Max 指定した値が一番大きいOptional<Collection>を返す
    
    people.stream()
    	.max(Comparator.comparing(Person::getAge));
//    	.ifPresent(s -> System.out.println(s));

    // Min　指定した値が一番小さいOptional<Collection>を返す

    people.stream()
    	.min(Comparator.comparing(Person::getAge));
//    	.ifPresent(System.out::println);
    
    // Group Collectionで指定した値でsqlのgroup byを行う
//    Map<Gender, List<Person>> groupByGender =
//    people.stream()
//    	.collect(Collectors.groupingBy(Person::getGender));
//    	groupByGender.forEach((gender,person) -> {
//    		System.out.println(gender);
//    		person.forEach(System.out::println);
//    		System.out.println();
//    	});

    people.stream()
    	.filter(person -> person.getGender().equals(Gender.MALE))
    	.max(Comparator.comparing(Person::getAge))
    	.ifPresent(man -> System.out.println(man.getName()));
  }
  private static List<Person> getPeople() {
    return List.of(
        new Person("Antonio", 20, Gender.MALE),
        new Person("Alina Smith", 33, Gender.FEMALE),
        new Person("Helen White", 57, Gender.FEMALE),
        new Person("Alex Boz", 14, Gender.MALE),
        new Person("Jamie Goa", 99, Gender.MALE),
        new Person("Anna Cook", 7, Gender.FEMALE),
        new Person("Zelda Brown", 120, Gender.FEMALE)
    );
  }

}
