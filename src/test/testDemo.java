package test;

import org.junit.Test;

import xupt.se.ttms.dao.PlayDAO;
import xupt.se.ttms.model.Play;

public class testDemo {
	@Test
	public void test1() {
		PlayDAO playDao = new PlayDAO();

		Play play = new Play();
		play.setPlay_type_id(1);
		play.setLang_id(2);
		play.setPlay_name("哈哈");
		play.setPlay_introduction("dsad a");
		play.setPlay_image("dnsaid");
		play.setPlay_length(120);
		play.setPlay_ticket_price(20);
		play.setPlay_status(1);
		playDao.insert(play);
	}
}
