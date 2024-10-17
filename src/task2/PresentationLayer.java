package task2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PresentationLayer extends JFrame implements ActionListener {
    private JTextField nameField, addressField, phoneField;
    private JButton addButton, updateButton, deleteButton, viewButton;
    private JTextArea displayArea;
    private JScrollPane scrollPane;
    private BusinessLogicLayer businessLogicLayer;

    public PresentationLayer(BusinessLogicLayer businessLogicLayer) {
        super("Personal Address Book");
        this.businessLogicLayer = businessLogicLayer;

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        nameField = new JTextField(20);
        addressField = new JTextField(20);
        phoneField = new JTextField(20);
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        viewButton = new JButton("View");

        displayArea = new JTextArea(10, 30);
        scrollPane = new JScrollPane(displayArea);

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Address:"));
        panel.add(addressField);
        panel.add(new JLabel("Phone:"));
        panel.add(phoneField);
        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(viewButton);
        panel.add(scrollPane);

        addButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        viewButton.addActionListener(this);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            // Add logic to handle adding a contact
        } else if (e.getSource() == updateButton) {
            // Add logic to handle updating a contact
        } else if (e.getSource() == deleteButton) {
            // Add logic to handle deleting a contact
        } else if (e.getSource() == viewButton) {
            // Add logic to handle viewing contacts
        }
    }

    public static void main(String[] args) {
        DataLayer dataLayer = new DataLayer();
        BusinessLogicLayer businessLogicLayer = new BusinessLogicLayer(dataLayer);
        new PresentationLayer(businessLogicLayer);
    }
}
