package com.ozgurokanozdal.interfaces;

import java.util.List;

public interface IServis<E,P> {

    public List<E> getAll();

    public boolean create(E entity);

    public E getById(Long id);

    public boolean updateById(P id, E entity);






}
