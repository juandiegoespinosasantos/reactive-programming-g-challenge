package com.juandiegoespinosasantos.challenges.reactive_programming.use_cases;

/**
 * @author juandiegoespinosasantos@gmail.com
 * @version Aug 23, 2023
 * @since 17
 */
public abstract class AbstractUseCase<T> {

    protected abstract T process();

    protected void validate() throws Exception {
    }

    public T execute() {
        try {
            validate();

            return process();
        } catch (Exception ex) {
            return null;
        }
    }
}