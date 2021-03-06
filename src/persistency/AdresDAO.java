package persistency;

import model.Adres;
import model.Reiziger;

import java.util.List;

public interface AdresDAO {

    boolean save(Adres adres);
    boolean update(Adres adres);
    boolean delete(Adres adres);
    Adres findById(int id);
    Adres findByReiziger(Reiziger reiziger);
    List<Adres> findAll();
}
