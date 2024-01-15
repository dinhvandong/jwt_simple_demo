package vn.vti.moneypig.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "services")
public class Service {
    @Id
    private Long id;

    @Transient
    public static final String SEQUENCE_NAME = "service_sequence";

    private String serviceCode;
    private String serviceType;
    private String serviceName;

}
