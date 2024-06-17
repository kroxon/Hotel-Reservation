import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Hotel implements Serializable {
    private List<Room> rooms;
    private List<Reservation> reservations;

    public Hotel() {
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void removeRoom(int roomNumber) {
        rooms.removeIf(room -> room.getRoomNumber() == roomNumber);
    }

    public Room findAvailableRoom(RoomType roomType, LocalDate startDate, LocalDate endDate) {
        for (Room room : rooms) {
            if (room.isAvailable() && room.getRoomType() == roomType && isRoomAvailable(room, startDate, endDate)) {
                return room;
            }
        }
        return null;
    }

    private boolean isRoomAvailable(Room room, LocalDate startDate, LocalDate endDate) {
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().getRoomNumber() == room.getRoomNumber() &&
                    reservation.checkOverlap(startDate, endDate)) {
                return false;
            }
        }
        return true;
    }

    public Reservation makeReservation(String guestName, String guestSurname, RoomType roomType, LocalDate startDate, LocalDate endDate) {
        Room availableRoom = findAvailableRoom(roomType, startDate, endDate);
        if (availableRoom != null) {
            Reservation newReservation = new Reservation(availableRoom, guestName, guestSurname, startDate, endDate, ReservationStatus.ACTIVE);
            reservations.add(newReservation);
            availableRoom.bookRoom();
            return newReservation;
        } else {
            System.out.println("No available rooms of the specified type for the given dates.");
            return null;
        }
    }

    public void cancelReservation(int reservationID) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationID() == reservationID) {
                reservation.getRoom().cancelBooking();
                reservation.setStatus(ReservationStatus.CANCELLED);
                return;
            }
        }
        System.out.println("Reservation ID not found.");
    }
}
