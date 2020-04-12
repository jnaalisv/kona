package kona.domain;

public class NotFoundException extends RuntimeException {
    static final long serialVersionUID = 5789825665837565216L;

    public NotFoundException(Class<?> notFoundType, String criteria) {
        super(notFoundType.getSimpleName() + " not found by " + criteria);
    }

    public static NotFoundException byId(Class<?> notFoundType, long id) {
        return new NotFoundException(notFoundType, " id="+id);
    }

}
