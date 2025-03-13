package seedu.address.model.booking;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Represents a Booking in the booking list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Booking {
    /**
     * Represents the status of the booking.
     */


    private static int bookingIdCounter = 0;

    private int bookingId;
    private Person bookingPerson;
    private LocalDateTime bookingDate;
    private LocalDateTime bookingMadeDate;
    private Set<Tag> tags;
    private Status status;
    private String remarks;
    private int pax;

    /**
     * Creates a Booking object.
     */
    public Booking(Person bookingPerson, LocalDateTime bookingDate, Set<Tag> tags, String remarks, int pax) {
        requireAllNonNull(bookingPerson, bookingDate, tags);
        this.bookingId = bookingIdCounter;
        bookingIdCounter++;
        this.bookingPerson = bookingPerson;
        this.bookingDate = bookingDate;
        this.bookingMadeDate = LocalDateTime.now();
        this.tags = tags;
        this.status = Status.UPCOMING;
        this.remarks = remarks;
        this.pax = pax;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public LocalDateTime getBookingMadeDate() {
        return bookingMadeDate;
    }

    public Person getBookingPerson() {
        return bookingPerson;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public String getRemarks() {
        return remarks;
    }

    public int getPax() {
        return pax;
    }

    public int getBookingId() {
        return bookingId;
    }

    // for when we read from storage
    public static void setBookingIdCounter(int bookingIdCounter) {
        Booking.bookingIdCounter = bookingIdCounter;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(bookingId, bookingPerson, bookingDate, bookingMadeDate, tags, remarks, pax);
    }

    @Override
    public String toString() {
        String tagsString = tags.toString();
        if (tags.isEmpty()) {
            tagsString = "No Remarks";
        }

        return new ToStringBuilder(this)
                .add("bookingID", bookingId)
                .add("name", bookingPerson.getName())
                .add("phone", bookingPerson.getPhone())
                .add("address", bookingPerson.getAddress())
                .add("bookingDate", bookingDate)
                .add("bookedOn", bookingMadeDate)
                .add("tags", tagsString)
                .add("remarks", remarks)
                .add("status", status.toString())
                .add("pax", pax)
                .toString();
    }
}
