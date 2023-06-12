package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.util;

import java.util.Arrays;

public enum RegistrationStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private final String description;

    RegistrationStatus(String description) {
        this.description = description;
    }

    public static RegistrationStatus fromString(String status) {
        return Arrays.stream(RegistrationStatus.values())
                .filter(s -> s.toString().equalsIgnoreCase(status))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("This is not a valid status!"));
    }

    @Override
    public String toString() {
        return description;
    }
}
