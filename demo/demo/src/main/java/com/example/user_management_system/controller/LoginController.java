import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

// Authentication DTO request
class AuthRequest {
    public String username;
    public String password;
}

// REST endpoints handling validation and secure message routing
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AppController {

    private AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public void LoginController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    // Public Route: Generates token upon successful credential validation
    @PostMapping("/auth/login")
    public String login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username, request.password)
        );
        return jwtUtil.generateToken(request.username);
    }

    // Protected Route: Requires valid JWT inclusion in request header
    @GetMapping("/protected/hello")
    public String secureEndpoint() {
        return "Access Granted! You hit a secure endpoint with a valid JWT token.";
    }
}
