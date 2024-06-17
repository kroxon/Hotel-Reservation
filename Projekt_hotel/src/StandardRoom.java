class StandardRoom extends Room {
    private boolean hasTV;
    private String bedSize;

    public StandardRoom(int roomNumber, double pricePerNight, RoomType roomType, boolean hasTV, String bedSize) {
        super(roomNumber, pricePerNight, roomType);
        this.hasTV = hasTV;
        this.bedSize = bedSize;
    }

    @Override
    public void getRoomDetails() {
        super.getRoomDetails();
        System.out.println("Has TV: " + hasTV);
        System.out.println("Bed Size: " + bedSize);
    }
}