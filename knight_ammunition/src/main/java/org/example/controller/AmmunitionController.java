package org.example.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.example.entity.Ammunition;
import org.example.service.AmmunitionService;
import org.example.utils.ReflectionUtils;
import org.example.view.AmmunitionView;

import java.util.List;

public class AmmunitionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AmmunitionService.class.getName());
    AmmunitionService ammunitionService;
    AmmunitionView ammunitionView;

    public AmmunitionController(AmmunitionService ammunitionService) {
        this.ammunitionService = ammunitionService;
        ammunitionView = new AmmunitionView(this, ammunitionService);
    }

    public void initView() {
        LOGGER.info("App is started");
        ammunitionView.initView();
    }

    public void updateView() {
        ammunitionView.updateView();
    }


    public void findAll()  {
        try {
            ammunitionView.printAmmunition(ammunitionService.findAll());
        } catch (Exception e) {

            LOGGER.warn("Input data for "+ReflectionUtils.getCurrentExecutingMethod() + " was incorrect");
           // throw new RuntimeException(e);
        }
    }

    public void findEntityById(int id)  {
        try {
            Ammunition ammunitionById = ammunitionService.findEntityById(id);
            ammunitionView.printAmmunitionById(id, ammunitionById);
        } catch (java.lang.Exception e) {
            LOGGER.warn("Input data for "+ReflectionUtils.getCurrentExecutingMethod() + " was incorrect");
            // throw new RuntimeException(e);
        }
    }

    public void delete(Ammunition ammunition){
        try {
            ammunitionService.delete(ammunition);
            ammunitionView.printDeleted(ammunition);
        } catch (Exception e) {
            LOGGER.warn("Input data for "+ReflectionUtils.getCurrentExecutingMethod() + " was incorrect");
            // throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try {
            Ammunition entityById = ammunitionService.findEntityById(id);
            ammunitionService.delete(id);
            ammunitionView.printDeleted(entityById);
        } catch (java.lang.Exception e) {
            LOGGER.warn("Input data for "+ReflectionUtils.getCurrentExecutingMethod() + " was incorrect");
            // throw new RuntimeException(e);
        }
    }

    public void create(Ammunition ammunition) {
        try {
            ammunitionService.create( ammunition);
            ammunitionView.printCreated(ammunition);
        } catch (Exception e) {
            LOGGER.warn("Input data for "+ReflectionUtils.getCurrentExecutingMethod() + " was incorrect");
            // throw new RuntimeException(e);
        }
    }


    public void computeTotalEquippedAmmunitionCost() {
        try {
            double l = ammunitionService.computeTotalEquippedAmmunitionCost();
            ammunitionView.printTotalEquippedAmmunitionCost(l);
        } catch (java.lang.Exception e) {
            LOGGER.warn("Input data for "+ReflectionUtils.getCurrentExecutingMethod() + " was incorrect");
            // throw new RuntimeException(e);
        }
    }

    public void getAmmunitionListSortedByWeight() {
        try {
            List<Ammunition> ammunitionListSortedByWeight = ammunitionService.getAmmunitionListSortedByWeight();
            ammunitionView.printAmmunitionSortedByWeight(ammunitionListSortedByWeight);
        } catch (java.lang.Exception e) {
            LOGGER.warn("Input data for "+ReflectionUtils.getCurrentExecutingMethod() + " was incorrect");
            // throw new RuntimeException(e);
        }
    }

    public void getAmmunitionListInCostRange(long inclusiveStart, long inclusiveEnd) {
        try {
            List<Ammunition> ammunitionListInCostRange = ammunitionService.getAmmunitionListInCostRange(inclusiveStart, inclusiveEnd);
            ammunitionView.printAmmunitionInCostRange(ammunitionListInCostRange, inclusiveStart,inclusiveEnd);
        } catch (java.lang.Exception e) {
            LOGGER.warn("Input data for "+ReflectionUtils.getCurrentExecutingMethod() + " was incorrect");
            // throw new RuntimeException(e);
        }
    }
}
