
package com.clonair.back.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getOne(@PathVariable String id, @RequestHeader(name = HttpHeaders.AUTHORIZATION) String token) throws Exception {
        return ResponseEntity.ok(userService.getOne(id, token));
    }

    @GetMapping()
    public ResponseEntity<List<UserResponse>> getAll(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token) throws Exception {
        return ResponseEntity.ok(userService.getAll(token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id, @RequestHeader(name = HttpHeaders.AUTHORIZATION) String token) {
        try {
            userService.delete(id, token);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
