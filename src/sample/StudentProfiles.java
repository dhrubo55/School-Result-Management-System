package sample;

public class StudentProfiles {
    private int roll;
    private String name;
    private int clas;
    private String section;
    private String fName;
    private String mName;
    private String group;
    private String birthDate;
    private String Address;



    public StudentProfiles(int roll, String name, int clas, String section, String fName, String mName, String group, String birthDate, String address) {
        this.roll = roll;
        this.name = name;
        this.clas = clas;
        this.section = section;
        this.fName = fName;
        this.mName = mName;
        this.group = group;
        this.birthDate = birthDate;
        Address = address;
    }

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

    public int getClas() {
        return clas;
    }

    public void setClas(int clas) {
        this.clas = clas;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getfNmame() {
        return fName;
    }

    public void setfNmame(String fNmame) {
        this.fName = fNmame;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
