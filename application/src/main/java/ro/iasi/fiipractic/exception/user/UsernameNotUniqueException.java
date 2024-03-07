package ro.iasi.fiipractic.exception.user;

public class UsernameNotUniqueException extends RuntimeException {

    public UsernameNotUniqueException(String message) {
        super(message);
    }
}
