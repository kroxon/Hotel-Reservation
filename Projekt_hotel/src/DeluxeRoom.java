class DeluxeRoom extends Room {
    private boolean hasMiniBar;
    private String viewType;

    public DeluxeRoom(int roomNumber, double pricePerNight, RoomType roomType, boolean hasMiniBar, String viewType) {
        super(roomNumber, pricePerNight, roomType);
        this.hasMiniBar = hasMiniBar;
        this.viewType = viewType;
    }

    @Override
    public void getRoomDetails() {
        super.getRoomDetails();
        System.out.println("Has MiniBar: " + hasMiniBar);
        System.out.println("View Type: " + viewType);
    }
}