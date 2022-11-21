
//Aмуніція

package org.example.entity;

public abstract class Ammunition extends Entity implements Costable, Weightable, Durable {
    private String name;
    private final double cost;
    private final double weight;

    private final double durability;

    public Ammunition(String name, double cost, double weight, double durability) {
        this.name = name;
        this.cost = cost;
        this.weight = weight;
        this.durability = durability;
    }

    public String getName() {
        return name;
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public double getDurability() {
        return durability;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.getClass().getSimpleName())
                .append(" ")
                .append(name)
                .append(" [id=")
                .append(id)
                .append(", cost=")
                .append(String.format("%.2f", cost))
                .append(", weight=")
                .append(String.format("%.2f", weight))
                .append(", ")
                .toString();
    }
}

//Нагрудник

package org.example.entity;

public class ChestPlate extends Ammunition implements Protectable {
    private final double protection;

    public ChestPlate(String name, double cost, double weight, double durability, double protection) {
        super(name, cost, weight, durability);
        this.protection = protection;
    }

    @Override
    public double getProtection() {
        return protection;
    }

    @Override
    public String toString() {
        return super.toString() +
                "protection=" + String.format("%.2f",protection)  +
                ']';
    }
}


//Шолом

package org.example.entity;

public class Helmet extends Ammunition {
    private final VisionLevel visionLevel;

    public Helmet(String name, double cost, double weight, double durability, VisionLevel visionLevel) {
        super(name, cost, weight, durability);
        this.visionLevel = visionLevel;
    }

    public VisionLevel getVisionLevel() {
        return visionLevel;
    }

    @Override
    public String toString() {
        return super.toString() + "vision level " + visionLevel + "]";
    }
}


//Щит

package org.example.entity;

public class Shield extends Ammunition implements Protectable{
    private final double protection;

    public Shield(String name, double cost, double weight, double durability, double protection) {
        super(name, cost, weight, durability);
        this.protection = protection;
    }

    @Override
    public double getProtection() {
        return protection;
    }

    @Override
    public String toString() {
        return super.toString() +
                "protection=" + String.format("%.2f",protection) +
                ']';
    }
}

//Меч

package org.example.entity;

public class Sword extends Ammunition implements Damageable{
    private final double damage;

    public Sword(String name, double cost, double weight, double durability, double damage) {
        super(name, cost, weight, durability);
        this.damage = damage;
    }

    @Override
    public double getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return super.toString()+ "damage=" + damage +
                ']';
    }
}

//AbstractDao

package org.example.dao;

import org.example.entity.Entity;


import java.util.ArrayList;
import java.util.List;

public class AbstractDao<T extends Entity> implements IDao<T> {
    List<T> storage;

    public AbstractDao(List<T> storage) {
        this.storage = storage;
    }

    public AbstractDao() {
        storage = new ArrayList<>();
    }

    @Override
    public List<T> findAll() throws Exception {
        return new ArrayList<>(storage);
    }

    @Override
    public T findEntityById(int id) throws Exception {
        return storage.get(id);
    }

    @Override
    public boolean delete(T t) throws Exception {
        return storage.remove(t);
    }

    @Override
    public boolean delete(int id) throws Exception {
        storage.remove(id);
        return true;
    }

    @Override
    public boolean create(T t) throws Exception {
        t.setId(storage.size());
        return storage.add(t);
    }
}

//Сервіс із реалізованою бізнес-логікою AmmunitionService

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

//Контроллер AmmunitionController

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


//AmmunitionView

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


//Реалізація патерну Команда

//Базовий інтерфейс IСommand:

package org.example.command;

public interface ICommand {
    void execute() throws Exception;
}

//Абстрактний клас AbstractCommand:

package org.example.command;



import org.example.controller.AmmunitionController;
import org.example.singleton.ScannerSingleton;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.Scanner;


public abstract class AbstractCommand implements ICommand {
    protected final static Logger LOGGER = LoggerFactory.getLogger(AbstractCommand.class.getSimpleName());
    protected final Scanner scanner = ScannerSingleton.getInstance().getScanner();
    protected AmmunitionController ammunitionController;

