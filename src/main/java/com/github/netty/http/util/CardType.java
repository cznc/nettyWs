package com.github.netty.http.util;

import java.util.HashMap;
import java.util.Map;

public class CardType {
    public static final int              GLOBAL                 = 0, TV_RELATED = 1;
    
    public static final int              CARD_TYPE_BOWL         = 1, CARD_MESSAGE_BOWL = 1;
    public static final int              CARD_TYPE_SCROLL       = 2, CARD_MESSAGE_SCROLL = 9;
    public static final int              CARD_TYPE_GENERAL      = 3, CARD_MESSAGE_GENERAL = 8;
    public static final int              CARD_TYPE_COLUMNINFO   = 5, CARD_MESSAGE_COLUMNINFO = 12;
    public static final int              CARD_TYPE_SUBSCRIBE    = 6, CARD_MESSAGE_SUBSCRIBE = 10;
    public static final int              CARD_TYPE_GUIDE        = 7, CARD_MESSAGE_GUIDE = 11;
    public static final int              CARD_TYPE_MALL         = 10, CARD_message_MALL = 13;
    public static final int              CARD_TYPE_BARRAGE      = 11, CARD_MESSAGE_BARRAGE = 14;
    public static final int              CARD_TYPE_VOTE         = 13, CARD_MESSAGE_VOTE = 16;
    public static final int              CARD_TYPE_QUIZ         = 15, CARD_MESSAGE_QUIZ = 18;
    public static final int              CARD_TYPE_PURCHASE     = 16, CARD_MESSAGE_PURCHASE = 19;
    public static final int              CARD_TYPE_RAFFLES      = 14, CARD_MESSAGE_RAFFLES = 17;//抽奖
    public static final int              CARD_TYPE_LOTTERY      = 12, CARD_MESSAGE_LOTTERY = 15;//彩票


    private static Map<Integer, Integer> cardTypeMap          = new HashMap<Integer, Integer>();
    static {// cardtype msgtype
        cardTypeMap.put(CARD_TYPE_BOWL, CARD_MESSAGE_BOWL);// 滚球下注
        cardTypeMap.put(CARD_TYPE_SCROLL, CARD_MESSAGE_SCROLL);// 滚字卡片
        cardTypeMap.put(CARD_TYPE_GENERAL, CARD_MESSAGE_GENERAL);// 通用卡片
        cardTypeMap.put(CARD_TYPE_COLUMNINFO, CARD_MESSAGE_COLUMNINFO);// 节目信息
        cardTypeMap.put(CARD_TYPE_SUBSCRIBE, CARD_MESSAGE_SUBSCRIBE);// 预约
        cardTypeMap.put(CARD_TYPE_GUIDE, CARD_MESSAGE_GUIDE);// 即将播放
        cardTypeMap.put(CARD_TYPE_MALL, CARD_message_MALL);// 积分商城
        cardTypeMap.put(CARD_TYPE_BARRAGE, CARD_MESSAGE_BARRAGE);// 弹幕
        cardTypeMap.put(CARD_TYPE_VOTE, CARD_MESSAGE_VOTE);// 投票
        cardTypeMap.put(CARD_TYPE_LOTTERY, CARD_MESSAGE_LOTTERY);// 彩票
        cardTypeMap.put(CARD_TYPE_QUIZ, CARD_MESSAGE_QUIZ);// 竞猜答题
        cardTypeMap.put(CARD_TYPE_PURCHASE, CARD_MESSAGE_PURCHASE);// 抢购卡片
        cardTypeMap.put(CARD_TYPE_RAFFLES, CARD_MESSAGE_RAFFLES);// 抽奖
    }

    public static int toMsgType(int card_type) {
        return cardTypeMap.get(card_type);
    }
    public static int toCardType(int msg_type) {
    	for(int card_type : cardTypeMap.keySet()){
    		if(cardTypeMap.get(card_type) == msg_type){
    			return card_type;
    		}
    	}
    	return -1;
    }

}
