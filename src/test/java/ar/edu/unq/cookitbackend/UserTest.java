package ar.edu.unq.cookitbackend;

import ar.edu.unq.cookitbackend.model.User;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

public class UserTest {

    @Test
    public void testGivenAUserWithEmailWhenRecieveGetEmailThenGiveHisEmail() {
        User user = User.builder()
                        .email("test@gmail.com")
                        .build();

        Assert.assertEquals("test@gmail.com", user.getEmail());
    }

    @Test
    public void testGivenAUserWithNameWhenRecieveGetNameThenGiveHisName() {
        User user = User.builder()
                .name("Facu")
                .build();

        Assert.assertEquals("Facu", user.getName());
    }

    @Test
    public void testGivenAUserWithLastnameWhenRecieveGetLastnameThenGiveHisLastname() {
        User user = User.builder()
                .lastname("Perez")
                .build();

        Assert.assertEquals("Perez", user.getLastname());
    }

    @Test
    public void testGivenAUserWithImageUrlWhenRecieveGetImageUrlThenGiveHisImageUrl() {
        User user = User.builder()
                .imageUrl("http://www.mifoto.com")
                .build();

        Assert.assertEquals("http://www.mifoto.com", user.getImageUrl());
    }
}
