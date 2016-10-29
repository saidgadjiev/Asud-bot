package db.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import db.models.Hudson;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by said on 27.10.16.
 */
public class HudsonDAO {
    private final String url = "jdbc:sqlite:asud_bot.sqlite";
    ConnectionSource connectionSource;
    Dao<Hudson, Integer> dao;

    public HudsonDAO() throws SQLException {
        connectionSource = new JdbcConnectionSource(url);
        dao = DaoManager.createDao(connectionSource, Hudson.class);
        TableUtils.createTableIfNotExists(connectionSource, Hudson.class);
    }

    public Hudson getByName(String name) throws SQLException {
        List<Hudson> hudsons = dao.queryForEq("name", name);

        if (hudsons.size() == 0) {
            return null;
        }

        return hudsons.get(0);
    }

    public List<Hudson> getAll() throws SQLException {
        return dao.queryForAll();
    }

    public int create(Hudson hudson) throws SQLException {
        return dao.create(hudson);
    }

    public Dao.CreateOrUpdateStatus createOrUpdate(Hudson hudson) throws SQLException {
        return dao.createOrUpdate(hudson);
    }
}
