package pl.senderek.gieldal2.tester.exception;

import pl.senderek.gieldal2.tester.dto.AuthDTO;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(AuthDTO user) {
        super("Cannot authorize user \"" + user.getUserName() + "\" with password \"" + user.getPassword() + "\"");
    }
}
