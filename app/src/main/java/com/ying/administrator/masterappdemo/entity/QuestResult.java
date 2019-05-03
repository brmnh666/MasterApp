package com.ying.administrator.masterappdemo.entity;

import java.util.List;

public class QuestResult {

    /**
     * Item1 : 60
     * Item2 : [{"Id":1,"QuesID":1,"QuesType":"1","QuesCategory":287,"Content":"灭霸怎么死的","Case1":"A","Case2":"B","Case3":"C","Case4":"D","Case5":"E","Answer":"A","Jieshi":null,"UseAnswer":"B","Value":5,"IsUse":"Y","page":1,"limit":999,"Version":0},{"Id":2,"QuesID":2,"QuesType":"1","QuesCategory":287,"Content":"测试","Case1":"A","Case2":"B","Case3":"C","Case4":"D","Case5":"E","Answer":"B","Jieshi":null,"UseAnswer":"C","Value":5,"IsUse":"Y","page":1,"limit":999,"Version":0},{"Id":3,"QuesID":3,"QuesType":"1","QuesCategory":287,"Content":"测试","Case1":"A","Case2":"B","Case3":"C","Case4":"D","Case5":"E","Answer":"C","Jieshi":null,"UseAnswer":"B","Value":5,"IsUse":"Y","page":1,"limit":999,"Version":0},{"Id":4,"QuesID":4,"QuesType":"1","QuesCategory":287,"Content":"测试","Case1":"A","Case2":"B","Case3":"C","Case4":"D","Case5":"E","Answer":"A","Jieshi":null,"UseAnswer":"C","Value":5,"IsUse":"Y","page":1,"limit":999,"Version":0},{"Id":5,"QuesID":5,"QuesType":"1","QuesCategory":287,"Content":"测试","Case1":"A","Case2":"B","Case3":"C","Case4":"D","Case5":"E","Answer":"B","Jieshi":null,"UseAnswer":"B","Value":5,"IsUse":"Y","page":1,"limit":999,"Version":0},{"Id":6,"QuesID":6,"QuesType":"2","QuesCategory":287,"Content":"测试","Case1":"A","Case2":"B","Case3":"C","Case4":"D","Case5":"E","Answer":"C","Jieshi":null,"UseAnswer":"B","Value":10,"IsUse":"Y","page":1,"limit":999,"Version":0},{"Id":7,"QuesID":7,"QuesType":"2","QuesCategory":287,"Content":"测试","Case1":"A","Case2":"B","Case3":"C","Case4":"D","Case5":"E","Answer":"A","Jieshi":null,"UseAnswer":"B","Value":10,"IsUse":"Y","page":1,"limit":999,"Version":0},{"Id":8,"QuesID":8,"QuesType":"2","QuesCategory":287,"Content":"测试","Case1":"A","Case2":"B","Case3":"C","Case4":"D","Case5":"E","Answer":"B","Jieshi":null,"UseAnswer":"B","Value":10,"IsUse":"Y","page":1,"limit":999,"Version":0},{"Id":9,"QuesID":9,"QuesType":"2","QuesCategory":287,"Content":"测试","Case1":"A","Case2":"B","Case3":"C","Case4":"D","Case5":"E","Answer":"C","Jieshi":null,"UseAnswer":"B","Value":10,"IsUse":"Y","page":1,"limit":999,"Version":0},{"Id":10,"QuesID":10,"QuesType":"2","QuesCategory":287,"Content":"测试","Case1":"A","Case2":"B","Case3":"C","Case4":"D","Case5":"E","Answer":"A","Jieshi":null,"UseAnswer":"B","Value":10,"IsUse":"Y","page":1,"limit":999,"Version":0},{"Id":11,"QuesID":11,"QuesType":"2","QuesCategory":287,"Content":"测试","Case1":"A","Case2":"B","Case3":"C","Case4":"D","Case5":"E","Answer":"B","Jieshi":null,"UseAnswer":"B","Value":15,"IsUse":"Y","page":1,"limit":999,"Version":0},{"Id":12,"QuesID":12,"QuesType":"2","QuesCategory":287,"Content":"测试","Case1":"A","Case2":"B","Case3":"C","Case4":"D","Case5":"E","Answer":"C","Jieshi":null,"UseAnswer":"B","Value":15,"IsUse":"Y","page":1,"limit":999,"Version":0},{"Id":13,"QuesID":13,"QuesType":"2","QuesCategory":287,"Content":"测试","Case1":"A","Case2":"B","Case3":"C","Case4":"D","Case5":"E","Answer":"B","Jieshi":null,"UseAnswer":"B","Value":15,"IsUse":"Y","page":1,"limit":999,"Version":0},{"Id":14,"QuesID":14,"QuesType":"2","QuesCategory":287,"Content":"测试","Case1":"A","Case2":"B","Case3":"C","Case4":"D","Case5":"E","Answer":"A","Jieshi":null,"UseAnswer":"B","Value":15,"IsUse":"Y","page":1,"limit":999,"Version":0},{"Id":15,"QuesID":15,"QuesType":"2","QuesCategory":287,"Content":"测试","Case1":"A","Case2":"B","Case3":"C","Case4":"D","Case5":"E","Answer":"B","Jieshi":null,"UseAnswer":"B","Value":15,"IsUse":"Y","page":1,"limit":999,"Version":0}]
     */

    private int Item1;
    private List<QuestBean> Item2;

    public int getItem1() {
        return Item1;
    }

    public void setItem1(int Item1) {
        this.Item1 = Item1;
    }

    public List<QuestBean> getItem2() {
        return Item2;
    }

    public void setItem2(List<QuestBean> Item2) {
        this.Item2 = Item2;
    }

}
