package model;

/**
 * Model for the contact objects.
 */
public class Contact {
    int contactID;
    String contactName;
    String email;

    /**
     * Returns the contact name when the toString method is called.
     * @return String of the name of the contact.
     */
    @Override
    public String toString() {
        return(contactName);
    }

    /**
     * Constructor for the contact object.
     * @param contactID Integer ID for the contact.
     * @param contactName String name for the contact.
     * @param email String email for the contact.
     */
    public Contact(int contactID, String contactName, String email) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * Returns the ID of the contact.
     * @return Integer of the ID of the contact.
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * Sets the ID of the contact.
     * @param contactID Integer of the ID of the contact.
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * Returns the name of the contact.
     * @return String of the name of the contact.
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the name of the contact.
     * @param contactName String of the name of the contact.
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Returns the email of the contact.
     * @return String of the email of the contact.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the contact.
     * @param email String of the email of the contact.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
