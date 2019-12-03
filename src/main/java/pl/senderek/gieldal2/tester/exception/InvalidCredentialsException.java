package pl.senderek.gieldal2.tester.exception;

import pl.senderek.gieldal2.tester.dto.AuthDTO;
/**
 * Wyjątek zgłaszany w przypadku próby autoryzacji nieistniejącego użytkownika
 */
public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(AuthDTO user) {
        super("Cannot authorize user \"" + user.getUserName() + "\" with password \"" + user.getPassword() + "\"");
    }
}
