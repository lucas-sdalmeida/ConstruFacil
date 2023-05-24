package br.edu.ifsp.aluno.ddos4.construfacil.domain.util.filter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MembershipFilter<A> implements Filter<A> {
    private final Set<A> allowedValuesSet = new HashSet<>();
    private final Set<A> forbiddenValuesSet = new HashSet<>();

    @Override
    public final boolean applyTo(A value) {
        Objects.requireNonNull(value);

        if (allowedValuesSet.isEmpty())
            return !forbiddenValuesSet.contains(value);

        return allowedValuesSet.contains(value);
    }

    public final void addToAllowedValuesSet(A value) {
        Objects.requireNonNull(value);

        if (forbiddenValuesSet.contains(value))
            throw new ValueAlreadyFilteredException(
                "Cannot add such value because it is on the forbidden values set!"
            );

        allowedValuesSet.add(value);
    }

    public final void removeFromAllowedValuesSet(A value) {
        Objects.requireNonNull(value);

        allowedValuesSet.remove(value);
    }

    public final void addToForbiddenValuesSet(A value) {
        Objects.requireNonNull(value);

        if (allowedValuesSet.contains(value))
            throw new ValueAlreadyFilteredException(
                    "Cannot add this value because it is on the allowed values set!"
            );

        forbiddenValuesSet.add(value);
    }

    public final void removeFromForbiddenValuesSet(A value) {
        Objects.requireNonNull(value);

        forbiddenValuesSet.remove(value);
    }
}
