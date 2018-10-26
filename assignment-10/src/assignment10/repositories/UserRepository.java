package assignment10.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import assignment10.dtos.GroupDTO;
import assignment10.dtos.UserDTO;

public class UserRepository implements IUserRepository{
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
    public void add(UserDTO dto) {
        PreparedStatement preparedStatementUser = null;
        PreparedStatement preparedStatementGroups = null;
        PreparedStatement preparedStatementGroupUsers = null;
        if (dto != null) {
            String sqlUser = "insert into users "
                    + "values(?, ?, ?);";
            try {
                ///////////////////////add new user//////////////////////////
                preparedStatementUser = connection.prepareStatement(sqlUser);
                preparedStatementUser.setInt(1, dto.getId());
                preparedStatementUser.setString(2, dto.getPassword());
                preparedStatementUser.setString(3, dto.getLogin());

                int result = preparedStatementUser.executeUpdate();
                if (result != 1) {
                    rollbackTransaction();
                    return;
                }
                /////////////////////////add new groups//////////////////////////
                String sqlChecking = "select groupID "
                        +            "from groups;";
                List<Integer> addedGroups = getIDs(sqlChecking);
                List<GroupDTO> myGroups = dto.getGroups();

                String sqlUsers = "insert into groups "
                        +       "values(?, ?, ?);";
                preparedStatementGroups = connection.prepareStatement(sqlUsers);
                for (GroupDTO g : myGroups) {
                    if (!(addedGroups.contains(g.getId()))) {
                        preparedStatementGroups.setInt(1, g.getId());
                        preparedStatementGroups.setString(2, g.getName());
                        preparedStatementGroups.setString(3, g.getDescription());
                        preparedStatementGroups.addBatch();
                        preparedStatementGroups.clearParameters();
                    }
                }
                int[] resultGroups = preparedStatementGroups.executeBatch();
                ////////////////////add associated records///////////////////////////
                String sqlGroupUsers = "insert into groups_users "
                        + "values(?, ?);";
                preparedStatementGroupUsers = connection.prepareStatement(sqlGroupUsers);
                for (GroupDTO g : myGroups) {
                    preparedStatementGroupUsers.setInt(1, dto.getId());
                    preparedStatementGroupUsers.setInt(2, g.getId());
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
                    if (preparedStatementGroups != null) {
                        preparedStatementGroups.close();
                    }
                    if (preparedStatementUser != null) {
                        preparedStatementUser.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(UserDTO dto) {
        PreparedStatement preparedStatementUser = null;
        PreparedStatement preparedStatementGroups = null;
        PreparedStatement preparedStatementGroupUsers = null;
        PreparedStatement preparedStatementUsersAssociated = null;
        //////////////////////update user////////////////////////
        if (dto != null) {
            String sqlUser = "update users "
                    + "set userPassword = ?, userLogin = ? "
                    + "where userID = ?;";
            try {
                preparedStatementUser = connection.prepareStatement(sqlUser);
                preparedStatementUser.setString(1, dto.getPassword());
                preparedStatementUser.setString(2, dto.getLogin());
                preparedStatementUser.setInt(3, dto.getId());
                int resultUser = preparedStatementUser.executeUpdate();
                if (resultUser != 1) {
                    rollbackTransaction();
                    return;
                }

                String sqlChecking = "select groupID "
                        + "from groups;";
                List<Integer> addedGroups = getIDs(sqlChecking);
                List<GroupDTO> myGroups = dto.getGroups();

                if (myGroups.size() == addedGroups.size() && myGroups.containsAll(addedGroups)) {
                    return;
                }
                /////////////////////////add new groups/////////////////////////
                if (!(addedGroups.containsAll(myGroups))) {
                    String sqlGroups = "insert into groups "
                            +          "values(?, ?, ?);";
                    preparedStatementGroups = connection.prepareStatement(sqlGroups);
                    for (GroupDTO g : myGroups) {
                        if (!(addedGroups.contains(g.getId()))) {
                            preparedStatementGroups.setInt(1, g.getId());
                            preparedStatementGroups.setString(2, g.getName());
                            preparedStatementGroups.setString(3, g.getDescription());
                            preparedStatementGroups.addBatch();
                            preparedStatementGroups.clearParameters();
                        }
                    }
                    int[] resultGroups = preparedStatementGroups.executeBatch();
                }

                //////////////////////delete associated records/////////////////
                String sqlDeleteAssociated = "delete from groups_users "
                        + "where userID = ?;";
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
                for (GroupDTO g : myGroups) {
                    preparedStatementGroupUsers.setInt(1, dto.getId());
                    preparedStatementGroupUsers.setInt(2, g.getId());
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
                    if (preparedStatementGroups != null) {
                        preparedStatementGroups.close();
                    }
                    if (preparedStatementUser != null) {
                        preparedStatementUser.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            
        }
    }

    @Override
    public void delete(UserDTO dto) {
        PreparedStatement preparedStatementDelete = null;
        if (dto != null) {
            String sqlDelete = "delete from users "
                    + "where userID = ?;";
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
    public UserDTO findById(int id) {
        PreparedStatement preparedStatementUser = null;
        PreparedStatement preparedStatementGroups = null;
        ResultSet resultGroups = null;
        ResultSet resultUser = null;
        GroupDTO group = null;
        UserDTO user = null;
        String sqlUser = "select * "
                +         "from users "
                +         "where userID = ?;";
        try {
            preparedStatementUser = connection.prepareStatement(sqlUser);
            preparedStatementUser.setInt(1, id);
            resultUser = preparedStatementUser.executeQuery();
            if(resultUser.next()){
                user = new UserDTO(resultUser.getInt(1), resultUser.getString(3), resultUser.getString(2));
            }
            
            String sqlGroups = "select groups.* "
                    +         "from users, groups, groups_users "
                    +         "where users.userID = groups_users.userID and groups.groupID = groups_users.groupID and users.userID = ?;";
            preparedStatementGroups = connection.prepareStatement(sqlGroups);
            preparedStatementGroups.setInt(1, id);
            resultGroups = preparedStatementGroups.executeQuery();
            while(resultGroups.next()){
                group = new GroupDTO(resultGroups.getInt(1), resultGroups.getString(2), resultGroups.getString(3));
                user.addGroup(group);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
                try {
                    if (resultGroups != null){
                        resultGroups.close();
                    }
                    if (preparedStatementGroups != null) {
                        preparedStatementGroups.close();
                    }
                    if (resultUser != null){
                        resultUser.close();
                    }
                    if (preparedStatementUser != null) {
                        preparedStatementUser.close();
                    }
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        return user;
    }

    @Override
    public List<UserDTO> findByName(String username) {
        PreparedStatement preparedStatementUser = null;
        PreparedStatement preparedStatementGroups = null;
        ResultSet resultGroups = null;
        ResultSet resultUser = null;
        List<UserDTO> users = null;
        GroupDTO group = null;
        UserDTO user = null;
        String sql = "select * "
                +    "from users "
                +    "where userLogin = ?;";
        try {
            preparedStatementUser = connection.prepareStatement(sql);
            preparedStatementUser.setString(1, username);
            resultUser = preparedStatementUser.executeQuery();
            while(resultUser.next()){
                if(users == null)
                    users = new ArrayList<>();
                user = new UserDTO(resultUser.getInt(1), resultUser.getString(3), resultUser.getString(2));
                users.add(user);
            }
            if (users != null) {
                String sqlUsers = "select groups.* "
                        + "from users, groups, groups_users "
                        + "where users.userID = groups_users.userID and groups.groupID = groups_users.groupID and users.userID = ?;";

                preparedStatementGroups = connection.prepareStatement(sqlUsers);
                for (UserDTO u : users) {
                    preparedStatementGroups.setInt(1, u.getId());
                    resultGroups = preparedStatementGroups.executeQuery();
                    while(resultGroups.next()){
                        group = new GroupDTO(resultGroups.getInt(1), resultGroups.getString(2), resultGroups.getString(3));
                        u.addGroup(group);
                    }
                    preparedStatementGroups.clearParameters();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
                try {
                    if (resultUser != null){
                        resultUser.close();
                    }
                    if (preparedStatementGroups != null) {
                        preparedStatementGroups.close();
                    }
                    if (resultGroups != null){
                        resultGroups.close();
                    }
                    if (preparedStatementUser != null) {
                        preparedStatementUser.close();
                    }
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        return users;
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
            String sql = "select count(userID) "
                    +    "from users";
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
