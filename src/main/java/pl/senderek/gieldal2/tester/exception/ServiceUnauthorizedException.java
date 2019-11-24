package pl.senderek.gieldal2.tester.exception;

public class ServiceUnauthorizedException extends RuntimeException {
    public ServiceUnauthorizedException() {
        super("Service unauthorized");
    }
}
