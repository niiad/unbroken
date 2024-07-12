package com.niiad.unbroken.domain.specs;

@FunctionalInterface
public interface Specification<T> {
    boolean isSatisfiedBy(T t);

    default Specification<T> and(Specification<T> specification) {
        return t -> this.isSatisfiedBy(t) && specification.isSatisfiedBy(t);
    }

    default Specification<T> or(Specification<T> specification) {
        return t -> this.isSatisfiedBy(t) || specification.isSatisfiedBy(t);
    }
}
