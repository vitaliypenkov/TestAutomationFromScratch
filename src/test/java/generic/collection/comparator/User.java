package generic.collection.comparator;

public class User{
    private String name;
    private Gender gender;
    private String email;
    private Integer age;

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public User setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public User setAge(Integer age) {
        this.age = age;
        return this;
    }
}

class Gender{
    private String gender;

    public String getGender() {
        return gender;
    }

    public Gender setMale() {
        this.gender = "male";
        return this;
    }

    public Gender setFemale() {
        this.gender = "female";
        return this;
    }
}
