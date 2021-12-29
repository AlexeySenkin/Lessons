package java2.lesson3;

import java.util.Set;

public interface IPhonesBook {
    void add(String name, String phoneNumber);
    Set<String> get(String name);
    Set<String> getNames();
}
