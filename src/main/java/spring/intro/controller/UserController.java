package spring.intro.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spring.intro.UserResponseDto;
import spring.intro.model.User;
import spring.intro.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<UserResponseDto> getAll() {
        List<UserResponseDto> userDtoList = new ArrayList<>();
        for (User user : userService.listUsers()) {
            String name = user.getName();
            String email = user.getEmail();
            userDtoList.add(new UserResponseDto(name, email));
        }
        return userDtoList;
    }

    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        User user = userService.get(id);
        String email = user.getEmail();
        String name = user.getName();
        return new UserResponseDto(name, email);

    }

    @RequestMapping(value = "/inject", method = RequestMethod.GET)
    public String injectUsers() {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Kolia");
        user1.setEmail("newkoliaemail@email");
        user1.setPassword("111");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Vasia");
        user2.setEmail("newvasiaemail@email");
        user2.setPassword("111");

        User user3 = new User();
        user3.setId(3L);
        user3.setName("Serj");
        user3.setEmail("newserjemail@email");
        user3.setPassword("111");

        User user4 = new User();
        user4.setId(4L);
        user4.setName("Bogdan");
        user4.setEmail("newbogdanemail@email");
        user4.setPassword("111");

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);
        return "injection complete";
    }
}
