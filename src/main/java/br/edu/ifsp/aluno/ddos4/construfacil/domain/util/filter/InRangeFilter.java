package br.edu.ifsp.aluno.ddos4.construfacil.domain.util.filter;

import java.util.Objects;

public class InRangeFilter<A extends Comparable<A>> implements Filter<A> {
    private A minValue;
    private A maxValue;

    public InRangeFilter() {
    }

    public InRangeFilter(A minValue, A maxValue) {
        setMinValue(minValue);
        setMaxValue(maxValue);
    }

    public InRangeFilter(A value) {
        this(value, value);
    }

    @Override
    public boolean applyTo(A value) {
        Objects.requireNonNull(value);

        if (minValue == null)
            throw new IllegalStateException("No min value has been added!");
        if (maxValue == null)
            throw new IllegalStateException("No max value has been added!");
        
        return value.compareTo(minValue) >= 0 && value.compareTo(maxValue) <= 0;
    }

    public A getMinValue() {
        return minValue;
    }

    public void setMinValue(A minValue) {
        Objects.requireNonNull(minValue);

        if (maxValue != null && maxValue.compareTo(minValue) < 0)
            throw new IllegalArgumentException("The min value cannot be after max value!");

        this.minValue = minValue;
    }

    public A getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(A maxValue) {
        Objects.requireNonNull(maxValue);

        if (minValue != null && minValue.compareTo(maxValue) > 0)
            throw new IllegalArgumentException("The max value cannot be before min value!");

        this.maxValue = maxValue;
    }
}
