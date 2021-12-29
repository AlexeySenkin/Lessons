package java2.lesson3;

import java.util.*;

public class PhonesBook implements IPhonesBook {
    public final Map<String , Set<String>> phonesByName  = new TreeMap<>();

    @Override
    public void add(String name, String phoneNumber) {
        if (!(Objects.equals(name, "") || Objects.equals(phoneNumber, ""))) {
            Set<String> phones = phonesByName.getOrDefault(name, new HashSet<>());
            phones.add(phoneNumber);
            this.phonesByName.put(name, phones);
        }
    }

    @Override
    public Set<String> get(String name) {
        return this.phonesByName.get(name);
    }

    @Override
    public Set<String> getNames() {
        return this.phonesByName.keySet();
    }
}
