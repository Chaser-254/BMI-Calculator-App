package com.example.bmi_calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class BMICalculator extends Application {

    private static  final double KG_TO_LB=2.20462;
    private static final double M_TO_IN=39.3701;

    private TextField heightField;
    private TextField weightField;
    private ComboBox<String> unitBox;
    private Label bmiLabel;

    @Override
    public void start(Stage primaryStage){
        //setting up the User Interface elements
        Label titleLabel = new Label("BMI CALCULATOR");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD,20));
        Label heightLabel = new Label("Height:");
        Label weightLabel = new Label("Weight:");
        unitBox = new ComboBox<>();
        unitBox.getItems()
                .addAll("Metric","English");
        unitBox.setValue("Metric");

        Button calculateButton = new Button("Calculate");
        bmiLabel = new Label();

        heightField = new TextField();
        weightField = new TextField();

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(titleLabel,0,0,2,1);
        gridPane.add(heightLabel,0,1);
        gridPane.add(heightField,0,1);
        gridPane.add(weightLabel,0,2);
        gridPane.add(weightField,1,2);
        gridPane.add(unitBox,0,3);
        gridPane.add(calculateButton,1,3);
        gridPane.add(bmiLabel,0,4,2,1);

        //I am adding Event Handler to calculate Button
        calculateButton.setOnAction(e -> calculateBMI());

        //Setting up scene and showing stage
        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("BMI CALCULATOR");
        primaryStage.show();

    }
    private void calculateBMI(){
        double height, weight;
        String unit = unitBox.getValue();
        try{
            height = Double.parseDouble(heightField.getText());
            weight = Double.parseDouble(weightField.getText());
        } catch (NumberFormatException e){
            bmiLabel.setText("Please enter a valid numbers for height and weight.");
            return;
        }
        if (unit.equals("English")){
           height *= M_TO_IN;
           weight /= KG_TO_LB;
        }
        double bmi = weight / (height * height);
        String bmiCategory;
        if (bmi < 18.5){
            bmiCategory = "Underweight";
        } else if (bmi < 25) {
            bmiCategory = "Normal";
        }else {
            bmiCategory = "Obese";
        }
        bmiLabel.setText(String.format("Your BMI is %.1f(%s)",bmi,bmiCategory));
    }

    public static void main(String[] args) {
        launch();
    }
}