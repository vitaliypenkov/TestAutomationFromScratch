package generic.collection.comparator;

import org.testng.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vitaliyp on 3/17/2016.
 */
public class GenericCollectionComparator {
    private static int i;
    private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();

    public static void compareCollections(Collection<?> actual, Collection<?> expected) throws IllegalAccessException, InstantiationException {
        Assert.assertEquals(actual.size(), expected.size(), "Collections size differ");

        if (actual.size() == 0) {
            return;
        }

        for (i = 0; i < actual.size(); i++) {
            Object aElement = actual.toArray()[i];
            Object eElement = expected.toArray()[i];

            Class<?> aClass = aElement.getClass();
            Class<?> eClass = eElement.getClass();
            Assert.assertEquals(aClass, eClass, String.format("Elements at index: %d are of different types", i));

            System.out.println();
            System.out.println(String.format("Verifying element at index: %d", i));
            compareObjects(aElement, eElement);
        }
    }

    private static void compareObjects(Object a, Object e) {
        Field[] aFields = a.getClass().getDeclaredFields();
        for (Field aField : aFields) {
            Object aValue = runGetter(aField, a);
            Object eValue = runGetter(aField, e);
            if (isWrapperType(aField.getType())) {
                printOutput(aField, aValue, eValue);

                Assert.assertEquals(aValue, eValue, String.format("Element index: %d, field name: %s", i, aField.getName()));
            } else {
                compareObjects(aValue, eValue);
            }
        }
    }

    private static void printOutput(Field aField, Object aValue, Object eValue) {
        System.out.println(String.format("Field - %s:",aField.getName()));
        System.out.println(String.format("Expected: \t%s", eValue));
        System.out.println(String.format("But was: \t%s", aValue));
        System.out.println();
    }

    private static boolean isWrapperType(Class<?> clazz) {
        return WRAPPER_TYPES.contains(clazz);
    }

    private static Set<Class<?>> getWrapperTypes() {
        Set<Class<?>> wrapperTypes = new HashSet<>();
        wrapperTypes.add(Boolean.class);
        wrapperTypes.add(Character.class);
        wrapperTypes.add(Byte.class);
        wrapperTypes.add(Short.class);
        wrapperTypes.add(Integer.class);
        wrapperTypes.add(Long.class);
        wrapperTypes.add(Float.class);
        wrapperTypes.add(Double.class);
        wrapperTypes.add(Void.class);
        wrapperTypes.add(String.class);
        return wrapperTypes;
    }

    private static Object runGetter(Field field, Object o) {
        for (Method method : o.getClass().getMethods()) {
            if ((method.getName().startsWith("get")) && (method.getName().toLowerCase().endsWith(field.getName().toLowerCase()))) {
                try {
                    return method.invoke(o);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.out.println("Could not determine method: " + method.getName());
                }
            }
        }
        return null;
    }
}

