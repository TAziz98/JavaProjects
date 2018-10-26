package eu.glowacki.utp.assignment10.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eu.glowacki.utp.assignment10.dtos.GroupDTO;
import eu.glowacki.utp.assignment10.dtos.UserDTO;

public class GroupRepository implements IGroupRepository{
    private Connection connection = getConnection();
    
    @Override
    public Connection getConnection() {
        Connection con = null;
        try {
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment10", "root", "root");
           con.setAutoCommit(false);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return con;
    }
    
    @Override
    public void add(GroupDTO dto) {
        PreparedStatement preparedStatementGroup = null;
        PreparedStatement preparedStatementUsers = null;
        PreparedStatement preparedStatementGroupUsers = null;
        if (dto != null) {
            String sqlGroup = "insert into groups "
                    + "values(?, ?, ?);";
            try {
                ///////////////////////add new group//////////////////////////
                preparedStatementGroup = connection.prepareStatement(sqlGroup);
                preparedStatementGroup.setInt(1, dto.getId());
                preparedStatementGroup.setString(2, dto.getName());
                preparedStatementGroup.setString(3, dto.getDescription());

                int result = preparedStatementGroup.executeUpdate();
                if (result != 1) {
                    rollbackTransaction();
                    return;
                }
                /////////////////////////add new users//////////////////////////
                String sqlChecking = "select userID "
                        + "from users;";
                List<Integer> addedUsers = getIDs(sqlChecking);
                List<UserDTO> myUsers = dto.getUsers();

                String sqlUsers = "insert into users "
                        + "values(?, ?, ?);";
                preparedStatementUsers = connection.prepareStatement(sqlUsers);
                for (UserDTO u : myUsers) {
                    if (!(addedUsers.contains(u.getId()))) {
                        preparedStatementUsers.setInt(1, u.getId());
                        preparedStatementUsers.setString(2, u.getPassword());
                        preparedStatementUsers.setString(3, u.getLogin());
                        preparedStatementUsers.addBatch();
                        preparedStatementUsers.clearParameters();
                    }
                }
                int[] resultUsers = preparedStatementUsers.executeBatch();
                ////////////////////add associated records///////////////////////////
                String sqlGroupUsers = "insert into groups_users "
                        + "values(?, ?);";
                preparedStatementGroupUsers = connection.prepareStatement(sqlGroupUsers);
                for (UserDTO u : myUsers) {
                    preparedStatementGroupUsers.setInt(1, u.getId());
                    preparedStatementGroupUsers.setInt(2, dto.getId());
                    preparedStatementGroupUsers.addBatch();
                    preparedStatementGroupUsers.clearParameters();
                }
                int[] resultGroupUsers = preparedStatementGroupUsers.executeBatch();
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (preparedStatementGroupUsers != null) {
                        preparedStatementGroupUsers.close();
                    }
                    if (preparedStatementUsers != null) {
                        preparedStatementUsers.close();
                    }
                    if (preparedStatementGroup != null) {
                        preparedStatementGroup.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(GroupDTO dto) {
        PreparedStatement preparedStatementGroup = null;
        PreparedStatement preparedStatementUsers = null;
        PreparedStatement preparedStatementGroupUsers = null;
        PreparedStatement preparedStatementUsersAssociated = null;
        //////////////////////update group////////////////////////
        if (dto != null) {
            String sqlGroup = "update groups "
                    + "set groupName = ?, groupDescription = ? "
                    + "where groupID = ?;";
            try {
                preparedStatementGroup = connection.prepareStatement(sqlGroup);
                preparedStatementGroup.setString(1, dto.getName());
                preparedStatementGroup.setString(2, dto.getDescription());
                preparedStatementGroup.setInt(3, dto.getId());
                int resultGroup = preparedStatementGroup.executeUpdate();
                if (resultGroup != 1) {
                    rollbackTransaction();
                    return;
                }

                String sqlChecking = "select userID "
                        + "from users;";
                List<Integer> addedUsers = getIDs(sqlChecking);
                List<UserDTO> myUsers = dto.getUsers();

                if (myUsers.size() == addedUsers.size() && myUsers.containsAll(addedUsers)) {
                    return;
                }
                ///////////////////////////add new users//////////////////////////////
                if (!(addedUsers.containsAll(myUsers))) {
                    String sqlUsers = "insert into users "
                            + "values(?, ?, ?);";
                    preparedStatementUsers = connection.prepareStatement(sqlUsers);
                    for (UserDTO u : myUsers) {
                        if (!(addedUsers.contains(u.getId()))) {
                            preparedStatementUsers.setInt(1, u.getId());
                            preparedStatementUsers.setString(2, u.getPassword());
                            preparedStatementUsers.setString(3, u.getLogin());
                            preparedStatementUsers.addBatch();
                            preparedStatementUsers.clearParameters();
                        }
                    }
                    int[] resultUsers = preparedStatementUsers.executeBatch();
                }

                //////////////////////delete associated records/////////////////
                String sqlDeleteAssociated = "delete from groups_users "
                        + "where groupID = ?;";
                preparedStatementUsersAssociated = connection.prepareStatement(sqlDeleteAssociated);
                preparedStatementUsersAssociated.setInt(1, dto.getId());
                int resultUsersAssociated = preparedStatementUsersAssociated.executeUpdate();
                if (resultUsersAssociated < 0) {
                    rollbackTransaction();
                    return;
                }

                ///////////////////////renew associated records////////////////////////
                String sqlGroupUsers = "insert into groups_users "
                        + "values(?, ?);";
                preparedStatementGroupUsers = connection.prepareStatement(sqlGroupUsers);
                for (UserDTO u : myUsers) {
                    preparedStatementGroupUsers.setInt(1, u.getId());
                    preparedStatementGroupUsers.setInt(2, dto.getId());
                    preparedStatementGroupUsers.addBatch();
                    preparedStatementGroupUsers.clearParameters();
                }
                int[] resultGroupUsers = preparedStatementGroupUsers.executeBatch();

            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (preparedStatementGroupUsers != null) {
                        preparedStatementGroupUsers.close();
                    }
                    if (preparedStatementUsersAssociated != null){
                        preparedStatementUsersAssociated.close();
                    }
                    if (preparedStatementUsers != null) {
                        preparedStatementUsers.close();
                    }
                    if (preparedStatementGroup != null) {
                        preparedStatementGroup.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            
        }
    }

    @Override
    public void delete(GroupDTO dto) {
        PreparedStatement preparedStatementDelete = null;
        if (dto != null) {
            String sqlDelete = "delete from groups "
                    + "where groupID = ?;";
            try {
                preparedStatementDelete = connection.prepareStatement(sqlDelete);
                preparedStatementDelete.setInt(1, dto.getId());
                int resultDelete = preparedStatementDelete.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (preparedStatementDelete != null) {
                        preparedStatementDelete.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } 
            }
        }
    }

    @Override
    public GroupDTO findById(int id) {
        PreparedStatement preparedStatementGroup = null;
        PreparedStatement preparedStatementUsers = null;
        ResultSet resultUsers = null;
        ResultSet resultGroup = null;
        GroupDTO group = null;
        UserDTO user = null;
        String sqlGroup = "select * "
                +         "from groups "
                +         "where groupID = ?;";
        try {
            preparedStatementGroup = connection.prepareStatement(sqlGroup);
            preparedStatementGroup.setInt(1, id);
            resultGroup = preparedStatementGroup.executeQuery();
            if(resultGroup.next()){
                group = new GroupDTO(resultGroup.getInt(1), resultGroup.getString(2), resultGroup.getString(3));
            }
            
            String sqlUsers = "select users.* "
                    +         "from users, groups, groups_users "
                    +         "where users.userID = groups_users.userID and groups.groupID = groups_users.groupID and groups.groupID = ?;";
            preparedStatementUsers = connection.prepareStatement(sqlUsers);
            preparedStatementUsers.setInt(1, id);
            resultUsers = preparedStatementUsers.executeQuery();
            while(resultUsers.next()){
                user = new UserDTO(resultUsers.getInt(1), resultUsers.getString(3), resultUsers.getString(2));
                group.addUser(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
                try {
                    if (resultUsers != null){
                        resultUsers.close();
                    }
                    if (preparedStatementUsers != null) {
                        preparedStatementUsers.close();
                    }
                    if (resultGroup != null){
                        resultGroup.close();
                    }
                    if (preparedStatementGroup != null) {
                        preparedStatementGroup.close();
                    }
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        return group;
    }
    
    @Override
    public List<GroupDTO> findByName(String name) {
        PreparedStatement preparedStatementGroup = null;
        PreparedStatement preparedStatementUsers = null;
        ResultSet resultUsers = null;
        ResultSet resultGroup = null;
        List<GroupDTO> groups = null;
        GroupDTO group = null;
        UserDTO user = null;
        String sql = "select * "
                +    "from groups "
                +    "where groupName = ?;";
        try {
            preparedStatementGroup = connection.prepareStatement(sql);
            preparedStatementGroup.setString(1, name);
            resultGroup = preparedStatementGroup.executeQuery();
            while(resultGroup.next()){
                if(groups == null)
                    groups = new ArrayList<>();
                group = new GroupDTO(resultGroup.getInt(1), resultGroup.getString(2), resultGroup.getString(3));
                groups.add(group);
            }
            if (groups != null) {
                String sqlUsers = "select users.* "
                        + "from users, groups, groups_users "
                        + "where users.userID = groups_users.userID and groups.groupID = groups_users.groupID and groups.groupID = ?;";

                preparedStatementUsers = connection.prepareStatement(sqlUsers);
                for (GroupDTO g : groups) {
                    preparedStatementUsers.setInt(1, g.getId());
                    resultUsers = preparedStatementUsers.executeQuery();
                    while(resultUsers.next()){
                        user = new UserDTO(resultUsers.getInt(1), resultUsers.getString(3), resultUsers.getString(2));
                        g.addUser(user);
                    }
                    preparedStatementUsers.clearParameters();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
                try {
                    if (resultUsers != null){
                        resultUsers.close();
                    }
                    if (preparedStatementUsers != null) {
                        preparedStatementUsers.close();
                    }
                    if (resultGroup != null){
                        resultGroup.close();
                    }
                    if (preparedStatementGroup != null) {
                        preparedStatementGroup.close();
                    }
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        return groups;
    }

    @Override
    public void rollbackTransaction() {
        if(connection!=null)
            try {
                connection.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Integer> getIDs(String sql) {
        List<Integer> ids = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next())
                ids.add(result.getInt(1));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ids;
    }

    @Override
    public int rowCount() {
        int count = 0;
        try {
            String sql = "select count(groupID) "
                    +    "from groups";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            count = result.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    
}
