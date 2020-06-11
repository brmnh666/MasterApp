package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;
import java.util.List;

public class MyServiceArea implements Serializable {

        private int Item2;
        private List<Address> Item1;

        public int getItem2() {
            return Item2;
        }

        public void setItem2(int Item2) {
            this.Item2 = Item2;
        }

        public List<Address> getItem1() {
            return Item1;
        }

        public void setItem1(List<Address> Item1) {
            this.Item1 = Item1;
        }
}
