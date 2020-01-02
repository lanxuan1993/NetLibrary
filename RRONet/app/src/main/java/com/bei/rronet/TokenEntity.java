package com.bei.rronet;

import com.bhfae.net.http.base.BaseEntity;

/**
 * @author: created by ZhaoBeibei on 2020-01-02 14:48
 * @describe:
 */
public class TokenEntity extends BaseEntity {
    /**
     * apiName : fetchToken
     * saleSystem : WMS
     * tradeWay : 01
     * clientType : 02
     * clientIp : 10.20.150.34
     * stationId : T00000
     * apiVersion : 1.0
     * token : 33b3204793204416a94c78874708e0987
     * trackToken : 04794FE06120E62E6D
     * visitorId :
     * appVersion : 2.7.5
     * appClientType : null
     * appMachineBrand : null
     * appMachineModel : null
     * appSystemVersion : null
     * reqToken : null
     * resultCode : SUCCESS
     * message : null
     * timestamp : 2019-12-25 18:31:38
     * body : null
     */

    private String apiName;
    private String saleSystem;
    private String tradeWay;
    private String clientType;
    private String clientIp;
    private String stationId;
    private String apiVersion;
    private String token;
    private String trackToken;
    private String visitorId;
    private String appVersion;
    private Object appClientType;
    private Object appMachineBrand;
    private Object appMachineModel;
    private Object appSystemVersion;
    private Object reqToken;
    private String resultCode;
    private Object message;
    private String timestamp;
    private Object body;

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getSaleSystem() {
        return saleSystem;
    }

    public void setSaleSystem(String saleSystem) {
        this.saleSystem = saleSystem;
    }

    public String getTradeWay() {
        return tradeWay;
    }

    public void setTradeWay(String tradeWay) {
        this.tradeWay = tradeWay;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTrackToken() {
        return trackToken;
    }

    public void setTrackToken(String trackToken) {
        this.trackToken = trackToken;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Object getAppClientType() {
        return appClientType;
    }

    public void setAppClientType(Object appClientType) {
        this.appClientType = appClientType;
    }

    public Object getAppMachineBrand() {
        return appMachineBrand;
    }

    public void setAppMachineBrand(Object appMachineBrand) {
        this.appMachineBrand = appMachineBrand;
    }

    public Object getAppMachineModel() {
        return appMachineModel;
    }

    public void setAppMachineModel(Object appMachineModel) {
        this.appMachineModel = appMachineModel;
    }

    public Object getAppSystemVersion() {
        return appSystemVersion;
    }

    public void setAppSystemVersion(Object appSystemVersion) {
        this.appSystemVersion = appSystemVersion;
    }

    public Object getReqToken() {
        return reqToken;
    }

    public void setReqToken(Object reqToken) {
        this.reqToken = reqToken;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
