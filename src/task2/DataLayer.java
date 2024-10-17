package task2;

import java.util.HashMap;
import java.util.Map;

public class DataLayer {
    private Map<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact contact) {
        contacts.put(contact.getName(), contact);
    }

    public void updateContact(String name, Contact updatedContact) {
        if (contacts.containsKey(name)) {
            contacts.put(name, updatedContact);
        }
    }

    public void deleteContact(String name) {
        contacts.remove(name);
    }

    public Contact getContact(String name) {
        return contacts.get(name);
    }
    
}
