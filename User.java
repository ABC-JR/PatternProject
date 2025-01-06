public class User {
    private String name;
    private String password;
    private int age;
    private double height;
    private double weight;


    public User() {}


    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public static class Builder {
        private User user;

        public Builder() {
            user = new User();
        }

        public Builder setName(String name) {
            user.name = name;
            return this;
        }

        public Builder setPassword(String password) {
            user.password = password;
            return this;
        }

        public Builder setAge(int age) {
            user.age = age;
            return this;
        }

        public Builder setHeight(double height) {
            user.height = height;
            return this;
        }

        public Builder setWeight(double weight) {
            user.weight = weight;
            return this;
        }

        public User build() {
            return user;
        }
    }
}

