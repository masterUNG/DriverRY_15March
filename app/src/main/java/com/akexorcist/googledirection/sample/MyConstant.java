package com.akexorcist.googledirection.sample;

/**
 * Created by DARK on 4/10/2559.
 */

public class MyConstant {

    //Explicit
    private String[] userStrings = new String[]{
            "id",
            "User",
            "Password",
            "Name",
            "Surname",
            "Address",
            "Phone",
            "Id_car",
            "Status"};

    private String[] jobStrings = new String[]{
            "id",
            "ID_passenger",
            "ID_driver",
            "Status",
            "Date",
            "Time",
            "NameStart",
            "Lat_start",
            "Lng_start",
            "NameEnd",
            "Lat_end",
            "Lng_end", "Length"};

    private int portAnInt = 21;
    private String urlGetPassengerWhereID = "http://swiftcodingthai.com/ry/get_passenger_where_id.php";
    private String urlGetJobWhereID = "http://swiftcodingthai.com/ry/get_job_where_idPass_and_Status.php";
    private String hostString = "ftp.swiftcodingthai.com";
    private String userFTPString = "ry@swiftcodingthai.com";
    private String passwordFTPString = "Abc12345";
    private String urlEditJobString = "http://swiftcodingthai.com/ry/edit_job_where_id_ry_master.php";
    private String urlEditMeterBack = "http://swiftcodingthai.com/ry/editMeterBack.php";
    private String urlEditLengthWhereIdStatus = "http://swiftcodingthai.com/ry/editLengthWhereIdStatus.php";
    private String urlEditStatusDriver = "http://swiftcodingthai.com/ry/edit_status_driver.php";
    private String urlEditStatusWhereId = "http://swiftcodingthai.com/ry/edit_status_where_id.php";
    private String urlEditTimeArriveWhereId = "http://swiftcodingthai.com/ry/edit_timeArrive_where_id_status.php";
    private String urlEditTimeWaitWhereIdStatus = "http://swiftcodingthai.com/ry/edit_timeWait_where_id_status.php";
    private String urlEditWalkTimeWhereIdStatus = "http://swiftcodingthai.com/ry/editWalkTimeWhereIdStatus.php";
    private String urlGetStatusWhereIdUser = "http://swiftcodingthai.com/ry/GetStatusWhereIdUser.php";
    private String urlGetJobWhereIdDriverStatus = "http://swiftcodingthai.com/ry/get_job_where_idDriver_Status.php";
    private String serverKey = "AIzaSyDE4-7-stlHCxH4BB539QF9OM4VU1u6HSs";
    private String urlImage = "http://swiftcodingthai.com/ry/Image";
    private String urlEditEndTimeWhereIdStatus = "http://swiftcodingthai.com/ry/edit_endTime_where_id_status.php";

    public String getUrlEditEndTimeWhereIdStatus() {
        return urlEditEndTimeWhereIdStatus;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getServerKey() {
        return serverKey;
    }

    public String getUrlGetJobWhereIdDriverStatus() {
        return urlGetJobWhereIdDriverStatus;
    }

    public String getUrlGetStatusWhereIdUser() {
        return urlGetStatusWhereIdUser;
    }

    public String getUrlEditWalkTimeWhereIdStatus() {
        return urlEditWalkTimeWhereIdStatus;
    }

    public String getUrlEditTimeWaitWhereIdStatus() {
        return urlEditTimeWaitWhereIdStatus;
    }

    public String getUrlEditTimeArriveWhereId() {
        return urlEditTimeArriveWhereId;
    }

    public String getUrlEditStatusWhereId() {
        return urlEditStatusWhereId;
    }

    public String getUrlEditStatusDriver() {
        return urlEditStatusDriver;
    }

    public String getUrlEditLengthWhereIdStatus() {
        return urlEditLengthWhereIdStatus;
    }

    public String getUrlEditMeterBack() {
        return urlEditMeterBack;
    }

    public String getUrlEditJobString() {
        return urlEditJobString;
    }

    public String getHostString() {
        return hostString;
    }

    public int getPortAnInt() {
        return portAnInt;
    }

    public String getUserFTPString() {
        return userFTPString;
    }

    public String getPasswordFTPString() {
        return passwordFTPString;
    }

    public String getUrlGetPassengerWhereID() {
        return urlGetPassengerWhereID;
    }

    public String[] getJobStrings() {
        return jobStrings;
    }

    public String getUrlGetJobWhereID() {
        return urlGetJobWhereID;
    }

    public String[] getUserStrings() {
        return userStrings;
    }
} // Main Class
