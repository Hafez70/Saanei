package com.example.hmohamadi.ebooklibrary.Models;

public class Setting_model {
    private int _id;
    private int lang_id;
    private String WebSiteUrl;
    private String SenderEmailAddress;
    private String SenderPassword;
    private String RecieverEmailAddress;
    private String SMTPHost;
    private String SMTPSocketFactoryPort;
    private String SMTPSocketFactoryClass;
    private String SMTPAuth;
    private String SMTPPort;
    private String VojoohatSiteUrl;
    private String Version;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getLang_id() {
        return lang_id;
    }

    public void setLang_id(int lang_id) {
        this.lang_id = lang_id;
    }

    public String getWebSiteUrl() {
        return WebSiteUrl;
    }

    public void setWebSiteUrl(String webSiteUrl) {
        WebSiteUrl = webSiteUrl;
    }

    public String getSenderEmailAddress() {
        return SenderEmailAddress;
    }

    public void setSenderEmailAddress(String senderEmailAddress) {
        SenderEmailAddress = senderEmailAddress;
    }

    public String getSenderPassword() {
        return SenderPassword;
    }

    public void setSenderPassword(String senderPassword) {
        SenderPassword = senderPassword;
    }

    public String getRecieverEmailAddress() {
        return RecieverEmailAddress;
    }

    public void setRecieverEmailAddress(String recieverEmailAddress) {
        RecieverEmailAddress = recieverEmailAddress;
    }

    public String getSMTPHost() {
        return SMTPHost;
    }

    public void setSMTPHost(String SMTPHost) {
        this.SMTPHost = SMTPHost;
    }

    public String getSMTPSocketFactoryPort() {
        return SMTPSocketFactoryPort;
    }

    public void setSMTPSocketFactoryPort(String SMTPSocketFactoryPort) {
        this.SMTPSocketFactoryPort = SMTPSocketFactoryPort;
    }

    public String getSMTPSocketFactoryClass() {
        return SMTPSocketFactoryClass;
    }

    public void setSMTPSocketFactoryClass(String SMTPSocketFactoryClass) {
        this.SMTPSocketFactoryClass = SMTPSocketFactoryClass;
    }

    public String getSMTPAuth() {
        return SMTPAuth;
    }

    public void setSMTPAuth(String SMTPAuth) {
        this.SMTPAuth = SMTPAuth;
    }

    public String getSMTPPort() {
        return SMTPPort;
    }

    public void setSMTPPort(String SMTPPort) {
        this.SMTPPort = SMTPPort;
    }

    public String getVojoohatSiteUrl() {
        return VojoohatSiteUrl;
    }

    public void setVojoohatSiteUrl(String vojoohatSiteUrl) {
        VojoohatSiteUrl = vojoohatSiteUrl;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }
}
