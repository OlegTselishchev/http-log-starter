package com.example.httploger.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/v1/rest/")
public class TestController {

    private static List<String> storage = new ArrayList<>();

    static {
        storage.addAll(Arrays.asList("apple", "chery"));
    }

    @Operation(description = "Get item.")
    @GetMapping("/test")
    public List<String> getInformation() {
        try {
            int timeout = ThreadLocalRandom.current().nextInt(3, 9);
            TimeUnit.SECONDS.sleep(timeout);
            return storage;
        }catch (Exception e) {
            return Arrays.asList(e.getMessage());
        }
    }

    @Operation(description = "Add item.")
    @PostMapping("/test")
    public List<String> addInformation(@RequestBody String info) {
        try {
            int timeout = ThreadLocalRandom.current().nextInt(3, 9);
            TimeUnit.SECONDS.sleep(timeout);
            storage.add(info);
            return storage;
        }catch (Exception e) {
            return Arrays.asList(e.getMessage());
        }
    }

    @Operation(description = "Delete item by id")
    @DeleteMapping("/test/{id}")
    public List<String> deleteInformation(@PathVariable int id) {
        try {
            int timeout = ThreadLocalRandom.current().nextInt(3, 9);
            TimeUnit.SECONDS.sleep(timeout);
            storage.remove(id);
            return storage;
        }catch (Exception e) {
            return Arrays.asList(e.getMessage());
        }
    }
}
