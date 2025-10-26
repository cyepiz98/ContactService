package grandStrandSystems;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactServiceTest {

    private ContactService service;

    @BeforeEach
    public void setUp() {
        service = new ContactService();
    }

    @Test
    public void testAddContact() {
        Contact contact = new Contact("001", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        assertEquals(contact, service.getContact("001"));
    }

    @Test
    public void testAddDuplicateContactId() {
        Contact contact1 = new Contact("002", "Jane", "Smith", "0987654321", "456 Elm St");
        Contact contact2 = new Contact("002", "Jake", "Stone", "1112223333", "789 Oak St");
        service.addContact(contact1);
        assertThrows(IllegalArgumentException.class, () -> service.addContact(contact2));
    }

    @Test
    public void testDeleteContact() {
        Contact contact = new Contact("003", "Mike", "Jones", "1112223333", "789 Oak St");
        service.addContact(contact);
        service.deleteContact("003");
        assertThrows(IllegalArgumentException.class, () -> service.getContact("003"));
    }

    @Test
    public void testDeleteNonexistentContact() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("999"));
    }

    @Test
    public void testUpdateFirstName() {
        Contact contact = new Contact("004", "Anna", "Lee", "4445556666", "321 Pine St");
        service.addContact(contact);
        service.updateFirstName("004", "Annabelle");
        assertEquals("Annabelle", service.getContact("004").getFirstName());
    }

    @Test
    public void testUpdateLastName() {
        Contact contact = new Contact("005", "Tom", "Brown", "7778889999", "654 Cedar St");
        service.addContact(contact);
        service.updateLastName("005", "Browning");
        assertEquals("Browning", service.getContact("005").getLastName());
    }

    @Test
    public void testUpdatePhone() {
        Contact contact = new Contact("006", "Sara", "White", "0001112222", "111 Maple St");
        service.addContact(contact);
        service.updatePhone("006", "3334445555");
        assertEquals("3334445555", service.getContact("006").getPhone());
    }

    @Test
    public void testUpdateAddress() {
        Contact contact = new Contact("007", "Leo", "King", "9998887777", "222 Birch St");
        service.addContact(contact);
        service.updateAddress("007", "333 Spruce St");
        assertEquals("333 Spruce St", service.getContact("007").getAddress());
    }

    @Test
    public void testUpdateNonexistentContact() {
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("888", "Ghost"));
    }
    
    @Test
    public void testInvalidUpdatePhone() {
        Contact contact = new Contact("008", "Amy", "Green", "1234567890", "101 Oak St");
        service.addContact(contact);
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("008", "abc"));
    }
    
    @Test
    public void testBoundaryUpdateValues() {
        Contact contact = new Contact("009", "Amy", "Green", "1234567890", "101 Oak St");
        service.addContact(contact);
        service.updateFirstName("009", "ABCDEFGHIJ");
        service.updateAddress("009", "123456789012345678901234567890");
        assertEquals("ABCDEFGHIJ", service.getContact("009").getFirstName());
        assertEquals("123456789012345678901234567890", service.getContact("009").getAddress());
    }
    
}
