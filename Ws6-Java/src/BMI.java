
//Class to handle the calculation of the BMI Calculator 

public class BMI {
//	Private Member variables.
	private double weightKG;
	private double heightCM;
	private double weightLB;
	private double heightIN;
	private double kgcmBMI;
	private double lbinBMI;
	
//	NO-arg Constructor. 
	BMI(){
		weightKG = 0;
		heightCM = 0;
		weightLB = 0; 
		heightIN = 0;
	}
	
//	3 Argument Constructor.
	BMI(double weight, double height, boolean preference){
//		For kG/CM.
		if(preference == true) {
			
			weightKG = weight;
			heightCM = height;
			
			weightLB = 0;
			heightIN = 0;
			
			kgcmBMI = calculateBMIKGCM();
			
		}
//		For LB/INCH
		else {
			weightLB = weight;
			heightIN = height;
			
			weightKG = 0;
			heightCM = 0;
			
			lbinBMI = calculateBMILBIN();			
		}
	}
	
//	Set Attributes.
	public void setWeightKG(double weight) {
		weightKG = weight;
	}
	
	public void setheightCM(double height) {
		heightCM = height;
	}
	
	public void setWeightLB(double weight) {
		weightLB = weight;
	}
	
	public void setheightIN(double height) {
		heightIN = height;
	}
	
//	Get Attributes.
	public double getWeightKG() {
		return weightKG;
	}
	
	public double getheightCM() {
		return heightCM;
	}
	
	public double getWeightLB() {
		return weightLB;
	}
	
	public double getheightIN() {
		return heightIN;
	}
	
//	Calculating Part Of the Class. 
	
	private double calculateBMIKGCM() {
		double BMI = 0;
				
		BMI = (weightKG / (heightCM * heightCM)) * 10000;
		
		return BMI;
	}
	
	private double calculateBMILBIN() {
		double BMI = 0;
		BMI = (weightLB / (heightIN * heightIN)) * 703;
		
		return BMI;
	}
	
//	Giving the results.
	public double get_KGCM_bmi() {
		return kgcmBMI;
	}
	
	public double get_LBIN_bmi() {
		return lbinBMI;
	}
}
