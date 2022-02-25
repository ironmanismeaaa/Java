package com.scnu.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.scnu.element.ElementBase;
import com.scnu.manager.ElementManager;
import com.scnu.manager.GameElement;

public class GameListener implements KeyListener{
	private ElementManager em=ElementManager.getManager();
	private Set<Integer> set=new HashSet<Integer>();
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		List<ElementBase> play=em.getElementsByKey(GameElement.PLAY);
		int key=e.getKeyCode();
		if(set.contains(key)){
			return;
		}
		set.add(key);
		for(ElementBase obj:play){
			obj.keyClick(true, e.getKeyCode());
			System.out.println("ÞôÏÂ"+e.getKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();
		set.remove(key);
		List<ElementBase> play=em.getElementsByKey(GameElement.PLAY);	
		for(ElementBase obj:play){
			obj.keyClick(false, key);
			//System.out.println("Í£Ö¹"+key);
		}
		if(!set.isEmpty()){
			for(Integer i:set){
				for(ElementBase obj:play){
					obj.keyClick(true, i);
					//System.out.println("Í£Ö¹"+key);
				}
			}
		}
	}

}
