import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Menu {
    private Hotel hotel;
    private Scanner scanner;
    private DateTimeFormatter dateFormatter;

    public Menu() {
        this.hotel = new Hotel();
        this.scanner = new Scanner(System.in);
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        populateSampleRooms();
    }

    private void populateSampleRooms() {
        hotel.addRoom(new StandardRoom(101, 100, RoomType.STANDARD, true, "Queen"));
        hotel.addRoom(new DeluxeRoom(102, 200, RoomType.DELUXE, true, "Ocean View"));
        hotel.addRoom(new Suite(201, 300, RoomType.SUITE, true, 2));
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. Display all rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. Cancel a reservation");
            System.out.println("4. Display all reservations");
            System.out.println("5. Exit");
            System.out.print("Please choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    displayAllRooms();
                    break;
                case 2:
                    makeReservation();
                    break;
                case 3:
                    cancelReservation();
                    break;
                case 4:
                    displayAllReservations();
                    break;
                case 5:
                    System.out.println("Thank you for using the Hotel Reservation System.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayAllRooms() {
        System.out.println("\nAll Rooms:");
        for (Room room : hotel.getRooms()) {
            room.getRoomDetails();
            System.out.println();
        }
    }

    private void makeReservation() {
        System.out.print("Enter guest name: ");
        String guestName = scanner.nextLine();

        System.out.print("Enter guest surname: ");
        String guestSurname = scanner.nextLine();

        RoomType roomType = null;
        while (roomType == null) {
            System.out.print("Enter room type (S for STANDARD, D for DELUXE, U for SUITE): ");
            try {
                roomType = getRoomTypeFromInput(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        LocalDate startDate = null;
        while (startDate == null) {
            System.out.print("Enter start date (yyyy-MM-dd): ");
            try {
                startDate = parseDate(scanner.nextLine());
            } catch (InvalidDateFormatException e) {
                System.out.println(e.getMessage());
            }
        }

        LocalDate endDate = null;
        while (endDate == null) {
            System.out.print("Enter end date (yyyy-MM-dd): ");
            try {
                endDate = parseDate(scanner.nextLine());
            } catch (InvalidDateFormatException e) {
                System.out.println(e.getMessage());
            }
        }

        Reservation reservation = hotel.makeReservation(guestName, guestSurname, roomType, startDate, endDate);
        if (reservation != null) {
            System.out.println("Reservation successful!");
            reservation.getReservationDetails();
        } else {
            System.out.println("Failed to make a reservation. No available rooms of the specified type for the given dates.");
        }
    }

    private RoomType getRoomTypeFromInput(String input) {
        switch (input) {
            case "S":
                return RoomType.STANDARD;
            case "D":
                return RoomType.DELUXE;
            case "U":
                return RoomType.SUITE;
            default:
                throw new IllegalArgumentException("Invalid room type input. Please enter S, D, or U.");
        }
    }

    private LocalDate parseDate(String dateStr) throws InvalidDateFormatException {
        try {
            return LocalDate.parse(dateStr, dateFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    private void cancelReservation() {
        System.out.print("Enter reservation ID to cancel: ");
        int reservationID = scanner.nextInt();
        scanner.nextLine();  // consume newline

        hotel.cancelReservation(reservationID);
        System.out.println("Reservation cancelled if it existed.");
    }

    private void displayAllReservations() {
        System.out.println("\nAll Reservations:");
        for (Reservation reservation : hotel.getReservations()) {
            reservation.getReservationDetails();
            System.out.println();
        }
    }

    public class InvalidDateFormatException extends Exception {
        public InvalidDateFormatException(String message) {
            super(message);
        }
    }
}





