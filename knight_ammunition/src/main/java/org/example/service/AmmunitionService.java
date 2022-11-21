package org.example.service;

import org.example.dao.IDao;
import org.example.entity.Ammunition;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AmmunitionService {
    IDao<Ammunition> dao;

    public AmmunitionService(IDao<Ammunition> dao) {
        this.dao = dao;
    }

    public List<Ammunition> findAll() throws Exception {
        return dao.findAll();
    }

    public Ammunition findEntityById(int id) throws Exception {
        return dao.findEntityById(id);
    }

    public boolean delete(Ammunition ammunition) throws Exception {
        return dao.delete(ammunition);
    }

    public boolean delete(int id) throws Exception {
        return dao.delete(id);
    }

    public boolean create(Ammunition ammunition) throws Exception {
        return dao.create(ammunition);
    }

    public double computeTotalEquippedAmmunitionCost() throws Exception {
        List<Ammunition> all = dao.findAll();
        return all.stream()
                .map(Ammunition::getCost)
                .reduce(0d, Double::sum);
    }

    public List<Ammunition> getAmmunitionListSortedByWeight() throws Exception {
        List<Ammunition> all = dao.findAll();
        all.sort(Comparator.comparingDouble(Ammunition::getWeight));
        return all;
    }

    public List<Ammunition> getAmmunitionListInCostRange(long inclusiveStart, long inclusiveEnd) throws Exception {
        List<Ammunition> all = dao.findAll();
        Predicate<Ammunition> isInCostRange = x -> x.getCost() >= inclusiveStart
                                                && x.getCost() <= inclusiveEnd;
        return all.stream()
                .filter(isInCostRange)
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
