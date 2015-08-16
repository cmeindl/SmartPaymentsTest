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
        "name",
        "bsb",
        "account"
})
public class PayToAccount {

    @JsonProperty("name")
    private String name;
    @JsonProperty("bsb")
    private String bsb;
    @JsonProperty("account")
    private String account;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The bsb
     */
    @JsonProperty("bsb")
    public String getBsb() {
        return bsb;
    }

    /**
     *
     * @param bsb
     * The bsb
     */
    @JsonProperty("bsb")
    public void setBsb(String bsb) {
        this.bsb = bsb;
    }

    /**
     *
     * @return
     * The account
     */
    @JsonProperty("account")
    public String getAccount() {
        return account;
    }

    /**
     *
     * @param account
     * The account
     */
    @JsonProperty("account")
    public void setAccount(String account) {
        this.account = account;
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