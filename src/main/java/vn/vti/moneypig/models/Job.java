package vn.vti.moneypig.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "jobs")
public class Job {
    @Id
    private Long id;
    @Transient
    public static final String SEQUENCE_NAME = "job_sequence";
    private String serviceCode;
    private String serviceType;
    private Long userID;
    private double price;
    private  double discount;
    private String paymentType;
    private int status;
    private long startDate; // yyyyMMDDHHmmss
    private long createdDate; // yyyyMMDDHHmmss
}
