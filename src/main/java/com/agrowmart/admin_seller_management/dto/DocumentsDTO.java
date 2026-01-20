package com.agrowmart.admin_seller_management.dto;

import com.agrowmart.admin_seller_management.enums.DocumentStatus;

public class DocumentsDTO {

    private String aadhaar;
    private String pan;
    private String udyam;
    private DocumentStatus aadhaarStatus;
    private DocumentStatus panStatus;
    private DocumentStatus udyamStatus;
//    private String ShopLicense;

    // ================= GETTERS & SETTERS =================

//    public String getShopLicense() {
//		return ShopLicense;
//	}
//
//	public void setShopLicense(String shopLicense) {
//		ShopLicense = shopLicense;
//	}

	public String getAadhaar() {
        return aadhaar;
    }

    public DocumentStatus getAadhaarStatus() {
		return aadhaarStatus;
	}

	public void setAadhaarStatus(DocumentStatus aadhaarStatus) {
		this.aadhaarStatus = aadhaarStatus;
	}

	public DocumentStatus getPanStatus() {
		return panStatus;
	}

	public void setPanStatus(DocumentStatus panStatus) {
		this.panStatus = panStatus;
	}

	public DocumentStatus getUdyamStatus() {
		return udyamStatus;
	}

	public void setUdyamStatus(DocumentStatus udyamStatus) {
		this.udyamStatus = udyamStatus;
	}

	public void setAadhaar(String aadhaar) {
        this.aadhaar = aadhaar;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getUdyam() {
        return udyam;
    }

    public void setUdyam(String udyam) {
        this.udyam = udyam;
    }
}
