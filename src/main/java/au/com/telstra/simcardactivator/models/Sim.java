package au.com.telstra.simcardactivator.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sim {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long ID;
    String iccid;
    String customerEmail;
    boolean activated = false;
}
