package com.aegis.safespace.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

//    private final UserService userService;

//    @GetMapping("/me")
//    public ResponseEntity<UserResponseDTO> getCurrentUser(Authentication authentication) {
//        String email = authentication.getName();
//        UserResponseDTO user = userService.getUserByEmail(email);
//        return ResponseEntity.ok(user);
//    }
//
//    @PutMapping("/me")
//    public ResponseEntity<UserResponseDTO> updateCurrentUser(
//            Authentication authentication,
//            @RequestBody UserDTO userDTO
//    ) {
//        String email = authentication.getName();
//        UserResponseDTO existingUser = userService.getUserByEmail(email);
//        UserResponseDTO updatedUser = userService.updateUser(existingUser.getId(), userDTO);
//        return ResponseEntity.ok(updatedUser);
//    }
//
//    @PostMapping("/me/change-password")
//    public ResponseEntity<String> changePassword(
//            Authentication authentication,
//            @RequestBody ChangePasswordDTO changePasswordDTO
//    ) {
//        String email = authentication.getName();
//        userService.changePassword(email, changePasswordDTO);
//        return ResponseEntity.ok("Password changed successfully");
//    }
//
//    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
//        List<UserResponseDTO> users = userService.getAllUsers();
//        return ResponseEntity.ok(users);
//    }
//
//    @GetMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable UUID id) {
//        return ResponseEntity.ok(userService.getUserById(id));
//    }
//
//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
//        userService.deleteUser(id);
//        return ResponseEntity.noContent().build();
//    }
}
