package com.example2;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "_id",
        "_rev",
        "customerNumber",
        "referenceNumber",
        "payToAccount",
        "amount",
        "payRule",
        "payPriority",
        "payBefore",
        "payStatus"
})
public class Value {

    @JsonProperty("_id")
    private String Id;
    @JsonProperty("_rev")
    private String Rev;
    @JsonProperty("customerNumber")
    private String customerNumber;
    @JsonProperty("referenceNumber")
    private String referenceNumber;
    @JsonProperty("payToAccount")
    private PayToAccount payToAccount;
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("payRule")
    private String payRule;
    @JsonProperty("payPriority")
    private String payPriority;
    @JsonProperty("payBefore")
    private String payBefore;
    @JsonProperty("payStatus")
    private String payStatus;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The Id
     */
    @JsonProperty("_id")
    public String getId() {
        return Id;
    }

    /**
     *
     * @param Id
     * The _id
     */
    @JsonProperty("_id")
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     *
     * @return
     * The Rev
     */
    @JsonProperty("_rev")
    public String getRev() {
        return Rev;
    }

    /**
     *
     * @param Rev
     * The _rev
     */
    @JsonProperty("_rev")
    public void setRev(String Rev) {
        this.Rev = Rev;
    }

    /**
     *
     * @return
     * The customerNumber
     */
    @JsonProperty("customerNumber")
    public String getCustomerNumber() {
        return customerNumber;
    }

    /**
     *
     * @param customerNumber
     * The customerNumber
     */
    @JsonProperty("customerNumber")
    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    /**
     *
     * @return
     * The referenceNumber
     */
    @JsonProperty("referenceNumber")
    public String getReferenceNumber() {
        return referenceNumber;
    }

    /**
     *
     * @param referenceNumber
     * The referenceNumber
     */
    @JsonProperty("referenceNumber")
    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    /**
     *
     * @return
     * The payToAccount
     */
    @JsonProperty("payToAccount")
    public PayToAccount getPayToAccount() {
        return payToAccount;
    }

    /**
     *
     * @param payToAccount
     * The payToAccount
     */
    @JsonProperty("payToAccount")
    public void setPayToAccount(PayToAccount payToAccount) {
        this.payToAccount = payToAccount;
    }

    /**
     *
     * @return
     * The amount
     */
    @JsonProperty("amount")
    public Integer getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     * The amount
     */
    @JsonProperty("amount")
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     *
     * @return
     * The payRule
     */
    @JsonProperty("payRule")
    public String getPayRule() {
        return payRule;
    }

    /**
     *
     * @param payRule
     * The payRule
     */
    @JsonProperty("payRule")
    public void setPayRule(String payRule) {
        this.payRule = payRule;
    }

    /**
     *
     * @return
     * The payPriority
     */
    @JsonProperty("payPriority")
    public String getPayPriority() {
        return payPriority;
    }

    /**
     *
     * @param payPriority
     * The payPriority
     */
    @JsonProperty("payPriority")
    public void setPayPriority(String payPriority) {
        this.payPriority = payPriority;
    }

    /**
     *
     * @return
     * The payBefore
     */
    @JsonProperty("payBefore")
    public String getPayBefore() {
        return payBefore;
    }

    /**
     *
     * @param payBefore
     * The payBefore
     */
    @JsonProperty("payBefore")
    public void setPayBefore(String payBefore) {
        this.payBefore = payBefore;
    }

    /**
     *
     * @return
     * The payStatus
     */
    @JsonProperty("payStatus")
    public String getPayStatus() {
        return payStatus;
    }

    /**
     *
     * @param payStatus
     * The payStatus
     */
    @JsonProperty("payStatus")
    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}