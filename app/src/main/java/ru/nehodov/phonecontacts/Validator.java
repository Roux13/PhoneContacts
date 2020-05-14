package ru.nehodov.phonecontacts;

public interface Validator<T> {

    boolean validate(T value);
}
