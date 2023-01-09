import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class BookingApp extends Application {
    public static void main(String[] args) throws IOException {
        Application.launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        //// LOGIN
        // login scene and create a grid pane
        GridPane grid2 = new GridPane();
        grid2.setAlignment(Pos.CENTER);
        grid2.setHgap(10);
        grid2.setVgap(10);
        grid2.setPadding(new Insets(25, 25, 25, 25));

        // Header
        Label loginHeader = new Label("Login Form");
        loginHeader.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        grid2.add(loginHeader, 0, 0, 2, 1);
        GridPane.setHalignment(loginHeader, HPos.CENTER);
        GridPane.setMargin(loginHeader, new Insets(20, 0, 20, 0));

        // Add labels and text fields for the form
        Label loginUsername = new Label("Username:");
        grid2.add(loginUsername, 0, 1);
        TextField usernameField2 = new TextField();
        grid2.add(usernameField2, 1, 1);

        Label loginPassword = new Label("Password:");
        grid2.add(loginPassword, 0, 2);
        PasswordField passwordField2 = new PasswordField();
        grid2.add(passwordField2, 1, 2);

        // Scene for login
        primaryStage.setTitle("World Cup 2026 Form JavaFX");
        Scene loginScene = new Scene(grid2, 500, 300);
        primaryStage.setScene(loginScene);
        primaryStage.show();

        ////////////////////////////////
        // Tick Box
        CheckBox checkBox = new CheckBox("ADMIN");
        HBox root = new HBox();
        root.getChildren().addAll(checkBox);
        root.setAlignment(Pos.BOTTOM_RIGHT);
        grid2.add(root, 1, 8);

        /////////////////////////////////////
        // Change to ADMIN SCENE
        // Create a grid pane for tick box for random
        GridPane grid3 = new GridPane();
        Scene checkScene = new Scene(grid3, 500, 300);
        grid3.setAlignment(Pos.CENTER);
        grid3.setHgap(10);
        grid3.setVgap(10);
        grid3.setPadding(new Insets(25, 25, 25, 25));

        checkBox.setOnAction(e -> primaryStage.setScene(checkScene));
        /////////////////////////////////////
        // Admin Scene for login
        // Header
        Label adminHeader = new Label("Admin Login");
        adminHeader.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        grid3.add(adminHeader, 0, 0, 2, 1);
        GridPane.setHalignment(adminHeader, HPos.CENTER);
        GridPane.setMargin(adminHeader, new Insets(20, 0, 20, 0));

        // Add labels and text fields for the form
        Label adminUsername = new Label("Username:");
        grid3.add(adminUsername, 0, 1);
        TextField adminUserField = new TextField();
        grid3.add(adminUserField, 1, 1);

        Label adminPassword = new Label("Password:");
        grid3.add(adminPassword, 0, 2);
        PasswordField adminPasswordField = new PasswordField();
        grid3.add(adminPasswordField, 1, 2);

        Button adminButton = new Button("Login");
        adminButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String adusername = adminUserField.getText();
                String adpassword = adminPasswordField.getText();

                // Read the data from the CSV file
                try (BufferedReader reader = new BufferedReader(new FileReader("admin.csv"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        String csvUsername = parts[0];
                        String csvPassword = parts[1];
                        if (adusername.equals(csvUsername) && adpassword.equals(csvPassword)) {
                            // Login successful
                            matchView(primaryStage, adusername, true);
                            System.out.println("Welcome Admin!");
                            return;
                        }
                    }
                    // Login failed
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setTitle("Error");
                    alert2.setHeaderText(null);
                    alert2.setContentText("Invalid username or password");
                    alert2.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        HBox admin = new HBox(10);
        admin.setAlignment(Pos.BOTTOM_RIGHT);
        admin.getChildren().addAll(adminButton);
        grid3.add(admin, 1, 5);

        /////////////////////////////////////////////////////////////
        //// REGISTRATION
        // Create a grid pane
        GridPane grid = new GridPane();
        Scene registerScene = new Scene(grid, 500, 300);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Header
        Label registerHeader = new Label("Register Form");
        registerHeader.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        grid.add(registerHeader, 0, 0, 2, 1);
        GridPane.setHalignment(registerHeader, HPos.CENTER);
        GridPane.setMargin(registerHeader, new Insets(20, 0, 20, 0));

        // Add labels and text fields for the form
        Label registerUsername = new Label("Username:");
        grid.add(registerUsername, 0, 1);
        TextField usernameField1 = new TextField();
        grid.add(usernameField1, 1, 1);

        Label registerPassword = new Label("Password:");
        grid.add(registerPassword, 0, 2);
        PasswordField passwordField1 = new PasswordField();
        grid.add(passwordField1, 1, 2);

        Label registerEmail = new Label("Email:");
        grid.add(registerEmail, 0, 3);
        TextField emailField = new TextField();
        grid.add(emailField, 1, 3);

        Label registerPhone = new Label("Phone number:");
        grid.add(registerPhone, 0, 4);
        TextField phoneField = new TextField();
        grid.add(phoneField, 1, 4);

        // log in button for Login
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        HBox hbox2 = new HBox(10);
        hbox2.setAlignment(Pos.BOTTOM_RIGHT);
        hbox2.getChildren().addAll(loginButton, registerButton);
        grid2.add(hbox2, 1, 5);

        registerButton.setOnAction(e -> primaryStage.setScene(registerScene));

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String username = usernameField2.getText();
                String password = passwordField2.getText();

                // Read the data from the CSV file
                try (BufferedReader reader = new BufferedReader(new FileReader("registration.csv"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        String csvUsername = parts[0];
                        String csvPassword = parts[1];
                        if (username.equals(csvUsername) && password.equals(csvPassword)) {
                            // Login successful
                            System.out.println("Login successful!");
                            matchView(primaryStage, username, false);
                            return;
                        }
                    }
                    // Login failed
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid username or password");
                    alert.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        /// REGISTRATION BUTTON
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String username = usernameField1.getText();
                String password = passwordField1.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();

                // Error for register
                if (usernameField1.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!",
                            "Please enter your username");
                    return;
                }
                if (passwordField1.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!",
                            "Please enter a password");
                    return;
                }
                if (emailField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!",
                            "Please enter your email id");
                    return;
                }
                if (phoneField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!",
                            "Please enter your phone number");
                    return;
                }
                showAlert(Alert.AlertType.CONFIRMATION, grid.getScene().getWindow(), "Registration Successful!",
                        "Welcome " + usernameField1.getText());

                // Write the data to a CSV file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("registration.csv", true))) {
                    String line = username + "," + password + "," + email + "," + phone;
                    writer.append(line);
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Button signInButton = new Button("Sign In");
        signInButton.setOnAction(e -> primaryStage.setScene(loginScene));

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(signInButton, submitButton);
        grid.add(hbBtn, 1, 5);

    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
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
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(primaryStage);
                    dialog.setScene(seatViewScene(match, primaryStage, username, isStaff)); // view Seats Scene
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

    private Match[] setup() {

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