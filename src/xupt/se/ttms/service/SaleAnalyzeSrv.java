package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Sale;
import xupt.se.ttms.model.SaleItem;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticket;

public class SaleAnalyzeSrv {
	public SaleAnalyzeSrv() {
		
	}
	
	public List<Play> getAllPlay() {
		PlaySrv playSrv = new PlaySrv();
		if(!playSrv.FetchAll().isEmpty())
			return playSrv.FetchAll();
		else
			return null;
	}
	
	public List<Studio> getAllStudio() {
		StudioSrv playSrv = new StudioSrv();
		if(!playSrv.FetchAll().isEmpty())
			return playSrv.FetchAll();
		else
			return null;
	}
	
	public List<Sale> getAllSale() {
		SaleSrv playSrv = new SaleSrv();
		if(!playSrv.FetchAll().isEmpty())
			return playSrv.FetchAll();
		else
			return null;
	}
	
	public List<SaleItem> getAllSaleItem() {
		SaleItemSrv sealItemSrv = new SaleItemSrv();
		if(!sealItemSrv.FetchAll().isEmpty())
			return sealItemSrv.FetchAll();
		else
			return null;
	}
	
	//总销售额
	public  int addAllSale() {
		List<Ticket> allPlayTicket = new TicketSrv().Fetch(" ticket_status = 2 ");
		int saleCount = 0;
		for(Ticket s : allPlayTicket) {
			saleCount += s.getTicket_price();
		}
		return saleCount;
	}
	
	public int addByPlay(int play_id) {
		int saleCount = 0;
		ScheduleSrv schedSrv = new ScheduleSrv();
		List<Schedule> allPlaySched =  schedSrv.Fetch("play_id = " + play_id);
		for(Schedule sched : allPlaySched) {
			List<Ticket> allPlayTicket = new TicketSrv().Fetch("sched_id = " + sched.getSched_id() + " and ticket_status = 2 ");
			for(Ticket ticket : allPlayTicket) {
				saleCount += ticket.getTicket_price();
			}
		}
		return saleCount;
	}
	
	public int addByStudio(int stu_id) {
		int saleCount = 0;
		ScheduleSrv schedSrv = new ScheduleSrv();
		List<Schedule> allPlaySched =  schedSrv.Fetch("studio_id = " + stu_id);
		for(Schedule sched : allPlaySched) {
			//获取该演出计划已卖出的票
			List<Ticket> allPlayTicket = new TicketSrv().Fetch("sched_id = " + sched.getSched_id() + " and ticket_status = 2 ");
			for(Ticket ticket : allPlayTicket) {
				saleCount += ticket.getTicket_price();
			}
		}
		return saleCount;
	}
	
}
