package test.db.service.validation;

public interface Validation<T> {
    boolean validate(T t);
    String getValidationMessage();
}
