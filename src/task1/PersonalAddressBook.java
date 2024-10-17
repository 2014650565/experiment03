package task1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersonalAddressBook {
    private JFrame frame;
    private JTextField textFieldName;
    private JTextField textFieldPhone;
    private JTextField textFieldAddress;
    private JTextArea textAreaContacts;
    private List<Contact> contacts;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PersonalAddressBook window = new PersonalAddressBook();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public PersonalAddressBook() {
        contacts = new ArrayList<>();
        loadContacts();
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(10, 11, 67, 14);
        frame.getContentPane().add(lblName);

        textFieldName = new JTextField();
        textFieldName.setBounds(89, 8, 166, 20);
        frame.getContentPane().add(textFieldName);
        textFieldName.setColumns(10);

        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(10, 39, 67, 14);
        frame.getContentPane().add(lblPhone);

        textFieldPhone = new JTextField();
        textFieldPhone.setBounds(89, 36, 166, 20);
        frame.getContentPane().add(textFieldPhone);
        textFieldPhone.setColumns(10);

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setBounds(10, 67, 67, 14);
        frame.getContentPane().add(lblAddress);

        textFieldAddress = new JTextField();
        textFieldAddress.setBounds(89, 64, 166, 20);
        frame.getContentPane().add(textFieldAddress);
        textFieldAddress.setColumns(10);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(e -> addContact());
        btnAdd.setBounds(262, 8, 89, 23);
        frame.getContentPane().add(btnAdd);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(e -> updateContact());
        btnUpdate.setBounds(262, 36, 89, 23);
        frame.getContentPane().add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> deleteContact());
        btnDelete.setBounds(262, 64, 89, 23);
        frame.getContentPane().add(btnDelete);

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(e -> refreshContacts());
        btnRefresh.setBounds(262, 92, 89, 23);
        frame.getContentPane().add(btnRefresh);

        textAreaContacts = new JTextArea();
        textAreaContacts.setEditable(false);
        textAreaContacts.setBounds(10, 120, 424, 160);
        frame.getContentPane().add(textAreaContacts);

        refreshContacts();
    }

    private void addContact() {
        String name = textFieldName.getText();
        String phone = textFieldPhone.getText();
        String address = textFieldAddress.getText();
        if (!name.isEmpty() && !phone.isEmpty() && !address.isEmpty()) {
            Contact contact = new Contact(name, phone, address);
            contacts.add(contact);
            saveContacts();
            refreshContacts();
            clearFields();
            JOptionPane.showMessageDialog(frame, "Contact added successfully!");
        } else {
            JOptionPane.showMessageDialog(frame, "Please fill all fields.");
        }
    }

    private void updateContact() {
        String name = textFieldName.getText();
        for (Contact c : contacts) {
            if (c.getName().equals(name)) {
                c.setPhone(textFieldPhone.getText());
                c.setAddress(textFieldAddress.getText());
                saveContacts();
                refreshContacts();
                clearFields();
                JOptionPane.showMessageDialog(frame, "Contact updated successfully!");
                return;
            }
        }
        JOptionPane.showMessageDialog(frame, "Contact not found.");
    }

    private void deleteContact() {
        String name = textFieldName.getText();
        Contact toRemove = null;
        for (Contact c : contacts) {
            if (c.getName().equals(name)) {
                toRemove = c;
                break;
            }
        }
        if (toRemove != null) {
            contacts.remove(toRemove);
            saveContacts();
            refreshContacts();
            clearFields();
            JOptionPane.showMessageDialog(frame, "Contact deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(frame, "Contact not found.");
        }
    }

    private void refreshContacts() {
        StringBuilder sb = new StringBuilder();
        for (Contact c : contacts) {
            sb.append(c).append("\n");
        }
        textAreaContacts.setText(sb.toString());
    }

    private void clearFields() {
        textFieldName.setText("");
        textFieldPhone.setText("");
        textFieldAddress.setText("");
    }

    private void saveContacts() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("contacts.txt"))) {
            for (Contact c : contacts) {
                bw.write(c.getName() + "," + c.getPhone() + "," + c.getAddress());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadContacts() {
        File file = new File("contacts.txt");
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        contacts.add(new Contact(parts[0], parts[1], parts[2]));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class Contact {
        private String name;
        private String phone;
        private String address;

        public Contact(String name, String phone, String address) {
            this.name = name;
            this.phone = phone;
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }

        public String getAddress() {
            return address;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Phone: " + phone + ", Address: " + address;
        }
    }
}