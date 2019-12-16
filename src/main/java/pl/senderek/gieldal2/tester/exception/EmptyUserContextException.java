package pl.senderek.gieldal2.tester.exception;

/**
 * Wyjątek zgłaszany przy próbie wywołania autoryzowanej metody bez wcześniejszego uzyskania tokenu
 */
public class EmptyUserContextException extends RuntimeException {
    public EmptyUserContextException() {
        super("User context is empty");
    }
}
