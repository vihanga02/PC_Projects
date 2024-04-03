import java.io.Serializable;

public class StudyRoom implements Serializable {
    private int roomNumber;
    private int capacity;
    private boolean availability;
    private int maxCapacity;

    private static int roomCount = 0;

    public StudyRoom(int capacity) {
        roomCount++;
        this.roomNumber = roomCount;
        this.capacity = capacity;
        this.maxCapacity = capacity;
        this.availability = true; // By default, room is available
    }

    public boolean isAvailability() {
        return availability;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
