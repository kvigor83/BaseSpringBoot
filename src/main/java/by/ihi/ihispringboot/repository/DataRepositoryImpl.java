package by.ihi.ihispringboot.repository;

import by.ihi.ihispringboot.entity.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.HashSet;
import java.util.Set;

@Repository("dataRepository")
public class DataRepositoryImpl implements DataRepository<Data> {
//    private final static String INSERT_QUERY="INSERT INTO spring_boot_table (data_id, data_description) VALUES (cast(? as INT), ?)";
    private final static String INSERT_QUERY="INSERT INTO spring_boot_table (data_id, data_description) VALUES (? , ?)";
    private final static String SELECT_QUERY="SELECT data_description FROM spring_boot_table ORDER BY rand() ASC LIMIT 6";

    @Autowired
    protected JdbcOperations jdbcOperations;

    @Override
    public void persist(Data object) {

        Object[] params = new Object[] { object.getId(), object.getDescription() };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };

        jdbcOperations.update(INSERT_QUERY, params, types);
    }

    @Override
    public void delete(Data object) {
        jdbcOperations.update("DELETE FROM spring_boot_table\n" +
                " WHERE data_id = '" + object.getId().toString() + "';");
    }

    @Override
    public Set<String> getRandomData() {
        Set<String> result = new HashSet<>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(SELECT_QUERY);
        while (rowSet.next()) {
            result.add(rowSet.getString("data_description"));
        }
        return result;
    }


}

