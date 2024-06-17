import java.time.LocalDate;

class Reservation {
    private static int idCounter = 1;
    private int reservationID;
    private Room room;
    private String guestName;
    private String guestSurname;
    private LocalDate startDate;
    private LocalDate endDate;
    private ReservationStatus status;

    public Reservation(Room room, String guestName, String guestSurname, LocalDate startDate, LocalDate endDate, ReservationStatus status) {
        this.reservationID = idCounter++;
        this.room = room;
        this.guestName = guestName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public int getReservationID() {
        return reservationID;
    }

    public Room getRoom() {
        return room;
    }

    public String getGuestName() {
        return guestName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public void getReservationDetails() {
        System.out.println("Reservation ID: " + reservationID);
        System.out.println("Guest Name: " + guestName + " " + guestSurname);
        System.out.println("Room Number: " + room.getRoomNumber());
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("Status: " + status);
        System.out.println("Total Price: " + calculateTotalPrice());
    }

    public double calculateTotalPrice() {
        return room.getPricePerNight() * (endDate.toEpochDay() - startDate.toEpochDay());
    }

    public boolean checkOverlap(LocalDate newStartDate, LocalDate newEndDate) {
        return !(newEndDate.isBefore(startDate) || newStartDate.isAfter(endDate));
    }
}
