package sample;

import java.sql.*;


public class bluePrint {

    private static Connection conn = null;
    private static Statement stmt;
    private static String sql;
    private static ResultSet rs, rs1;

    public bluePrint(String address, String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //String url = "jdbc:mysql://localhost:3311/Crawler";
            String url = address;
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("conn built");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public bluePrint()
    {

    }

    public void createDB(String dbName) throws Exception {
        //boolean flag = false;
        String sql0;
        Statement stmt;
        ResultSet resultSet = conn.getMetaData().getCatalogs();
        String databaseName = "";
        //iterate each catalog in the ResultSet
        while (resultSet.next()) {
            databaseName = resultSet.getString(1);
        }
        if (databaseName.equals("result_processing_db")) {
            try {
                stmt = conn.createStatement();
                sql0 = "use " + databaseName;
                stmt.executeUpdate(sql0);

                DatabaseMetaData metadeta=conn.getMetaData();
                resultSet=metadeta.getTables("result_processing_db", null,  "%",null);

                boolean accessPanel=false, classTeacher=false, subjectTeacher=false, studentInformation=false, subjectSettings=false;
                String str;
                while(resultSet.next())
                {
                    str=resultSet.getString("TABLE_NAME");
//                    System.out.println(str+" "+accessPanel+" "+classTeacher+" "+subjectTeacher+" "+studentInformation+" "+subjectSettings);
                    if(str.equals("access_panel"))
                        accessPanel=true;
                    else if(str.equals("class_teacher"))
                        classTeacher=true;
                    else if(str.equals("subject_teacher"))
                        subjectTeacher=true;
                    else if(str.equals("std_info"))
                        studentInformation=true;
                    else if(str.equals("subject_settings"))
                        subjectSettings=true;
                }

                if(accessPanel==false)
                {
                    sql0="create table access_panel("+
                            "id int not null AUTO_INCREMENT,"+
                            "name varchar(40),"+
                            "pass varchar(40),"+
                            "color varchar(10),"+
                            "c_acc int not null,"+
                            "s_acc int not null,"+
                            "PRIMARY KEY (id) );";
                    stmt.executeUpdate(sql0);
                }
                else if(classTeacher==false)
                {
                    sql0="create table class_teacher("+
                        "name varchar(40) unique,"+
                        "class_sec varchar(30) not null,"+
                        "PRIMARY KEY (class_sec));";
                    stmt.executeUpdate(sql0);
                }
                else if(subjectTeacher==false)
                {
                    sql0="create table subject_teacher("+
                        "name varchar(40) not null,"+
                        "class_sec_subj varchar(30),"+
                        "PRIMARY KEY (class_sec_subj));";
                    stmt.executeUpdate(sql0);
                }
                else if(studentInformation==false)
                {
                    sql0 = "create table " +"std_info"+
                            "(id int not null AUTO_INCREMENT, " +
                            " name varchar(50) not null, " +
                            " roll int not null, " +
                            " f_name  varchar(50), " +
                            " m_name  varchar(50), " +
                            " class int not null, " +
                            " sec  varchar(10) not null, " +
                            " group_name  varchar(20), " +
                            " birth_date date, " +
                            " address  varchar(128), " +
                            " primary key ( id ));";

                    stmt.executeUpdate(sql0);
                }
                else if(subjectSettings==false)
                {
                    sql0="create table subject_settings" +
                            "(" +
                            " class_subject varchar(20) not null," +
                            " subject_teacher varchar(40)," +
                            " subjective double not null," +
                            " objective double not null," +
                            " practical double not null," +
                            " subTotal double not null," +
                            " mE double not null," +
                            " total double not null," +
                            " PRIMARY KEY (class_subject));";
                    stmt.executeUpdate(sql0);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }




    protected boolean loginInfo(String name, String password)
    {
        System.out.println("loginInfo accessed");
        String username = "";
        String Pass= "";
        ResultSet rs;
        Statement stmt1;
        String sql1;
        String sql;
        try {
            Statement stmt4 = conn.createStatement();
            sql = "select name, pass from access_panel order by id;";

            stmt1 = conn.createStatement();
            sql1 = "use result_processing_db";
            stmt1.executeUpdate(sql1);
            rs = stmt4.executeQuery(sql);
            while (rs.next())
            {
                username = rs.getString("name");
                Pass = rs.getString("pass");
                if (username.equals(name) && Pass.equals(password))
                {
                    return true;
                }
            }
        }
        catch (Exception e){
           e.printStackTrace();
        }

        return false;
    }

    protected static String getColor(String name)
    {
        System.out.println("getColor accessed");
        String color, userName;

        try {
            stmt = conn.createStatement();
            sql = "use result_processing_db";
            stmt.executeUpdate(sql);
            sql = "select name, color from access_panel order by id;";
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                userName = rs.getString("name");
                color = rs.getString("color");
                if (userName.equals(name))
                {
                    System.out.println("name "+userName+" Color "+color);
                    return color;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "white";
    }

    protected static void setColor(String name, String color)
    {
        System.out.println("setColor accessed");
        try {
            stmt = conn.createStatement();
            sql = "use result_processing_db";
            stmt.executeUpdate(sql);
            sql = "update access_panel set color = "+'"'+color+'"'+" where name = "+'"'+name+'"'+";";
            stmt.executeUpdate(sql);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    protected static boolean isAllowAccess(String name, int accessLevel)
    {
        System.out.println("isAllowAccess accessed");
        String  userName;

        try {
            stmt = conn.createStatement();
            sql = "use result_processing_db";
            stmt.executeUpdate(sql);
            sql = "select name, c_acc, s_acc from access_panel where name = "+'"'+name+'"'+";";
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                userName = rs.getString("name");
                int c_acc = rs.getInt("c_acc");
                int s_acc = rs.getInt("s_acc");
                System.out.println("name "+userName+" class Teacher "+c_acc+" Sub. Teacher "+s_acc);
                if (userName.equals(name))
                {
                    if(accessLevel==0 && c_acc==1)
                        return true;
                    else if(accessLevel==1 && s_acc==1)
                        return  true;
                    else
                        return false;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    protected static boolean newUser(String uName, String passWord, boolean classTeacher, boolean subjectTeacher)
    {
        System.out.println("New User accessed");
        try {
            stmt = conn.createStatement();
            sql = "use result_processing_db";
            stmt.executeUpdate(sql);
            sql="select max(id) from access_panel";
            rs = stmt.executeQuery(sql);
            rs.next();
            int max=rs.getInt("max(id)");
            int cTeacher, sTeacher;

            if(classTeacher)
                cTeacher=1;
            else
                cTeacher=0;
            if(subjectTeacher)
                sTeacher=1;
            else
                sTeacher=0;

            sql = "insert into access_panel values("+(max+1)+",'"+uName+"','"+passWord+"','white',"+cTeacher+","+sTeacher+");";
            stmt.executeUpdate(sql);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    protected static String[] userDetails(String name)
    {
        String str[]=null;
        System.out.println("User Details accessed for "+name);

        try {
            stmt = conn.createStatement();
            sql = "use result_processing_db";
            stmt.executeUpdate(sql);
            sql="select pass, color, c_acc, s_acc from access_panel where name="+'"'+name+'"'+';';
            rs = stmt.executeQuery(sql);
            if(rs.next())
            {
                str=new String[4];
                str[0]=rs.getString("pass");
                str[1]=rs.getString("color");
                str[2]=rs.getString("c_acc");
                str[3]=rs.getString("s_acc");
                return str;
            }

        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return str;
    }

    protected static boolean updateUser(String userName, String passWord, String color, int c_acc, int s_acc)
    {
        System.out.println("update User accessed");
        try {
            stmt = conn.createStatement();
            sql = "use result_processing_db";
            stmt.executeUpdate(sql);
            sql = "update access_panel set pass="+'"'+passWord+
                    '"'+", color="+'"'+color+'"'+", c_acc="+c_acc+", s_acc="+s_acc+
                    " where name="+'"'+userName+'"'+";";
            stmt.executeUpdate(sql);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    protected static String[] classTeachersNames()
    {
        String str[]=null;
        System.out.println("Class Teacher Names accessed ");

        try {
            stmt = conn.createStatement();
            sql = "use result_processing_db";
            stmt.executeUpdate(sql);
            sql="select count(*) from access_panel where c_acc=1;";
            rs=stmt.executeQuery(sql);
            rs.next();
            int size=rs.getInt("count(*)"), i=0;
            str=new String[size];
            sql="select name from access_panel where c_acc=1;";
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                str[i]=rs.getString("name");
                i++;
            }

        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return str;
    }

    protected static boolean createClassSec(String classSection)
    {
        System.out.println("create class section accessed");
        try {
            stmt = conn.createStatement();
            sql = "use result_processing_db";
            stmt.executeUpdate(sql);
            sql = "insert into class_teacher (class_sec) values('"+classSection+"');";
            stmt.executeUpdate(sql);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    protected static boolean assignClassTeacher(String name, String classSection)
    {
        System.out.println("Assign class Teacher accessed");
        try {
            stmt = conn.createStatement();
            sql = "use result_processing_db";
            stmt.executeUpdate(sql);
            sql = "update class_teacher set name='"+name+"' where class_sec='"+classSection+"';";
            stmt.executeUpdate(sql);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    protected static String getClassAndSection(String name)
    {
        System.out.println("Get Class & Section For class teacher accessed");
        String str=null;
        try {
            stmt = conn.createStatement();
            sql = "use result_processing_db";
            stmt.executeUpdate(sql);
            sql = "select class_sec from class_teacher where name='"+name+"';";
//            System.out.println(sql);
            rs=stmt.executeQuery(sql);

            rs.next();
            if(rs!=null)
            {
                str=rs.getString("class_sec");
            }
            return str;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }

    protected static boolean addStudent(int roll, String name, String fName, String mName, String address,
                                        String group, String date, int clas, String section){
        System.out.println("Add Student accessed");
        try {
            stmt = conn.createStatement();
            sql = "use result_processing_db";
            stmt.executeUpdate(sql);
            sql="select max(id) from std_info";
            rs = stmt.executeQuery(sql);
            rs.next();
            int max=rs.getInt("max(id)");
            sql = "insert into std_info values("+(max+1)+", '"+name+"', "+roll+
                    ", '"+fName+"', '"+mName+"', "+clas+", '"+section+
                    "', '"+group+"', "+date+", '"+address+"');";
            stmt.executeUpdate(sql);

            if(addStudentInSubjects(clas, (max+1), roll, name, section))
                return true;
            else
                return false;


        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    protected static String[][] studentInformationForClassTeacher(int clas, String sec)
    {
        System.out.println("Student Information For Class Teacher accessed");
        String[][] stdInfo=null;

        try {
            stmt = conn.createStatement();
            sql = "use result_processing_db";
            stmt.executeUpdate(sql);
            sql="select  count(id) from std_info where class="+clas+" and sec='"+sec+"';";
            rs = stmt.executeQuery(sql);
            rs.next();
            int size=rs.getInt("count(id)");
            stdInfo=new String[size][10];
            sql="select * from std_info where class="+clas+" and sec='"+sec+"';";
            rs = stmt.executeQuery(sql);

            for(int i=0; i<stdInfo.length;  i++)
            {
                rs.next();
                stdInfo[i][0]=""+rs.getInt("id");
                stdInfo[i][1]=rs.getString("name");
                stdInfo[i][2]=""+rs.getInt("roll");
                stdInfo[i][3]=""+rs.getString("f_name");
                stdInfo[i][4]=""+rs.getString("m_name");
                stdInfo[i][5]=""+rs.getInt("class");
                stdInfo[i][6]=""+rs.getString("sec");
                stdInfo[i][7]=""+rs.getString("group_name");
                stdInfo[i][8]=""+rs.getDate("birth_date");
                stdInfo[i][9]=""+rs.getString("address");
                System.out.println("FName :"+stdInfo[i][3]+" MName :"+stdInfo[i][4]);
            }
            return stdInfo;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return stdInfo;
    }

    protected  static boolean isTableExist(String classSubject)
    {
        System.out.println("is Table Exist accessed");
        boolean classSubjectTable = false;
        try {
            stmt = conn.createStatement();
            sql = "use result_processing_db";
            stmt.executeUpdate(sql);

            DatabaseMetaData metadeta = conn.getMetaData();
            rs = metadeta.getTables("result_processing_db", null, "%", null);


            String str;
            while (rs.next()) {
                str = rs.getString("TABLE_NAME");
                System.out.println(str + " " + classSubjectTable);
                if (str.equals(""+classSubject))
                    classSubjectTable = true;
            }
            return classSubjectTable;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    protected static boolean createNewClassSubject(String classSubject, double subjective,double objective, double practical,
                                                    double subTotal, double mE, double total)
    {
        System.out.println("create new class subject accessed");

        try {
            String [] str=classSubject.split("_");
            stmt = conn.createStatement();
            sql = "use result_processing_db";
            stmt.executeUpdate(sql);
            sql = "insert into subject_settings (class_subject, subjective, objective, practical, subTotal, mE, total) "+
                    "values('"+classSubject+"', "+subjective+", "+objective+", "+practical+", "+subTotal+", "+mE+", "+total+");";
            stmt.executeUpdate(sql);
            sql="create table "+classSubject+
                "(id int not null ,"+
                    "roll int not null, name varchar(40) not null, sec varchar(10) not null, m_subj int, "+
                    "m_obj int, m_prac int , m_monthly int, m_sub_total int, m_total int , f_subj int , f_obj int ,"+
                    "f_prac int , f_monthly int , f_sub_total int , f_total int , PRIMARY KEY (id));";
            stmt.executeUpdate(sql);
            int clas=Integer.parseInt(str[1]);
            sql="INSERT INTO "+classSubject+" (id, roll, sec, name)"+
                    " SELECT id, roll, sec, name FROM `std_info` WHERE `class` = '"+clas+"';";
            stmt.executeUpdate(sql);

            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    protected static String[] subjectTeachersNames()
    {
        String str[]=null;
        System.out.println("Subject Teacher Names accessed ");

        try {
            stmt = conn.createStatement();
            sql = "use result_processing_db";
            stmt.executeUpdate(sql);
            sql="select count(*) from access_panel where s_acc=1;";
            rs=stmt.executeQuery(sql);
            rs.next();
            int size=rs.getInt("count(*)"), i=0;
            str=new String[size];
            sql="select name from access_panel where s_acc=1;";
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                str[i]=rs.getString("name");
                i++;
            }
            return str;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    protected static boolean assignSubjectTeacher(String name, String classSecSubject)
    {
        System.out.println("Assign class Teacher accessed");
        try {
            stmt = conn.createStatement();
            sql = "use result_processing_db";
            stmt.executeUpdate(sql);
            sql = "insert into subject_teacher values('"+name+"', '"+classSecSubject+"');";
            stmt.executeUpdate(sql);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    protected static String[] getClassSectionAndSubjects(String name)
    {
        System.out.println("Get Class & Subject For subject teacher accessed");
        String [] str=null;
        try {
            stmt = conn.createStatement();
            sql = "use result_processing_db";
            stmt.executeUpdate(sql);

            sql="select count(*) from subject_teacher where name='"+name+"';";
            rs=stmt.executeQuery(sql);
            rs.next();
            int size=rs.getInt("count(*)"), i=0;
            str=new String[size];
            sql = "select class_sec_subj from subject_teacher where name='"+name+"';";
//            System.out.println(sql);
            rs=stmt.executeQuery(sql);

            while(rs.next()){
                str[i]=rs.getString("class_sec_subj");
                System.out.println("Subject "+(i+1)+" "+str[i]);
                i++;
            }

            return str;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }

    protected static String[][] getSubjectResult(String classSectionSubject){
//        System.out.println("get Subject Result Accessed for "+classSectionSubject);
        String [] CSS=classSectionSubject.split("_");
        String cs="c_"+CSS[1]+"_"+CSS[4];
        String [][] subResult=null;

        try{
            stmt = conn.createStatement();
            sql = "use result_processing_db";
            stmt.executeUpdate(sql);
            sql="select count(roll) from "+cs+" where sec='"+CSS[3]+"';";
            rs = stmt.executeQuery(sql);
            rs.next();
            int size=rs.getInt("count(roll)");
            subResult=new String[size][15];
            sql="select * from "+cs+" where sec='"+CSS[3]+"';";;
            rs = stmt.executeQuery(sql);
            for(int i=0; i<subResult.length; i++)
            {
                rs.next();
//                subResult[i][0]=""+rs.getInt("id");
                subResult[i][0]=""+rs.getInt("roll");
                subResult[i][1]=""+rs.getString("name");
                subResult[i][2]=""+rs.getString("sec");
                subResult[i][3]=""+rs.getInt("m_subj");
                subResult[i][4]=""+rs.getInt("m_obj");
                subResult[i][5]=""+rs.getInt("m_prac");
                subResult[i][6]=""+rs.getInt("m_monthly");
                subResult[i][7]=""+rs.getInt("m_sub_total");
                subResult[i][8]=""+rs.getInt("m_total");
                subResult[i][9]=""+rs.getInt("f_subj");
                subResult[i][10]=""+rs.getInt("f_obj");
                subResult[i][11]=""+rs.getInt("f_prac");
                subResult[i][12]=""+rs.getInt("f_monthly");
                subResult[i][13]=""+rs.getInt("f_sub_total");
                subResult[i][14]=""+rs.getInt("f_total");
            }
            return subResult;
        }
        catch(Exception e){
            e.printStackTrace();
            return subResult;
        }
    }

    protected static boolean isStudentExistSubjectTable(int clas, String section, String subject, int roll)
    {
        System.out.println("Is Student Exist Subject table");
        String classSection="c_"+clas+"_"+subject;

        try {
            stmt = conn.createStatement();
            sql = "use result_processing_db";
            stmt.executeUpdate(sql);
            sql="select roll from "+classSection+" where sec='"+section+"';";
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                if(rs.getInt("roll")==roll)
                {
                    return true;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return false;
    }

    protected static int[] studentSubjectResultDetails(int clas, String section, String subject, int roll)
    {
        System.out.println("student subject result details accessed");
        String classSection="c_"+clas+"_"+subject;
        int [] studentResult=null;

        try {
            stmt = conn.createStatement();
            sql = "use result_processing_db";
            stmt.executeUpdate(sql);
            sql="select * from "+classSection+" where sec='"+section+"' and roll="+roll+";";
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                if(rs.getInt("roll")==roll)
                {
                    studentResult=new int[12];
                    studentResult[0]=rs.getInt("m_subj");
                    studentResult[1]=rs.getInt("m_obj");
                    studentResult[2]=rs.getInt("m_prac");
                    studentResult[3]=rs.getInt("m_monthly");
                    studentResult[4]=rs.getInt("m_sub_total");
                    studentResult[5]=rs.getInt("m_total");
                    studentResult[6]=rs.getInt("f_subj");
                    studentResult[7]=rs.getInt("f_obj");
                    studentResult[8]=rs.getInt("f_prac");
                    studentResult[9]=rs.getInt("f_monthly");
                    studentResult[10]=rs.getInt("f_sub_total");
                    studentResult[11]=rs.getInt("f_total");
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return studentResult;
        }
        return studentResult;
    }

    protected static boolean updateStudentResultMid(int clas, String section, String subject, int roll, int midSubjective, int midObjective,
                                                    int midPractical, int midMonthly, int midSubTotal, int midTotal){
        System.out.println("update student subject result for Mid accessed");
        String classSubject="c_"+clas+"_"+subject;

        try{
            stmt = conn.createStatement();
            sql = "use result_processing_db";
            stmt.executeUpdate(sql);
            sql="update "+classSubject+" set m_subj = "+midSubjective+", m_obj = "+midObjective+", m_prac ="+midPractical+
                    ", m_monthly = "+midMonthly+" , m_sub_total = "+midSubTotal+", m_total = "+midTotal+
                    " where roll = "+roll+" and sec = '"+section+"';";
            stmt.executeUpdate(sql);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    protected static boolean updateStudentResultFinal(int clas, String section, String subject, int roll, int finalSubjective, int finalObjective,
                                                    int finalPractical, int finalMonthly, int finalSubTotal, int finalTotal){
//        System.out.println("update student subject result for Final accessed");
        String classSubject="c_"+clas+"_"+subject;

        try{
            stmt = conn.createStatement();
            sql = "use result_processing_db";
            stmt.executeUpdate(sql);
            sql="update "+classSubject+" set f_subj = "+finalSubjective+", f_obj = "+finalObjective+", f_prac ="+finalPractical+
                    ", f_monthly = "+finalMonthly+" , f_sub_total = "+finalSubTotal+", f_total = "+finalTotal+
                    " where roll = "+roll+" and sec = '"+section+"';";
            stmt.executeUpdate(sql);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    protected static double[] subjectSettings(int clas, String subject){
        System.out.println("subject settings accessed");
        String classSubject="c_"+clas+"_"+subject;
        double [] subjectSettings=null;
        try{
            stmt = conn.createStatement();
            sql = "use result_processing_db";
            stmt.executeUpdate(sql);
            sql="select * from subject_settings where class_subject='"+classSubject+"';";
            rs=stmt.executeQuery(sql);
            if(rs.next()){
                subjectSettings=new double[6];
                subjectSettings[0]=rs.getDouble("subjective");
                subjectSettings[1]=rs.getDouble("objective");
                subjectSettings[2]=rs.getDouble("practical");
                subjectSettings[3]=rs.getDouble("subTotal");
                subjectSettings[4]=rs.getDouble("mE");
                subjectSettings[5]=rs.getDouble("total");
            }

        }
        catch (Exception e){
            e.printStackTrace();
            return subjectSettings;
        }
        return subjectSettings;
    }

    private static boolean addStudentInSubjects(int clas,int id, int roll, String name, String section){

//        System.out.println("add Student In Subjects accessed");
        try {
            stmt = conn.createStatement();
            sql = "use result_processing_db";
            stmt.executeUpdate(sql);

            DatabaseMetaData metadeta=conn.getMetaData();
            rs=metadeta.getTables("result_processing_db", null,  "%",null);

            String str, classSubject;
            String [] strings;
            while(rs.next()) {
                str = rs.getString("TABLE_NAME");
                strings=str.split("_");
                if(strings[0].equals("c")){
                    if(clas==Integer.parseInt(strings[1])){
                        classSubject="c_"+clas+"_"+strings[2];

                        System.out.println("Adding student "+name+" to class_Subject "+classSubject);

                        sql="INSERT INTO "+classSubject+" (id, roll, name, sec)"+
                                " values("+id+", "+roll+", '"+name+"', '"+section+"');";

                        stmt.executeUpdate(sql);
                    }
                }
            }

            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    protected static String [][] showStudentResultMid(int clas, String section, int roll){
//        System.out.println("show Student Result Mid Accessed");
        /*String [] CSS=classSectionSubject.split("_");
        String cs="c_"+CSS[1]+"_"+CSS[4];*/
        String [][] studentResults=null;

        try {
            stmt = conn.createStatement();
            sql = "use result_processing_db";
            stmt.executeUpdate(sql);

            DatabaseMetaData metadeta = conn.getMetaData();
            rs = metadeta.getTables("result_processing_db", null, "%", null);

            String str, classSubject;
            String [] strings;
            int count=0, i=0;
            while(rs.next()){
                str = rs.getString("TABLE_NAME");
                strings=str.split("_");
                if(strings[0].equals("c")){
                    if(clas==Integer.parseInt(strings[1]))
                        count++;
                }
            }
            studentResults=new String[count][7];
//            System.out.println("Total subject Found: "+count);

            DatabaseMetaData metadeta1 = conn.getMetaData();
            rs = metadeta1.getTables("result_processing_db", null, "%", null);
            i=0;
            while(rs.next()){
                str = rs.getString("TABLE_NAME");
                strings=str.split("_");
                if(strings[0].equals("c")){
                    if(clas==Integer.parseInt(strings[1])){
                        sql="select * from "+str+" where sec='"+section+"' and roll="+roll+";";
                        rs1=stmt.executeQuery(sql);
                        studentResults[i][0]=str;
                        rs1.next();
//                        System.out.println(rs1.getInt("m_subj"));
                        studentResults[i][1]=""+rs1.getInt("m_subj");
                        studentResults[i][2]=""+rs1.getInt("m_obj");
                        studentResults[i][3]=""+rs1.getInt("m_prac");
                        studentResults[i][4]=""+rs1.getInt("m_monthly");
                        studentResults[i][5]=""+rs1.getInt("m_sub_total");
                        studentResults[i][6]=""+rs1.getInt("m_total");
                        i++;
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return studentResults;
    }

    protected static String [][] showStudentResultFinal(int clas, String section, int roll){
        System.out.println("show Student Result Mid Accessed");
        /*String [] CSS=classSectionSubject.split("_");
        String cs="c_"+CSS[1]+"_"+CSS[4];*/
        String [][] studentResults=null;

        try {
            stmt = conn.createStatement();
            sql = "use result_processing_db";
            stmt.executeUpdate(sql);

            DatabaseMetaData metadeta = conn.getMetaData();
            rs = metadeta.getTables("result_processing_db", null, "%", null);

            String str, classSubject;
            String [] strings;
            int count=0, i=0;
            while(rs.next()){
                str = rs.getString("TABLE_NAME");
                strings=str.split("_");
                if(strings[0].equals("c")){
                    if(clas==Integer.parseInt(strings[1]))
                        count++;
                }
            }
            studentResults=new String[count][7];
//            System.out.println("Total subject Found: "+count);

            DatabaseMetaData metadeta1 = conn.getMetaData();
            rs = metadeta1.getTables("result_processing_db", null, "%", null);
            i=0;
            while(rs.next()){
                str = rs.getString("TABLE_NAME");
                strings=str.split("_");
                if(strings[0].equals("c")){
                    if(clas==Integer.parseInt(strings[1])){
                        sql="select * from "+str+" where sec='"+section+"' and roll="+roll+";";
                        rs1=stmt.executeQuery(sql);
                        studentResults[i][0]=str;
                        rs1.next();
//                        System.out.println(rs1.getInt("f_subj"));
                        studentResults[i][1]=""+rs1.getInt("f_subj");
                        studentResults[i][2]=""+rs1.getInt("f_obj");
                        studentResults[i][3]=""+rs1.getInt("f_prac");
                        studentResults[i][4]=""+rs1.getInt("f_monthly");
                        studentResults[i][5]=""+rs1.getInt("f_sub_total");
                        studentResults[i][6]=""+rs1.getInt("f_total");
                        i++;
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return studentResults;
    }

}