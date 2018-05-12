package test;

public interface Validatable<T> {
    boolean validate(T entity);
}
