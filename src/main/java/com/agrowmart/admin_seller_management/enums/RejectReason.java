package com.agrowmart.admin_seller_management.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RejectReason {

    DOCUMENT_MISMATCH("DOCUMENT_MISMATCH"),
    BLUR_IMAGE("BLUR_IMAGE"),
    INVALID_DOCUMENT("INVALID_DOCUMENT"),
    EXPIRED_DOCUMENT("EXPIRED_DOCUMENT"),
    OTHER("OTHER");

    private final String value;

    RejectReason(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static RejectReason fromValue(String value) {
        for (RejectReason reason : RejectReason.values()) {
            if (reason.value.equalsIgnoreCase(value)) {
                return reason;
            }
        }
        throw new IllegalArgumentException("Invalid reject reason: " + value);
    }
}
