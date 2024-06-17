public abstract class Room {
    private int roomNumber;
    private double pricePerNight;
    private boolean isAvailable;
    private RoomType roomType;

    public Room(int roomNumber, double pricePerNight, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true;
        this.roomType = roomType;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void getRoomDetails() {
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Price per Night: " + pricePerNight);
        System.out.println("Availability: " + (isAvailable ? "Available" : "Not Available"));
        System.out.println("Room Type: " + roomType);
    }

    public boolean checkAvailability() {
        return isAvailable;
    }

    public void bookRoom() {
        if (isAvailable) {
            isAvailable = false;
        } else {
            System.out.println("Room is not available for booking.");
        }
    }

    public void cancelBooking() {
        isAvailable = true;
    }
}
