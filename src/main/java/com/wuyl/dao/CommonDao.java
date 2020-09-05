package com.wuyl.dao;

public interface CommonDao {
    int qryLastInsertId();

    int qrySequence(String seqName);
}
