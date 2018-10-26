package eu.glowacki.utp.assignment10.dtos;

public abstract class DTOBase {

    private int _id;

    protected DTOBase() {
    }

    protected DTOBase(int id) {
        _id = id;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public boolean hasExistingId() {
        return getId() > 0;
    }

    @Override
    public String toString() {
        return _id + "" ;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this._id;
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
        final DTOBase other = (DTOBase) obj;
        if (this._id != other._id) {
            return false;
        }
        return true;
    }
    
    
}
