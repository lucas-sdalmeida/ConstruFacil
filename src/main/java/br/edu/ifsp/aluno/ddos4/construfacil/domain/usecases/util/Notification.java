package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util;

import java.util.ArrayList;
import java.util.List;

public class Notification {
    private final List<String> messages = new ArrayList<>();

    public boolean hasMessages() {
        return !messages.isEmpty();
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public String getMessagesAsString() {
        StringBuilder stringBuilder = new StringBuilder();

        messages.forEach(message -> stringBuilder.append(message).append('\n'));

        return stringBuilder.toString();
    }
}
