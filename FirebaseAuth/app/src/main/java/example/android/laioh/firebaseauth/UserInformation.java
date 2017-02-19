package example.android.laioh.firebaseauth;

/**
 * Created by Lai.OH on 2017-02-19.
 */

public class UserInformation {

    private String name;
    private String address;

    public UserInformation() {

    }

    public UserInformation(String name, String address) {

        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
