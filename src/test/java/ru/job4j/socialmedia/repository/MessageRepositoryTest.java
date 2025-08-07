package ru.job4j.socialmedia.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.socialmedia.model.Message;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MessageRepositoryTest {

    @Autowired
    private MessageRepository messageRepository;

    @BeforeEach
    public void setUp() {
        messageRepository.deleteAll();
    }

    @BeforeAll
    public void clearRepositories() {
        messageRepository.deleteAll();
    }

    @Test
    public void whenDontSaveMessageAndFoundItByIdThenReturnEmptyOptional() {
        Optional<Message> foundMessage = messageRepository.findById(1L);
        assertThat(foundMessage).isEmpty();
    }

    @Test
    public void whenSaveMessageAndFoundItByIdThenReturnSavedMessage() {
        Message savedMessage = new Message();
        savedMessage.setContent("content");
        messageRepository.save(savedMessage);
        Optional<Message> foundMessage = messageRepository.findById(savedMessage.getId());
        assertThat(foundMessage).isPresent();
        assertThat(foundMessage.get().getContent()).isEqualTo("content");
    }

    @Test
    public void whenDontSavedMessagesAndFoundAllThenReturnEmptyCollection() {
        List<Message> messages = (List) messageRepository.findAll();
        assertThat(messages).isEmpty();
    }

    @Test
    public void whenSavedMessagesAndFoundAllThenReturnCollectionWithSavedMessages() {
        Message savedMessage1 = new Message();
        savedMessage1.setContent("content1");
        Message savedMessage2 = new Message();
        savedMessage2.setContent("content2");
        messageRepository.save(savedMessage1);
        messageRepository.save(savedMessage2);
        List<Message> messages = (List) messageRepository.findAll();
        assertThat(messages)
                .isNotEmpty()
                .hasSize(2)
                .containsExactlyInAnyOrder(savedMessage1, savedMessage2);
    }

    @Test
    public void whenSavedMessageAndDeleteItThenReturnEmptyOptional() {
        Message savedMessage = new Message();
        savedMessage.setContent("content");
        messageRepository.save(savedMessage);
        messageRepository.deleteById(savedMessage.getId());
        Optional<Message> foundMessage = messageRepository.findById(savedMessage.getId());
        assertThat(foundMessage).isEmpty();
    }

    @Test
    public void whenSavedMessagesAndDeleteAllThenReturnEmptyCollection() {
        Message savedMessage1 = new Message();
        savedMessage1.setContent("content1");
        Message savedMessage2 = new Message();
        savedMessage2.setContent("content2");
        messageRepository.save(savedMessage1);
        messageRepository.save(savedMessage2);
        messageRepository.deleteAll();
        List<Message> messages = (List) messageRepository.findAll();
        assertThat(messages)
                .isEmpty();
    }
}