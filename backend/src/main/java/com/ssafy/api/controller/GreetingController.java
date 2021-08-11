package com.ssafy.api.controller;

import com.ssafy.api.domain.Chat;
import com.ssafy.api.domain.Greeting;
import com.ssafy.api.domain.HelloMessage;
import com.ssafy.db.entity.Dm;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {
//	@MessageMapping("/hello")
//	@SendTo("/topic/greetings")
//	public Greeting greeting(HelloMessage message) throws Exception {
//		Thread.sleep(100); // delay
//		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//	}
	
	@MessageMapping("/{room}")
	@SendTo("/topic/{room}")
	public Chat chat(Chat chat) throws Exception {
		return new Chat(chat.getName(), chat.getMessage());
	}

	@MessageMapping("/dm/{room}")
	@SendTo("/topic/dm/{room}")
	public Chat dmChat(Dm dm) throws Exception {
		System.out.println(dm.getRoomId());
		System.out.println(dm.getUser());
		System.out.println(dm.getMessage());

		return new Chat(dm.getUser(), dm.getMessage());
	}
}
