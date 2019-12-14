package pl.senderek.gieldal2.tester.exception;
/**
 * Wyjątek zgłaszany przy próbie wywołania autoryzowanej metody bez wcześniejszego uzyskania tokenu
 */
public class ServiceUnauthorizedException extends RuntimeException {
    public ServiceUnauthorizedException() {
        super("Service unauthorized");
    }
}
