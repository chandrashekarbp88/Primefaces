package com.chandru.primefaces.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.PhaseEvent;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean
@SessionScoped
public class AutoComplete {
	@ManagedProperty("#{playerDataSource}")
	private PlayerDataSource ds;

	private List<Player> players;

	public AutoComplete (){

	}

	public List<Player> complete(String query){
		// Assumed Datasource
		return ds.queryByName(query);
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public PlayerDataSource getDs() {
		return ds;
	}

	public void setDs(PlayerDataSource ds) {
		this.ds = ds;
	}

	public void handleSelect(SelectEvent e){
		Player p = (Player)e.getObject();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Add Player :: Player Info: ID :: "+ p.getPlayerId() + " :: Name :: "+p.getPlayerName(),""));
	}

	public void handleUnSelect(UnselectEvent e){
		Player p = (Player)e.getObject();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Remove Player :: Player Info: ID :: "+ p.getPlayerId() + " :: Name :: "+p.getPlayerName(),""));
	}
	
	/*public void handleChange(AjaxBehaviorEvent  e){
		int childCount = e.getComponent().getChildCount();
		
		e.getSource();
		String clientId = e.getComponent().getClientId();
		System.out.println("inside ajax event");
		Player p = (Player)((SelectEvent) e).getObject();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Remove Player :: Player Info: ID :: "+ p.getPlayerId() + " :: Name :: "+p.getPlayerName(),""));
	}*/
	
	
	public void handleChangeEvent(AjaxBehaviorEvent e){
		int childCount = e.getComponent().getChildCount();
		
		e.getSource();
		String clientId = e.getComponent().getClientId();
		System.out.println("inside ajax event");
		
	}
	
	

	public void phaseListener(PhaseEvent e){
		List<FacesMessage> messages = e.getFacesContext().getMessageList();
		System.out.println(messages.size());
	}

}
