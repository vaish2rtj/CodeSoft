public class GradeCalculator{
   public static String calculateGrade(double[] marks){
    double t=0;
    for(double i:marks){
        t+=i;
    }
    double p=t/marks.length;
    if(p>=90)
    return "A";
    else if(p>=80)
    return "B";
    else if(p>=70)
    return "C";
    else if(p>=60)
    return "D";
    else
    return "F";
   }
}