import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestHash {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = "$2a$10$cCnrsaPA6/eGtrDHuOvcMeZFor9T.nk3Dq9AN/j4S6D46wdjBd/OO";
        System.out.println("Matches: " + encoder.matches("admin123", hash));
        System.out.println("New Hash: " + encoder.encode("admin123"));
    }
}