    public AbstractCommand(AmmunitionController ammunitionController) {
        this.ammunitionController = ammunitionController;
    }

    @Override
    public void execute() {
        LOGGER.info(this.getClass().getSimpleName() + " is executed");
    }
}

//Імплементації команд:

package org.example.command;

import org.example.controller.AmmunitionController;
import org.example.entity.AmmunitionFactory;

public class CraftAmmunitionCommand extends AbstractCommand {
    AmmunitionFactory ammunitionFactory;

    public CraftAmmunitionCommand(AmmunitionController ammunitionController, AmmunitionFactory ammunitionFactory) {
        super(ammunitionController);
        this.ammunitionFactory = ammunitionFactory;
    }


    @Override
    public void execute() {
        super.execute();
        ammunitionController.create(ammunitionFactory.createRandomAmmunition());
    }
}

package org.example.command;

import org.example.controller.AmmunitionController;

public class DeleteAmmunitionCommandById extends AbstractCommand {

    public DeleteAmmunitionCommandById(AmmunitionController ammunitionController) {
        super(ammunitionController);
    }

    @Override
    public void execute() {
        super.execute();
        System.out.println("Enter ammunition ID to be deleted: ");
        int nextInt = scanner.nextInt();
        ammunitionController.delete(nextInt);
    }
}

package org.example.command;

import org.example.controller.AmmunitionController;

public class EmptyCommand extends AbstractCommand {

    public EmptyCommand(AmmunitionController ammunitionController) {
        super(ammunitionController);
    }

    @Override
    public void execute() {
        super.execute();
        System.out.println("Command by this ID does not exist...");
    }
}

package org.example.command;

import org.example.controller.AmmunitionController;

public class ExitProgramCommand extends AbstractCommand {


    public ExitProgramCommand(AmmunitionController ammunitionController) {
        super(ammunitionController);
    }

    @Override
    public void execute() {
        super.execute();
        scanner.close();
        System.exit(0);
    }
}

package org.example.command;

import org.example.controller.AmmunitionController;

public class PrintAllAmmunitionInCostRangeCommand extends AbstractCommand {

    public PrintAllAmmunitionInCostRangeCommand(AmmunitionController ammunitionController) {
        super(ammunitionController);
    }

    @Override
    public void execute() {
        super.execute();
        System.out.println("Enter inclusive start and end boundaries for ammunition cost to be printed: ");
        int start = scanner.nextInt();
        int end = scanner.nextInt();
        ammunitionController.getAmmunitionListInCostRange(start, end);
    }
}

package org.example.command;

import org.example.controller.AmmunitionController;

public class PrintAllAmmunitionSortedByWeightCommand extends AbstractCommand {

    public PrintAllAmmunitionSortedByWeightCommand(AmmunitionController ammunitionController) {
        super(ammunitionController);
    }

    @Override
    public void execute() {
        super.execute();
        ammunitionController.getAmmunitionListSortedByWeight();
    }
}

package org.example.command;

import org.example.controller.AmmunitionController;

public class PrintAmmunitionByIdCommand extends AbstractCommand {

    public PrintAmmunitionByIdCommand(AmmunitionController ammunitionController) {
        super(ammunitionController);
    }

    @Override
    public void execute() {
        super.execute();
        System.out.println("Enter ammunition ID to be printed: ");
        int nextInt = scanner.nextInt();
        ammunitionController.findEntityById(nextInt);
    }
}

package org.example.command;

import org.example.controller.AmmunitionController;

public class PrintTotalAmmunitionCommand extends AbstractCommand {

    public PrintTotalAmmunitionCommand(AmmunitionController ammunitionController) {
        super(ammunitionController);
    }

    @Override
    public void execute() {
        super.execute();
        ammunitionController.findAll();
    }
}

package org.example.command;

import org.example.controller.AmmunitionController;

public class PrintTotalAmmunitionCostCommand extends AbstractCommand {

    public PrintTotalAmmunitionCostCommand(AmmunitionController ammunitionController) {
        super(ammunitionController);
    }

    @Override
    public void execute() {
        super.execute();
        ammunitionController.computeTotalEquippedAmmunitionCost();
    }
}

