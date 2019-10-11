package com.tavisca.springapplication.utility;

import com.tavisca.springapplication.model.User;
import com.tavisca.springapplication.utility.validate.Operation;

import java.util.ArrayList;
import java.util.List;


public class UserRole {
    private static List<UserFields> notUpdatableFieldsByNormalUser
                = new ArrayList<UserFields>(){{
        add(UserFields.DATEOFJOINING);
        add(UserFields.ROLE);
        add(UserFields.SALARY);
        add(UserFields.DEPARTMENTID);
        add(UserFields.UID);
        add(UserFields.CREATEDBY);
        add(UserFields.CREATEDON);
        add(UserFields.ADDRESS);
        }};

    private static List<UserFields> notUpdatableFieldByAdmin = new ArrayList<UserFields>(){{
        add(UserFields.UID);
        add(UserFields.ROLE);
    }};


    public static boolean canDoOperation(User user, Operation operation){

        if(user.getRole().equals("admin"))
            return true;
        else if(user.getRole().equals("normal")){

            if(operation == Operation.ACCESSONE)
                return true;

            if(operation == Operation.QUERYONE)
                return true;

            if(operation == Operation.UPDATE)
                return true;
        }
        return false;
    }

    public static boolean canAccess(User user, int id){
        if(user.getRole().equals("admin"))
            return true;

        if(user.getUid() == id)
            return  true;
        else
            return false;
    }

    public static  boolean canUpdate(User user, List<UserFields> list){
        if(user.getRole().equals("admin")){
            for(UserFields userFields : list){
                if(notUpdatableFieldByAdmin.contains(userFields))
                    return false;
            }
        }
        else{
            for(UserFields userFields : list){
                if(notUpdatableFieldsByNormalUser.contains(userFields))
                    return false;
            }
        }
        return true;
    }

    public static boolean canDoOperation(User operatingUser, Operation accessone, Integer idRequested) {

        if(operatingUser.getRole().equals("admin"))
            return true;
        else if(operatingUser.getRole().equals("normal") && operatingUser.getUid() == idRequested)
            return true;

        return false;
    }
}
