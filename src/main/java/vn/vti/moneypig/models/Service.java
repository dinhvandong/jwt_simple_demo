package vn.vti.moneypig.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    public Service() {
    }

    public Service(Long id, String serviceCode, String serviceType, String serviceName) {
        this.id = id;
        this.serviceCode = serviceCode;
        this.serviceType = serviceType;
        this.serviceName = serviceName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
