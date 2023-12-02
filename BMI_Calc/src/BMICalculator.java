
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Slider;

public class BMICalculator extends Application {

//	Private Member variables. 
	private boolean KGCM_LBIN = false;
	private TextField height = new TextField();
	private TextField weight = new TextField();
	private TextField result = new TextField();

	@Override
	public void start(Stage primaryStage) throws Exception {
//		Setting the name of the Stage.
		primaryStage.setTitle("Body Mass Index Calculator");

//		Root Pane.
//		----------
		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		root.setHgap(5.5);
		root.setVgap(5.5);
		root.setStyle("-fx-background-color: LIGHTBLUE;");
//		--------------
//		Creating Nodes
//		--------------

//		Label Nodes
		Label selectBtnLabel = new Label();
		Label heightWeidth = new Label();
		selectBtnLabel.setText("Select Scale");
		selectBtnLabel.setFont(Font.font("Verdana", 20));
		heightWeidth.setText("Heigth Weigth Display");
		heightWeidth.setFont(Font.font("Verdana", 20));

		Label heightScaleLabel = new Label("Select Height");
		heightScaleLabel.setFont(Font.font("Verdana", 20));
		Label weightScaleLabel = new Label("Select Weight");
		weightScaleLabel.setFont(Font.font("Verdana", 20));

		Label l = new Label();

		Label resultTitle = new Label("Result: ");
		resultTitle.setFont(Font.font("Verdana", 20));

//		Slider Node
		Slider sliderWeight = new Slider(0, 200, 50);
		sliderWeight.setShowTickMarks(true);
		sliderWeight.setShowTickLabels(true);
		sliderWeight.setBlockIncrement(0.1f);
		sliderWeight.setPrefHeight(20);
		sliderWeight.setPrefWidth(500);

		Slider sliderHeight = new Slider(0, 180, 50);
		sliderHeight.setShowTickMarks(true);
		sliderHeight.setShowTickLabels(true);
		sliderHeight.setBlockIncrement(0.1f);
		sliderHeight.setPrefHeight(20);
		sliderHeight.setPrefWidth(500);

//		Image Node
		FileInputStream img = new FileInputStream("BMIChart.png");
		Image image = new Image(img);

//		Radio Button Pane
		RadioButton rButton1 = new RadioButton("cmKg");
		RadioButton rButton2 = new RadioButton("inLb");
//		rButton1.setAlignment(Pos.CENTER);
//		rButton2.setAlignment(Pos.TOP_LEFT);

//		ImageView Node
		ImageView pic = new ImageView();
		pic.setFitWidth(530);
		pic.setFitHeight(530);
		pic.setImage(image);

//		TextField Nodes.
		height.setEditable(false);
		weight.setEditable(false);
		result.setEditable(false);

//		Button Node.
		Button calculateBMI = new Button("Calculate");

//		Children Panes.
//		---------------
		VBox selectBtn = new VBox();
		selectBtn.setSpacing(10);
		selectBtn.setPadding(new Insets(0, 20, 10, 20));
		selectBtn.setStyle("-fx-border-color: WHITE;-fx-background-color: LIGHTPINK;");

		VBox textShow = new VBox();
		textShow.setSpacing(10);
		textShow.setPadding(new Insets(0, 20, 10, 20));
		textShow.getChildren().add(new Label("\n\n\n\n\n"));
		textShow.getChildren().add(selectBtnLabel);
		textShow.getChildren().addAll(rButton1, rButton2);
		textShow.setAlignment(Pos.TOP_CENTER);
		textShow.getChildren().add(new Label("\n\n\n\n\n"));

		selectBtn.getChildren().add(textShow);

//		Anonymous Class
//		---------------

//		Handling the events for the Radio Buttons according to the selection.
		rButton1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				if (rButton1.isSelected()) {
					textShow.getChildren().add(heightWeidth);
					selectBtnLabel.setFont(Font.font("Verdana", 20));

					textShow.getChildren().addAll(new Label("HEIGHT (in CentiMeter): "), height);
					textShow.getChildren().addAll(new Label("WEIGHT (in KiloGrams): "), weight);
					textShow.getChildren().add(calculateBMI);
					KGCM_LBIN = true;
				}
			}
		});

		rButton2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				if (rButton2.isSelected()) {
					textShow.getChildren().add(heightWeidth);
					selectBtnLabel.setFont(Font.font("Verdana", 20));

					textShow.getChildren().addAll(new Label("HEIGHT (in Inches): "), height);
					textShow.getChildren().addAll(new Label("WEIGHT (in LBS): "), weight);
					textShow.getChildren().add(calculateBMI);
					KGCM_LBIN = false;

				}
			}

		});

//		Creating the Slider for the value inputs of weight and Height.
		sliderWeight.valueProperty().addListener(new ChangeListener<Number>() {

			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				weight.setText(String.format("%.2f", newValue, ""));
			}
		});

		sliderHeight.valueProperty().addListener(new ChangeListener<Number>() {

			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				height.setText(String.format("%.2f", newValue));
			}
		});

//		Handling Events when the Calculate Button is pressed. 
		calculateBMI.setOnAction(e -> calculateBMI());

//		Final Layout of the Root Pane.
		root.add(selectBtn, 0, 0);
		root.add(pic, 15, 0);
		root.add(heightScaleLabel, 0, 8);
		root.add(sliderHeight, 0, 10);
		root.add(weightScaleLabel, 0, 14);
		root.add(sliderWeight, 0, 20);
		root.add(l, 0, 4);
		root.add(resultTitle, 15, 8);
		root.add(result, 15, 10);

//		Creating the Scene and setting it to the Primary Stage.
		Scene scene = new Scene(root, 1000, 800);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

//	Calculating the BMI according to the weight and height in KG/CM or LB/IN.
	private void calculateBMI() {

		double localWeight = Double.parseDouble(weight.getText());
		double localHeight = Double.parseDouble(height.getText());
		double calcuatedBMI = 0;
		BMI bmiCalVal = new BMI(localWeight, localHeight, KGCM_LBIN);

		if (KGCM_LBIN == true) {
			calcuatedBMI = bmiCalVal.get_KGCM_bmi();
		} else if (KGCM_LBIN == false) {
			calcuatedBMI = bmiCalVal.get_LBIN_bmi();
		}

		result.setText(String.format("%.2f", calcuatedBMI));
	}

//	Main function. Entry point of the application.
	public static void main(String[] args) {
		launch(args);
	}

}
