package moviereservation;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

public class ReservationSystemUnitTests {

    @Test
    public void testTicketPricingFormat() {
        //Create a ticket with a price of 15.5 (it's missing the zero)
        BigDecimal rawPrice = new BigDecimal("15.5");
        Ticket ticket = new Ticket("Adult", rawPrice);
        
        //Get the formatted ticket details
        String details = ticket.getTicketDetails();
        
        //Verifying that the system properly formats the price to "$15.50"
        assertTrue(details.contains("$15.50"), "Test Failed: Ticket price did not format to exactly two decimal places.");
    }

    @Test
    public void testBookingConfirmationInvalidData() {
        //Set up empty, invalid reservation data
        String emptyReservationData = "";
        String customerEmail = "testcustomer@email.com";
        
        //Attempt to generate a confirmation and verify the system throws the correct error
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new BookingConfirmation(customerEmail, emptyReservationData);
        });
        
        //Verify the error message matches our validation gate
        assertTrue(exception.getMessage().contains("Validation Error: Reservation is invalid"), 
                   "Test Failed: System did not block the invalid confirmation.");
    }
}