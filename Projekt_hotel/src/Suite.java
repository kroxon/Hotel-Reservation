class Suite extends Room {
    private boolean hasJacuzzi;
    private int numberOfRooms;

    public Suite(int roomNumber, double pricePerNight, RoomType roomType, boolean hasJacuzzi, int numberOfRooms) {
        super(roomNumber, pricePerNight, roomType);
        this.hasJacuzzi = hasJacuzzi;
        this.numberOfRooms = numberOfRooms;
    }

    @Override
    public void getRoomDetails() {
        super.getRoomDetails();
        System.out.println("Has Jacuzzi: " + hasJacuzzi);
        System.out.println("Number of Rooms: " + numberOfRooms);
    }
}