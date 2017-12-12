package edu.karazin.shop.repository;

import edu.karazin.shop.model.OrderStory;
import edu.karazin.shop.repository.util.BaseOrderStoryRepository;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public class OrderStoryRepositoryTest extends BaseOrderStoryRepository {

    @Autowired
    OrderStoryRepository orderStoryRepository;

    @Test
    public void shouldAddOrderStory(){
        OrderStory orderStory = getOrderStory(1);

        OrderStory save = orderStoryRepository.save(orderStory);
        OrderStory actual = orderStoryRepository.findOne(save.getId());

        assertEquals(save, actual);
    }

    @Test
    public void shouldUpdateOrderStory(){
        OrderStory orderStory = getOrderStory(1);

        OrderStory save1 = orderStoryRepository.save(orderStory);

        OrderStory save = orderStoryRepository.findOne(save1.getId());

        save1.setName("Test2");

        OrderStory save2 = orderStoryRepository.save(save1);

        OrderStory actual = orderStoryRepository.findOne(save2.getId());

        assertEquals("Test2", actual.getName());
        assertEquals(save.getId(), actual.getId());

    }

    @Test
    public void shouldDeleteOrderStory(){
        OrderStory orderStory = getOrderStory(1);

        OrderStory save = orderStoryRepository.save(orderStory);
        OrderStory actual = orderStoryRepository.findOne(save.getId());

        assertEquals(save, actual);

        orderStoryRepository.delete(actual.getId());
        OrderStory delete = orderStoryRepository.findOne(actual.getId());

        assertNull(delete);
    }

    @Test
    public void shouldFindAll(){
        OrderStory orderStory1 = getOrderStory(1);
        OrderStory orderStory2 = getOrderStory(2);

        orderStoryRepository.save(orderStory1);
        orderStoryRepository.save(orderStory2);

        List<OrderStory> orderStories = (List<OrderStory>) orderStoryRepository.findAll();

        assertEquals(orderStories.size(), 2);
    }

    @Test
    public void shouldFindAllByName(){
        OrderStory orderStory1 = getOrderStory(1);
        OrderStory orderStory2 = getOrderStory(2);

        OrderStory save1 = orderStoryRepository.save(orderStory1);
        OrderStory save2 = orderStoryRepository.save(orderStory2);

        List<OrderStory> orderStories = orderStoryRepository.findAllByName(save1.getName());

        assertEquals(orderStories.size(), 1);
    }

}
