package ru.senkinay.client.model;

import ru.senkinay.clientserver.Command;

public interface ReadCommandListener {

    void processReceivedCommand(Command command);

}
