package db.models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by said on 27.10.16.
 */
@DatabaseTable(tableName = "hudson")
public class Hudson {

    @DatabaseField(canBeNull = false, id = true, dataType = DataType.STRING, columnName = "name")
    private String name;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING, columnName = "hudson_url")
    private String hudsonUrl;

    @DatabaseField(dataType = DataType.STRING, columnName = "login")
    private String login;

    @DatabaseField(dataType = DataType.STRING, columnName = "password")
    private String password;

    public Hudson() {

    }

    public Hudson(String name, String hudsonUrl) {
        this.name = name;
        this.hudsonUrl = hudsonUrl;
        this.login = "";
        this.password = "";
    }

    public Hudson(String name, String hudsonUrl, String login, String password, String param) {
        this.name = name;
        this.hudsonUrl = hudsonUrl;
        this.login = login;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHudsonUrl() {
        return hudsonUrl;
    }

    public void setHudsonUrl(String hudsonUrl) {
        this.hudsonUrl = hudsonUrl;
    }

    @Override
    public String toString() {
        return name + " " + hudsonUrl + " " + login + " " + password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
