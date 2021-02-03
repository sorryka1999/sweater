package letsCode.sweater.controller;

import letsCode.sweater.domain.Role;
import letsCode.sweater.domain.User;
import letsCode.sweater.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
// access restriction to all mappings on this controller for nonAdmins
// https://docs.spring.io/spring-security/site/docs/current/reference/html5/#el-access
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    @Autowired
    UserRepo userRepo;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEdit(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String userUpdate(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam(name = "userId") User user) {
        user.setUsername(username);
        // transforming enum type array to string type array
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        // clearing set of roles before filling it again
        user.getRoles().clear();
        for (String key : form.keySet()) {
            // if user contains some roles
            if (roles.contains(key)) {
                // then we adding this enum type role to set of roles
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);
        return "redirect:/user";
    }

}
