import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Database implements Observer {
    boolean reminder = true;
    @Override
    public void register(String notify) {

        for (String userName : users.keySet()) {
            System.out.println(userName + " " + notify );
        }
    }






    private static Database instance;
    private Map<String, String> users = new HashMap<>();
    private Map<String, User> userData = new HashMap<>();





    private Database() {}

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void addUser(User user) {
        users.put(user.getName(), user.getPassword());
        userData.put(user.getName(), user); // Добавляем профиль пользователя

    }

    public void removeUser(String userName) {
        users.remove(userName);
        userData.remove(userName);


    }

    public void putLocaldata(User user) {
        userData.put(user.getName(), user);
    }

    public int getage(String name) {
        return userData.get(name).getAge();
    }

    public double getw(String name) {
        return userData.get(name).getWeight();
    }

    public double gethegt(String name) {
        return userData.get(name).getHeight();
    }

    public boolean authenticate(String name, String password) {
        return users.containsKey(name) && users.get(name).equals(password);
    }

    public boolean userExists(String name) {
        return users.containsKey(name);
    }
    public void showAlluser() {

        for (String userName : users.keySet()) {
            System.out.println(userName);
        }

    }

}



interface Observer {
    void register(String notify);
    void addUser(User user);
    void removeUser(String userName);
    void showAlluser();
}

