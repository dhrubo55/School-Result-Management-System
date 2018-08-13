package sample;

/**
 * Created by Murshid on 7/14/2017.
 */
public class genericSubject {
    private int roll;
    private String name;
    private String section;
    private int midSubjective;
    private int midObjective;
    private int midPractical;
    private int midMonthly;
    private int midSubTotal;
    private int midTotal;
    private int finalSubjective;
    private int finalObjective;
    private int finalPractical;
    private int finalMonthly;
    private int finalSubTotal;
    private int finalTotal;
    private String classSectionRoll;

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getMidSubjective() {
        return midSubjective;
    }

    public void setMidSubjective(int midSubjective) {
        this.midSubjective = midSubjective;
    }

    public int getMidObjective() {
        return midObjective;
    }

    public void setMidObjective(int midObjective) {
        this.midObjective = midObjective;
    }

    public int getMidPractical() {
        return midPractical;
    }

    public void setMidPractical(int midPractical) {
        this.midPractical = midPractical;
    }

    public int getMidMonthly() {
        return midMonthly;
    }

    public void setMidMonthly(int midMonthly) {
        this.midMonthly = midMonthly;
    }

    public int getMidSubTotal() {
        return midSubTotal;
    }

    public void setMidSubTotal(int midSubTotal) {
        this.midSubTotal = midSubTotal;
    }

    public int getMidTotal() {
        return midTotal;
    }

    public void setMidTotal(int midTotal) {
        this.midTotal = midTotal;
    }

    public int getFinalSubjective() {
        return finalSubjective;
    }

    public void setFinalSubjective(int finalSubjective) {
        this.finalSubjective = finalSubjective;
    }

    public int getFinalObjective() {
        return finalObjective;
    }

    public void setFinalObjective(int finalObjective) {
        this.finalObjective = finalObjective;
    }

    public int getFinalPractical() {
        return finalPractical;
    }

    public void setFinalPractical(int finalPractical) {
        this.finalPractical = finalPractical;
    }

    public int getFinalMonthly() {
        return finalMonthly;
    }

    public void setFinalMonthly(int finalMonthly) {
        this.finalMonthly = finalMonthly;
    }

    public int getFinalSubTotal() {
        return finalSubTotal;
    }

    public void setFinalSubTotal(int finalSubTotal) {
        this.finalSubTotal = finalSubTotal;
    }

    public int getFinalTotal() {
        return finalTotal;
    }

    public void setFinalTotal(int finalTotal) {
        this.finalTotal = finalTotal;
    }

    public genericSubject(int roll, String name, String section, int midSubjective, int midObjective,
                          int midPractical, int midMonthly, int midSubTotal, int midTotal) {
        this.roll = roll;
        this.name = name;
        this.section = section;
        this.midSubjective = midSubjective;
        this.midObjective = midObjective;
        this.midPractical = midPractical;
        this.midMonthly = midMonthly;
        this.midSubTotal = midSubTotal;
        this.midTotal = midTotal;
    }

    public genericSubject(int roll, String name, String section) {

        this.roll = roll;
        this.name = name;
        this.section = section;
    }

    public genericSubject(int roll, String name, String section, int midSubjective, int midObjective, int midPractical, int midMonthly,
                          int midSubTotal, int midTotal, int finalSubjective, int finalObjective, int finalPractical, int finalMonthly,
                          int finalSubTotal, int finalTotal) {
        this.roll = roll;
        this.name = name;
        this.section = section;
        this.midSubjective = midSubjective;
        this.midObjective = midObjective;
        this.midPractical = midPractical;
        this.midMonthly = midMonthly;
        this.midSubTotal = midSubTotal;
        this.midTotal = midTotal;
        this.finalSubjective = finalSubjective;
        this.finalObjective = finalObjective;
        this.finalPractical = finalPractical;
        this.finalMonthly = finalMonthly;
        this.finalSubTotal = finalSubTotal;
        this.finalTotal = finalTotal;
//        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "genericSubject{" +
                "roll=" + roll +
                ", name='" + name + '\'' +
                ", section='" + section + '\'' +
                ", midSubjective=" + midSubjective +
                ", midObjective=" + midObjective +
                ", midPractical=" + midPractical +
                ", midMonthly=" + midMonthly +
                ", midSubTotal=" + midSubTotal +
                ", midTotal=" + midTotal +
                ", finalSubjective=" + finalSubjective +
                ", finalObjective=" + finalObjective +
                ", finalPractical=" + finalPractical +
                ", finalMonthly=" + finalMonthly +
                ", finalSubTotal=" + finalSubTotal +
                ", finalTotal=" + finalTotal +
                '}';
    }
}
