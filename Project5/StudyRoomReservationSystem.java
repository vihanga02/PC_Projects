import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StudyRoomReservationSystem {
    private List<StudyRoom> studyRooms; // Array to store study rooms
    private Random random = new Random();

    public StudyRoomReservationSystem (){
        this.studyRooms = new ArrayList<>();
        for (int i = 0; i < 10;i++){
            this.studyRooms.add(new StudyRoom(random.nextInt(20)));
        }
        //this.loadFile();
    }
    public void saveInAFile() {
        try{
            FileOutputStream data = new FileOutputStream("Data1.ser");
            ObjectOutputStream obj = new ObjectOutputStream(data);
            obj.writeObject(studyRooms);
            obj.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void loadFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Data1.ser"))){
            this.studyRooms = (List<StudyRoom>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("errorrrrrrrrrrrr");
            e.printStackTrace();
        }
    }
    public synchronized void reserveStudyRoom(int roomNumber) throws StudyRoomUnavailableException{
        try {
            boolean isBooked = false;
            for (StudyRoom room: studyRooms){
                if (roomNumber == room.getRoomNumber()) {
                    if (room.isAvailability()) {

                        isBooked = true;
                        room.setCapacity(room.getCapacity()-1);
                        if (room.getCapacity() == 0){
                            room.setAvailability(false);
                        }
                    }
                    else{
                        throw new StudyRoomUnavailableException("Study room " + roomNumber + " is already occupied");
                    }
                    break;
                }
            }
            if (isBooked) {
                System.out.println("Room " + roomNumber + " reserved successfully");
            }
            else {
                throw new StudyRoomUnavailableException("Study rooms are full");
            }
        } finally {
            System.out.println("Reservation ended.");
        }
    }

    public synchronized void releaseStudyRoom(int roomNumber){
        try {
            for (StudyRoom room: studyRooms){
                if (room.isAvailability() && roomNumber == room.getRoomNumber()) {
                    room.setAvailability(true);
                    break;
                }
            }
            System.out.println("Room " + roomNumber + " released successfully");
        }
        catch (Exception e){
            System.out.println("Error occurred.");
        }
    }

    public void displayStudyRoomStatus(){
        System.out.println("Study Room Status:");
        for (StudyRoom room: studyRooms){
            System.out.print("Room: " + room.getRoomNumber() + " is ");
            if (room.isAvailability()){
                System.out.print("available and Capacity is " + room.getCapacity() + ".\n");
            }
            else{
                System.out.println("not available.\n");
            }
        }
    }
}
