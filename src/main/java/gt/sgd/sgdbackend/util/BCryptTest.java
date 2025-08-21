package gt.sgd.sgdbackend.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin123";
        String storedHash = "$2a$10$E9xQ9zYwFJ7uPqZ1Kz1eUeGZJv8xYzL8gFZbQJvZ1Kz1eUeGZJv8xY";

        boolean matches = encoder.matches(rawPassword, storedHash);
        System.out.println("¿Coincide? " + matches);
        
        BCryptPasswordEncoder encoder2 = new BCryptPasswordEncoder();
        System.out.println(encoder2.encode("admin123"));
    }

}
