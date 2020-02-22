package kona.domain;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Class<?> notFoundType, String criteria) {
        super(notFoundType.getSimpleName() + " not found by " + criteria);
    }

    public static NotFoundException byId(Class<?> notFoundType, long id) {
        return new NotFoundException(notFoundType, " id="+id);
    }

}
