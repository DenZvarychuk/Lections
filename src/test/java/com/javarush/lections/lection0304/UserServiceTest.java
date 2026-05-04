package com.javarush.lections.lection0304;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserValidator userValidator;
    @Spy
    private UserConvertor userConvertor;
    @Captor
    ArgumentCaptor<User> userArgumentCaptor;

    @Test
    void findByIdShouldReturnUser() {
        User actual = userService.findById(123);
        User expected = new User("John Doe");
        // check all field in separate method
        //assertEqualsUser(expected, actual);

        //if exist, use equals() + hashCode() from User
        assertEquals(expected, actual);
    }

    void assertEqualsUser(User expected, com.javarush.lections.lection0303.User actual) {
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void registerShouldThrowExceptionIfEmailExist() {

        UserService userService = new UserService(new UserRepository() {
            @Override
            public Optional<User> findByEmail(String email) {
                return Optional.of(new User("Alex"));
            }

            @Override
            public void save(User user) {

            }
        }, new UserValidator() {
            @Override
            public void validate(String email, String password) {

            }
        }, new UserConvertor());
        assertThrows(IllegalArgumentException.class,
                () -> userService.register("exist@email.com", "password"));
    }

    @Test
    void registerShouldThrowExceptionIfEmailExistWithMockito() {

        when(userRepository.findByEmail("exist@email.com"))
                .thenReturn(Optional.of(new User("Alex")));
        Mockito.lenient().doNothing().when(userRepository).save(any());

        assertThrows(IllegalArgumentException.class,
                () -> userService.register("exist@email.com", "password"));
        verify(userRepository, times(0)).save(any());
        verify(userRepository, never()).save(any());
        verify(userRepository, times(1)).findByEmail("exist@email.com");
        verifyNoMoreInteractions(userRepository);

    }

    @Test
    void registerShouldThrowExceptionIfEmailAndPasswordNotValid() {
        Mockito.doThrow(IllegalStateException.class)
                .when(userValidator)
                .validate(anyString(), anyString());

        assertThrows(IllegalStateException.class,
                () -> userService.register("exist@email.com", "password"));

        verifyNoMoreInteractions(userRepository);

    }

    @Test
    void test() {
        when(userRepository.findByEmail("exist@email.com"))
                .thenReturn(Optional.of(new User("Alex")))
                .thenReturn(Optional.of(new User("Ivan")));

        Optional<User> user = userRepository.findByEmail("exist@email.com");
        System.out.println(user.get().getName());
        Optional<User> user2 = userRepository.findByEmail("exist@email.com");
        System.out.println(user2.get().getName());
        Optional<User> user3 = userRepository.findByEmail("exist@email.com");
        System.out.println(user3.get().getName());
    }

    @Test
    void registerShouldThrowExceptionIfEmailExistWithMockito2() {

        when(userRepository.findByEmail("exist@email.com"))
                .thenAnswer(invocationOnMock -> {
                    String email = invocationOnMock.getArgument(0);
                    User user = new User("Alex");
                    user.setEmail(email);
                    return Optional.of(user);
                });
        Mockito.lenient().doNothing().when(userRepository).save(any());

        assertThrows(IllegalArgumentException.class,
                () -> userService.register("exist@email.com", "password"));
        verify(userRepository, times(0)).save(any());
        verify(userRepository, never()).save(any());
        verify(userRepository, times(1)).findByEmail("exist@email.com");
        verifyNoMoreInteractions(userRepository);

    }

    @Test
    void registerShouldBeSuccessful() {
        User user = new User("Alex");
        Mockito.when(userConvertor.convert(any())).thenReturn(user); // 1
        //Mockito.doReturn(user).when(userConvertor).convert(any()); // 2
        assertDoesNotThrow(
                () -> userService.register("exist@email.com", "password"));
        verify(userRepository).save(user);

    }

    @Test
    void registerShouldBeSuccessfulWithCaptor() {
        assertDoesNotThrow(
                () -> userService.register("exist@email.com", "password"));
        verify(userConvertor).convert(userArgumentCaptor.capture());

        User value = userArgumentCaptor.getValue();
        assertAll(
                () -> assertEquals("Alex", value.getName()),
                () -> assertEquals("exist@email.com", value.getEmail()),
                () -> assertEquals("password", value.getPassword())
        );
    }

    @Test
    void testMockStatic() {
        try (MockedStatic<UserUtil> userUtilMockedStatic = mockStatic(UserUtil.class);)
        {
            userUtilMockedStatic.when(UserUtil::createUser).thenReturn(new User("Alex2"));
            User user = UserUtil.createUser();
            assertEquals("Alex2", user.getName());
        }
    }
}

// !!! think before mock static variables !!!
// !!! it's not a good practice !!!
class UserUtil{
    public static User createUser() {
        return new User("Alex");
    }
}