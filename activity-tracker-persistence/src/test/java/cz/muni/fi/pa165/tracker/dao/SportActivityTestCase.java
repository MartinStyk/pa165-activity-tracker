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
 * Almoust test implemented except testUpdateNotExistingSportActivity
 * Not sure about behivior of aplication
 * 
 * @author Petra Ondřejková
 * @version 24.10.2016
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

    @Test(expectedExceptions = ValidationException.class)
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

    @Test(expectedExceptions = {ConstraintViolationException.class, TransactionSystemException.class})
    public void testUpdateCalorieFactorNull() {
        sportActivityDao.create(football);
        football.setCaloriesFactor(null);

        SportActivity updated = sportActivityDao.update(football);
    }

    @Test (expectedExceptions = {ConstraintViolationException.class, TransactionSystemException.class})
    public void testUpdateCalorieFactorNegative() {
        sportActivityDao.create(football);
        football.setCaloriesFactor(-1.5);

        SportActivity updated = sportActivityDao.update(football);
    }

    @Test
    public void testUpdateNotExistingSportActivity() {
        sportActivityDao.create(football);
        SportActivity updated = sportActivityDao.update(biathlon);
        Assert.assertEquals(sportActivityDao.findAll().size(), 2);
    }

    @Test
    public void testFindSportActivityByID() {
        sportActivityDao.create(football);
        sportActivityDao.create(biathlon);

        SportActivity result1 = sportActivityDao.findById(football.getId());
        assertDeepEquals(result1, football);

        SportActivity result2 = sportActivityDao.findById(biathlon.getId());
        assertDeepEquals(result2, biathlon);
    }

    @Test (expectedExceptions = DataAccessException.class)
    public void testFindSportActivityByIDNull() {
        sportActivityDao.create(football);
        sportActivityDao.create(biathlon);
        SportActivity result1 = sportActivityDao.findById(null);
    }

    @Test
    public void testFindAll() {
        sportActivityDao.create(football);
        sportActivityDao.create(biathlon);
        Assert.assertEquals(sportActivityDao.findAll().size(), 2);
    }

    @Test
    public void testFindAllEmpty() {
        Assert.assertEquals(sportActivityDao.findAll().size(), 0);
    }

    @Test
    public void testFindSportActivityByName() {
        sportActivityDao.create(football);
        sportActivityDao.create(biathlon);
        SportActivity result1 = sportActivityDao.findByName(football.getName());
        assertDeepEquals(result1, football);
        SportActivity result2 = sportActivityDao.findByName(biathlon.getName());
        assertDeepEquals(result2, biathlon);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testFindSportActivityByNameNull() {
        sportActivityDao.create(football);
        sportActivityDao.create(biathlon);
        SportActivity result = sportActivityDao.findByName(null);
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


private void assertDeepEquals(SportActivity sport1, SportActivity sport2) {
        Assert.assertEquals(sport1, sport2);
        Assert.assertEquals(sport1.getId(), sport2.getId());
        Assert.assertEquals(sport1.getName(), sport2.getName());
        Assert.assertEquals(sport1.getCaloriesFactor(), sport2.getCaloriesFactor());
    }
}
