package com.ozgurokanozdal.interfaces;

import java.util.List;

public interface IServis<T> {

    public List<T> getAll();

    public boolean create(T entity);

    public T getById(Long id);

    public T updateById(Long id);

    public boolean deleteById(Long id);




}
