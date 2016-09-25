package com.finleash.eserve.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A MutualFundNav.
 */
@Entity
@Table(name = "mutual_fund_nav")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MutualFundNav implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "scheme_code")
    private String schemeCode;

    @Column(name = "isin_div_payout_isin_growth")
    private String isinDivPayoutIsinGrowth;

    @Column(name = "status")
    private String status;

    @Column(name = "scheme_name")
    private String schemeName;

    @Column(name = "net_asset_value")
    private String netAssetValue;

    @Column(name = "repurchase_price")
    private String repurchasePrice;

    @Column(name = "sale_price")
    private String salePrice;

    @Column(name = "date")
    private String date;

    @Column(name = "updated_date")
    private String updatedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public MutualFundNav schemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
        return this;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getIsinDivPayoutIsinGrowth() {
        return isinDivPayoutIsinGrowth;
    }

    public MutualFundNav isinDivPayoutIsinGrowth(String isinDivPayoutIsinGrowth) {
        this.isinDivPayoutIsinGrowth = isinDivPayoutIsinGrowth;
        return this;
    }

    public void setIsinDivPayoutIsinGrowth(String isinDivPayoutIsinGrowth) {
        this.isinDivPayoutIsinGrowth = isinDivPayoutIsinGrowth;
    }

    public String getStatus() {
        return status;
    }

    public MutualFundNav status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public MutualFundNav schemeName(String schemeName) {
        this.schemeName = schemeName;
        return this;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getNetAssetValue() {
        return netAssetValue;
    }

    public MutualFundNav netAssetValue(String netAssetValue) {
        this.netAssetValue = netAssetValue;
        return this;
    }

    public void setNetAssetValue(String netAssetValue) {
        this.netAssetValue = netAssetValue;
    }

    public String getRepurchasePrice() {
        return repurchasePrice;
    }

    public MutualFundNav repurchasePrice(String repurchasePrice) {
        this.repurchasePrice = repurchasePrice;
        return this;
    }

    public void setRepurchasePrice(String repurchasePrice) {
        this.repurchasePrice = repurchasePrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public MutualFundNav salePrice(String salePrice) {
        this.salePrice = salePrice;
        return this;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getDate() {
        return date;
    }

    public MutualFundNav date(String date) {
        this.date = date;
        return this;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public MutualFundNav updatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MutualFundNav mutualFundNav = (MutualFundNav) o;
        if(mutualFundNav.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, mutualFundNav.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MutualFundNav{" +
            "id=" + id +
            ", schemeCode='" + schemeCode + "'" +
            ", isinDivPayoutIsinGrowth='" + isinDivPayoutIsinGrowth + "'" +
            ", status='" + status + "'" +
            ", schemeName='" + schemeName + "'" +
            ", netAssetValue='" + netAssetValue + "'" +
            ", repurchasePrice='" + repurchasePrice + "'" +
            ", salePrice='" + salePrice + "'" +
            ", date='" + date + "'" +
            ", updatedDate='" + updatedDate + "'" +
            '}';
    }
}
