package com.example.secuxper.controller;


import com.example.secuxper.apilog.ApiLog;
import com.example.secuxper.data.User;
import com.example.secuxper.service.UserService;
import com.example.secuxper.service.apilog.ApiLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ApiLogService apiLogService;

    @PostMapping("/add")
    public String add(@RequestBody User user) {
        userService.saveUser(user);
        // Time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        String formattedDateTime = now.format(formatter);

        // ip
        String ipAddress = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest().getRemoteAddr();
        apiLogService.addLog(new ApiLog(formattedDateTime, "/add", ipAddress));

        return "Saved student!";
    }

    @GetMapping("/all")
    public List<User> getAll() {
        return userService.getAllUser();
    }
}
