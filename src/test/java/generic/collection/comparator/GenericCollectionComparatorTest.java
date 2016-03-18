package generic.collection.comparator;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitaliyp on 3/18/2016.
 */
public class GenericCollectionComparatorTest {

    @Test(expectedExceptions = AssertionError.class)
    public void compareCollections_collectionsOfDifferentData_assertionError() throws IllegalAccessException, InstantiationException {
        // arrange
        List<User> actualUserList = new ArrayList<>();
        actualUserList.add(
                new User()
                        .setName("Sub-Zero")
                        .setAge(23)
                        .setGender(new Gender().setMale())
                        .setEmail("fake1@gmail.com"));

        actualUserList.add(
                new User()
                        .setName("Scorpion")
                        .setAge(50)
                        .setGender(new Gender().setMale())
                        .setEmail("fake2@gmail.com"));


        List<User> expectedUserList = new ArrayList<>();
        expectedUserList.add(
                new User()
                        .setName("Sub-Zero")
                        .setAge(23)
                        .setGender(new Gender().setMale())
                        .setEmail("fake1@gmail.com"));

        expectedUserList.add(
                new User()
                        .setName("Scorpion")
                        .setAge(51)
                        .setGender(new Gender().setFemale())
                        .setEmail("fake2@gmail.com"));

        // act & assert
        GenericCollectionComparator.compareCollections(actualUserList, expectedUserList);
    }

    @Test
    public void compareCollections_emptyCollections_match() throws IllegalAccessException, InstantiationException {
        // arrange
        List<User> actualList = new ArrayList<>();
        List<User> expectedList = new ArrayList<>();

        // act & assert
        GenericCollectionComparator.compareCollections(actualList, expectedList);
    }

    @Test(expectedExceptions = AssertionError.class)
    public void compareCollections_collectionsOfDifferentType_assertionError() throws IllegalAccessException, InstantiationException {
        // arrange
        List<Integer> actualList = new ArrayList<>();
        actualList.add(5);
        List<String> expectedList = new ArrayList<>();
        expectedList.add("5");

        // act & assert
        GenericCollectionComparator.compareCollections(actualList, expectedList);
    }

    @Test(expectedExceptions = AssertionError.class)
    public void compareCollections_collectionsOfDifferentSize_assertionError() throws IllegalAccessException, InstantiationException {
        // arrange
        List<Integer> actualList = new ArrayList<>();
        actualList.add(5);
        actualList.add(1);
        List<Integer> expectedList = new ArrayList<>();
        actualList.add(5);

        // act & assert
        GenericCollectionComparator.compareCollections(actualList, expectedList);
    }
}
