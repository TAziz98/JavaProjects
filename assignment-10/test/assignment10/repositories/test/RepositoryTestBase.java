package assignment10.repositories.test;

import org.junit.After;
import org.junit.Before;

import assignment10.dtos.DTOBase;
import assignment10.repositories.IRepository;

public abstract class RepositoryTestBase<TDTO extends DTOBase, TRepository extends IRepository<TDTO>> {

    private TRepository _repository;

    @Before
    public void before() {
        _repository = Create();
    }

    @After
    public void after() {
        if (_repository != null) {
            _repository.rollbackTransaction();
        }
    }

    protected abstract TRepository Create();
}
