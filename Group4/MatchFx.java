
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MatchFx {
    public Match[] setup() {

        // make stadium and seats;
        ArrayList<Stadium> stadiums = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Seat[] seats = new Seat[10];
            stadiums.add(new Stadium("stadium" + i, i, seats));
        }

        // make matches
        Match mFinal = new Match("Final", 101, stadiums.get(0));
        Match mSemi = new Match("Semifinal", 102, stadiums.get(1));
        Match mCommon = new Match("CommonGame", 103, stadiums.get(2));

        Match[] matches = { mFinal, mSemi, mCommon };

        // read booking.csv
        try (BufferedReader reader = new BufferedReader(new FileReader("bookings.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String customerName = parts[0];
                String matchName = parts[1];
                int seatNo = Integer.parseInt(parts[2]);
                for (int i = 0; i < matches.length; i++) {
                    if (matches[i].getMatchName().equals(matchName)) {
                        matches[i].getStadium().getStadiumSeats()[seatNo - 1].bookSeat(); // index of seatNo start at 1
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return matches;

    }
    
    public void matchView(Stage primaryStage, String username, boolean isStaff) {

        FlowPane pane = new FlowPane();
        pane.setVgap(8);
        pane.setHgap(8);

        Match[] matches = setup();

        for (int i = 0; i < matches.length; i++) {
            Match match = matches[i];
            Button btn = new Button(match.getMatchName());
            pane.getChildren().add(btn); // add here

            // set button action on click
            btn.setOnAction ((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(primaryStage);
                    //dialog.setScene(seatViewScene(match, primaryStage, username, isStaff)); // view Seats Scene
                    dialog.show();
                }
            });
        }

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        Label header = new Label("Matches List");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        layout.getChildren().addAll(header, pane);

        Scene matchScene = new Scene(layout, 500, 300);

        primaryStage.setTitle("Matches");
        primaryStage.setScene(matchScene);
        primaryStage.show();

    }

    private Scene seatViewScene(Match match, Stage stage, String username, boolean isStaff) {

        Stadium stadium = match.getStadium();

        Seat[] seats = stadium.getStadiumSeats();

        FlowPane pane = new FlowPane();

        if (!isStaff) {
            for (int i = 0; i < seats.length; i++) {
                Button btn = new Button("Seat" + seats[i].getSeatNo());
                if (seats[i].getIsBooked()) {
                    btn.setStyle("-fx-background-color: #EE4B2B; ");
                } else {
                    int seatNo = i + 1; // again index for seat starts at 1
                    btn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            booking(username, match, seatNo, stage); /// IMPORTANTTT
                        }
                    });
                }
                pane.getChildren().add(btn); 
            }

            VBox layout = new VBox(10);
            layout.setAlignment(Pos.CENTER);
            Label header = new Label("Please Select Seat to Book");
            header.setFont(Font.font("Arial", FontWeight.NORMAL, 24));
            layout.getChildren().addAll(header, pane);
            Scene seatScene = new Scene(layout, 400, 200);

            return seatScene;
        } else {
            for (int i = 0; i < seats.length; i++) {
                Button btn = new Button("Seat" + seats[i].getSeatNo());
                if (seats[i].getIsBooked()) {
                    int seatNo = i + 1;
                    btn.setStyle("-fx-background-color: #EE4B2B; ");

                    btn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            // show customer details on click
                            showCustomerDetails(match, seatNo);
                        }
                    });
                }
                pane.getChildren().add(btn);
            }

            VBox layout = new VBox(10);
            layout.setAlignment(Pos.CENTER);
            Label header = new Label("Please Select Seat to View Customer Details");
            header.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
            layout.getChildren().addAll(header, pane);
            Scene seatScene = new Scene(layout, 400, 200);

            return seatScene;
        }

    }

    private void booking(String username, Match match, int seatNo, Stage stage) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        VBox confirmVbox = new VBox(20);
        Label confirmLbl = new Label("Please confirm your booking for seat " + seatNo);
        confirmLbl.setFont(Font.font("Arial", FontWeight.NORMAL, 15));

        Button confirmBtn = new Button("Confirm");
        Button cancelBtn = new Button("Cancel");

        confirmBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("bookings.csv", true))) {
                    String line = username + "," + match.getMatchName() + "," + seatNo;
                    writer.append(line);
                    writer.newLine();
                    System.out.println("Booking Successful");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    dialog.close();
                }
            }
        });

        confirmVbox.getChildren().addAll(confirmLbl, confirmBtn, cancelBtn);

        cancelBtn.setOnAction(e -> dialog.close());

        Scene dialogScene = new Scene(confirmVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();

    }

    private void showCustomerDetails(Match match, int seatNo) {
        Stage custDetails = new Stage();
        String custName = "placeholder";

        try (BufferedReader reader = new BufferedReader(new FileReader("bookings.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String customerName = parts[0];
                String matchName = parts[1];
                int seatNumber = Integer.parseInt(parts[2]);
                if (matchName.equals(match.getMatchName()) && seatNumber == seatNo) {
                    custName = customerName;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Text bookDetails = new Text("Match : " + match.getMatchName() +"\nSeat " + seatNo + " booked by : " + custName);
        bookDetails.setFont(Font.font("Arial", FontWeight.NORMAL, 24));
        VBox box = new VBox(bookDetails);
        box.setAlignment(Pos.CENTER);

        Scene layout = new Scene(box, 300, 200);
        custDetails.setScene(layout);
        custDetails.show();

    }

}
