package cz.muni.fi.pa165.tracker.dao;


import cz.muni.fi.pa165.tracker.PersistenceApplicationContext;
import cz.muni.fi.pa165.tracker.entity.SportActivity;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.TransactionSystemException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import java.util.List;
import javax.validation.ValidationException;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * Test class for SportActivity
 * TODO implements all tests
 * 
 * @author Petra Ondřejková
 * @version 22.10.2016
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SportActivityTestCase extends AbstractTestNGSpringContextTests {

    @Inject
    private SportActivityDao sportActivityDao;
    
    private SportActivity football;
    private SportActivity biathlon;
    private SportActivity nameNull;
    private SportActivity caloriesFactorNull;
    private SportActivity negativeCalorieFactor;

    @BeforeMethod
    public void createSports() {
        football = new SportActivity();
        football.setName("Football");
        football.setCaloriesFactor(30.1);

        biathlon = new SportActivity();
        biathlon.setName("Biathlon");
        biathlon.setCaloriesFactor(20.1);

        nameNull = new SportActivity();
        nameNull.setName(null);
        nameNull.setCaloriesFactor(0.1);

        caloriesFactorNull = new SportActivity();
        caloriesFactorNull.setName("CaloriesFactorNull");
        caloriesFactorNull.setCaloriesFactor(null);

        negativeCalorieFactor = new SportActivity();
        negativeCalorieFactor.setName("NegativeCalorieFactor");
        negativeCalorieFactor.setCaloriesFactor(-10.1);
    }

    @Test
    public void testCreate() {
       sportActivityDao.create(football);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testCreateSportActivityNull(){
        sportActivityDao.create(null);
    }

    @Test(expectedExceptions = ValidationException.class)
    public void testCreateNullName() {
       sportActivityDao.create(nameNull);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testCreateNullCalorieFactor() {
       sportActivityDao.create(caloriesFactorNull);
    }
    
    @Test(expectedExceptions = ValidationException.class)
    public void testCreateNegativeCalorieFactor() {
       sportActivityDao.create(negativeCalorieFactor);
    }

    @Test
    public void testUpdate() {
        sportActivityDao.create(football);
        football.setCaloriesFactor(50.6);

        SportActivity updated = sportActivityDao.update(football);
        Assert.assertEquals(updated.getCaloriesFactor(), 50.6);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testUpdateNull() {
        sportActivityDao.create(football);
        SportActivity updated = sportActivityDao.update(null);
    }

    @Test(expectedExceptions = {ConstraintViolationException.class, TransactionSystemException.class})
    public void testUpdateNameNull() {
        sportActivityDao.create(football);
        football.setName(null);

        SportActivity updated = sportActivityDao.update(football);
    }

    @Test
    public void testUpdateCalorieFactorNull() {
        Assert.fail("todo...");
        //sportActivityDao.create(football);
        //football.setCaloriesFactor(null);

        //SportActivity updated = sportActivityDao.update(football);
    }

    @Test (expectedExceptions = {ConstraintViolationException.class, TransactionSystemException.class})
    public void testUpdateCalorieFactorNegative() {
        sportActivityDao.create(football);
        football.setCaloriesFactor(-1.5);

        SportActivity updated = sportActivityDao.update(football);
    }

    @Test
    public void testUpdateNotExistingSportActivity() {
        Assert.fail("todo...");
        //sportActivityDao.create(football);
        //SportActivity updated = sportActivityDao.update(biathlon);
    }

    @Test
    public void testFindSportActivityByID() {
        Assert.fail("todo...");
    }

    @Test
    public void testFindSportActivityByIDNull() {
        Assert.fail("todo...");
    }

    @Test
    public void testFindAll() {
        Assert.fail("todo...");
    }

    @Test
    public void testFindAllEmpty() {
        Assert.assertEquals(sportActivityDao.findAll().size(), 0);
    }

    @Test
    public void testFindSportActivityByName() {
        Assert.fail("todo...");
    }

    @Test
    public void testFindSportActivityByNameNull() {
        Assert.fail("todo...");
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testFindSportActivityByNotExistingName() {
        sportActivityDao.create(football);
        SportActivity find = sportActivityDao.findByName("biathlon");
    }

    @Test
    public void testRemove() {
        sportActivityDao.create(football);
        sportActivityDao.create(biathlon);
        Assert.assertEquals(sportActivityDao.findAll().size(), 2);

        sportActivityDao.remove(biathlon);
        List<SportActivity> result = sportActivityDao.findAll();
        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(result.get(0).getCaloriesFactor(), football.getCaloriesFactor());
        Assert.assertEquals(result.get(0).getName(), football.getName());
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testRemoveNull() {
        sportActivityDao.create(football);
        sportActivityDao.create(biathlon);

        sportActivityDao.remove(null);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testRemoveNotExistingSportActivity() {
        sportActivityDao.create(football);
        sportActivityDao.remove(biathlon);
    }
}
