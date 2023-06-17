package ru.smirnov.restaurant_voting.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.smirnov.restaurant_voting.model.DishRef;
import ru.smirnov.restaurant_voting.repository.DishRefRepository;
import ru.smirnov.restaurant_voting.util.JsonUtil;
import ru.smirnov.restaurant_voting.web.AbstractControllerTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.smirnov.restaurant_voting.web.restaurant.RestaurantTestData.*;
import static ru.smirnov.restaurant_voting.web.user.UserTestData.ADMIN_MAIL;
import static ru.smirnov.restaurant_voting.web.user.UserTestData.USER_MAIL;

class AdminDishRefControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminRestaurantController.REST_URL + '/';

    @Autowired
    private DishRefRepository dishRefRepository;

    private String getUrl(int restaurantId) {
        return REST_URL + restaurantId + "/dish-ref";
    }

    private String getUrl(int restaurantId, int id) {
        return getUrl(restaurantId) + '/' + id;
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(getUrl(MAC_ID, mac_fof.id())))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_REF_MATCHER.contentJson(mac_fof));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(getUrl(MAC_ID, mac_fof.id())))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(getUrl(MAC_ID, wasabi_rsh.id())))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(getUrl(WASABI_ID, wasabi_rsh.id())))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(dishRefRepository.findById(wasabi_rsh.id()).isPresent());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        DishRef updated = getUpdatedDish();
        updated.setId(null);
        perform(MockMvcRequestBuilders.put(getUrl(MAC_ID, mac_fof.id()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        DISH_REF_MATCHER.assertMatch(dishRefRepository.getExisted(mac_fof.id()), getUpdatedDish());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createWithLocation() throws Exception {
        DishRef newDishRef = getNewDish();
        ResultActions action = perform(MockMvcRequestBuilders.post(getUrl(MAC_ID))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDishRef)))
                .andExpect(status().isCreated());

        DishRef created = DISH_REF_MATCHER.readFromJson(action);
        int newId = created.id();
        newDishRef.setId(newId);
        DISH_REF_MATCHER.assertMatch(created, newDishRef);
        DISH_REF_MATCHER.assertMatch(dishRefRepository.getExisted(newId), newDishRef);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getByRestaurant() throws Exception {
        perform(MockMvcRequestBuilders.get(getUrl(MAC_ID)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_REF_MATCHER.contentJson(mac_fof, mac_chm20, mac_chb));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void enable() throws Exception {
        perform(MockMvcRequestBuilders.patch(getUrl(WASABI_ID, wasabi_rsh.id()))
                .param("enabled", "false")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertFalse(dishRefRepository.getExisted(wasabi_rsh.id()).isEnabled());
    }
}