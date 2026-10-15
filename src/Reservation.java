
import java.time.LocalDate;

public class Reservation {

    private String pnr;
    private String username;
    private String passengerName;
    private String trainNumber;
    private String trainName;
    private LocalDate journeyDate;
    private String sourceStation;
    private String destinationStation;
    private String seatClass;
    private String bookingStatus;

    public Reservation(
            String pnr,
            String username,
            String passengerName,
            String trainNumber,
            String trainName,
            LocalDate journeyDate,
            String sourceStation,
            String destinationStation,
            String seatClass) {

        this.pnr = pnr;
        this.username = username;
        this.passengerName = passengerName;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.journeyDate = journeyDate;
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
        this.seatClass = seatClass;
        this.bookingStatus = "CONFIRMED";
    }

    public String getPnr() {
        return pnr;
    }

    public String getUsername() {
        return username;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public LocalDate getJourneyDate() {
        return journeyDate;
    }

    public String getSourceStation() {
        return sourceStation;
    }

    public String getDestinationStation() {
        return destinationStation;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
    this.bookingStatus = bookingStatus;
}
}
