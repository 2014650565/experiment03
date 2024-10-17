package task2;

public class BusinessLogicLayer {
    private DataLayer dataLayer;

    public BusinessLogicLayer(DataLayer dataLayer) {
        this.dataLayer = dataLayer;
    }

    public void addContact(Contact contact) {
        dataLayer.addContact(contact);
    }

    public void updateContact(String name, Contact updatedContact) {
        dataLayer.updateContact(name, updatedContact);
    }

    public void deleteContact(String name) {
        dataLayer.deleteContact(name);
    }

    public Contact getContact(String name) {
        return dataLayer.getContact(name);
    }
}
