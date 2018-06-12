package DTOs;

import java.util.HashMap;

/**
 * Created by ArbabKhan on 11/06/2018.
 */

public class StudentInfo {

    public String strUserName;
    public String strUserSurname;
    public String strUserId;
    public String strObtainedMarks;

    public StudentInfo(HashMap hashMap) {
        this.strUserName = hashMap.get("user_name").toString();
        this.strUserSurname = hashMap.get("sur_name").toString();
        this.strUserId = hashMap.get("user_id").toString();
        this.strObtainedMarks = hashMap.get("marks").toString();
    }

}
