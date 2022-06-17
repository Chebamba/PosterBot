package com.chatbot.PosterBot.repository;

import com.chatbot.PosterBot.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByChatId(long chatId);
    void deleteByChatId(long chatId);
}
