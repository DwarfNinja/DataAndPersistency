package persistency;

import model.Reiziger;

import java.sql.Connection;
import java.util.List;

public class ReizigerDAOsql implements ReizigerDAO {
    Connection conn;

    public ReizigerDAOsql(Connection conn) {

    }

    @Override
    public boolean save(Reiziger reiziger) {
        return false;
    }

    @Override
    public boolean update(Reiziger reiziger) {
        return false;
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        return false;
    }

    @Override
    public boolean findById(int id) {
        return false;
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        return null;
    }

    @Override
    public List<Reiziger> findAll() {
        return null;
    }
}
