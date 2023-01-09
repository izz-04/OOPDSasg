public class Stadium {
    private String stadiumName = "default";
    private int stadiumId = 999999;
    private Seat[] seats = new Seat[10]; // deafult seat is 10 seats

    // cosntructors
    public Stadium () {
            // set general seats 
            int seatNo = 1;
            for (int i  = 0; i < seats.length; i++  ){
                seats[i] = new Seat('A', seatNo++, false );
            }
    }

    public Stadium (String stadiumName, int stadiumId, Seat[] seats) {
        this.stadiumName = stadiumName;
        this.stadiumId = stadiumId;
        this.seats = seats;
        
        // set general seats 
        int seatNo = 1;
        for (int i  = 0; i < seats.length; i++  ){
            seats[i] = new Seat('A', seatNo++, false );
        }
    }

    //getters

    public String getStadiumName () {
        return stadiumName;
    }

    public int getStadiumId () {
        return stadiumId;
    }

    public Seat[] getStadiumSeats() {
        return seats;
    }

    public String toString () {
        return ("StadiumName : " +stadiumName + ", StadiumId : " + stadiumId + ", Seats: " + seats ); 
    }


}
