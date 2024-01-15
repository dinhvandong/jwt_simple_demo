package vn.vti.moneypig.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vouchers")
public class Voucher {
    private Long id;
    private String title;
    private String prefer;
    private String voucherName;
    private String serviceType;
    private int status;
    private long finishTime;
    private long createdTime;


}
