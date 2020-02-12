package spring.intro;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.intro.config.AppConfig;
import spring.intro.model.User;
import spring.intro.service.UserService;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        userService.listUsers().forEach(System.out::println);

        User user1 = new User();
        user1.setName("Kolia");
        user1.setEmail("newEmail");
        user1.setPassword("bestPassword");
        userService.add(user1);

        User user2 = new User();
        user2.setName("Vasia");
        user2.setEmail("anotherNewEmail");
        user2.setPassword("simplePassword");
        userService.add(user2);

        userService.listUsers().forEach(System.out::println);
    }
}
