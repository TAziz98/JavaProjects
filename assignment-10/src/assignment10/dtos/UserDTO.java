package assignment10.dtos;


import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class UserDTO extends DTOBase {

    private String _login;
    private String _password;
    private List<GroupDTO> _groups;

    public UserDTO() {
    }

    public UserDTO(int id, String login, String password) {
        super(id);
        _login = login;
        _password = password;
    }

    public String getLogin() {
        return _login;
    }

    public void setLogin(String login) {
        _login = login;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }

    public List<GroupDTO> getGroups() {
        return _groups;
    }

    public void setGroups(List<GroupDTO> groups) {
        _groups = groups;
    }

    public void addGroup(GroupDTO group) {
        if (_groups == null) {
            _groups = new LinkedList<GroupDTO>();
        }
        _groups.add(group);
    }

    public void deleteGroup(GroupDTO group) {
        if (_groups != null) {
            _groups.remove(group);
        }
    }

    @Override
    public String toString() {
        return "UserDTO{id=" + super.toString()+ ", login=" + _login + ", password=" + _password + '}';
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 19 * hash + Objects.hashCode(this._login);
        hash = 19 * hash + Objects.hashCode(this._password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserDTO other = (UserDTO) obj;
        if (!Objects.equals(this._login, other._login)) {
            return false;
        }
        if (!Objects.equals(this._password, other._password)) {
            return false;
        }
        boolean r = super.equals(obj);
        return r;
    }
    
}
