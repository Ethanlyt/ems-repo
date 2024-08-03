package atoz.interview.ems.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import atoz.interview.ems.Entity.Auth;
import atoz.interview.ems.Entity.User;
import atoz.interview.ems.Repository.UserRepo;
import atoz.interview.ems.Service.AuthService;
import atoz.interview.ems.util.JwtUtil;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return "User registered successfully";
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @PostMapping("/login")
    public String loginUser(@RequestBody Auth auth) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(auth.getName(),
                            auth.getPassword()));

            final UserDetails userDetails = authService.loadUserByUsername(auth.getName());
            final String jwt = jwtUtil.generateToken(userDetails);
            return jwt;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/test")
    public String hello() {
        return "Testing Authentication!";
    }
}
