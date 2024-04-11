import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

class Contact {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + emailAddress;
    }
}

public class ContactManagerGUI extends JFrame {
    private Map<String, Contact> contacts;
    private JTextArea contactListTextArea;

    public ContactManagerGUI() {
        super("Contact Manager");

        contacts = new HashMap<>();
        contactListTextArea = new JTextArea(20, 40);
        contactListTextArea.setEditable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addBtn = new JButton("Add Contact");
        addBtn.addActionListener(e -> addContact());

        JButton viewBtn = new JButton("View Contacts");
        viewBtn.addActionListener(e -> viewContacts());

        JButton editBtn = new JButton("Edit Contact");
        editBtn.addActionListener(e -> editContact());

        JButton deleteBtn = new JButton("Delete Contact");
        deleteBtn.addActionListener(e -> deleteContact());

        panel.add(addBtn, BorderLayout.NORTH);
        panel.add(viewBtn, BorderLayout.WEST);
        panel.add(editBtn, BorderLayout.CENTER);
        panel.add(deleteBtn, BorderLayout.EAST);

        JScrollPane scrollPane = new JScrollPane(contactListTextArea);
        panel.add(scrollPane, BorderLayout.SOUTH);

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addContact() {
        String name = JOptionPane.showInputDialog("Enter name:");
        String phoneNumber = JOptionPane.showInputDialog("Enter phone number:");
        String emailAddress = JOptionPane.showInputDialog("Enter email address:");

        contacts.put(name, new Contact(name, phoneNumber, emailAddress));
        JOptionPane.showMessageDialog(this, "Contact added successfully.");
    }

    private void viewContacts() {
        StringBuilder contactsText = new StringBuilder();
        if (contacts.isEmpty()) {
            contactsText.append("Contact list is empty.");
        } else {
            contactsText.append("Contact List:\n");
            for (Contact contact : contacts.values()) {
                contactsText.append(contact).append("\n");
            }
        }
        contactListTextArea.setText(contactsText.toString());
    }

    private void editContact() {
        String name = JOptionPane.showInputDialog("Enter name of contact to edit:");
        if (contacts.containsKey(name)) {
            String newPhoneNumber = JOptionPane.showInputDialog("Enter new phone number:");
            String newEmailAddress = JOptionPane.showInputDialog("Enter new email address:");
            Contact contact = contacts.get(name);
            contact.setPhoneNumber(newPhoneNumber);
            contact.setEmailAddress(newEmailAddress);
            JOptionPane.showMessageDialog(this, "Contact updated successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Contact not found.");
        }
    }

    private void deleteContact() {
        String name = JOptionPane.showInputDialog("Enter name of contact to delete:");
        if (contacts.containsKey(name)) {
            contacts.remove(name);
            JOptionPane.showMessageDialog(this, "Contact deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Contact not found.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ContactManagerGUI::new);
    }
}
