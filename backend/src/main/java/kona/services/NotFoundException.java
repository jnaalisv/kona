package kona.services;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 8481975297152064490L;

    public NotFoundException(Class<?> notFoundType, String criteria) {
        super(notFoundType.getSimpleName() + " not found by " + criteria);
    }

    public static NotFoundException byId(Class<?> notFoundType, long id) {
        return new NotFoundException(notFoundType, " id="+id);
    }

}
