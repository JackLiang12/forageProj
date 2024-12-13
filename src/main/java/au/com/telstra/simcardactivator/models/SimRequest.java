package au.com.telstra.simcardactivator.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SimRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long ID;

    private String iccid;
    private String customerEmail;
    private boolean activated = false;

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
    public String getIccid() {
        return iccid;
    }
    public void setIccid(String iccid) {
        this.iccid = iccid;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public String toString() {
        return "SimRequest [iccid=" + iccid + ", customerEmail=" + customerEmail + ", activated=" + activated + ", ID =" + ID+"]";
    }
}
