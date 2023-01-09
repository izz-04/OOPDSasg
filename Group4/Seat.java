public class Seat {
    private char area;
    private int seatNo;
    private boolean isBooked = false; 


    public Seat () {}

    public Seat (char area, int seatNo, boolean isBooked) {
        this.area = area; 
        this.seatNo = seatNo; 
        this.isBooked = isBooked; 
    }


    // getters and setters

    public char getSeatArea () {
        return area; 
    }

    public int getSeatNo () {
        return seatNo; 
    }

    public boolean getIsBooked () {
        return isBooked;
    }

    public void bookSeat () {
        this.isBooked = true; 
    }

    public void cancelSeat () {
        this.isBooked = false; 
    }

    public String toString() {
        return ("area  :" + area + " , seatNo: " + seatNo + ", isBooked:" + isBooked);
    }
}

