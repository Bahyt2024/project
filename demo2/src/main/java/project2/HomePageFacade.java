package project2;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

interface SeatObserver {
    void update(int seatNumber, boolean booked,Text text);
}
class SeatSubject  {
    public static List<SeatObserver> observers = new ArrayList<>();

    public static void attach(SeatObserver observer) {
        observers.add(observer);
    }

    public static void notifyObservers(int seatNumber, boolean booked,Text text) {
        for (SeatObserver observer : observers) {
            observer.update(seatNumber, booked,text);
        }
    }
}
class users implements SeatObserver{
    String name;
    public users(String name){
        this.name = name;
    }

    @Override
    public void update(int seatNumber, boolean booked, Text text) {
        if (booked) {
            text.setText("User: " + name + ", Seat " + seatNumber + " has been booked.");
        } else {
            text.setText("User: " + name + ", Seat " + seatNumber + " has been released.");
        }
    }
}

interface PricingStrategy {
    double calculatePrice();
}

class BasePricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice() {
        return 100;
    }

}

class IncreasingPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice() {
        return 500;
    }
}

class Seat {
    private final int seatNumber;
    private  boolean booked;
    private final PricingStrategy pricingStrategy;

    public Seat(int seatNumber, PricingStrategy pricingStrategy) {
        this.seatNumber = seatNumber;
        this.booked = false;
        this.pricingStrategy = pricingStrategy;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public double getPrice() {
        return pricingStrategy.calculatePrice();
    }
}

interface SeatFactory {
    Seat createSeat(int seatNumber);
}

class BasePricingSeatFactory implements SeatFactory {
    @Override
    public Seat createSeat(int seatNumber) {
        return new Seat(seatNumber, new BasePricingStrategy());
    }
}

class IncreasingPricingSeatFactory implements SeatFactory {
    @Override
    public Seat createSeat(int seatNumber) {
        return new Seat(seatNumber, new IncreasingPricingStrategy());
    }
}

public class HomePageFacade {

    @FXML
    private DatePicker datePicker;

    @FXML
    private ChoiceBox<String> fromCityChoiceBox;

    @FXML
    private ChoiceBox<String> toCityChoiceBox;

    @FXML
    private GridPane gridPane;
    @FXML
    private Button backButton;
    @FXML
    public Text text;

    public void initialize(){
        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demo2/hello-view.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });

        datePicker.setValue(LocalDate.now());

        fromCityChoiceBox.setItems(FXCollections.observableArrayList(
                "New York", "Los Angeles", "Chicago", "San Francisco", "Miami"
        ));
        toCityChoiceBox.setItems(FXCollections.observableArrayList(
                "New York", "Los Angeles", "Chicago", "San Francisco", "Miami"
        ));

        SeatFactory basePricingFactory = new BasePricingSeatFactory();
        SeatFactory increasingPricingFactory = new IncreasingPricingSeatFactory();

        for (int i = 0; i < 36; i++) {
            SeatFactory factory = (i % 2 == 0) ? basePricingFactory : increasingPricingFactory;
            Seat seat = factory.createSeat(i + 1);

            Button seatButton = new Button("Seat " + seat.getSeatNumber() + "\n" + seat.getPrice()+"$");
            seatButton.setMinSize(40,45);

            seatButton.setOnAction(e -> {
                if (!seat.isBooked()) {
                    seat.setBooked(true); // Бронируем место
                    SeatSubject.notifyObservers(seat.getSeatNumber(),seat.isBooked(),text);
                } else {
                    SeatSubject.notifyObservers(seat.getSeatNumber(),false,text);
                }
            });

            gridPane.add(seatButton, i % 6, i / 6 + 1);
        }
    }

    @FXML
    void handleSelectButtonAction() {
        LocalDate selectedDate = datePicker.getValue();
        if (selectedDate != null) {
            int selectedDay = selectedDate.getDayOfMonth();
            String selectedMonth = selectedDate.getMonth().toString();
            String fromCity = fromCityChoiceBox.getValue();
            String toCity = toCityChoiceBox.getValue();
            text.setText("Date: " + selectedDay + " " + selectedMonth + ", From: " + fromCity + ", To: " + toCity);
        } else {
            text.setText("Please select a date.");
        }
    }
}
