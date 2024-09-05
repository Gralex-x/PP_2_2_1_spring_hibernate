package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        user1.setCar(new Car("Sollicitudinaptent", 369));
        userService.add(user1);

        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        user2.setCar(new Car("Atortor", 818));
        userService.add(user2);

        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        user3.setCar(new Car("Disconsectetur", 84));
        userService.add(user3);

        User user4 = new User("User4", "Lastname4", "user4@mail.ru");
        user4.setCar(new Car("Galaxy", 90));
        userService.add(user4);

        User user5 = new User("User5", "Lastname5", "user5@mail.ru");
        user5.setCar(new Car("Galaxy", 90));
        userService.add(user5);

        List<User> users = userService.listUsers();
        printUsers(users);

        List<User> users2 = userService.getUsersByCarModelAndSeries("Galaxy", 90);

        printUsers(users2);

        context.close();
    }

    private static void printUsers(List<User> users2) {
        for (User user : users2) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }
    }
}
